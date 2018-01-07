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
		fabricante.setDescricao("Nívea");
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.salvar(fabricante);
	}
	
	@Test
	public void listar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		List<Fabricante> resultado = fabricanteDAO.listar();
		
		for (Fabricante fabricante:resultado) {
			System.out.println(fabricante.getDescricao());
		}
	}

}
