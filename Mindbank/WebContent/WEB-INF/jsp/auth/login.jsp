<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Login" scope="request" />

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="../partial/head.jsp" %>
	</head>
	<body>
		<main class="d-flex justify-content-center align-items-center h-100">
			<div class="ui vertical basic segment">
				<div class="ui container">
					<div class="ui centered grid">
						<div class="row">
							<div class="wide column">
								<a href="${pageContext.request.contextPath}">
									<img class="ui small rounded image m-auto" src="${pageContext.request.contextPath}/img/brand.png">
								</a>
								<h1 class="ui header">Log in</h1>
								<form class="ui form" action="${pageContext.request.contextPath}/login" method="post">
									<c:if test="${errors.error != null}">
										<div class="ui negative message">
											<i class="close icon"></i>
											<div class="header">${errors.error}</div>
										</div>
									</c:if>
									<div class="field">
										<label>Username</label>
										<input type="text" name="username" value="${username}">
									</div>
									<div class="field">
										<label>Password</label>
										<input type="password" name="password" value="${password}">
									</div>
									<div class="field">
										<div class="ui checkbox">
											<input id="showPassword" type="checkbox">
											<label for="showPassword">Show password</label>
										</div>
									</div>
									<div class="field">
										<p class="ui">
											<a href="${pageContext.request.contextPath}/forgotPassword">Forgot Password?</a>
										</p>
									</div>
									<div class="inline field">
										<div class="ui checkbox">
											<input id="rememberMe" type="checkbox" name="remember" ${(remember eq 'on') ? 'checked' : ''}>
											<label for="rememberMe">Remember me</label>
										</div>
									</div>
									<div class="ui center aligned basic segment">
										<button class="ui primary button" type="submit">Log in</button>
									</div>
									<div class="field d-flex justify-content-center">
										<p>Not a member? <a href="${pageContext.request.contextPath}/register">Register</a></p>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<%@ include file="../partial/footer.jsp" %>
		</main>
		
		<jsp:include page="../partial/scripts.jsp" />
	</body>
</html>