package org.lee.mugen.sprite.character;

import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.lang.WrapInt;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitBySub;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub;
import org.lee.mugen.sprite.character.spiteCnsSubClass.NotHitBySub;
import org.lee.mugen.sprite.character.spiteCnsSubClass.VelSetSub;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub.AttrClass;
import org.lee.mugen.sprite.character.spiteCnsSubClass.constante.Data;
import org.lee.mugen.sprite.character.spiteCnsSubClass.constante.Movement;
import org.lee.mugen.sprite.character.spiteCnsSubClass.constante.Size;
import org.lee.mugen.sprite.character.spiteCnsSubClass.constante.Velocity;
import org.lee.mugen.sprite.character.spiteCnsSubClass.constante.Movement.AirJump;
import org.lee.mugen.sprite.character.spiteCnsSubClass.constante.Size.Ground;
import org.lee.mugen.sprite.cns.type.function.Playsnd;
import org.lee.mugen.sprite.entity.BindToSub;
import org.lee.mugen.sprite.entity.HitOverrideSub;
import org.lee.mugen.sprite.entity.PointF;
import org.lee.mugen.sprite.entity.ScreenboundSub;
import org.lee.mugen.sprite.entity.Shake;
import org.lee.mugen.sprite.parser.Parser;
import org.lee.mugen.sprite.parser.Parser.GroupText;

public class SpriteCns implements Cloneable, Serializable {
	private List<GroupText> groups = new LinkedList<GroupText>();
	
	public List<GroupText> getGroups() {
		return groups;
	}

	public static enum Type {
		S("STAND", 1), C("CROUCH", 2), I("IN_THE_AIR", 4), L("LYING_DOUWN", 8), A(
				"IN_THE_AIR", 4), U("UNCHANGED", 32);
		private String description;

		private int bit;

		Type(String desc, int bit) {
			description = desc;
			this.bit = bit;
		}

		public static final String ACCESS = "type";

		public int getBit() {
			return bit;
		}

		public void setBit(int bit) {
			this.bit = bit;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public static Type getType(int type) {
			for (Type t: Type.values()) {
				if (type == t.getBit())
					return t;
			}
			return null;
		}
	}

	public static enum MoveType {
		A("ATTACK", 1), I("IDLE", 2), H("HIT_BY", 4), U("UNCHANGED", 8);
		public static final String ACCESS = "movetype";

		private String description;

		private int bit;

		MoveType(String desc, int bit) {
			description = desc;
			this.bit = bit;
		}

		public int getBit() {
			return bit;
		}

		public void setBit(int bit) {
			this.bit = bit;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
	}

	public enum Physics {
		S("STAND", 1), C("CROUCH", 2), A("AIR", 4), N("NONE", 8), U(
				"UNCHANGED", 16);
		public static final String ACCESS = "physics";

		private String description;

		private int bit;

		Physics(String desc, int bit) {
			description = desc;
			this.bit = bit;
		}

		public int getBit() {
			return bit;
		}

		public void setBit(int bit) {
			this.bit = bit;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
	}



	

	/*------------------------------*/
	// CONST
	private Data data = new Data();

	private Size size = new Size();

	private Movement movement = new Movement();

	private Velocity velocity = new Velocity();



	public void reset() {
	}
	
	public void init() {
		life = getData().getLife();
		power = getData().getPower();
		ctrl = 1;
		getSprite().getSpriteState().selfstate(190);
		hitoverrides.clear();
		
	}

	// CNS
	private Type type = Type.S;

	private MoveType movetype = MoveType.I;

	private Physics physics = Physics.S;

	int life;

	int attack = 100;

	int power = 1000;

	int defence = 100;

	int fallDefenceUp = 50;

	int liedownTime = -1;

	int airjuggle = 15;

	int sparkno = 2;

	int guardSparkno = 40;

	int koEcho = 0;

	int volume = 0;

	int intPersistIndex = 60;

	float floatPersistIndex = 40f;

	int posfreeze = 0;

	Ground ground = null;

	int hitdefpersist = 0;

	int playerpush = 0;

	Shake shake = new Shake();

	BindToSub bindTo = null;

	float attackmulset = 1f;

