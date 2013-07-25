package br.com.petserv.entidades;



public class Endereco {
	
	private Long id_endereco;
	private String descricao;
	private String complemento;
	private String bairro;
	private String cidade;
	private String cep;
	
	
	public Endereco(){		
	}
	
	
	public Endereco(String endereco, String complemento,
			String bairro, String cidade, String cep) {
		setId_endereco(id_endereco);
		setDescricao(endereco);
		setComplemento(complemento);
		setBairro(bairro);
		setCidade(cidade);
		setCep(cep);
	}
	public Long getId_endereco() {
		return id_endereco;
	}
	public Long setId_endereco(Long id_endereco) {
		return this.id_endereco = id_endereco;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String endereco) {
		this.descricao = endereco;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}

}
