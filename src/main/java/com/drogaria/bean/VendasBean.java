package com.drogaria.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import com.drogaria.dao.ClienteDAO;
import com.drogaria.dao.FuncionarioDAO;
import com.drogaria.dao.ProdutoDAO;
import com.drogaria.domain.Cliente;
import com.drogaria.domain.Funcionario;
import com.drogaria.domain.ItemVenda;
import com.drogaria.domain.Produto;
import com.drogaria.domain.Venda;

@ManagedBean
@ViewScoped
public class VendasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Produto> produtos;
	private ProdutoDAO produtoDAO;
	private List<ItemVenda> itensVenda;
	private Venda venda;
	private List<Cliente> clientes;
	private List<Funcionario> funcionarios;

	@PostConstruct
	public void init() {
		listarProdutos();
	}

	public void listarProdutos() {
		try {
			venda = new Venda();
			venda.setValorTotal(new BigDecimal("0.00"));

			produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar();

			itensVenda = new ArrayList<>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao listar os produtos.");
			erro.printStackTrace();
		}
	}

	public void adicionarNoCarrinho(ActionEvent evento) {
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

		int achou = -1;
		for (int pos = 0; pos < itensVenda.size(); pos++) {
			if (itensVenda.get(pos).getProduto().equals(produto)) {
				achou = pos;
			}
		}

		if (achou < 0) {
			ItemVenda itemVenda = new ItemVenda();
			itemVenda.setValorParcial(produto.getPreco());
			itemVenda.setProduto(produto);
			itemVenda.setQuantidade(new Short("1"));

			itensVenda.add(itemVenda);
		} else {
			ItemVenda itemVenda = itensVenda.get(achou);
			itemVenda.setQuantidade(new Short(itemVenda.getQuantidade() + 1 + ""));
			itemVenda.setValorParcial(produto.getPreco().multiply(new BigDecimal(itemVenda.getQuantidade())));
		}
		calcular();
	}

	public void removerDoCarrinho(ActionEvent evento) {
		ItemVenda itemVenda = (ItemVenda) evento.getComponent().getAttributes().get("itemSelecionado");

		int achou = -1;

		for (int pos = 0; pos < itensVenda.size(); pos++) {
			if (itensVenda.get(pos).getProduto().equals(itemVenda.getProduto())) {
				achou = pos;
			}
		}

		if (achou > -1) {
			itensVenda.remove(achou);
		}
		calcular();
	}

	public void calcular() {
		venda.setValorTotal(new BigDecimal("0.00"));

		for (int pos = 0; pos < itensVenda.size(); pos++) {
			ItemVenda itemVenda = itensVenda.get(pos);
			venda.setValorTotal(venda.getValorTotal().add(itemVenda.getValorParcial()));
		}
	}
	
	public void finalizar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listarOrdenado();
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listarOrdenado();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao listar funcionários / clientes");
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

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
}
