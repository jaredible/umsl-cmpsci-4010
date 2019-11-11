<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="main.java.mindbank.model.Category" %>
<%@ page import="main.java.mindbank.util.CategoryList" %>
<%@ page import="main.java.mindbank.model.Problem" %>
<%@ page import="main.java.mindbank.util.ProblemList" %>
<%@ page import="java.util.Map" %>
<%@ page import="main.java.mindbank.util.StringMap" %>
<%
boolean loggedIn = false;
int userId = -1;
if (session != null) {
	try {
		userId = (int) session.getAttribute("userId");
		loggedIn = true;
	} catch (Exception e) {
	}
}
List<Category> categories = (CategoryList) request.getAttribute("categories");
ProblemList problems = (ProblemList) request.getAttribute("problems");

Map<String, String> errors = (StringMap) request.getAttribute("errors");
String nameError = null;
String descriptionError = null;

if (errors != null) {
	nameError = errors.get("name");
	descriptionError = errors.get("description");
}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<title>Category | Mindbank</title>
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
								<a class="dropdown-item" href="category">New category</a>
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
			
			<div class="main d-flex justify-content-center align-items-center">
				<div class="container">
					<form id="category-form" class="text-center mw-400 m-auto" action="category" method="post" novalidate>
						<p class="h4 mb-3">New category</p>
						
						<hr>
						
						<div class="form-row justify-content-center align-items-center mb-2">
							<div class="col-12">
								<input class="form-control <% if (nameError != null) { %>is-invalid<% } %>" type="text" name="name" placeholder="Name" value="${category}" maxlength="30">
								<% if (nameError != null) { %><div class="invalid-feedback"><%= nameError %></div><% } %>
							</div>
						</div>
						
						<div class="form-row justify-content-center align-items-center mb-2">
							<div class="col-12">
								<textarea class="form-control <% if (descriptionError != null) { %>is-invalid<% } %>" name="description" placeholder="Type a description here" rows="10" maxlength="100">${content}</textarea>
								<% if (descriptionError != null) { %><div class="invalid-feedback"><%= descriptionError %></div><% } %>
							</div>
						</div>
						
						<hr>
						
						<div class="d-flex justify-content-center align-items-center">
							<button class="btn btn-outline-grey waves-effect rounded" type="submit">Post</button>
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
		<script id="MathJax-script" async src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js"></script>
		<script src="${pageContext.request.contextPath}/js/main.js"></script>
	</body>
</html>