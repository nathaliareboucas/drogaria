package com.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.drogaria.domain.Cidade;
import com.drogaria.domain.Estado;

public class CidadeDAOTest {

	@Test
	@Ignore
	public void salvar() {
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(3L);

		Cidade cidade = new Cidade();
		cidade.setNome("Quixadá");
		cidade.setEstado(estado);

		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.salvar(cidade);
		System.out.println("Registro salvo com sucesso.");

	}

	@Test
	@Ignore
	public void listar() {
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> resultado = cidadeDAO.listar();

		if (resultado.isEmpty()) {
			System.out.println("Nenhum registro encontrado.");
		} else {
			for (Cidade cidade : resultado) {
				System.out.println("Cidade: " + cidade.getNome() + " - Estado: " + cidade.getEstado().getNome());
			}
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 9L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);

		if (cidade == null) {
			System.out.println("Registro não encontrado");
		} else {
			System.out.println("Cidade: " + cidade.getNome() + " - Estado: " + cidade.getEstado().getSigla());
		}
	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 3L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);

		if (cidade == null) {
			System.out.println("Registro não encontrado");
		} else {
			cidadeDAO.excluir(cidade);
			System.out.println("Registro excluído com sucesso.");
		}
	}

	@Test
	@Ignore
	public void editar() {
		Long codigo = 2L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);

		if (cidade == null) {
			System.out.println("Registro não encontrado");
		} else {
			cidade.setNome("Campinas");

			EstadoDAO estadoDAO = new EstadoDAO();
			Estado estado = estadoDAO.buscar(1L);

			cidade.setEstado(estado);
			cidadeDAO.editar(cidade);
			System.out.println("Registro alterado com sucesso.");
		}
	}

}
