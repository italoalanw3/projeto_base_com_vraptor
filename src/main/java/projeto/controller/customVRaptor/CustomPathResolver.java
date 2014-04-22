package projeto.controller.customVRaptor;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;

import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.http.FormatResolver;
import br.com.caelum.vraptor.view.DefaultPathResolver;

@Specializes
@ApplicationScoped
public class CustomPathResolver extends DefaultPathResolver {

	private FormatResolver resolver;

	@Inject
	public CustomPathResolver(FormatResolver resolver) {
		super(resolver);
		this.resolver = resolver;
	}

	@Override
	public String pathFor(ControllerMethod method) {
		String format = this.resolver.getAcceptFormat();

		String suffix = "";
		if (format != null && !format.equals("html")) {
			suffix = "." + format;
		}
		String name = method.getController().getType().getSimpleName();
		String folderName = extractControllerFromName(name);
		String pkg = method.getController().getPackageName();
		pkg = pkg.replace("projeto.controller", "");

		if (pkg.contains(".")) {
			pkg = pkg.replace(".", "");
		}
		String retorno = "";

		retorno = "/WEB-INF/jsp/" + pkg + "/" + folderName + "/"
				+ method.getMethod().getName() + suffix + "." + getExtension();

		return retorno;
	}
}
