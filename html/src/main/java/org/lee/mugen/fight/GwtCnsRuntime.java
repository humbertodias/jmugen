package org.lee.mugen.fight;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Phase 2 hybrid CNS runtime: enter statedefs and tick a subset of StateCtrls
 * (ChangeState, ChangeAnim, VelSet, CtrlSet) with basic triggers.
 */
public final class GwtCnsRuntime {

    public interface Host {
        int getStateNo();

        int getStateTime();

        int getAnimElem();

        /** Mugen {@code AnimTime} (0 on last frame, negative after). */
        int getAnimTime();

        boolean isMoveContact();

        boolean isHitPause();

        int getFacing();

        void setAnim(int anim);

        void setAnimElem(int elem1Based);

        /** World velocities; runtime multiplies X by facing when CNS uses relative vel. */
        void setVel(float vx, float vy);

        void setCtrl(boolean ctrl);

        /** Apply ChangeState side-effects (stand/crouch/attack). */
        void applyChangeState(int stateNo, int ctrl);
    }

    private static final Pattern TIME_EQ = Pattern.compile("^time\\s*=\\s*(-?\\d+)$");
    private static final Pattern ANIMTIME_EQ = Pattern.compile("^animtime\\s*=\\s*(-?\\d+)$");
    private static final Pattern ANIMELEM_EQ = Pattern.compile("^animelem\\s*=\\s*(\\d+)$");
    private static final Pattern ANIMELEMTIME =
            Pattern.compile("animelemtime\\s*\\(\\s*(\\d+)\\s*\\)\\s*(<=|>=|<|>|=)\\s*(-?\\d+)");

    private final GwtCnsData data;
    private int stateNo;
    private int stateTime;
    private final Set<Integer> firedOnce = new HashSet<Integer>();

    public GwtCnsRuntime(GwtCnsData data) {
        this.data = data != null ? data : new GwtCnsData();
        this.stateNo = 0;
    }

    public GwtCnsData getData() {
        return data;
    }

    public int getStateNo() {
        return stateNo;
    }

    public int getStateTime() {
        return stateTime;
    }

    public void changeState(int newState, Host host) {
        stateNo = newState;
        stateTime = 0;
        firedOnce.clear();
        GwtCnsData.StateMeta meta = data.getState(newState);
        if (meta == null || host == null) {
            return;
        }
        if (meta.anim >= 0) {
            host.setAnim(meta.anim);
        } else if (newState >= 0) {
            host.setAnim(newState);
        }
        if (meta.hasVelset) {
            host.setVel(meta.velsetX * host.getFacing(), meta.velsetY);
        }
        if (meta.ctrl >= 0) {
            host.setCtrl(meta.ctrl != 0);
        }
    }

    /** Call once per fight tick while this statedef should run. */
    public void tick(Host host) {
        if (host == null) {
            return;
        }
        GwtCnsData.StateMeta meta = data.getState(stateNo);
        if (meta == null) {
            stateTime++;
            return;
        }
        boolean paused = host.isHitPause();
        for (int i = 0; i < meta.controllers.size(); i++) {
            GwtCnsData.StateCtrl ctrl = meta.controllers.get(i);
            if (paused && !ctrl.ignoreHitPause) {
                continue;
            }
            if (ctrl.persistent == 0 && firedOnce.contains(i)) {
                continue;
            }
            if (!triggersPass(ctrl, host)) {
                continue;
            }
            if (ctrl.persistent == 0) {
                firedOnce.add(i);
            }
            execute(ctrl, host);
            // ChangeState may have switched statedefs mid-tick — stop this frame.
            if ("changestate".equals(ctrl.type) || "selfstate".equals(ctrl.type)) {
                break;
            }
        }
        if (!paused) {
            stateTime++;
        }
    }

