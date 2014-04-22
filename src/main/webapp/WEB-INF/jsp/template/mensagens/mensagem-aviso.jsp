<c:forEach var="error" items="${errors}">
	<div class="alert alert-warning alert-dismissable">
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true">&times;</button>
		<span tabindex="button" class="close" data-dismiss="alert"
			aria-hidden="true"></span> 
			<strong>
			<c:if test="${fn:contains(error.category, '.')}"> <!-- Se contém ponto, significa que a mensagem vem do i38r -->
				<fmt:message key="${error.category}" />
			</c:if>
			<c:if test="${not fn:contains(error.category, '.')}">
				${error.category}
			</c:if>
			</strong>
			<c:if test="${fn:contains(error.message, '.')}"> <!-- Se contém ponto, significa que a mensagem vem do i38r -->
				<fmt:message key="${error.message}" />
			</c:if>
			<c:if test="${not fn:contains(error.message, '.')}">
				${error.message}
			</c:if>
		<br />
	</div>
</c:forEach>