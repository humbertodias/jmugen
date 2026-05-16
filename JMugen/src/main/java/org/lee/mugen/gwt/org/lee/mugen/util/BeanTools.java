package org.lee.mugen.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.lee.mugen.background.BG;
import org.lee.mugen.background.BGdef;
import org.lee.mugen.geom.MugenPoint;
import org.lee.mugen.sprite.entity.PointF;
import org.lee.mugen.sprite.entity.SprGrpNum;
import org.lee.mugen.geom.MugenRect;
import org.lee.mugen.object.Rectangle;
import org.lee.mugen.renderer.RGB;
import org.lee.mugen.renderer.Trans;
import org.lee.mugen.sprite.entity.PointF;
import org.lee.mugen.sprite.entity.SndGrpNum;
import org.lee.mugen.sprite.entity.SprGrpNum;

/**
 * GWT: field-based property access (no {@code Class#getMethods()} / {@code getConstructor()}).
 */
public class BeanTools {

    public interface Converter<T> {
        T convert(Object o);
    }

    private static final Map<Class, Converter> convertersMap = new HashMap<Class, Converter>();

    private static final Converter<MugenPoint> pointConverter = new Converter<MugenPoint>() {
        public MugenPoint convert(Object o) {
            if (o instanceof String) {
                String[] value = ((String) o).replaceAll(" ", "").split(",");
                return new MugenPoint(Integer.parseInt(value[0]), Integer.parseInt(value[1]));
            }
            Object[] objects = (Object[]) o;
            return new MugenPoint(((Number) objects[0]).intValue(), ((Number) objects[1]).intValue());
        }
    };

    private static final Converter<int[]> intArrayConverter = new Converter<int[]>() {
        public int[] convert(Object o) {
            if (o instanceof int[]) {
                return (int[]) o;
            }
            if (o instanceof String) {
                String[] arr = o.toString().trim().replace(" ", "").split(",");
                int[] iarr = new int[arr.length];
                for (int i = 0; i < arr.length; i++) {
                    iarr[i] = Integer.parseInt(arr[i]);
                }
                return iarr;
            }
            Object[] array = (Object[]) o;
            int[] farray = new int[array.length];
            for (int i = 0; i < farray.length; i++) {
                farray[i] = ((Number) array[i]).intValue();
            }
            return farray;
        }
    };

    private static final Converter<Integer> integerConverter = new Converter<Integer>() {
        public Integer convert(Object o) {
            if (o == null) {
                return 0;
            }
            if (o instanceof Number) {
                return ((Number) o).intValue();
            }
            if (o.getClass().isArray()) {
                Object[] obs = (Object[]) o;
                return obs.length == 0 ? 0 : Integer.valueOf(obs[0].toString());
            }
            return Integer.valueOf(o.toString());
        }
    };

    private static final Converter<Float> floatConverter = new Converter<Float>() {
        public Float convert(Object o) {
            if (o == null) {
                return 0f;
            }
            if (o instanceof Number) {
                return ((Number) o).floatValue();
            }
            if (o.getClass().isArray()) {
                Object[] obs = (Object[]) o;
                return obs.length == 0 ? 0f : Float.valueOf(obs[0].toString());
            }
            return Float.valueOf(o.toString());
        }
    };

    private static final Converter<float[]> floatPrimiArrayConverter = new Converter<float[]>() {
        public float[] convert(Object o) {
            if (o instanceof String) {
                String[] value = ((String) o).replaceAll(" ", "").split(",");
                return new float[] {Float.parseFloat(value[0]), Float.parseFloat(value[1])};
            }
            if (o == null) {
                return new float[] {0f};
            }
            if (o.getClass().isArray()) {
                Object[] array = (Object[]) o;
                float[] farray = new float[array.length];
                for (int i = 0; i < farray.length; i++) {
                    farray[i] = floatConverter.convert(array[i]);
                }
                return farray;
            }
            return new float[] {Float.parseFloat(o.toString())};
        }
    };

    private static final Converter<PointF> pointfConverter = new Converter<PointF>() {
        public PointF convert(Object o) {
            float[] values = floatPrimiArrayConverter.convert(o);
            PointF p = new PointF();
            if (values.length >= 1) {
                p.setX(values[0]);
            }
            if (values.length >= 2) {
                p.setY(values[1]);
            }
            return p;
        }
    };