    private void execute(GwtCnsData.StateCtrl ctrl, Host host) {
        String type = ctrl.type;
        Map<String, String> p = ctrl.params;
        if ("changestate".equals(type) || "selfstate".equals(type)) {
            int value = parseInt(p.get("value"), stateNo);
            int ctrlFlag = parseInt(p.get("ctrl"), -1);
            changeState(value, host);
            host.applyChangeState(value, ctrlFlag);
        } else if ("changeanim".equals(type) || "changeanim2".equals(type)) {
            int value = parseInt(p.get("value"), -1);
            int elem = parseInt(p.get("elem"), -1);
            if (value >= 0) {
                host.setAnim(value);
            }
            if (elem > 0) {
                host.setAnimElem(elem);
            }
        } else if ("velset".equals(type)) {
            float x = parseFloat(p.get("x"), Float.NaN);
            float y = parseFloat(p.get("y"), Float.NaN);
            float vx = Float.isNaN(x) ? 0f : x * host.getFacing();
            float vy = Float.isNaN(y) ? 0f : y;
            // If only one component given, preserve other via NaN sentinel — host replaces both.
            if (Float.isNaN(x) && Float.isNaN(y)) {
                return;
            }
            if (Float.isNaN(x)) {
                vx = 0f; // leave X unset → 0 relative; player may merge
            }
            if (Float.isNaN(y)) {
                vy = 0f;
            }
            host.setVel(vx, vy);
        } else if ("veladd".equals(type)) {
            float x = parseFloat(p.get("x"), 0f) * host.getFacing();
            float y = parseFloat(p.get("y"), 0f);
            // Host only has setVel; veladd is approximated as set for Phase 2 if both given.
            host.setVel(x, y);
        } else if ("ctrlset".equals(type)) {
            host.setCtrl(parseInt(p.get("value"), 0) != 0);
        }
        // hitdef / playsnd / width / sprpriority: handled elsewhere or ignored for now
    }

    private boolean triggersPass(GwtCnsData.StateCtrl ctrl, Host host) {
        for (String t : ctrl.triggerAll) {
            if (!evalExpr(t, host)) {
                return false;
            }
        }
        if (ctrl.triggerGroups.isEmpty()) {
            return true;
        }
        for (List<String> group : ctrl.triggerGroups) {
            boolean ok = true;
            for (String t : group) {
                if (!evalExpr(t, host)) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                return true;
            }
        }
        return false;
    }

    private boolean evalExpr(String raw, Host host) {
        if (raw == null) {
            return false;
        }
        String expr = raw.trim().toLowerCase(Locale.ROOT);
        if (expr.isEmpty() || "1".equals(expr) || "true".equals(expr)) {
            return true;
        }
        if ("0".equals(expr) || "false".equals(expr)) {
            return false;
        }
        // Split && (common in KFM ChangeAnim movecontact)
        if (expr.indexOf("&&") >= 0) {
            String[] parts = expr.split("&&");
            for (int i = 0; i < parts.length; i++) {
                if (!evalSimple(parts[i].trim(), host)) {
                    return false;
                }
            }
            return true;
        }
        return evalSimple(expr, host);
    }

    private boolean evalSimple(String expr, Host host) {
        if ("movecontact".equals(expr)) {
            return host.isMoveContact();
        }
        if ("movehit".equals(expr)) {
            return host.isMoveContact();
        }
        Matcher m = TIME_EQ.matcher(expr);
        if (m.matches()) {
            return host.getStateTime() == Integer.parseInt(m.group(1));
        }
        m = ANIMTIME_EQ.matcher(expr);
        if (m.matches()) {
            return host.getAnimTime() == Integer.parseInt(m.group(1));
        }
        m = ANIMELEM_EQ.matcher(expr);
        if (m.matches()) {
            return host.getAnimElem() == Integer.parseInt(m.group(1));
        }
        m = ANIMELEMTIME.matcher(expr);
        if (m.find()) {
            // Approximate: use AnimElem relative time via elem index comparison.
            int elem = Integer.parseInt(m.group(1));
            String op = m.group(2);
            int rhs = Integer.parseInt(m.group(3));
            int cur = host.getAnimElem();
            // AnimElemTime(N) > 0 ≈ currently at or past elem N
            // AnimElemTime(N) <= 0 ≈ not yet reached / just at start of N
            int approx = cur - elem;
            return compare(approx, op, rhs);
        }
        // Unsupported trigger → false (safe)
        return false;
    }

    private static boolean compare(int left, String op, int right) {
        if ("=".equals(op)) {
            return left == right;
        }
        if ("<".equals(op)) {
            return left < right;
        }
        if (">".equals(op)) {
            return left > right;
        }
        if ("<=".equals(op)) {
            return left <= right;
        }
        if (">=".equals(op)) {
            return left >= right;
        }
        return false;
    }

    private static int parseInt(String raw, int def) {
        if (raw == null || raw.trim().isEmpty()) {
            return def;
        }
        try {
            return (int) Float.parseFloat(raw.trim().split("[^0-9.+\\-eE]")[0]);
        } catch (Exception e) {
            return def;
        }
    }

    private static float parseFloat(String raw, float def) {
        if (raw == null || raw.trim().isEmpty()) {
            return def;
        }
        try {
            return Float.parseFloat(raw.trim().split("[^0-9.+\\-eE]")[0]);
        } catch (Exception e) {
            return def;
        }
    }
}
