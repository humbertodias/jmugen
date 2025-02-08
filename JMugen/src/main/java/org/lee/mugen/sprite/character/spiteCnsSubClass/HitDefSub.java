package org.lee.mugen.sprite.character.spiteCnsSubClass;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.lee.mugen.renderer.PalFxSub;
import org.lee.mugen.sprite.base.AbstractSprite;
import org.lee.mugen.sprite.entity.CoupleOfAttrTypeAndLevel;
import org.lee.mugen.sprite.entity.Priority;
import org.lee.mugen.sprite.entity.Shake;
import org.lee.mugen.sprite.entity.SndGrpNum;
import org.lee.mugen.sprite.entity.Sparkno;
import org.lee.mugen.sprite.entity.Velocity;
import org.lee.mugen.sprite.parser.Parser;


public class HitDefSub implements Serializable {
	///
	protected int hittime;
	protected AbstractSprite spriteHitter;
	protected String spriteId;
	protected String targetId;




	private long timeCreated = -1;
	
	private long lastTimeHitSomething = -1;
	private long lastTimeBlockBySomething = -1;
	
	private org.lee.mugen.sprite.character.SpriteCns.Type sprHittedTypeWhenHit;

	///////////////////////////////////////
	

	public boolean isBlocked() {
		return lastTimeBlockBySomething != -1;
	}
	//	@Override
//	public int hashCode() {
//		return (stateTimeHitted + spriteId + getClass()).hashCode();
//	}
	public void addHittime(int i) {
		if (hittime >= 0)
			hittime += i;
	}
	public int getHittime() {
		return hittime;
	}
	public void setHittime(int hittime) {
		this.hittime = hittime;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		return hashCode() == obj.hashCode();
	}
	
	
	
	///////////////////////////////////////
	
	public static enum HitFlag {
		H("H", 1), L("L", 2), A("A", 4), M("M", 3), F("F", 8), D("D", 16), P("P", 32), PLUS("+", 64), MOINS("-", 128);
		
		private String desc;
		public final int bit;
		
		public static final int DEFAULT = M.bit | A.bit | F.bit;

		HitFlag(String desc, int bit) {
			this.desc = desc;
			this.bit = bit;
		}
		public String getDesc() {
			return desc;
		}
		public int getBit() {
			return bit;
		}
	}
	public static enum AttrLevel {
		N("N", 1), S("S", 2), H("H", 4);
		//N("Normal", 1), S("Special", 2), H("Hyper", 4);
		public static boolean isAttrLevel(String lvl) {
			return lvl.equals("N") || lvl.equals("S") || lvl.equals("H");
		}
		private String desc;
		public final int bit;
		AttrLevel(String desc, int bit) {
			this.desc = desc;
			this.bit = bit;
		}
		public String getDesc() {
			return desc;
		}
		public int getBit() {
			return bit;
		}
		
	}
	public static enum AttrType {
		A("A", 1), T("T", 2), P("P", 4);
		//A("Attack", 1), T("Throw", 2), P("Projectile", 4);
		public static boolean isAttrType(String lvl) {
			return lvl.equals("A") || lvl.equals("T") || lvl.equals("P");
		}
		private String desc;
		public final int bit;
		AttrType(String desc, int bit) {
			this.desc = desc;
			this.bit = bit;
		}
		public String getDesc() {
			return desc;
		}
		public int getBit() {
			return bit;
		}
		
	}
//  attr = hit_attribute (string)
	private AttrClass attr = new AttrClass();
	public void setAttr(AttrClass attrClass) {
		attr = attrClass;
		
	}

	public AttrClass getAttr() {
		return attr;
	}
	
	public static class AttrClass implements Serializable {
	/*
- arg1 est soit 
	"S", 
	"C" ou 
	"A". De même que pour "statetype" pour le StateDef, ceci indique soit que l'attaque est une attaque en Stand (debout), en Crouch (accroupi) ou Aerial (aérienne), respectivement.
- arg2 est une chaîne de 2 caractères. Le premier est soit 
	"N" pour "normal", 
	"S" pour "special", ou 
	"H" pour "hyper" (ou "super"/"fury", tels que communément appelés). 

Le second caractère doit être soit 
	"A" pour "attaque" (coup normal d'attaque), 
	"T" pour "throw" (projection/prise) ou 
	"P" pour "projectile".
	 */
		
		

		
		private int type = org.lee.mugen.sprite.character.SpriteCns.Type.S.getBit();
		private List<CoupleOfAttrTypeAndLevel> couples = new ArrayList<CoupleOfAttrTypeAndLevel>();

		public boolean containsType(org.lee.mugen.sprite.character.SpriteCns.Type type) {
			return (type.getBit() | this.type) == type.getBit();
		}
		
