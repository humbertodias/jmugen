package org.lee.mugen.fight;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.lee.mugen.core.JMugenConstant;
import org.lee.mugen.io.MugenDataStreams;
import org.lee.mugen.sprite.parser.Parser;
import org.lee.mugen.sprite.parser.Parser.GroupText;

/**
 * GWT Phase 1 CNS loader: merges character constants + states + {@code common1.cns} without
 * desktop {@code File}/{@code CnsParse}, then extracts movement constants and HitDefs.
 */
public final class GwtCnsLoader {

    private static final Pattern STATEDEF =
            Pattern.compile("\\s*statedef\\s*(-?\\d+)\\s*");
    private static final Pattern ANIM_ELEM =
            Pattern.compile("animelem\\s*=\\s*(\\d+)");

    private GwtCnsLoader() {}

    public static GwtCnsData load(String characterId) throws Exception {
        DefFiles files = readDefFiles(characterId);
        StringBuilder merged = new StringBuilder();
        String base = JMugenConstant.RESOURCE + "chars/" + characterId + "/";

        appendFile(resolveCharFile(base, files.cns), merged);
        merged.append('\n');
        if (files.st != null && !files.st.equalsIgnoreCase(files.cns)) {
            appendFile(resolveCharFile(base, files.st), merged);
            merged.append('\n');
        }
        for (String extra : files.extraSt) {
            if (extra == null || extra.isEmpty()) {
                continue;
            }
            if (extra.equalsIgnoreCase(files.cns) || extra.equalsIgnoreCase(files.st)) {
                continue;
            }
            try {
                appendFile(resolveCharFile(base, extra), merged);
                merged.append('\n');
            } catch (Exception ignored) {
            }
        }
        appendCommonCns(files.stCommon, base, merged);

        List<GroupText> groups = Parser.getGroupTextMap(new StringReader(merged.toString()), true);
        return parseGroups(groups);
    }

    private static void appendCommonCns(String stCommon, String charBase, StringBuilder out)
            throws Exception {
        String name = stCommon != null && !stCommon.isEmpty() ? stCommon : "common1.cns";
        name = name.replace('\\', '/');
        String[] candidates =
                new String[] {
                    charBase + name,
                    JMugenConstant.RESOURCE + "data/" + name,
                    JMugenConstant.RESOURCE + name
                };
        Exception last = null;
        for (String c : candidates) {
            try {
                appendFile(Parser.getExistFile(c), out);
                out.append('\n');
                return;
            } catch (Exception e) {
                last = e;
            }
        }
        if (last != null) {
            throw last;
        }
    }

    private static String resolveCharFile(String base, String relative) throws Exception {
        return Parser.getExistFile(base + relative.replace('\\', '/'));
    }

