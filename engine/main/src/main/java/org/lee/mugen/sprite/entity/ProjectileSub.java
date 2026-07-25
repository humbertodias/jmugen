package org.lee.mugen.sprite.entity;

import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub;

public class ProjectileSub extends HitDefSub {
	// /////////////////////////
	private float x;

	private float y;

	long lastTimeCancelByProjectile = -1;

	private Sprite spriteParent;
	public ProjectileSub(Sprite spriteParent) {
		this.spriteParent = spriteParent;
	}
	
	@Override
	public ProjectileSprite getSpriteHitter() {
		return (ProjectileSprite) super.getSpriteHitter();
	};

	public void setSpriteHitter(ProjectileSprite spriteHitter) {
		super.setSpriteHitter(spriteHitter);
	};

	// ///////////////////////////
	/*
	 * ProjID = id_no (entier) Indique un numéro ID pour pouvoir se référer à ce
	 * projectile. Doit être positif, si spécifié.
	 */
	private Integer projid;

	/*
	 * projanim = anim_no (entier) Indique le numéro d'action à utiliser pour
	 * l'animation du projectile. 0 par défaut si omis.
	 */
	private int projanim;

	/*
	 * projhitanim = anim_no (entier) Indique le numéro d'action de l'animation
	 * à jouer quand le projectile touche l'adversaire. -1 par défaut si omis
	 * (pas de changement dans l'animation.
	 */
	private int projhitanim = -1;

	/*
	 * projremanim = anim_no (entier) Indique le numéro d'action de l'animation
	 * à jouer quand le projectile est enlevé (arrivé à expiration, ou ayant
	 * atteint ses limites d'affichage, etc.). Si omis, projhitanim est utilisé
	 * à la place.
	 */
	private int projremanim = -1;

	/*
	 * projcancelanim = anim_no (entier) Indique le numéro d'action à jouer
	 * quand le projectile est annulé en touchat un autre projectile. Si omis,
	 * projremanim est utilisé à la place.
	 */
	private int projcancelanim = -1;

	/*
	 * projscale = x_scale, y_scale (flottant) Indique les facteurs de scale
	 * pour le projectile. Le scale final du projectile est affecté à la fois
	 * par ces paramètres et par le paramètre "proj.doscale" dans le groupe
	 * [Size] du fichier de constantes de P1. 1,1 par défaut (taille normale) si
	 * omis.
	 */
	private PointF projscale = new PointF(1, 1);

	/*
	 * projremove = remove_flag (entier) Mettez une valeur différente de zéro
	 * pour que le projectile soit effacé après avoir touché, ou 0 pour
	 * désactiver ce comportement. 1 par défaut.
	 */
	private int projremove = 1;

	/*
	 * projremovetime = remove_time (entier) Indique le nombre de tick après
	 * lequel le projectile doit être effacé de l'écran. -1 par défaut (aucune
	 * limite de temps).
	 */
	private int projremovetime = -1;

	/*
	 * velocity = x_vel, y_vel (flottant) Indique les vitesses initiales en x et
	 * y auxquelles le projectile doit se déplacer. 0,0 par défaut si omis.
	 */
	private Velocity velocity = new Velocity();

	/*
	 * remvelocity = x_vel, y_vel (flottant) Indique les vitesses x et y
	 * auxquelles le projectile doit se déplacer alors qu'il est effacé. 0,0 par
	 * défaut si omis.
	 */
	private Velocity remvelocity = new Velocity();

	/*
	 * accel = x_accel, y_accel (flottant) Indique l'accélération à appliquer au
	 * projectile dans les directions x et y. 0,0 par défaut si omis.
	 */
	private Velocity accel = new Velocity();

	/*
	 * velmul = x_mul, y_mul (flottant) Indique les facteurs de vitesses x et y.
	 * La vitesse du projectile est multipliée par ces facteurs à chaque tick.
	 * Les facteurs ont une valeur défaut de 1 si omis.
	 */
	private Velocity velmul = new Velocity(1, 1);

	/*
	 * projhits = num_hits (entier) Indique le nombre de coups que le projectile
	 * est capable de donner avant d'être effacé. 1 par défaut.
	 */
	private int projhits = 1;

	/*
	 * projmisstime = miss_time (entier) Si le projectile fait des coups
	 * multiples, indique le nombre minimum de ticks qui doivent s'écouler entre
	 * les coups. 0 par défaut, mais la plupart du temps, vous aurez besoin
	 * d'une valeur différente de 0.
	 */
	private int projmisstime = 0;

	/*
	 * projpriority = proj_priority (entier) Indique la priorité du projectile.
	 * Si le projectile rencontre un autre projectile de priorité égale, ils
	 * s'annuleront. S'il entre en collision avec un projectile de priorité
	 * inférieure, le projectile de priorité inférieure sera annulé, et le
	 * projectile de priorité supérieure aura sa priorité réduite de 1. Valeur
	 * par défaut : 1.
	 */
	private int projpriority = 1;

