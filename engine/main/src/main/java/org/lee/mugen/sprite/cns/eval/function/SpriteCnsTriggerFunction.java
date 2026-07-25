package org.lee.mugen.sprite.cns.eval.function;

import java.util.Arrays;
import java.util.List;

import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.AbstractCnsFunction;
import org.lee.mugen.sprite.parser.ExpressionFactory;

public class SpriteCnsTriggerFunction extends AbstractCnsFunction {

	protected SpriteCnsTriggerFunction(String functionName, String[] paramNames) {
		super(functionName, paramNames);
	}
	@Override
	public Valueable[] parseValue(String name, String value) {
		return null;
	}
	
	public int parseValue(String[] tokens, int pos, List<Valueable> result) {
		String[] subTokens = {};
		pos++;
		if (pos < tokens.length && tokens[pos].equals("(")) {
			subTokens = ExpressionFactory.getCloseInTokens(tokens, pos, "(", ")");
		}
		 
		final Valueable[] valueables;
		
		if (subTokens.length != 0) {
			pos += subTokens.length + 1;
			valueables = ExpressionFactory.evalExpression(subTokens);
		} else {
			valueables = new Valueable[0];
			pos--;
		}
		result.addAll(Arrays.asList(valueables));
		return pos;
	}

	public String getRegex() {
		return "\\b(" + getFunctionName() + ")\\b";
	}
	public void reset() {};
}