package com.drogaria.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.drogaria.domain.Cliente;
import com.drogaria.domain.Pessoa;

public class ClienteDAOTest {

	@Test
	@Ignore
	public void salvar() throws ParseException {
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(2L);

		Cliente cliente = new Cliente();
		cliente.setPessoa(pessoa);
		cliente.setDataCadastro(new Date());
		cliente.setLiberado(false);

		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.salvar(cliente);

		System.out.println("Registro salvo com sucesso.");
	}

	@Test
	@Ignore
	public void listar() {
		ClienteDAO clienteDAO = new ClienteDAO();
		List<Cliente> resultado = clienteDAO.listar();

		if (resultado.isEmpty()) {
			System.out.println("Nenhum registro encontrado.");
		} else {
			for (Cliente cliente : resultado) {
				System.out.println(
						"Nome: " + cliente.getPessoa().getNome() + " - Data de cadastro: " + cliente.getDataCadastro());
			}
		}
	}

	@Test
	@Ignore
	public void buscar() {
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(1L);

		if (cliente == null) {
			System.out.println("Registro não encontrado.");
		} else {
			System.out.println(
					"Nome: " + cliente.getPessoa().getNome() + " - Data de cadastro: " + cliente.getDataCadastro());

		}
	}
	
	@Test
	@Ignore
	public void excluir() {
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(2L);

		if (cliente == null) {
			System.out.println("Registro não encontrado.");
		} else {
			clienteDAO.excluir(cliente);
			System.out.println("Registro excluído com sucesso.");
		}
	}
	
	@Test
	@Ignore
	public void editar() {
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(1L);

		if (cliente == null) {
			System.out.println("Registro não encontrado.");
		} else {
			cliente.setLiberado(false);
			clienteDAO.editar(cliente);
			System.out.println("Registro alterado com sucesso.");
		}
	}
}
