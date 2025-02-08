package org.lee.mugen.util;

import java.awt.Point;
import java.awt.Rectangle;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;
import org.lee.mugen.background.BG;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.renderer.RGB;
import org.lee.mugen.renderer.Trans;
import org.lee.mugen.renderer.PalFxSub.Sinadd;
import org.lee.mugen.sprite.character.SpriteCns.MoveType;
import org.lee.mugen.sprite.character.SpriteCns.Physics;
import org.lee.mugen.sprite.character.SpriteCns.Type;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub.AffectTeam;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub.AttrClass;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub.AttrLevel;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub.AttrType;
import org.lee.mugen.sprite.entity.Anim;
import org.lee.mugen.sprite.entity.BindToTargetSub;
import org.lee.mugen.sprite.entity.PointF;
import org.lee.mugen.sprite.entity.Postype;
import org.lee.mugen.sprite.entity.Priority;
import org.lee.mugen.sprite.entity.SndGrpNum;
import org.lee.mugen.sprite.entity.Sparkno;
import org.lee.mugen.sprite.entity.SprGrpNum;
import org.lee.mugen.sprite.entity.Velocity;
import org.lee.mugen.sprite.entity.BindToTargetSub.Pos;
import org.lee.mugen.sprite.entity.Priority.HitType;
import org.lee.mugen.sprite.parser.Parser;


public class BeanTools {

	public static interface Converter<T> {
		T convert(Object o);
	}


	private static Map<Class, Converter> convertersMap = new HashMap<Class, Converter>();

	private static Converter<Rectangle> rectangleConverter = new Converter<Rectangle>() {

		public Rectangle convert(Object o) {
			if (o instanceof String) {
				String[] params = o.toString().replaceAll(" ", "").split(",");
				int x = Integer.parseInt(params[0]);
				int y = Integer.parseInt(params[1]);
				int width = Integer.parseInt(params[2]);
				int height = Integer.parseInt(params[3]);

				Rectangle result = new Rectangle();
				result.x = x;
				result.y = y;
				result.width = width;
				result.height = height;
				return result;
			} else if (o.getClass().isArray()) {
				Object[] params = (Object[]) o;
				int x = ((Number) params[0]).intValue();
				int y = ((Number) params[1]).intValue();
				int width = ((Number) params[2]).intValue();
				int height = ((Number) params[3]).intValue();

				Rectangle result = new Rectangle();
				result.x = x;
				result.y = y;
				result.width = width;
				result.height = height;
				return result;
			}
			throw new IllegalArgumentException();
			
		}
		
	};
	
	private static Converter<org.lee.mugen.object.Rectangle> mugenRectangleConverter = new Converter<org.lee.mugen.object.Rectangle>() {

		public org.lee.mugen.object.Rectangle convert(Object o) {
			if (o instanceof String) {
				String[] params = o.toString().replaceAll(" ", "").split(",");
				int x = Integer.parseInt(params[0]);
				int y = Integer.parseInt(params[1]);
				int x1 = Integer.parseInt(params[2]);
				int y1 = Integer.parseInt(params[3]);

				org.lee.mugen.object.Rectangle result = new org.lee.mugen.object.Rectangle();
				result.setX1(x);
				result.setX2(x1);
				result.setY1(y);
				result.setY2(y1);
				return result;
			} else if (o.getClass().isArray()) {
				Object[] params = (Object[]) o;
				int x = ((Number) params[0]).intValue();
				int y = ((Number) params[1]).intValue();
				int x1 = ((Number) params[2]).intValue();
				int y1 = ((Number) params[3]).intValue();

				org.lee.mugen.object.Rectangle result = new org.lee.mugen.object.Rectangle();
				result.setX1(x);
				result.setX2(x1);
				result.setY1(y);
				result.setY2(y1);
				return result;
			}
			throw new IllegalArgumentException();
			
		}
		
	};
	
