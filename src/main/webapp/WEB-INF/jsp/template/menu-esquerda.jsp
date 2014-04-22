<div class="sidebar">

	<!-- Logo Start -->
	<a href="${ctx}/resources/tema-admin/index.html">
		<div class="logo-container" id="step1">
			<h1>SIGE</h1>			
		</div>
	</a>
	<!-- Logo End -->	
	<div class="responsive-menu">
		<a href="#">
			<i class="fa fa-bars"></i>
		</a>
	</div>
	<!-- Sidebar User Profile End -->

	<!-- Menu Start -->
	<ul class="menu">
		<li class="lightblue active">
			<a href="<c:url value="/index"/>">
				<span class="menu-icon">
					<i class="fa fa-home"></i>
				</span>
				<span class="menu-text">Inicio</span>				
			</a>
		</li>
		<li class="lightblue parent">
			<a href="#">
				<span class="menu-icon">
					<i class="fa fa-user"></i>
				</span>
				<span class="menu-text">Clientes</span>
			</a>
			<ul class="child">
				<li>
					<a href="#">Cadastrar</a>
				</li>
				<li>
					<a href="#">Relacionar</a>
				</li>				
			</ul>
		</li>
		<li class="lightblue parent">
			<a href="#">
				<span class="menu-icon">
					<i class="fa fa-cogs"></i>
				</span>
				<span class="menu-text">Configura��es</span>
			</a>
			<ul class="child">
				<li>
					<a href="#">Usu�rios</a>
				</li>
				<li>
					<a href="#">Grupos de Usu�rio</a>
				</li>				
			</ul>
		</li>
	</ul>
	<!-- Menu End -->

</div>
<!-- Sidebar End -->