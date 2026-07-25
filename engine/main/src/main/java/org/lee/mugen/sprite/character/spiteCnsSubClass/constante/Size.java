package org.lee.mugen.sprite.character.spiteCnsSubClass.constante;

import java.io.Serializable;

import org.lee.mugen.sprite.entity.PointF;


public class Size implements Cloneable, Serializable {
	public static class Air implements Cloneable, Serializable {
		float back = 12; // Player width (back, air)
		float front = 12; // Player width (front, air)
		@Override
		protected Object clone() throws CloneNotSupportedException {
			// TODO Auto-generated method stub
			return super.clone();
		}
		public float getBack() {
			return back;
		}
		public void setBack(float back) {
			this.back = back;
		}
		public float getFront() {
			return front;
		}
		public void setFront(float front) {
			this.front = front;
		}
	}
	public static class Ground implements Cloneable, Serializable {
		public Ground() {
			back = 15; // Player width (back, ground)
			front = 16; // Player width (front, ground)
		}
		public Ground(float b, float f) {
			back = b; // Player width (back, ground)
			front = f; // Player width (front, ground)
		}
		float back; // Player width (back, ground)
		float front; // Player width (front, ground)
		@Override
		public Object clone() throws CloneNotSupportedException {
			return super.clone();
		}
		public float getBack() {
			return back;
		}
		public void setBack(float back) {
			this.back = back;
		}
		public float getFront() {
			return front;
		}
		public void setFront(float front) {
			this.front = front;
		}
		
	}
	public static class Attack implements Cloneable, Serializable {
		@Override
		protected Object clone() throws CloneNotSupportedException {
			return super.clone();
		}
		float dist = 160; // Default attack distance
		float[] width = new float[2];
		public float getDist() {
			return dist;
		}

		public void setDist(float dist) {
			this.dist = dist;
		}
		public float[] getWidth() {
			return width;
		}
		public void setWidth(float[] width) {
			this.width[0] = width.length > 0? width[0]: this.width[0];
			this.width[1] = width.length > 1? width[1]: this.width[1];
		}
	}
	public static class Proj implements Cloneable, Serializable {
		float doscale = 0; // Set to 1 to scale projectiles too
		Attack attack = new Attack(); // Default attack distance for projectiles
		
		public float getDoscale() {
			return doscale;
		}
		public void setDoscale(float doscale) {
			this.doscale = doscale;
		}
		public Attack getAttack() {
			return attack;
		}
		
		@Override
		protected Object clone() throws CloneNotSupportedException {
			Proj proj = (Proj) super.clone();
			proj.attack = (Attack) attack.clone();
			return proj;
		}
	}
	
	public static class Position implements Cloneable, Serializable {
		public Position(PointF p) {
			pos = p;
		}
		@Override
		protected Object clone() throws CloneNotSupportedException {
			Position position = (Position) super.clone();
			position.setPos(new PointF(pos));
			return position;
		}
		public Position() {
			pos = new PointF();
		}
		public Position(float x, float y) {
			pos = new PointF();
			pos.setLocation(x, y);
		}
		PointF pos; // Approximate position
		public PointF getPos() {
			return pos;
		}
		public void setPos(PointF pos) {
			this.pos = pos;
		}
		protected void setUtil(PointF p, float...values) {
			float x = p.getX();
			float y = p.getX();
			int size = values.length;
			p.setLocation(size >= 1? values[0]: x, size >= 2? values[1]: y);
		}
		public void setPos(float...values) {
			setUtil(pos, values);
		}
		public void setPos(Object...values) {
			float[] vs = new float[values.length];
			int i = 0;
			for (Object o: values)
				vs[i++] = (Float)o;
			setUtil(pos, vs);
		}
		public void setPos(Float...values) {
			float[] vs = new float[values.length];
			int i = 0;
			for (Float o: values)
				vs[i++] = o;
			setUtil(pos, vs);
		}
		
	}
	public static class OffSet implements Cloneable, Serializable {
		@Override
		protected Object clone() throws CloneNotSupportedException {
			OffSet offSet = (OffSet) super.clone();
			offSet.offset = new PointF(offset);
			return offSet;
		}
		public OffSet(PointF p) {
			offset = p;
		}
		public PointF getOffset() {
			return offset;
		}
		public OffSet() {
			offset = new PointF();
		}
		public OffSet(float x, float y) {
			offset = new PointF();
			offset.setLocation(x, y);
		}
		PointF offset; // Approximate position
		protected void setUtil(PointF p, float...values) {
			float x = p.getX();
			float y = p.getX();
			int size = values.length;
			p.setLocation(size >= 1? values[0]: x, size >= 2? values[1]: y);
		}

