package org.lee.mugen.fight;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.lee.mugen.core.JMugenConstant;
import org.lee.mugen.input.Key;
import org.lee.mugen.input.MugenCommands;
import org.lee.mugen.input.MugenSingleCmd;
import org.lee.mugen.input.MugenSingleCmd.CommandType;
import org.lee.mugen.io.MugenDataStreams;
import org.lee.mugen.sprite.parser.Parser;
import org.lee.mugen.sprite.parser.Parser.GroupText;

/** GWT: load character {@code *.cmd} (commands + state -1 entries) without desktop {@code File}. */
public final class GwtCmdLoader {

    public static final class FightCmdData {
        public final List<MugenCommands> commands = new ArrayList<MugenCommands>();
        public final Map<String, Integer> commandToState = new HashMap<String, Integer>();
    }

    private GwtCmdLoader() {}

    public static FightCmdData load(String characterId) throws Exception {
        StringBuilder merged = new StringBuilder();
        Reader charReader =
                MugenDataStreams.openUtf8Reader(
                        JMugenConstant.RESOURCE + "chars/" + characterId + "/" + characterId + ".cmd");
        try {
            appendReader(charReader, merged);
        } finally {
            charReader.close();
        }
        merged.append('\n');
        try {
            Reader commonReader =
                    MugenDataStreams.openUtf8Reader(JMugenConstant.RESOURCE + "data/common.cmd");
            try {
                appendReader(commonReader, merged);
            } finally {
                commonReader.close();
            }
        } catch (Exception ignored) {
        }
        List<GroupText> groups = Parser.getGroupTextMap(new StringReader(merged.toString()), true);
        return parseGroups(groups);
    }

    private static void appendReader(Reader reader, StringBuilder out) throws Exception {
        BufferedReader br = new BufferedReader(reader);
        String line;
        while ((line = br.readLine()) != null) {
            out.append(line).append('\n');
        }
    }

    static FightCmdData parseGroups(List<GroupText> groups) throws Exception {
        FightCmdData data = new FightCmdData();
        for (GroupText grp : groups) {
            if ("command".equalsIgnoreCase(grp.getSection())) {
                data.commands.add(interpretCmd(grp));
            } else if (isStateMinusOne(grp.getSectionRaw())) {
                parseStateEntry(grp, data.commandToState);
            }
        }
        return data;
    }

    private static boolean isStateMinusOne(String sectionRaw) {
        if (sectionRaw == null) {
            return false;
        }
        return sectionRaw.toLowerCase().startsWith("state -1");
    }

    private static void parseStateEntry(GroupText grp, Map<String, Integer> commandToState) {
        Integer value = null;
        String commandName = null;
        String text = grp.getText() != null ? grp.getText().toString() : "";
        String[] lines = text.split("\r?\n");
        for (String raw : lines) {
            String line = raw.trim().toLowerCase();
            if (line.isEmpty() || line.startsWith(";")) {
                continue;
            }
            if (line.startsWith("value")) {
                int eq = line.indexOf('=');
                if (eq > 0) {
                    try {
                        value = Integer.parseInt(line.substring(eq + 1).trim().split("[^0-9-]")[0]);
                    } catch (Exception ignored) {
                    }
                }
            } else if (line.contains("command")) {
                int q1 = line.indexOf('"');
                int q2 = line.lastIndexOf('"');
                if (q1 >= 0 && q2 > q1) {
                    commandName = line.substring(q1 + 1, q2);
                }
            }
        }
        Map<String, String> kv = grp.getKeyValues();
        if (kv != null) {
            if (kv.containsKey("value") && value == null) {
                try {
                    value = Integer.parseInt(kv.get("value").trim());
                } catch (Exception ignored) {
                }
            }
            for (Map.Entry<String, String> e : kv.entrySet()) {
                String k = e.getKey().toLowerCase();
                if (k.contains("command") && commandName == null) {
                    String v = e.getValue();
                    if (v != null && v.indexOf('"') >= 0) {
                        int q1 = v.indexOf('"');
                        int q2 = v.lastIndexOf('"');
                        if (q2 > q1) {
                            commandName = v.substring(q1 + 1, q2);
                        }
                    }
                }
            }
        }
        if (value != null && commandName != null && !commandToState.containsKey(commandName)) {
            commandToState.put(commandName, value);
        }
    }

