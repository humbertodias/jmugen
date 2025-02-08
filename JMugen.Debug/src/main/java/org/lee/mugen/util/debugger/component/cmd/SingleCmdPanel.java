package org.lee.mugen.util.debugger.component.cmd;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.lee.mugen.core.JMugenConstant;
import org.lee.mugen.sprite.parser.Parser;
import org.lee.mugen.sprite.parser.Parser.GroupText;
import org.lee.mugen.util.debugger.component.cmd.CmdDebugParser.GrpTxtCategory;
import org.lee.mugen.util.debugger.component.cmd.CmdDebugParser.FileType;

import com.Ostermiller.Syntax.HighlightedDocument;

public class SingleCmdPanel extends JPanel {

	
	static {
	    String nativeLF = UIManager.getSystemLookAndFeelClassName();
	    
	    // Install the look and feel
	    try {
	        UIManager.setLookAndFeel(nativeLF);
	    } catch (InstantiationException e) {
	    } catch (ClassNotFoundException e) {
	    } catch (UnsupportedLookAndFeelException e) {
	    } catch (IllegalAccessException e) {
	    }
	}

	private GrpTxtCategory gtCategory;
	private JPanel pnlSections = new JPanel();
	final String defaultText = 	"[command]\n" +
								"name = \"name\"\n" + 
								"command = \n" +
								"time = 1";
	private List<JTextPane> listOfTextPane;
	
	List<JTextPane> getListOfTextPane() {
		return listOfTextPane;
	}

	public SingleCmdPanel(List<JTextPane> listOfTextPane, GrpTxtCategory gtCategory) {
		this.listOfTextPane = listOfTextPane;
		this.gtCategory = gtCategory;
		
		setLayout(new BorderLayout());
		pnlSections.setLayout(new BoxLayout(pnlSections, BoxLayout.PAGE_AXIS));

		JScrollPane scp = new JScrollPane(pnlSections);
		
		for (GroupText grp: gtCategory.getList()) {
			JTextPane textPane = createDefaultTextPane();
			textPane.setText(grp.toString());
			
			
			pnlSections.add(new CmdActionSeparatorPanel(this, pnlSections));
			pnlSections.add(textPane);
			
		}
		pnlSections.add(new CmdActionSeparatorPanel(this, pnlSections));
		add(scp);
		add(new JButton("Save"), BorderLayout.PAGE_END);
	}
	
	JTextPane createDefaultTextPane() {
		final JTextPane result = new JTextPane(new HighlightedDocument());
		listOfTextPane.add(result);
		result.setText(defaultText);
		final JPopupMenu menu = new JPopupMenu();

		JMenuItem itemRef = new JMenuItem("Get all references");
		itemRef.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					GroupText grp = Parser.getGroupTextMap(new StringReader(result.getText())).get(0);
					if (!grp.getSectionRaw().equalsIgnoreCase("[command]"))
						return;
					String cmd = grp.getKeyValues().get("name");
					for (JTextPane pane: listOfTextPane) {
						if (cmd != null && pane.getText().contains(cmd)) {
							grp = Parser.getGroupTextMap(new StringReader(pane.getText())).get(0);
							if (!grp.getSectionRaw().equalsIgnoreCase("[command]"))
								JOptionPane.showMessageDialog(null, pane.getText());
						}
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}});
		menu.add(itemRef);
		result.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent evt) {
	            if (evt.isPopupTrigger()) {
					try {
						GroupText grp = Parser.getGroupTextMap(new StringReader(result.getText())).get(0);
						if (!grp.getSectionRaw().equalsIgnoreCase("[command]"))
							return;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
	                menu.show(evt.getComponent(), evt.getX(), evt.getY());
	            }
	        }
	        public void mouseReleased(MouseEvent evt) {
	            if (evt.isPopupTrigger()) {
					try {
						GroupText grp = Parser.getGroupTextMap(new StringReader(result.getText())).get(0);
						if (!grp.getSectionRaw().equalsIgnoreCase("[command]"))
							return;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                menu.show(evt.getComponent(), evt.getX(), evt.getY());
	            }
	        }
	    });
		
		return result;
	}


	
	public GrpTxtCategory getgtCategory() {
		return gtCategory;
	}
	
	
}
