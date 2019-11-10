<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="main.java.mindbank.model.Category" %>
<%@ page import="main.java.mindbank.util.CategoryList" %>
<%@ page import="main.java.mindbank.model.Problem" %>
<%@ page import="main.java.mindbank.util.ProblemList" %>
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

boolean previousEnabled = (boolean) request.getAttribute("previousEnabled");
boolean nextEnabled = (boolean) request.getAttribute("nextEnabled");;
int pageItem1 = (int) request.getAttribute("pageItem1");
int pageItem2 = (int) request.getAttribute("pageItem2");
int pageItem3 = (int) request.getAttribute("pageItem3");
int pageItem4 = (int) request.getAttribute("pageItem4");
int pageItem5 = (int) request.getAttribute("pageItem5");
String pageItem1Link = "";
String pageItem2Link = "?page=1";
String pageItem3Link = "?page=2";
String pageItem4Link = "?page=3";
String pageItem5Link = "?page=4";

String pageNumber = request.getParameter("page");
String category = request.getParameter("category");
String limit = request.getParameter("limit");
System.out.println(pageNumber + " " + category + " " + limit);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<title>Home | Mindbank</title>
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
			
			<div class="main d-flex justify-content-center align-items-center">
				<div class="container">
					<div class="row justify-content-center align-items-center my-2">
						<div class="col-xs-12 col-sm-6 col-md-4 mb-2">
							<select id="category-select" class="browser-default custom-select" autofocus>
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
						<div class="col-12">
							<% if (problems != null && problems.size() > 0) { %>
							<div class="list-group test">
								<%
								int length = problems.size();
								for (int i = 0; i < length; i++) {
									Problem p = problems.get(i);
									String footer = "Posted by Jaredible 3 days ago";
								%>
								<div class="list-group-item list-group-item-action flex-column justify-content-center align-items-center p-0 test problem-card<% if (i == 0) { %> rounded-top<% } if (i == length - 1) { %> rounded-bottom<% } %>" data-id="<%= p.getId() %>">
									<div class="d-flex w-100 justify-content-between align-items-center">
										<h5 class="mt-1 mb-1 p1-1 pl-2"><%= p.getTitle() == null ? "" : p.getTitle() %></h5>
										<% if (loggedIn && p.getCreatedByUserId() == userId) { %>
										<div class="d-flex justify-content-between align-items-center">
											<button type="button" class="btn btn-sm btn-outline-grey px-1 waves-effect mt-1 pt-1">Edit</button>
											<!-- Hamburger dropdown button? <button type="button" class="btn btn-sm btn-outline-grey px-1 waves-effect mt-1 pt-1">Delete</button> -->
										</div>
										<% } %>
									</div>
									<p class="pl-2 pr-2 mb-1"><%= p.getContent() %></p>
									<div class="d-flex w-100 justify-content-left align-items-center">
										<small class="mb-1 pl-2"><%= footer %><% if (p.isEdited()) { %> - <i>edited</i><% } %></small>
									</div>
								</div>
								<% } %>
							</div>
							<% } %>
						</div>
						<div class="col-12">
							<nav class="my-2 p-1">
								<ul class="pagination pg-blue justify-content-center align-items-center mb-0 p-0">
									<li class="page-item"><a class="page-link rounded mx-1 <% if (!previousEnabled) { %>disabled grey-text<% } %>" href="<%= pageItem1Link %>">Previous</a></li>
									<li class="page-item"><a class="page-link rounded mx-1" href="${pageContext.request.contextPath}<%= pageItem2Link %>"><%= pageItem2 %></a></li>
									<li class="page-item"><a class="page-link rounded mx-1" href="${pageContext.request.contextPath}<%= pageItem3Link %>"><%= pageItem3 %></a></li>
									<li class="page-item"><a class="page-link rounded mx-1" href="${pageContext.request.contextPath}<%= pageItem4Link %>"><%= pageItem4 %></a></li>
									<li class="page-item"><a class="page-link rounded mx-1 <% if (!nextEnabled) { %>disabled grey-text<% } %>" href="<%= pageItem5Link %>">Next</a></li>
								</ul>
							</nav>
						</div>
					</div>
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