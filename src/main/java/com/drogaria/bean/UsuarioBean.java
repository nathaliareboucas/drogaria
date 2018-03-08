package com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import com.drogaria.dao.PessoaDAO;
import com.drogaria.dao.UsuarioDAO;
import com.drogaria.domain.Pessoa;
import com.drogaria.domain.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private UsuarioDAO usuarioDAO;
	private List<Usuario> usuarios;
	private List<Pessoa> pessoas;
	private PessoaDAO pessoaDAO;

	@PostConstruct
	public void init() {
		usuarioDAO = new UsuarioDAO();
		listar();
	}
	
	public void salvar() {
		try {
			usuarioDAO.merge(usuario);
			novo();
			listar();
			Messages.addGlobalInfo("Usuário salvom com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao salvar o usuário");
			erro.printStackTrace();
		}
	}

	public void listar() {
		try {
			usuarios = usuarioDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao listar os usuários");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
		listarPessoas();
	}

	public void novo() {
		usuario = new Usuario();
		listarPessoas();
	}

	public void listarPessoas() {
		try {
			pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao listar as pessoas");
			erro.printStackTrace();
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

}
