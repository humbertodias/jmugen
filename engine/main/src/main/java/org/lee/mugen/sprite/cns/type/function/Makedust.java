package org.lee.mugen.sprite.cns.type.function;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.entity.MakeDustSpriteManager;
import org.lee.mugen.sprite.entity.PointF;
import org.lee.mugen.sprite.parser.Parser;
import org.lee.mugen.util.Logger;

public class Makedust extends StateCtrlFunction {

	public Makedust() {
		super("makedust", new String[] {"pos", "pos2", "spacing"});
	}
	
	@Override
	public Object getValue(String spriteId, Valueable...params) {
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		
		final boolean isFlip = sprite.isFlip();
		float x = sprite.getInfo().getXPos();
		float y = sprite.getInfo().getYPos();

		int spacing = 1;
		
		int indexSpacing = getParamIndex("spacing");
		if (indexSpacing != -1) {
			Valueable[] vals = valueableParams[indexSpacing];
			spacing = Parser.getIntValue(vals[0].getValue(spriteId));
		}
		
		List<PointF> pts = new ArrayList<PointF>();
		int indexPos = getParamIndex("pos");
		if (indexPos != -1) {
			Valueable[] vals = valueableParams[indexPos];
			if (vals == null) {
				pts.add(new PointF(0, 0));
			} else {
				pts.add(new PointF(Parser.getFloatValue(vals[0].getValue(spriteId)), 
						Parser.getFloatValue(vals[1].getValue(spriteId))));
			}


			List<String> names = new ArrayList<String>();
			names.addAll(getParamNames());
			Collections.sort(names);
			
			for (String name: names) {
				if (name.equals("pos"))
					continue;
				if (name.startsWith("pos")) {
					indexPos = getParamIndex(name);
					
					if (indexPos != -1 && valueableParams[indexPos] != null) {
						vals = valueableParams[indexPos];
						if (vals == null) {
							pts.add(new PointF(0, 0));
						} else { 
							pts.add(new PointF(Parser.getFloatValue(vals[0].getValue(spriteId)), 
									Parser.getFloatValue(vals[1].getValue(spriteId))));
						}
					}
				}
				
			}
			if (MAKEDUSTMAP.containsKey(spriteId)) {
				MakeDustSpriteManager md = MAKEDUSTMAP.get(spriteId);
				int restOfSpacing = md.getSpacing();
				spacing += restOfSpacing + 1;

				if (md.remove()) {
					MAKEDUSTMAP.remove(spriteId);
				}
			} else {
				spacing = 1;
			}
			MakeDustSpriteManager manager = new MakeDustSpriteManager(sprite, spacing, pts.toArray(new PointF[pts.size()]));
			
			GameFight.getInstance().addMakedustSpriteManager(manager);
			MAKEDUSTMAP.put(spriteId, manager);

		} else{
			throw new IllegalArgumentException("almost one position must be define in Makedust");
		}
		return null;
	};
	
	private static Map<String, MakeDustSpriteManager> MAKEDUSTMAP = new HashMap<String, MakeDustSpriteManager>();
	
	public void addParam(String name, Valueable[] param) {
		int index = getParamIndex(name);
		if (index == -1) {
			Logger.log("add more param " + name);
			int len = paramNameIndexMap.size();
			paramNameIndexMap.put(name, len);
			Valueable[][] newTable = new Valueable[valueableParams.length + 1][];
			System.arraycopy(valueableParams, 0, newTable, 0, valueableParams.length);
			valueableParams = newTable;
		}
		valueableParams[index] = param;
	}


}
