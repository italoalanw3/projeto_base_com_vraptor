package projeto.controller.admin;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import projeto.controller.interceptadores.RequisicaoInterceptadorAnnotation;
import projeto.controller.supercontroller.ControllerSuper;
import projeto.modelo.negocio.entidade.GrupoUsuario;
import projeto.modelo.negocio.entidade.Usuario;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;

@Controller
@RequisicaoInterceptadorAnnotation(GrupoController.class)
@Path("/admin/grupo")
public class GrupoController extends
		ControllerSuper<GrupoUsuario> {

	public GrupoController() {
	}

	@Inject
	public GrupoController(Result result, Validator validator,
			GrupoUsuario grupo) {
		this.result = result;
		this.validator = validator;
		this.entidade = grupo;
	}

	public void lista() {
		List<GrupoUsuario> grupos = this.entidade.listarTodos();
		if (grupos.isEmpty() == false) {
			result.include("grupos", grupos);
		}
	}

	@Post("/grupo")
	public void adiciona(@NotNull @Valid GrupoUsuario grupo) {
		if (this.entidade.salvar() == false) {
			validator.add(new SimpleMessage("Falha ao salvar",
					"Não foi possivel salvar novo grupo"));
		}
		result.redirectTo(this).lista();
	}

	@Path("/grupo/edita/{id}")
	public void edita(@NotNull @Valid long id) {
		this.entidade.setId(id);
		result.include(this.entidade.carrega());
	}

	@Put("/grupo")
	public void edita(@NotNull @Valid GrupoUsuario grupo) {
		if (this.entidade.alterar()) {
			result.include("mensagem", "Grupo: " + grupo.getNome()
					+ ", alterado com sucesso");
		} else {
			validator.add(new SimpleMessage("Falha ao editar",
					"Não foi possivel editar"));

		}
		validator.onErrorForwardTo(this).lista();
		result.redirectTo(this).lista();
	}

	public void remove(@NotNull @Valid long id) {
		GrupoUsuario grupo = this.entidade.carrega();
		if (grupo == null) {
			validator.add(new SimpleMessage("Falha ao remover",
					"Não foi possivel remover"));
		} else if (grupo.remover()) {
			result.include("mensagem", "Grupo removido com sucesso!");
		}

		validator.onErrorForwardTo(this).lista();

		result.redirectTo(this).lista();
	}

	@Get("/grupo/carrega/{id}.json")
	public void carrega(@NotNull @Valid long id) {
		this.entidade.setId(id);
		List<Usuario> usuarios = this.entidade.carrega().getUsuarios();
		result.use(Results.json()).withoutRoot().from(usuarios).serialize();
	}

	@Override
	protected Class<GrupoController> classType() {
		return GrupoController.class;
	}

}
