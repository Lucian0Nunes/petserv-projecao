package br.com.petserv.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class Clientes extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public Clientes() {
		setLayout(null);
		
		JLabel lblTeste = new JLabel("TESTE");
		lblTeste.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeste.setBounds(345, 11, 71, 21);
		add(lblTeste);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(32, 54, 384, 109);
		add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 11, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 42, 86, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);

	}
}
