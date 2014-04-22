package projeto.controller.sessao;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import projeto.modelo.negocio.entidade.Usuario;

@SessionScoped
@Named
public class UserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuarioLogado;

	public UserInfo() {
		usuarioLogado = new Usuario();
	}

	public Usuario getUsuarioLogado() {
		if (usuarioLogado == null)
			usuarioLogado = new Usuario();
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public boolean getLogado() {
		return getUsuarioLogado() != null;
	}

	public void logout() {
		if (usuarioLogado != null)
			usuarioLogado = null;
	}

}
