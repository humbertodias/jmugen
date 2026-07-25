package org.lee.mugen.sprite.cns.type.function;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Functionable;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteState;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.parser.ExpressionFactory;
import org.lee.mugen.sprite.parser.Parser;
import org.lee.mugen.util.Logger;
/**
 * 
 * @author Dr Wong
 * @category Cns Function : Complete
 */
public class Varadd extends StateCtrlFunction {

	public Varadd() {
		super("varadd", new String[] {"v", "fv", "value"});
	}

	
	private List<Functionable> listOfValSet = new ArrayList<Functionable>();
	private List<Functionable> listOfValFSet = new ArrayList<Functionable>();


	
	@Override
    public boolean containsParam(String param) {
    	return super.containsParam(param) || Varset.isMatch(Varset.PREG_IS_VAR_PARAM, param);
    }
	
	public void addParam(String name, Valueable[] param) {
		if (Varset.isMatch(Varset.P_SYSVAR_F_REG, name)) {
			Matcher m = Varset.P_SYSVAR_F_REG.matcher(name);
			m.find();
			final String value = m.group(1);
			String[] tokens = ExpressionFactory.expression2Tokens(value);
			final Valueable[] valueables = param;//ExpressionFactory.evalExpression(tokens, getSpriteId());
			
			Functionable functionable = new Functionable() {
				public void reset() {}

				public Object getValue(String spriteId, Valueable... params) {
					Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
					final SpriteState spriteState = sprite.getSpriteState();
					
					spriteState.getVars().addSysFVar(value, valueables[0]);
					return null;
				}};
			listOfValFSet.add(functionable);
			return;
		} else if (Varset.isMatch(Varset.P_SYSVAR_REG, name)) {
			Matcher m = Varset.P_SYSVAR_REG.matcher(name);
			m.find();
			final String value = m.group(1);
			String[] tokens = ExpressionFactory.expression2Tokens(value);
			final Valueable[] valueables = param;//ExpressionFactory.evalExpression(tokens, getSpriteId());
			
			Functionable functionable = new Functionable() {
				public void reset() {}

				public Object getValue(String spriteId, Valueable... params) {
					Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
					final SpriteState spriteState = sprite.getSpriteState();
					
					spriteState.getVars().addSysVar(value, valueables[0]);
					return null;
				}};
			listOfValSet.add(functionable);
			return;
		} else if (Varset.isMatch(Varset.P_VAR_F_REG, name)) {
			Matcher m = Varset.P_VAR_F_REG.matcher(name);
			m.find();
			final String value = m.group(1);
			String[] tokens = ExpressionFactory.expression2Tokens(value);
			final Valueable[] valueables = param;//ExpressionFactory.evalExpression(tokens, getSpriteId());
			
			Functionable functionable = new Functionable() {
				public void reset() {}

				public Object getValue(String spriteId, Valueable... params) {
					Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
					final SpriteState spriteState = sprite.getSpriteState();					
					spriteState.getVars().addVarFloat(value, valueables[0]);
					return null;
				}};
			listOfValFSet.add(functionable);
			return;
		} else if (Varset.isMatch(Varset.P_VAR_REG, name)) {
			Matcher m = Varset.P_VAR_REG.matcher(name);
			m.find();
			final String value = m.group(1);
			String[] tokens = ExpressionFactory.expression2Tokens(value);
			final Valueable[] valueables = param;//ExpressionFactory.evalExpression(tokens, getSpriteId());
			
			Functionable functionable = new Functionable() {
				public void reset() {}

				public Object getValue(String spriteId, Valueable... params) {
					Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
					final SpriteState spriteState = sprite.getSpriteState();
					
					spriteState.getVars().addVar(value, valueables[0]);
					return null;
				}};
			listOfValFSet.add(functionable);
			return;
		}

		int index = getParamIndex(name);
		if (index == -1) {
			Logger.log("This line can't be compile in Varset >> " + name);
			return;
		}
			
		valueableParams[index] = param;

	}
	
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		boolean exec = false;
		for (Functionable f: listOfValSet) {
			f.getValue(spriteId);
			exec = true;
		}
		for (Functionable f: listOfValFSet) {
			f.getValue(spriteId);
			exec = true;
		}
		
		if (exec)
			return null;
		int vIndex = getParamIndex("v");
		int vfIndex = getParamIndex("fv");
		int valueIndex = getParamIndex("value");
		
		Valueable v = vIndex == -1? null: valueableParams[vIndex] == null? null: valueableParams[vIndex][0];
		Valueable vf = vfIndex == -1? null: valueableParams[vfIndex] == null? null: valueableParams[vfIndex][0];
		Valueable value = valueIndex == -1? null: valueableParams[valueIndex] == null? null: valueableParams[valueIndex][0];
		
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		
		final SpriteState spriteState = sprite.getSpriteState();					
		
		
		if (v != null) {
			final int intValue = Parser.getIntValue(value.getValue(spriteId));
			int intV = Parser.getIntValue(v.getValue(spriteId));

			spriteState.getVars().addVar(String.valueOf(intV), new Valueable() {

				public Object getValue(String spriteId, Valueable... params) {
					return intValue;
				}});
		} else if (vf != null) {
			final float floatValue = Parser.getFloatValue(value.getValue(spriteId));
			int intV = Parser.getIntValue(vf.getValue(spriteId));
			spriteState.getVars().addVarFloat(String.valueOf(intV), new Valueable() {

				public Object getValue(String spriteId, Valueable... params) {
					return floatValue;
				}});
			
		} else {
			throw new IllegalStateException("This state musn't be reached : Varset none of v or vf");
		}
		
		return null;
	}
}
