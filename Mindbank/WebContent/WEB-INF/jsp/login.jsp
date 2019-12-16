<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Login" scope="request" />

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="partial/head.jsp" %>
	</head>
	<body>
		<main>
			<div class="ui container" style="margin-top: 3vh; margin-bottom: 3vh;">
				<div class="ui center aligned middle grid" style="height: 100%;">
					<div class="column" style="max-width: 450px;">
						<a href="<c:url value='/' />">
							<h2 class="ui header">
								<img class="ui small rounded image" src="<c:url value='/img/brand.png' />">Mindbank
							</h2>
						</a>
						<h2>Log in to your account</h2>
						<form class="ui large form" action="login" method="post">
							<div class="ui segment">
								<c:if test="${not empty errors.credentials}">
									<div class="ui negative message">
										<i class="close icon"></i>
										<div class="header"><c:out value="${errors.credentials}" /></div>
									</div>
								</c:if>
								<div style="margin: 3vh 0;">
									<div class="ui required field ${not empty errors.userName ? 'error' : ''}">
										<div class="ui fluid left icon input">
											<input type="text" name="userName" placeholder="Username" value="<c:out value="${userName}" />">
											<i class="user icon"></i>
										</div>
										<c:if test="${not empty errors.userName}">
											<div class="ui pointing above red basic label"><c:out value="${errors.userName}" /></div>
										</c:if>
									</div>
								</div>
								<div style="margin: 3vh 0;">
									<div class="ui required field ${not empty errors.passWord ? 'error' : ''}">
										<div class="ui fluid left icon input">
											<input type="password" name="passWord" placeholder="Password" value="<c:out value="${passWord}" />">
											<i class="lock icon"></i>
										</div>
										<c:if test="${not empty errors.passWord}">
											<div class="ui pointing above red basic label"><c:out value="${errors.passWord}" /></div>
										</c:if>
									</div>
								</div>
								<div style="margin: 3vh 0;">
									<div class="inline field">
										<div class="ui checkbox">
											<input id="rememberMe" type="checkbox" name="rememberMe">
											<label for="rememberMe">Remember me</label>
										</div>
									</div>
								</div>
								<button class="ui large fluid button" type="submit">Log in</button>
							</div>
						</form>
						<div class="ui container" style="margin: 3vh 0;">
							<b>Not registered? <a href="register">Register</a></b>
						</div>
					</div>
				</div>
			</div>
		</main>
		
		<jsp:include page="partial/scripts.jsp" />
	</body>
</html>