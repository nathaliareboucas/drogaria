package com.drogaria.dao;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.drogaria.domain.Funcionario;
import com.drogaria.domain.Pessoa;

public class FuncionarioDAOTest {

	@Test
	@Ignore
	public void salvar() {
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(2L);

		Funcionario funcionario = new Funcionario();
		funcionario.setPessoa(pessoa);
		funcionario.setCarteiraTrabalho("123456789-00");
		funcionario.setDataAdmissao(new Date());

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarioDAO.salvar(funcionario);

		System.out.println("Registro salvo com sucesso.");
	}

	@Test
	@Ignore
	public void listar() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		List<Funcionario> resultado = funcionarioDAO.listar();

		if (resultado.isEmpty()) {
			System.out.println("Nenhum registro encontrado");
		} else {
			for (Funcionario funcionario : resultado) {
				System.out.println("Nome: " + funcionario.getPessoa().getNome() + " - Carteira de trabalho: "
						+ funcionario.getCarteiraTrabalho());
			}
		}
	}
	
	@Test
	@Ignore
	public void buscar() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(1L);
		
		if (funcionario == null) {
			System.out.println("Registro não encontrado.");
		} else {
			System.out.println("Nome: " + funcionario.getPessoa().getNome() + " - Carteira de trabalho: "
					+ funcionario.getCarteiraTrabalho());
		}
	}

	@Test
	@Ignore
	public void excluir() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(2L);
		
		if (funcionario == null) {
			System.out.println("Registro não encontrado.");
		} else {
			funcionarioDAO.excluir(funcionario);
			System.out.println("Registro excluído com sucesso.");
		}
	}
	
	@Test
	@Ignore
	public void editar() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(3L);
		
		if (funcionario == null) {
			System.out.println("Registro não encontrado.");
		} else {
			funcionario.setCarteiraTrabalho("2222224444-00");
			funcionarioDAO.editar(funcionario);
			System.out.println("Registro alterado com sucesso.");
		}
	}
}
