package projeto.controller.admin;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Message;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import projeto.controller.interceptadores.RequisicaoInterceptadorAnnotation;
import projeto.controller.publico.HomeController;
import projeto.controller.publico.LoginController;
import projeto.controller.sessao.UserInfo;
import projeto.controller.supercontroller.ControllerSuper;
import projeto.modelo.negocio.entidade.Usuario;

@Controller
@RequisicaoInterceptadorAnnotation(UsuarioController.class)
@Path("/admin/usuario")
public class UsuarioController extends ControllerSuper<Usuario> {

	private UserInfo userInfo;

	public UsuarioController() {
	}

	@Inject
	public UsuarioController(Result result, Validator validator,
			Usuario usuario, UserInfo userInfo) {
		this.result = result;
		this.validator = validator;
		this.entidade = usuario;
		this.userInfo = userInfo;
	}

	public void lista() {
		try {
			List<Usuario> lista = entidade.listarTodos();
			if (lista.isEmpty() == false) {
				result.include("usuarios", lista);
			}
		} catch (Exception e) {
			Message msg = new SimpleMessage(
					"Erro no servidor",
					"A mensagem a seguir com dados do erro foi enviado ao administrador do sistema: "
							+ e.getMessage()
							+ " | Causa: "
							+ e.getCause()
							+ " Desculpe pelo transtorno.");
			validator.add(msg);
			validator.onErrorForwardTo(HomeController.class).index();
		}
	}

	public void preencherValoresCadastro() {

	}

	@Post("/novo")
	public void adiciona(@NotNull @Valid Usuario usuario) {
		validator.onErrorForwardTo(this).formulario(TIPO_FORMULARIO.NOVO);		
		if (this.entidade.salvar() == false) {
			Message msg = new SimpleMessage("Não foi possivel adicionar",
					"Falha ao salvar novo");
			validator.add(msg);
		}else{
			result.include("mensagemAdicionar", "mensagem.adicionado");
		}
		
		
		validator.onErrorForwardTo(this).formulario(TIPO_FORMULARIO.NOVO);

		result.redirectTo(this).lista();
	}

	@Get("edita/{id}")
	public void edita(@NotNull @Valid long id) {
		this.entidade.setId(id);
		result.include(this.entidade.carrega());
	}

	@Put("/edita")
	public void edita(@NotNull @Valid Usuario usuario) {
		validator.onErrorForwardTo(this).formulario(TIPO_FORMULARIO.EDITA);
		if (this.entidade.alterar()) {
			result.include("mensagemAlterar", "mensagem.alterado");
			usuario.setAutenticado(true);
			userInfo.setUsuarioLogado(usuario);

		} else {
			Message msg = new SimpleMessage("Erro!", "Não foi possivel editar.");
			validator.add(msg);
		}

		validator.onErrorForwardTo(this).formulario(TIPO_FORMULARIO.EDITA);

		result.redirectTo(this).lista();
	}

	@Get("/remove/{id}")
	public void remove(@NotNull @Valid long id) {
		validator.onErrorForwardTo(this).lista();
		//Usuario usuario = new Usuario(id);
		Usuario usuario = new Usuario();
		usuario = this.entidade.carrega();
		if (usuario == null) {
			Message msg = new SimpleMessage("Não foi possivel remover",
					"Usuário não encontrado");
			validator.add(msg);
		} else if (usuario.remover()) {
			result.include("mensagemRemover", "mensagem.removido");
		}

		validator.onErrorForwardTo(this).lista();

		verificaSeUsuarioSessaoRemovido(usuario);

		result.redirectTo(this).lista();
	}

	// Método conclúido
	private void verificaSeUsuarioSessaoRemovido(Usuario usuario) {
		if (usuario.getEmail().equals(userInfo.getUsuarioLogado().getEmail())
				&& this.entidade.listarTodos().size() == 0) {
			Message msg = new SimpleMessage(
					"categoria.todos.usuarios.removidos",
					"mensagem.todos.usuarios.removidos");
			validator.add(msg);
		} else if (usuario.getEmail().equals(
				userInfo.getUsuarioLogado().getEmail())) {
			Message msg = new SimpleMessage(
					"usuario.categoria.removido.sessao",
					"usuario.mensagem.removido.sessao");
			validator.add(msg);
		}
		validator.onErrorRedirectTo(LoginController.class).logout();
	}

	@Override
	public void carrega(@NotNull @Valid long id) {
		this.entidade.setId(id);
		result.include(this.entidade.carrega());
	}

	@Override
	protected Class<UsuarioController> classType() {
		return UsuarioController.class;
	}
}
