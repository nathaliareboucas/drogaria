package com.drogaria.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.drogaria.domain.Fabricante;
import com.drogaria.domain.Produto;

public class ProdutoDAOTest {
	
	@Test
	@Ignore
	public void salvar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(3L);
		
		Produto produto = new Produto();
		produto.setDescricao("Desodorante");
		produto.setFabricante(fabricante);
		produto.setQuantidade(new Short("5"));
		produto.setPreco(new BigDecimal("10.00"));
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);
		
		System.out.println("Registro salvo com sucesso.");
	}
	
	@Test
	@Ignore
	public void listar() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> resultado = produtoDAO.listar();
		
		if (resultado.isEmpty()) {
			System.out.println("Nenhum registro encontrado.");
		} else {
			for (Produto produto:resultado) {
				System.out.println("Quantidade: " + produto.getQuantidade() + " - Descrição: " + produto.getDescricao());
				System.out.println("Fabricante: " + produto.getFabricante().getDescricao() + " - Preço: " + produto.getPreco());
				System.out.println();
			}
		}
	}
	
	@Test
	@Ignore
	public void buscar() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(2L);
		
		if (produto == null) {
			System.out.println("Registro não encontrado.");
		} else {
			System.out.println("Quantidade: " + produto.getQuantidade() + " - Descrição: " + produto.getDescricao());
			System.out.println("Fabricante: " + produto.getFabricante().getDescricao() + " - Preço: " + produto.getPreco());
		}
	}
	
	@Test
	@Ignore
	public void excluir() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(3L);
		
		if (produto == null) {
			System.out.println("Registro não encontrado.");
		} else {
			produtoDAO.excluir(produto);
			System.out.println("Registro excluído com sucesso.");
		}
	}
	
	@Test
	@Ignore
	public void editar() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(2L);
		
		if (produto == null) {
			System.out.println("Registro não encontrado.");
		} else {
			produto.setDescricao("Sabonete");
			
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			Fabricante fabricante = fabricanteDAO.buscar(7L);
			
			produto.setFabricante(fabricante);
			produto.setPreco(new BigDecimal("2.00"));
			produtoDAO.editar(produto);
			System.out.println("Registro alterado com sucesso.");
		}
	}

}
