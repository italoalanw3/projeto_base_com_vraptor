<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<c:if test="${not empty param.language}">
	<fmt:setLocale value="${param.language}" scope="session" />
</c:if>
<html lang="pt">

<!-- Mirrored from 198.74.61.72/themes/preview/ace/login.html by HTTrack Website Copier/3.x [XR&CO'2010], Tue, 25 Mar 2014 22:45:31 GMT -->
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title><fmt:message key="login.titulo" /></title>


<link rel="stylesheet" type="text/css" href="${ctx}/resources/tema-admin/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/tema-admin/assets/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/tema-admin/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/tema-admin/css/responsive.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/tema-admin/css/animate.css">

<!-- Google Fonts -->
<link href='../../../fonts.googleapis.com/css5805.css?family=Lobster' rel='stylesheet' type='text/css'>
<link href='../../../fonts.googleapis.com/css810b.css?family=Lato:400,300,700,700italic,900,100' rel='stylesheet' type='text/css'>

<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
  <![endif]-->


<link rel="stylesheet" href="${ctx}/resources/tema-admin/assets/css/ace.min.css" />

</head>

<body class="login-page">
	<section class="content login-page">

		<div class="content-liquid">
			<div class="container">
				<div class="row">
					<div class="login-page-container">

						<div class="boxed animated flipIny">
							<div class="inner">							

								<div class="login-title text-center">
									<h4>Login to your account</h4>
									<h5>We're happy to see you return</h5>
								</div>


								<form role="form" action="<c:url value="/login"/>" method="POST">
									<%@ include file="/WEB-INF/jsp/template/mensagens/mensagem-erro-br.jsp"%>
									<div class="input-group">
										<span class="input-group-addon">
											<i class="fa fa-user"></i>
										</span>
										<input type="text" class="form-control" placeholder="<fmt:message key="login.usuario.email.placeholder" />" required autofocus tabindex="1" value="${usuario.email}" name="usuario.email" />
									</div>
									<div class="input-group">
										<span class="input-group-addon">
											<i class="fa fa-lock"></i>
										</span>
										<input type="password" class="form-control" placeholder="<fmt:message key="login.usuario.senha.placeholder" />" required tabindex="2" value="${usuario.senha}" name="usuario.senha" />
									</div>


									<input type="submit" class="btn btn-lg btn-success" value="<fmt:message key="login.botao.entrar" />">
										
									</input>
									<p class="footer">
										We respect your privacy.
										<br />
										We hate spam as much as you do.
									</p>

								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- Javascript -->
	<script src="${ctx}/resources/tema-admin/assets/jquery/jquery.min.js"></script>
	<script src="${ctx}/resources/tema-admin/assets/bootstrap/js/bootstrap.min.js"></script>
	<script src="${ctx}/resources/tema-admin/assets/flippy/jquery.flippy.min.html"></script>


	<script type="text/javascript">
		jQuery(document).ready(
				function($) {

					var min_height = jQuery(window).height();
					jQuery('div.login-page-container').css('min-height',
							min_height);
					jQuery('div.login-page-container').css('line-height',
							min_height + 'px');

					//$(".inner", ".boxed").fadeIn(500);
				});
	</script>
</body>

<!-- Mirrored from 198.74.61.72/themes/preview/ace/login.html by HTTrack Website Copier/3.x [XR&CO'2010], Tue, 25 Mar 2014 22:45:31 GMT -->
</html>
