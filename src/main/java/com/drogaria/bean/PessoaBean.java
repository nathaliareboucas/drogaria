package com.drogaria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import com.drogaria.dao.CidadeDAO;
import com.drogaria.dao.EstadoDAO;
import com.drogaria.dao.PessoaDAO;
import com.drogaria.domain.Cidade;
import com.drogaria.domain.Estado;
import com.drogaria.domain.Pessoa;

@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pessoa pessoa;
	private PessoaDAO pessoaDAO;
	private List<Pessoa> pessoas;
	private List<Estado> estados;
	private EstadoDAO estadoDAO;
	private List<Cidade> cidades;
	private CidadeDAO cidadeDAO;
	private Estado estadoSelecionado;

	@PostConstruct
	public void init() {
		pessoaDAO = new PessoaDAO();
		listar();
	}
	
	public void salvar() {
		try {
			pessoaDAO.merge(pessoa);
			novo();
			listar();
			Messages.addGlobalInfo("Dados pessoais salvos com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao salvar os dados pessoas");
			erro.printStackTrace();
		}
	}

	public void listar() {
		try {
			pessoas = pessoaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao listar pessoas.");
			erro.printStackTrace();
		}
	}

	public void listarEstados() {
		estadoDAO = new EstadoDAO();
		try {
			estados = estadoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao listar os estados.");
			erro.printStackTrace();
		}
	}

	public void novo() {
		pessoa = new Pessoa();
		estadoSelecionado = null;
		listarEstados();
		cidades = new ArrayList<Cidade>();
	}

	public void popular() {
		if (estadoSelecionado != null) {
			try {
				cidadeDAO = new CidadeDAO();
				cidades = cidadeDAO.buscarPorEstado(estadoSelecionado.getCodigo());
				if (cidades.isEmpty()) {
					cidades = new ArrayList<Cidade>();
				}
			} catch (RuntimeException erro) {
				Messages.addGlobalError("Erro ao tentar filtrar as cidades");
				erro.printStackTrace();
			}
		} else {
			cidades = new ArrayList<Cidade>();
		}
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public Estado getEstadoSelecionado() {
		return estadoSelecionado;
	}

	public void setEstadoSelecionado(Estado estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}

}
