<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<nav class="mb-1 navbar navbar-expand-lg navbar-dark bg-dark">
			<a class="navbar-brand" href="${pageContext.request.contextPath}">Mindbank</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="navbar-nav ml-auto">
					<% if (email != null) { %>
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
		<div class="container">
			<div class="row justify-content-center align-items-center">
				<div class="col-xs-12 col-lg-4 mb-2">
					<select class="browser-default custom-select">
						<option selected>Select a subject</option>
						<option>Computer Science</option>
						<option>English</option>
						<option>Mathematics</option>
						<option>Physics</option>
					</select>
				</div>
				<div class="col-xs-12 col-lg-4 mb-2">
					<select class="browser-default custom-select">
						<option selected>Select a category</option>
						<option>Algebra</option>
						<option>Calculus</option>
						<option>Combinatorics</option>
						<option>Geometry</option>
						<option>Logic</option>
						<option>Number Theory</option>
						<option>Trigonometry</option>
					</select>
				</div>
				<div class="col-xs-6 col-lg-2 mb-2">
					<button type="button" class="btn btn-dark">Go</button>
				</div>
				<div class="col-xs-6 col-lg-2 mb-2">
					<button type="button" class="btn btn-dark">New Problem</button>
				</div>
				<% for (int i = 0; i < 3; i++) { %>
					<div class="col-lg-3"></div>
					<div class="col-lg-6">
						<div class="card m-1">
							<div class="card-body">
								<h5 class="card-title">Panel title</h5>
								<p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This card has even longer content than the first to show that equal height action.</p>
							</div>
						</div>
					</div>
					<div class="col-lg-3"></div>
				<% } %>
			</div>
		</div>
		<%@ include file="footer.jsp" %>
		<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.7.6/js/mdb.min.js"></script>
		<script type="text/javascript" color="0,0,0" opacity='0.7' zIndex="-2" count="99" src="js/canvas-nest.js"></script>
		<script src="js/main.js"></script>
	</body>
</html>