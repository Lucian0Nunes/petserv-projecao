package br.com.petserv.entidades;

import java.util.Calendar;

import br.com.petserv.dao.EnderecoDao;

public class Cliente {

	private Long idCliente;
	private String nome;
	private String cpf;
	private String telefone;
	private String email;
	private Calendar data_cadastro;
	private Long fkEndereco;

	public Cliente() {

	}

	public Cliente(Long idCliente, String nome, String cpf, String telefone,
			String email, Calendar data_cadastro, Endereco Endereco) {

		setIdCliente(idCliente);
		setNome(nome);
		setCpf(cpf);
		setTelefone(telefone);
		setEmail(email);
		setData_cadastro(data_cadastro);
		setFkEndereco(Endereco);
	}

	

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Calendar getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Calendar data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public Long getFkEndereco() {
		return fkEndereco;
	}

	public void setFkEndereco(Endereco endereco) {	
		EnderecoDao dao  = new EnderecoDao();
		this.fkEndereco = dao.getIdEndereco(endereco);
		
	}

}
