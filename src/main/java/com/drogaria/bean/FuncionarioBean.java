package com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import com.drogaria.dao.FuncionarioDAO;
import com.drogaria.dao.PessoaDAO;
import com.drogaria.domain.Funcionario;
import com.drogaria.domain.Pessoa;

@ManagedBean
@ViewScoped
public class FuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Funcionario funcionario;
	private List<Funcionario> funcionarios;
	private FuncionarioDAO funcionarioDAO;
	private List<Pessoa> pessoas;
	private PessoaDAO pessoaDAO;

	@PostConstruct
	public void init() {
		funcionarioDAO = new FuncionarioDAO();
		listar();
	}

	public void listar() {
		try {
			funcionarios = funcionarioDAO.listarOrdenado();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao listar os funcionários");
			erro.printStackTrace();
		}
	}
	
	public void salvar() {
		try {		
			funcionarioDAO.merge(funcionario);
			Messages.addGlobalInfo("Funcionário salvo com sucesso");
			novo();
			listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao salvar o funcionário");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");
		listarPessoas();
	}
	
	public void excluir(ActionEvent evento) {
		funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");
		try {
			funcionarioDAO.excluir(funcionario);
			Messages.addGlobalInfo("Funcionário excluído com sucesso");
			listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao excluir o funcionário");
			erro.printStackTrace();
		}
	}

	public void novo() {
		funcionario = new Funcionario();
		listarPessoas();
	}
	
	public void listarPessoas() {
		try {
			pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao listar as pessoas");
			erro.printStackTrace();
		}
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

}
