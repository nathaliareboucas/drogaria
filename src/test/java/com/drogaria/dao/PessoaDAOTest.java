package com.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.drogaria.domain.Pessoa;

public class PessoaDAOTest {

	@Test
	@Ignore
	public void salvar() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("João");
		pessoa.setCpf("123.123.123-00");
		pessoa.setRg("8764534579");
		pessoa.setRua("Rua Santos Dumont");
		pessoa.setNumero(new Short("145"));
		pessoa.setBairro("Aldeota");
		pessoa.setCep("60110-333");
		pessoa.setComplemento("A");
		pessoa.setTelefone("(88)3222-3432");
		pessoa.setCelular("(88)99999-9999");
		pessoa.setEmail("joao@email.com");

		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.salvar(pessoa);

		System.out.println("Registro salvo com sucesso");
	}

	@Test
	@Ignore
	public void listar() {
		PessoaDAO pessoaDAO = new PessoaDAO();
		List<Pessoa> resultado = pessoaDAO.listar();

		if (resultado.isEmpty()) {
			System.out.println("Nenhum registro encontrado.");
		} else {
			for (Pessoa pessoa : resultado) {
				System.out.println("Nome: " + pessoa.getNome() + " - CPF: " + pessoa.getCpf());
			}
		}
	}

	@Test
	@Ignore
	public void buscar() {
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(1L);

		if (pessoa == null) {
			System.out.println("Registro não encontrado.");
		} else {
			System.out.println("Nome: " + pessoa.getNome() + " - CPF: " + pessoa.getCpf());
		}
	}

	@Test
	@Ignore
	public void excluir() {
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(2L);

		if (pessoa == null) {
			System.out.println("Registro não encontrado.");
		} else {
			pessoaDAO.excluir(pessoa);
			System.out.println("Registro excluído com sucesso.");
		}
	}
	
	@Test
	@Ignore
	public void editar() {
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(1L);

		if (pessoa == null) {
			System.out.println("Registro não encontrado.");
		} else {
			pessoa.setEmail("joao@gmail.com");
			pessoaDAO.editar(pessoa);
			System.out.println("Registro alterado com sucesso.");
		}
	}

}
