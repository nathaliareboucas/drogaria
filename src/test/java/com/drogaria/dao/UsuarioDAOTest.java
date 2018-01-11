package com.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.drogaria.domain.Pessoa;
import com.drogaria.domain.Usuario;

public class UsuarioDAOTest {

	@Test
	@Ignore
	public void salvar() {
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(1L);

		Usuario usuario = new Usuario();
		usuario.setPessoa(pessoa);
		usuario.setTipo('A');
		usuario.setSenha("a1s2d3f4");
		usuario.setAtivo(true);

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);

		System.out.println("Registro salvo com sucesso.");
	}

	@Test
	@Ignore
	public void listar() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> resultado = usuarioDAO.listar();

		if (resultado.isEmpty()) {
			System.out.println("Nenhum registro encontrado.");
		} else {
			for (Usuario usuario : resultado) {
				System.out.println("Nome: " + usuario.getPessoa().getNome() + " - Ativo: " + usuario.getAtivo());
			}
		}
	}

	@Test
	@Ignore
	public void buscar() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(1L);

		if (usuario == null) {
			System.out.println("Registro não encontrado.");
		} else {
			System.out.println("Nome: " + usuario.getPessoa().getNome() + " - Ativo: " + usuario.getAtivo());
		}
	}

	@Test
	@Ignore
	public void excluir() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(2L);

		if (usuario == null) {
			System.out.println("Registro não encontrado.");
		} else {
			usuarioDAO.excluir(usuario);
			System.out.println("Registro excluído com sucesso.");
		}
	}

	@Test
	@Ignore
	public void editar() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(1L);

		if (usuario == null) {
			System.out.println("Registro não encontrado.");
		} else {
			usuario.setAtivo(false);
			usuarioDAO.editar(usuario);
			System.out.println("Registro alterado com sucesso.");
		}
	}
}