	int number = 0;
	
	int ownpal = 1;
	ScreenboundSub screenbound = new ScreenboundSub();


	public ScreenboundSub getScreenbound() {
		return screenbound;
	}

	public void setScreenbound(ScreenboundSub screenbound) {
		this.screenbound = screenbound;
	}

	AirJump airjump = new AirJump(); 
	

	public int getOwnpal() {
		return ownpal;
	}

	public void setOwnpal(int ownpal) {
		this.ownpal = ownpal;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public float getAttackmulset() {
		return attackmulset;
	}

	public void setAttackmulset(float attackmulset) {
		this.attackmulset = attackmulset;
	}

	public BindToSub getBindTo() {
		return bindTo;
	}

	public Shake getShake() {
		return shake;
	}

	public int getHitdefpersist() {
		return hitdefpersist;
	}

	public void setHitdefpersist(int hitdefpersist) {
		this.hitdefpersist = hitdefpersist;
	}

	private int widthTime = -1;

	public void setWidth(int... data) {
		if (ground == null) {
			ground = new Ground();
			ground.setBack(size.getGround().getBack());
			ground.setFront(size.getGround().getFront());
		}
		ground.setFront(data[0]);
		if (data.length > 1)
			ground.setBack(data[1]);

		widthTime = 2;

	}
	public void setEdge(int... data) {
		if (edge == null) {
			edge = new Ground(0, 0);
		}
		edge.setFront(data[0]);
		if (data.length > 1)
			edge.setBack(data[1]);

		widthTime = 2;
		ground = edge;
	}

	public void restoreWidth() {
		if (ground == null) {
			ground = new Ground();
		}
		if (type == Type.I || type == Type.A) {
			ground.setBack(size.getAir().getBack());
			ground.setFront(size.getAir().getFront());
			
			edge.setBack(size.getAir().getBack());
			edge.setFront(size.getAir().getFront());
		} else {
			ground.setBack(size.getGround().getBack());
			ground.setFront(size.getGround().getFront());

			edge.setBack(size.getGround().getBack());
			edge.setFront(size.getGround().getFront());

		}

	}

	public Ground getWidth() {
		if (ground == null) {
			ground = new Ground();
			if (type == Type.A || type == Type.I) {
				ground.setBack(size.getAir().getBack());
				ground.setFront(size.getAir().getFront());
			} else {
				ground.setBack(size.getGround().getBack());
				ground.setFront(size.getGround().getFront());
			}
		}
		return ground;
	}
	
	private Ground edge = new Ground();
	
	public Ground getEdge() {
		if (edge == null) {
			edge = new Ground();
			if (type == Type.A || type == Type.I) {
				edge.setBack(size.getAir().getBack());
				edge.setFront(size.getAir().getFront());
			} else {
				edge.setBack(size.getGround().getBack());
				edge.setFront(size.getGround().getFront());
			}
		}
		return edge;
	}

	public Rectangle getSizeRect() {
		int topX = (int) (isFlip ? getWidth().getFront() : getWidth().getBack());
		int bottomX = (int) (isFlip ? getWidth().getBack() : getWidth()
				.getFront());
		int topY = (int) (getSize().getHeight());
		int bottomY = 0;

		topX = (int) (getXPos() - topX);
		bottomX = (int) (getXPos() + bottomX);
		topY = (int) (getYPos() + topY);
		bottomY = (int) (getYPos() - bottomY);

		return new Rectangle(topX, (int) (topY + getSize().getHeight()), Math
				.abs(bottomX - topX),
				(int) (Math.abs(bottomY - topY) - getSize().getHeight()));
	}

	private PointF offset = new PointF();

	private int sprpriority = 0;

	private float defenceMul = 1;

	private float xPos;

	private float yPos;
	
	private float xPosWhenBind;

	private float yPosWhenBind;

	private int ctrl = 1;

	private boolean isFlip = false;

	private VelSetSub velset;

	private NotHitBySub nothitby = new NotHitBySub();
	private HitBySub hitby = new HitBySub();

	private LinkedList<HitDefSub> hitdefs = new LinkedList<HitDefSub>();

	private Playsnd playsnd = new Playsnd();

	/**
	 * 
	 */
	private Map<String, WrapInt> commands = new HashMap<String, WrapInt>();

	//

	public int getCommand(String cmd) {
		return commands.containsKey(cmd) ? 1 : 0;
	}



	public NotHitBySub getNothitby() {
		return nothitby;
	}

	public void setNothitby(NotHitBySub nothitby) {
		this.nothitby = nothitby;
	}

	public Playsnd getPlaysnd() {
		return playsnd;
	}

	public void setPlaysnd(Playsnd playsnd) {
		this.playsnd = playsnd;
	}

	public int getCtrl() {
		return ctrl;
	}

	public int getSprpriority() {
		return sprpriority;
	}

	public void setSprpriority(int sprPriority) {
		this.sprpriority = sprPriority;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public float getXPos() {
		if (bindTo != null && bindTo.getTime() > 0) {
			return xPosWhenBind;
		}
		return xPos;
	}

	public void setXPos(float pos) {
//		if (bindTo != null && bindTo.getTime() > 0) {
//			
//		} else {
		xPos = pos;
		xPosWhenBind = pos;
	}

	public void moveXPos(float x) {
		if (isFlip())
			x = -x;
		xPos += x;
		xPosWhenBind += x;

	}

	public void addXPos(float x) {
		xPosWhenBind += x;
		xPos += x;
	}

	public void addYPos(float y) {
		yPosWhenBind += y;
		yPos += y;
	}
	public float getYPos() {
		if (bindTo != null && bindTo.getTime() > 0) {
			return yPosWhenBind;
		}
		return yPos;
	}

	public void setYPos(float pos) {
		yPos = pos;
		yPosWhenBind = pos;
	}

	public void setCtrl(int ctrl) {
		this.ctrl = ctrl;
	}

	public PointF getOffset() {
		return offset;
	}

	public void setOffset(PointF offset) {
		this.offset = offset;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public float getDefenceMul() {
		return defenceMul;
	}

	public void setDefenceMul(float defenceMul) {
		this.defenceMul = defenceMul;
	}

	public void lifeAdd(float value, int kill, int absolute) {
		float add = value;
		if (absolute != 0) {
			add *= defenceMul;
		}
		if (kill == 0 && life == 1) {
			life += add;
			return;
		}
		life += add;
	}

	public void addAttack(float value) {
		attack += value;
	}

	public void setAnim(int animIdx) {
		getSprite().getSprAnimMng().setAnim(animIdx);
	}

	public int getAnim() {
		return getSprite().getSprAnimMng().getAction();
	}

	// Additionnal

	// Function



	// --------------------------------//
	// Getters Setter
	// --------------------------------//

	private boolean isFirstTimeLieDown = true;

	public void process() {
		if (getSprite().isPause()) {
			return;
		}
		// HIT_LIEDOWN
		if (type == Type.L
				&& getSprite().getSpriteState().getCurrentState().getIntId() == 5110) {
			if (isFirstTimeLieDown) {
				isFirstTimeLieDown = false;
				liedownTime = getData().getLiedown().getTime();

			}
			if (liedownTime < 0) {
				getSprite().getSpriteState().selfstate(5120);
				isFirstTimeLieDown = true;
			}
			liedownTime--;
		}
		shake.addTime(-1);
		if (widthTime < 0) {
			restoreWidth();
		} else
			widthTime--;
		if (playerpush >= 0)
			playerpush--;

		List<String> removes = new ArrayList<String>();
		for (String cmdKey : commands.keySet()) {
			WrapInt time = commands.get(cmdKey);

			time.setValue(time.getValue() - 1);
			if (time.getValue() < 0)
				removes.add(cmdKey);
		}
		for (String cmdKey : removes)
			commands.remove(cmdKey);
		// bindToTarget

		if (bindTo != null && bindTo.getTime() > 0) {
			xPosWhenBind = bindTo.getPos().getX();
			yPosWhenBind = bindTo.getPos().getY();


		} 
		if (bindTo != null && bindTo.getTime() > -1)
			bindTo.addTime(-1);
		if (bindTo != null && bindTo.getTime() == 0) {
			if (bindTo.isBindOriginal()) {
				xPos = bindTo.getPos().getX();
				yPos = bindTo.getPos().getY();
			}
		}
		for (Iterator<HitDefSub> iter = hitdefs.iterator();iter.hasNext();) {
			HitDefSub hitdef = iter.next();
			hitdef.addHittime(-1);
			if (hitdef.getHittime() < 0 && hitdefs.size() > 1)
				iter.remove();
				
		}
		
		
		if (type == Type.C || type == Type.S)
			sprpriority = 0;
		else if (type == Type.A || type == Type.I)
			sprpriority = 1;
		if (movetype == MoveType.H)
			sprpriority = 0;
		else if (movetype == MoveType.A)
			sprpriority = 1;
		if (nothitby != null && nothitby.getTime() > 0)
			nothitby.setTime(nothitby.getTime() - 1);
		
		for (HitOverrideSub h: hitoverrides.values()) {
			if (h.getTime() > 0) {
				h.decreaseTime();
			}
		}
	}
	
	// ----- ========= Build from parsing ======== --------
	private String spriteId;

	private Sprite getSprite() {
		return GameFight.getInstance().getSpriteInstance(spriteId);
	}

	public SpriteCns(String spriteId) {
		this.spriteId = spriteId;
		velset = new VelSetSub(spriteId);
	}

	// ------- ========= Trigger Or Cns State Controller ====== -------

	// / CONST GETTER
	public Data getData() {
		return data;
	}

	public Movement getMovement() {
		return movement;
	}

	public Size getSize() {
		return size;
	}

	public int[] getCurrentFrontAndBackSize() {
		if (type.bit == Type.A.bit) {
			return new int[] {(int) getSize().getAir().getFront(), (int) getSize().getAir().getBack()};
		} else {
			return new int[] {(int) getSize().getGround().getFront(), (int) getSize().getGround().getBack()};
			
		}
	}
	
	public Velocity getVelocity() {
		return velocity;
	}

	public VelSetSub getVelset() {
		return velset;
	}

	public void setVelset(float... velset) {
		if (velset.length > 0)
			this.velset.setX(velset[0]);
		if (velset.length > 1)
			this.velset.setY(velset[1]);
		else
			this.velset.setY(0);
	}

	public void setVelset(Object... params) {
		if (params.length > 0) {
			velset
					.setX(params[0] instanceof Number ? Parser
							.getFloatValue(params[0]) : new Float(params[0]
							.toString()));
		}
		if (params.length > 1) {
			velset
					.setY(params[1] instanceof Number ? Parser
							.getFloatValue(params[1]) : new Float(params[1]
							.toString()));
		} else {
			this.velset.setY(0);
		}
		if (params.length > 2) {
			velset
					.setZ(params[2] instanceof Number ? Parser
							.getFloatValue(params[2]) : new Float(params[2]
							.toString()));
		}

	}

	public void setVeladd(float... veladd) {
		if (veladd.length > 0)
			this.velset.addX(veladd[0]);
		if (veladd.length > 1)
			this.velset.addY(veladd[1]);
	}

	public void setVeladd(Object... params) {
		if (params.length > 0) {
			velset
					.addX(params[0] instanceof Number ? Parser
							.getFloatValue(params[0]) : new Float(params[0]
							.toString()));
		}
		if (params.length > 1) {
			velset
					.addY(params[0] instanceof Number ? Parser
							.getFloatValue(params[0]) : new Float(params[0]
							.toString()));
		}
		if (params.length > 2) {
			velset
					.addZ(params[0] instanceof Number ? Parser
							.getFloatValue(params[0]) : new Float(params[0]
							.toString()));
		}
	}

	public boolean isFlip() {
		if (bindTo != null && bindTo.getTime() > 0 && bindTo.isFlip() != null) {
			return bindTo.isFlip();
		}
		
		return isFlip;
	}

	public void setFlip(boolean pIsFlip) {

		if (this.isFlip != pIsFlip) {
//			getVelset().mulX(-1);
		}
		this.isFlip = pIsFlip;
		
	}

	public MoveType getMovetype() {
		return movetype;
	}

	private int forceMoveTypeTime = -1;

	private MoveType forceMoveType;

	public void setMovetype(MoveType moveType) {
		if (forceMoveTypeTime >= 0) {
			// if (forceMoveType != MoveType.H) {
			// lastHitdef = null;
			// }

			this.movetype = forceMoveType;
			forceMoveTypeTime--;
			return;
		}
		if (moveType == MoveType.U)
			return;
		// if (moveType != MoveType.H) {
		// lastHitdef = null;
		// }
		this.movetype = moveType;

	}

	public void setMovetype(Object moveType) {
		if (MoveType.U == MoveType.valueOf(moveType.toString()))
			return;
		setMovetype(MoveType.valueOf(moveType.toString()));
	}

	public Physics getPhysics() {
		return physics;
	}

	public void setPhysics(Physics physics) {
		if (physics == Physics.U)
			return;
		this.physics = physics;
	}

	public void setPhysics(Object physics) {
		if (Physics.valueOf(physics.toString()) == Physics.U)
			return;
		setPhysics(Physics.valueOf(physics.toString()));
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		if (type == Type.U)
			return;
		this.type = type;
	}

	public void setType(Object type) {
		if (Type.valueOf(type.toString()) == Type.U)
			return;
		this.type = Type.valueOf(type.toString());
	}

	public void clearCommand() {
		commands.clear();
	}

	public void addCommand(String commandName, int time) {
		commands.put(commandName, new WrapInt(time));

	}

	public String getCommands() {
		return commands.keySet().toString();
	}
	public int getCommandsCount() {
		return commands.keySet().size();
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
		if (this.power > getData().getPower())
			this.power = getData().getPower();
	}

	public void setPoweradd(int power) {
		this.power += power;
		if (this.power > getData().getPower())
			this.power = getData().getPower();
	}

	public int getAirjuggle() {
		return airjuggle;
	}

	public void setAirjuggle(int airjuggle) {
		this.airjuggle = airjuggle;
	}

	public int getJuggle() {
		return airjuggle;
	}

	public void setJuggle(int airjuggle) {
		this.airjuggle = airjuggle;
	}

	// TODO : Make facep2 a fonction that place player 1
	// int facep2;
	// public int getFacep2() {
	// return facep2;
	// }
	public void setFacep2(int facep2) {
		
		if (facep2 == 1) {
			Sprite spr = null;
			for (Sprite s : GameFight.getInstance().getEnnmies(getSprite()))
				if (!s.getSpriteId().equals(spriteId) && !(s instanceof SpriteHelper))
					spr = s;
			if (xPos > spr.getInfo().getXPos())
				isFlip = true;
			else 
				isFlip = false;
		}
	}

	public MoveType getForceMoveType() {
		return forceMoveType;
	}

	public int getForceMoveTypeTime() {
		return forceMoveTypeTime;
	}

	public void setForceMoveType(MoveType forceMoveType, int time) {
		this.forceMoveType = forceMoveType;
		this.forceMoveTypeTime = time;
	}

	public int getPosfreeze() {
		return posfreeze;
	}

	public void setPosfreeze(int posfreeze) {
		this.posfreeze = posfreeze;
	}

	public void addPosFreeze(int i) {
		posfreeze += i;
	}

	public void addLife(int value) {
		life += value;

	}

	public int getPlayerpush() {
		return playerpush;
	}

	public void setPlayerpush(int playerpush) {
		this.playerpush = playerpush;
	}

	public HitDefSub getLastHitdef() {
		return hitdefs.isEmpty() ? null: hitdefs.getLast();
	}

	
	public int getHitcount() {
		int hitCount = 0;
		for (HitDefSub h: hitdefs) {
			if (h.getHittime() > 0)
				hitCount++;
		}
		return hitCount;
	}

	public void setLastHitdef(HitDefSub hitdef) {
		if (hitdef.getSnap() != null) {
			int mul = 1;
			if (!isFlip() && hitdef.getSpriteHitter().isFlip())
				mul = -1;
			if (isFlip() && !hitdef.getSpriteHitter().isFlip())
				mul = 1;
			setXPos(hitdef.getSpriteHitter().getRealXPos() + hitdef.getSnap().x * (mul));
			yPos = hitdef.getSpriteHitter().getRealYPos() + hitdef.getSnap().y;
		}
		hitdef.setSprHittedTypeWhenHit(getType());
		if (hitdef.getAttr().containsType(Type.A)) {
			hitdef.setHittime(hitdef.getAir().getHittime());
		} else {
			hitdef.setHittime(hitdef.getGround().getHittime());
			
		}
		HitDefSub lastHitdef = getLastHitdef();
		hitdef.setLastTimeHitSomething(GameFight.getInstance().getGameState().getGameTime());
//		hitdef.setHittedFlipWhenHit(isFlip());
		
		if (lastHitdef != null) {
			long resultOfNew = hitdef.getHittime();
			long resultOfOld = lastHitdef.getHittime();
			if (resultOfNew < resultOfOld) {
//				hitdef.addHittime((int) (resultOfOld - resultOfNew));
			}
		}
		// Add 2 because in this time it'll be decrease
//		hitdef.addHittime(-1);

		hitdefs.add(hitdef);
		this.sprpriority = hitdef.getSprpriority();

	}

	public void setSpriteId(String id) {
		spriteId = id;
		velset.setSpriteId(id);
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		SpriteCns scns = (SpriteCns) super.clone();
		
		scns.commands = new HashMap<String, WrapInt>();

		scns.bindTo = null;//new BindToSub();//(BindToSub) bindTo.clone();
		scns.data = (Data) data.clone();
		if (ground != null)
			scns.ground = (Ground) ground.clone();
		scns.movement = (Movement) movement.clone();
		scns.nothitby = (NotHitBySub) nothitby.clone();
		scns.shake = (Shake) shake.clone();
		scns.size = (Size) size.clone();
		scns.velocity = (Velocity) velocity.clone();
		scns.velset = (VelSetSub) velset.clone();
		scns.hitdefs = new LinkedList<HitDefSub>();
		scns.hitoverrides = new HashMap<Integer, HitOverrideSub>();
		return scns;
	}



	public void setBindTo(BindToSub bindTo) {
		this.bindTo = bindTo;
		if (bindTo != null && bindTo.getTime() > 0) {
			xPosWhenBind = bindTo.getPos().getX();
			yPosWhenBind = bindTo.getPos().getY();
			if (bindTo.getTime() == 0) {
				if (bindTo.isBindOriginal()) {
					xPos = bindTo.getPos().getX();
					yPos = bindTo.getPos().getY();
				}
			}
		} 
		//		xPos = bindTo.getPos().getX();
//		yPos = bindTo.getPos().getY();
		
	}

	public void addHitCount(Integer val) {
//		hitcount += val; // TODO
	}

	public HitBySub getHitby() {
		return hitby;
	}

	public void setHitby(HitBySub hitby) {
		this.hitby = hitby;
	}

	public AirJump getAirjump() {
		return airjump;
	}

	public void setAirjump(AirJump airjump) {
		this.airjump = airjump;
	}
	
	public boolean isMoveContact() {
		for (HitDefSub hitdef: hitdefs) {
			if (hitdef.getHittime() > 0 
					&& hitdef.getLastTimeHitSomething() != -1
					&& hitdef.getLastTimeHitSomething() + 1 < GameFight.getInstance().getGameState().getGameTime())
				return true;
		}
		return false;
	}

	public List<HitDefSub> getHitdefs() {
		return hitdefs;
	}

	
	private Map<Integer, HitOverrideSub> hitoverrides = new HashMap<Integer, HitOverrideSub>();
	
	public void setHitOverride(HitOverrideSub hitOverride) {
		hitoverrides.put(hitOverride.getSlot(), hitOverride);
	}
	public boolean isHitOverride(AttrClass attr) {
		for (HitOverrideSub h: hitoverrides.values()) {
			if ((h.getTime() == -1 || h.getTime() > 0) && attr.match(h.getAttr()))
				return true;
		}
		return false;
	}
	
	public HitOverrideSub getHitOverride(AttrClass attr) {
		for (HitOverrideSub h: hitoverrides.values()) {
			if ((h.getTime() == -1 || h.getTime() > 0) && attr.match(h.getAttr()))
				return h;
		}
		return null;
	}

	public void addGroupData(GroupText grp) {
		getGroups().add(grp);
		
	}
}
