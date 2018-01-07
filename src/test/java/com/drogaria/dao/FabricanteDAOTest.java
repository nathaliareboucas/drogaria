package com.drogaria.dao;

import org.junit.Test;

import com.drogaria.domain.Fabricante;

public class FabricanteDAOTest {
	
	@Test
	public void salvar() {
		Fabricante fabricante = new Fabricante();
		fabricante.setDescricao("Nívea");
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.salvar(fabricante);
	}

}