    private static void appendFile(String path, StringBuilder out) throws Exception {
        Reader reader = MugenDataStreams.openUtf8Reader(path);
        try {
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                out.append(line).append('\n');
            }
        } finally {
            reader.close();
        }
    }

    static GwtCnsData parseGroups(List<GroupText> groups) {
        GwtCnsData data = new GwtCnsData();
        int currentState = Integer.MIN_VALUE;
        for (GroupText grp : groups) {
            String section = grp.getSection() != null ? grp.getSection().trim() : "";
            String raw = grp.getSectionRaw() != null ? grp.getSectionRaw() : section;
            String sectionLower = section.toLowerCase(Locale.ROOT);

            if ("data".equals(sectionLower)) {
                fillData(data, grp.getKeyValues());
            } else if ("size".equals(sectionLower)) {
                fillSize(data, grp.getKeyValues());
            } else if ("velocity".equals(sectionLower)) {
                fillVelocity(data, grp.getKeyValues());
            } else if ("movement".equals(sectionLower)) {
                fillMovement(data, grp.getKeyValues());
            } else {
                Matcher sd = STATEDEF.matcher(sectionLower);
                if (sd.matches() || STATEDEF.matcher(raw.toLowerCase(Locale.ROOT)).find()) {
                    Matcher m = STATEDEF.matcher(sectionLower);
                    if (!m.find()) {
                        m = STATEDEF.matcher(raw.toLowerCase(Locale.ROOT));
                        m.find();
                    }
                    currentState = Integer.parseInt(m.group(1));
                    GwtCnsData.StateMeta meta = data.states.get(currentState);
                    if (meta == null) {
                        meta = new GwtCnsData.StateMeta();
                        meta.id = currentState;
                        data.states.put(currentState, meta);
                    }
                    fillStateMeta(meta, grp.getKeyValues());
                } else if (sectionLower.startsWith("state") && currentState != Integer.MIN_VALUE) {
                    parseStateCtrl(data, currentState, grp);
                }
            }
        }
        return data;
    }

    private static void parseStateCtrl(GwtCnsData data, int stateId, GroupText grp) {
        Map<String, String> kv = grp.getKeyValues();
        if (kv == null || kv.isEmpty()) {
            return;
        }
        String type = getIgnoreCase(kv, "type");
        if (type == null || type.trim().isEmpty()) {
            return;
        }
        type = type.trim().toLowerCase(Locale.ROOT);

        GwtCnsData.StateMeta meta = data.states.get(stateId);
        if (meta == null) {
            meta = new GwtCnsData.StateMeta();
            meta.id = stateId;
            data.states.put(stateId, meta);
        }

        GwtCnsData.StateCtrl ctrl = new GwtCnsData.StateCtrl();
        ctrl.type = type;
        ctrl.ignoreHitPause = parseBool(getIgnoreCase(kv, "ignorehitpause"));
        ctrl.persistent = parseInt(getIgnoreCase(kv, "persistent"), 1);
        for (Map.Entry<String, String> e : kv.entrySet()) {
            String k = e.getKey() != null ? e.getKey().toLowerCase(Locale.ROOT).replaceAll("\\s+", "") : "";
            String v = e.getValue() != null ? e.getValue() : "";
            if (k.equals("triggerall")) {
                ctrl.triggerAll.add(v.trim());
            } else if (k.startsWith("trigger") && !k.equals("triggerall")) {
                List<String> group = new ArrayList<String>();
                group.add(v.trim());
                ctrl.triggerGroups.add(group);
            } else if (!k.equals("type")
                    && !k.equals("ignorehitpause")
                    && !k.equals("persistent")) {
                ctrl.params.put(k, v.trim());
            }
        }
        // No triggers at all → treat as always true (some CNS use only triggerall).
        if (ctrl.triggerGroups.isEmpty() && ctrl.triggerAll.isEmpty()) {
            List<String> always = new ArrayList<String>();
            always.add("1");
            ctrl.triggerGroups.add(always);
        }
        meta.controllers.add(ctrl);

        if ("hitdef".equals(type)) {
            maybeParseHitDef(data, stateId, grp);
        }
    }

    private static void maybeParseHitDef(GwtCnsData data, int stateId, GroupText grp) {
        Map<String, String> kv = grp.getKeyValues();
        if (kv == null) {
            return;
        }
        String type = getIgnoreCase(kv, "type");
        if (type == null || !"hitdef".equalsIgnoreCase(type.trim())) {
            return;
        }
        GwtCnsData.StateMeta meta = data.states.get(stateId);
        if (meta == null) {
            meta = new GwtCnsData.StateMeta();
            meta.id = stateId;
            data.states.put(stateId, meta);
        }
        GwtCnsData.HitDef hit = new GwtCnsData.HitDef();
        parseDamage(hit, getIgnoreCase(kv, "damage"));
        parsePause(hit, getIgnoreCase(kv, "pausetime"));
        parseGroundVel(hit, getIgnoreCase(kv, "ground.velocity"));
        parseAirVel(hit, getIgnoreCase(kv, "air.velocity"));
        hit.groundSlideTime = parseInt(getIgnoreCase(kv, "ground.slidetime"), hit.groundSlideTime);
        hit.groundHitTime = parseInt(getIgnoreCase(kv, "ground.hittime"), hit.groundHitTime);
        String gtype = getIgnoreCase(kv, "ground.type");
        if (gtype != null && gtype.trim().equalsIgnoreCase("trip")) {
            hit.trip = true;
        }
        hit.fall = parseBool(getIgnoreCase(kv, "fall"));
        String gf = getIgnoreCase(kv, "guardflag");
        if (gf != null) {
            hit.guardFlag = gf.trim();
        }
        String hf = getIgnoreCase(kv, "hitflag");
        if (hf != null) {
            hit.hitFlag = hf.trim();
        }
        hit.animElem = parseAnimElemTrigger(kv);
        parseSndRef(hit, getIgnoreCase(kv, "hitsound"), true);
        parseSndRef(hit, getIgnoreCase(kv, "guardsound"), false);
        hit.animType = parseAnimType(getIgnoreCase(kv, "animtype"));
        meta.hitDefs.add(hit);
    }

    /** Light=0, Medium=1, Hard/Heavy=2 (common1 GetHitVar(animtype)). */
    private static int parseAnimType(String raw) {
        if (raw == null || raw.trim().isEmpty()) {
            return 0;
        }
        String v = raw.trim().toLowerCase(Locale.ROOT);
        if (v.startsWith("med") || "m".equals(v)) {
            return 1;
        }
        if (v.startsWith("har") || v.startsWith("hea") || "h".equals(v) || "2".equals(v)) {
            return 2;
        }
        if (v.startsWith("bac") || v.startsWith("up") || v.startsWith("diag")) {
            return 2;
        }
        return 0;
    }

    /** Parse {@code hitsound = 5,0} or {@code s0,1} (sprite snd deferred). */
    private static void parseSndRef(GwtCnsData.HitDef hit, String raw, boolean hitNotGuard) {
        if (raw == null || raw.trim().isEmpty()) {
            return;
        }
        String v = raw.trim().replace(" ", "");
        boolean fromSprite = false;
        if (v.length() > 0 && (v.charAt(0) == 's' || v.charAt(0) == 'S')) {
            fromSprite = true;
            v = v.substring(1);
        }
        String[] p = v.split(",");
        if (p.length < 2) {
            return;
        }
        try {
            int grp = Integer.parseInt(p[0]);
            int num = Integer.parseInt(p[1]);
            if (hitNotGuard) {
                hit.hitsoundGrp = grp;
                hit.hitsoundNum = num;
                hit.hitsoundFromSprite = fromSprite;
            } else {
                hit.guardsoundGrp = grp;
                hit.guardsoundNum = num;
                hit.guardsoundFromSprite = fromSprite;
            }
        } catch (Exception ignored) {
        }
    }

    private static int parseAnimElemTrigger(Map<String, String> kv) {
        for (Map.Entry<String, String> e : kv.entrySet()) {
            String k = e.getKey().toLowerCase(Locale.ROOT).replaceAll("\\s+", "");
            if (!k.startsWith("trigger")) {
                continue;
            }
            Matcher m = ANIM_ELEM.matcher(e.getValue().toLowerCase(Locale.ROOT));
            if (m.find()) {
                return Integer.parseInt(m.group(1));
            }
        }
        return 0;
    }

    private static void fillStateMeta(GwtCnsData.StateMeta meta, Map<String, String> kv) {
        if (kv == null) {
            return;
        }
        String t = getIgnoreCase(kv, "type");
        if (t != null) {
            meta.type = t.trim().toUpperCase(Locale.ROOT);
        }
        String mt = getIgnoreCase(kv, "movetype");
        if (mt != null) {
            meta.moveType = mt.trim().toUpperCase(Locale.ROOT);
        }
        String p = getIgnoreCase(kv, "physics");
        if (p != null) {
            meta.physics = p.trim().toUpperCase(Locale.ROOT);
        }
        meta.anim = parseInt(getIgnoreCase(kv, "anim"), meta.anim);
        meta.ctrl = parseInt(getIgnoreCase(kv, "ctrl"), meta.ctrl);
        String vel = getIgnoreCase(kv, "velset");
        if (vel != null) {
            float[] xy = parseFloats(vel);
            if (xy.length >= 1) {
                meta.velsetX = xy[0];
                meta.hasVelset = true;
            }
            if (xy.length >= 2) {
                meta.velsetY = xy[1];
            }
        }
    }

    private static void fillData(GwtCnsData data, Map<String, String> kv) {
        data.life = parseInt(getIgnoreCase(kv, "life"), data.life);
        data.attack = parseInt(getIgnoreCase(kv, "attack"), data.attack);
        data.defence = parseInt(getIgnoreCase(kv, "defence"), data.defence);
        data.airJuggle = parseInt(getIgnoreCase(kv, "airjuggle"), data.airJuggle);
        data.lieDownTime = parseInt(getIgnoreCase(kv, "liedown.time"), data.lieDownTime);
    }

    private static void fillSize(GwtCnsData data, Map<String, String> kv) {
        data.xScale = parseFloat(getIgnoreCase(kv, "xscale"), data.xScale);
        data.yScale = parseFloat(getIgnoreCase(kv, "yscale"), data.yScale);
        data.groundBack = parseInt(getIgnoreCase(kv, "ground.back"), data.groundBack);
        data.groundFront = parseInt(getIgnoreCase(kv, "ground.front"), data.groundFront);
        data.height = parseInt(getIgnoreCase(kv, "height"), data.height);
    }

    private static void fillVelocity(GwtCnsData data, Map<String, String> kv) {
        data.walkFwd = parseFloat(getIgnoreCase(kv, "walk.fwd"), data.walkFwd);
        data.walkBack = parseFloat(getIgnoreCase(kv, "walk.back"), data.walkBack);
        float[] runFwd = parseFloats(getIgnoreCase(kv, "run.fwd"));
        if (runFwd.length >= 1) {
            data.runFwdX = runFwd[0];
        }
        float[] runBack = parseFloats(getIgnoreCase(kv, "run.back"));
        if (runBack.length >= 1) {
            data.runBackX = runBack[0];
        }
        if (runBack.length >= 2) {
            data.runBackY = runBack[1];
        }
        float[] jumpNeu = parseFloats(getIgnoreCase(kv, "jump.neu"));
        if (jumpNeu.length >= 1) {
            data.jumpNeuX = jumpNeu[0];
        }
        if (jumpNeu.length >= 2) {
            data.jumpNeuY = jumpNeu[1];
        }
        data.jumpBackX = parseFloat(getIgnoreCase(kv, "jump.back"), data.jumpBackX);
        data.jumpFwdX = parseFloat(getIgnoreCase(kv, "jump.fwd"), data.jumpFwdX);
    }

    private static void fillMovement(GwtCnsData data, Map<String, String> kv) {
        data.yAccel = parseFloat(getIgnoreCase(kv, "yaccel"), data.yAccel);
        data.standFriction = parseFloat(getIgnoreCase(kv, "stand.friction"), data.standFriction);
        data.crouchFriction = parseFloat(getIgnoreCase(kv, "crouch.friction"), data.crouchFriction);
    }

    private static void parseDamage(GwtCnsData.HitDef hit, String raw) {
        float[] v = parseFloats(raw);
        if (v.length >= 1) {
            hit.damage = (int) v[0];
        }
        if (v.length >= 2) {
            hit.guardDamage = (int) v[1];
        }
    }

    private static void parsePause(GwtCnsData.HitDef hit, String raw) {
        float[] v = parseFloats(raw);
        if (v.length >= 1) {
            hit.pauseP1 = (int) v[0];
        }
        if (v.length >= 2) {
            hit.pauseP2 = (int) v[1];
        } else if (v.length >= 1) {
            hit.pauseP2 = hit.pauseP1;
        }
    }

    private static void parseGroundVel(GwtCnsData.HitDef hit, String raw) {
        float[] v = parseFloats(raw);
        if (v.length >= 1) {
            // CNS stores attacker-relative (usually negative = away). Stub uses magnitude * facing.
            hit.groundVelocityX = Math.abs(v[0]);
        }
    }

    private static void parseAirVel(GwtCnsData.HitDef hit, String raw) {
        float[] v = parseFloats(raw);
        if (v.length >= 1) {
            hit.airVelocityX = Math.abs(v[0]);
        }
        if (v.length >= 2) {
            hit.airVelocityY = v[1];
        }
    }

    private static String getIgnoreCase(Map<String, String> kv, String key) {
        if (kv == null) {
            return null;
        }
        if (kv.containsKey(key)) {
            return kv.get(key);
        }
        String want = key.toLowerCase(Locale.ROOT);
        for (Map.Entry<String, String> e : kv.entrySet()) {
            if (e.getKey() != null && e.getKey().toLowerCase(Locale.ROOT).equals(want)) {
                return e.getValue();
            }
        }
        return null;
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
        float[] v = parseFloats(raw);
        return v.length > 0 ? v[0] : def;
    }

    private static float[] parseFloats(String raw) {
        if (raw == null || raw.trim().isEmpty()) {
            return new float[0];
        }
        String cleaned = raw.trim().replace(" ", "");
        String[] parts = cleaned.split(",");
        float[] out = new float[parts.length];
        int n = 0;
        for (String p : parts) {
            try {
                out[n++] = Float.parseFloat(p);
            } catch (Exception ignored) {
            }
        }
        if (n == out.length) {
            return out;
        }
        float[] trimmed = new float[n];
        System.arraycopy(out, 0, trimmed, 0, n);
        return trimmed;
    }

    private static boolean parseBool(String raw) {
        if (raw == null) {
            return false;
        }
        String t = raw.trim();
        return "1".equals(t) || "true".equalsIgnoreCase(t);
    }

    private static final class DefFiles {
        String cns = "kfm.cns";
        String st;
        String stCommon = "common1.cns";
        final java.util.ArrayList<String> extraSt = new java.util.ArrayList<String>();
    }

    private static DefFiles readDefFiles(String characterId) throws Exception {
        DefFiles files = new DefFiles();
        files.cns = characterId + ".cns";
        files.st = characterId + ".cns";
        String defPath =
                Parser.getExistFile(
                        JMugenConstant.RESOURCE
                                + "chars/"
                                + characterId
                                + "/"
                                + characterId
                                + ".def");
        Reader reader = MugenDataStreams.openUtf8Reader(defPath);
        try {
            BufferedReader br = new BufferedReader(reader);
            String section = "";
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith(";")) {
                    continue;
                }
                if (line.startsWith("[") && line.endsWith("]")) {
                    section = line.substring(1, line.length() - 1).trim().toLowerCase(Locale.ROOT);
                    continue;
                }
                if (!"files".equals(section)) {
                    continue;
                }
                int eq = line.indexOf('=');
                if (eq < 0) {
                    continue;
                }
                String key = line.substring(0, eq).trim().toLowerCase(Locale.ROOT).replaceAll("\\s+", "");
                String value = line.substring(eq + 1).trim();
                int semi = value.indexOf(';');
                if (semi >= 0) {
                    value = value.substring(0, semi).trim();
                }
                value = value.replace('\\', '/');
                if ("cns".equals(key)) {
                    files.cns = value;
                } else if ("st".equals(key) || key.matches("st\\d+")) {
                    if ("st".equals(key)) {
                        files.st = value;
                    } else {
                        files.extraSt.add(value);
                    }
                } else if ("stcommon".equals(key)) {
                    files.stCommon = value;
                }
            }
        } finally {
            reader.close();
        }
        if (files.st == null) {
            files.st = files.cns;
        }
        return files;
    }
}
