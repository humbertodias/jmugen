package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.lee.mugen.core.FightEngine;
import org.lee.mugen.core.GameFight;
import org.lee.mugen.lang.Wrap;
import org.lee.mugen.lang.Wrapper;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.MathFunction;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;
import org.lee.mugen.sprite.cns.eval.operator.CnsOperatorsDef;
import org.lee.mugen.sprite.entity.ProjectileSub;
import org.lee.mugen.sprite.parser.Parser;

public class Projguarded extends SpriteCnsTriggerFunction {

	public Projguarded() {
		super("projguarded", new String[] {});
	}

	@Override
	public Object getValue(String spriteId, Valueable...params) {		
		return params[0].getValue(spriteId);
	};
	/*
	Projhit[ID] = valeur
	Projhit[ID] = valeur, [oper] valeur2
	 */
	public static Pattern PROJ_GUARDED_SPEC_REG = Pattern.compile(
					"(" + "projguarded *\\((\\d+)\\)" + ")" +
					"|" +
					"\\b(" + "projguarded(\\d*)" + ")\\b" , Pattern.CASE_INSENSITIVE);
	@Override
	public int parseValue(String[] tokens, int pos, List<Valueable> result) {
		final Wrap<MathFunction> firstOp = new Wrapper<MathFunction>();
		final Wrap<String[]> key = new Wrapper<String[]>();;
		final Wrap<Valueable> value1 = new Wrapper<Valueable>();;
		final Wrap<MathFunction> compareOp = new Wrapper<MathFunction>();;
		final Wrap<Valueable> value2 = new Wrapper<Valueable>();
		
		int position = Parser.getValueForSpecialOpAndReturnPos(tokens, pos, key, firstOp, value1, compareOp, value2);
		
		String keyStr = key.getValue()[0];
		// TODO IF projhit => projhit(id)
		Matcher m = PROJ_GUARDED_SPEC_REG.matcher(keyStr);
		final Integer projid;
		String grp = null;
		if (m.find()) {
			if (m.group(2) != null) {
				grp = m.group(2);
			}
			if (m.group(4) != null) {
				grp = m.group(4);
			}
		}
			
		if (grp != null && grp.length() > 0) {
			projid = Integer.parseInt(grp);
		} else {
			projid = null;
		}

		Valueable value = new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				List<ProjectileSub> projectiles;
				FightEngine fightEngine = GameFight.getInstance().getFightEngine();

				projectiles = fightEngine.getProjectiles(projid);
		
				int projCount = projectiles.size();
				int projDefineCount = Parser.getIntValue(value1.getValue().getValue(spriteId));
//				if (projDefineCount == 0 && projDefineCount == projCount) {
//					
//				} else 
				if (projDefineCount == projCount) {
					long delay = 1;
					MathFunction func = null;
					if (value2.getValue() != null) {
						delay = Parser.getIntValue(value2.getValue().getValue(spriteId));
						func = compareOp.getValue();
					} else {
						func = CnsOperatorsDef.getOperator("=");
					}
					long currentTime = GameFight.getInstance().getGameState().getGameTime();
					for (ProjectileSub projectile: projectiles) {
						if (projectile.getLastTimeBlockBySomething() == -1)
							continue;
						long delta = currentTime - projectile.getLastTimeBlockBySomething();
						int res = Parser.getIntValue(func.getFunctionResult(spriteId, delay, delta));
						if (res != 0) {
							return 1;
						}
					}
				}
				return 0;
			}
			
		};
		
		result.add(value);
		return position;
		
	
	
	}
	
	@Override
	public String getRegex() {
		return PROJ_GUARDED_SPEC_REG.pattern();
//		return "\\b(" + getFunctionName() + "[0-9]*" + ")\\b";
	}
}