		public boolean containsType(int type) {
			return (type & this.type) != 0;
		}
		
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}

		public void addAttrTypeAndLevel(AttrType attrType, AttrLevel attrLevel) {
			couples.add(new CoupleOfAttrTypeAndLevel(attrType, attrLevel));
		}
		
		

		public boolean isAttrTypeAndLevel(AttrType attrType, AttrLevel attrLevel) {
			for (CoupleOfAttrTypeAndLevel c: couples) {
				if (c.getAttrLevel() == attrLevel && c.getAttrType() == attrType)
					return true;
			}
			return false;
		}

		public boolean isAttrTypesAndLevels(Collection<CoupleOfAttrTypeAndLevel> couples) {
			for (CoupleOfAttrTypeAndLevel c: couples) {
				if (isAttrTypeAndLevel(c.getAttrType(), c.getAttrLevel()))
					return true;
			}
			return false;
		}

		
		public boolean isAttrType(AttrType attrType) {
			for (CoupleOfAttrTypeAndLevel c: couples) {
				if (c.getAttrType() == attrType)
					return true;
			}
			return false;
		}
		public boolean isAttrLevel(AttrType attrType) {
			for (CoupleOfAttrTypeAndLevel c: couples) {
				if (c.getAttrType() == attrType)
					return true;
			}
			return false;
		}
		public List<CoupleOfAttrTypeAndLevel> getCouples() {
			return couples;
		}

		public boolean match(AttrClass attr) {
			if ((attr.getType() & getType()) != 0) {
				if (attr.getCouples().size() == 0)
					return true;
				for (CoupleOfAttrTypeAndLevel tl : attr.getCouples()) {
					if (isAttrTypeAndLevel(tl.getAttrType(), tl.getAttrLevel()))
						return true;
				}
			}
			return false;
		}
		
	}

	  
//  hitflag = hit_flags (string)
	private int hitflag = HitFlag.M.getBit() | HitFlag.A.getBit() | HitFlag.F.getBit();
	public void setHitflag(int value) {
		hitflag = value;
	}
	public int getHitFlag() {
		return hitflag;
	}
	
//  guardflag = hit_flags (string)
	public static enum GuardFlag {
		H("H", 1), L("L", 2), A("A", 4), M("M", 3), PLUS("-", 16), MOINS("-", 32);
		public static boolean isGuardFlag(String g) {
			return "H".equals(g)
			|| "L".equals(g) || "A".equals(g) || "M".equals(g) || "PLUS".equals(g) || "MOINS".equals(g);
		}
		private String desc;
		public final int bit;
		GuardFlag(String desc, int bit) {
			this.desc = desc;
			this.bit = bit;
		}
		public String getDesc() {
			return desc;
		}
		public int getBit() {
			return bit;
		}
	}

	private int guardflag = 0;
	public void setGuardflag(int guardflag) {
		this.guardflag = guardflag;
	}
	public int getGuardFlag() {
		return guardflag;
	}
	
//--------------Optional parameters:
//	  affectteam = team_type (string)
	public static enum AffectTeam {
		B("B", 1), E("E", 2), F("F", 4);
		
		private String desc;
		private int bit;
		
		public static final AffectTeam DEFAULT = E;
		
		AffectTeam(String desc, int bit) {
			this.desc = desc;
			this.bit = bit;
		}
		public String getDesc() {
			return desc;
		}
		public int getBit() {
			return bit;
		}
		
	}

	private AffectTeam affectTeam = AffectTeam.E;
	public AffectTeam getAffectTeam() {
		return affectTeam;
	}
	public void setAffectteam(AffectTeam affectTeam) {
		this.affectTeam = affectTeam;
	}
	

//	  damage = hit_damage, guard_damage (int)
	private Damage damage = new Damage();
	public Damage getDamage() {
		return damage;
	}
	public static class Damage {
		private int hit_damage;
		private int guard_damage;
		public int getGuard_damage() {
			return guard_damage;
		}
		public void setGuard_damage(int guard_damage) {
			this.guard_damage = guard_damage;
		}
		public int getHit_damage() {
			return hit_damage;
		}
		public void setHit_damage(int hit_damage) {
			this.hit_damage = hit_damage;
		}
		public static void setDamage(Damage damage, Object...params) {
			if (params.length > 2) {
				throw new IllegalArgumentException("Set HiDef.damage take 2 parameters");
			}
			if (params.length > 0) {
				Integer hit_damage = params[0] instanceof Number? Parser.getIntValue(params[0]): new Integer(params[0].toString());
				damage.setHit_damage(hit_damage);
			}
			if (params.length == 2) {
				Integer guard_damage = params[1] instanceof Number? Parser.getIntValue(params[1]): new Integer(params[1].toString());
				damage.setGuard_damage(guard_damage);
			}
		}
		
	}
	public void setDamage(Object...params) {
		Damage.setDamage(damage, params);
	}
	
	//SPARK
	private int sparkguardauto = 0;
	private int sparkhitauto = 1;
	
