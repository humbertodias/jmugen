package org.lee.mugen.fight.select;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.lee.mugen.core.JMugenConstant;
import org.lee.mugen.imageIO.PCXPalette;
import org.lee.mugen.imageIO.RawPCXImage;
import org.lee.mugen.io.IOUtils;
import org.lee.mugen.io.MugenDataStreams;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.sff.SffReader;
import org.lee.mugen.sprite.baseForParse.ImageSpriteSFF;
import org.lee.mugen.sprite.baseForParse.SpriteSFF;
import org.lee.mugen.sprite.parser.Parser;

/**
 * GWT: character portraits and display names for the select screen (no {@code FileInputStream}).
 */
public class GwtCharacters {

    private static final int PORTRAIT_GRP = 9000;
    private static final int PORTRAIT_IMG = 0;
    private static final int BIG_PORTRAIT_IMG = 1;

    private final Map<String, String> spriteFiles = new HashMap<String, String>();
    private final Map<String, String> animFiles = new HashMap<String, String>();
    private final Map<String, String> displayNames = new HashMap<String, String>();
    private final Map<String, ImageContainer> portraits = new HashMap<String, ImageContainer>();
    private final Map<String, ImageSpriteSFF> portraitSprites = new HashMap<String, ImageSpriteSFF>();
    private final Map<String, ImageContainer> bigPortraits = new HashMap<String, ImageContainer>();
    private final Map<String, Integer> lifeValues = new HashMap<String, Integer>();

    public GwtCharacters(List<String> characterIds) throws IOException {
        for (String id : characterIds) {
            loadCharacterMeta(id);
        }
    }

    public String getDisplayName(String characterId) {
        String name = displayNames.get(characterId);
        return name != null ? name : characterId;
    }

    public ImageContainer getPortrait(String characterId) {
        ImageSpriteSFF spr = getPortraitSprite(characterId);
        return spr != null ? spr.getImage() : null;
    }

    /**
     * Small portrait (9000,0) with SFF axes — needed for fight HUD face placement.
     */
    public ImageSpriteSFF getPortraitSprite(String characterId) {
        ImageSpriteSFF cached = portraitSprites.get(characterId);
        if (cached != null) {
            return cached;
        }
        ImageSpriteSFF fromSff = loadPortraitFromSff(characterId, PORTRAIT_IMG);
        if (fromSff != null) {
            portraitSprites.put(characterId, fromSff);
            portraits.put(characterId, fromSff.getImage());
            return fromSff;
        }
        ImageContainer ic = loadPortraitImage(characterId, PORTRAIT_IMG);
        if (ic == null) {
            return null;
        }
        ImageSpriteSFF fallback = new ImageSpriteSFF(PORTRAIT_GRP, PORTRAIT_IMG, ic, 0, 0);
        portraitSprites.put(characterId, fallback);
        portraits.put(characterId, ic);
        return fallback;
    }

    /** Stand pose (group 0, image 0) for in-fight display. */
    public ImageContainer getStandSprite(String characterId) {
        return loadFightSpriteImage(characterId, 0, 0);
    }

    /** Life from character.def {@code [Info] life}, or 1000. */
    public int getLife(String characterId) {
        Integer life = lifeValues.get(characterId);
        return life != null ? life : 1000;
    }

    /** {@code [sff, air]} paths from character.def {@code [Files]}, or null. */
    public String[] getFightFiles(String characterId) {
        String sff = spriteFiles.get(characterId);
        String air = animFiles.get(characterId);
        if (sff == null || air == null) {
            return null;
        }
        return new String[] {sff, air};
    }

    public ImageContainer getBigPortrait(String characterId) {
        ImageContainer cached = bigPortraits.get(characterId);
        if (cached != null) {
            return cached;
        }
        ImageContainer ic = loadPortraitImage(characterId, BIG_PORTRAIT_IMG);
        if (ic != null) {
            bigPortraits.put(characterId, ic);
        }
        return ic;
    }

