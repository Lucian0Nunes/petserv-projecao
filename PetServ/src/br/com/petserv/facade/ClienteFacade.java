package br.com.petserv.facade;

import java.sql.SQLException;
import java.util.List;

import br.com.petserv.dao.ClienteDao;
import br.com.petserv.dao.EnderecoDao;
import br.com.petserv.entidades.Cliente;
import br.com.petserv.entidades.Endereco;

public class ClienteFacade {
	ClienteDao dao = new ClienteDao();
	EnderecoDao enderecoDao = new EnderecoDao();

	public boolean salvaCliente(Cliente cliente, Endereco endereco)
			throws SQLException {
		return enderecoDao.inserirEndereco(endereco)
				&& dao.inserirCliente(cliente);

	}

	public void removeCliente(Long id) {
		dao.removerCliente(id);
		

	}

	public boolean atualizarCliente(Cliente cliente, Endereco endereco) {
		return enderecoDao.atualizarEndereco(endereco)
		&&  dao.atualizarCliente(cliente);
	}

	public List<Cliente> getLista(Cliente cliente) {

		return dao.getListaClientes();
	}

	public List<Endereco> getListEnderecos(Cliente cliente) {
		return enderecoDao.getListaEnderecos();
	}

	public Cliente getCliente(Long id) {
		return dao.getCliente(id);
	}

	public Endereco getEndereco(Cliente cliente) {
		return enderecoDao.getEndereco(cliente.getFkEndereco());
	}

}
