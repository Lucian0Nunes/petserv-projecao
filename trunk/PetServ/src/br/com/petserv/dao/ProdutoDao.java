package br.com.petserv.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.petserv.entidades.Produto;
import br.com.petserv.interfaces.Mercadoria;
import br.com.petserv.interfaces.Persistencia;
import br.com.petserv.util.FabricaConexao;

public class ProdutoDao implements Persistencia{

	private Connection connection;
	private java.sql.PreparedStatement ptmt;
	private ResultSet rs;

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = FabricaConexao.getInstance().getConnection();
		return conn;
	}
	
	@Override
	public boolean inserirMercadoria(Object mercadoria) {
		Produto produto = (Produto) mercadoria;
		boolean operacao = false;
		try {
			
			connection = getConnection();
			String query = ("INSERT INTO produto (ds_descricao, dt_cadastro, qt_qtdestoque, vr_venda, vr_compra) VALUES (?,?,?,?,?)");
			ptmt = connection.prepareStatement(query);
			
			ptmt.setString(1, produto.getDescricao());
			ptmt.setDate(2, new Date(produto.getData_cadastro().getTimeInMillis()));
			ptmt.setInt(3, produto.getQtd_estoque());
			ptmt.setBigDecimal(4, produto.getValor_venda());
			ptmt.setBigDecimal(5, produto.getValor_nf());
			int a = ptmt.executeUpdate();
			if(a != 0){
				operacao = true;
			}

		} catch (SQLException e) {
			System.out.println("erro ao inserir produto na base de dados");
			e.printStackTrace();
			operacao = false;
		} finally {
			try {
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("erro ao inserir produto na base de dados");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("erro ao inserir produto na base de dados");
			}
		}
		return operacao;
	}

	@Override
	public boolean removerMercadoria(Long id) {

		boolean operacao = false;
		try {
			String queryString = "DELETE FROM produto WHERE id_produto = ?;";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setLong(1, id);
			int a = ptmt.executeUpdate();
			if(a != 0){
				operacao = true;
			}
		} catch (SQLException e) {
			System.out.println("erro ao remover produto na base de dados");
			operacao = false;
		} finally {
			try {
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.out.println("erro ao remover produto na base de dados");
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("erro ao remover produto na base de dados");
				e.printStackTrace();
			}
		}
		return operacao;
	}

	@Override
	public boolean atualizarMercadoria(Object mercadoria) {
		Produto produto = (Produto) mercadoria;
		boolean operacao = false;
		try {
			String queryString = "UPDATE produto SET ds_descricao = ?, dt_cadastro = ?, qt_qtdestoque = ?, vr_venda = ?, vr_compra = ? WHERE id_produto = ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, produto.getDescricao());
			ptmt.setDate(2,new Date(produto.getData_cadastro().getTimeInMillis()));
			ptmt.setInt(3, produto.getQtd_estoque());
			ptmt.setBigDecimal(4, produto.getValor_venda());
			ptmt.setBigDecimal(5, produto.getValor_nf());
			ptmt.setLong(6,produto.getId());
			int a = ptmt.executeUpdate();
			if(a != 0){
				operacao = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			}catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
			  e.printStackTrace();
			}
		}
		return operacao;
	}

	@Override
	public List<Produto> getListaMercadorias() {
		List<Produto> lista = new ArrayList<Produto>();
		try {
			String queryString = "SELECT * FROM produto";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			rs = ptmt.executeQuery();
			while (rs.next()) {
				Produto produto = new Produto();
				Calendar cal = new GregorianCalendar();
				produto.setId(rs.getLong("id_produto"));
				produto.setDescricao(rs.getString("ds_descricao"));
				produto.setQtd_estoque(rs.getInt("qt_qtdestoque"));
				produto.setValor_venda(rs.getBigDecimal("vr_venda"));
				produto.setValor_nf(rs.getBigDecimal("vr_compra"));
				cal.setTime((rs.getDate("dt_cadastro")));
				produto.setData_cadastro(cal);
				lista.add(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return lista;
	}

	@Override
	public Mercadoria getMercadoria(Long id) {
		Produto produto = new Produto();
		try {
			String queryString = "SELECT * FROM produto WHERE id_produto = ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setLong(1, id);

			rs = ptmt.executeQuery();
			while (rs.next()) {
				Calendar cal = new GregorianCalendar();
				produto.setId(rs.getLong("id_produto"));
				produto.setDescricao(rs.getString("ds_descricao"));
				produto.setQtd_estoque(rs.getInt("qt_qtdestoque"));
				produto.setValor_venda(rs.getBigDecimal("vr_venda"));
				produto.setValor_nf(rs.getBigDecimal("vr_compra"));
				cal.setTime((rs.getDate("dt_cadastro")));
				produto.setData_cadastro(cal);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			}catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
			  e.printStackTrace();
			}
		}
		return produto;
	}

}
