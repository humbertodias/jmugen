package org.lee.mugen.fight.system.elem;

import org.lee.mugen.geom.MugenPoint;

import org.lee.mugen.fight.section.Section;
import org.lee.mugen.fight.section.elem.Type;
import org.lee.mugen.fight.system.MugenSystem;
import org.lee.mugen.object.Rectangle;
import org.lee.mugen.sprite.entity.SndGrpNum;
import org.lee.mugen.util.BeanTools;

public class Menu implements Section {
	MugenSystem ms;
	
	private MugenPoint pos;
	private Item item;
	private ItemName itemname;
	
	private MugenPoint window$margins$y;
	private int window$visibleitems;
	private int boxcursor$visible;
	private Rectangle boxcursor$coords;
	private SndGrpNum cursor$move$snd;
	private SndGrpNum cursor$done$snd;
	private SndGrpNum cancel$snd;
	
	
	@Override
	public void parse(Object root, String name, String value) throws Exception {
		ms = (MugenSystem) root;
		if (name.startsWith("item.")) {
			if (item == null)
				item = new Item(ms);

			item.setType(Type.getNext(name), item, value, ms);
			item.parse(Type.getNext(name), value);
		} else if (name.equals("pos")) {
			pos = (MugenPoint) BeanTools.getConvertersMap().get(MugenPoint.class).convert(value);
		} else if (name.startsWith("itemname")) {
			if (itemname == null)
				itemname = new ItemName();
			itemname.parse(root, Type.getNext(name), value);
		} else if (name.equals("window.margins.y")) {
			window$margins$y = (MugenPoint) BeanTools.getConvertersMap().get(MugenPoint.class).convert(value);
		} else if (name.equals("window.visibleitems")) {
			window$visibleitems = Integer.parseInt(value);
			
		} else if (name.equals("boxcursor.visible")) {
			boxcursor$visible = Integer.parseInt(value);
		} else if (name.equals("boxcursor.coords")) {
			int[] res = (int[]) BeanTools.getConvertersMap().get(int[].class).convert(value);
			boxcursor$coords = new Rectangle(res[0], res[1], res[2], res[3]);
			
		} else if (name.equals("cursor.move.snd")) {
			cursor$move$snd = (SndGrpNum) BeanTools.getConvertersMap().get(SndGrpNum.class).convert(value);
			
		} else if (name.equals("cursor.done.snd")) {
			cursor$done$snd = (SndGrpNum) BeanTools.getConvertersMap().get(SndGrpNum.class).convert(value);
			
		} else if (name.equals("cancel.snd")) {
			cancel$snd = (SndGrpNum) BeanTools.getConvertersMap().get(SndGrpNum.class).convert(value);
		}
	}
	public MugenPoint getPos() {
		return pos;
	}
	public void setPos(MugenPoint pos) {
		this.pos = pos;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public ItemName getItemname() {
		return itemname;
	}
	public void setItemname(ItemName itemname) {
		this.itemname = itemname;
	}
	public MugenPoint getWindow$margins$y() {
		return window$margins$y;
	}
	public void setWindow$margins$y(MugenPoint window$margins$y) {
		this.window$margins$y = window$margins$y;
	}
	public int getWindow$visibleitems() {
		return window$visibleitems;
	}
	public void setWindow$visibleitems(int window$visibleitems) {
		this.window$visibleitems = window$visibleitems;
	}
	public int getBoxcursor$visible() {
		return boxcursor$visible;
	}
	public void setBoxcursor$visible(int boxcursor$visible) {
		this.boxcursor$visible = boxcursor$visible;
	}
	public Rectangle getBoxcursor$coords() {
		return boxcursor$coords;
	}
	public void setBoxcursor$coords(Rectangle boxcursor$coords) {
		this.boxcursor$coords = boxcursor$coords;
	}
	public SndGrpNum getCursor$move$snd() {
		return cursor$move$snd;
	}
	public void setCursor$move$snd(SndGrpNum cursor$move$snd) {
		this.cursor$move$snd = cursor$move$snd;
	}
	public SndGrpNum getCursor$done$snd() {
		return cursor$done$snd;
	}
	public void setCursor$done$snd(SndGrpNum cursor$done$snd) {
		this.cursor$done$snd = cursor$done$snd;
	}
	public SndGrpNum getCancel$snd() {
		return cancel$snd;
	}
	public void setCancel$snd(SndGrpNum cancel$snd) {
		this.cancel$snd = cancel$snd;
	}
	
	
}
