<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="main.java.mindbank.util.StringMap" %>
<%
int userId = (int) session.getAttribute("userId");
boolean loggedIn = userId != -1;
Map<String, String> errors = (StringMap) request.getAttribute("errors");
String nameError = null;
String bioError = null;

if (errors != null) {
	nameError = errors.get("name");
	bioError = errors.get("bio");
}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<title>Profile | Mindbank</title>
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
			
			<div class="alert alert-success rounded-0 text-center m-0" role="alert">Profile updated!</div>
			
			<div class="main d-flex justify-content-center align-items-center">
				<div class="container">
					<form id="profile-form" class="text-center mw-600 m-auto" action="profile" method="post" novalidate>
						<p class="h4 mb-3">Your profile</p>
						
						<hr>
												
						<div class="form-row justify-content-center align-items-center">
							<div class="col-sm-6 order-sm-12">
								<div class="view overlay">
									<img src="https://www.mememaker.net/api/bucket?path=static/img/memes/full/2019/Mar/15/2/victory-36356.png" class="img-fluid border rounded" alt="placeholder">
									<a>
										<div class="mask waves-effect rgba-white-slight rounded"></div>
									</a>
								</div>
							</div>
							<div class="col-sm-6 order-sm-1 h-100">
								<div class="form-row justify-content-center align-items-center">
									<div class="col-12 my-2">
										<input class="form-control <% if (nameError != null) { %>is-invalid<% } %>" type="text" name="name" placeholder="Name" value="${name}">
										<% if (nameError != null) { %><div class="invalid-feedback"><%= nameError %></div><% } %>
									</div>
								</div>
								<div class="form-row justify-content-center align-items-center">
									<div class="col-12">
										<textarea class="form-control <% if (bioError != null) { %>is-invalid<% } %>" name="bio" placeholder="Tell us a little about yourself">${bio}</textarea>
										<% if (bioError != null) { %><div class="invalid-feedback"><%= bioError %></div><% } %>
									</div>
								</div>
							</div>
						</div>
					    
					    <hr>
					    
					   <div class="d-flex justify-content-center align-items-center">
							<button class="btn btn-outline-grey text-dark waves-effect rounded" type="submit">Update profile</button>
						</div>
					</form>
				</div>
			</div>
			
			<%@ include file="footer.jsp" %>
		</div>
		
		<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.7.6/js/mdb.min.js"></script>
		<script type="text/javascript" color="0,0,0" opacity='0.5' zIndex="-2" count="99" src="js/canvas-nest.js"></script>
		<script src="${pageContext.request.contextPath}/js/main.js"></script>
	</body>
</html>