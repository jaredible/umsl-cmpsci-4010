<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="main.java.mindbank.model.User" %>
<%@ page import="java.util.Map" %>
<%@ page import="main.java.mindbank.util.StringMap" %>
<%
User user = (User) session.getAttribute("user");
Map<String, String> errors = (StringMap) request.getAttribute("errors");
String oldPasswordError = null;
String newPasswordError = null;
String newPasswordConfirmError = null;

if (errors != null) {
	oldPasswordError = errors.get("oldPassword");
	newPasswordError = errors.get("newPassword");
	newPasswordConfirmError = errors.get("newPasswordConfirm");
}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<title>Security | Mindbank</title>
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
						<% if (user != null) { %>
						<li class="nav-item dropdown">
							<a class="nav-link rounded mx-1" href="problem"><i class="fas fa-plus"></i> New </a>
						</li>
						<li class="nav-item dropdown">
							<a id="navbarDropdown" class="nav-link dropdown-toggle rounded mx-1" data-toggle="dropdown"><i class="fas fa-user"></i> Me </a>
							<div class="dropdown-menu dropdown-menu-right dropdown-info">
								<a class="dropdown-item" href="${pageContext.request.contextPath}">Home</a>
								<a class="dropdown-item" href="profile">Profile</a>
								<a class="dropdown-item" href="account">Account</a>
								<a class="dropdown-item" href="security">Security</a>
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
			
			<div class="main">
				<div class="container test5">
					<form id="profile-form" class="text-center" action="security" method="post" novalidate>
						<p class="h4 mb-3">Change password</p>
												
						<div class="form-row justify-content-center align-items-center">
							<div class="col-12 mb-2">
								<input class="form-control <% if (oldPasswordError != null) { %>is-invalid<% } %>" type="text" name="oldPassword" placeholder="Old password" value="${oldPassword}">
								<% if (oldPasswordError != null) { %><div class="invalid-feedback"><%= oldPasswordError %></div><% } %>
							</div>
						</div>
						
						<div class="form-row justify-content-center align-items-center">
							<div class="col-12 mb-2">
								<input class="form-control <% if (newPasswordError != null) { %>is-invalid<% } %>" type="text" name="newPassword" placeholder="New password" value="${newPassword}">
								<% if (newPasswordError != null) { %><div class="invalid-feedback"><%= newPasswordError %></div><% } %>
							</div>
						</div>
						
						<div class="form-row justify-content-center align-items-center">
							<div class="col-12 mb-2">
								<input class="form-control <% if (newPasswordConfirmError != null) { %>is-invalid<% } %>" type="text" name="newPasswordConfirm" placeholder="Confirm new password" value="${newPasswordConfirm}">
								<% if (newPasswordConfirmError != null) { %>
								<div class="invalid-feedback"><%= newPasswordConfirmError %></div>
								<% } else { %>
			    				<small class="form-text text-muted mb-2">At least 8 characters and 1 digit</small>
			    				<% } %>
							</div>
						</div>
						
						<div class="form-row justify-content-around align-items-center">
							<div class="col-sm-6 order-sm-12">
		            			<a href="forgot">I forgot my password</a>
							</div>
							<div class="col-sm-6 order-sm-1">
		            			<button class="btn btn-outline-grey waves-effect rounded" type="submit">Update password</button>
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