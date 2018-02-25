package com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import com.drogaria.dao.ClienteDAO;
import com.drogaria.domain.Cliente;

@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cliente cliente;
	private ClienteDAO clienteDAO;
	private List<Cliente> clientes;

	@PostConstruct
	public void init() {
		clienteDAO = new ClienteDAO();
		listar();
	}

	public void listar() {
		try {
			clientes = clienteDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao listar os clientes.");
			erro.printStackTrace();
		}
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

}
