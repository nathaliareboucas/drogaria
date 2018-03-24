
package com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import com.drogaria.dao.FabricanteDAO;
import com.drogaria.dao.ProdutoDAO;
import com.drogaria.domain.Fabricante;
import com.drogaria.domain.Produto;

@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Produto produto;
	private List<Produto> produtos;
	private ProdutoDAO produtoDAO;
	private List<Fabricante> fabricantes;
	private FabricanteDAO fabricanteDAO;

	@PostConstruct
	public void init() {
		produtoDAO = new ProdutoDAO();
		listar();
	}

	public void salvar() {
		try {
			produtoDAO.merge(produto);
			produto = new Produto();
			listar();
			Messages.addGlobalInfo("Produto salvo com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao salvar produto");
			erro.printStackTrace();
		}

	}
	
	public void editar(ActionEvent evento) {
		produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
		listarFabricantes();
	}

	public void excluir(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			produtoDAO.excluir(produto);
			Messages.addGlobalInfo("Produto excluído com sucesso");
			listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao excluir o produto");
			erro.printStackTrace();
		}		
	}
	
	public void listar() {
		try {
			produtos = produtoDAO.listar("descricao");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao listar os produtos.");
			erro.printStackTrace();
		}
	}

	public void listarFabricantes() {
		try {
			fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao listar os fabricantes.");
			erro.printStackTrace();
		}
	}

	public void novo() {
		produto = new Produto();
		listarFabricantes();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

}
