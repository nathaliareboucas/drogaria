package com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.drogaria.dao.VendaDAO;
import com.drogaria.domain.Venda;

@ManagedBean
@ViewScoped
public class VendasListagemBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Venda> vendas;
	private VendaDAO vendaDAO;

	@PostConstruct
	public void init() {
		vendaDAO = new VendaDAO();
		vendas = vendaDAO.listar();
	}

	public List<Venda> getVendas() {
		return vendas;
	}
}