    private static final Converter<MugenRect> mugenRectConverter = new Converter<MugenRect>() {
        public MugenRect convert(Object o) {
            if (o instanceof String) {
                String[] params = o.toString().replaceAll(" ", "").split(",");
                MugenRect result = new MugenRect();
                result.x = Integer.parseInt(params[0]);
                result.y = Integer.parseInt(params[1]);
                result.width = Integer.parseInt(params[2]);
                result.height = Integer.parseInt(params[3]);
                return result;
            }
            Object[] params = (Object[]) o;
            MugenRect result = new MugenRect();
            result.x = ((Number) params[0]).intValue();
            result.y = ((Number) params[1]).intValue();
            result.width = ((Number) params[2]).intValue();
            result.height = ((Number) params[3]).intValue();
            return result;
        }
    };

    private static final Converter<Rectangle> mugenRectangleConverter = new Converter<Rectangle>() {
        public Rectangle convert(Object o) {
            if (o instanceof String) {
                String[] params = o.toString().replaceAll(" ", "").split(",");
                Rectangle result = new Rectangle();
                result.setX1(Integer.parseInt(params[0]));
                result.setY1(Integer.parseInt(params[1]));
                result.setX2(Integer.parseInt(params[2]));
                result.setY2(Integer.parseInt(params[3]));
                return result;
            }
            Object[] params = (Object[]) o;
            Rectangle result = new Rectangle();
            result.setX1(((Number) params[0]).intValue());
            result.setY1(((Number) params[1]).intValue());
            result.setX2(((Number) params[2]).intValue());
            result.setY2(((Number) params[3]).intValue());
            return result;
        }
    };

    private static final Converter<RGB> rgbConverter = new Converter<RGB>() {
        public RGB convert(Object o) {
            RGB rgb = new RGB();
            if (o instanceof String) {
                o = intArrayConverter.convert(o);
            }
            if (o instanceof int[]) {
                int[] objects = (int[]) o;
                int pos = 0;
                rgb.setR(objects[pos++]);
                rgb.setG(objects[pos++]);
                rgb.setB(objects[pos++]);
                if (objects.length == 4) {
                    rgb.setA(objects[pos]);
                }
                return rgb;
            }
            Object[] objects = (Object[]) o;
            int pos = 0;
            rgb.setR(((Number) objects[pos++]).floatValue());
            rgb.setG(((Number) objects[pos++]).floatValue());
            rgb.setB(((Number) objects[pos++]).floatValue());
            if (objects.length == 4) {
                rgb.setA(((Number) objects[pos]).floatValue());
            }
            return rgb;
        }
    };

    private static final Converter<SndGrpNum> soundConvertor = new Converter<SndGrpNum>() {
        public SndGrpNum convert(Object o) {
            SndGrpNum sound = new SndGrpNum();
            if (o instanceof String) {
                String[] str = ((String) o).replaceAll(" ", "").replaceAll("\t", "").split(",");
                o = new Object[] {Integer.parseInt(str[0]), Integer.parseInt(str[1])};
            }
            Object[] objects = (Object[]) o;
            if (objects.length > 2) {
                sound.setPlaySpriteSnd((Boolean) objects[2]);
            }
            sound.setSnd_grp(((Number) objects[0]).intValue());
            sound.setSnd_item(((Number) objects[1]).intValue());
            return sound;
        }
    };

    private static final Converter<SprGrpNum> sprConverter = new Converter<SprGrpNum>() {
        public SprGrpNum convert(Object o) {
            Object[] objects = (Object[]) o;
            return new SprGrpNum(((Number) objects[0]).intValue(), ((Number) objects[1]).intValue());
        }
    };

    private static final Converter<BG.Type> bgTypeConverter = new Converter<BG.Type>() {
        public BG.Type convert(Object o) {
            return BG.Type.valueOf(o.toString().toUpperCase());
        }
    };

    private static final Converter<String> stringConverter = new Converter<String>() {
        public String convert(Object o) {
            return o == null ? "" : o.toString();
        }
    };

    private static final Converter<Trans> transConverter = new Converter<Trans>() {
        public Trans convert(Object o) {
            return Trans.valueOf(o.toString().toUpperCase());
        }
    };

    private static final Converter<Object[]> objectArrayConverter = new Converter<Object[]>() {
        public Object[] convert(Object o) {
            if (o == null) {
                return new Object[0];
            }
            if (o.getClass().isArray()) {
                return (Object[]) o;
            }
            return new Object[] {o};
        }
    };

