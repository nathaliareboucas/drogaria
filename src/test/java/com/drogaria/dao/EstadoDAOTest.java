package com.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.drogaria.domain.Estado;

public class EstadoDAOTest {

	@Test
	@Ignore
	public void salvar() {
		Estado estado = new Estado();
		estado.setNome("Rio de Janeiro");
		estado.setSigla("RJ");

		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);
		System.out.println("Registro salvo com sucesso.");
	}

	@Test
	@Ignore
	public void listar() {
		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> resultado = estadoDAO.listar();

		if (resultado.isEmpty()) {
			System.out.println("Nenhum registro encontrado");
		} else {
			for (Estado estado : resultado) {
				System.out.println(estado.getSigla() + " - " + estado.getNome());
			}
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 3L;

		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);

		if (estado == null) {
			System.out.println("Registro não encontrado.");
		} else {
			System.out.println(estado.getSigla() + " - " + estado.getNome());
		}

	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 4L;

		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);

		if (estado == null) {
			System.out.println("Registro não encontrado.");
		} else {
			estadoDAO.excluir(estado);
			System.out.println("Registro removido com sucesso.");
		}
	}

	@Test
	@Ignore
	public void editar() {
		Long codigo = 3L;

		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);

		if (estado == null) {
			System.out.println("Registro não encontrado.");
		} else {
			estado.setSigla("CE");
			estadoDAO.editar(estado);
			System.out.println("Registro alterado com sucesso.");
		}
	}

}