	private static Converter<Sinadd> sinaddClassConverter = new Converter<Sinadd>() {

		public Sinadd convert(Object o) {
			if (o.getClass().isArray()) {
				Object[] params = (Object[]) o;
				int count = params.length;
				Sinadd sinadd = new Sinadd();
				int pos = 0;
				if (count > 0) {
					Object p = params[pos++];
					sinadd.setAmpl_r(p instanceof Number? Parser.getIntValue(p): new Integer(p.toString()));
				}
				if (count > pos) {
					Object p = params[pos++];
					sinadd.setAmpl_g(p instanceof Number? Parser.getIntValue(p): new Integer(p.toString()));
				}
				if (count > pos) {
					Object p = params[pos++];
					sinadd.setAmpl_b(p instanceof Number? Parser.getIntValue(p): new Integer(p.toString()));
				}
				if (count > pos) {
					Object p = params[pos++];
					sinadd.setPeriod(p instanceof Number? Parser.getIntValue(p): new Integer(p.toString()));
				}
				return sinadd;
			}
			throw new IllegalArgumentException();
		}
		
	};
	private static Converter<BG.Type> bg$TypeClassConverter = new Converter<BG.Type>() {

		public BG.Type convert(Object o) {
			return BG.Type.valueOf(o.toString().toUpperCase());
		}
		
	};
	private static Converter<AttrClass> attrClassConverter = new Converter<AttrClass>() {

		public AttrClass convert(Object o) {
			if (o instanceof String || o instanceof Object[]) {
				String[] tokens = null;
				if (o instanceof String) {
					String str = o.toString().replaceAll(" ", "").toUpperCase();
					
					tokens = str.split(",");
				} else {
					Object[] objects = (Object[]) o;
					tokens = new String[objects.length];
					for (int i = 0; i < objects.length; i++)
						tokens[i] = objects[i].toString();
				}
				AttrClass attrClass = new AttrClass();
				int i = 0;
				String first = tokens[i++];
				int typeFirst = 0;
				for (char c: first.toUpperCase().toCharArray()) {
					typeFirst = typeFirst | Type.valueOf(c + "").getBit();
				}
				attrClass.setType(typeFirst);
				while (i < tokens.length) {
					char[] chars = tokens[i++].toCharArray();
					String slvl = String.valueOf(chars[0]);
					String stype = String.valueOf(chars[1]);
					AttrType typeToSet = null;
					AttrLevel lvlToSet = null;
					
					if (AttrLevel.isAttrLevel(slvl)) {
						lvlToSet = AttrLevel.valueOf(slvl);
						
					}
					if (AttrType.isAttrType(stype)) {
						typeToSet = AttrType.valueOf(stype);
						
					}
					if (AttrType.isAttrType(slvl)) {
						typeToSet = AttrType.valueOf(slvl);
						
					}
					if (AttrLevel.isAttrLevel(stype)) {
						lvlToSet = AttrLevel.valueOf(stype);
						
					}
					if (lvlToSet == null)
						lvlToSet = AttrLevel.N;
					if (typeToSet == null)
						typeToSet = AttrType.A;
					attrClass.addAttrTypeAndLevel(typeToSet, lvlToSet);
				}
				return attrClass;
			} else if (o.getClass() == AttrClass.class) {
				return (AttrClass) o;
			}
			throw new IllegalArgumentException();
		}
		
	};
	
//	private static Converter<ReversalAttrClass> reversalAttrClassConverter = new Converter<ReversalAttrClass>() {
//
//		public ReversalAttrClass convert(Object o) {
//			if (o instanceof String) {
//				String str = o.toString().replaceAll(" ", "").toUpperCase();
//				ReversalAttrClass attrClass = new ReversalAttrClass();
//				String[] tokens = str.split(",");
//				
//				if (tokens.length > 0) {
//					String first = tokens[0];
//					for (char c: first.toCharArray())
//						attrClass.addType(Type.valueOf(String.valueOf(c)));
//				}
//				for (int i = 1; i < tokens.length; i++) {
//					char[] chars = tokens[i].toCharArray();
//					AttrLevel lvl = null;
//					AttrType type = null;
//					if (AttrLevel.isAttrLevel(String.valueOf(chars[0]))) {
//						lvl = AttrLevel.valueOf(String.valueOf(chars[0]));
//					} else if (AttrType.isAttrType(String.valueOf(chars[0]))) {
//						type = AttrType.valueOf(String.valueOf(chars[0]));
//					}
//					
//					if (AttrLevel.isAttrLevel(String.valueOf(chars[1]))) {
//						lvl = AttrLevel.valueOf(String.valueOf(chars[1]));
//					} else if (AttrType.isAttrType(String.valueOf(chars[1]))) {
//						type = AttrType.valueOf(String.valueOf(chars[1]));
//					}
//					attrClass.addAttrTypeAndLevel(type, lvl);
//				}
//				return attrClass;
//			} else if (o.getClass() == ReversalAttrClass.class) {
//				return (ReversalAttrClass) o;
//			}
//			throw new IllegalArgumentException();
//		}
//		
//	};
	
