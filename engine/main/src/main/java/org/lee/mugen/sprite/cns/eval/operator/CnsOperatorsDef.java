package org.lee.mugen.sprite.cns.eval.operator;

import static org.lee.mugen.sprite.parser.Parser.getFloatValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.lee.mugen.parser.type.Functionable;
import org.lee.mugen.parser.type.ObjectValueable;
import org.lee.mugen.parser.type.Setable;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.MathFunction;

public class CnsOperatorsDef {
	private static Map<String, MathFunction> functionsMap = buildOperatorMap();
	
	private static Map<String, MathFunction> buildOperatorMap()	{
		Map<String, MathFunction> map = new HashMap<String, MathFunction>();
		map.put("+", new MathFunction("+", 100, 2, new Sum()));
		map.put("-", new MathFunction("-", 100, 2, new Sub()));
		map.put("*", new MathFunction("*", 200, 2, new Mul()));
		map.put("**", new MathFunction("**", 400, 2, new Exp()));
		map.put("/", new MathFunction("/", 200, 2, new Div()));
		map.put("%", new MathFunction("%", 200, 2, new Mod()));
		map.put("!=", new MathFunction("!=", 50, 2, new NotEquals()));
		map.put("=", new MathFunction("=", 50, 2, new Equals()));
		map.put(",", new MathFunction(",", 50, 0, new Functionable() {
			public void reset() {}

			public Object getValue(String spriteId, Valueable... params) {
				return null;
			}}));
		map.put("<", new MathFunction("<", 50, 2, new Inf()));
		map.put("<=", new MathFunction("<=", 50, 2, new InfEq()));
		map.put(">", new MathFunction(">", 50, 2, new Sup()));
		map.put(">=", new MathFunction(">=", 50, 2, new SupEq()));
		
		
		// boolean
		map.put("&&", new MathFunction("&&", 27, 2, new And()));
		map.put("||", new MathFunction("||", 25, 2, new Or()));
		map.put("^^", new MathFunction("^^", 25, 2, new Xor()));
		
		// Unary
		map.put("~", new MathFunction("~", 300, 1, new BitNot()));
		map.put("!", new MathFunction("!", 300, 1, new Not()));
		
		// byte Op
		map.put("&", new MathFunction("&", 300, 2, new BitAnd()));
		map.put("|", new MathFunction("|", 300, 2, new BitOr()));
		map.put("^", new MathFunction("^", 300, 2, new BitXor()));
		
		return map;
	};

	public static String getOperatorRegex() {
		List<String> list = new ArrayList<String>();
		StringBuilder builder = new StringBuilder();
		list.addAll(functionsMap.keySet());
		Collections.sort(list, new Comparator<String>() {

			public int compare(String o1, String o2) {
				return o2.length() - o1.length();
			}});
		builder.append("(");
		for (Iterator<String> iter = list.iterator(); iter.hasNext();) {
			String s = iter.next();
			s = s.replaceAll("\\+", "\\\\+");
			s = s.replaceAll("\\*", "\\\\*");
			s = s.replaceAll("\\%", "\\\\%");
			s = s.replaceAll("\\|", "\\\\|");
			s = s.replaceAll("\\^", "\\\\^");

			builder.append(s + (iter.hasNext()? "|": ""));
		}
		builder.append(")");
		return builder.toString();
	}
	public static MathFunction getOperator(String op) {
		return functionsMap.get(op);
	}


	
private static Map<String, MathFunction> specialOpMap = buildSpecialOperatorMap();
	
