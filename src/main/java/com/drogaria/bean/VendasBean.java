package com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import com.drogaria.dao.ProdutoDAO;
import com.drogaria.domain.ItemVenda;
import com.drogaria.domain.Produto;

@ManagedBean
@ViewScoped
public class VendasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Produto> produtos;
	private ProdutoDAO produtoDAO;
	private List<ItemVenda> itensVenda;

	@PostConstruct
	public void init() {
		listarProdutos();
	}

	public void listarProdutos() {
		try {
			produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao listar os produtos.");
			erro.printStackTrace();
		}
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}

}
