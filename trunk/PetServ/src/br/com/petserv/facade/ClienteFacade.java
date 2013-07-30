package br.com.petserv.facade;

import java.util.List;

import br.com.petserv.dao.ClienteDao;
import br.com.petserv.dao.EnderecoDao;
import br.com.petserv.entidades.Cliente;
import br.com.petserv.entidades.Endereco;

public class ClienteFacade {
	ClienteDao cdao = new ClienteDao();
	EnderecoDao edao = new EnderecoDao();

	public boolean salvaCliente(Cliente cliente) {
		boolean retorno = false;
		Long id = edao.inserirEndereco(cliente.getEndereco());
		cliente.getEndereco().setId_endereco(id);
		retorno = cdao.inserirCliente(cliente);
		return retorno;

	}

	public void removeCliente(Long id) {
		Cliente cliente = cdao.getCliente(id);
		long idEnd = cliente.getEndereco().getId_endereco();
		cdao.removerCliente(id);
		edao.removerEndereco(idEnd);

	}

	public boolean atualizarCliente(Long id) {
		Cliente cliente = cdao.getCliente(id);
		long idEnd = cliente.getEndereco().getId_endereco();
		return edao.atualizarEndereco(idEnd) && cdao.atualizarCliente(id);

	}

	public List<Cliente> getListaClientes() {

		return cdao.getListaClientes();
	}

	public List<Endereco> getListEnderecos() {
		return edao.getListaEnderecos();
	}

	public Cliente getCliente(Long id) {
		return cdao.getCliente(id);
	}

	public Endereco getEndereco(Long id) {
		return edao.getEndereco(id);
	}

}
