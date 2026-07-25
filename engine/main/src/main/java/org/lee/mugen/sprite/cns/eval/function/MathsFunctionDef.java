package org.lee.mugen.sprite.cns.eval.function;

import static org.lee.mugen.sprite.parser.Parser.getFloatValue;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.lee.mugen.parser.type.Functionable;
import org.lee.mugen.parser.type.Valueable;
public class MathsFunctionDef {

	private static Map<String, MathFunction> functionInfoMap = buildFunctionInfo();
	
	public static Functionable getFunction(String func) {
		return functionInfoMap.get(func);
	}
	private static Map<String, MathFunction> buildFunctionInfo() {
		Map<String, MathFunction> map = new HashMap<String, MathFunction>();
		map.put("abs", new MathFunction("abs", 100, 1, new Abs()));
		map.put("acos", new MathFunction("acos", 100, 1, new Acos()));
		map.put("asin", new MathFunction("asin", 100, 1, new Asin()));
		map.put("atan", new MathFunction("atan", 100, 1, new Atan()));
		map.put("ceil", new MathFunction("ceil", 100, 1, new Ceil()));
		map.put("cos", new MathFunction("cos", 100, 1, new Cos()));
		map.put("e", new MathFunction("e", 100, 1, new E()));
		map.put("exp", new MathFunction("exp", 100, 1, new Exp()));
		map.put("floor", new MathFunction("floor", 100, 1, new Floor()));
		map.put("ifelse", new MathFunction("ifelse", 100, 3, new Ifelse()));
		map.put("ln", new MathFunction("ln", 100, 1, new Ln()));
		map.put("log", new MathFunction("log", 100, 1, new Log()));
		map.put("pi", new MathFunction("pi", 100, 0, new Pi()));
		map.put("sin", new MathFunction("sin", 100, 1, new Sin()));
		map.put("tan", new MathFunction("tan", 100, 1, new Tan()));

		map.put("max", new MathFunction("max", 100, 2, new Max()));
		map.put("min", new MathFunction("min", 100, 2, new Min()));
//		map.put("random", new MathFunction("random", 100, 1, new Random()));
//		map.put("rangerandom", new MathFunction("rangerandom", 100, 2, new RangeRandom()));
		
		return map;
	}
	
	//////////////////////////////////////////////
	private static class Max implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return Math.max(getFloatValue(params[0].getValue(spriteId)), getFloatValue(params[1].getValue(spriteId)));
		}
		public void reset() {}
	}
	private static class Min implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return Math.min(getFloatValue(params[0].getValue(spriteId)), getFloatValue(params[1].getValue(spriteId)));
		}
		public void reset() {}
	}
//	private static class Random implements Functionable {
//		public Object getValue(String spriteId, Valueable... params) {
//			return MugenRandom.getRandomNumber(0, getIntValue(params[0].getValue(spriteId)));
//		}
//		public void reset() {}
//	}
//	private static class RangeRandom implements Functionable {
//		public Object getValue(String spriteId, Valueable... params) {
//			return MugenRandom.getRandomNumber(getIntValue(params[0].getValue(spriteId)), getIntValue(params[1].getValue(spriteId)));
//		}
//		public void reset() {}
//	}
	
	
	private static class Abs implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return Math.abs(getFloatValue(params[0].getValue(spriteId)));
		}
		public void reset() {}
	}

	private static class Acos implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return Math.acos(getFloatValue(params[0].getValue(spriteId)));
		}
		public void reset() {}
	}

	private static class Asin implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return Math.asin(getFloatValue(params[0].getValue(spriteId)));
		}
		public void reset() {}
	}

	private static class Atan implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return Math.atan(getFloatValue(params[0].getValue(spriteId)));
		}
		public void reset() {}
	}

	private static class Ceil implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return Math.ceil(getFloatValue(params[0].getValue(spriteId)));
		}
		public void reset() {}
	}

	private static class Cos implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return Math.cos(getFloatValue(params[0].getValue(spriteId)));
		}
		public void reset() {}
	}

	private static class E implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return Math.E;
		}
		public void reset() {}
	}

	private static class Exp implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return Math.exp(getFloatValue(params[0].getValue(spriteId)));
		}
		public void reset() {}
	}

	private static class Floor implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return Math.floor(getFloatValue(params[0].getValue(spriteId)));
		}
		public void reset() {}
	}

	private static class Ifelse implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
		 	Valueable bool = params[0];
			Valueable v1 = params[1];
			Valueable v2 = params[2];

			return getFloatValue(bool.getValue(spriteId)) != 0 ? v1.getValue(spriteId) : v2.getValue(spriteId);
		}
		public void reset() {}
	}

	private static class Ln implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return Math.log10(getFloatValue(params[0].getValue(spriteId)));
		}
		public void reset() {}
	}

	private static class Log implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return Math.log(getFloatValue(params[0].getValue(spriteId)));
		}
		public void reset() {}
	}

	private static class Pi implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return Math.PI;
		}
		public void reset() {}
	}

	private static class Sin implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return Math.sin(getFloatValue(params[0].getValue(spriteId)));
		}
		public void reset() {}
	}

	private static class Tan implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return Math.tan(getFloatValue(params[0].getValue(spriteId)));
		}
		public void reset() {}
	}

	public static String getMathsFunctionDefRegex() {
		StringBuilder strBuilder = new StringBuilder();
		for (Iterator<String> iter = MathsFunctionDef.functionInfoMap.keySet().iterator(); iter.hasNext();) {
			String key = iter.next();
			strBuilder.append("(" + "\\b" + key + "\\b" + ")" + (iter.hasNext()? "|": ""));
		}
		return strBuilder.toString();
	}



}
