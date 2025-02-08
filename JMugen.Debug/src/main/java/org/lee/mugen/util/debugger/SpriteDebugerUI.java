package org.lee.mugen.util.debugger;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.StateDef;
import org.lee.mugen.sprite.parser.Parser;
import org.lee.mugen.sprite.parser.Parser.GroupText;

import com.Ostermiller.Syntax.HighlightedDocument;

public class SpriteDebugerUI extends JFrame {
	
	
	private JLabel lblForCbxSprChooser;
	private JComboBox cbxSprChooser;
	
//	private JLabel lblForLstActions;
	private JList lstActions;
	public String getCurrentSpriteName() {
		return cbxSprChooser.getSelectedItem().toString();
	}
	
	private JTextPane txaStateCtrInfo;

	public class UndoAction extends AbstractAction {
	    public UndoAction(UndoManager manager) {
	      this.manager = manager;
	    }

	    public void actionPerformed(ActionEvent evt) {
	      try {
	        manager.undo();
	      } catch (CannotUndoException e) {
	        Toolkit.getDefaultToolkit().beep();
	      }
	    }

	    private UndoManager manager;
	  }

	  // The Redo action
	  public class RedoAction extends AbstractAction {
	    public RedoAction(UndoManager manager) {
	      this.manager = manager;
	    }

	    public void actionPerformed(ActionEvent evt) {
	      try {
	        manager.redo();
	      } catch (CannotRedoException e) {
	        Toolkit.getDefaultToolkit().beep();
	      }
	    }

