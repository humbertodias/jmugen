package org.lee.mugen.sprite.character.spiteCnsSubClass;

import org.lee.mugen.sprite.entity.CoupleOfAttrTypeAndLevel;

public class ReversaldefSub extends HitDefSub {
	public static class ReversalAttrClass {
//		private int type = 0;
//		private List<CoupleOfAttrTypeAndLevel> couples = new ArrayList<CoupleOfAttrTypeAndLevel>();
//
//
//		public boolean containsType(int type) {
//			return (type & this.type) != 0;
//		}
//
//		public void addType(org.lee.mugen.sprite.character.SpriteCns.Type type) {
//			this.type = this.type | type.getBit();
//		}
//
//		public void addAttrTypeAndLevel(AttrType attrType, AttrLevel attrLevel) {
//			couples.add(new CoupleOfAttrTypeAndLevel(attrType, attrLevel));
//		}
//
//		public boolean isAttrTypeAndLevel(AttrType attrType, AttrLevel attrLevel) {
//			for (CoupleOfAttrTypeAndLevel c : couples) {
//				if ((c.getAttrLevel() == attrLevel || c.getAttrLevel() == null || attrLevel == null)
//						&& (c.getAttrType() == attrType || c.getAttrType() == null || attrType == null)
//						)
//					return true;
//			}
//			return false;
//		}
//
//		public boolean isAttrType(AttrType attrType) {
//			for (CoupleOfAttrTypeAndLevel c : couples) {
//				if (c.getAttrType() == attrType)
//					return true;
//			}
//			return false;
//		}
//
//		public boolean isAttrLevel(AttrType attrType) {
//			for (CoupleOfAttrTypeAndLevel c : couples) {
//				if (c.getAttrType() == attrType)
//					return true;
//			}
//			return false;
//		}
//
//		public Collection<CoupleOfAttrTypeAndLevel> getCouples() {
//			return couples;
//		}

		private AttrClass attr = new AttrClass();

		public AttrClass getAttr() {
			return attr;
		}

		public void setAttr(AttrClass attr) {
			this.attr = attr;
		}
	}

	private ReversalAttrClass reversal = new ReversalAttrClass();

	public ReversalAttrClass getReversal() {
		return reversal;
	}

	public void setReversal(ReversalAttrClass reversal) {
		this.reversal = reversal;
	}

	public boolean canBlockAttack(HitDefSub otherHitdef) {
		if (getAttr().containsType(otherHitdef.getAttr().getType())) {
			if (otherHitdef.getAttr().getCouples().size() == 0)
				return true;
			for (CoupleOfAttrTypeAndLevel couple: otherHitdef.getAttr().getCouples()) {
				if (getAttr().isAttrTypeAndLevel(couple.getAttrType(), couple.getAttrLevel()))
					return true;
			}
		}
		
		return false; 
	}

}
