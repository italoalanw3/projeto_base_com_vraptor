<%@include file="/WEB-INF/jsp/template/cabecalho.jsp"%>
<%@include file="/WEB-INF/jsp/template/menu-superior.jsp"%>
<%@include file="/WEB-INF/jsp/template/menu-esquerda.jsp"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				<i class="glyphicon glyphicon-edit"></i> adicionar novo grupo
			</h1>
		</div>
	</div>

	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Cadastrar novo usuário</div>
			</div>
		</div>
		<div class="panel-body">
			<div class="row">
				<div class="col-lg-6">
					<form role="form" action="<c:url value="/usuario"/>" method="post">
						<div class="form-group">
							<label for="nome">Nome: </label> <input class="form-control" type="text" id="nome" name="usuario.nome" value="${usuario.nome}" placeholder="Informe o nome do usuário" />
						</div>
						<div class="form-group">
							<label for="email">E-mail:</label> <input class="form-control" type="text" id="email" name="usuario.email" value="${usuario.email}" placeholder="Informe o e-mail do usuário" />
						</div>
						<div class="form-group">
							<label for="senha">Senha: </label> <input class="form-control" type="password" id="senha" name="usuario.senha" value="${usuario.senha}" placeholder="Informe a senha do usuário" />
						</div>

						<button class="btn btn-primary" type="submit">
							<i class="glyphicon glyphicon-ok"> Enviar</i>
						</button>
						<a href="<c:url value="/usuario/lista"/>" class="btn btn-primary" role="button"> <i class="glyphicon glyphicon-share-alt"></i> Voltar
						</a>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- page-wrapper -->
<%@include file="/WEB-INF/jsp/template/rodape.jsp"%>