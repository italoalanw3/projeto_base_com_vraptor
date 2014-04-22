package projeto.controller.supercontroller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import projeto.modelo.negocio.PersistenciaBD;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;

/**
 * 
 * @author Italo Alan Computer
 * 
 * 
 *         Redirecionamento e forward
 * 
 *         No VRaptor, podemos tanto realizar um redirect ou um forward do
 *         usuário para uma outra lógica ou um JSP. Apesar de serem conceitos da
 *         API de Servlets, vale a pena relembrar a diferença: o
 *         redirecionamento acontece no lado do cliente, através de códigos HTTP
 *         que farão o browser acessar uma nova URL; já o forward acontece no
 *         lado do servidor, totalmente transparente para o cliente/browser.
 * 
 *         Um bom exemplo de uso do redirect é no chamado 'redirect-after-post'.
 *         Por exemplo: quando você adiciona um cliente e que, após o formulário
 *         submetido, o cliente seja retornado para a página de listagem de
 *         clientes. Fazendo isso com redirect, impedimos que o usuário atualize
 *         a página (F5) e reenvie toda a requisição, acarretando em dados
 *         duplicados.
 * 
 *         No caso do forward, um exemplo de uso é quando você possui uma
 *         validação e essa validação falhou, geralmente você quer que o usuário
 *         continue na mesma tela do formulário com os dados da requisição
 *         preenchidos, mas internamente você vai fazer o forward para outra
 *         lógica de negócios (a que prepara os dados necessários para o
 *         formulário).
 */
public abstract class ControllerSuper<T> {

	protected Validator validator;
	protected Result result;
	protected T entidade;

	/** Construtor default */
	public ControllerSuper() {

	}

	/**
	 * Lógica para redirecionar para página de listagem Será anotado com a
	 * seguinte anotação: * Caso não seja preciso no método listar utilizar algo
	 * a mais do que apenas retornar uma lista de objetos, então, utilizar esse
	 * método. Utilizar por exemplo: result.include("")
	 * 
	 * @Path("/entidade/lista")
	 * */
	public abstract void lista();

	/**
	 * Lógica para redirecionar para página de novo; Toda entidade que chama
	 * essa lógica deve possuir uma págin jsp, baseada na seguinte recomendação:
	 * /jsp/entidade/novo.jsp Sempre redireciona para Projeto/controller/novo
	 * */
	public void novo() {
	}
	
	/**
	 * Lógica para redirecionar para página de edita em caso de erro de validação; 
	 * */
	public void edita() {
	}

	/**
	 * Ação de salvar novo, via página de novo. Será anotado com a seguinte
	 * anotação:
	 * 
	 * @Post("/entidade")
	 * */
	public abstract void adiciona(@NotNull @Valid T entidade);

	/**
	 * Lógica para carregar objeto. Será anotado com as seguintes anotações:
	 * 
	 * Deverá ter um atributo no html, ex.: <button type="submit" name="_method"
	 * value="PUT">remover cliente 5</button> temos que usar o parâmetro
	 * _method, uma vez que o browser ainda não suporta tais requisições:
	 * 
	 * @Path("/entidade/edita")
	 * */
	public abstract void edita(@NotNull @Valid long id);

	/**
	 * Ação para editar. Será anotado com a seguinte anotação:
	 * 
	 * @Put("/entidade")
	 * 
	 * */
	public abstract void edita(@NotNull @Valid T entidade);

	/**
	 * Ação que remove Será anotado com as seguintes anotações:
	 * 
	 * @Get("/entidade/remove")
	 * */
	public abstract void remove(@NotNull @Valid long id);

	/**
	 * Carrega objeto na página, necessita do id; Apenas dá um include
	 * */
	public abstract void carrega(@NotNull @Valid long id);
	
	//------------------------------------------------------------------------------------------
	//Redirecionamento para casos de erros: Validação ou falha de cadastro/edição;
	public void formulario(TIPO_FORMULARIO tipo) {
		switch (tipo) {
		case NOVO:
			validator.onErrorForwardTo(classType()).novo();
			break;
		case EDITA:
			validator.onErrorForwardTo(classType()).edita();
			break;
		}
	}

	public enum TIPO_FORMULARIO {
		NOVO, EDITA;
	}

	protected abstract Class<? extends ControllerSuper> classType();
}