    private void loadCharacterMeta(String characterId) throws IOException {
        String defPath = JMugenConstant.RESOURCE + "chars/" + characterId + "/" + characterId + ".def";
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
                    section = line.substring(1, line.length() - 1).trim().toLowerCase();
                    continue;
                }
                int eq = line.indexOf('=');
                if (eq < 0) {
                    continue;
                }
                String key = line.substring(0, eq).trim().toLowerCase().replaceAll("\\s+", "");
                String value = stripComment(line.substring(eq + 1).trim());
                if ("info".equals(section) && "displayname".equals(key)) {
                    displayNames.put(characterId, unquote(value));
                } else if ("info".equals(section) && "life".equals(key)) {
                    try {
                        lifeValues.put(characterId, Integer.parseInt(value.trim()));
                    } catch (NumberFormatException ignored) {
                    }
                } else if ("files".equals(section) && "sprite".equals(key)) {
                    spriteFiles.put(characterId, value.replace('\\', '/'));
                } else if ("files".equals(section) && "anim".equals(key)) {
                    animFiles.put(characterId, value.replace('\\', '/'));
                }
            }
        } finally {
            reader.close();
        }
        if (!displayNames.containsKey(characterId)) {
            displayNames.put(characterId, characterId);
        }
    }

    private ImageContainer loadFightSpriteImage(String characterId, int group, int image) {
        String sff = spriteFiles.get(characterId);
        if (sff == null || sff.isEmpty()) {
            return null;
        }
        try {
            String path =
                    Parser.getExistFile(JMugenConstant.RESOURCE + "chars/" + characterId + "/" + sff);
            InputStream in = MugenDataStreams.openBinary(path);
            try {
                byte[] data = SffReader.getImage(in, group, image, null);
                return GraphicsWrapper.getInstance().getImageContainer(new RawPCXImage(data, new PCXPalette()));
            } finally {
                in.close();
            }
        } catch (Exception e) {
            return null;
        }
    }

    private ImageContainer loadPortraitImage(String characterId, int imageIndex) {
        RawPCXImage pcx = loadCachedPcx(characterId, imageIndex);
        if (pcx == null) {
            pcx = loadFromSff(characterId, imageIndex);
        }
        if (pcx == null) {
            return null;
        }
        return GraphicsWrapper.getInstance().getImageContainer(pcx);
    }

    private RawPCXImage loadCachedPcx(String characterId, int imageIndex) {
        String suffix = imageIndex == BIG_PORTRAIT_IMG ? "braw" : "sraw";
        String palSuffix = imageIndex == BIG_PORTRAIT_IMG ? "bpal" : "spal";
        String rawPath = JMugenConstant.RESOURCE + "cache/char/" + characterId + "/" + characterId + "." + suffix;
        String palPath = JMugenConstant.RESOURCE + "cache/char/" + characterId + "/" + characterId + "." + palSuffix;
        try {
            InputStream rawIn = MugenDataStreams.openBinary(rawPath);
            byte[] raw;
            try {
                raw = IOUtils.toByteArray(rawIn);
            } finally {
                rawIn.close();
            }
            PCXPalette pal = new PCXPalette();
            try {
                InputStream palIn = MugenDataStreams.openBinary(palPath);
                try {
                    pal.load(palIn);
                } finally {
                    palIn.close();
                }
            } catch (IOException ignored) {
                // palette optional
            }
            return new RawPCXImage(raw, pal);
        } catch (Exception e) {
            return null;
        }
    }

    private RawPCXImage loadFromSff(String characterId, int imageIndex) {
        String sff = spriteFiles.get(characterId);
        if (sff == null || sff.isEmpty()) {
            return null;
        }
        try {
            String path =
                    Parser.getExistFile(JMugenConstant.RESOURCE + "chars/" + characterId + "/" + sff);
            InputStream in = MugenDataStreams.openBinary(path);
            try {
                byte[] data = SffReader.getImage(in, PORTRAIT_GRP, imageIndex, null);
                return new RawPCXImage(data, new PCXPalette());
            } finally {
                in.close();
            }
        } catch (Exception e) {
            return null;
        }
    }

    /** Full SFF load so portrait axes match desktop FaceRender. */
    private ImageSpriteSFF loadPortraitFromSff(String characterId, int imageIndex) {
        String sff = spriteFiles.get(characterId);
        if (sff == null || sff.isEmpty()) {
            return null;
        }
        try {
            String path =
                    Parser.getExistFile(JMugenConstant.RESOURCE + "chars/" + characterId + "/" + sff);
            SpriteSFF spriteSff = new SpriteSFF(new SffReader(path, null), true, false);
            if (spriteSff.getGroupSpr(PORTRAIT_GRP) == null) {
                return null;
            }
            return spriteSff.getGroupSpr(PORTRAIT_GRP).getImgSpr(imageIndex);
        } catch (Exception e) {
            return null;
        }
    }

    private static String stripComment(String value) {
        int semi = value.indexOf(';');
        if (semi >= 0) {
            value = value.substring(0, semi).trim();
        }
        return value;
    }

    private static String unquote(String value) {
        if (value.length() >= 2 && value.startsWith("\"") && value.endsWith("\"")) {
            return value.substring(1, value.length() - 1);
        }
        return value;
    }
}
