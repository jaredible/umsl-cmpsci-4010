<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="main.java.mindbank.model.User" %>
<%@ page import="main.java.mindbank.model.Category" %>
<%@ page import="main.java.mindbank.util.CategoryList" %>
<%
User user = (User) session.getAttribute("user");
List<Category> categories = (CategoryList) request.getAttribute("categories");
String edit = request.getParameter("edit");
request.setAttribute("id", 2);
System.out.println(request.getAttribute("errors"));
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<title>${title} | Mindbank</title>
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
				<div class="container mw-600">
					<% if (edit != null && edit.equals("true")) { %>
					<form id="problem-form" class="text-center" action="problem?id=${param.id}" method="post" novalidate style="flex: 1;">
						<p class="h4 mb-3">New problem</p>
						
						<hr>
						
						<div class="form-row justify-content-center align-items-center">
							<div class="col-sm-6 mb-2">
								<input class="form-control" type="text" name="title" placeholder="Title" value="${title}">
							</div>
							
							<div class="col-sm-6 mb-2">
								<select class="browser-default custom-select" name="categoryId">
									<option value="0" selected>Select a category</option>
									<%
									if (categories != null) {
										for (int i = 0; i < categories.size(); i++) {
											Category c = categories.get(i);
									%>
									<option value="<%= c.getId() %>"><%= c.getName() %></option>
									<% }} %>
								</select>
							</div>
						</div>
						
						<div class="form-row justify-content-center align-items-center">
							<div class="col-sm-12">
								<textarea class="form-control w-100 h-100" name="content" placeholder="Write your problem statement here">${content}</textarea>
							</div>
						</div>
						
						<hr>
						
						<div class="d-flex justify-content-center align-items-center">
							<button class="btn btn-outline-grey waves-effect rounded" type="submit">Post</button>
						</div>
					</form>
					<% } else { %>
					<article class="problem-wrapper">
						<header class="problem-header">
							<a href="${pageContext.request.contextPath}/problem?id=${param.id}&edit=true">Edit</a>
							<h1 class="problem-title">${title}</h1>
							<div class="problem-meta">
								<div class="problem-time">Published: <a href="">${time}</a></div>
								<div class="problem-author">Author: <a href="">${author}</a></div>
								<div class="problem-category">Category: <a href="">${category}</a></div>
							</div>
						</header>
						<div class="problem-content">${content}</div>
					</article>
					<% } %>
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