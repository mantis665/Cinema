<%@ include file="/WEB-INF/jspf/header.jspf"%>
<body>
	<div class="container">
		<div id="content" class="site-content">
			<main id="main" class="site-main">
				<div class="grid portfoliogrid">
					<c:forEach items="${sessions}" var="session">
						<article class="hentry">
							<header class="entry-header">
								<h2 class="entry-title">${session.key}</h2>
								<c:forEach items="${session.value}" var="value">
									<a href="?command=session-page&sessionId=${value.id}"
										class='portfoliotype'>${value.time} / ${value.film} / 4</a>
									<br>
								</c:forEach>
							</header>
						</article>
					</c:forEach>
				</div>
			</main>
		</div>
	</div>
</body>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
<%@ include file="/WEB-INF/jspf/script-include.jspf"%>
</html>