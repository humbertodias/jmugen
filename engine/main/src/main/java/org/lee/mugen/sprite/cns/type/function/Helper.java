package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteCns;
import org.lee.mugen.sprite.character.SpriteHelper;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.entity.HelperSub;
import org.lee.mugen.sprite.parser.ExpressionFactory;

public class Helper extends StateCtrlFunction {

    // TODO : helper
    public Helper() {
        super("helper", new String[] {
        		"helpertype","name"
        		,"id","pos"
        		,"postype","facing"
        		,"stateno","keyctrl"
        		,"ownpal","supermovetime"
        		,"pausemovetime","size.xscale"
        		,"size.yscale","size.ground.back"
        		,"size.ground.front","size.air.back"
        		,"size.air.front","size.height"
        		,"size.proj.doscale","size.head.pos"
        		,"size.mid.pos","size.shadowoffset", "bindtime"      		
        });
    }

    public void fillBeanSize(String spriteId, SpriteCns beanSub) {
		String error = null;
			for (String paramName : getParamNames()) {
				try {
					error = paramName;
					if (paramName.startsWith("size")) {
						fillBeanChild(spriteId, paramName, beanSub);
					}
				} catch (Exception e) {
					System.err.println(beanSub.getClass().getName() + ">>>>>>>>>" + error);
					e.printStackTrace();
	//				throw new IllegalStateException("This state musn't be reached !!  " + getClass());
				}
			}
	}

    @Override
    public Object getValue(String spriteId, Valueable... params) {
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		HelperSub helperSub = new HelperSub();
		fillBean(spriteId, helperSub);
		helperSub.setSpriteFrom(sprite);
		

		
		
		final SpriteHelper helperSpr = new SpriteHelper(spriteId + "'s helper " + helperSub.hashCode(), sprite, helperSub);

		fillBeanSize(spriteId, helperSpr.getInfo());
//		Logger.log("Create helper " + helperSub.getId());
		
		helperSpr.initSpriteHelper();
		GameFight.getInstance().addSpriteHelper(helperSpr);
		
		
//		CnsRender cnsRender = new CnsRender(helperSpr) {
//		@Override
//		public boolean remove() {
//			return helperSpr.remove();
//		}
//	};
//	cnsRender.setShowAttackCns(true);
//	StateMachine.getInstance().addRender(cnsRender);
		
		
		helperSpr.getSpriteState().changeStateDef(helperSub.getStateno());

		
		return null;
	}
    @Override
    public Valueable[] parseValue(String name, String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

}
