package org.lee.mugen.util.debugger.component.cnsFile;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.StringReader;
import java.util.regex.Matcher;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;

import org.lee.mugen.core.debug.BreakPoint;
import org.lee.mugen.core.debug.BreakPosition;
import org.lee.mugen.core.debug.Debug;
import org.lee.mugen.core.debug.StatectrlBreakPoint;
import org.lee.mugen.sprite.parser.Parser;
import org.lee.mugen.sprite.parser.Parser.GroupText;

public class CnsActionSeparatorPanel extends JPanel {
	AddButton btnAddSection;
	RemoveButton btnRemoveSection;
	BreakPointBefore btnBreakPointBefore = new BreakPointBefore();
	BreakPointAfter btnBreakPointAfter = new BreakPointAfter();
	GoBtn btnGo = new GoBtn();
	
	SingleCnsPanel pnlParentSingleCmd;
	JPanel cntParent;
	public CnsActionSeparatorPanel(SingleCnsPanel main, JPanel pnlList) {
		this.pnlParentSingleCmd = main;
		this.cntParent = pnlList;
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

		add(btnAddSection = new AddButton());
		add(btnRemoveSection = new RemoveButton());
		add(btnBreakPointBefore);
		add(btnBreakPointAfter);
		add(btnGo);
		add(Box.createHorizontalGlue());
		
		btnBreakPointBefore.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnBreakPointBefore.isSelected()) {
					
				} else {
					
				}
				
			}});
		
	}
	class GoBtn extends JButton {
		public GoBtn() {
			super("Go");
			setPreferredSize(new Dimension(100, 20));
			addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Debug.getDebug().setStop(false);
					
				}});
		}
	}
	class BreakPointBefore extends JToggleButton {
		public BreakPointBefore() {
			super("Break Before Trigger");
			setPreferredSize(new Dimension(100, 20));
			addMouseListener(new MouseAdapter() {
				BreakPoint last = null;
				@Override
				public void mouseClicked(MouseEvent e) {
					BreakPointBefore bp = (BreakPointBefore) e.getSource();
					if (bp.isSelected()) {
						int index = 0;
						Integer lastStatedefId = null;
						for (Component c: cntParent.getComponents()) {
							if (c instanceof JTextPane) {
								GroupText grp = null;
								try {
									grp = Parser.getGroupTextMap(new StringReader(((JTextPane)c).getText())).get(0);
									
								} catch (Exception e2) {
									// TODO: handle exception
								}
								if (grp != null) {
									Matcher m = CnsDebugParser.P_STATE_DEF_TITLE_REGEX.matcher(grp.getSectionRaw());
									if (m.find()) {
										String s = m.group(1);
										lastStatedefId = new Integer(s);
										index = -1;
									}
									
								}
							}
							if (c instanceof CnsActionSeparatorPanel) {
								if (((CnsActionSeparatorPanel)c).btnBreakPointBefore != BreakPointBefore.this) {
									continue;
								}
								if (lastStatedefId != null) {
									BreakPoint bpt = last = new BreakPoint("1", new StatectrlBreakPoint(lastStatedefId, index), BreakPosition.Before);
									Debug.getDebug().addBreakPoint(bpt);
								} else {
									JOptionPane.showMessageDialog(null, "Parent statedef not found");
								}
							}
							index++;
						}
					
					} else {
						Debug.getDebug().removeBreakPoint(
								"1", 
								last.getType().getStateDef(), 
								((StatectrlBreakPoint)last.getType()).getStateCtrlPosition(),
								BreakPosition.Before);
						
//						Debug.getDebug().setGo(false);
//						Debug.getDebug().setStop(false);
						
					}
				}});
		}
	}
	class BreakPointAfter extends JToggleButton {
		public BreakPointAfter() {
			super("Break After Trigger");
			setPreferredSize(new Dimension(100, 20));
			addMouseListener(new MouseAdapter() {
				BreakPoint last = null;
				@Override
				public void mouseClicked(MouseEvent e) {
					BreakPointAfter bp = (BreakPointAfter) e.getSource();
					if (bp.isSelected()) {
						int index = 0;
						Integer lastStatedefId = null;
						for (Component c: cntParent.getComponents()) {
							if (c instanceof JTextPane) {
								GroupText grp = null;
								try {
									grp = Parser.getGroupTextMap(new StringReader(((JTextPane)c).getText())).get(0);
									
								} catch (Exception e2) {
									// TODO: handle exception
								}
								if (grp != null) {
									Matcher m = CnsDebugParser.P_STATE_DEF_TITLE_REGEX.matcher(grp.getSectionRaw());
									if (m.find()) {
										String s = m.group(1);
										lastStatedefId = new Integer(s);
										index = -1;
									}
									
								}
							}
							if (c instanceof CnsActionSeparatorPanel) {
								if (((CnsActionSeparatorPanel)c).btnBreakPointAfter != BreakPointAfter.this) {
									continue;
								}
								if (lastStatedefId != null) {
									BreakPoint bpt = last = new BreakPoint("1", new StatectrlBreakPoint(lastStatedefId, index), BreakPosition.After);
									Debug.getDebug().addBreakPoint(bpt);
								} else {
									JOptionPane.showMessageDialog(null, "Parent statedef not found");
								}
							}
							index++;
						}
					
					} else {
						Debug.getDebug().removeBreakPoint(
								"1", 
								last.getType().getStateDef(), 
								((StatectrlBreakPoint)last.getType()).getStateCtrlPosition(),
								BreakPosition.After);
//						Debug.getDebug().setStop(false);
//						Debug.getDebug().setGo(false);
						
					}
				}});
		}
	}
	
	class AddButton extends JButton {
		public AddButton() {
			super("Add");
			setPreferredSize(new Dimension(100, 20));
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int index = 0;
					for (Component c: cntParent.getComponents()) {

						if (c instanceof CnsActionSeparatorPanel) {
							if (((CnsActionSeparatorPanel)c).btnAddSection != AddButton.this) {
								
								index++;
								continue;
							}
							if (index != 0)
								cntParent.add(pnlParentSingleCmd.createDefaultTextPane(), index + 1);
							else
								cntParent.add(pnlParentSingleCmd.createDefaultTextPane(), 1);
							cntParent.add(new CnsActionSeparatorPanel(pnlParentSingleCmd, cntParent), index + 2);
						}
						index++;
					}
					cntParent.validate();
					cntParent.repaint();
				}
			});
		}

	}
	class RemoveButton extends JButton {
		public RemoveButton() {
			super("Remove");
			setPreferredSize(new Dimension(100, 20));
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int index = 0;
					for (Component c: cntParent.getComponents()) {
						if (c instanceof CnsActionSeparatorPanel) {
							if (((CnsActionSeparatorPanel)c).btnRemoveSection != RemoveButton.this) {
								index++;
								continue;
								
							}
							if (index+1 < cntParent.getComponentCount()) {
								Component comp = cntParent.getComponent(index+1);
								if (comp instanceof JTextPane) {
									pnlParentSingleCmd.getListOfTextPane().remove(comp);
								}
								cntParent.remove(index+1);
							}
							if (index+1 < cntParent.getComponentCount()) {
								Component comp = cntParent.getComponent(index+1);
								if (comp instanceof JTextPane) {
									pnlParentSingleCmd.getListOfTextPane().remove(comp);
								}
								cntParent.remove(index+1);
							}
						}
						index++;
					}
					if (cntParent.getComponentCount() == 0)
						cntParent.add(new CnsActionSeparatorPanel(pnlParentSingleCmd, cntParent));
					cntParent.validate();
					cntParent.repaint();
				}
			});
		}

	}
}