//	  sparkno = [s]action_no (int)
	private Sparkno sparkno;
	public Sparkno getSparkno() {
		return sparkno;
	}
	public void setSparkno(Sparkno sparkno) {
		this.sparkno = sparkno;
	}
//	  sparkxy = spark_x, spark_y (int)
	private Sparkxy sparkxy = new Sparkxy();
	public Sparkxy getSparkxy() {
		return sparkxy;
	}
	public static class Sparkxy {
		private int spark_x;
		private int spark_y;
		public int getSpark_y() {
			return spark_y;
		}
		public void setSpark_y(int spark_y) {
			this.spark_y = spark_y;
		}
		public int getSpark_x() {
			return spark_x;
		}
		public void setSpark_x(int spark_x) {
			this.spark_x = spark_x;
		}
		public static void setSparkxy(Sparkxy sparkxy, Object...params) {
			if (params.length > 2) {
				throw new IllegalArgumentException("Set HiDef.sparkxy take 2 parameters");
			}
			if (params.length > 0) {
				Integer spark_x = params[0] instanceof Number? Parser.getIntValue(params[0]): new Integer(params[0].toString());
				sparkxy.setSpark_x(spark_x);
			}
			if (params.length == 2) {
				Integer spark_y = params[1] instanceof Number? Parser.getIntValue(params[1]): new Integer(params[1].toString());
				sparkxy.setSpark_y(spark_y);
			}
		}
	}
	public void setSparkxy(Object...params) {
		Sparkxy.setSparkxy(sparkxy, params);
	}
	


//	  hitsound = snd_grp, snd_item (int)
	private SndGrpNum hitsound = new SndGrpNum();
	public SndGrpNum getHitsound() {
		return hitsound;
	}
	public void setHitsound(Object...params) {
		SndGrpNum.setSound(hitsound, params);
	}
//	  guardsound = snd_grp, snd_item (int)
	private SndGrpNum guardsound = new SndGrpNum();
	public SndGrpNum getGuardsound() {
		return guardsound;
	}
	public void setGuardsound(Object...params) {
		SndGrpNum.setSound(guardsound, params);
	}
	
//	  yaccel = accel (float)
	private float yaccel;
	public float getYaccel() {
		return yaccel;
	}
	public void setYaccel(float yaccel) {
		this.yaccel = yaccel;
	}
	
// X Y Pos Class
	public static class XYPos {
		private int x_pos;
		private int y_pos;
		public int getX_pos() {
			return x_pos;
		}
		public void setX_pos(int x_pos) {
			this.x_pos = x_pos;
		}
		public int getY_pos() {
			return y_pos;
		}
		public void setY_pos(int y_pos) {
			this.y_pos = y_pos;
		}
		public static void setSound(XYPos xypos, Object...params) {
			if (params.length > 2) {
				throw new IllegalArgumentException("Set HiDef.XXXsound take 2 parameters");
			}
			if (params.length > 0) {
				Integer x_pos = params[0] instanceof Number? Parser.getIntValue(params[0]): new Integer(params[0].toString());
				xypos.setX_pos(x_pos);
			}
			if (params.length > 1) {
				Integer y_pos = params[1] instanceof Number? Parser.getIntValue(params[1]): new Integer(params[1].toString());
				xypos.setY_pos(y_pos);
			}
		}
	}
	
//	  mindist = x_pos, y_pos (int) 
	private XYPos mindist = new XYPos();
	public XYPos getMindist() {
		return mindist;
	}
	public void setMindist(Object...params) {
		XYPos.setSound(mindist, params);
	}
//	  maxdist = x_pos, y_pos (int)
	private XYPos maxdist = new XYPos();
	public XYPos getMaxdist() {
		return maxdist;
	}
	public void setMaxdist(Object...params) {
		XYPos.setSound(maxdist, params);
	}	
//	  snap = x_pos, y_pos (int)
	private Point snap;
	public Point getSnap() {
		return snap;
	}
	public void setSnap(Point params) {
		snap = params;
	}		
//	  p1sprpriority = drawing_priority (int)
	private int p1sprpriority;
	
	public int getP1sprpriority() {
		return p1sprpriority;
	}
	public void setP1sprpriority(int p1sprpriority) {
		this.p1sprpriority = p1sprpriority;
	}
	
//	  p2sprpriority = drawing_priority (int)
	private int p2sprpriority;
	public int getP2sprpriority() {
		return p2sprpriority;
	}
	public void setP2sprpriority(int p2sprpriority) {
		this.p2sprpriority = p2sprpriority;
	}
	