	private static Converter<AffectTeam> affectTeamConverter = new Converter<AffectTeam>() {

		public AffectTeam convert(Object o) {
			if (o instanceof String) {
				return AffectTeam.valueOf(o.toString());
			} else if (o.getClass() == AffectTeam.class) {
				return (AffectTeam) o;
			}
			throw new IllegalArgumentException();
		}
		
	};

	private static Converter<BindToTargetSub.Pos> bindToTargetSub$posConverter = new Converter<BindToTargetSub.Pos>() {

		public Pos convert(Object o) {
			Pos pos = new Pos();
			if (o.getClass().isArray()) {
				Object[] objs = (Object[]) o;
				int x = ((Number)objs[0]).intValue();
				int y = ((Number)objs[1]).intValue();
				if (objs.length > 2) {
					String sType = objs[2].toString();
					BindToTargetSub.BindToTargetType type = BindToTargetSub.BindToTargetType.valueOf(sType.toUpperCase());
					pos.setType(type);
				}
				pos.setX(x);
				pos.setY(y);

				
				return pos;
			}
			assert false;
			return null;
		
		}
		
	};
	
	private static Converter<SndGrpNum> soundConvertor = new Converter<SndGrpNum>() {

		public SndGrpNum convert(Object o) {
			SndGrpNum sound = new SndGrpNum();
			if (o instanceof String) {
				String[] str = ((String) o).replaceAll(" ", "").replaceAll("\t", "").split(",");
				Object[] objects = new Object[2];
				objects[0] = Integer.parseInt(str[0]);
				objects[1] = Integer.parseInt(str[1]);
				o = objects;
			} 
			
			if (o.getClass().isArray()) {
				Object[] objects = (Object[]) o;
				if (objects.length > 2)
					sound.setPlaySpriteSnd((Boolean) objects[2]);
				sound.setSnd_grp(((Number) objects[0]).intValue());
				sound.setSnd_item(((Number) objects[1]).intValue());
				return sound;
			}
			return sound;
		}
		
	};

