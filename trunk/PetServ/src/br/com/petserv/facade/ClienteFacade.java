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
		cdao.removerCliente(id);
		

	}

	public boolean atualizarCliente(Cliente cliente, Endereco endereco) {
		return edao.atualizarEndereco(endereco)
		&&  cdao.atualizarCliente(cliente);
	}

	public List<Cliente> getLista() {

		return cdao.getListaClientes();
	}

	public List<Endereco> getListEnderecos(Cliente cliente) {
		return edao.getListaEnderecos();
	}

	public Cliente getCliente(Long id) {
		return cdao.getCliente(id);
	}

	public Endereco getEndereco(Cliente cliente) {
		return edao.getEndereco(cliente.getEndereco().getId_endereco());
	}

}
