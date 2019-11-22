<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="net.jaredible.mindbank.util.StringMap" %>
<%
int userId = (int) session.getAttribute("userId");
boolean loggedIn = userId != -1;
Map<String, String> errors = (StringMap) request.getAttribute("errors");
String oldPasswordError = null;
String newPasswordError = null;
String newPasswordConfirmError = null;
String phoneNumberError = null;

if (errors != null) {
	oldPasswordError = errors.get("oldPassword");
	newPasswordError = errors.get("newPassword");
	newPasswordConfirmError = errors.get("newPasswordConfirm");
	phoneNumberError = errors.get("phoneNumber");
}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<title>Security | Mindbank</title>
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
			
			<!--<div class="alert alert-success rounded-0 text-center m-0" role="alert">Security updated!</div>-->
			
			<div class="main d-flex justify-content-center align-items-center">
				<div class="container">
					<form id="profile-form" class="text-center mw-300 m-auto" action="security" method="post" novalidate>
						<p class="h4 mb-3">Change password</p>
						
						<hr>
												
						<div class="form-row justify-content-center align-items-center">
							<div class="col-12 mb-2">
								<input class="form-control <% if (oldPasswordError != null) { %>is-invalid<% } %>" type="password" name="oldPassword" placeholder="Old password" value="${oldPassword}">
								<% if (oldPasswordError != null) { %><div class="invalid-feedback"><%= oldPasswordError %></div><% } %>
							</div>
						</div>
						
						<div class="form-row justify-content-center align-items-center">
							<div class="col-12 mb-2">
								<input class="form-control <% if (newPasswordError != null) { %>is-invalid<% } %>" type="password" name="newPassword" placeholder="New password" value="${newPassword}">
								<% if (newPasswordError != null) { %><div class="invalid-feedback"><%= newPasswordError %></div><% } %>
							</div>
						</div>
						
						<div class="form-row justify-content-center align-items-center">
							<div class="col-12">
								<input class="form-control <% if (newPasswordConfirmError != null) { %>is-invalid<% } %>" type="password" name="newPasswordConfirm" placeholder="Confirm new password" value="${newPasswordConfirm}">
								<% if (newPasswordConfirmError != null) { %>
								<div class="invalid-feedback"><%= newPasswordConfirmError %></div>
								<% } else { %>
			    				<small class="form-text text-muted">At least 8 characters and 1 digit</small>
			    				<% } %>
							</div>
						</div>
						
						<hr>
						
						<div class="form-row justify-content-around align-items-center">
							<div class="col-sm-12">
		            			<button class="btn btn-outline-grey waves-effect rounded" type="submit">Update password</button>
		        			</div>
							<div class="col-sm-12">
		            			<a href="forgot">I forgot my password</a>
							</div>
						</div>
					</form>
					
					<form id="account-form" class="text-center mw-500 m-auto" action="security?two-factor=true" method="post" novalidate>
						<p class="h4 mb-3 test8">Two-factor authentication</p>
						
						<hr>
						
						<p>Two-factor authentication adds an additional layer of security to your account by requiring more than just a password to log in.</p>
						
						<div class="form-row justify-content-center align-items-center mw-300 m-auto">
							<div class="col-12">
								<input class="form-control <% if (phoneNumberError != null) { %>is-invalid<% } %>" type="text" name="phoneNumber" placeholder="Phone number" value="${phoneNumber}" data-mask="(000) 000-0000">
								<% if (phoneNumberError != null) { %>
								<div class="invalid-feedback"><%= phoneNumberError %></div>
								<% } else { %>
								<small class="form-text text-muted">Optional - for two step authentication</small>
								<% } %>
							</div>
						</div>
						
						<div class="form-row justify-content-center align-items-center mb-2">
							<div class="col-12">
								<button class="btn btn-outline-green waves-effect rounded" type="submit" disabled>Enable</button>
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
		<script src="${pageContext.request.contextPath}/js/jquery.mask.min.js"></script>
		<script type="text/javascript" color="0,0,0" opacity='0.3' zIndex="-2" count="99" src="js/canvas-nest.js"></script>
		<script src="${pageContext.request.contextPath}/js/main.js"></script>
	</body>
</html>