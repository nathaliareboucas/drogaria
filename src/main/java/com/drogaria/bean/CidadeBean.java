package com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import com.drogaria.dao.CidadeDAO;
import com.drogaria.dao.EstadoDAO;
import com.drogaria.domain.Cidade;
import com.drogaria.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable {

	private Cidade cidade;
	private List<Cidade> cidades;
	private CidadeDAO cidadeDAO;
	private List<Estado> estados;
	private EstadoDAO estadoDAO;

	@PostConstruct
	public void init() {
		cidade = new Cidade();
		listar();
	}
	
	public void listar() {
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as cidades");
			erro.printStackTrace();
		}
	}

	public void listaEstados() {
		try {
			estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao listar os estados.");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			cidadeDAO = new CidadeDAO();
			cidadeDAO.merge(cidade);
			cidade = new Cidade();
			listar();
			Messages.addGlobalInfo("Cidade salva com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar a cidade");
			erro.printStackTrace();
		}
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public List<Estado> getEstados() {
		return estados;
	}
}