<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Register" scope="request" />

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
									<img class="ui small rounded image m-auto" src="img/brand.png">
								</a>
								<h1 class="ui header">Register</h1>
								<form class="ui form" action="register" method="post">
									<div class="ui required field ${(errors.email != null) ? 'error' : ''}">
										<label>Email</label>
										<input type="email" name="email" value="${email}">
										<c:if test="${errors.email != null}">
											<div class="ui pointing above red basic label"><c:out value="${errors.email}" /></div>
										</c:if>
									</div>
									<div class="ui required field ${(errors.userName != null) ? 'error' : ''}">
										<label>Username</label>
										<input type="text" name="userName" value="${userName}">
										<c:if test="${errors.userName != null}">
											<div class="ui pointing above red basic label"><c:out value="${errors.userName}" /></div>
										</c:if>
									</div>
									<div class="ui required field ${(errors.password != null) ? 'error' : ''}">
										<label>Password</label>
										<input type="password" name="password" value="${password}">
										<c:if test="${errors.password != null}">
											<div class="ui pointing above red basic label"><c:out value="${errors.password}" /></div>
										</c:if>
									</div>
									<div class="ui required field ${(errors.passwordConfirm != null) ? 'error' : ''}">
										<label>Confirm password</label>
										<input type="password" name="passwordConfirm" value="${passwordConfirm}">
										<c:if test="${errors.passwordConfirm != null}">
											<div class="ui pointing above red basic label"><c:out value="${errors.passwordConfirm}" /></div>
										</c:if>
									</div>
									<div class="ui center aligned basic segment">
										<button class="ui primary button" type="submit">Register</button>
									</div>
									<div class="field d-flex justify-content-center">
										<p>Already a member? <a href="login">Login</a></p>
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