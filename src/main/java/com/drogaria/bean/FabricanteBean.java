package com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import com.drogaria.dao.FabricanteDAO;
import com.drogaria.domain.Fabricante;

@ManagedBean
@ViewScoped
public class FabricanteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Fabricante fabricante;
	private List<Fabricante> fabricantes;
	private FabricanteDAO fabricanteDAO;

	@PostConstruct
	public void init() {
		fabricanteDAO = new FabricanteDAO();
		listar();
	}

	public void salvar() {
		try {
			fabricanteDAO.merge(fabricante);
			Messages.addGlobalInfo("Fabricante salvo com sucesso.");
			fabricante = new Fabricante();
			listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar salvar o fabricante.");
			erro.printStackTrace();
		}
	}

	public void listar() {
		try {
			fabricantes = fabricanteDAO.listar("descricao");
		} catch (RuntimeException erro) {
			Messages.addGlobalInfo("Erro ao tentar listar os fabricantes.");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");
		try {
			fabricanteDAO.excluir(fabricante);
			Messages.addGlobalInfo("Fabricante exclído com sucesso.");
			listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao excluir o fabricante.");
			erro.printStackTrace();
		}
	}

	public void novo() {
		fabricante = new Fabricante();
	}

	public void editar(ActionEvent evento) {
		fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

}
