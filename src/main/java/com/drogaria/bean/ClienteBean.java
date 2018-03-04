package com.drogaria.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import com.drogaria.dao.ClienteDAO;
import com.drogaria.dao.PessoaDAO;
import com.drogaria.domain.Cliente;
import com.drogaria.domain.Pessoa;

@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cliente cliente;
	private ClienteDAO clienteDAO;
	private List<Cliente> clientes;
	private List<Pessoa> pessoas;
	private Cliente clienteSelecionado;

	@PostConstruct
	public void init() {
		clienteDAO = new ClienteDAO();
		listar();
	}

	public void salvar() {
		Date dataCadastro = new Date();
		cliente.setDataCadastro(dataCadastro);
		try {
			clienteDAO.merge(cliente);
			novo();
			listar();
			Messages.addGlobalInfo("Cliente salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao salvar o cliente");
			erro.printStackTrace();
		}
	}

	public void listar() {
		try {
			clientes = clienteDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao listar os clientes");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
		listarPessoas();
	}

	public void excluir(ActionEvent evento) {
		cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
		try {
			clienteDAO.excluir(cliente);
			Messages.addGlobalInfo("Cliente excuído com sucesso.");
			listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar excluir o cliente.");
			erro.printStackTrace();
		}
	}

	public void listarPessoas() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao listar as pessoas");
			erro.printStackTrace();
		}
	}

	public void novo() {
		cliente = new Cliente();
		listarPessoas();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}
}