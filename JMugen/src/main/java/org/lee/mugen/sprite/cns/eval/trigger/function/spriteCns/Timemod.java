package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import java.util.List;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.IntValueable;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.MathFunction;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;
import org.lee.mugen.sprite.cns.eval.operator.CnsOperatorsDef;
import org.lee.mugen.sprite.parser.ExpressionFactory;
import org.lee.mugen.sprite.parser.Parser;

public class Timemod extends SpriteCnsTriggerFunction {

	public Timemod() {
		super("timemod", new String[] {});
	}
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		int pstatetime = (int) GameFight.getInstance().getSpriteInstance(spriteId).getSpriteState().getTimeInState();
		// 1er op
		// 2 diviseur
		// value to compare
		Valueable op = params[0];
		Valueable vdivider = params[1];
		Valueable vvalueToCompare = params[2];
		
		int divider = Parser.getIntValue(vdivider.getValue(spriteId));
//		int valueToCompare = Parser.getIntValue(vvalueToCompare.getValue(spriteId));
		
		final int rs = pstatetime % divider;
		Valueable vRs = new IntValueable(rs);
		return op.getValue(spriteId, new Valueable[] {vRs, vvalueToCompare});
	}

		
	
	@Override
	public int parseValue(String[] tokens, int pos, List<Valueable> result) {
		// TimeMod [oper] diviseur, valeur1

		pos = pos + 1;
		
		while (pos < tokens.length && !",".equals(tokens[pos]))
			pos++;
			
		boolean find = false;
		if (pos < tokens.length && tokens[pos].equals(","))
			find = true;
		if (find) {
			final MathFunction op = CnsOperatorsDef.getOperator(tokens[pos - 2]);
			
			if (op != null) {
				result.add(new Valueable() {
					public Object getValue(String spriteId, Valueable... params) {
						return op.getValue(spriteId, params);
					}});
			} else {
				throw new IllegalArgumentException();
			}
			String[] subStrFirst = new String[] {tokens[pos - 1]}; // =
			Valueable[] vsFirst = ExpressionFactory.evalExpression(subStrFirst);
			if (vsFirst.length > 0)
				result.add(vsFirst[0]);
			else
				throw new IllegalArgumentException();

			String[] subStrSecond = new String[] {tokens[pos + 1]}; // =
			Valueable[] vsSecond = ExpressionFactory.evalExpression(subStrSecond);
			if (vsSecond.length > 0)
				result.add(vsSecond[0]);
			else
				throw new IllegalArgumentException();

			return pos + 1;
		}
		throw new IllegalArgumentException();
	}


}
