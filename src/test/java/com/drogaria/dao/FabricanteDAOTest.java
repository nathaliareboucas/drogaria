package com.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.drogaria.domain.Fabricante;

public class FabricanteDAOTest {

	@Test
	@Ignore
	public void salvar() {
		Fabricante fabricante = new Fabricante();
		fabricante.setDescricao("Novalgina");

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.salvar(fabricante);
		System.out.println("Registro salvo com sucesso.");
	}

	@Test
	@Ignore
	public void listar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		List<Fabricante> resultado = fabricanteDAO.listar();

		if (resultado.isEmpty()) {
			System.out.println("Nenhum registro encontrado");
		} else {
			for (Fabricante fabricante : resultado) {
				System.out.println(fabricante.getDescricao());
			}
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 3L;
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigo);

		if (fabricante == null) {
			System.out.println("Registro não encontrado.");
		} else {
			System.out.println("Fabricante: " + fabricante.getDescricao());
		}
	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 3L;
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigo);

		if (fabricante == null) {
			System.out.println("Registro não encontrado.");
		} else {
			fabricanteDAO.excluir(fabricante);
			System.out.println("Registro excluído com sucesso.");
		}
	}

	@Test
	@Ignore
	public void editar() {
		Long codigo = 2L;
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigo);

		if (fabricante == null) {
			System.out.println("Registro não encontrado.");
		} else {
			fabricante.setDescricao("Doril");
			fabricanteDAO.editar(fabricante);
			System.out.println("Registro alterado com sucesso.");
		}
	}

}