	private static Map<String, MathFunction> buildSpecialOperatorMap() {
		Map<String, MathFunction> map = new HashMap<String, MathFunction>();
		final Functionable inf = getOperator("<");
		final Functionable infEq = getOperator("<=");
		final Functionable sup = getOperator(">");
		final Functionable supEq = getOperator(">=");
		
		map.put("!=[]", new MathFunction("!=[]", 30, 3, new Functionable() {
			public void reset() {}
			public Object getValue(String spriteId, Valueable... params) {
				Valueable compare = params[0];
				Valueable l = params[1];
				Valueable r = params[2];
				return getFloatValue(inf.getValue(spriteId, compare, l)) != 0 || getFloatValue(sup.getValue(spriteId, compare, r)) != 0? 1: 0;
			}}));
		map.put("!=(]", new MathFunction("!=(]", 30, 3, new Functionable() {
			public void reset() {}
			public Object getValue(String spriteId, Valueable... params) {
				Valueable compare = params[0];
				Valueable l = params[1];
				Valueable r = params[2];
				return getFloatValue(infEq.getValue(spriteId, compare, l)) != 0 || getFloatValue(sup.getValue(spriteId, compare, r)) != 0? 1: 0;
			}}));
		map.put("!=[)", new MathFunction("!=[)", 30, 3, new Functionable() {
			public void reset() {}
			public Object getValue(String spriteId, Valueable... params) {
				Valueable compare = params[0];
				Valueable l = params[1];
				Valueable r = params[2];
				return getFloatValue(inf.getValue(spriteId, compare, l)) != 0 || getFloatValue(supEq.getValue(spriteId, compare, r)) != 0? 1: 0;
			}}));

		
		
		map.put("=()", new MathFunction("=()", 30, 3, new Functionable() {
			public void reset() {}
			public Object getValue(String spriteId, Valueable... params) {
				Valueable compare = params[0];
				Valueable l = params[1];
				Valueable r = params[2];
				return getFloatValue(sup.getValue(spriteId, compare, l)) != 0 && getFloatValue(inf.getValue(spriteId, compare, r)) != 0? 1: 0;
			}}));
		map.put("=(]", new MathFunction("=(]", 30, 3, new Functionable() {
			public void reset() {}
			public Object getValue(String spriteId, Valueable... params) {
				Valueable compare = params[0];
				Valueable l = params[1];
				Valueable r = params[2];
				return getFloatValue(sup.getValue(spriteId, compare, l)) != 0 && getFloatValue(infEq.getValue(spriteId, compare, r)) != 0? 1: 0;
			}}));

		map.put("=[)", new MathFunction("=[)", 30, 3, new Functionable() {
			public void reset() {}
			public Object getValue(String spriteId, Valueable... params) {
				Valueable compare = params[0];
				Valueable l = params[1];
				Valueable r = params[2];
				return getFloatValue(supEq.getValue(spriteId, compare, l)) != 0 && getFloatValue(inf.getValue(spriteId, compare, r)) != 0? 1: 0;
			}}));
		map.put("=[]", new MathFunction("=[]", 30, 3, new Functionable() {
			public void reset() {}
			public Object getValue(String spriteId, Valueable... params) {
				Valueable compare = params[0];
				Valueable l = params[1];
				Valueable r = params[2];
				return getFloatValue(supEq.getValue(spriteId, compare, l)) != 0 && getFloatValue(infEq.getValue(spriteId, compare, r)) != 0? 1: 0;
			}}));

		
		return map;
	}

	public static MathFunction getSpecialOp(String equalityOp) {
		return specialOpMap.get(equalityOp);
	}

	// ------------------------------------ //
	
	
	
	
	public static class Sum implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			Object l = params[0].getValue(spriteId);
			Object r = params[1].getValue(spriteId);
			
