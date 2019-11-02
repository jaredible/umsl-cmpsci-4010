<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="main.java.mindbank.util.StringMap" %>
<%
	String email = null;
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (Cookie c : cookies) {
			if (c.getName().equals("email")) {
				email = c.getValue();
			}
		}
	}
	
	Map<String, String> errors = (StringMap) request.getAttribute("errors");
	String userNameError = null;
	String firstNameError = null;
	String lastNameError = null;
	String emailError = null;
	
	if (errors != null) {
		userNameError = errors.get("userName");
		firstNameError = errors.get("firstName");
		lastNameError = errors.get("lastName");
		emailError = errors.get("email");
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Account | Mindbank</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.7.6/css/mdb.min.css">
		<link rel="stylesheet" href="css/styles.css">
	</head>
	<body>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<a class="navbar-brand" href="${pageContext.request.contextPath}">Mindbank</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="navbar-nav ml-auto">
					<% if (email != null) { %>
						<li class="nav-item dropdown">
							<a class="nav-link" href="newProblem"><i class="fas fa-plus"></i> New problem </a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="settings"><i class="fas fa-cogs"></i> Settings </a>
						</li>
						<li class="nav-item dropdown">
							<a id="navbarDropdown" class="nav-link dropdown-toggle" data-toggle="dropdown"><i class="fas fa-user"></i> Profile </a>
							<div class="dropdown-menu dropdown-menu-right dropdown-info">
								<a class="dropdown-item" href="${pageContext.request.contextPath}">Home</a>
								<a class="dropdown-item" href="logout">Log out</a>
							</div>
						</li>
					<% } else { %>
						<li class="nav-item">
							<a class="nav-link" href="login">Log in</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="register">Register</a>
						</li>
					<% } %>
				</ul>
			</div>
		</nav>
		<main>
			<div class="container">
				<div class="d-flex h-100 justify-content-center align-items-center">
					<form class="d-fixed text-center p-1 needs-validation" action="account" method="post" novalidate>						
						<div class="form-row">
							<div class="col-12 my-3">
								<img src="https://mdbootstrap.com/img/Photos/Others/placeholder-avatar.jpg" class="img-fluid rounded-circle border avatar-pic" alt="example placeholder avatar">
							</div>
							<div class="col-12 mb-3">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text">@</span>
									</div>
									<input type="text" name="username" class="form-control <% if (userNameError != null) { %>is-invalid<% } %>" placeholder="Username" value="${user.userName}">
									<% if (userNameError != null) { %><div class="invalid-feedback"><%= userNameError %></div><% } %>
								</div>
							</div>
							<div class="col-6 mb-3">
								<input type="text" name="firstName" class="form-control <% if (firstNameError != null) { %>is-invalid<% } %>" placeholder="First name" value="${user.firstName}">
								<% if (firstNameError != null) { %><div class="invalid-feedback"><%= firstNameError %></div><% } %>
							</div>
							<div class="col-6 mb-3">
								<input type="text" name="lastName" class="form-control <% if (lastNameError != null) { %>is-invalid<% } %>" placeholder="Last name" value="${user.lastName}">
								<% if (lastNameError != null) { %><div class="invalid-feedback"><%= lastNameError %></div><% } %>
							</div>
							<div class="col-12 mb-3">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text" style="background-color: #78e08f;"><i class="fas fa-check"></i></span>
									</div>
									<input type="email" name="email" class="form-control" placeholder="E-mail" value="${user.email}" disabled>
								</div>
							</div>
							<div class="col-12">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text" style="background-color: none;"><i class="fas fa-check"></i></span>
									</div>
									<input type="text" name="phoneNumber" class="form-control" placeholder="Phone number">
								</div>
								<small class="form-text text-muted">Optional - for two-step authentication</small>
							</div>
						</div>
					    
						<button type="submit" class="btn btn-outline-grey waves-effect">Save</button>
					</form>
				</div>
			</div>
		</main>
		<%@ include file="footer.jsp" %>
		<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.7.6/js/mdb.min.js"></script>
		<script type="text/javascript" color="0,0,0" opacity='0.3' zIndex="-2" count="99" src="js/canvas-nest.js"></script>
		<script src="js/main.js"></script>
	</body>
</html>