// sprpriority
	private int sprpriority;
	public int getSprpriority() {
		return sprpriority;
	}

	public void setSprpriority(int sprpriority) {
		this.sprpriority = sprpriority;
	}
	
//	  p1facing = facing (int)
	private int p1facing;
	public int getP1facing() {
		return p1facing;
	}
	public void setP1facing(int p1facing) {
		this.p1facing = p1facing;
	}

//	  p1getp2facing = facing (int)
	private int p1getp2facing;
	public int getP1getp2facing() {
		return p1getp2facing;
	}
	public void setP1getp2facing(int p1getp2facing) {
		this.p1getp2facing = p1getp2facing;
	}
//	  p2facing = facing (int)
	private int p2facing;
	public int getP2facing() {
		return p2facing;
	}
	public void setP2facing(int p2facing) {
		this.p2facing = p2facing;
	}
//	  p1stateno = state_no (int)
	private Integer p1stateno;
	public Integer getP1stateno() {
		return p1stateno;
	}
	public void setP1stateno(Integer p1stateno) {
		this.p1stateno = p1stateno;
	}
//	  p2stateno = state_no (int)
	private Integer p2stateno;
	public Integer getP2stateno() {
		return p2stateno;
	}
	public void setP2stateno(Integer p2stateno) {
		this.p2stateno = p2stateno;
	}
//	  p2getp1state = value (int)
	private int p2getp1state = 1;
	public int getP2getp1state() {
		return p2getp1state;
	}
	public void setP2getp1state(int p2getp1state) {
		this.p2getp1state = p2getp1state;
	}
//	  forcestand = value (int)
	private int forcestand;
	public int getForcestand() {
		return forcestand;
	}
	public void setForcestand(int forcestand) {
		this.forcestand = forcestand;
	}
//	  id = id_number (int)
	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
//	  chainID = id_number (int)
	private int chainID;
	public int getChainid() {
		return chainID;
	}
	public void setChainid(int chainID) {
		this.chainID = chainID;
	}
//	  kill = kill_flag (int)
	private int kill;
	public int getKill() {
		return kill;
	}
	public void setKill(int kill) {
		this.kill = kill;
	}
//	  numhits = hit_count (int)
	private int numhits;
//	  getpower = p1power, p1gpower (int)
	public static class Getpower {
		int p1power;
		int p1gpower;
	}
	
	private Getpower getpower = new Getpower();
	public void setGetpower(int...params) {
		if (params.length > 0) {
			getpower.p1power = params[0];
		}
		if (params.length > 1) {
			getpower.p1gpower = params[1];
		}
			
	}
	public Getpower getGetpower() {
		return getpower;
	}
	public int getNumhits() {
		return numhits;
	}
	public void setNumhits(int numhits) {
		this.numhits = numhits;
	}
//	  hitonce = hitonce_flag (boolean)
	private int hitonce;
	public int getHitonce() {
		return hitonce;
	}
	public void setHitonce(int hitonce) {
		this.hitonce = hitonce;
	}
	




	
//	  givepower = p2power, p2gpower (int)
	private Givepower givepower = new Givepower();
	public Givepower getGivepower() {
		return givepower;
	}
	public void setGivepower(Object...params) {
		Givepower.setGivepower(givepower, params);
	}
	public static class Givepower {
		private int p2power;
		private int p2gpower;
		public int getP2power() {
			return p2power;
		}
		public void setP2power(int p2power) {
			this.p2power = p2power;
		}
		public int getP2gpower() {
			return p2gpower;
		}
		public void setP2gpower(int p2gpower) {
			this.p2gpower = p2gpower;
		}
		public static void setGivepower(Givepower givepower, Object...params) {
			if (params.length > 2) {
				throw new IllegalArgumentException("Set HiDef.XXXsound take 2 parameters");
			}
			if (params.length > 0) {
				Integer p2power = params[0] instanceof Number? Parser.getIntValue(params[0]): new Integer(params[0].toString());
				givepower.setP2power(p2power);
			}
			if (params.length == 2) {
				Integer p2gpower = params[1] instanceof Number? Parser.getIntValue(params[1]): new Integer(params[1].toString());
				givepower.setP2gpower(p2gpower);
			}
		}
	}
	
