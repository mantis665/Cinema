<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div class="container">
		<div class="registration-div">
			<h4 class="widget-title">Registration</h4>
			<form class="wpcf7" method="post" action="controller" id="login_form">
				<div class="form">
					<input type="hidden" name="command" value="registration" />
					<p>
						<input type="text" name="login" placeholder="Login *">
					</p>
					<p>
						<input type="password" name="password" placeholder="Password *">
					</p>
					<p>
						<input type="text" name="first_name" placeholder="Name *">
					</p>
					<p>
						<input type="text" name="last_name" placeholder="Surename *">
					</p>
					<input type="submit" id="submit" class="clearfix btn" value="Send">
				</div>
			</form>
		</div>
	</div>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>