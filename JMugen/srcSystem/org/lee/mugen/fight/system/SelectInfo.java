package org.lee.mugen.fight.system;

import org.lee.mugen.geom.MugenPoint;

import org.lee.mugen.fight.intro.entity.Fade;
import org.lee.mugen.fight.section.Section;
import org.lee.mugen.fight.section.elem.Type;
import org.lee.mugen.fight.system.elem.Cell;
import org.lee.mugen.fight.system.elem.PlayerSelectInfo;
import org.lee.mugen.fight.system.elem.Stage;
import org.lee.mugen.fight.system.elem.StageDisplay;
import org.lee.mugen.util.BeanTools;

public class SelectInfo implements Section {

	public StageDisplay getStagedisplay() {
		return stagedisplay;
	}



	private Fade fadein;
	private Fade fadeout;
	int rows;
	int columns;
	int wrapping;
	MugenPoint pos;
	boolean showemptyboxes;
	boolean moveoveremptyboxes;
	Cell cell;
	PlayerSelectInfo p1;
	PlayerSelectInfo p2;
	boolean random$move$snd$cancel;
	Stage stage;
	StageDisplay stagedisplay;
	Type cancel;
	Type portrait;
	Type title;
	int teammenu$move$wrapping;
	
	
	
	@Override
	public void parse(Object root, String name, String value) throws Exception {
		if (name.startsWith("fadein.")) {
			if (fadein == null)
				fadein = new Fade();
			fadein.parse(Type.getNext(name), value);
		} else if (name.startsWith("fadeout.")) {
			if (fadeout == null)
				fadeout = new Fade();
			fadeout.parse(Type.getNext(name), value);
		} else if (name.equals("rows")) {
			rows = Integer.parseInt(value);
		} else if (name.equals("columns")) {
			columns = Integer.parseInt(value);
		} else if (name.equals("wrapping")) {
			wrapping = Integer.parseInt(value);
		} else if (name.equals("pos")) {
			pos = (MugenPoint) BeanTools.getConvertersMap().get(MugenPoint.class).convert(value);
		} else if (name.equals("showemptyboxes")) {
			showemptyboxes = Integer.parseInt(value) != 0;
		} else if (name.equals("moveoveremptyboxes")) {
			moveoveremptyboxes = Integer.parseInt(value) != 0;
		} else if (name.startsWith("cell.")) {
			if (cell == null)
				cell = new Cell();
			cell.parse(root, Type.getNext(name), value);
		} else if (name.startsWith("p1.")) {
			if (p1 == null)
				p1 = new PlayerSelectInfo();
			p1.parse(root, Type.getNext(name), value);
		} else if (name.startsWith("p2.")) {
			if (p2 == null)
				p2 = new PlayerSelectInfo();
			p2.parse(root, Type.getNext(name), value);
		} else if (name.equals("random.move.snd.cancel")) {
			random$move$snd$cancel = Integer.parseInt(value) != 0;
		} else if (name.startsWith("stage.")) {
			if (stage == null)
				stage = new Stage();
			stage.parse(root, Type.getNext(name), value);
		}  else if (name.startsWith("stagedisplay.")) {
			if (stagedisplay == null)
				stagedisplay = new StageDisplay();
			stagedisplay.parse(root, Type.getNext(name), value);
		} else if (name.startsWith("cancel")) {
			if (cancel == null)
				cancel = new Type();
			cancel.setType(Type.getNext(name), cancel, value, root);
			cancel.parse(Type.getNext(name), value);
		} else if (name.startsWith("portrait")) {
			if (cancel == null)
				cancel = new Type();
			cancel.setType(Type.getNext(name), cancel, value, root);
			cancel.parse(Type.getNext(name), value);	
		} else if (name.startsWith("title")) {
			if (title == null)
				title = new Type();
			title.setType(Type.getNext(name), title, value, root);
			title.parse(Type.getNext(name), value);
		} else if (name.equals("teammenu.move.wrapping")) {
			teammenu$move$wrapping = Integer.parseInt(value);
		}
	}



	public Fade getFadein() {
		return fadein;
	}



	public void setFadein(Fade fadein) {
		this.fadein = fadein;
	}



	public Fade getFadeout() {
		return fadeout;
	}



	public void setFadeout(Fade fadeout) {
		this.fadeout = fadeout;
	}



	public int getRows() {
		return rows;
	}



	public void setRows(int rows) {
		this.rows = rows;
	}



	public int getColumns() {
		return columns;
	}



	public void setColumns(int columns) {
		this.columns = columns;
	}



	public int getWrapping() {
		return wrapping;
	}



	public void setWrapping(int wrapping) {
		this.wrapping = wrapping;
	}



	public MugenPoint getPos() {
		return pos;
	}



	public void setPos(MugenPoint pos) {
		this.pos = pos;
	}



	public boolean isShowemptyboxes() {
		return showemptyboxes;
	}



	public void setShowemptyboxes(boolean showemptyboxes) {
		this.showemptyboxes = showemptyboxes;
	}



	public boolean isMoveoveremptyboxes() {
		return moveoveremptyboxes;
	}



	public void setMoveoveremptyboxes(boolean moveoveremptyboxes) {
		this.moveoveremptyboxes = moveoveremptyboxes;
	}



	public Cell getCell() {
		return cell;
	}



	public void setCell(Cell cell) {
		this.cell = cell;
	}



	public PlayerSelectInfo getP1() {
		return p1;
	}



	public void setP1(PlayerSelectInfo p1) {
		this.p1 = p1;
	}



	public PlayerSelectInfo getP2() {
		return p2;
	}



	public void setP2(PlayerSelectInfo p2) {
		this.p2 = p2;
	}



	public boolean isRandom$move$snd$cancel() {
		return random$move$snd$cancel;
	}



	public void setRandom$move$snd$cancel(boolean random$move$snd$cancel) {
		this.random$move$snd$cancel = random$move$snd$cancel;
	}



	public Stage getStage() {
		return stage;
	}



	public void setStage(Stage stage) {
		this.stage = stage;
	}



	public Type getCancel() {
		return cancel;
	}



	public void setCancel(Type cancel) {
		this.cancel = cancel;
	}



	public Type getPortrait() {
		return portrait;
	}



	public void setPortrait(Type portrait) {
		this.portrait = portrait;
	}



	public Type getTitle() {
		return title;
	}



	public void setTitle(Type title) {
		this.title = title;
	}



	public int getTeammenu$move$wrapping() {
		return teammenu$move$wrapping;
	}



	public void setTeammenu$move$wrapping(int teammenu$move$wrapping) {
		this.teammenu$move$wrapping = teammenu$move$wrapping;
	}
	

}
