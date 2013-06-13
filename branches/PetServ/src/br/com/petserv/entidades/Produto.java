package br.com.petserv.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import br.com.petserv.interfaces.Mercadoria;

public class Produto implements Serializable,Mercadoria{
	
	private static final long serialVersionUID = 6808822915566782991L;

	private Long id;
	private String descricao;
	private Calendar data_cadastro;
	private BigDecimal valor_venda;
	
	private Integer qtd_estoque;
	private BigDecimal valor_nf;
	
	public Produto() {
	
	}
	
	public Produto(Mercadoria mercadoria) {
		this.id = ((Produto)mercadoria).getId();
		this.descricao = ((Produto)mercadoria).getDescricao();
		this.data_cadastro = ((Produto)mercadoria).getData_cadastro();
		this.valor_venda = ((Produto)mercadoria).getValor_venda();
		this.qtd_estoque = ((Produto)mercadoria).getQtd_estoque();
		this.valor_nf = ((Produto)mercadoria).getValor_nf();
	}
	
	
	public Produto(Long id_mercadoria,String descricao,Calendar data_cadastro,BigDecimal valor_venda,int qtd_estoque,BigDecimal valor_nf) {
		this.id = id_mercadoria;
		this.descricao = descricao;
		this.data_cadastro = data_cadastro;
		this.valor_venda = valor_venda;
		this.qtd_estoque = qtd_estoque;
		this.valor_nf = valor_nf;
	}
	
	public long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Calendar getData_cadastro() {
		return data_cadastro;
	}
	public void setData_cadastro(Calendar data_cadastro) {
		this.data_cadastro = data_cadastro;
	}
	public int getQtd_estoque() {
		return qtd_estoque;
	}
	public void setQtd_estoque(Integer qtd_estoque) {
		this.qtd_estoque = qtd_estoque;
	}
	
	public BigDecimal getValor_venda() {
		return valor_venda;
	}

	public void setValor_venda(BigDecimal valor_venda) {
		this.valor_venda = valor_venda;
	}

	public BigDecimal getValor_nf() {
		return valor_nf;
	}

	public void setValor_nf(BigDecimal valor_nf) {
		this.valor_nf = valor_nf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	
}