//	  nochainID = nochain_1, nochain_2 (int)
	private NochainID nochainID = new NochainID();
	public NochainID getNochainID() {
		return nochainID;
	}
	public void setNochainID(Object...params) {
		NochainID.setNochainID(nochainID, params);
	}
	public static class NochainID {
		private int nochain_1;
		private int nochain_2;
		public int getP2power() {
			return nochain_1;
		}
		public void setP2power(int nochain_1) {
			this.nochain_1 = nochain_1;
		}
		public int getP2gpower() {
			return nochain_2;
		}
		public void setP2gpower(int nochain_2) {
			this.nochain_2 = nochain_2;
		}
		public static void setNochainID(NochainID nochainID, Object...params) {
			if (params.length > 2) {
				throw new IllegalArgumentException("Set HiDef." + nochainID.getClass().getName() + " take 2 parameters");
			}
			if (params.length > 0) {
				Integer nochain_1 = params[0] instanceof Number? Parser.getIntValue(params[0]): new Integer(params[0].toString());
				nochainID.setP2power(nochain_1);
			}
			if (params.length == 2) {
				Integer nochain_2 = params[1] instanceof Number? Parser.getIntValue(params[1]): new Integer(params[1].toString());
				nochainID.setP2gpower(nochain_2);
			}
		}
	}

//	    animtype = anim_type (string)
/*    This refers to the type of animation that P2 will go into when hit
    by the attack. Choose from "light", "medium", "hard", "back", "up",
    or "diagup".
    The first three should be self-explanatory. "Back" is the
    animation where P2 is knocked off her feet. "Up" should be used
    when the character is knocked straight up in the air (for instance,
    by an uppercut), and "DiagUp" should be used when the character is
    knocked up and backwards in the air, eventually landing on his
    head.
    The default is "Light".
    Light, Medium, Heavy, Back
*///(0 pour light, 1 pour medium, 2 pour hard, 3 pour back, 4 pour up, 5 pour diag-up).

	public static enum AnimType {
		LIGHT(0), MEDIUM(1), MED(1), MID(1), HARD(2), HEAVY(2), BACK(2), UP(4), DIAGUP(5), LOW(0), HIGH(2)
		,HITUP(2);
		AnimType(int v) {
			value = v;
		}
		public final int value;
		
		public static AnimType getValueFromStr(String s) {
			if (s.length() == 1) {
				for (AnimType name : values()) {
					if (name.toString().startsWith(s.toUpperCase()))
						return name;
				}
				throw new IllegalArgumentException();
			} else {
				return valueOf(s);
			}
		}
	}
	private AnimType animType = AnimType.LIGHT;
	public AnimType getAnimtype() {
		return animType;
	}
	public void setAnimtype(AnimType animType) {
		this.animType = animType;
	}
    
   
//	  priority = hit_prior (int), hit_type (string)
	private Priority priority = new Priority();
	


// PauseTime Class
	public static class Pausetime {
		private int p1_pausetime;
		private int p2_shaketime;
		public int getP1_pausetime() {
			return p1_pausetime;
		}
		public void setP1_pausetime(int p1_pausetime) {
			this.p1_pausetime = p1_pausetime;
		}
		public int getP2_shaketime() {
			return p2_shaketime;
		}
		public void setP2_shaketime(int p2_shaketime) {
			this.p2_shaketime = p2_shaketime;
		}
		public static void setPausetime(Pausetime pausetime, Object...params) {
			if (params.length > 2) {
				throw new IllegalArgumentException("Set HiDef." + pausetime.getClass().getName() + " take 2 parameters");
			}
			if (params.length > 0) {
				Integer p1_pausetime = params[0] instanceof Number? Parser.getIntValue(params[0]): new Integer(params[0].toString());
				pausetime.setP1_pausetime(p1_pausetime);
			}
			if (params.length == 2) {
				Integer p2_shaketime = params[1] instanceof Number? Parser.getIntValue(params[1]): new Integer(params[1].toString());
				pausetime.setP2_shaketime(p2_shaketime);
			}
		}
	}

//	  pausetime = p1_pausetime, p2_shaketime (int)
	private Pausetime pausetime = new Pausetime();
	public Pausetime getPausetime() {
		return pausetime;
	}
	public void setPausetime(Object...params) {
		Pausetime.setPausetime(pausetime, params);
	}

// Cornpush Class
	public static class Cornerpush {
		private float veloff;

		public float getVeloff() {
			return veloff;
		}

		public void setVeloff(float veloff) {
			this.veloff = veloff;
		}
	}

