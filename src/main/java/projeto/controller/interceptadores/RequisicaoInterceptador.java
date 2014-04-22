package projeto.controller.interceptadores;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import projeto.controller.publico.LoginController;
import projeto.controller.sessao.UserInfo;
import br.com.caelum.vraptor.BeforeCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.AcceptsWithAnnotations;

@Intercepts
@AcceptsWithAnnotations(RequisicaoInterceptadorAnnotation.class)
public class RequisicaoInterceptador {

	@Inject
	private HttpServletResponse response;
	@Inject
	private UserInfo usuarioSessao;
	@Inject
	private Result result;

	public RequisicaoInterceptador() {
	}

	public RequisicaoInterceptador(HttpServletResponse response,
			UserInfo usuarioSessao, Result result) {
		this.response = response;
		this.usuarioSessao = usuarioSessao;
		this.result = result;
	}

	@BeforeCall
	public void before() {
		if (usuarioSessao.getUsuarioLogado().getAutenticado()) {
			System.err.println("Usuário da sessão: "
					+ usuarioSessao.getUsuarioLogado().getNome());
			response.setHeader("Expires", "Wed, 31 Dec 1969 21:00:00 GMT");

			// no-cache headers for HTTP/1.1
			response.setHeader("Cache-Control",
					"no-store, no-cache, must-revalidate");

			// no-cache headers for HTTP/1.1 (IE)
			response.addHeader("Cache-Control", "post-check=0, pre-check=0");

			// no-cache headers for HTTP/1.0
			response.setHeader("Pragma", "no-cache");
		} else {
			System.err.println("Nenhum usuário na sessão.");
			result.redirectTo(LoginController.class).logout();
		}
	}

	public void after() {

	}

}
