package org.lee.mugen.fight;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.lee.mugen.core.JMugenConstant;
import org.lee.mugen.core.sound.SoundSystem;
import org.lee.mugen.io.MugenDataStreams;
import org.lee.mugen.snd.GroupSnd;
import org.lee.mugen.snd.Snd;
import org.lee.mugen.snd.SndReader;
import org.lee.mugen.sprite.parser.Parser;

/**
 * GWT fight audio: loads {@code fight.snd} + {@code common.snd} and plays round/FIGHT/KO/hit SFX
 * through {@link SoundSystem} (LibGDX web {@code LGDXWebAudioPlayback}).
 */
public final class GwtFightAudio {

    private static final String FIGHT_DEF = "data/fight.def";

    private Snd fightSnd;
    private Snd commonSnd;
    private final Map<Integer, int[]> roundSnds = new HashMap<Integer, int[]>();
    private int[] fightCallSnd = new int[] {1, 0};
    private int[] koSnd = new int[] {2, 0};
    private int[] dkoSnd = new int[] {2, 1};
    private int[] toSnd = new int[] {2, 2};

    public static GwtFightAudio load() {
        GwtFightAudio audio = new GwtFightAudio();
        try {
            audio.parseAndLoad();
        } catch (Exception e) {
            System.err.println("GwtFightAudio: failed to load fight sounds: " + e);
        }
        return audio;
    }

    private void parseAndLoad() throws Exception {
        String defPath = Parser.getExistFile(JMugenConstant.RESOURCE + FIGHT_DEF);
        String fightSndPath = "data/fight.snd";
        String commonSndPath = "data/common.snd";
        roundSnds.put(1, new int[] {0, 1});
        roundSnds.put(2, new int[] {0, 2});
        roundSnds.put(3, new int[] {0, 3});

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
                if ("files".equals(section)) {
                    if ("snd".equals(key)) {
                        fightSndPath = value.startsWith("data/") ? value : "data/" + value;
                    } else if ("common.snd".equals(key)) {
                        commonSndPath = value.startsWith("data/") ? value : "data/" + value;
                    }
                } else if ("round".equals(section)) {
                    if (key.length() > 9 && key.startsWith("round") && key.endsWith(".snd")) {
                        // round1.snd — avoid Pattern (GWT edge cases)
                        String numPart = key.substring(5, key.length() - 4);
                        try {
                            int n = Integer.parseInt(numPart);
                            int[] sn = parseSndPair(value);
                            if (sn != null) {
                                roundSnds.put(n, sn);
                            }
                        } catch (Exception ignored) {
                        }
                    } else if ("fight.snd".equals(key)) {
                        int[] sn = parseSndPair(value);
                        if (sn != null) {
                            fightCallSnd = sn;
                        }
                    } else if ("ko.snd".equals(key)) {
                        int[] sn = parseSndPair(value);
                        if (sn != null) {
                            koSnd = sn;
                        }
                    } else if ("dko.snd".equals(key)) {
                        int[] sn = parseSndPair(value);
                        if (sn != null) {
                            dkoSnd = sn;
                        }
                    } else if ("to.snd".equals(key)) {
                        int[] sn = parseSndPair(value);
                        if (sn != null) {
                            toSnd = sn;
                        }
                    }
                }
            }
        } finally {
            reader.close();
        }

        try {
            fightSnd = loadSnd(fightSndPath);
        } catch (Exception e) {
            System.err.println("GwtFightAudio: cannot load " + fightSndPath + ": " + e);
        }
        try {
            commonSnd = loadSnd(commonSndPath);
        } catch (Exception e) {
            System.err.println("GwtFightAudio: cannot load " + commonSndPath + ": " + e);
        }
    }

    public boolean hasFightSnd() {
        return fightSnd != null;
    }

    private static Snd loadSnd(String relative) throws Exception {
        String path = Parser.getExistFile(JMugenConstant.RESOURCE + relative);
        return SndReader.parse(path);
    }

    private static int[] parseSndPair(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        String cleaned = value.replace(" ", "");
        String[] p = cleaned.split(",");
        if (p.length < 2) {
            return null;
        }
        try {
            return new int[] {Integer.parseInt(p[0]), Integer.parseInt(p[1])};
        } catch (Exception e) {
            return null;
        }
    }

    public void playRound(int roundNo) {
        int[] sn = roundSnds.get(roundNo);
        if (sn == null) {
            sn = roundSnds.get(1);
        }
        if (sn != null) {
            playFight(sn[0], sn[1]);
        }
    }

    public void playFightCall() {
        playFight(fightCallSnd[0], fightCallSnd[1]);
    }

    public void playKo() {
        playFight(koSnd[0], koSnd[1]);
    }

    public void playDko() {
        playFight(dkoSnd[0], dkoSnd[1]);
    }

    public void playTimeOver() {
        playFight(toSnd[0], toSnd[1]);
    }

    /** Hit / guard sample from {@code common.snd} (HitDef without {@code s} prefix). */
    public void playCommon(int grp, int num) {
        play(commonSnd, grp, num);
    }

    public void playFight(int grp, int num) {
        play(fightSnd, grp, num);
    }

    private static void play(Snd snd, int grp, int num) {
        if (snd == null || grp < 0) {
            return;
        }
        try {
            GroupSnd group = snd.getGroup(grp);
            if (group == null) {
                System.err.println("GwtFightAudio: missing group " + grp);
                return;
            }
            byte[] data = group.getSound(num);
            if (data != null && data.length > 0) {
                SoundSystem.Sfx.playSnd(data);
            } else {
                System.err.println("GwtFightAudio: missing sample " + grp + "," + num);
            }
        } catch (Exception e) {
            System.err.println("GwtFightAudio: play failed " + grp + "," + num + ": " + e);
        }
    }
}