//	  guard.pausetime = p1_pausetime, p2_shaketime (int)
//	  guard.sparkno = action_no (int)
//	  guard.slidetime = slide_time (int)
//	  guard.hittime = hit_time (int)
//	  guard.ctrltime = ctrl_time(int)
//	  guard.dist = x_dist (int)
//	  guard.velocity = x_velocity (float)
//	  guard.cornerpush.veloff = x_velocity (float)
//	  guard.kill = gkill_flag (int)
	private Guard guard = new Guard();
	public Guard getGuard() {
		return guard;
	}
	public void setGuard(Guard guard) {
		this.guard = guard;
	}
	public static class Guard {
		private Pausetime pausetime = new Pausetime();
		private Sparkno sparkno;
		private int slidetime;
		private int hittime;
		private int ctrltime;
		private int dist;
		private int kill;
		
		private org.lee.mugen.sprite.entity.PointF velocity;
		private Cornerpush cornerpush = new Cornerpush();
		
		public Pausetime getPausetime() {
			return pausetime;
		}
		public Sparkno getSparkno() {
			return sparkno;
		}
		public void setSparkno(Sparkno sparkno) {
			this.sparkno = sparkno;
		}
		public int getCtrltime() {
			return ctrltime;
		}
		public void setCtrltime(int ctrltime) {
			this.ctrltime = ctrltime;
		}
		public int getDist() {
			return dist;
		}
		public void setDist(int dist) {
			this.dist = dist;
		}
		public int getHittime() {
			return hittime;
		}
		public void setHittime(int hittime) {
			this.hittime = hittime;
		}
		public int getKill() {
			return kill;
		}
		public void setKill(int kill) {
			this.kill = kill;
		}
		public int getSlidetime() {
			return slidetime;
		}
		public void setSlidetime(int slidetime) {
			this.slidetime = slidetime;
		}
		public org.lee.mugen.sprite.entity.PointF getVelocity() {
			return velocity;
		}
		public void setVelocity(org.lee.mugen.sprite.entity.PointF velocity) {
			this.velocity = velocity;
		}
		public Cornerpush getCornerpush() {
			return cornerpush;
		}
		public void setPausetime(Pausetime pausetime) {
			this.pausetime = pausetime;
		}
		public void addHittime(int time) {
			hittime += time;
		}
		public void addSlidetime(int i) {
			slidetime += i;
		}
		public void addCtrltime(int i) {
			ctrltime += i;
		}
	}

	

//	  ground.type = attack_type (string) High Low Trip None (default : High)
//	  ground.slidetime = slide_time (int)
//	  ground.hittime = hit_time (int)
//	  ground.velocity = x_velocity, y_velocity (float)
//	  ground.cornerpush.veloff = x_velocity (float)
	
	public static enum Type {
		NONE, HIGH, LOW, TRIP;
	}

	private Ground ground = new Ground();
	public Ground getGround() {
		return ground;
	}

	public void setGround(Ground ground) {
		this.ground = ground;
	}
	public static class Ground {

		private Type type = Type.HIGH;
		private int slidetime;
		private int hittime;
		private Velocity velocity = new Velocity();
		private Cornerpush cornerpush = new Cornerpush();

		public int getHittime() {
			return hittime;
		}
		public void setHittime(int hittime) {
			this.hittime = hittime;
		}
		public int getSlidetime() {
			return slidetime;
		}
		public void setSlidetime(int slidetime) {
			this.slidetime = slidetime;
		}
		public Type getType() {
			return type;
		}
		public void setType(Type type) {
			this.type = type;
		}
		public Cornerpush getCornerpush() {
			return cornerpush;
		}
		public void setCornerpush(Cornerpush cornerpush) {
			this.cornerpush = cornerpush;
		}

		public void addHittime(int i) {
			hittime += i;
		}
		public void addSlidetime(int i) {
			slidetime += i;
		}
		public Velocity getVelocity() {
			return velocity;
		}
		public void setVelocity(Velocity velocity) {
			this.velocity = velocity;
		}
	}
//	  air.animtype = anim_type (string)
//	  air.type = attack_type (string)
	/*
	  air.type = attack_type (string)
	    This is the kind of attack if P2 is in the air. Defaults to the
	    same value as "ground.type" if omitted.
	 */
//	  air.hittime = hit_time (int)
//	  air.velocity = x_velocity, y_velocity (float)
//	  air.cornerpush.veloff = x_velocity (float)
//	  air.juggle = juggle_points (int)
//	  air.fall = value (int)
	private Air air = new Air();
	public Air getAir() {
		return air;
	}
	public void setAir(Air air) {
		this.air = air;
	}
	public static class Air {
		private AnimType animtype = AnimType.LIGHT;
		private Type type = Type.HIGH;
		private int hittime;
		private Velocity velocity = new Velocity();
		private Cornerpush cornerpush = new Cornerpush();
		private int juggle;
		private int fall;
		private int recover = 0;
		
		private AirGuard guard = new AirGuard();
		public AirGuard getGuard() {
			return guard;
		}
		public AnimType getAnimtype() {
			return animtype;
		}
		public void setAnimtype(AnimType animType) {
			this.animtype = animType;
		}
		public int getFall() {
			return fall;
		}
		public void setFall(int fall) {
			this.fall = fall;
		}
		public int getHittime() {
			return hittime;
		}
		public void setHittime(int hittime) {
			this.hittime = hittime;
		}
		public int getJuggle() {
			return juggle;
		}
		public void setJuggle(int juggle) {
			this.juggle = juggle;
		}
		public Type getType() {
			return type;
		}
		public void setType(Type type) {
			this.type = type;
		}
		public Cornerpush getCornerpush() {
			return cornerpush;
		}
		public Velocity getVelocity() {
			return velocity;
		}
		public void setVelocity(Velocity velocity) {
			this.velocity = velocity;
		}
		public void addHittime(int i) {
			hittime += i;
		}
		public int getRecover() {
			return recover;
		}
		public void setRecover(int recover) {
			this.recover = recover;
		}
		
		
	}
	
	
