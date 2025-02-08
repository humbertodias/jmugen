package org.lee.mugen.sprite.entity;

import java.awt.Point;

import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.spiteCnsSubClass.constante.Size;

public class HelperSub {
//	Paramètres optionnels :
//	helpertype = type_string
//	    (déconseillé -- n'utilisez pas d'helpers de type player)
//	    Si helpertype = normal, alors l'helper sera autorisé à bouger en dehors des bords de l'écran. De plus, la caméra ne bougera pas pour tenter de garder l'helper à l'écran. Si helpertype = player, alors l'helper sera confiné à l'écran et sera suivi par la caméra, comme un joueur normal. Si vous voulez utiliser un helper pour une manipulation de caméra, n'utilisez pas d'helper de type player (ils sont déconseillés). Utilisez le controller "ScreenBound" avec le paramètre "movecamera" si besoin.
//
//	name = "name_string"
//	    Spécifie le nom de cet helper, qui doit être entre guillemets. S'il est omis, le nom par défaut sera "<parent>'s helper" (helper de <parent>), où <parent> représente le nom du joueur créant l'helper.
//
//	ID = id_no (entier)
//	    Donne un numéro ID pour pouvoir se référer à cet helper. Valeur défaut : 0.
//
//	pos = x_off, y_off (entier)
//	    Détermine les offsets x et y où l'helper est créé. La signification exacte de ces paramètres dépend du postype. Par défaut : 0,0.
//
//	postype = postype_string
//	    postype fonctionne à peu près de la même façon que pour le controller Explod. postype_string spécifie le postype -- comment doivent être interprétés les paramètres pos. Dans tous les cas, un offset y positif implique un déplacement vers le bas. Les valeurs valides pour postype sont les suivantes :
//	- p1 : Interprète le pos relativement à l'axe de P1. Un offset x positif est placé devant P1. C'est la valeur défaut pour postype.
//	- p2 : Interprète le pos relativement à l'axe de P2. Un offset x positif est placé devant P2. Si P2 n'existe pas, l'helper sera créé par rapport à P1.
//	- front : Interprète le pos x relativement au bord de l'écran auquel P1 fait face, et le pos y relativement à l'axe de P1. Un offset x positif s'éloigne du centre de l'écran, alors qu'un offset x négatif se rapproche du centre de l'écran.
//	- back : Interprète le pos x relativement au bord de l'écran auquel P1 fait face, et le pos y relativement à l'axe de P1. Un offset x positif se rapproche du centre de l'écran, alors qu'un offset x négatif s'éloigne du centre de l'écran.
//	- left : Interprète le pos x relativement au bord gauche de l'écran, et le pos y relativement à l'axe de P1. Les offsets x positifs vont vers la droite de l'écran.
//	- right : Interprète le pos x relativement au bord droit de l'écran, et le pos y relativement à l'axe de P1. Les offsets x positifs vont vers la gauche de l'écran.
//
//	facing = facing (entier)
//	    Si le postype est left ou right, mettre un facing à 1 fera que l'helper regardera vers la droite, et une valeur de -1 fera que l'helper regardera vers la gauche. Pour tous les autres valeurs de postype sauf p2, si facing vaut 1, l'helper regardera dans la même direction que le joueur. Si facing vaut -1, l'helper regardera dans la direction opposée. Dans le cas où postype = p2, facing a le même effet que ci-dessus, sauf que cela fonctionnera par rapport au facing de P2. Valeur défaut : 1.
//
//	stateno = start_state (entier)
//	    Détermine le numéro du state auquel démarre l'helper (0 par  défaut).
//
//	keyctrl = ctrl_flag (boolean)
//	    Si keyctrl = 1, alors l'helper est capable d'analyser les entrées de commande du joueur (ex. : le clavier ou le joystick). De même, l'helper héritera des State -1 de sa racine. Si keyctrl = 0, alors l'helper n'aura pas accès aux entrées de commande et n'héritera pas des State -1. La valeur défaut de keyctrl est 0.
//
//	ownpal = pal_flag (boolean)
//	    Si ownpal = 0, l'helper partagera sa palette en cours avec celle de son parent. Ainsi, si la palette du parent est temporairement modifiée (ex. : par un controller PalFX), alors celle de l'helper le sera également, et vice versa. Si ownpal = 1, l'helper aura sa propre palette, indépendamment de celle de son parent. Valeur défaut : 0.
//
//	supermovetime = value (entier)
//	    Détermine le nombre de ticks durant lesquels l'helper doit être "débloqué" durant une SuperPause. Souvent utile si vous voulez que l'helper fasse son apparition durant une SuperPause. Par défaut : 0.
//
//	pausemovetime = move_time (entier)
//	    Détermine le nombre de ticks durant lesquels l'helper doit être "débloqué" durant une Pause. Par défaut : 0.
//
//	size.xscale (flottant)
//	size.yscale (flottant)
//	size.ground.back (entier)
//	size.ground.front (entier)
//	size.air.back (entier)
//	size.air.front (entier)
//	size.height (entier)
//	size.proj.doscale (entier)
//	size.head.pos (entier,entier)
//	size.mid.pos (entier,entier)
//	size.shadowoffset (entier)

	public static enum HELPERTYPE {
		NORMAL, PLAYER;
	}
	private String helpertype = "normal";
	private String name;
	private int id;
	private Point pos = new Point();
	private Postype postype = Postype.p1;
	private int facing;
	private int stateno;
	private boolean keyctrl;
	private boolean ownpal;
	private int supermovetime;
	private int pausemovetime;
	private int bindtime;
	public int getBindtime() {
		return bindtime;
	}
	public void setBindtime(int bindtime) {
		this.bindtime = bindtime;
	}
	private Size size = new Size();
	
	private Sprite spriteFrom;
	
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}
	public int getFacing() {
		return facing;
	}
	public void setFacing(int facing) {
		this.facing = facing;
	}
	public String getHelpertype() {
		return helpertype;
	}
	public void setHelpertype(String helpertype) {
		this.helpertype = helpertype;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isKeyctrl() {
		return keyctrl;
	}
	public void setKeyctrl(boolean keyctrl) {
		this.keyctrl = keyctrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isOwnpal() {
		return ownpal;
	}
	public void setOwnpal(boolean ownpal) {
		this.ownpal = ownpal;
	}
	public int getPausemovetime() {
		return pausemovetime;
	}
	public void setPausemovetime(int pausemovetime) {
		this.pausemovetime = pausemovetime;
	}
	public Point getPos() {
		return pos;
	}
	public void setPos(Point pos) {
		this.pos = pos;
	}
	public Postype getPostype() {
		return postype;
	}
	public void setPostype(Postype postype) {
		this.postype = postype;
	}
	public int getStateno() {
		return stateno;
	}
	public void setStateno(int stateno) {
		this.stateno = stateno;
	}
	public int getSupermovetime() {
		return supermovetime;
	}
	public void setSupermovetime(int supermovetime) {
		this.supermovetime = supermovetime;
	}
	public Sprite getSpriteFrom() {
		return spriteFrom;
	}
	public void setSpriteFrom(Sprite spriteFrom) {
		this.spriteFrom = spriteFrom;
	}

//	size.xscale (flottant)
//	size.yscale (flottant)
//	size.ground.back (entier)
//	size.ground.front (entier)
//	size.air.back (entier)
//	size.air.front (entier)
//	size.height (entier)
//	size.proj.doscale (entier)
//	size.head.pos (entier,entier)
//	size.mid.pos (entier,entier)
//	size.shadowoffset (entier)


}
