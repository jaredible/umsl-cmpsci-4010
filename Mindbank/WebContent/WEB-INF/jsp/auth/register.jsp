<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../partial/head.jsp">
			<jsp:param name="title" value="Register" />
		</jsp:include>
	</head>
<body>
	<div class="wrapper">
		<div class="main">
			<header>
				<%@ include file="../partial/header.jsp" %>
			</header>
			
			<div class="ui container">
				<form class="ui form" action="register" method="post">
					<div class="two fields">
						<div class="ui required field ${(errors.email != null) ? 'error' : ''}">
							<label>Email</label>
							<input type="email" name="email" placeholder="Email" value="${email}">
							<c:if test="${errors.email != null}">
								<div class="ui pointing above red basic label"><c:out value="${errors.email}" /></div>
							</c:if>
						</div>
						<div class="ui required field ${(errors.userName != null) ? 'error' : ''}">
							<label>Username</label>
							<input type="text" name="userName" placeholder="Username" value="${userName}">
							<c:if test="${errors.userName != null}">
								<div class="ui pointing above red basic label"><c:out value="${errors.userName}" /></div>
							</c:if>
						</div>
					</div>
					<div class="two fields">
						<div class="ui required field ${(errors.password != null) ? 'error' : ''}">
							<label>Password</label>
							<input type="password" name="password" placeholder="Password" value="${password}">
							<c:if test="${errors.password != null}">
								<div class="ui pointing above red basic label"><c:out value="${errors.password}" /></div>
							</c:if>
						</div>
						<div class="ui required field ${(errors.passwordConfirm != null) ? 'error' : ''}">
							<label>Confirm password</label>
							<input type="password" name="passwordConfirm" placeholder="Confirm password" value="${passwordConfirm}">
							<c:if test="${errors.passwordConfirm != null}">
								<div class="ui pointing above red basic label"><c:out value="${errors.passwordConfirm}" /></div>
							</c:if>
						</div>
					</div>
					<button class="ui button" type="submit">Register</button>
				</form>
			</div>
		</div>
		
		<%@ include file="../partial/footer.jsp" %>
	</div>
	
	<jsp:include page="../partial/scripts.jsp" />
</body>
</html>