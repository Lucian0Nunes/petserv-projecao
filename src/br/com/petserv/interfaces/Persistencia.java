package br.com.petserv.interfaces;

import java.util.List;

public interface Persistencia{

	boolean inserirMercadoria(Object tipo_mercadoria);
	boolean removerMercadoria(Long id);
	boolean atualizarMercadoria(Object tipo_mercadoria);
	List<?> getListaMercadorias();
	Mercadoria getMercadoria(Long id);
	
	
}