    static {
        convertersMap.put(MugenPoint.class, pointConverter);
        convertersMap.put(MugenRect.class, mugenRectConverter);
        convertersMap.put(Rectangle.class, mugenRectangleConverter);
        convertersMap.put(int[].class, intArrayConverter);
        convertersMap.put(Integer.class, integerConverter);
        convertersMap.put(int.class, integerConverter);
        convertersMap.put(Float.class, floatConverter);
        convertersMap.put(float.class, floatConverter);
        convertersMap.put(float[].class, floatPrimiArrayConverter);
        convertersMap.put(PointF.class, pointfConverter);
        convertersMap.put(RGB.class, rgbConverter);
        convertersMap.put(SndGrpNum.class, soundConvertor);
        convertersMap.put(SprGrpNum.class, sprConverter);
        convertersMap.put(BG.Type.class, bgTypeConverter);
        convertersMap.put(String.class, stringConverter);
        convertersMap.put(Trans.class, transConverter);
        convertersMap.put(Object[].class, objectArrayConverter);
    }

    private static final Pattern LIST_PARTTERN_BEAN = Pattern.compile(".*(\\d+)");

    private BeanTools() {
    }

    public static Map<Class, Converter> getConvertersMap() {
        return convertersMap;
    }

    public static void setObject(Object bean, String access, Object value) throws Exception {
        int index = access.indexOf('.');
        if (index != -1) {
            String head = access.substring(0, index);
            String tail = access.substring(index + 1);
            Matcher m = LIST_PARTTERN_BEAN.matcher(head);
            if (m.find()) {
                String capture = m.group(1);
                int idxList = Integer.parseInt(capture);
                String headName = head.substring(0, head.length() - capture.length());
                Object child = getSimpleProperty(bean, headName);
                if (child instanceof Map) {
                    setObject(((Map) child).get(idxList), tail, value);
                    return;
                }
            }
            setObject(getSimpleProperty(bean, head), tail, value);
            return;
        }
        if (bean instanceof BG) {
            applyBgProperty((BG) bean, access, value);
            return;
        }
        if (bean instanceof BGdef) {
            applyBgdefProperty((BGdef) bean, access, value);
            return;
        }
    }

    private static void applyBgProperty(BG bg, String key, Object value) {
        String k = key.toLowerCase();
        if ("type".equals(k) && value instanceof String) {
            bg.setType((String) value);
        } else if ("spriteno".equals(k) && value instanceof SprGrpNum) {
            bg.setSpriteno((SprGrpNum) value);
        } else if ("start".equals(k) && value instanceof PointF) {
            bg.setStart((PointF) value);
        } else if ("width".equals(k) && value instanceof MugenPoint) {
            bg.setWidth((MugenPoint) value);
        } else if ("tile".equals(k) && value instanceof PointF) {
            bg.setTile((PointF) value);
        } else if ("velocity".equals(k) && value instanceof PointF) {
            bg.setVelocity((PointF) value);
        } else if ("delta".equals(k) && value instanceof PointF) {
            bg.setDelta((PointF) value);
        } else if ("trans".equals(k) && value instanceof String) {
            bg.setTrans((String) value);
        } else if ("mask".equals(k) && value instanceof Integer) {
            bg.setMask(((Integer) value).intValue());
        } else if ("layerno".equals(k) && value instanceof Integer) {
            bg.setLayerno(((Integer) value).intValue());
        } else if ("actionno".equals(k) && value instanceof Integer) {
            bg.setActionno(((Integer) value).intValue());
        } else if ("yscalestart".equals(k) && value instanceof Float) {
            bg.setYscalestart(((Float) value).floatValue());
        } else if ("yscaledelta".equals(k) && value instanceof Float) {
            bg.setYscaledelta(((Float) value).floatValue());
        }
    }

    private static void applyBgdefProperty(BGdef bgdef, String key, Object value) {
        if ("bgclearcolor".equalsIgnoreCase(key) && value instanceof RGB) {
            bgdef.setBgclearcolor((RGB) value);
        } else if ("debugbg".equalsIgnoreCase(key) && value instanceof Integer) {
            bgdef.setDebugbg(((Integer) value).intValue());
        }
    }

    public static Object getSimpleProperty(Object bean, String name) throws Exception {
        if (bean instanceof Map) {
            return ((Map) bean).get(name);
        }
        throw new NoSuchFieldException(name);
    }
}
