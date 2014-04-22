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
 *         usu�rio para uma outra l�gica ou um JSP. Apesar de serem conceitos da
 *         API de Servlets, vale a pena relembrar a diferen�a: o
 *         redirecionamento acontece no lado do cliente, atrav�s de c�digos HTTP
 *         que far�o o browser acessar uma nova URL; j� o forward acontece no
 *         lado do servidor, totalmente transparente para o cliente/browser.
 * 
 *         Um bom exemplo de uso do redirect � no chamado 'redirect-after-post'.
 *         Por exemplo: quando voc� adiciona um cliente e que, ap�s o formul�rio
 *         submetido, o cliente seja retornado para a p�gina de listagem de
 *         clientes. Fazendo isso com redirect, impedimos que o usu�rio atualize
 *         a p�gina (F5) e reenvie toda a requisi��o, acarretando em dados
 *         duplicados.
 * 
 *         No caso do forward, um exemplo de uso � quando voc� possui uma
 *         valida��o e essa valida��o falhou, geralmente voc� quer que o usu�rio
 *         continue na mesma tela do formul�rio com os dados da requisi��o
 *         preenchidos, mas internamente voc� vai fazer o forward para outra
 *         l�gica de neg�cios (a que prepara os dados necess�rios para o
 *         formul�rio).
 */
public abstract class ControllerSuper<T> {

	protected Validator validator;
	protected Result result;
	protected T entidade;

	/** Construtor default */
	public ControllerSuper() {

	}

	/**
	 * L�gica para redirecionar para p�gina de listagem Ser� anotado com a
	 * seguinte anota��o: * Caso n�o seja preciso no m�todo listar utilizar algo
	 * a mais do que apenas retornar uma lista de objetos, ent�o, utilizar esse
	 * m�todo. Utilizar por exemplo: result.include("")
	 * 
	 * @Path("/entidade/lista")
	 * */
	public abstract void lista();

	/**
	 * L�gica para redirecionar para p�gina de novo; Toda entidade que chama
	 * essa l�gica deve possuir uma p�gin jsp, baseada na seguinte recomenda��o:
	 * /jsp/entidade/novo.jsp Sempre redireciona para Projeto/controller/novo
	 * */
	public void novo() {
	}
	
	/**
	 * L�gica para redirecionar para p�gina de edita em caso de erro de valida��o; 
	 * */
	public void edita() {
	}

	/**
	 * A��o de salvar novo, via p�gina de novo. Ser� anotado com a seguinte
	 * anota��o:
	 * 
	 * @Post("/entidade")
	 * */
	public abstract void adiciona(@NotNull @Valid T entidade);

	/**
	 * L�gica para carregar objeto. Ser� anotado com as seguintes anota��es:
	 * 
	 * Dever� ter um atributo no html, ex.: <button type="submit" name="_method"
	 * value="PUT">remover cliente 5</button> temos que usar o par�metro
	 * _method, uma vez que o browser ainda n�o suporta tais requisi��es:
	 * 
	 * @Path("/entidade/edita")
	 * */
	public abstract void edita(@NotNull @Valid long id);

	/**
	 * A��o para editar. Ser� anotado com a seguinte anota��o:
	 * 
	 * @Put("/entidade")
	 * 
	 * */
	public abstract void edita(@NotNull @Valid T entidade);

	/**
	 * A��o que remove Ser� anotado com as seguintes anota��es:
	 * 
	 * @Get("/entidade/remove")
	 * */
	public abstract void remove(@NotNull @Valid long id);

	/**
	 * Carrega objeto na p�gina, necessita do id; Apenas d� um include
	 * */
	public abstract void carrega(@NotNull @Valid long id);
	
	//------------------------------------------------------------------------------------------
	//Redirecionamento para casos de erros: Valida��o ou falha de cadastro/edi��o;
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
