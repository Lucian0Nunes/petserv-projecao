package br.com.petserv.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import br.com.petserv.entidades.Endereco;
import br.com.petserv.util.FabricaConexao;

public class EnderecoDao {

	private Connection connection;
	private java.sql.PreparedStatement ptmt;
	private ResultSet rs;

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = FabricaConexao.getInstance().getConnection();
		return conn;
	}

	public Long inserirEndereco(Object obj) {

		Long id = -1l;

		try {
			Endereco endereco = (Endereco) obj;
			connection = getConnection();
			ResultSet rs = null;
			String query = ("INSERT INTO endereco (str_endereco, str_complemento, str_bairro, str_cidade, str_cep) VALUES (?,?,?,?,?)");
			ptmt = connection.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);

			ptmt.setString(1, endereco.getDescricao());
			ptmt.setString(3, endereco.getComplemento());
			ptmt.setString(2, endereco.getBairro());
			ptmt.setString(4, endereco.getCidade());
			ptmt.setString(5, endereco.getCep());

			ptmt.executeUpdate();
			rs = ptmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				id = rs.getLong(1);

			}

		} catch (SQLException e) {
			System.out.println("erro ao inserir o endere�o 1" + e);
			e.printStackTrace();
		}
		return id;
	}

	public boolean removerEndereco(Long id) {

		boolean operacao = false;
		try {
			String queryString = "DELETE FROM endereco WHERE id_endereco = ?;";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setLong(1, id);
			int a = ptmt.executeUpdate();
			if (a != 0) {
				operacao = true;
			}
		} catch (SQLException e) {
			System.out.println("erro ao remover cliente(endere�o)" + e);
			operacao = false;
		} finally {
			try {
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.out.println("erro ao remover cliente(endere�o)");
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("erro ao remover cliente(endere�o)");
				e.printStackTrace();
			}
		}
		return operacao;
	}

	public boolean atualizarEndereco(Long id) {
		boolean operacao = false;
		Endereco endereco = new Endereco();
		try {

			String queryString = "UPDATE endereco SET str_endereco = ?, str_complemento = ?, str_bairro = ?, str_cidade = ?, str_cep = ? WHERE id_endereco = ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, endereco.getDescricao());
			ptmt.setString(2, endereco.getComplemento());
			ptmt.setString(3, endereco.getBairro());
			ptmt.setString(4, endereco.getCidade());
			ptmt.setString(5, endereco.getCep());
			ptmt.setLong(6, id);

			int a = ptmt.executeUpdate();
			if (a != 0) {
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
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return operacao;
	}

	public List<Endereco> getListaEnderecos() {
		List<Endereco> lista = new ArrayList<Endereco>();
		try {
			String queryString = "SELECT * FROM endereco";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			rs = ptmt.executeQuery();
			while (rs.next()) {
				Endereco endereco = new Endereco();

				endereco.setId_endereco(rs.getLong("id_endereco"));
				endereco.setDescricao(rs.getString("str_endereco"));
				endereco.setComplemento(rs.getString("str_complemento"));
				endereco.setBairro(rs.getString("str_bairro"));
				endereco.setCidade(rs.getString("str_cidade"));
				endereco.setCep(rs.getString("str_cep"));

				lista.add(endereco);
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

	public Endereco getEndereco(Long id) {
		Endereco endereco = new Endereco();

		try {
			String queryString = "SELECT * FROM endereco WHERE id_endereco = ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setLong(1, id);

			rs = ptmt.executeQuery();
			while (rs.next()) {

				endereco.setId_endereco(rs.getLong("id_endereco"));
				endereco.setDescricao(rs.getString("str_endereco"));
				endereco.setComplemento(rs.getString("str_complemento"));
				endereco.setBairro(rs.getString("str_bairro"));
				endereco.setCidade(rs.getString("str_cidade"));
				endereco.setCep(rs.getString("str_cep"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
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
		return endereco;
	}

}