		public void setOffset(float...values) {
			setUtil(offset, values);
		}
		public void setOffset(Object...values) {
			float[] vs = new float[values.length];
			int i = 0;
			for (Object o: values)
				vs[i++] = (Float)o;
			setUtil(offset, vs);

		}

	}
	public static class Mid implements Cloneable, Serializable {
		Position mid = new Position(-5,-60); // Approximate position of midsection
		@Override
		protected Object clone() throws CloneNotSupportedException {
			Mid m = (Mid) super.clone();
			m.mid = (Position) mid.clone();
			return m;
		}
	}	
	public static class Draw implements Cloneable, Serializable {
		OffSet draw = new OffSet(0,0); // Player drawing offset

		public OffSet getDraw() {
			return draw;
		}
		@Override
		protected Object clone() throws CloneNotSupportedException {
			// TODO Auto-generated method stub
			Draw dr =(Draw)  super.clone();
			dr.draw = (OffSet) draw.clone();
			return dr;
		}
	}	
	
	private float xscale = 1.f; // Horizontal scaling factor.
	private float yscale = 1.f; // Vertical scaling factor.
	private float height = 60; // Height of player (for opponent to jump over)
	private float shadowoffset = 0; // Number of pixels to vertically offset the shadow
	
	private Ground ground = new Ground(); // Player width (back, ground)
	private Air air = new Air(); // Player width (back, air)
	private Attack attack = new Attack(); // Default attack distance
	private Proj proj = new Proj(); // Default attack distance for projectiles
	private Position head = new Position(-5, -90); // Approximate position of head
	private Position mid = new Position(-5, -60); // Approximate position of midsection
	private OffSet draw = new OffSet(0, 0); // Player drawing offset in pixels (x, y)
	
	private Stand stand = new Stand();
	private Z z = new Z();
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Size s = (Size) super.clone();

		s.ground = (Ground) ground.clone();
		s.air = (Air) air.clone();
		s.attack = (Attack) attack.clone();
		s.proj = (Proj) proj.clone();
		s.head = (Position) head.clone();
		s.mid = (Position) mid.clone();
		s.draw = (OffSet) draw.clone();
		
		s.stand = (Stand) stand.clone();
		s.z = (Z) z.clone();
		
		return s;
	}
	
	public class Stand implements Cloneable, Serializable {
		@Override
		protected Object clone() throws CloneNotSupportedException {
			return super.clone();
		}
		public float getHeight() {
			return height;
		}
		public void setHeight(float height) {
			Size.this.height = height;
		}
	}
	public class Z implements Cloneable, Serializable {
		@Override
		protected Object clone() throws CloneNotSupportedException {
			// TODO Auto-generated method stub
			return super.clone();
		}
		private float width;
		public float getWidth() {
			return width;
		}
		public void setWidth(float width) {
			this.width = width;
		}
	}
	//
	
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getShadowoffset() {
		return shadowoffset;
	}
	public void setShadowoffset(float shadowoffset) {
		this.shadowoffset = shadowoffset;
	}
	public float getXscale() {
		return xscale;
	}
	public void setXscale(float xscale) {
		this.xscale = xscale;
	}
	public float getYscale() {
		return yscale;
	}
	public void setYscale(float yscale) {
		this.yscale = yscale;
	}
	public Air getAir() {
		return air;
	}
	public Attack getAttack() {
		return attack;
	}
	public OffSet getDraw() {
		return draw;
	}
	public Ground getGround() {
		return ground;
	}
	public Position getHead() {
		return head;
	}
	public Position getMid() {
		return mid;
	}
	public Proj getProj() {
		return proj;
	}
	public Stand getStand() {
		return stand;
	}
	public Z getZ() {
		return z;
	}

	
}
