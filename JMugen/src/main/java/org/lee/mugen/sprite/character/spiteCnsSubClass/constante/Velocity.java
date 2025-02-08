package org.lee.mugen.sprite.character.spiteCnsSubClass.constante;

import java.io.Serializable;

import org.lee.mugen.sprite.entity.PointF;


public class Velocity implements Cloneable, Serializable {

	
	public static class Move implements Cloneable, Serializable {
		private PointF fwd = new PointF(0,0);
		private PointF back = new PointF(0,0);
		@Override
		protected Object clone() throws CloneNotSupportedException {
			Move m = (Move) super.clone();
			m.fwd = (PointF) fwd.clone();
			m.back = (PointF) back.clone();
			
			return m;
		}
		public Move() {}
		public void setBack(PointF back) {
			this.back = back;
		}
		public void setFwd(PointF fwd) {
			this.fwd = fwd;
		}
		protected void setPos(PointF p, float...values) {
			float x = p.getX();
			float y = p.getY();
			int size = values.length;
			p.setLocation(size >= 1? values[0]: x, size >= 2? values[1]: y);
		}
		public PointF getBack() {
			return back;
		}
		public PointF getFwd() {
			return fwd;
		}
		public void setFwd(float value) {
			setPos(fwd, value, 0);
		}
	
		public void setFwd(float...values) {
			setPos(fwd, values);
		}

		public void setBack(float...values) {
			setPos(back, values);
		}
		public void setBack(float value) {
			setPos(back, value, 0);
		}
		public void setFwd(Object...values) {
			float[] vs = new float[values.length];
			int i = 0;
			for (Object o: values)
				vs[i++] = (Float)o;
			setPos(fwd, vs);
		}
		public void setFwd(Object value) {
			setFwd(value, 0);
		}
		public void setBack(Object value) {
			setBack(value, 0);
		}
		public void setBack(Object...values) {
			float[] vs = new float[values.length];
			int i = 0;
			for (Object o: values)
				vs[i++] = (Float)o;
			setPos(back, vs);
		}

	}
	public static class JumpMove extends Move implements Cloneable {
		private PointF neu = new PointF();
		@Override
		protected Object clone() throws CloneNotSupportedException {
			JumpMove jm = (JumpMove) super.clone();
			jm.neu = (PointF) neu.clone();
			return jm;
		}
		public JumpMove() {}
		public void setNeu(PointF neu) {
			this.neu = neu;
		}
		public PointF getNeu() {
			return neu;
		}
		public void setNeu(float...values) {
			setPos(neu, values);
		}
		public void setNeu(float value) {
			setPos(neu, value, 0);
		}
		public void setNeu(Object value) {
			setNeu(value, 0);
		}
		public void setNeu(Object...values) {
			float[] vs = new float[values.length];
			int i = 0;
			for (Object o: values)
				vs[i++] = (Float)o;
			setNeu(vs);
		}
		public float getX() {
			return neu.getX();
		}
		public float getY() {
			return neu.getY();
		}
	}
	
	private Move walk = new Move();
	private Move run = new Move(); // Run forward (x, y)
	private JumpMove jump = new JumpMove(); // Neutral jumping velocity (x, y)
	private JumpMove runjump = new JumpMove();
	private JumpMove airjump = new JumpMove();
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Velocity v = (Velocity) super.clone();
		v.walk = (Move) walk.clone();
		v.run = (Move) run.clone();
		
		v.jump = (JumpMove) jump.clone();
		v.runjump = (JumpMove) runjump.clone();
		v.airjump = (JumpMove) airjump.clone();
		return v;
	}
	
	public JumpMove getAirjump() {
		return airjump;
	}
	public JumpMove getJump() {
		return jump;
	}
	public Move getRun() {
		return run;
	}
	public JumpMove getRunjump() {
		return runjump;
	}
	public Move getWalk() {
		return walk;
	}

	
}
