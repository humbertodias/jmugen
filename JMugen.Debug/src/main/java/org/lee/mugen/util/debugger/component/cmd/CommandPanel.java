package org.lee.mugen.util.debugger.component.cmd;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

import org.lee.mugen.core.JMugenConstant;
import org.lee.mugen.util.debugger.component.cmd.CmdDebugParser.FileType;
import org.lee.mugen.util.debugger.component.cmd.CmdDebugParser.GrpTxtCategory;

public class CommandPanel extends JPanel {
	final List<JTextPane> listOfTextPane = new ArrayList<JTextPane>();
	final JTabbedPane tbPane = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT);
	final Action actNewCategoryBefore = new AbstractAction("< New category") {

		@Override
		public void actionPerformed(ActionEvent e) {		
			String in = JOptionPane.showInputDialog("Enter a category");
			if (in != null && in.length() > 0) {
				GrpTxtCategory gtCategory = new GrpTxtCategory(in);
				int index = tbPane.getSelectedIndex();
				if (index < 0)
					index = 0;
				tbPane.insertTab(in, null, new SingleCmdPanel(listOfTextPane, gtCategory), null, index);
			}

		}
	};

	final Action actNewCategoryAfter = new AbstractAction("New category >") {
		@Override
		public void actionPerformed(ActionEvent e) {
			String in = JOptionPane.showInputDialog("Enter a category");
			if (in != null && in.length() > 0) {
				GrpTxtCategory gtCategory = new GrpTxtCategory(in);
				int index = tbPane.getSelectedIndex();
				index++;
				tbPane.insertTab(in, null, new SingleCmdPanel(listOfTextPane, gtCategory), null, index);
			}
		}
	};
	final Action actDeleteCategory = new AbstractAction("Delete category") {

		@Override
		public void actionPerformed(ActionEvent e) {
			int index = tbPane.getSelectedIndex();
			tbPane.removeTabAt(index);
			tbPane.validate();
		}
		
	};
	final Action actRenameCategory = new AbstractAction("Rename category") {

		@Override
		public void actionPerformed(ActionEvent e) {
			int index = tbPane.getSelectedIndex();
			String initialTitle = tbPane.getTitleAt(index);
			String in = JOptionPane.showInputDialog(null, "Enter a new name", initialTitle);
			if (in != null && in.length() > 0) {
				tbPane.setTitleAt(index, in);
			}
		}
		
	};
	final JPopupMenu menu = new JPopupMenu();
	public CommandPanel(String cmdFile) throws Exception {
		setLayout(new BorderLayout());
		menu.add(actNewCategoryBefore);
		menu.add(actNewCategoryAfter);
		menu.add(actDeleteCategory);
		menu.add(actRenameCategory);
		tbPane.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent evt) {
	            if (evt.isPopupTrigger()) {
	                menu.show(evt.getComponent(), evt.getX(), evt.getY());
	            }
	        }
	        public void mouseReleased(MouseEvent evt) {
	            if (evt.isPopupTrigger()) {
	                menu.show(evt.getComponent(), evt.getX(), evt.getY());
	            }
	        }
	    });

		CmdDebugParser debugParser = new CmdDebugParser(FileType.CMD, new String[] {cmdFile});
	    
		for (GrpTxtCategory c: debugParser.getCategories()) {
			SingleCmdPanel cmdPanel = new SingleCmdPanel(listOfTextPane, c);
			cmdPanel.setPreferredSize(new Dimension(300, 500));
			tbPane.addTab(c.getCategory(), cmdPanel);
		}
		add(tbPane);
	}
	
	public static void main(String[] args) throws Exception {
		final JFrame frm = new JFrame("Test");
		
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frm.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
		});
		String name = "kfm";
		String sFile = JMugenConstant.RESOURCE + "chars/" + name + "/" + name + ".cmd";
//		String sCommonFile = JMugenConstant.RESOURCE + "data/common1.cns";
	
		frm.getContentPane().add(new CommandPanel(sFile));
		
		frm.pack();
		
		
		
//		SwingUtilities.invokeLater(new Runnable() {
//
//			@Override
//			public void run() {
				frm.setVisible(true);
				
//			}});
		
	}
}