	private static Converter<RGB> rgbConverter = new Converter<RGB>() {

		public RGB convert(Object o) {
			RGB rgb = new RGB();
			if (o instanceof String) {
				o = intArrayConverter.convert(o);
			}
			if (o != null &&(o instanceof RGB)) {
				return (RGB) o;
			} else if (o.getClass() == int[].class) {
				int[] objects = (int[]) o;
				int pos = 0;

				rgb.setR(objects[pos++]);
				rgb.setG(objects[pos++]);
				rgb.setB(objects[pos++]);
				if (objects.length == 4) {
					rgb.setA(objects[pos++]);
				}
				return rgb;
				
			} else if (o.getClass().isArray()) {
				Object[] objects = (Object[]) o;
				int pos = 0;

				rgb.setR(((Number)objects[pos++]).floatValue());
				rgb.setG(((Number)objects[pos++]).floatValue());
				rgb.setB(((Number)objects[pos++]).floatValue());
				if (objects.length == 4) {
					rgb.setA(((Number)objects[pos++]).floatValue());
				}
				return rgb;
			} else if (o.getClass() == String.class) {
				String s = o.toString().trim();
				String[] arr = s.replace(" ", "").split(",");
				int[] iarr = new int[arr.length];
				for (int i = 0; i < arr.length; i++)
					iarr[i] = Integer.parseInt(arr[i]);
				convert(iarr);

			}
			assert false;
			return rgb;
		}
		
	};

	
	private static Converter<HitDefSub.Pausetime> hitdef$pausetimeConverter = new Converter<HitDefSub.Pausetime>() {

		public HitDefSub.Pausetime convert(Object o) {
			HitDefSub.Pausetime pausetime = new HitDefSub.Pausetime();

				Object[] fs = (Object[]) o;
				int p1pausetime = 0;
				int p2shaketime = 0;
				if (fs[0] instanceof Valueable) {
					Valueable v1 = (Valueable) fs[0];
					p1pausetime = Parser.getIntValue(v1.getValue(null));
				} else {
					p1pausetime = ((Number)fs[0]).intValue();
				}
				if (fs[0] instanceof Valueable) {
					Valueable v2 = (Valueable) fs[1];
					p2shaketime = Parser.getIntValue(v2.getValue(null));
				} else {
					p2shaketime = ((Number)fs[1]).intValue();
				}
				
				pausetime.setP1_pausetime(p1pausetime);
				pausetime.setP2_shaketime(p2shaketime);
			return pausetime;
		}
		
	};
	
	private static Converter<Velocity> velocityConverter = new Converter<Velocity>() {

		public Velocity convert(Object o) {
			Velocity vel = new Velocity();
			if (o == null) {
				return vel;

			} else if (o instanceof Object[]) {
				Object[] fs = (Object[]) o;
				vel.setX(Float.parseFloat(fs[0].toString()));
				vel.setY(Float.parseFloat(fs[1].toString()));
				return vel;
			} else if (o instanceof Float[]) {
				Float[] fs = (Float[]) o;
				vel.setX(fs[0]);
				vel.setY(fs[1]);
				return vel;
			} else if (o instanceof Float) {
				Float f = (Float) o;
				vel.setX(f);
				return vel;
			}
			throw new IllegalArgumentException(o.getClass().getCanonicalName() + " not implemented for " + Velocity.class.getName());
		}

	};
	//Priority
	private static Converter<Priority> hitdef$priorityConverter = new Converter<Priority>() {

		public Priority convert(Object o) {
			Object[] v = (Object[]) o;
			Priority priority = new Priority();
			priority.setHit_prior(Parser.getIntValue(v[0]));
			priority.setHit_type((HitType) v[1]);
			return priority;
		}

	};
	// Spr
	private static Converter<SprGrpNum> sprConverter = new Converter<SprGrpNum>() {

		public SprGrpNum convert(Object o) {
			
			if (o.getClass().isArray()) {
				Object[] objects = (Object[]) o;
				SprGrpNum spr = new SprGrpNum(((Number) objects[0]).intValue(), ((Number) objects[1]).intValue());
				return spr;
			}
			throw new IllegalArgumentException("Not supported checkwhat is excatly");
		}
	};
	
	
	// Point
	private static Converter<Point> pointConverter = new Converter<Point>() {

		public Point convert(Object o) {
			if (o instanceof String) {
				String[] value = ((String) o).replaceAll(" ", "").split(",");
				return new Point(Integer.parseInt(value[0]), Integer.parseInt(value[1]));
			} else if (o.getClass().isArray()) {
				Object[] objects = (Object[]) o;
				Point pt = new Point(((Number) objects[0]).intValue(), ((Number) objects[1]).intValue());
				return pt;
			} else if (o instanceof Number) {
				return new Point(((Number)o).intValue(), 0);
			}
			throw new IllegalArgumentException("Not supported checkwhat is excatly");
		}
	};

	//Boolean
	private static Converter<Boolean> booleanConverter = new Converter<Boolean>() {

		public Boolean convert(Object o) {
			
			if (o instanceof Number) {
				return ((Number)o).intValue() != 0;
			}
			throw new IllegalArgumentException("Not supported check what is excatly");
		}
	};

