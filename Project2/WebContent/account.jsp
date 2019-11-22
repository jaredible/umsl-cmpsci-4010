<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="net.jaredible.mindbank.util.StringMap" %>
<%
int userId = (int) session.getAttribute("userId");
boolean loggedIn = userId != -1;
Map<String, String> errors = (StringMap) request.getAttribute("errors");
String emailError = null;
String userNameError = null;

if (errors != null) {
	emailError = errors.get("email");
	userNameError = errors.get("userName");
}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<title>Account | Mindbank</title>
		<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon.ico">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.7.6/css/mdb.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
	</head>
	<body>
		<div class="wrapper">
			<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
				<a class="navbar-brand" href="${pageContext.request.contextPath}">Mindbank</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div id="navbar" class="collapse navbar-collapse">
					<ul class="navbar-nav ml-auto">
						<% if (loggedIn) { %>
						<li class="nav-item dropdown">
							<a id="navbarDropdown" class="nav-link dropdown-toggle rounded mx-1" data-toggle="dropdown"><i class="fas fa-plus"></i></a>
							<div class="dropdown-menu dropdown-menu-right dropdown-info">
								<a class="dropdown-item" href="problem">New problem</a>
							</div>
						</li>
						<li class="nav-item dropdown">
							<a id="navbarDropdown" class="nav-link dropdown-toggle rounded mx-1" data-toggle="dropdown"><i class="fas fa-user"></i></a>
							<div class="dropdown-menu dropdown-menu-right dropdown-info">
								<a class="dropdown-item" href="${pageContext.request.contextPath}">Home</a>
								<a class="dropdown-item" href="profile">Profile</a>
								<a class="dropdown-item" href="account">Account</a>
								<a class="dropdown-item" href="security">Security</a>
								<a class="dropdown-item" href="help">Help</a>
								<a class="dropdown-item" href="logout">Log out</a>
							</div>
						</li>
						<% } else { %>
						<li class="nav-item">
							<a class="nav-link rounded mx-1" href="login">Log in</a>
						</li>
						<li class="nav-item">
							<a class="nav-link rounded mx-1" href="register">Register</a>
						</li>
						<% } %>
					</ul>
				</div>
			</nav>
			
			<!--<div class="alert alert-success rounded-0 text-center m-0" role="alert">Account updated!</div>
			
			<div class="alert alert-warning rounded-0 text-center m-0" role="alert">Verify your e-mail address!</div>-->
			
			<div class="main d-flex justify-content-center align-items-center">
				<div class="container">
					<form id="account-form" class="text-center mw-300 m-auto" action="account" method="post" novalidate>
						<p class="h4 mb-3">Your account</p>
						
						<hr>
						
						<div class="form-row justify-content-center align-items-center mb-2">
							<div class="col-12">
								<input class="form-control <% if (emailError != null) { %>is-invalid<% } %>" type="text" name="email" placeholder="E-mail" value="${email}" disabled>
								<% if (emailError != null) { %><div class="invalid-feedback"><%= emailError %></div><% } %>
							</div>
						</div>
						
						<div class="form-row justify-content-center align-items-center mb-2">
							<div class="col-12">
								<input class="form-control <% if (userNameError != null) { %>is-invalid<% } %>" type="text" name="userName" placeholder="Username" value="${userName}" maxlength="12">
								<% if (userNameError != null) { %><div class="invalid-feedback"><%= userNameError %></div><% } %>
							</div>
						</div>
						
						<hr>
						
						<div class="d-flex justify-content-center align-items-center">
							<button class="btn btn-outline-grey waves-effect rounded" type="submit">Update account</button>
						</div>
					</form>
					
					<form id="account-form" class="text-center mw-500 m-auto" action="account?delete=true" method="post" novalidate>
						<p class="h4 mb-3 test7">Delete account</p>
						
						<hr>
						
						<p>Once you delete your account, there is no going back. Please be certain.</p>
						
						<div class="form-row justify-content-center align-items-center mb-2">
							<div class="col-12">
								<button class="btn btn-outline-red waves-effect rounded" type="submit">Delete your account</button>
							</div>
						</div>
					</form>
				</div>
			</div>
			
			<%@ include file="footer.jsp" %>
		</div>
		
		<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.7.6/js/mdb.min.js"></script>
		<script type="text/javascript" color="0,0,0" opacity='0.3' zIndex="-2" count="99" src="js/canvas-nest.js"></script>
		<script src="${pageContext.request.contextPath}/js/main.js"></script>
	</body>
</html>