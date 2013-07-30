package br.com.petserv.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import br.com.petserv.entidades.Cliente;
import br.com.petserv.entidades.Endereco;
import br.com.petserv.facade.ClienteFacade;

import com.toedter.calendar.JDateChooser;

public class Clientes extends JPanel {
	private static final long serialVersionUID = 8124150294430304521L;

	private JLabel jlbCpf;
	private JTextField jtfCpf;
	private JTextField jtfTelefone;
	private JDateChooser jCalendar;
	private JLabel jlbCep;
	private JLabel lblNome;
	private JLabel jlbTelefone;
	private JLabel jlbDataCadastro;
	private JLabel jlbEmail;
	private JLabel jblComplemento;
	private JTextField jtfCep;
	private String[] titulosClientes = { "", "Nome", "Cpf", "Telefone",
			"Email", "Data de Cadastro" };
	private ClienteFacade dao;
	private JPanel jpTabela;
	private JPanel jpComponentes;
	private JTable tabelaCliente;
	private JButton btnLimpar;
	private JButton btnRemover;
	private JButton btnSalvar;
	private Long idCliente;
	private Long Cliente_ser_removido_ou_alterado;
	private JTextField jtfNome;
	private JTextField jtfEmail;
	private JTextField jtfCidade;
	private JTextField jtfBairro;
	private JTextField jtfEndereco;
	private JTextField jtfComplemento;


	public Clientes() {
		setBackground(Color.LIGHT_GRAY);
		dao = new ClienteFacade();
		initComponents();
		jtbTabela_Cliente();
		atualizaTabela();

	}

	private void initComponents() {
		setLayout(null);

		jpComponentes = new JPanel();
		jpComponentes.setBorder(new LineBorder(new Color(0, 0, 0)));
		jpComponentes.setBounds(10, 11, 611, 202);
		add(jpComponentes);
		jpComponentes.setLayout(null);

		lblNome = new JLabel("Nome: ");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setBounds(43, 29, 58, 14);
		jpComponentes.add(lblNome);

		jlbCpf = new JLabel(" CPF: ");
		jlbCpf.setHorizontalAlignment(SwingConstants.RIGHT);
		jlbCpf.setBounds(36, 57, 65, 14);
		jpComponentes.add(jlbCpf);

		jtfCpf = new JTextField();
		jtfCpf.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				jtfCpf.setText(jtfCpf.getText().replaceAll("[^0-9]", ""));
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		jtfCpf.setToolTipText("CPF do cliente a ser cadastrado");
		jtfCpf.setBounds(109, 51, 112, 20);
		jpComponentes.add(jtfCpf);

		jtfTelefone = new JTextField();
		jtfTelefone.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				jtfTelefone.setText(jtfTelefone.getText().replaceAll("[^0-9]",
						""));
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		jtfTelefone.setToolTipText("Numero do telefone do cliente");

		jtfTelefone.setBounds(299, 51, 129, 20);
		jpComponentes.add(jtfTelefone);

		jlbTelefone = new JLabel("Telefone :");
		jlbTelefone.setHorizontalAlignment(SwingConstants.RIGHT);
		jlbTelefone.setBounds(231, 54, 58, 14);
		jpComponentes.add(jlbTelefone);

		jlbDataCadastro = new JLabel("Data Cadastro:");
		jlbDataCadastro.setHorizontalAlignment(SwingConstants.RIGHT);
		jlbDataCadastro.setBounds(12, 177, 89, 14);
		jpComponentes.add(jlbDataCadastro);

		Date date = new Date();
		jCalendar = new JDateChooser(date);
		jCalendar.setBounds(109, 171, 110, 20);
		jpComponentes.add(jCalendar);

		btnSalvar = new JButton("Adicionar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validaCampos()) {
					try {
						filtro();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnSalvar.setBounds(463, 21, 112, 43);
		jpComponentes.add(btnSalvar);

		btnRemover = new JButton("Remover");
		btnRemover.setEnabled(false);
		btnRemover.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				removerCliente(Cliente_ser_removido_ou_alterado);

			}
		});
		btnRemover.setBounds(463, 70, 112, 43);
		jpComponentes.add(btnRemover);

		jlbEmail = new JLabel("Email:");
		jlbEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		jlbEmail.setBounds(12, 84, 89, 14);
		jpComponentes.add(jlbEmail);

		jblComplemento = new JLabel("Complemento:");
		jblComplemento.setHorizontalAlignment(SwingConstants.RIGHT);
		jblComplemento.setBounds(0, 143, 101, 14);
		jpComponentes.add(jblComplemento);

