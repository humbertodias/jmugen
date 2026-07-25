package org.lee.mugen.background;

import org.lee.mugen.geom.MugenPoint;
import java.io.Serializable;
import java.util.HashMap;

import org.lee.mugen.object.Rectangle;
import org.lee.mugen.renderer.Trans;
import org.lee.mugen.sprite.base.AbstractAnimManager;
import org.lee.mugen.sprite.character.AnimGroup;
import org.lee.mugen.sprite.entity.PointF;
import org.lee.mugen.sprite.entity.SprGrpNum;

public class BG implements Cloneable, Serializable {
	// type = ? "Normal"/"Parallax" (def: "normal")
	public static enum Type {
		ANIM, NORM, NORMAL, PARALLAX;
	}
	private int order = -1;
	private int actionno = 0;


	
	private String debugbg;
	private boolean enable = true;
	private boolean visible = true;
	private Sin sin = new Sin();
	
	public Sin getSin() {
		return sin;
	}

	public class Sin implements Serializable {

		public void setX(MugenPoint p) {
			sinXAmp = p.x;
			sinXTime = p.y;
		}
		public void setY(MugenPoint p) {
			sinYAmp = p.x;
			sinYTime = p.y;
		}
		
	}
	
	private int sinYAmp = 0;
	private int sinYTime = 0;
	private float sinYTimeLine = 0;
	
	private int sinXAmp = 0;
	private int sinXTime = 0;
	private int sinXTimeLine = 0;

	private Integer id;
	// This is the layer number, which determines where the sprite is drawn to.
	// Valid values are 0 and 1.
	// 0 for background (behind characters), 1 for foreground (in front)
	// If this line is omitted, the default value of 0 will be assumed.
	private int layerno = 0;
	// mask = ? Masking (int): 0 - off, 1 - on (def: 0)
	private int mask = 0;
	private String name = "";
	// alpha
	private int alpha1 = 256;
	private int alpha2 = 256;
	private Object parent = null;

	// spriteno = ?, ? Sprite group and number: groupno, imgno (req'd)
	private SprGrpNum spriteno = new SprGrpNum();
	// width = ?, ? Top width, bottom width (int) (use either this or above, but
	// not both)
	private int topWidth = 0;

	// Parallax-only:
	// xscale = ?, ? Top xscale, bottom xscale (float)
	private PointF xscale = new PointF(1f, 1f);
	// trans = ? Transparency settings: "none"/"add"/"add1"/"sub"" (def: "none")
	private Trans trans = Trans.NONE;
	private Type type = Type.NORMAL;
	private org.lee.mugen.object.Rectangle window;
	// start = ?, ? Starting location (integer) (def: 0, 0)
	private PointF start = new PointF();
	// tile = ?, ? Tiling: xtile, ytile (int): 0 - off, 1 - infinite,
	// >1 - tile that number of times (def: 0, 0) - only for Normal BG
	private PointF tile = new PointF();

	// tilespacing = ?, ? Tiling: x, y (int) : space between tiles (def: 0, 0)
	private PointF tilespacing;

	// velocity = ?, ? Velocity: x, y (float): speed background moves (def: 0,
	// 0)
	private PointF velocity = new PointF();
	// Similar to the delta parameter, this one affects the movement of
	// the window. Defaults to 0,0
	private PointF windowdelta = new PointF();
	// delta = ?, ? Change in location per camera unit moved (float) (def: 1,1)
	private PointF delta = new PointF(1, 1);
	// yscaledelta = ? Change in y-scale per unit (float, in percent) (def: 0)
	private float yscaledelta;
	// yscalestart = ? Starting y-scale (float, in percent) (def: 100)
	private float yscalestart;

	private MugenPoint width = new MugenPoint();

	
	private PointF pos = new PointF();

	public void init() {
		pos = new PointF(start);
		getAnimManager().setAction(actionno);
	}
	
	public PointF getPos() {
		return pos;
	}

	public void setPos(PointF pos) {
		this.pos = pos;
	}

	public BG(Object root) {
		parent = root;
	}

	public int getActionno() {
		return actionno;
	}

	public Trans getBgTrans() {
		return trans;
	}

	public Type getBgType() {
		return type;
	}

	public Rectangle getBgWindow() {
		return window;
	}


	public String getDebugbg() {
		return debugbg;
	}
	int time = 0;
	float yMul = 1;
	public boolean isInit() {
		return isInit;
	}

