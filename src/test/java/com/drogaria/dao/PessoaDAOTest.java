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
		pessoa.setNome("Maria");
		pessoa.setCpf("123.444.123-00");
		pessoa.setRg("12345234579");
		pessoa.setRua("Rua Pinto Madeira");
		pessoa.setNumero(new Short("2345"));
		pessoa.setBairro("Centro");
		pessoa.setCep("60110-123");
		pessoa.setComplemento("A");
		pessoa.setTelefone("(88)3254-1212");
		pessoa.setCelular("(88)98888-7899");
		pessoa.setEmail("maria@email.com");

		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.salvar(pessoa);

		System.out.println("Registro salvo com sucesso");
	}

	@Test
	@Ignore
	public void listar() {
		PessoaDAO pessoaDAO = new PessoaDAO();
		List<Pessoa> resultado = pessoaDAO.listar();

		if (resultado == null) {
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