	    private UndoManager manager;
	  }
	public SpriteDebugerUI() {
		super("Mugen StateControler Debugger");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		JPanel pnlContenpane = (JPanel) getContentPane();
		
		// Left
		JPanel pnlLeft = new JPanel();
		pnlLeft.setLayout(new BoxLayout(pnlLeft, BoxLayout.PAGE_AXIS));
		
		JPanel pnlForSprChooser = new JPanel();
		pnlForSprChooser.setLayout(new BoxLayout(pnlForSprChooser, BoxLayout.LINE_AXIS));
		
		lblForCbxSprChooser = new JLabel("Click Here After Sprite Load");

		lblForCbxSprChooser.setPreferredSize(new Dimension(70, 25));
		
		Collection<Sprite> sprites = GameFight.getInstance().getSprites();
		List<String> spriteIdList = new ArrayList<String>();
		for (Sprite s: sprites)
			spriteIdList.add(s.getSpriteId());
		

		cbxSprChooser = new JComboBox(spriteIdList.toArray());
		cbxSprChooser.setPreferredSize(new Dimension(100, 25));
		cbxSprChooser.setMaximumSize(new Dimension(100, 25));
		cbxSprChooser.setMinimumSize(new Dimension(100, 25));
		cbxSprChooser.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		pnlForSprChooser.add(lblForCbxSprChooser);
		pnlForSprChooser.add(cbxSprChooser);
		
		lblForCbxSprChooser.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				Collection<Sprite> sprites = GameFight.getInstance().getSprites();
				DefaultComboBoxModel model = new DefaultComboBoxModel();
				for (Sprite s: sprites)
					model.addElement(s.getSpriteId());
				cbxSprChooser.setModel(model);
				
			}
		});
		
		
		pnlForSprChooser.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		pnlLeft.add(pnlForSprChooser);
		
		lstActions = new JList();
		lstActions.setModel(new DefaultListModel());
		
		JScrollPane scpActions = new JScrollPane(lstActions);
		scpActions.setPreferredSize(new Dimension(170, 600));
		scpActions.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		pnlLeft.add(scpActions);
		
		// Right
		JPanel pnlRight = new JPanel();
		pnlRight.setLayout(new BorderLayout());
		HighlightedDocument kit = new HighlightedDocument();
		txaStateCtrInfo = new JTextPane(kit);
		
	   UndoManager manager = new UndoManager();
	   txaStateCtrInfo.getDocument().addUndoableEditListener(manager);
	    Action undoAction = new UndoAction(manager);
	    Action redoAction = new RedoAction(manager);
	    txaStateCtrInfo.registerKeyboardAction(undoAction, KeyStroke.getKeyStroke(
	            KeyEvent.VK_Z, InputEvent.CTRL_MASK), JComponent.WHEN_FOCUSED);
	    txaStateCtrInfo.registerKeyboardAction(redoAction, KeyStroke.getKeyStroke(
	            KeyEvent.VK_Y, InputEvent.CTRL_MASK), JComponent.WHEN_FOCUSED);
	    
	    
		JScrollPane scpStateCtrl = new JScrollPane(txaStateCtrInfo);
		scpStateCtrl.setPreferredSize(new Dimension(400, 600));
		scpStateCtrl.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		
		pnlRight.add(scpStateCtrl);
		
		JButton btnSave = new JButton("Save");
		btnSave.setPreferredSize(new Dimension(150,25));
		pnlRight.add(btnSave, BorderLayout.PAGE_END);
		
		
		// Add Both pnl
		pnlContenpane.add(pnlLeft, BorderLayout.LINE_START);
		pnlContenpane.add(pnlRight, BorderLayout.CENTER);
		
		
		// Listener
		ActionListener al = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String spriteId = (String) cbxSprChooser.getSelectedItem();
				if (spriteId != null) {
					DefaultListModel model = (DefaultListModel) lstActions.getModel();
					model.clear();
					txaStateCtrInfo.setText("");
					
					Sprite spr = GameFight.getInstance().getSpriteInstance(spriteId);
					List<StateDef> list = spr.getSpriteState().getAllStateDef();
					
					Collections.sort(list, new Comparator<StateDef>() {

						public int compare(StateDef o1, StateDef o2) {
							// TODO Auto-generated method stub
							return o1.getIntId() - o2.getIntId();
						}});
					for (StateDef statedef : list) {
//						String action = statedef.getId();
						model.addElement(statedef);
					}
				}
			}};
		cbxSprChooser.addActionListener(al);
		al.actionPerformed(null);
		
		lstActions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstActions.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				StateDef sdef = (StateDef) lstActions.getSelectedValue();
				if (sdef == null) {
					txaStateCtrInfo.setText("");
					return;
				}
				String action = sdef.getId();
				if (action != null) {
					String spriteId = (String) cbxSprChooser.getSelectedItem();
					StringBuilder builder = new StringBuilder();
					if (Integer.parseInt(action) < 0) {
						for (StateDef state: GameFight.getInstance().getSpriteInstance(spriteId).getSpriteState().getNegativeStateSef()) {
							if (state.getId().equals(action)) {
								for (GroupText grp: state.getGroups()) {
									builder.append(grp.getSectionRaw() + "\n");
									builder.append(grp.getText() + "\n");
								}
							}
						}
						
					} else {
						for (GroupText grp: GameFight.getInstance().getSpriteInstance(spriteId).getSpriteState().getStateDef(action).getGroups()) {
							builder.append(grp.getSectionRaw() + "\n");
							builder.append(grp.getText() + "\n");
						}
						
					}
					txaStateCtrInfo.setText(builder.toString());

				}
				
			}});
		btnSave.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String spriteId = (String) cbxSprChooser.getSelectedItem();
				StateDef sdef = (StateDef) lstActions.getSelectedValue();
				String action = sdef.getId();
				String raw = txaStateCtrInfo.getText();
				try {

					Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
					StateDef statedef = sprite.getSpriteState().getStateDef(action);
					statedef.getGroups().clear();
					List<GroupText> groups = Parser.getGroupTextMap(new StringReader(raw), true);
					statedef.getGroups().addAll(groups);
					statedef.recompile();
						

				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}});
		pack();
	}
	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
		Collection<Sprite> sprites = GameFight.getInstance().getSprites();
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		for (Sprite s: sprites)
			model.addElement(s.getSpriteId());
		cbxSprChooser.setModel(model);
		cbxSprChooser.setSelectedIndex(sprites.size() -1);
		lstActions.setSelectedIndex(0);
	}
}
