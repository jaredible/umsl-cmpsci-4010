<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="main.java.mindbank.util.StringMap" %>
<%
Map<String, String> errors = (StringMap) request.getAttribute("errors");
String emailError = null;
String passwordError = null;
String confirmError = null;

if (errors != null) {
	emailError = errors.get("email");
	passwordError = errors.get("password");
	confirmError = errors.get("confirm");
}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<title>Register | Mindbank</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.7.6/css/mdb.min.css">
		<link rel="stylesheet" href="css/styles.css">
	</head>
	<body>
		<div class="wrapper">
			<div class="main">
				<div class="container test6">
					<form class="text-center" action="register" method="post" novalidate>
						<p class="h4 mb-3">Register</p>
						
						<div class="form-row">
							<div class="col-12 mb-3">
								<input class="form-control <% if (emailError != null) { %>is-invalid<% } %>" type="email" name="email" placeholder="E-mail" value="${email}">
								<% if (emailError != null) { %><div class="invalid-feedback"><%= emailError %></div><% } %>
							</div>
							<div class="col-12 mb-3">
								<input class="form-control <% if (passwordError != null) { %>is-invalid<% } %>" type="password" name="password" placeholder="Password" value="${password}">
								<% if (passwordError != null) { %><div class="invalid-feedback"><%= passwordError %></div><% } %>
							</div>
							<div class="col-12">
								<input class="form-control <% if (confirmError != null) { %>is-invalid<% } %>" type="password" name="confirm" placeholder="Confirm password" value="${confirm}">
									<% if (confirmError != null) { %>
									<div class="invalid-feedback"><%= confirmError %></div>
									<% } else { %>
					    			<small class="form-text text-muted mb-2">At least 8 characters and 1 digit</small>
					    			<% } %>
							</div>
						</div>
					    
					    <button class="btn btn-dark my-3 btn-block rounded" type="submit">Register</button>
					    
						<div class="mb-2">
					    	<p class="mb-1">or register with:</p>
					    	
							<a href="#" class="mx-2" role="button"><i class="fab fa-facebook-f blue-text"></i></a>
							<a href="#" class="mx-2" role="button"><i class="fab fa-twitter light-blue-text"></i></a>
							<a href="#" class="mx-2" role="button"><i class="fab fa-linkedin-in dark-blue-text"></i></a>
							<a href="#" class="mx-2" role="button"><i class="fab fa-github black-text"></i></a>
					    </div>
					    
					    <p>Already a member?
		        			<a href="login">Login</a>
		    			</p>
		    			
		    			<hr>
		    			
		    			<p>By clicking <em>Register</em> you agree to our <a href="" target="_blank">terms of service</a></p>
					</form>
				</div>
			</div>
		</div>
		<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.7.6/js/mdb.min.js"></script>
		<script type="text/javascript" color="0,0,0" opacity='0.3' zIndex="-2" count="99" src="${pageContext.request.contextPath}/js/canvas-nest.js"></script>
		<script src="${pageContext.request.contextPath}/js/main.js"></script>
	</body>
</html>