	public void setInit(boolean isInit) {
		this.isInit = isInit;
	}
	float xMul = 1;
	boolean isInit = false;
	public void process() {

		if (!enable)
			return;
		
		float x = getVelocity().getX();
				
		if (sinXTime != 0) {
			xMul = (xMul>0? 1f: -1f) / (sinXTime/4f);
			x += (sinYAmp * Math.sin(sinXTimeLine)) * xMul;
			sinXTimeLine += yMul;
			if (sinXTimeLine <= 0 || sinXTimeLine >= sinXTime)
				xMul = -xMul;
		}
		getPos().addX(x);
		
		float y = getVelocity().getY();
		if (sinYTime != 0) {
			yMul = (yMul>0? 1f: -1f) / (sinYTime/4f);
			y += (sinYAmp * Math.sin(sinYTimeLine)) * yMul;
			sinYTimeLine += yMul;
			if (sinYTimeLine <= 0 || sinYTimeLine >= sinYTime)
				yMul = -yMul;
		}


		getPos().addY(y);
		if (type == BG.Type.ANIM)
			getAnimManager().process();
		time++;
	}
	
	
	// This defines the drawing space, or "window" of the background. It's
	// given in the form
	// x1,y1, x2,y2
	// where (x1,y1)-(x2,y2) define a rectangular box.
	// Make the window smaller if you only want to draw part of the background.
	// You normally do not have to change this setting. Value values range from
	// 0-319 for x, and 0-239 for y. The values are 0,0, 319,239 by default
	// (full
	// screen).

	public Integer getId() {
		return id;
	}

	public int getLayerno() {
		return layerno;
	}

	public int getMask() {
		return mask;
	}

	public String getName() {
		return name;
	}



	public SprGrpNum getSpriteno() {
		return spriteno;
	}

	public int getTopWidth() {
		return topWidth;
	}


	// ////////////////////////////////////////////////////////////

	public float getYscaledelta() {
		return yscaledelta;
	}

	public float getYscalestart() {
		return yscalestart;
	}

	public void setActionno(int actionno) {
		this.actionno = actionno;
//		animManager.setAction(actionno);
	}
	public void setAlpha(int[] alpha) {
		alpha1 = alpha[0];
		if (alpha.length > 1)
			alpha2 = alpha[1];
		
	}

	public float getAlphaOne() {
		return (float)alpha1/256f;
	}
	public int[] getAlpha() {
		return new int[] {alpha1, alpha2};
	}

	public void setDebugbg(String debugbg) {
		this.debugbg = debugbg;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLayerno(int layerno) {
		this.layerno = layerno;
	}

	public void setMask(int mask) {
		this.mask = mask;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSpriteno(SprGrpNum spriteno) {
		this.spriteno = spriteno;
	}

	public void setTopWidth(int topWidth) {
		this.topWidth = topWidth;
	}


	public void setTrans(String trans) {
		this.trans = Trans.valueOf(trans.toUpperCase());
		;
	}

	public void setType(String type) {
		this.type = Type.valueOf(type.toUpperCase());
	}

	public void setWindow(Rectangle r) {
		window = r.copy();
	}


	public void setYscaledelta(float yscaledelta) {
		this.yscaledelta = yscaledelta;
	}

	public void setYscalestart(float yscalestart) {
		this.yscalestart = yscalestart;
	}

	public Trans getTrans() {
		return trans;
	}

	public void setTrans(Trans trans) {
		this.trans = trans;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public PointF getStart() {
		return start;
	}

	public void setStart(PointF start) {
		this.start = start;
	}

	public PointF getTile() {
		return tile;
	}

	public void setTile(PointF tile) {
		this.tile = tile;
	}

	public PointF getTilespacing() {
		return tilespacing;
	}

	public void setTilespacing(PointF tilespacing) {
		this.tilespacing = tilespacing;
		if (!tilespacing.isYSetted()) {
			this.tilespacing.setY(this.tilespacing.getX());
		}
	}

	public PointF getVelocity() {
		return velocity;
	}

	public void setVelocity(PointF velocity) {
		this.velocity = velocity;
	}

	public PointF getWindowdelta() {
		return windowdelta;
	}

	public void setWindowdelta(PointF windowdelta) {
		this.windowdelta = windowdelta;
	}

	public PointF getDelta() {
		return delta;
	}

	public void setDelta(PointF delta) {
		this.delta = delta;
	}

	public org.lee.mugen.object.Rectangle getWindow() {
		return window;
	}

	private AbstractAnimManager anim;

	public PointF getXscale() {
		return xscale;
	}

	public void setXscale(PointF xscale) {
		this.xscale = xscale;
	}

	public MugenPoint getWidth() {
		return width;
	}

	public void setWidth(MugenPoint width) {
		this.width = width;
	}

	public AbstractAnimManager getAnimManager() {
		if (anim == null) {
			// Parent Background owns the shared AIR map; an empty map makes setAction NPE.
			if (parent instanceof Background) {
				anim = ((Background) parent).getAnim();
			}
			if (anim == null) {
				anim = new AbstractAnimManager(new HashMap<Integer, AnimGroup>());
			}
			anim.setAction(actionno);
		}
		return anim;
	}

	public void rezet() {
		getPos().setX(start.getX());
		getPos().setY(start.getY());
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public int getSinYAmp() {
		return sinYAmp;
	}

	public void setSinYAmp(int sinAmp) {
		this.sinYAmp = sinAmp;
	}

	public int getSinYTime() {
		return sinYTime;
	}

	public void setSinYTime(int sinTime) {
		this.sinYTime = sinTime;
	}

	public int getSinXAmp() {
		return sinXAmp;
	}

	public void setSinXAmp(int sinXAmp) {
		this.sinXAmp = sinXAmp;
	}

	public int getSinXTime() {
		return sinXTime;
	}

	public void setSinXTime(int sinXTime) {
		this.sinXTime = sinXTime;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
