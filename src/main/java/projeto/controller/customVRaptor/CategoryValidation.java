package projeto.controller.customVRaptor;


import java.util.Iterator;
import java.util.Locale;
 
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.MessageInterpolator;
import javax.validation.Path.Node;
import javax.validation.Path.ParameterNode;
import javax.validation.Validator;

import br.com.caelum.vraptor.http.ValuedParameter;
import br.com.caelum.vraptor.validator.beanvalidation.MethodValidator;

@Specializes
@ApplicationScoped
public class CategoryValidation extends MethodValidator {

	public CategoryValidation() {
		this(null, null, null);
	}

	@Inject
	public CategoryValidation(Locale locale, MessageInterpolator interpolator,
			javax.validation.Validator bvalidator) {
		super(locale, interpolator, bvalidator);
	}

	/** Método para padronizar no nome da categoria para exibir na página HTML.
	 *  Segue o padrão: objeto.atributo
	 *  */
	@Override
	protected String extractCategory(ValuedParameter[] params, ConstraintViolation<Object> violation) {
		Iterator<Node> path = violation.getPropertyPath().iterator();
		
		path.next(); // Essa chamada, faz com que o nome do método não seja incluido no nome.
 
		StringBuilder category = new StringBuilder();
		category.append(params[path.next().as(ParameterNode.class).getParameterIndex()].getName());
 
		while (path.hasNext()) {
			category.append(".").append(path.next());
		}
 
		return category.toString();
	}
}
