package br.com.petserv.facade;

import java.util.List;

import br.com.petserv.dao.ProdutoDao;
import br.com.petserv.dao.ServicoDao;
import br.com.petserv.entidades.Produto;
import br.com.petserv.interfaces.Mercadoria;
import br.com.petserv.interfaces.Persistencia;

public class MercadoriaFacade {
	
	private Persistencia dao;
	private Persistencia daoP = new ProdutoDao();
	private Persistencia daoS = new ServicoDao();

	private Persistencia avaliaMercadoria(Mercadoria mercadoria){
		if(mercadoria instanceof Produto){
			dao = daoP;
		}else{
			dao = daoS;
		}
		return dao;
	}
	
	public boolean salvaMercadoria(Mercadoria mer){
		avaliaMercadoria(mer);
		return dao.inserirMercadoria(mer);
	}
	public void removeMercadoria(Long id,Mercadoria mer){
		avaliaMercadoria(mer);
		dao.removerMercadoria(id);
	}
	public boolean atualizarMercadoria(Mercadoria mer){
		avaliaMercadoria(mer);
		return dao.atualizarMercadoria(mer);
	}
	
	public List<?> getLista(Mercadoria tipo){
		avaliaMercadoria(tipo);
		return dao.getListaMercadorias();
	}
	
	public Mercadoria getMercadoria(Long id){
		return dao.getMercadoria(id);
	}
}