	/*
	 * projsprpriority = priority (entier) Indique la priorité d'affichage du
	 * projectile; Les sprites de haute priorité sont dessinés par dessus les
	 * sprites de faible priorité. 3 par défaut.
	 */
	private int projsprpriority = 3;

	/*
	 * projedgebound = value (entier) C'est la distance (en pixels) au-delà du
	 * bord de l'écran que peut parcourir le projectile avant d'être effacé. 40
	 * par défaut.
	 */
	private int projedgebound = 40;

	/*
	 * projstagebound = value (entier) Indique la distance maximale que peut
	 * parcourir le projectile au-delà du bord du stage (pas de l'écran) avant
	 * d'être effacé. 40 par défaut.
	 */
	private int projstagebound = 40;

	/*
	 * projheightbound = lowbound, highbound (entier) Indique les valeurs y
	 * maximale et minimale que le projectile peut atteindre. Si le projectile
	 * sort de ces limites, il est effacé. NB : puisque les valeurs y
	 * décroissent en montant dans l'écran, lowbound indique actuellement la
	 * hauteur maximale que le projectile peut atteindre. Ces paramètres sont de
	 * -240,1 par défaut, si omis.
	 */
	private PointF projheightbound = new PointF(-240, 1);

	/*
	 * offset = off_x, off_y (entier) Indique les offsets x et y auxquels le
	 * projectile doit être créé. Ces deux paramètres ont une valeur défaut de
	 * 0. Le comportement exact des paramètres d'offsets dépend du postype.
	 */
	private PointF offset = new PointF(0, 0);

	/*
	 * postype = type_string type_string indique le postype -- comment
	 * interpréter les paramètres de pos. Dans tous les cas, un offset y positif
	 * indique un déplacement vers le bas. Les valeurs acceptées pour postype
	 * sont les suivantes : - p1 : interprète la position relativement à l'axe
	 * de P1. Un offset x positif est vers la direction dans laquelle regarde
	 * P1. C'est la valeur défaut pour postype. - p2 : interprète la position
	 * relativement à l'axe de P2. Un offset x positif est vers la direction
	 * dans laquelle regarde P2. - front : interprète la position x relativement
	 * au bord de l'écran auquel P1 fait face, et la position y relativement à
	 * l'axe y de P1. Un offset x positif s'éloigne du centre de l'écran alors
	 * qu'un offset x négatif s'en rapproche. - back : interprète la position x
	 * relativement au bord de l'écran auquel P1 est de dos, et ypos
	 * relativement à l'axe y de P1. Un offset x positif se rapproche du centre
	 * de l'écran alors qu'un offset x négatif s'en éloigne. - left : interprète
	 * la position x et la position y relativement au coin haut gauche de
	 * l'écran. Un offset x positif est vers la droite de l'écran. - right :
	 * interprète les positions x et y relativement au coin haut droit de
	 * l'écran. Un offset x positif est vers la droite de l'écran.
	 */

	private Postype postype = Postype.p1;

	/*
	 * projshadow = shad_r, shad_g, shad_b (entier) Indique les composants R, V,
	 * et B de l'ombre du projectile. Ces composants doivent être des entiers
	 * entre 0 et 255, inclusivement. Si shad_r vaut -1, alors la couleur de
	 * l'ombre du stage sera utilisée. Plus la valeur d'un composant est élevée,
	 * moins cette couleur apparaîtra dans l'ombre. Donc une ombre parfaitement
	 * noire est 255,255,255. Par défaut : 0,0,0 (pas d'ombre).
	 */
	private int shad_r, shad_g, shad_b;

	public void setProjshadow(int... params) {
		if (params.length > 0)
			shad_r = params[0];
		if (params.length > 1)
			shad_g = params[1];
		if (params.length > 2)
			shad_b = params[2];

	}

	/*
	 * supermovetime = move_time (entier) Détermine le nombre de ticks durant
	 * lesquels le projectile doit être "non gelé" pendant une SuperPause. 0 par
	 * défaut.
	 */
	private int supermovetime = 0;

	/*
	 * pausemovetime = move_time (entier) Détermine le nombre de ticks durant
	 * lesquels le projectile doit être "non gelé" pendant une Pause. 0 par
	 * défaut.
	 * 
	 */
	private int pausemovetime = 0;

	
	
	private int removeongethit = 0;
	public int getRemoveongethit() {
		return removeongethit;
	}

	public void setRemoveongethit(int removeongethit) {
		this.removeongethit = removeongethit;
	}

	// /////////////////////////////////////////
	public Velocity getAccel() {
		return accel;
	}

	public void setAccel(Velocity accel) {
		this.accel = accel;
	}

	public PointF getOffset() {
		return offset;
	}

	public void setOffset(PointF offset) {
		this.offset = offset;
	}

	public int getPausemovetime() {
		return pausemovetime;
	}

