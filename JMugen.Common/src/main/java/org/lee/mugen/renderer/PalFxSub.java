package org.lee.mugen.renderer;

import java.io.Serializable;

/*
time = durée (entier)
    Indique le nombre de tick que dureront les effets de palette. 
    Indiquer -1 pour que les effets de palettes durent indéfiniment.

add = add_r, add_g, add_b (entier)
mul = mul_r, mul_g, mul_b (entier)
    Chaque composant add est ajouté au composant approprié de 
    la palette du joueur, et le résultat est multiplié par 
    le composant mul approprié divisé par 256. 
    Par exemple, si pal_r est le composant rouge de la palette originale 
    du personnage, alors le nouveau composant rouge est 
    (pal_r + add_r)*mul_r/256. 
    Les valeurs défaut de ces paramètres sont : 
    add = 0,0,0 ; mul = 256,256,256 (pas de changement).

sinadd = ampl_r, ampl_g, ampl_b, period (entier)
    Crée un effet de "vague" sinusoïdale additionnel sur la palette. 
    Period indique la durée d'une "vague" en tick, 
    et les paramètres amplitude contrôlent l'amplitude 
    de la "vague" pour chaque composant respectif. 
    Par exemple, si t représente le nombre de ticks écoulés 
    depuis l'activation du controller PalFX et que pal_r est 
    le composant rouge de la palette originale du personnage, 
    alors le composant rouge de la palette du personnage au temps t est : 
    (pal_r + add_r + ampl_r*sin(2*pi*t/period))*mul_r/256.

invertall = bvalue (bool)
    Si bvalue est différent de 0, alors les couleurs de la palette sont 
    inversées, créant un effet "négatif". 
    L'inversion de couleur est appliquée avant les effets de add et mul. 
    0 par défaut.

color = value (entier)
    Ceci affecte le niveau de couleur de la palette. 
    Si value est 0, la palette sera en niveaux de gris. 
    Si la valeur est 256, 
    il n'y aura aucun changement dans la palette. 
    Les valeurs intermédiaires auront un effet intermédiaire. 
    Les effets de ce paramètre sont appliqués avant invertall, 
    add et mul. La valeur défaut est 256.

*/
public class PalFxSub implements Serializable {
	public void setDrawProperties(DrawProperties dp) {
		dp.setPalfx(this);
	}
	private int time = 0;
	private RGB mul = new RGB(256, 256, 256, 256);
	private RGB add = new RGB(0, 0, 0, 256);
	private Sinadd sinadd = new Sinadd();
	private int invertall = 0;
	private int color;
	
	private int timeActivate = 0;
	
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getInvertall() {
		return invertall;
	}
	public void setInvertall(int invertall) {
		this.invertall = invertall;
	}
	public void setSinadd(Sinadd sinadd) {
		this.sinadd = sinadd;

	}
	public Sinadd getSinadd() {
		return sinadd;
	}

	
	public static class Sinadd implements Serializable {
		private int ampl_r;
		private int ampl_g;
		private int ampl_b;
		private int period;
		
		public int getAmpl_r() {
			return ampl_r;
		}
		public void setAmpl_r(int p1) {
			this.ampl_r = p1;
		}
		public int getAmpl_g() {
			return ampl_g;
		}
		public void setAmpl_g(int p2) {
			this.ampl_g = p2;
		}
		public int getAmpl_b() {
			return ampl_b;
		}
		public void setAmpl_b(int p3) {
			this.ampl_b = p3;
		}
		public int getPeriod() {
			return period;
		}
		public void setPeriod(int p4) {
			this.period = p4;
		}
		public RGB getAmpl() {
			return new RGB(ampl_r, ampl_g, ampl_b, 256);
		}
		public void rezet() {
			ampl_r = 0;
			ampl_g = 0;
			ampl_b = 0;
		}
	}
	
	public RGB getAdd() {
		return add;
	}
	public RGB getMul() {
		return mul;
	}
	public int getTime() {
		return time;
	}
	public boolean isNoPalFx() {
		return time <= 0;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public void addTime(int i) {
		time += i;
	}	
	public int decreaseTime() {
//		if (time == 0)
//			return 0;
		if (time == -1)
			return -1;
		timeActivate++;
		return time--;
	}
	public void init() {
		//timeActivate = 0;
		time = 0;
		mul = new RGB(256, 256, 256, 256);
		add = new RGB(0, 0, 0, 256);
		sinadd.rezet();
		setInvertall(0);
		setColor(256);
	}
	public int getTimeActivate() {
		return timeActivate;
	}
	public void setAdd(RGB add) {
		this.add = add;
	}
	public void setMul(RGB mul) {
		this.mul = mul;
	}
	public void setTimeActivate(int timeActivate) {
		this.timeActivate = timeActivate;
	}

}
