<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../partial/head.jsp">
			<jsp:param name="title" value="Login" />
		</jsp:include>
	</head>
<body>
	<div class="wrapper">
		<div class="main">
			<header>
				<%@ include file="../partial/header.jsp" %>
			</header>
			
			<div class="container">
				<form action="login" method="post">
					<input type="email" name="email" placeholder="Email">
					<input type="password" name="password" placeholder="Password">
					<button type="submit">Login</button>
				</form>
			</div>
		</div>
		
		<%@ include file="../partial/footer.jsp" %>
	</div>
	
	<jsp:include page="../partial/scripts.jsp" />
</body>
</html>