	public void setPausemovetime(int pausemovetime) {
		this.pausemovetime = pausemovetime;
	}

	public Postype getPostype() {
		return postype;
	}

	public void setPostype(Postype postype) {
		this.postype = (Postype) postype;
	}

	public int getProjanim() {
		return projanim;
	}

	public void setProjanim(int projanim) {
		this.projanim = projanim;
	}

	public int getProjcancelanim() {
		return projcancelanim;
	}

	public void setProjcancelanim(int projcancelanim) {
		this.projcancelanim = projcancelanim;
	}

	public int getProjedgebound() {
		return projedgebound;
	}

	public void setProjedgebound(int projedgebound) {
		this.projedgebound = projedgebound;
	}

	public PointF getProjheightbound() {
		return projheightbound;
	}

	public void setProjheightbound(PointF projheightbound) {
		this.projheightbound = projheightbound;
	}

	public int getProjhitanim() {
		return projhitanim;
	}

	public void setProjhitanim(int projhitanim) {
		this.projhitanim = projhitanim;
	}

	public int getProjhits() {
		return projhits;
	}

	public void setProjhits(int projhits) {
		this.projhits = projhits;
	}

	public Integer getProjid() {
		return projid;
	}

	public void setProjid(Integer projid) {
		this.projid = projid;
	}

	public int getProjmisstime() {
		return projmisstime;
	}

	public void setProjmisstime(int projmisstime) {
		this.projmisstime = projmisstime;
	}

	public int getProjpriority() {
		return projpriority;
	}

	public void setProjpriority(int projpriority) {
		this.projpriority = projpriority;
	}

	public int getProjremanim() {
		return projremanim;
	}

	public void setProjremanim(int projremanim) {
		this.projremanim = projremanim;
	}

	public int getProjremove() {
		return projremove;
	}

	public void setProjremove(int projremove) {
		this.projremove = projremove;
	}

	public int getProjremovetime() {
		return projremovetime;
	}

	public void setProjremovetime(int projremovetime) {
		this.projremovetime = projremovetime;
	}

	public PointF getProjscale() {
		return projscale;
	}

	public void setProjscale(PointF projscale) {
		this.projscale = projscale;
	}

	public int getProjsprpriority() {
		return projsprpriority;
	}

	public void setProjsprpriority(int projsprpriority) {
		this.projsprpriority = projsprpriority;
	}

	public int getProjstagebound() {
		return projstagebound;
	}

	public void setProjstagebound(int projstagebound) {
		this.projstagebound = projstagebound;
	}

	public Velocity getRemvelocity() {
		return remvelocity;
	}

	public void setRemvelocity(Velocity remvelocity) {
		this.remvelocity = remvelocity;
	}

	public int getShad_b() {
		return shad_b;
	}

	public void setShad_b(int shad_b) {
		this.shad_b = shad_b;
	}

	public int getShad_g() {
		return shad_g;
	}

	public void setShad_g(int shad_g) {
		this.shad_g = shad_g;
	}

	public int getShad_r() {
		return shad_r;
	}

	public void setShad_r(int shad_r) {
		this.shad_r = shad_r;
	}

	public int getSupermovetime() {
		return supermovetime;
	}

	public void setSupermovetime(int supermovetime) {
		this.supermovetime = supermovetime;
	}

	public Velocity getVelmul() {
		return velmul;
	}

	public void setVelmul(Velocity velmul) {
		this.velmul = velmul;
	}

	public Velocity getVelocity() {
		return velocity;
	}

	public void setVelocity(Velocity velocity) {
		this.velocity = velocity;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void addProjremanim(int i) {
		if (projremanim > 0)
			projremanim += i;
	}

	public void addProjremovetime(int i) {
		if (projremovetime > 0)
			projremovetime += i;
	}

	public void addHisVelocity(boolean isFlip) {
		getVelocity().setX(getVelocity().getX() + getAccel().getX());
		getVelocity().setY(getVelocity().getY() + getAccel().getY());
		
		getVelocity().setX(getVelocity().getX() * getVelmul().getX());
		getVelocity().setY(getVelocity().getY() * getVelmul().getY());
		
		x += (isFlip ? -getVelocity().getX() : getVelocity().getX());
		y += getVelocity().getY();
	}

	public void addHisRemoveVelocity(boolean isFlip) {
		x += isFlip ? -getRemvelocity().getX() : getRemvelocity().getX();
		y += getVelocity().getY();

	}

	public void addProjhits(int i) {
		projhits += i;
	}

	public Sprite getSpriteParent() {
		return spriteParent;
	}

	public long getLastTimeCancelByProjectile() {
		return lastTimeCancelByProjectile;
	}

	// TODO : set it in Fightengine
	public void setLastTimeCancelByProjectile(long lastTimeCancelByProjectile) {
		this.lastTimeCancelByProjectile = lastTimeCancelByProjectile;
	}


}
