package com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import com.drogaria.dao.EstadoDAO;
import com.drogaria.domain.Estado;

@ManagedBean
@ViewScoped
public class EstadoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Estado estado;
	private List<Estado> estados;
	private EstadoDAO estadoDAO;

	@PostConstruct
	public void init() {
		estado = new Estado();
		estadoDAO = new EstadoDAO();
		listar();
	}

	public void salvar() {
		try {
			estadoDAO.merge(estado);
			Messages.addGlobalInfo("Estado salvo com sucesso.");
			estado = new Estado();
			listar();
		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao salvar o estado.");
			error.printStackTrace();
		}
	}

	public void listar() {
		try {
			estados = estadoDAO.listar();
		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao tentar listar os estados.");
			error.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
		try {
			estadoDAO.excluir(estado);
			Messages.addGlobalInfo("Estado excluído com sucesso.");
			listar();
		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao excluir estado.");
			error.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");		
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

}
