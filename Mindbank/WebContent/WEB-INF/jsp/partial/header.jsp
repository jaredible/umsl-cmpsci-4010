<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="ui vertical center aligned segment">
	<div class="ui container">
		<div class="ui large secondary pointing menu">
			<a class="toc item">
				<i class="sidebar icon"></i>
			</a>
			<a class="header item" href="${pageContext.request.contextPath}">
				<img class="ui mini rounded logo image" src="img/brand.png">
				Mindbank
			</a>
			<c:choose>
				<c:when test="${sessionScope.user != null}">
					<div class="right menu">
						<div class="ui dropdown item">
							<i class="plus icon"></i>
							<i class="dropdown icon"></i>
							<div class="menu">
								<a class="item" href="newProblem">New problem</a>
								<a class="item" href="newCategory">New category</a>
								<a class="item" href="newTag">New tag</a>
							</div>
						</div>
						<div class="ui dropdown item">
							<img class="ui mini rounded image" src="https://fomantic-ui.com/images/avatar/small/matt.jpg">
							<i class="dropdown icon"></i>
							<div class="menu">
								<a class="item" href="${pageContext.request.contextPath}">Home</a>
								<a class="item" href="profile">Profile</a>
								<a class="item" href="account">Account</a>
								<a class="item" href="security">Security</a>
								<a class="item" href="logout">Log out</a>
							</div>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="right item">
						<a class="ui button" href="login">Log in</a>
						<a class="ui button" href="register">Register</a>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</header>