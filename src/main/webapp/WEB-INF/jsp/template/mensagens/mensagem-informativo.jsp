<c:forEach var="error" items="${errors}">
	<div class="alert alert-info alert-dismissable">
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true">&times;</button>
		<strong> <c:if test="${fn:contains(error.category, '.')}">
				<!-- Se cont�m ponto, significa que a mensagem vem do i38r -->
				<fmt:message key="${error.category}" />
			</c:if> <c:if test="${not fn:contains(error.category, '.')}">
				${error.category}
			</c:if>
		</strong>
		<c:if test="${fn:contains(error.message, '.')}">
			<!-- Se cont�m ponto, significa que a mensagem vem do i38r -->
			<fmt:message key="${error.message}" />
		</c:if>
		<c:if test="${not fn:contains(error.message, '.')}">
				${error.message}
			</c:if>
	</div>
</c:forEach>