package br.com.petserv.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.petserv.entidades.Cliente;
import br.com.petserv.util.FabricaConexao;

public class ClienteDao {

	private Connection connection;
	private java.sql.PreparedStatement ptmt;
	private ResultSet rs;

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = FabricaConexao.getInstance().getConnection();
		return conn;
	}

	public boolean inserirCliente(Cliente cliente) {

		boolean operacao = false;

		try {
			connection = getConnection();
			
			EnderecoDao dao = new EnderecoDao();
			dao.inserirEndereco(cliente.getEndereco());
			
			Long id = (Long) dao.getEndereco(cliente.getEndereco().getId_endereco());
			
			
			 

			String query = ("INSERT INTO cliente (str_nome, str_email, str_telefone, dt_cadastro, str_cpf, fk_endereco) VALUES (?,?,?,?,?,?)");
			ptmt = connection.prepareStatement(query);

			ptmt.setString(1, cliente.getNome());
			ptmt.setString(2, cliente.getEmail());
			ptmt.setString(3, cliente.getTelefone());
			ptmt.setDate(4, new Date(cliente.getData_cadastro().getTimeInMillis()));
			ptmt.setLong(5, id);
			
//			Meu problema est� aqui
			ptmt.setLong(6, id);
			int a = ptmt.executeUpdate();
			if (a != 0) {
				operacao = true;
			}

		} catch (SQLException e) {
			System.out.println("erro ao inserir o Cliente");
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
				System.out.println("erro ao inserir o Cliente");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("erro ao inserir o Cliente");
			}
			
		}
		return operacao;

	}

	public boolean removerCliente(Long id) {

		boolean operacao = false;
		try {
			String queryString = "DELETE FROM cliente WHERE idcliente = ?;";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setLong(1, id);
			int a = ptmt.executeUpdate();
			if (a != 0) {
				operacao = true;
			}
		} catch (SQLException e) {
			System.out.println("erro ao remover cliente na base de dados");
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

	public boolean atualizarCliente(Cliente cliente) {
		boolean operacao = false;
		try {
			String queryString = "UPDATE cliente SET nome = ?, cpf = ?, telefone = ?, email = ?, data_cadastro = ? WHERE idcliente = ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, cliente.getNome());
			ptmt.setString(2, cliente.getCpf());
			ptmt.setString(3, cliente.getTelefone());
			ptmt.setString(4, cliente.getEmail());
			ptmt.setDate(5, new Date(cliente.getData_cadastro()
					.getTimeInMillis()));
			ptmt.setLong(6, cliente.getIdCliente());
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

	public List<Cliente> getListaClientes() {
		List<Cliente> lista = new ArrayList<Cliente>();
		try {
			String queryString = "SELECT * FROM cliente";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			rs = ptmt.executeQuery();
			while (rs.next()) {
				Cliente cliente = new Cliente();
				Calendar cal = new GregorianCalendar();
				cliente.setIdCliente(rs.getLong("id_cliente"));
				cliente.setNome(rs.getString("str_nome"));
				cliente.setCpf(rs.getString("str_cpf"));
				cliente.setTelefone(rs.getString("str_telefone"));
				cliente.setEmail(rs.getString("str_email"));
				cal.setTime((rs.getDate("dt_cadastro")));
				cliente.setData_cadastro(cal);
				lista.add(cliente);
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

	public Cliente getCliente(Long id) {
		Cliente cliente = new Cliente();
		try {
			String queryString = "SELECT * FROM cliente WHERE id_cliente = ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setLong(1, id);

			rs = ptmt.executeQuery();
			while (rs.next()) {
				Calendar cal = new GregorianCalendar();
				cliente.setIdCliente(rs.getLong("id_cliente"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setEmail(rs.getString("email"));
				cal.setTime((rs.getDate("dt_cadastro")));
				cliente.setData_cadastro(cal);
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
		return cliente;
	}

}
