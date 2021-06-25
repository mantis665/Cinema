<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div class="container">
		<div class="registration-div">
			<h4 class="widget-title">${film.name}</h4>
			<form class="wpcf7" method="post" action="controller" id="login_form">
				<div class="form">
					<input type="hidden" name="command" value="session-booking" />
					<input type="hidden" name="sessionId" value="${session.id}" />
					<p>
					Session date:
					${session.date}
					</p>
					<p>
					Session time:
					${session.time}
					</p>
					<input type="submit" id="book" class="clearfix btn" value="To book">
				</div>
			</form>
		</div>
	</div>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>