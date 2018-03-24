package com.drogaria.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import com.drogaria.dao.UsuarioDAO;
import com.drogaria.domain.Pessoa;
import com.drogaria.domain.Usuario;

@ManagedBean
@SessionScoped
public class AutenticacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private UsuarioDAO usuarioDAO;
	private Usuario usuarioLogado;

	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
		usuario.setPessoa(new Pessoa());
	}

	public void autenticar() {
		try {
			usuarioDAO = new UsuarioDAO();
			usuarioLogado = usuarioDAO.autenticar(usuario.getPessoa().getCpf(), usuario.getSenha());

			if (usuarioLogado == null) {
				Messages.addGlobalError("CPF e ou Senha incorretos");
				iniciar();
				return;
			}
			Faces.redirect("./pages/home.xhtml");
		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
}
