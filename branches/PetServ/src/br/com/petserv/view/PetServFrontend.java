package br.com.petserv.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import br.com.petserv.entidades.Produto;
import br.com.petserv.entidades.Servico;
import br.com.petserv.facade.MercadoriaFacade;
import br.com.petserv.interfaces.Mercadoria;
import br.com.petserv.util.JNumberFormatField;

import com.toedter.calendar.JDateChooser;

public class PetServFrontend extends JFrame {

	private static final long serialVersionUID = 3073122990601604252L;
	private JPanel contentPane;
	private JPanel jpMercadoria;
	private JLabel jlbDescricao;
	private JTextField  jtfDescricao;
	private JTextField  jtfEstoqueInicial;
	private JDateChooser jCalendar;
	private JTextField  jtfValorNotaFiscal;
	private JTextField  jtfValorVenda;
	private JLabel jlbTempoMedio;
	private JLabel lblTipoMercadoria;
	private JLabel jlbEstoqueInicial;
	private JLabel jlbDataCadastro;
	private JLabel jlbValorCadastro;
	private JLabel jblValorVenda;
	private JTextField  jtfTempoMedio;
	private ButtonGroup btnRadioGroup;
	private String[] titulosServico = {"", "Servico", "Data Cadastro","Tempo Medio", "Valor Servico", "Vendidos" };
	private String[] titulosProduto = {"", "Produto", "Data Entrada","Valor Compra", "Valor Venda", "Estoque" };
	private MercadoriaFacade dao;
	private JPanel jpTabela;
	private JTable tabelaMercadoria;
	private JButton btnLimpar;
	private JButton btnRemover;
	private JButton btnSalvar;
	private JRadioButton jrbProduto;
	private JRadioButton jrbServico;
	private Long id_mercadoria;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PetServFrontend frame = new PetServFrontend();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PetServFrontend() {
		dao = new MercadoriaFacade();
		initComponents();
		jtbTabela_Mercadorias();
	}

