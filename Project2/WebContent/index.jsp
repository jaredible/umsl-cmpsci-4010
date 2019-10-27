<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="main.java.mindbank.model.User" %>
<%@ page import="main.java.mindbank.model.Subject" %>
<%@ page import="main.java.mindbank.util.SubjectList" %>
<%@ page import="main.java.mindbank.model.Category" %>
<%@ page import="main.java.mindbank.util.CategoryList" %>
<%@ page import="main.java.mindbank.model.Problem" %>
<%@ page import="main.java.mindbank.util.ProblemList" %>
<%@ page import="com.google.gson.Gson" %>
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
	
	User user = (User) request.getAttribute("user");
	List<Subject> subjects = (SubjectList) request.getAttribute("subjects");
	List<Category> categories = (CategoryList) request.getAttribute("categories");
	ProblemList problems = (ProblemList) request.getAttribute("problems");
	int length;
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Home</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.7.6/css/mdb.min.css">
		<link rel="stylesheet" href="css/styles.css">
	</head>
	<body>
		<nav class="mb-1 navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
			<a class="navbar-brand" href="${pageContext.request.contextPath}">Mindbank</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="navbar-nav ml-auto">
					<% if (email != null) { %>
					<li class="nav-item dropdown">
						<a class="nav-link" href="newProblem"><i class="fas fa-plus"></i> New </a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="settings"><i class="fas fa-cogs"></i> Settings </a>
					</li>
					<li class="nav-item dropdown">
						<a id="navbarDropdown" class="nav-link dropdown-toggle" data-toggle="dropdown"><i class="fas fa-user"></i> Profile </a>
						<div class="dropdown-menu dropdown-menu-right dropdown-info">
							<a class="dropdown-item" href="account">My account</a>
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
				<div class="row justify-content-center align-items-center mt-5 mb-4 pt-3 pb-4">
					<div class="col-xs-12 col-sm-4 mb-2">
						<select class="browser-default custom-select">
							<option value="0" selected>Select a subject</option>
							<%
								length = subjects.size();
								for (int i = 0; i < length; i++) {
									Subject s = subjects.get(i);
							%>
							<option value="<%= s.getId() %>"><%= s.getName() %></option>
							<% } %>
						</select>
					</div>
					<div class="col-xs-12 col-sm-4 mb-2">
						<select class="browser-default custom-select" disabled>
							<option value="0" selected>Select a category</option>
							<%
								length = categories.size();
								for (int i = 0; i < length; i++) {
									Category c = categories.get(i);
							%>
							<option value="<%= c.getId() %>"><%= c.getName() %></option>
							<% } %>
						</select>
					</div>
					<div class="col-xs-12 col-sm-4 mb-2">
						<input class="form-control" type="text" placeholder="Search anything">
					</div>
					<% if (problems.size() > 0) { %>
					<div class="list-group test">
						<%
						length = problems.size();
						for (int i = 0; i < length; i++) {
							Problem p = problems.get(i);
							String footer = "footer";
						%>
						<div class="list-group-item list-group-item-action flex-column justify-content-center align-items-center p-0 test <% if (i == 0) { %>rounded-top<% } else if (i == length - 1) { %>rounded-bottom<% } %>">
							<div class="d-flex w-100 justify-content-between align-items-center">
								<h5 class="mt-1 mb-1 p1-1 pl-2"><%= p.getTitle() == null ? "" : p.getTitle() %></h5>
								<% if (p.getCreatedByUserId() == user.getId()) { %>
								<div class="d-flex justify-content-between align-items-center pr-2">
									<button type="button" class="btn btn-sm btn-outline-grey px-1 waves-effect mt-1 pt-1">Edit</button>
									<button type="button" class="btn btn-sm btn-outline-grey px-1 waves-effect mt-1 pt-1">Delete</button>
								</div>
								<% } %>
							</div>
							<p class="pl-2 pr-2 mb-1"><%= p.getContent() == null ? "" : p.getContent() %></p>
							<div class="d-flex w-100 justify-content-center align-items-center">
								<small class="mb-1 pl-2"><%= footer %><% if (p.isEdited()) { %> - <i>edited</i><% } %></small>
							</div>
						</div>
						<% } %>
						<nav class="my-3 p-1">
							<ul class="pagination pg-blue justify-content-center align-items-center mb-0 p-0">
								<li class="page-item">
									<a class="page-link" tabindex="-1">Previous</a>
								</li>
								<li class="page-item"><a class="page-link">1</a></li>
								<li class="page-item"><a class="page-link">2</a></li>
								<li class="page-item"><a class="page-link">3</a></li>
								<li class="page-item">
									<a class="page-link">&nbsp;&nbsp;Next&nbsp;&nbsp;</a>
								</li>
							</ul>
						</nav>
					</div>
					<% } %>
				</div>
			</div>
		</main>
		<%@ include file="footer.jsp" %>
		<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.7.6/js/mdb.min.js"></script>
		<script type="text/javascript" color="0,0,0" opacity='0.7' zIndex="-2" count="99" src="js/canvas-nest.js"></script>
		<script id="MathJax-script" async src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js"></script>
		<script src="js/main.js"></script>
		<script>
			var subjects = <%= new Gson().toJson(subjects) %>;
			var categories = <%= new Gson().toJson(categories) %>;
			var problems = <%= new Gson().toJson(problems) %>;
			
			console.log(subjects);
			console.log(categories);
			console.log(problems);
		</script>
	</body>
</html>