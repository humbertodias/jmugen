package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import java.util.List;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.lang.Wrap;
import org.lee.mugen.lang.Wrapper;
import org.lee.mugen.parser.type.IntValueable;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.eval.function.MathFunction;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;
import org.lee.mugen.sprite.cns.eval.operator.CnsOperatorsDef;
import org.lee.mugen.sprite.parser.ExpressionFactory;
import org.lee.mugen.sprite.parser.Parser;

/**
 * 
 * @author Dr Wong
 * @category Trigger : Complete
 */
public class Animelem extends SpriteCnsTriggerFunction {
	
	public Animelem() {
		super("animelem", new String[] {"value1", "oper", "value2"});
	}


// AnimElem = value1, [oper] value2
//	  [oper]	    =, !=, <, >, <=, >=
	

	@Override
	public Object getValue(String spriteId, Valueable... params) {
		final int tick = Parser.getIntValue(params[3].getValue(spriteId));

		final int grp = Parser.getIntValue(params[1].getValue(spriteId));
		Valueable opSecond = params[2];
		int res = 0;
		
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		final int[] grpImg = tick < 0? sprite.getSprAnimMng().getAnimElemNoNegative(tick): sprite.getSprAnimMng().getAnimElemNoImgCount();


		
		if (grp == grpImg[0]) {
			res = Parser.getIntValue(opSecond.getValue(spriteId, new Valueable[] {
					new IntValueable(grpImg[1]), 
					new IntValueable(tick)}));
			
			return res == 0? 0: 1;
		}
		
		{
			res = Parser.getIntValue(opSecond.getValue(spriteId, new Valueable[] {
					new IntValueable(grpImg[0])
					,new IntValueable(grp)
				}));
			return res == 0? 0: 1;
			
		}
	}
	
	private boolean isOpEq(String op) {
		return "=".equals(op) || "<".equals(op) || "<=".equals(op) || ">".equals(op) || ">=".equals(op);
	}
	
	
	
	
	@Override
	public int parseValue(String[] tokens, int pos, List<Valueable> result) {
		return parseValueOld(tokens, pos, result);
//		return parseValueNew(tokens, pos, result);
	}	
	public int parseValueNew(String[] tokens, int pos, List<Valueable> result) {
		Wrap<MathFunction> firstOp = new Wrapper<MathFunction>();
		Wrap<String[]> key = new Wrapper<String[]>();;
		Wrap<Valueable> value1 = new Wrapper<Valueable>();;
		Wrap<MathFunction> compareOp = new Wrapper<MathFunction>();;
		Wrap<Valueable> value2 = new Wrapper<Valueable>();
		
		int position = Parser.getValueForSpecialOpAndReturnPos(tokens, pos, key, firstOp, value1, compareOp, value2);
		
		result.add(firstOp.getValue());
		result.add(value1.getValue());
		if (compareOp.getValue() != null) {
			result.add(compareOp.getValue());
		} else {
			result.add(CnsOperatorsDef.getOperator("="));
		}
		if (value2.getValue() != null) {
			result.add(value2.getValue());
		} else {
			result.add(new Valueable() {

				public Object getValue(String spriteId, Valueable... params) {
					return 0;
				}});
		}
		
		
		return position;
		
	}
	
	public int parseValueOld(String[] tokens, int pos, List<Valueable> result) {
		int posVirgule = pos + 1;
		
//		while (posVirgule < tokens.length && !",".equals(tokens[posVirgule]))
//			posVirgule++;
		if (tokens[pos + 2].equals("(")) {
			posVirgule += ExpressionFactory.getCloseInTokens(tokens, pos + 2, "(", ")").length;
		}
		posVirgule = pos + 3;
		boolean find = false;
		if (posVirgule < tokens.length && tokens[posVirgule].equals(","))
			find = true;

		// If we find an op i think it'll be be like with a ,
		if (!find && tokens.length > pos + 2 + 1 && isOpEq(tokens[pos + 2 + 1])) {
			String[] tokens2 = new String[tokens.length + 1];
			int j = 0;
			for (int i = 0; i < tokens2.length; i++) {
				tokens2[i] = tokens[j];
				if (i == pos + 2 + 1) {
					tokens2[i] = ",";
				} else {
					j++;
				}
			}
			return parseValue(tokens2, pos, result) - 1;
		}
		
		if (find) {
			final MathFunction opFirst = CnsOperatorsDef.getOperator(tokens[pos + 1]);
			
			if (opFirst != null) {
				result.add(new Valueable() {
					public Object getValue(String spriteId, Valueable... params) {
						return opFirst.getValue(spriteId, params);
					}});
			} else {
				throw new IllegalArgumentException();
			}
			String[] subStrFrist = new String[] {tokens[pos + 2]}; // =
			Valueable[] vsFrist = ExpressionFactory.evalExpression(subStrFrist);
			if (vsFrist.length > 0)
				result.add(vsFrist[0]);
			else
				throw new IllegalArgumentException();
			
			final MathFunction op = CnsOperatorsDef.getOperator(tokens[posVirgule + 1]);
			int adder = 0;
			if (op != null) {
				result.add(new Valueable() {
					public Object getValue(String spriteId, Valueable... params) {
						return op.getValue(spriteId, params);
					}});
			} else {
				final MathFunction opDefault = CnsOperatorsDef.getOperator("=");
				result.add(new Valueable() {
					public Object getValue(String spriteId, Valueable... params) {
						return opDefault.getValue(spriteId, params);
					}});
				
				adder--;
			}
			
//			String[] subToken = new String[tokens.length - (posVirgule + 1 + 1 + adder)]; // =
			int len = 1;
			if (tokens[posVirgule + 2 + adder].equals("-") || tokens[posVirgule + 2 + adder].equals("+"))
				len++;
			String[] subToken = new String[len];
			System.arraycopy(tokens, posVirgule + 2 + adder, subToken, 0, len);
			Valueable[] valueables = ExpressionFactory.evalExpression(subToken);


			if (valueables.length > 0)
				result.add(valueables[0]);
			else
				throw new IllegalArgumentException();
			
			return pos + 4 + len;
			
		} else {
			final MathFunction op = CnsOperatorsDef.getOperator(tokens[pos + 1]);
			
			if (op != null) {
				result.add(new Valueable() {
					public Object getValue(String spriteId, Valueable... params) {
						return op.getValue(spriteId, params);
					}});
			}
			String[] subStr = new String[] {tokens[pos + 2]}; // =
			
			Valueable[] vs = ExpressionFactory.evalExpression(subStr);
			if (vs.length > 0)
				result.add(vs[0]);
			else
				throw new IllegalArgumentException();
			final MathFunction opDefault = CnsOperatorsDef.getOperator("=");
			result.add(new Valueable() {
				public Object getValue(String spriteId, Valueable... params) {
					return opDefault.getValue(spriteId, params);
				}});
			result.add(new Valueable() {
				public Object getValue(String spriteId, Valueable... params) {
					return 0;
				}});

			return pos + 2;
		}
		
	}
}