	private void initComponents() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PetServFrontend.class.getResource("/resources/icon.png")));
		setTitle("PetServ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable( false );
		setBounds(100, 100, 653, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		jpMercadoria = new JPanel();
		jpMercadoria.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				" Cadastro de Mercadorias ",TitledBorder.LEADING, TitledBorder.TOP, null,new Color(0, 0, 0)));
		jpMercadoria.setBounds(8, 11, 613, 176);
		contentPane.add(jpMercadoria);
		jpMercadoria.setLayout(null);

		jrbProduto = new JRadioButton("Produto");
		jrbProduto.setBounds(119, 21, 89, 23);
		jrbProduto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				limpaCampos();
				ativaDesativaCamposPorMercadoria(true);
				atualizaTabela(new Produto());
			}
		});
		jpMercadoria.add(jrbProduto);

		jrbServico = new JRadioButton("Servi\u00E7o");
		jrbServico.setBounds(250, 21, 83, 23);
		jrbServico.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				limpaCampos();
				ativaDesativaCamposPorMercadoria(false);
				atualizaTabela(new Servico());
				
			}
		});
		jpMercadoria.add(jrbServico);

		btnRadioGroup = new ButtonGroup();
		btnRadioGroup.add(jrbServico);
		btnRadioGroup.add(jrbProduto);

		lblTipoMercadoria = new JLabel("Mercadoria: ");
		lblTipoMercadoria.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoMercadoria.setBounds(12, 25, 89, 14);
		jpMercadoria.add(lblTipoMercadoria);

		jlbDescricao = new JLabel("Descri\u00E7\u00E3o: ");
		jlbDescricao.setHorizontalAlignment(SwingConstants.RIGHT);
		jlbDescricao.setBounds(36, 54, 65, 14);
		jpMercadoria.add(jlbDescricao);

		jtfDescricao = new JTextField ();
		jtfDescricao.setToolTipText("Descri\u00E7\u00E3o do Produto a ser cadastrado");
		jtfDescricao.setBounds(109, 51, 319, 20);
		jpMercadoria.add(jtfDescricao);
		
		jtfEstoqueInicial = new JTextField();
		jtfEstoqueInicial.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				jtfEstoqueInicial.setText(jtfEstoqueInicial.getText().replaceAll("[^0-9]", ""));
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		jtfEstoqueInicial.setToolTipText("Valor inicial do estoque do produto em quest\u00E3o");
		
		jtfEstoqueInicial.setBounds(109, 81, 112, 20);
		jpMercadoria.add(jtfEstoqueInicial);

		jlbEstoqueInicial = new JLabel("Estoque Inicial:");
		jlbEstoqueInicial.setHorizontalAlignment(SwingConstants.RIGHT);
		jlbEstoqueInicial.setBounds(10, 83, 89, 14);
		jpMercadoria.add(jlbEstoqueInicial);

		jlbDataCadastro = new JLabel("Data Cadastro:");
		jlbDataCadastro.setHorizontalAlignment(SwingConstants.RIGHT);
		jlbDataCadastro.setBounds(10, 112, 89, 14);
		jpMercadoria.add(jlbDataCadastro);
		
		Date date = new Date();
		jCalendar = new JDateChooser(date);
		jCalendar.setBounds(109, 109, 110, 20);
		jpMercadoria.add(jCalendar);

		btnSalvar = new JButton("Adicionar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validaCampos()){
					filtro();
				}
			}
		});
		btnSalvar.setBounds(463, 21, 112, 43);
		jpMercadoria.add(btnSalvar);

		btnRemover = new JButton("Remover");
		btnRemover.setEnabled(false);
		btnRemover.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				removerMercadoria(id_mercadoria);
				
			}
		});
		btnRemover.setBounds(463, 70, 112, 43);
		jpMercadoria.add(btnRemover);

		jlbValorCadastro = new JLabel("Valor de Nota:");
		jlbValorCadastro.setHorizontalAlignment(SwingConstants.RIGHT);
		jlbValorCadastro.setBounds(10, 143, 89, 14);
		jpMercadoria.add(jlbValorCadastro);

		jblValorVenda = new JLabel("Valor de Venda:");
		jblValorVenda.setHorizontalAlignment(SwingConstants.RIGHT);
		jblValorVenda.setBounds(219, 143, 101, 14);
		jpMercadoria.add(jblValorVenda);

		jtfValorNotaFiscal = new JNumberFormatField(new DecimalFormat("R$ 0.00")){    
			private static final long serialVersionUID = 1L;
			{   
                setLimit(6);    
            }    
        };
		
		jtfValorNotaFiscal.setToolTipText("Valor de entrada da mercadoria");
		jtfValorNotaFiscal.setBounds(109, 140, 100, 20);
		jpMercadoria.add(jtfValorNotaFiscal);


		jtfValorVenda = new JNumberFormatField(new DecimalFormat("R$ 0.00")){    
			private static final long serialVersionUID = 1L;
			{   
                setLimit(6);    
            }    
        };
		jtfValorVenda.setToolTipText("Valor de venda da Mercadoria - 30% acima do valo da NF");
		jtfValorVenda.setBounds(330, 140, 98, 20);
		jpMercadoria.add(jtfValorVenda);


		jlbTempoMedio = new JLabel("Tempo Medio:");
		jlbTempoMedio.setHorizontalAlignment(SwingConstants.RIGHT);
		jlbTempoMedio.setBounds(231, 83, 88, 14);
		jpMercadoria.add(jlbTempoMedio);

		jtfTempoMedio = new JTextField ();
		jtfTempoMedio.setToolTipText("Tempo medio gasto na execu\u00E7\u00E3o do servico.");
		jtfTempoMedio.setBounds(330, 81, 98, 20);
		jpMercadoria.add(jtfTempoMedio);


		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(463, 120, 112, 43);
		btnLimpar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpaCampos();
				btnRadioGroup.clearSelection();
			}
		});

		jpMercadoria.add(btnLimpar);

	}
	
	private void jtbTabela_Mercadorias() {
		jpTabela = new JPanel();
		jpTabela.setBounds(8, 190, 613, 278);

		tabelaMercadoria = new JTable();
		String[] colunas = new String[]{};
		String[][] dados = new String[][]{{},{}};
		DefaultTableModel model = new DefaultTableModel(dados , colunas );
		tabelaMercadoria.setModel(model);
        
		ListSelectionModel selectionModel = tabelaMercadoria.getSelectionModel();  
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
        
        selectionModel.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()){
					ListSelectionModel model = tabelaMercadoria.getSelectionModel();
					int index = model.getLeadSelectionIndex();
					carregandoCampos(carregarMercadoria(index));

				}
			} 
		});
			
		JScrollPane scroll = new JScrollPane();
		scroll.setPreferredSize(new Dimension(610, 270));
		scroll.setViewportView(tabelaMercadoria);
		jpTabela.add(scroll);
		contentPane.add(jpTabela);
	}

	protected Object[] carregarMercadoria(int index) {
		Object[] arr = new Object[6];
		if(index != -1){
			btnSalvar.setText("ALTERAR");
			btnRemover.setEnabled(true);
			arr[0]  = (Long) tabelaMercadoria.getValueAt(index, 0);
			arr[1]  = (String) tabelaMercadoria.getValueAt(index, 1);
			arr[2] = (String) tabelaMercadoria.getValueAt(index, 2);
			arr[3] = (BigDecimal) tabelaMercadoria.getValueAt(index, 4);
			if(jrbServico.isSelected()){
				arr[4] = (Float) tabelaMercadoria.getValueAt(index, 3);
				arr[5]  = (String) tabelaMercadoria.getValueAt(index, 5);
			}else{
				arr[4] = (BigDecimal) tabelaMercadoria.getValueAt(index, 3);
				arr[5] = (Integer) tabelaMercadoria.getValueAt(index, 5);
			}
		}else{
			btnSalvar.setText("Adicionar");
			btnRemover.setEnabled(false);
		}
		
		return arr;
	}

	private void carregandoCampos(Object[] mercadoria) {
		id_mercadoria = (Long) mercadoria[0];
		jtfDescricao.setText((String) mercadoria[1]);
		jCalendar.setDateFormatString((String) mercadoria[2]);
		jtfValorVenda.setText(""+(BigDecimal) mercadoria[3]);
		if(jrbServico.isSelected()){
			jtfTempoMedio.setText(""+(Float) mercadoria[4]);
		}else{
			jtfValorNotaFiscal.setText(""+(BigDecimal) mercadoria[4]);
			jtfEstoqueInicial.setText(""+(Integer) mercadoria[5]);

		}

	}

	private void ajustandoTamanhoTabela(JTable tabelaMercadoria) {

		DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
		centro.setHorizontalAlignment(JLabel.CENTER);

		DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
		direita.setHorizontalAlignment(JLabel.RIGHT);
		
		tabelaMercadoria.getColumnModel().getColumn(0).setPreferredWidth(0);
		tabelaMercadoria.getColumnModel().getColumn(0).setMinWidth(0);
		tabelaMercadoria.getColumnModel().getColumn(0).setMaxWidth(0);
		
		tabelaMercadoria.getColumnModel().getColumn(1).setPreferredWidth(270);
		tabelaMercadoria.getColumnModel().getColumn(2).setPreferredWidth(90);
		tabelaMercadoria.getColumnModel().getColumn(2).setCellRenderer(centro);
		tabelaMercadoria.getColumnModel().getColumn(3).setPreferredWidth(90);
		tabelaMercadoria.getColumnModel().getColumn(3).setCellRenderer(direita);
		tabelaMercadoria.getColumnModel().getColumn(4).setPreferredWidth(90);
		tabelaMercadoria.getColumnModel().getColumn(4).setCellRenderer(direita);
		tabelaMercadoria.getColumnModel().getColumn(5).setPreferredWidth(67);
		tabelaMercadoria.getColumnModel().getColumn(5).setCellRenderer(centro);
	}
	
	public void jtable_produto(List<?> mercadorias) {

		DefaultTableModel model = (DefaultTableModel) tabelaMercadoria.getModel();
		model.setColumnIdentifiers(titulosProduto);
		model.setNumRows(0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		ajustandoTamanhoTabela(tabelaMercadoria);
		if (mercadorias != null) {
			try {
				for (Object tipo : mercadorias) {
					model.addRow(new Object[] {
							((Produto) tipo).getId(),
							((Produto) tipo).getDescricao(),
							sdf.format(((Produto) tipo).getData_cadastro().getTime()),
							((Produto) tipo).getValor_nf(),
							((Produto) tipo).getValor_venda(),
							((Produto) tipo).getQtd_estoque(), });
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void jtable_servico(List<?> mercadorias) {

		DefaultTableModel model = (DefaultTableModel) tabelaMercadoria.getModel();
		model.setColumnIdentifiers(titulosServico);
		model.setNumRows(0);
		ajustandoTamanhoTabela(tabelaMercadoria);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		if (mercadorias != null) {
			try {
				for (Object tipo : mercadorias) {
					model.addRow(new Object[] {
							((Servico) tipo).getId(),
							((Servico) tipo).getDescricao(),
							sdf.format(((Servico) tipo).getData_cadastro().getTime()),
							((Servico) tipo).getTempo_medio(),
							((Servico) tipo).getValor_venda(), "0", });
				}
			} catch (Exception e) {

			}
		}
	}
	
	protected boolean validaCampos() {
		
		if(!jrbProduto.isSelected() && !jrbServico.isSelected()){
			JOptionPane.showMessageDialog(null,"Por favor escolha o tipo do Produto a ser cadastrado");
			return false;
		}
		
		if(jtfDescricao.getText().equals("")){
			JOptionPane.showMessageDialog(null,"Campo Descrição não pode ser nulo");
			return false;
		}
		if(jtfEstoqueInicial.getText().equals("") && jrbProduto.isSelected()){
			JOptionPane.showMessageDialog(null,"Campo Estoque deve conter um valor positivo");
			return false;
		}
		if(jCalendar.getDate() == null){
			JOptionPane.showMessageDialog(null,"Campo data de cadastro não pode ser nulo ou está no formato errado");
			return false;
		}
		if(jtfTempoMedio.getText().equals("") && jrbServico.isSelected()){
			JOptionPane.showMessageDialog(null,"Campo tempo medio não pode ser nulo");
			return false;
		}
		if(jtfValorNotaFiscal.getText().equals("") && jrbProduto.isSelected()){
			JOptionPane.showMessageDialog(null,"Campo de valor de NF não pode ser nulo");
			return false;
		}
		if(jtfValorVenda.getText().equals("")){
			JOptionPane.showMessageDialog(null,"Campo valor de venda não pode ser nulo");
			return false;
		}
		
		return true;
	}


	public void limpaCampos() {
		jtfDescricao.setText("");
		jtfEstoqueInicial.setText("");
		jCalendar.setDate(new Date());
		jtfValorNotaFiscal.setText("");
		jtfValorVenda.setText("");
		jtfTempoMedio.setText("");
		btnSalvar.setText("Adicionar");
		btnRemover.setEnabled(false);
		if (jrbProduto.isSelected()) {
			jtable_produto(null);
		}
		if (jrbServico.isSelected()) {
			jtable_servico(null);
		}
	}
	
	public void filtro(){
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Mercadoria mercadoria;
		//genericos
		String descricao = jtfDescricao.getText().toString();
		BigDecimal valor_venda = new BigDecimal(jtfValorVenda.getText().toString().replace("R$","").replace(",", ".").trim());
		Calendar cal_cadastro = Calendar.getInstance();
		cal_cadastro.setTime(jCalendar.getDate());
		//tipados
		BigDecimal valor_nf = null;
		Float tempo_medio = null;
		Integer estoque = null;
		if(jrbProduto.isSelected()){
			valor_nf = new BigDecimal(jtfValorNotaFiscal.getText().toString().replace("R$","").replace(",", ".").trim());
			estoque = new Integer(jtfEstoqueInicial.getText().toString());
			mercadoria = new Produto(id_mercadoria,descricao, cal_cadastro, valor_venda, estoque, valor_nf);
		}else{
			tempo_medio = new Float(jtfTempoMedio.getText().toString().replace(",",".").replace("hs","").replace("HS","").replace(":", "."));
			mercadoria = new Servico(id_mercadoria,descricao, cal_cadastro, valor_venda, tempo_medio, true);
		}
		boolean operacao = false;
		if(btnSalvar.getText().equals("ALTERAR")){
			operacao = dao.atualizarMercadoria(mercadoria);
		}else{
			operacao = dao.salvaMercadoria(mercadoria);
		}
		
		if(operacao){
			limpaCampos();
			atualizaTabela(mercadoria);
		}else{
			JOptionPane.showMessageDialog(null,"Erro na operação, verifique os dados digitados");
		}
	}

	public List<?> retornoMercadorias(Mercadoria mercadoriaDesejada) {
		return dao.getLista(mercadoriaDesejada);
	}
	
	public void atualizaTabela(Mercadoria mercadoria){
		if(mercadoria instanceof Produto){
			jtable_produto(retornoMercadorias(mercadoria));
		}else{
			jtable_servico(retornoMercadorias(mercadoria));
		}
	}
	
	private void ativaDesativaCamposPorMercadoria(boolean mercadoria) {
		jlbTempoMedio.setEnabled(!mercadoria);
		jtfTempoMedio.setEnabled(!mercadoria);
		jlbEstoqueInicial.setEnabled(mercadoria);
		jtfEstoqueInicial.setEnabled(mercadoria);
		jlbValorCadastro.setEnabled(mercadoria);
		jtfValorNotaFiscal.setEnabled(mercadoria);
	}
	
	protected void removerMercadoria(Long id) {
		Mercadoria tipo;
		if(jrbProduto.isSelected()){
			tipo = new Produto();
		}else{
			tipo = new Servico();
		}			
		dao.removeMercadoria(id,tipo);
		limpaCampos();
		atualizaTabela(tipo);
		btnRemover.setEnabled(false);
	}

}
