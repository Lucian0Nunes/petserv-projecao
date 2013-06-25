package br.com.petserv.view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.Toolkit;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5255447374225652157L;
	
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/resources/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 677, 599);
		
		//panel primario
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// componente ABA
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 641, 543);
		contentPane.add(tabbedPane);
		
		//Panel de Cliente
		JPanel panel = new JPanel();
		panel.setBounds(10, 5, 650, 194);
		panel.setLayout(null);

		
		//adicionando o panel Mercadoria a uma nova TAB
		PDV pdv = new PDV();
		tabbedPane.addTab("PDV", new ImageIcon(Principal.class.getResource("/resources/cash.png")), pdv, null);
		
		
		Mercadorias mercadoria = new Mercadorias();
		tabbedPane.addTab("Mercadorias", new ImageIcon(Principal.class.getResource("/resources/prod.png")), mercadoria, null);
		
		Clientes clientes = new Clientes();
		tabbedPane.addTab("Clientes", new ImageIcon(Principal.class.getResource("/resources/cliente.png")), clientes, null);
	}
}