//	  air.guard.velocity = x_velocity, y_velocity (float)
//	  air.guard.cornerpush.veloff = x_velocity (float)
//	  air.guard.ctrltime = ctrl_time (int)
	private AirGuard airguard = new AirGuard();
	public AirGuard getAirguard() {
		return airguard;
	}
	public static class AirGuard {
		private Velocity velocity = new Velocity();
		private Cornerpush cornerpush = new Cornerpush();
		private int ctrltime;
		public int getCtrltime() {
			return ctrltime;
		}
		public void setCtrltime(int ctrltime) {
			this.ctrltime = ctrltime;
		}
		public Cornerpush getCornerpush() {
			return cornerpush;
		}

		public void adCtrltime(int i) {
			ctrltime += i;
		}
		public Velocity getVelocity() {
			return velocity;
		}
		public void setVelocity(Velocity velocity) {
			this.velocity = velocity;
		}
		
	}
	
	
//	  down.cornerpush.veloff = x_velocity (float)
//	  down.velocity = x_velocity, y_velocity (float)
//	  down.hittime = hit_time (int)
//	  down.bounce = value (int)
	private Down down = new Down();
	public Down getDown() {
		return down;
	}
	public static class Down {
		private Cornerpush cornerpush = new Cornerpush();
		private Velocity velocity = new Velocity();
		private int hittime;
		private int bounce;
		public int getBounce() {
			return bounce;
		}
		public void setBounce(int bounce) {
			this.bounce = bounce;
		}
		public int getHittime() {
			return hittime;
		}
		public void setHittime(int hittime) {
			this.hittime = hittime;
		}
		public Cornerpush getCornerpush() {
			return cornerpush;
		}

		public void addHittime(int i) {
			hittime += i;
		}
		public Velocity getVelocity() {
			return velocity;
		}
		public void setVelocity(Velocity velocity) {
			this.velocity = velocity;
		}
		
	}

//	  fall = value (int)
//	  fall.animtype = anim_type (string)
//	  fall.xvelocity = x_velocity (float)
//	  fall.yvelocity = y_velocity (float)
//	  fall.recover = value (int)
//	  fall.recovertime = recover_time (int)
//	  fall.damage = damage_amt (int)
//	  fall.kill = fkill_flag (int)
//	  fall.envshake.time = envshake_time (int)
//	  fall.envshake.freq = envshake_freq (float)
//	  fall.envshake.ampl = envshake_ampl (int)
//	  fall.envshake.phase = envshake_phase (float)
	
	private int fallInt;
	public int fall() {
		return fallInt;
	}
	public void setFall(int fall) {
		this.fallInt = fall;
	}
	
	private Fall fall = new Fall();
	public Fall getFall() {
		return fall;
	}

	public static class Fall {
		private AnimType animtype = AnimType.LIGHT;
		private Float xvelocity;
		private Float yvelocity = -4.5f;
		private int recover = 1;
		private int recovertime = 4;
		private int damage;
		private int kill;
		private int rebond = 0;
		private Shake envshake = new Shake();
		public AnimType getAnimtype() {
			return animtype;
		}
		public void setAnimtype(AnimType animType) {
			this.animtype = animType;
		}
		public int getDamage() {
			return damage;
		}
		public void setDamage(int damage) {
			this.damage = damage;
		}
		public int getKill() {
			return kill;
		}
		public void setKill(int kill) {
			this.kill = kill;
		}
		public int getRecover() {
			return recover;
		}
		public void setRecover(int recover) {
			this.recover = recover;
		}
		public int getRecovertime() {
			return recovertime;
		}
		public void setRecovertime(int recovertime) {
			this.recovertime = recovertime;
		}
		public Float getXvelocity() {
			return xvelocity;
		}
		public void setXvelocity(Float xvelocity) {
			this.xvelocity = xvelocity;
		}
		public Float getYvelocity() {
			return yvelocity;
		}
		public void setYvelocity(Float yvelocity) {
			this.yvelocity = yvelocity;
		}
		public Shake getEnvshake() {
			return envshake;
		}
		
		public void setVelocity(Object...params) {
			if (params.length > 2) {
				throw new IllegalArgumentException("Set HiDef.velocity take 2 parameters");
			}
			if (params.length > 0) {
				Float arg1 = params[0] instanceof Number? Parser.getFloatValue(params[0]): new Float(params[0].toString());
				setXvelocity(arg1.floatValue());
			}
			if (params.length == 2) {
				Float arg2 = params[1] instanceof Number? Parser.getFloatValue(params[1]): new Float(params[1].toString());
				setYvelocity(arg2.floatValue());
			}
		}
		public void addRecovertime(int i) {
			recovertime += i;
		}
		public void setEnvshake(Shake envshake) {
			this.envshake = envshake;
		}
		public int getRebond() {
			return rebond;
		}
		public void setRebond(int rebond) {
			this.rebond = rebond;
		}
		
	}
	
	private int ownpal;

