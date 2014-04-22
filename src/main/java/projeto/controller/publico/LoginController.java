package projeto.controller.publico;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import projeto.controller.admin.UsuarioController;
import projeto.controller.sessao.UserInfo;
import projeto.modelo.negocio.entidade.Usuario;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Message;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;

@Controller
public class LoginController {

	private Validator validator;
	private Result result;
	private UserInfo userInfo;

	public LoginController() {
	}

	@Inject
	public LoginController(Validator validator, Result result, UserInfo userInfo) {
		this.validator = validator;
		this.result = result;
		this.userInfo = userInfo;
	}

	@Get("/login")
	public void login() {
	}
	
	public void erros(){
		Message msg1 = new SimpleMessage("login.erro", "?");
		Message msg2 = new SimpleMessage("login.email.senha.invalido",
				"login.erro.email.senha.invalido");
		List<Message> msgs = new ArrayList<>();
		msgs.add(msg1);
		msgs.add(msg2);
		validator.addAll(msgs);
		validator.onErrorForwardTo(this).login();
	}

	@Post("/login")
	public void login(Usuario usuario) {
		Usuario carregado = usuario.validarUsuario(usuario);
		if (carregado == null) {
			erros();	
		}		

		carregado.setAutenticado(true);
		this.userInfo.setUsuarioLogado(carregado);

		result.redirectTo(UsuarioController.class).lista();

	}

	@Get("/logout")
	public void logout() {
		userInfo.logout();

		result.redirectTo(this).login();
	}
}
