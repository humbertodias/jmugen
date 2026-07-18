package org.lee.mugen.util.debugger.component.cmd;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class CmdActionSeparatorPanel extends JPanel {
	AddButton btnAddSection;
	RemoveButton btnRemoveSection;
	SingleCmdPanel pnlParentSingleCmd;
	JPanel cntParent;
	public CmdActionSeparatorPanel(SingleCmdPanel main, JPanel pnlList) {
		this.pnlParentSingleCmd = main;
		this.cntParent = pnlList;
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		add(btnAddSection = new AddButton());
		add(btnRemoveSection = new RemoveButton());
		
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

						if (c instanceof CmdActionSeparatorPanel) {
							if (((CmdActionSeparatorPanel)c).btnAddSection != AddButton.this) {
								
								index++;
								continue;
							}
							if (index != 0)
								cntParent.add(pnlParentSingleCmd.createDefaultTextPane(), index + 1);
							else
								cntParent.add(pnlParentSingleCmd.createDefaultTextPane(), 1);
							cntParent.add(new CmdActionSeparatorPanel(pnlParentSingleCmd, cntParent), index + 2);
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
						if (c instanceof CmdActionSeparatorPanel) {
							if (((CmdActionSeparatorPanel)c).btnRemoveSection != RemoveButton.this) {
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
						cntParent.add(new CmdActionSeparatorPanel(pnlParentSingleCmd, cntParent));
					cntParent.validate();
					cntParent.repaint();
				}
			});
		}

	}
}
