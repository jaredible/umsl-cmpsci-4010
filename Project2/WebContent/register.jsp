<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="main.java.mindbank.util.StringMap" %>
<%
	Map<String, String> errors = (StringMap) request.getAttribute("errors");
	String firstNameError = null;
	String lastNameError = null;
	String emailError = null;
	String passwordError = null;
	String confirmError = null;
	
	if (errors != null) {
		firstNameError = errors.get("firstName");
		lastNameError = errors.get("lastName");
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
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Register</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.7.6/css/mdb.min.css">
		<link rel="stylesheet" href="css/styles.css">
	</head>
	<body>
		<div class="container">
			<form class="d-fixed text-center p-1 needs-validation" action="register" method="post" novalidate>
				<p class="h4 mb-3">Register</p>
				
				<div class="form-row">
					<div class="col-6 mb-3">
						<input type="text" name="firstName" class="form-control <% if (firstNameError != null) { %>is-invalid<% } %>" placeholder="First name" value="${user.firstName}">
						<% if (firstNameError != null) { %><div class="invalid-feedback"><%= firstNameError %></div><% } %>
					</div>
					<div class="col-6 mb-3">
						<input type="text" name="lastName" class="form-control <% if (lastNameError != null) { %>is-invalid<% } %>" placeholder="Last name" value="${user.lastName}">
						<% if (lastNameError != null) { %><div class="invalid-feedback"><%= lastNameError %></div><% } %>
					</div>
					<div class="col-12 mb-3">
						<input type="email" name="email" class="form-control <% if (emailError != null) { %>is-invalid<% } %>" placeholder="E-mail" value="${user.email}">
						<% if (emailError != null) { %><div class="invalid-feedback"><%= emailError %></div><% } %>
					</div>
					<div class="col-12 mb-3">
						<input type="password" name="password" class="form-control <% if (passwordError != null) { %>is-invalid<% } %>" placeholder="Password">
						<% if (passwordError != null) { %><div class="invalid-feedback"><%= passwordError %></div><% } %>
					</div>
					<div class="col-12">
						<input type="password" name="confirm" class="form-control <% if (confirmError != null) { %>is-invalid<% } %>" placeholder="Confirm password">
						<% if (confirmError != null) { %>
							<div class="invalid-feedback"><%= confirmError %></div>
						<% } else { %>
			    			<small class="form-text text-muted mb-2">At least 8 characters and 1 digit</small>
			    		<% } %>
					</div>
				</div>
			    
			    <button class="btn btn-dark my-3 btn-block" type="submit">Register</button>
			    
			    <p>Already a member?
        			<a href="login">Login</a>
    			</p>
			</form>
		</div>
		<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.7.6/js/mdb.min.js"></script>
		<script type="text/javascript" color="0,0,0" opacity='0.7' zIndex="-2" count="99" src="js/canvas-nest.js"></script>
		<script src="js/main.js"></script>
	</body>
</html>