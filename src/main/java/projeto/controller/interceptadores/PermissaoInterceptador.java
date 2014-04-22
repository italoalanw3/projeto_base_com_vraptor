package projeto.controller.interceptadores;

import projeto.modelo.negocio.entidade.GRUPO_USUARIO;
import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.BeforeCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.AcceptsWithAnnotations;

@AcceptsWithAnnotations(PermissaoInterceptadorAnnotation.class)
public class PermissaoInterceptador {
	
	@BeforeCall
	public void verificaPermissao(){
		
	}
	
	/** Intercepta apenas os métodos que possuem a anotação */
	@AroundCall
    public boolean around(ControllerMethod method) {
		return true;//return method.containsAnnotation(GRUPO_USUARIO.ADMINISTRADOR);
    }

}