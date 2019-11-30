<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="ui vertical center aligned segment">
	<div class="ui container">
		<div class="ui large secondary pointing menu">
			<a class="toc item">
				<i class="sidebar icon"></i>
			</a>
			<a class="header item" href="${pageContext.request.contextPath}">
				<img class="ui mini rounded logo image" src="${pageContext.request.contextPath}/img/brand.png">
				Mindbank
			</a>
			<c:choose>
				<c:when test="${sessionScope.user != null}">
					<div class="right menu">
						<div class="ui dropdown item">
							<i class="plus icon"></i>
							<i class="dropdown icon"></i>
							<div class="menu">
								<a class="item" href="${pageContext.request.contextPath}/problem/new">New problem</a>
								<a class="item" href="${pageContext.request.contextPath}/category/new">New category</a>
								<a class="item" href="${pageContext.request.contextPath}/tag/new">New tag</a>
							</div>
						</div>
						<div class="ui dropdown item">
							<img class="ui mini rounded image" src="https://fomantic-ui.com/images/avatar/small/matt.jpg">
							<i class="dropdown icon"></i>
							<div class="menu">
								<a class="ui item" href="${pageContext.request.contextPath}">Logged in as <strong>${sessionScope.user.userName}</strong></a>
								<div class="item">
									<button class="ui basic button">
										<i class="grin outline icon"></i>
										Set status
									</button>
								</div>
								<a class="item" href="${pageContext.request.contextPath}/${sessionScope.user.userName}">Your profile</a>
								<a class="item" href="${pageContext.request.contextPath}/${sessionScope.user.userName}/problems">Your problems</a>
								<div class="ui divider"></div>
								<a class="item" href="${pageContext.request.contextPath}/settings/profile">Settings</a>
								<a class="item" href="${pageContext.request.contextPath}/logout">Log out</a>
							</div>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="right item">
						<a class="ui button" href="${pageContext.request.contextPath}/login">Log in</a>
						<a class="ui button" href="${pageContext.request.contextPath}/register">Register</a>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</header>