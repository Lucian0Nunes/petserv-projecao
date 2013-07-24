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
	
	public boolean salvaCliente(Cliente cliente) throws SQLException {		
		return dao.inserirCliente(cliente);

	}

	public void removeCliente(Long id) {
		enderecoDao.removerEndereco(id);
		dao.removerCliente(id);

	}

	public boolean atualizarCliente(Cliente cliente) {
		
		return dao.atualizarCliente(cliente) && enderecoDao.atualizarEndereco(cliente.getEndereco());
	}

	public List<Cliente> getLista(Cliente cliente) {
		
		return dao.getListaClientes();
	}
	
	public List<Endereco> getListEnderecos(Cliente cliente){
		return enderecoDao.getListaEnderecos();
	}

	public Cliente getCliente(Long id) {
		
		return dao.getCliente(id);
	}
	public Endereco getEndereco(Long id) {		
		return (Endereco) enderecoDao.getEndereco(id);
	}

}
