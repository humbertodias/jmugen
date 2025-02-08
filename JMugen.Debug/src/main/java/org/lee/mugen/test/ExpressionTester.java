package org.lee.mugen.test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;

import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.parser.ExpressionFactory;

import com.Ostermiller.Syntax.HighlightedDocument;

public class ExpressionTester extends JPanel {
	public static void main(String[] args) {
		lanch();
	}
	
	public static void lanch() {
		JFrame frm = new JFrame("Expression Tester");
		frm.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frm.getContentPane().add(new ExpressionTester());
		frm.pack();
		frm.setVisible(true);
	
	}
	
	JTextPane _txtExpression = null;
	JTextArea _txtResult = null;
	JButton _btnEval = null;

	public ExpressionTester() {
		setLayout(new BorderLayout());
		_txtExpression = new JTextPane(new HighlightedDocument());
		JScrollPane scpExp = new JScrollPane(_txtExpression);
		scpExp.setPreferredSize(new Dimension(400, 400));
		
		_txtResult = new JTextArea();
		_txtResult.setEditable(false);
		JScrollPane scpResutlt = new JScrollPane(_txtResult);
		scpResutlt.setPreferredSize(new Dimension(200, 400));

		
		_btnEval = new JButton("Evaluate");
		_btnEval.setPreferredSize(new Dimension(400, 20));
		JSeparator sep = new JSeparator();
		sep.setPreferredSize(new Dimension(10, 400));
		sep.setMaximumSize(new Dimension(10, 400));
		
		add(scpExp, BorderLayout.CENTER);
		add(scpResutlt, BorderLayout.LINE_END);
		add(_btnEval, BorderLayout.PAGE_END);
		
		_btnEval.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				_txtResult.setText("");
				try {
					Valueable[] valueables = ExpressionFactory.evalExpression(_txtExpression.getText().toLowerCase());
					StringBuilder buffer = new StringBuilder();
					for (Valueable val: valueables) {
						buffer.append("[" + val.getValue("1") + "]\n");
					}
					_txtResult.setText(buffer.toString());
				} catch (Exception exc) {
					exc.printStackTrace();
					_txtResult.setText("Can not evaluate");
				}
			}});
	}
}