    public static MugenCommands interpretCmd(GroupText grp) {
        if (!"command".equalsIgnoreCase(grp.getSection())) {
            throw new IllegalArgumentException("not a [Command] section: " + grp.getSection());
        }
        String cmds = grp.getKeyValues().get("command");
        String time = grp.getKeyValues().get("time");
        String bufferTime = grp.getKeyValues().get("buffer.time");
        String name = grp.getKeyValues().get("name");
        int iTime = time == null ? 15 : Integer.parseInt(time);
        int iBufferTime = bufferTime == null ? 1 : Integer.parseInt(bufferTime);
        return new MugenCommands(getMugenSingleCmds(cmds), name, iTime, iBufferTime);
    }

    private static MugenSingleCmd[] getMugenSingleCmds(String cmds) {
        List<MugenSingleCmd> mugenSingleCmds = new ArrayList<MugenSingleCmd>();
        String[] singleCmds = cmds.split(",");
        MugenSingleCmd previous = null;
        for (String scm : singleCmds) {
            boolean repeat = false;
            final MugenSingleCmd decodeScm = getMugenSingleCmd(scm.trim());
            if (previous != null) {
                if ((previous.getTypes() == CommandType.PRESS.bit)
                        && decodeScm.getTypes() == CommandType.PRESS.bit
                        && previous.getKeys() == decodeScm.getKeys()) {
                    for (Key k : Key.values()) {
                        if (k.bit == previous.getKeys() && k.bit == decodeScm.getKeys()) {
                            repeat = true;
                            mugenSingleCmds.add(
                                    new MugenSingleCmd(
                                            new Key[] {k},
                                            new CommandType[] {
                                                CommandType.NO_OTHER_KEY_BEFORE, CommandType.RELEASED
                                            }));
                            mugenSingleCmds.add(
                                    new MugenSingleCmd(
                                            new Key[] {k},
                                            new CommandType[] {
                                                CommandType.NO_OTHER_KEY_BEFORE, CommandType.PRESS
                                            }));
                            break;
                        }
                    }
                }
            }
            if (!repeat) {
                mugenSingleCmds.add(decodeScm);
            }
            previous = decodeScm;
        }
        return mugenSingleCmds.toArray(new MugenSingleCmd[mugenSingleCmds.size()]);
    }

    private static MugenSingleCmd getMugenSingleCmd(String scm) {
        CommandType[] cmdTypes = MugenSingleCmd.CommandType.values();
        boolean isPress = true;
        int timeIfReleased = 1;
        List<CommandType> modifiers = new ArrayList<CommandType>();
        for (CommandType cmdType : cmdTypes) {
            if (cmdType.desc.length() > 0
                    && scm.indexOf(CommandType.NO_OTHER_KEY_BEFORE.desc) != -1
                    && CommandType.NO_OTHER_KEY_BEFORE == cmdType) {
                modifiers.add(cmdType);
            } else if (cmdType.desc.length() > 0
                    && scm.indexOf(CommandType.DIRECTION.desc) != -1
                    && CommandType.DIRECTION == cmdType) {
                modifiers.add(cmdType);
            } else if (cmdType.desc.length() > 0 && scm.indexOf(cmdType.desc) != -1) {
                isPress = false;
                modifiers.add(cmdType);
                Matcher m = Pattern.compile("([0-9].)").matcher(scm);
                if (m.find()) {
                    timeIfReleased = Integer.parseInt(m.group(1));
                }
            }
        }
        Key[] keysDDir = new Key[] {Key.DB, Key.DF, Key.UF, Key.UB};
        Key selectedKey = null;
        for (Key dd : keysDDir) {
            if (scm.indexOf(dd.toString()) != -1) {
                selectedKey = dd;
            }
        }
        List<Key> keys = new ArrayList<Key>();
        if (selectedKey == null) {
            for (Key k : Key.values()) {
                if (scm.indexOf(k.toString()) != -1) {
                    keys.add(k);
                }
            }
        } else {
            keys.add(selectedKey);
        }
        if (isPress) {
            modifiers.add(CommandType.PRESS);
        }
        MugenSingleCmd cmd =
                new MugenSingleCmd(
                        keys.toArray(new Key[keys.size()]),
                        modifiers.toArray(new CommandType[modifiers.size()]));
        cmd.setTime(timeIfReleased);
        return cmd;
    }
}
