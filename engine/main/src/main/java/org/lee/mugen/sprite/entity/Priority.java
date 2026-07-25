package org.lee.mugen.sprite.entity;

import java.io.Serializable;


public class Priority implements Serializable {
	private int hit_prior;
	private HitType hit_type;
	
	public static enum HitType {
		DODGE, HIT, MISS;
	}

	public int getHit_prior() {
		return hit_prior;
	}

	public void setHit_prior(int hit_prior) {
		this.hit_prior = hit_prior;
	}

	public HitType getHit_type() {
		return hit_type;
	}

	public void setHit_type(HitType hit_type) {
		this.hit_type = hit_type;
	}
}