		jlbCep = new JLabel("Cep:");
		jlbCep.setHorizontalAlignment(SwingConstants.RIGHT);
		jlbCep.setBounds(231, 84, 58, 14);
		jpComponentes.add(jlbCep);

		jtfCep = new JTextField();
		jtfCep.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				jtfCep.setText(jtfCep.getText().replaceAll("[^0-9]", ""));

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		jtfCep.setToolTipText("Cep do Cliente");
		jtfCep.setBounds(299, 81, 129, 20);
		jpComponentes.add(jtfCep);

		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(463, 120, 112, 43);
		btnLimpar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpaCampos();
			}
		});

		jpComponentes.add(btnLimpar);

		jtfNome = new JTextField();
		jtfNome.setToolTipText("Nome do Cliente a ser cadastrado");
		jtfNome.setBounds(109, 22, 319, 20);
		jpComponentes.add(jtfNome);

		jtfEmail = new JTextField();
		jtfEmail.setToolTipText("Email do Cliente a ser cadastrado");
		jtfEmail.setBounds(109, 82, 147, 20);
		jpComponentes.add(jtfEmail);

		JLabel lblEndereco = new JLabel("Endere\u00E7o:");
		lblEndereco.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEndereco.setBounds(12, 113, 89, 14);
		jpComponentes.add(lblEndereco);

		JLabel lblCidade = new JLabel("Cidade :");
		lblCidade.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCidade.setBounds(231, 177, 58, 14);
		jpComponentes.add(lblCidade);

		jtfCidade = new JTextField();
		jtfCidade.setToolTipText("Cidade em que reside o Cliente");
		jtfCidade.setBounds(299, 174, 129, 20);
		jpComponentes.add(jtfCidade);

		jtfBairro = new JTextField();
		jtfBairro.setToolTipText("Bairro em que reside o Cliente");
		jtfBairro.setBounds(299, 140, 129, 20);
		jpComponentes.add(jtfBairro);

		JLabel jlbBairro = new JLabel("Bairro :");
		jlbBairro.setHorizontalAlignment(SwingConstants.RIGHT);
		jlbBairro.setBounds(231, 143, 58, 14);
		jpComponentes.add(jlbBairro);

		jtfEndereco = new JTextField();
		jtfEndereco.setToolTipText("Email do Cliente a ser cadastrado");
		jtfEndereco.setBounds(109, 113, 319, 20);
		jpComponentes.add(jtfEndereco);

		jtfComplemento = new JTextField();
		jtfComplemento.setToolTipText("Email do Cliente a ser cadastrado");
		jtfComplemento.setBounds(109, 140, 129, 20);
		jpComponentes.add(jtfComplemento);

	}

	private void jtbTabela_Cliente() {
		jpTabela = new JPanel();
		jpTabela.setBackground(Color.WHITE);
		jpTabela.setBorder(new LineBorder(new Color(0, 0, 0)));
		jpTabela.setBounds(10, 215, 611, 255);

		tabelaCliente = new JTable();
		tabelaCliente.setBorder(null);
		tabelaCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		String[] colunas = new String[] {};
		String[][] dados = new String[][] { {}, {} };
		DefaultTableModel model = new DefaultTableModel(dados, colunas);
		tabelaCliente.setModel(model);

		ListSelectionModel selectionModel = tabelaCliente.getSelectionModel();
		selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		selectionModel.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					ListSelectionModel model = tabelaCliente
							.getSelectionModel();
					int index = model.getLeadSelectionIndex();
					carregandoCampos(carregarCliente(index));					

				}
			}
		});
		jpTabela.setLayout(new BorderLayout(0, 0));

		JScrollPane scroll = new JScrollPane();
		scroll.setViewportBorder(null);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setViewportView(tabelaCliente);
		jpTabela.add(scroll, BorderLayout.CENTER);
		add(jpTabela);
	}

	protected Long carregarCliente(int index) {
		Long id = -1l;
		if (index != -1) {
			btnSalvar.setText("ALTERAR");
			btnRemover.setEnabled(true);

			id = (Long) tabelaCliente.getValueAt(index, 0);
			
			
			Cliente_ser_removido_ou_alterado = (long) id;

		} else {
			btnSalvar.setText("Adicionar");
			btnRemover.setEnabled(false);
		}
		return id;
	}

	private void carregandoCampos(Long idCliente) {
		// aqui
		
		Cliente cli = dao.getCliente(idCliente);
		
		
		Calendar cal_cadastro = Calendar.getInstance();
		cal_cadastro.setTime(jCalendar.getDate());

		idCliente = cli.getIdCliente();
		jtfNome.setText(cli.getNome());
		jtfCpf.setText(cli.getCpf());
		jtfTelefone.setText(cli.getTelefone());
		jtfEmail.setText(cli.getEmail());
		jCalendar.setDateFormatString(cli.getData_cadastro().toString());
		jtfCep.setText(cli.getEndereco().getCep());
		jtfEndereco.setText(cli.getEndereco().getDescricao());
		jtfComplemento.setText(cli.getEndereco().getComplemento());
		jtfBairro.setText(cli.getEndereco().getBairro());
		jtfCidade.setText(cli.getEndereco().getCidade());

	}

	private void ajustandoTamanhoTabela(JTable tabelaCliente) {

		DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
		centro.setHorizontalAlignment(JLabel.CENTER);

		DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
		direita.setHorizontalAlignment(JLabel.RIGHT);

		tabelaCliente.getColumnModel().getColumn(0).setPreferredWidth(0);
		tabelaCliente.getColumnModel().getColumn(0).setMinWidth(0);
		tabelaCliente.getColumnModel().getColumn(0).setMaxWidth(0);

	}

	public void jtable_Cliente(List<Cliente> clientes) {

		DefaultTableModel model = (DefaultTableModel) tabelaCliente.getModel();
		model.setColumnIdentifiers(titulosClientes);
		model.setNumRows(0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		ajustandoTamanhoTabela(tabelaCliente);
		if (clientes != null) {
			//
			try {

				for (Cliente cliente : clientes) {

					model.addRow(new Object[] { cliente.getIdCliente(),
							cliente.getNome(), cliente.getCpf(),
							cliente.getTelefone(), cliente.getEmail(),
							sdf.format(cliente.getData_cadastro().getTime()),
							cliente.getEndereco().getCep(),
							cliente.getEndereco().getDescricao(),
							cliente.getEndereco().getComplemento(),
							cliente.getEndereco().getBairro(),
							cliente.getEndereco().getCidade() });
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	protected boolean validaCampos() {

		if (jtfCpf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Campo Cpf não pode ser nulo");
			return false;
		}
		if (jtfTelefone.getText().equals("")) {
			JOptionPane.showMessageDialog(null,
					"Campo telefone não pode ser vazio");
			return false;
		}
		if (jCalendar.getDate() == null) {
			JOptionPane
					.showMessageDialog(null,
							"Campo data de cadastro não pode ser nulo ou está no formato errado");
			return false;
		}

		return true;
	}

	public void limpaCampos() {
		jtfNome.setText("");
		jtfCpf.setText("");
		jtfTelefone.setText("");
		jtfEmail.setText("");
		jtfCep.setText("");
		jtfEndereco.setText("");
		jtfComplemento.setText("");
		jtfCidade.setText("");
		jtfBairro.setText("");
		jCalendar.setDate(new Date());
		btnSalvar.setText("Adicionar");
		btnRemover.setEnabled(false);
	}

	public void filtro() throws SQLException {

		Calendar cal_cadastro = Calendar.getInstance();
		cal_cadastro.setTime(jCalendar.getDate());

		Endereco endereco = new Endereco(jtfEndereco.getText(),
				jtfComplemento.getText(), jtfBairro.getText(),
				jtfCidade.getText(), jtfCep.getText());

		Cliente cliente = new Cliente(idCliente, jtfNome.getText(),
				jtfCpf.getText(), jtfTelefone.getText(), jtfEmail.getText(),
				cal_cadastro, endereco);

		boolean operacao_cliente = false;

		if (btnSalvar.getText().equals("ALTERAR")) {
			operacao_cliente = dao.atualizarCliente(Cliente_ser_removido_ou_alterado);
		} else {
			operacao_cliente = dao.salvaCliente(cliente);
		}

		if (operacao_cliente) {
			limpaCampos();
			atualizaTabela();
		} else {
			JOptionPane.showMessageDialog(null,
					"Erro na operação, verifique os dados digitados");
		}
	}

	public void atualizaTabela() {
		jtable_Cliente(dao.getListaClientes());		
//		Aqui
		dao.getListEnderecos();
		

	}

	protected void removerCliente(Long id) {
		dao.removeCliente(id);
		limpaCampos();
		atualizaTabela();
		btnRemover.setEnabled(false);
	}
}
