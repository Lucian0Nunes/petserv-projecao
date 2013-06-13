package br.com.petserv.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import br.com.petserv.interfaces.Mercadoria;

public class Servico implements Serializable,Mercadoria{
	
	private static final long serialVersionUID = 5814287226357256014L;

	private Long id;
	private String descricao;
	private Calendar data_cadastro;
	private BigDecimal valor_venda;
	
	private Float tempo_medio;
	private boolean disponivel;
	
	public Servico() {
	
	}
	
	public Servico(Long id_mercadoria,String descricao,Calendar data_cadastro,BigDecimal valor_venda,Float tempo_medio,boolean disponivel) {
		setId(id_mercadoria);
		setDescricao(descricao);
		setData_cadastro(data_cadastro);
		setValor_venda(valor_venda);
		setTempo_medio(tempo_medio);
		setDisponivel(disponivel);
	}	
	
	public Long getId() {
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
	public Float getTempo_medio() {
		return tempo_medio;
	}
	public void setTempo_medio(Float tempo_medio) {
		this.tempo_medio = tempo_medio;
	}
	public boolean isDisponivel() {
		return disponivel;
	}
	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
	
	public BigDecimal getValor_venda() {
		return valor_venda;
	}

	public void setValor_venda(BigDecimal valor_venda) {
		this.valor_venda = valor_venda;
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
		Servico other = (Servico) obj;
		if (id != other.id)
			return false;
		return true;
	}

	

}