			if (((l instanceof String) && l != null) || ((r instanceof String) && r != null)) {
				return "" + l + r;
			}
			return getFloatValue(params[0].getValue(spriteId)) + getFloatValue(params[1].getValue(spriteId));
		}
		public void reset() {}
	}
	public static class Sub implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return getFloatValue(params[0].getValue(spriteId)) - getFloatValue(params[1].getValue(spriteId));
		}
		public void reset() {}
	}
	public static class Mul implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return getFloatValue(params[0].getValue(spriteId)) * getFloatValue(params[1].getValue(spriteId));
		}
		public void reset() {}
	}
	public static class Div implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return getFloatValue(params[0].getValue(spriteId)) / getFloatValue(params[1].getValue(spriteId));
		}
		public void reset() {}
	}
	public static class Mod implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return getFloatValue(params[0].getValue(spriteId)) % getFloatValue(params[1].getValue(spriteId));
		}
		public void reset() {}
	}
	public static class Exp implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return (Object) Math.pow(getFloatValue(params[0].getValue(spriteId)),getFloatValue(params[1].getValue(spriteId)));
		}
		public void reset() {}
	}
	public static class Not implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			float result = getFloatValue(params[0].getValue(spriteId));
			return result == 0? 1: 0;
		}
		public void reset() {}
	}
	public static class And implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {			
			return (getFloatValue(params[0].getValue(spriteId)) != 0) && (getFloatValue(params[1].getValue(spriteId)) != 0)? 1: 0;
		}
		public void reset() {}
	}
	public static class Or implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return (getFloatValue(params[0].getValue(spriteId)) != 0) || (getFloatValue(params[1].getValue(spriteId)) != 0)? 1: 0;
		}
		public void reset() {}
	}
	public static class Xor implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return (getFloatValue(params[0].getValue(spriteId)) != 0) ^ (getFloatValue(params[1].getValue(spriteId)) != 0)? 1: 0;
		}
		public void reset() {}
	}
	public static class BitNot implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return ~(int)(getFloatValue(params[0].getValue(spriteId)));
		}
		public void reset() {}
	}
	public static class BitAnd implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return (int)getFloatValue(params[0].getValue(spriteId)) & (int)getFloatValue(params[1].getValue(spriteId));
		}
		public void reset() {}
	}
	public static class BitOr implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return (int)getFloatValue(params[0].getValue(spriteId)) | (int)getFloatValue(params[1].getValue(spriteId));
		}
		public void reset() {}
	}
	public static class BitXor implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return (int)getFloatValue(params[0].getValue(spriteId)) ^ (int)getFloatValue(params[1].getValue(spriteId));
		}
		public void reset() {}
	}
	public static class Equals implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			Object l = params[0].getValue(spriteId);
			Object r = params[1].getValue(spriteId);
			
			if (((l instanceof String) && l != null) || ((r instanceof String) && r != null)) {
				return ("" + l).equals(r.toString())? 1: 0;
			}
			return getFloatValue(params[0].getValue(spriteId)) == getFloatValue(params[1].getValue(spriteId))? 1: 0;
		}
		public void reset() {}
	}
	public static class Inf implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return getFloatValue(params[0].getValue(spriteId)) < getFloatValue(params[1].getValue(spriteId))? 1: 0;
		}
		public void reset() {}
	}
	public static class Sup implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return getFloatValue(params[0].getValue(spriteId)) > getFloatValue(params[1].getValue(spriteId))? 1: 0;
		}
		public void reset() {}
	}
	public static class InfEq implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return getFloatValue(params[0].getValue(spriteId)) <= getFloatValue(params[1].getValue(spriteId))? 1: 0;
		}
		public void reset() {}
	}
	public static class SupEq implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return getFloatValue(params[0].getValue(spriteId)) >= getFloatValue(params[1].getValue(spriteId))? 1: 0;
		}
		public void reset() {}
	}
	public static class Affect implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			final Object r = params[0].getValue(spriteId);
			if (params[0] instanceof Setable) {
				Valueable v = null;
				v = new ObjectValueable(r);
				((Setable)params[0]).setValue(v);
				return params[0].getValue(spriteId);
			}
			throw new IllegalArgumentException("The first argument must be Setable");
		}
		public void reset() {}
	}
	public static class NotEquals implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			Object l = params[0].getValue(spriteId);
			Object r = params[1].getValue(spriteId);
		
			if (((l instanceof String) && l != null) || ((r instanceof String) && r != null)) {
				return !("" + l).equals(r)? 1: 0;
			}
			return getFloatValue(params[0].getValue(spriteId)) != getFloatValue(params[1].getValue(spriteId))? 1: 0;
		}
		public void reset() {}
	}
	public static class Minor implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return getFloatValue(params[0].getValue(spriteId)) < getFloatValue(params[1].getValue(spriteId))? 1: 0;
		}
		public void reset() {}
	}
	public static class MinorEquals implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return getFloatValue(params[0].getValue(spriteId)) <= getFloatValue(params[1].getValue(spriteId))? 1: 0;
		}
		public void reset() {}
	}
	public static class Superior implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return getFloatValue(params[0].getValue(spriteId)) > getFloatValue(params[1].getValue(spriteId))? 1: 0;
		}
		public void reset() {}
	}
	public static class SuperiorEquals implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			return getFloatValue(params[0].getValue(spriteId)) >= getFloatValue(params[1].getValue(spriteId))? 1: 0;
		}
		public void reset() {}
	}
}
