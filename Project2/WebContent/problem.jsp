<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="main.java.mindbank.model.Problem" %>
<%@ page import="main.java.mindbank.model.Category" %>
<%@ page import="main.java.mindbank.util.CategoryList" %>
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

Problem problem = (Problem) request.getAttribute("problem");
List<Category> categories = (CategoryList) request.getAttribute("categories");

String problemId = request.getParameter("id");
String edit = request.getParameter("edit");

Map<String, String> errors = (StringMap) request.getAttribute("errors");
String titleError = null;
String categoryIdError = null;
String contentError = null;

if (errors != null) {
	titleError = errors.get("title");
	categoryIdError = errors.get("categoryId");
	contentError = errors.get("content");
}

int categoryId = 0;
try {
	categoryId = (int) request.getAttribute("categoryId");
} catch (Exception e) {
}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<title>Problem | Mindbank</title>
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
					<% if (problemId == null) { %>
					<form id="problem-form" class="text-center mw-600 m-auto" action="problem" method="post" novalidate>
						<p class="h4 mb-3">New problem</p>
						
						<hr>
						
						<div class="form-row justify-content-center align-items-center">
							<div class="col-sm-6 mb-2">
								<input class="form-control <% if (titleError != null) { %>is-invalid<% } %>" type="text" name="title" placeholder="Title" value="${title}">
								<% if (titleError != null) { %><div class="invalid-feedback"><%= titleError %></div><% } %>
							</div>
							
							<div class="col-sm-6 mb-2">
								<select class="browser-default custom-select <% if (categoryIdError != null) { %>is-invalid<% } %>" name="categoryId">
									<option value="0" selected>Select a category</option>
									<%
									if (categories != null) {
										for (int i = 0; i < categories.size(); i++) {
											Category c = categories.get(i);
									%>
									<option value="<%= c.getId() %>"><%= c.getName() %></option>
									<% }} %>
								</select>
								<% if (categoryIdError != null) { %><div class="invalid-feedback"><%= categoryIdError %></div><% } %>
							</div>
						</div>
						
						<div class="form-row justify-content-center align-items-center">
							<div class="col-sm-12">
								<textarea class="form-control <% if (contentError != null) { %>is-invalid<% } %>" name="content" placeholder="Type your problem here" rows="10">${content}</textarea>
								<% if (contentError != null) { %><div class="invalid-feedback"><%= contentError %></div><% } %>
							</div>
						</div>
						
						<hr>
						
						<div class="d-flex justify-content-center align-items-center">
							<button class="btn btn-outline-grey waves-effect rounded" type="submit">Post</button>
						</div>
					</form>
					<% } else if (edit != null && edit.equals("true")) { %>
					<form id="problem-form" class="text-center mw-600 m-auto" action="problem?id=${param.id}&edit=true" method="post" novalidate>
						<p class="h4 mb-3">Edit problem</p>
						
						<hr>
						
						<div class="form-row justify-content-center align-items-center">
							<div class="col-sm-6 mb-2">
								<input class="form-control <% if (titleError != null) { %>is-invalid<% } %>" type="text" name="title" placeholder="Title" value="${title}">
								<% if (titleError != null) { %><div class="invalid-feedback"><%= titleError %></div><% } %>
							</div>
							
							<div class="col-sm-6 mb-2">
								<select class="browser-default custom-select <% if (categoryIdError != null) { %>is-invalid<% } %>" name="categoryId">
									<option value="0" <% if (categoryId == 0) { %>selected<% } %>>Select a category</option>
									<%
									if (categories != null) {
										for (int i = 0; i < categories.size(); i++) {
											Category c = categories.get(i);
									%>
									<option value="<%= c.getId() %>" <% if (categoryId == c.getId()) { %>selected<% } %>><%= c.getName() %></option>
									<% }} %>
								</select>
								<% if (categoryIdError != null) { %><div class="invalid-feedback"><%= categoryIdError %></div><% } %>
							</div>
						</div>
						
						<div class="form-row justify-content-center align-items-center">
							<div class="col-sm-12">
								<textarea class="form-control <% if (contentError != null) { %>is-invalid<% } %>" name="content" placeholder="Type your problem here" rows="10">${content}</textarea>
								<% if (contentError != null) { %><div class="invalid-feedback"><%= contentError %></div><% } %>
							</div>
						</div>
						
						<hr>
						
						<div class="d-flex justify-content-center align-items-center">
							<button class="btn btn-outline-grey waves-effect rounded" type="submit">Update</button>
						</div>
					</form>
					<% } else { %>
					<article class="problem-wrapper">
						<header class="problem-header">
							<h1 class="problem-title">${title}</h1>
							<div class="problem-meta">
								<div class="problem-time">Published: <a href="#">${time}</a></div>
								<div class="problem-category">Category: <a href="${pageContext.request.contextPath}/?category=ai">${category}</a></div>
								<div class="problem-author">Author: <a href="${pageContext.request.contextPath}/profile?user=${author}">${author}</a></div>
								<% if (loggedIn && problem.getCreatedByUserId() == userId) { %>
								<ul class="navbar-nav mr-auto">
									<li class="nav-item dropdown">
										<a id="navbarDropdown" class="nav-link rounded mx-1" data-toggle="dropdown"><i class="fas fa-ellipsis-h"></i></a>
										<div class="dropdown-menu dropdown-menu-left dropdown-info">
											<a class="dropdown-item" href="${pageContext.request.contextPath}/problem?id=${param.id}&edit=true">Edit</a>
											<a class="dropdown-item" href="${pageContext.request.contextPath}/problem?id=${param.id}&delete=true">Delete</a>
										</div>
									</li>
								</ul>
								<% } %>
							</div>
						</header>
						
						<hr>
						
						<div class="problem-content">${content}</div>
						
						<hr>
						
						<div id="disqus_thread"></div>
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
		<script>
			var PAGE_URL = window.location.href;
			var PAGE_IDENTIFIER = window.location.pathname;
			
			var disqus_config = function () {
				this.page.url = PAGE_URL;
				this.page.identifier = PAGE_IDENTIFIER; 
			};
    
    		(function() {
				var d = document, s = d.createElement('script');
				s.src = 'https://jaredible.disqus.com/embed.js';
				s.setAttribute('data-timestamp', +new Date());
				(d.head || d.body).appendChild(s);
			})();
		</script>
		<noscript>
			Please enable JavaScript to view the <a href="https://disqus.com/?ref_noscript" rel="nofollow">comments powered by Disqus.</a>
		</noscript>
	</body>
</html>