package br.com.petserv.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.petserv.entidades.Servico;
import br.com.petserv.interfaces.Mercadoria;
import br.com.petserv.interfaces.Persistencia;
import br.com.petserv.util.FabricaConexao;


public class ServicoDao implements Persistencia{

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
		Servico servico = (Servico) mercadoria;
		boolean operacao = false;
		try {
			
			connection = getConnection();
			String query = ("INSERT INTO servico (ds_descricao, dt_cadastro, tempo_medio, vr_venda, bol_disponibilidade) VALUES (?, ?, ?, ?, ?)");
			ptmt = connection.prepareStatement(query);
			ptmt.setString(1, servico.getDescricao());
			ptmt.setDate(2, new Date(servico.getData_cadastro().getTimeInMillis()));
			ptmt.setFloat(3, servico.getTempo_medio());
			ptmt.setBigDecimal(4, servico.getValor_venda());
			ptmt.setBoolean(5, servico.isDisponivel());
			int a = ptmt.executeUpdate();
			if(a != 0){
				operacao = true;
			}

		} catch (SQLException e) {
			System.out.println("erro ao inserir o servico na base de dados");
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
			String queryString = "DELETE FROM servico WHERE id_servico = ?;";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setLong(1, id);
			int a = ptmt.executeUpdate();
			if(a != 0){
				operacao = true;
			}
		} catch (SQLException e) {
			System.out.println("erro ao remover o servico na base de dados");
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
		Servico servico = (Servico) mercadoria;
		boolean operacao = false;
		try {
			String queryString = "UPDATE servico SET ds_descricao = ?, dt_cadastro = ?, tempo_medio = ?, vr_venda = ?, bol_disponibilidade = ? WHERE id_servico = ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, servico.getDescricao());
			ptmt.setDate(2,new Date(servico.getData_cadastro().getTimeInMillis()));
			ptmt.setFloat(3, servico.getTempo_medio());
			ptmt.setBigDecimal(4, servico.getValor_venda());
			ptmt.setBoolean(5, servico.isDisponivel());
			ptmt.setLong(6, servico.getId());
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
	public List<Servico> getListaMercadorias() {
		List<Servico> lista = new ArrayList<Servico>();
		try {
			String queryString = "SELECT * FROM servico";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			rs = ptmt.executeQuery();
			while (rs.next()) {
				Servico servico = new Servico();
				Calendar cal = new GregorianCalendar();
				servico.setId(rs.getLong("id_servico"));
				servico.setDescricao(rs.getString("ds_descricao"));
				servico.setDisponivel(rs.getBoolean("bol_disponibilidade"));
				servico.setValor_venda(rs.getBigDecimal("vr_venda"));
				servico.setTempo_medio(rs.getFloat("tempo_medio"));
				cal.setTime((rs.getDate("dt_cadastro")));
				servico.setData_cadastro(cal);
				lista.add(servico);
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
		Servico servico = new Servico();
		try {
			String queryString = "SELECT * FROM servico WHERE id_servico = ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setLong(1, id);

			rs = ptmt.executeQuery();
			while (rs.next()) {
				Calendar cal = new GregorianCalendar();
				servico.setId(rs.getLong("id_servico"));
				servico.setDescricao(rs.getString("ds_descricao"));
				servico.setDisponivel(rs.getBoolean("bol_disponibilidade"));
				servico.setValor_venda(rs.getBigDecimal("vr_venda"));
				servico.setTempo_medio(rs.getFloat("tempo_medio"));
				cal.setTime((rs.getDate("dt_cadastro")));
				servico.setData_cadastro(cal);
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
		return servico;
	}

	
}