//	  palfx.time = palfx_time (int)
//	  palfx.mul = r1, g1, b1 (int)
//	  palfx.add = r2, g2, b2 (int)
//	  palfx.sinadd = int, int , int, int
	private PalFxSub _palfx = new PalFxSub();

//	  envshake.time = envshake_time (int)
//	  envshake.freq = envshake_freq (float)
//	  envshake.ampl = envshake_ampl (int)
//	  envshake.phase = envshake_phase (float)
	private Shake envshake = new Shake();
	public Shake getEnvshake() {
		return envshake;
	}
	
//	  attack.width = z1, z2 (int)
//	    Not currently used.

	public void addPausetime(int p1PauseTime, int p2ShakeTime) {
		pausetime.p1_pausetime += p1PauseTime;
		pausetime.p2_shaketime += p2ShakeTime;
	}

	public PalFxSub getPalfx() {
		return _palfx;
	}

	public void setPalfx(PalFxSub palfx) {
		_palfx = palfx;
	}

	public void setSpriteHitter(AbstractSprite spriteHitter) {
		this.spriteHitter = spriteHitter;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public AbstractSprite getSpriteHitter() {
		return spriteHitter;
	}

	public String getSpriteId() {
		return spriteId;
	}

	public void setSpriteId(String spriteId) {
		this.spriteId = spriteId;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

//	private org.lee.mugen.sprite.character.SpriteCns.Type initialType;
//	public org.lee.mugen.sprite.character.SpriteCns.Type getTypeWhenHitted() {
//		return initialType;
//	}
//
//	public void setInitialType(
//			org.lee.mugen.sprite.character.SpriteCns.Type initialType) {
//		this.initialType = initialType;
//	}

	public int getSparkguardauto() {
		return sparkguardauto;
	}

	public void setSparkguardauto(int sparkguardauto) {
		this.sparkguardauto = sparkguardauto;
	}

	public int getSparkhitauto() {
		return sparkhitauto;
	}

	public void setSparkhitauto(int sparkhitauto) {
		this.sparkhitauto = sparkhitauto;
	}
	public int getOwnpal() {
		return ownpal;
	}
	public void setOwnpal(int ownpal) {
		this.ownpal = ownpal;
	}
	public long getLastTimeBlockBySomething() {
		return lastTimeBlockBySomething;
	}
	public void setLastTimeBlockBySomething(long lastTimeBlockBySomething) {
		this.lastTimeBlockBySomething = lastTimeBlockBySomething;
	}
	public long getLastTimeHitSomething() {
		return lastTimeHitSomething;
	}
	public void setLastTimeHitSomething(long lastTimeHitSomething) {
		this.lastTimeHitSomething = lastTimeHitSomething;
	}
	
	public boolean isHitAt(long time) {
		return lastTimeHitSomething == time && lastTimeBlockBySomething < lastTimeHitSomething;
	}
	public boolean isContactAt(long time) {
		return lastTimeHitSomething == time;
	}
	public boolean isBlockedAt(long time) {
		return lastTimeHitSomething == time && lastTimeBlockBySomething == lastTimeHitSomething;
	}
	
	public boolean isHitAt(long time, long delta) {
		return lastTimeHitSomething == time && lastTimeBlockBySomething < lastTimeHitSomething;
	}
	public boolean isContactAt(long time, long delta) {
		return lastTimeHitSomething == time;
	}
	public boolean isBlockedAt(long time, long delta) {
		return lastTimeHitSomething == time && lastTimeBlockBySomething == lastTimeHitSomething;
	}
	public org.lee.mugen.sprite.character.SpriteCns.Type getSprHittedTypeWhenHit() {
		return sprHittedTypeWhenHit;
	}
	public void setSprHittedTypeWhenHit(
			org.lee.mugen.sprite.character.SpriteCns.Type sprHittedTypeWhenHit) {
		this.sprHittedTypeWhenHit = sprHittedTypeWhenHit;
	}
	public long getTimeCreated() {
		return timeCreated;
	}
	public void setTimeCreated(long timeCreated) {
		this.timeCreated = timeCreated;
	}


	
}