	// AnimExplod
	private static Converter<Anim> animExplodConverter = new Converter<Anim>() {

		public Anim convert(Object o) {
			Anim anim = new Anim();
			if (o.getClass().isArray()) {
				Object[] objects = (Object[]) o;
				anim.setSpriteUse((Boolean) objects[1]);
				anim.setAction(((Number) objects[0]).intValue());
				return anim;
			}
			String str = o.toString();
			if (str.indexOf("f") != -1) {
				str = str.substring(1);
				anim.setSpriteUse(false);
			} else {
				anim.setSpriteUse(true);
				
			}
			try {
				anim.setAction((int) Float.parseFloat(str));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			anim.setAction((int) Float.parseFloat(str));
			return anim;
		}

	};
	
	//Sparkno
	private static Converter<Sparkno> sparknoConverter = new Converter<Sparkno>() {

		public Sparkno convert(Object o) {
			if (o instanceof Sparkno)
				return (Sparkno) o;
			Sparkno sparkno = new Sparkno();
			if (o.getClass().isArray()) {
				Object[] objects = (Object[]) o;
				sparkno.setSpriteUse((Boolean) objects[1]);
				sparkno.setAction(((Number) objects[0]).intValue());
				return sparkno;
			}
			String str = o.toString();
			if (str.indexOf("s") != -1) {
				str = str.substring(1);
				sparkno.setSpriteUse(true);
			}
			try {
				sparkno.setAction((int) Float.parseFloat(str));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			sparkno.setAction((int) Float.parseFloat(str));
			return sparkno;
		}

	};
	// Projectyle$postype
	private static Converter<Postype> postypeConverter = new Converter<Postype>() {

		public Postype convert(Object o) {
			return Postype.valueOf(o.toString().toLowerCase());
		}

	};
	
	// HitDefSub$Type
	private static Converter<HitDefSub.Type> hitdef$typeConverter = new Converter<HitDefSub.Type>() {

		public HitDefSub.Type convert(Object o) {
			if (o.toString().toUpperCase().startsWith("H"))
				o = "HIGH";
			return HitDefSub.Type.valueOf(o.toString().toUpperCase());

		}

	};
	private static Converter<Trans> afterImage$transConverter = new Converter<Trans>() {

		public Trans convert(Object o) {
			return Trans.valueOf(o.toString().toUpperCase());
		}

	};
	private static Converter<HitDefSub.AnimType> hitdef$animTypeConverter = new Converter<HitDefSub.AnimType>() {

		public HitDefSub.AnimType convert(Object o) {
			return HitDefSub.AnimType.getValueFromStr(o.toString().toUpperCase());
		}

	};
	
	private static Converter<PointF> pointfConverter = new Converter<PointF>() {

		public PointF convert(Object o) {
			float[] values = floatPrimiArrayConverter.convert(o);
			PointF p = new PointF();
			int size = values.length;
			if (size >= 1)
				p.setX(values[0]);
			if (size >= 2)
				p.setY(values[1]);
			
			return p;
		}

	};

	private static Converter<List<Integer>> integerListConverter = new Converter<List<Integer>>() {

		public List<Integer> convert(Object o) {
			if (o == null) {
				return new ArrayList<Integer>();

			} else if (o.getClass().isArray()) {
				Object[] obs = (Object[]) o;
				List<Integer> list = new ArrayList<Integer>();
				
				for (Object obj: obs)
					list.add(integerConverter.convert(obj));
				return list;
			} else {
				List<Integer> list = new ArrayList<Integer>();
				list.add(integerConverter.convert(o));
				return list;
			}
		}
		
	};
	
	private static Converter<Float[]> floatArrayConverter = new Converter<Float[]>() {

		public Float[] convert(Object o) {
			if (o == null) {
				return new Float[] { 0f };

			} else if (o instanceof Float[]) {
				return (Float[]) o;
			} else if (o instanceof Float) {
				return new Float[] { (Float) o };
			} else if (o.getClass().isArray()) {
				Object[] array = (Object[]) o;
				Float[] farray = new Float[array.length];

				for (int i = 0; i < farray.length; ++i) {
					Float f = array[i] == null ? 0f : floatConverter.convert(array[i]);
					farray[i] = new Float(f);

				}
				return farray;
			} else {
				return new Float[] { new Float(o.toString()) };
			}
		}
	};

	private static Converter<float[]> floatPrimiArrayConverter = new Converter<float[]>() {

		public float[] convert(Object o) {
			if (o instanceof String) {
				String[] value = ((String) o).replaceAll(" ", "").split(",");
				return new float[] {Float.parseFloat(value[0]), Float.parseFloat(value[1])};
			} else if (o == null) {
				return new float[] { 0f };

			} else if (o instanceof float[]) {
				return (float[]) o;
			} else if (o instanceof Float) {
				return new float[] { ((Float) o).floatValue() };
			} else if (o.getClass().isArray()) {
				Object[] array = (Object[]) o;
				float[] farray = new float[array.length];

				for (int i = 0; i < farray.length; ++i) {
					float f = array[i] == null ? 0f : floatConverter.convert(array[i]);
					farray[i] = f;

				}
				return farray;
			} else {
				return new float[] { new Float(o.toString()) };
			}
		}
	};

	private static Converter<Integer[]> integerArrayConverter = new Converter<Integer[]>() {

		public Integer[] convert(Object o) {
			if (o == null) {
				return new Integer[] { 0 };

			} else if (o instanceof Integer[]) {
				return (Integer[]) o;
			} else if (o instanceof Integer) {
				return new Integer[] { ((Integer) o).intValue() };
			} else if (o.getClass().isArray()) {
				Object[] array = (Object[]) o;
				Integer[] farray = new Integer[array.length];

				for (int i = 0; i < farray.length; ++i) {
					Integer f = array[i] == null ? 0
							: integerConverter.convert(array[i]);
					farray[i] = f;

				}
				return farray;
			} else {
				return new Integer[] { new Integer(o.toString()) };
			}
		}
	};

	private static Converter<int[]> intArrayConverter = new Converter<int[]>() {

		public int[] convert(Object o) {

			
			if (o == null) {
				return new int[] { 0 };

			} else if (o instanceof int[]) {
				return (int[]) o;
			} else if (o instanceof Number) {
				return new int[] { ((Number) o).intValue() };
			} else if (o.getClass().isArray()) {
				Object[] array = (Object[]) o;
				int[] farray = new int[array.length];

				for (int i = 0; i < farray.length; ++i) {
					int f = array[i] == null ? 0 : integerConverter.convert(array[i]);
					farray[i] = f;

				}
				return farray;
			} else if (o instanceof String) {
				String s = o.toString().trim();
				String[] arr = s.replace(" ", "").split(",");
				int[] iarr = new int[arr.length];
				for (int i = 0; i < arr.length; i++)
					iarr[i] = Integer.parseInt(arr[i]);
				return iarr;
			} else {
				return new int[] { new Integer(o.toString()) };
			}
		}
	};

	private static Converter<Integer> integerConverter = new Converter<Integer>() {

		public Integer convert(Object o) {
			if (o == null) {
				return 0;
			} else if (o instanceof Number) {
				return ((Number) o).intValue();
			} else if (o.getClass().isArray()) {
				Object[] obs = (Object[]) o;
				if (obs.length > 1) {
//					System.err.println("Normaly it have only one arg, thes second will be ignore");
					throw new IllegalArgumentException("this argument iznogoud");

				}
				if (obs.length == 0)
					return 0;
				return new Integer(obs[0].toString());
			} else {
				return new Integer(o.toString());
			}
		}
	};
	

	private static Converter<Float> floatConverter = new Converter<Float>() {

		public Float convert(Object o) {
			if (o == null) {
				return 0f;
			} else if (o instanceof Number) {
				return ((Number) o).floatValue();
			} else if (o.getClass().isArray()) {
				Object[] obs = (Object[]) o;
				if (obs.length > 1)
					throw new IllegalArgumentException("this argument iznogoud");
				if (obs.length == 0)
					return 0f;
				return new Float(obs[0].toString());
			} else {
				return new Float(o.toString());
			}
		}
	};

	private static Converter<Double> doubleConverter = new Converter<Double>() {

		public Double convert(Object o) {
			if (o == null) {
				return 0d;
			} else if (o instanceof Number) {
				return ((Number) o).doubleValue();
			} else if (o.getClass().isArray()) {
				Object[] obs = (Object[]) o;
				if (obs.length > 1)
					throw new IllegalArgumentException("this argument iznogoud");
				if (obs.length == 0)
					return 0d;
				return new Double(obs[0].toString());
			} else {
				return new Double(o.toString());
			}
		}
	};

	private static Converter<String> stringConverter = new Converter<String>() {

		public String convert(Object o) {
			if (o == null) {
				return "";
			} else {
				return o.toString();
			}
		}
	};

	private static Converter<String[]> stringArrayConverter = new Converter<String[]>() {

		public String[] convert(Object o) {
			if (o == null) {
				return new String[] { "" };
			} else if (o.getClass().isArray()) {
				Object[] obs = (Object[]) o;
				String[] farray = new String[obs.length];

				for (int i = 0; i < farray.length; ++i) {
					String f = obs[i] == null ? "" : obs[i].toString();
					farray[i] = f;

				}
				return farray;
			} else {
				return new String[] { o.toString() };
			}
		}
	};

	private static Converter<Object[]> objectArrayConverter = new Converter<Object[]>() {

		public Object[] convert(Object o) {
			if (o == null) {
				return new Object[0];
			} else if (o.getClass().isArray()) {
				return (Object[]) o;
			} else {
				return new Object[] { o };
			}
		}
	};
	private static Converter<Type> stateTypeConverter = new Converter<Type>() {

		public Type convert(Object o) {
			return Type.valueOf(o.toString().toUpperCase());
		}};

	private static Converter<MoveType> moveTypeConverter = new Converter<MoveType>() {

		public MoveType convert(Object o) {
			return MoveType.valueOf(o.toString().toUpperCase());
		}};
	private static Converter<Physics> physicsConverter = new Converter<Physics>() {
	
		public Physics convert(Object o) {
			return Physics.valueOf(o.toString().toUpperCase());
		}};

		
	static {
		
		
		convertersMap.put(org.lee.mugen.object.Rectangle.class, mugenRectangleConverter);
		convertersMap.put(Rectangle.class, rectangleConverter);
		convertersMap.put(Sinadd.class, sinaddClassConverter);
		convertersMap.put(BG.Type.class, bg$TypeClassConverter);
//		convertersMap.put(ReversalAttrClass.class, reversalAttrClassConverter);
		convertersMap.put(SprGrpNum.class, sprConverter);
		convertersMap.put(AttrClass.class, attrClassConverter);
		convertersMap.put(AffectTeam.class, affectTeamConverter);
		convertersMap.put(BindToTargetSub.Pos.class, bindToTargetSub$posConverter);
		
		convertersMap.put(Type.class, stateTypeConverter);
		convertersMap.put(MoveType.class, moveTypeConverter);
		convertersMap.put(Physics.class, physicsConverter);		
		
		//Entity
		convertersMap.put(RGB.class, rgbConverter);
		convertersMap.put(Velocity.class, velocityConverter);

		convertersMap.put(SndGrpNum.class, soundConvertor);
		
		convertersMap.put(Anim.class, animExplodConverter);
		convertersMap.put(Sparkno.class, sparknoConverter);
		convertersMap.put(Postype.class, postypeConverter);
		convertersMap.put(Trans.class, afterImage$transConverter);
		
		convertersMap.put(Priority.class, hitdef$priorityConverter);
		convertersMap.put(HitDefSub.Type.class, hitdef$typeConverter);
		convertersMap.put(HitDefSub.AnimType.class, hitdef$animTypeConverter);
		convertersMap.put(HitDefSub.Pausetime.class, hitdef$pausetimeConverter);

		convertersMap.put(PointF.class, pointfConverter);
		
		//
		convertersMap.put(Point.class, pointConverter);
		//
		convertersMap.put(Float[].class, floatArrayConverter);
		convertersMap.put(float[].class, floatPrimiArrayConverter);
		convertersMap.put(Float.class, floatConverter);
		convertersMap.put(float.class, floatConverter);
		convertersMap.put(Double.class, doubleConverter);
		convertersMap.put(double.class, doubleConverter);
		
		convertersMap.put(Integer[].class, integerArrayConverter);
		convertersMap.put(int[].class, intArrayConverter);
		convertersMap.put(Integer.class, integerConverter);
		convertersMap.put(int.class, integerConverter);
		
		convertersMap.put(Boolean.class, booleanConverter);
		convertersMap.put(boolean.class, booleanConverter);
		
		
		convertersMap.put(String[].class, stringArrayConverter);
		convertersMap.put(String.class, stringConverter);
		
		convertersMap.put(Object[].class, objectArrayConverter);
		
	}
	public static Map<Class, Converter> getConvertersMap() {
		return convertersMap;
	}
	private static Pattern LIST_PARTTERN_BEAN = Pattern.compile(".*(\\d+)");
	
	
	
	public static void setObject(Object bean, String acces, Object value)
			throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, IntrospectionException {
		int index = acces.indexOf('.');
		if (index != -1) {
			String newBean = acces.substring(0, index);
			String target = acces.substring(index + 1);
			Matcher m = LIST_PARTTERN_BEAN.matcher(newBean);
			if (m.find()) {
				String capture = m.group(1);
				int idxList = Integer.parseInt(capture);
				String newBeanTemp = newBean.substring(0, newBean.length() - capture.length());
				try {
					Object o = PropertyUtils.getProperty(bean, newBeanTemp);
					if (o instanceof Map) {
						Map map = (Map) o;
						o = map.get(idxList);
						setObject(o, target, value);
						return;
					}
				} catch (NoSuchMethodException nsme) {
					// TODO: handle exception
//					nsme.printStackTrace();
				} catch (Exception e) {
//					e.printStackTrace();
				}
			} 
			Object o = PropertyUtils.getProperty(bean, newBean);
			setObject(o, target, value);
				
		} else {
			PropertyDescriptor pd = null;
			Matcher m = LIST_PARTTERN_BEAN.matcher(acces);
			if (m.find()) {
				String capture = m.group(1);
				int idxList = Integer.parseInt(capture);
				String newBeanTemp = acces.substring(0, acces.length() - capture.length());
			} 
			pd = PropertyUtils.getPropertyDescriptor(bean, acces);
			if (pd == null)
				System.err.println(acces);
			Class aClass = pd.getPropertyType();
			Method mW = pd.getWriteMethod();
			if (mW == null) {
				mW = getMethod(bean.getClass(), acces, pd.getPropertyType());
			}
			if (mW == null) {
				mW = getMethod(bean.getClass(), acces);
			}
			if (mW == null)
				System.err.println("Error for getMethod " + acces + " of class : " + bean.getClass().getName());
			aClass = mW.getParameterTypes()[0];
			Converter converter = convertersMap.get(aClass);
			if (converter == null){
				System.err.println("Error for get Convertor " + acces + " of class : " + bean.getClass().getName());
				return;
			}
			Object o = converter.convert(value); //use spriteId for dynamic value
			mW.invoke(bean, o);
			o = converter.convert(value); 
		}
	}
		
	 private static Method getMethod(Class clazz, String name, Class clz)
			throws SecurityException, NoSuchMethodException {
		name = "set" + Character.toUpperCase(name.charAt(0))
				+ name.substring(1);
		try {
			Method m = clazz.getMethod(name, clz);
			if (m == null) {
				if (clazz != Object.class && clazz.getSuperclass() != Object.class)
					m = clazz.getSuperclass().getMethod(name, clz);
			}
			return m;
			
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return null;
	 }
	private static Method getMethod(Class clazz, String name) {
		name = "set" + Character.toUpperCase(name.charAt(0)) + name.substring(1);
		for (Method m: clazz.getMethods()) {
			if (m.getName().equals(name)) {
				return m;
			}
		}
		return null;
	}
}
