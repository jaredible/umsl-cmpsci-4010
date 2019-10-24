<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String username = null;
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (Cookie c : cookies) {
			if (c.getName().equals("username")) {
				username = c.getValue();
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
		<title>Account</title>
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
					<% if (username != null) { %>
						<li class="nav-item dropdown">
							<a id="navbarDropdown" class="nav-link dropdown-toggle" data-toggle="dropdown"><i class="fas fa-user"></i> Profile </a>
							<div class="dropdown-menu dropdown-menu-right dropdown-info">
								<a class="dropdown-item" href=${pageContext.request.contextPath}>Home</a>
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
			<form class="d-fixed text-center p-5" action="account" method="post">
				<p class="h4 mb-3">Account</p>

				<div class="form-row mb-3">
					<div class="col">
						<input type="text" name="firstname" class="form-control" placeholder="First name" value="${firstname}">
					</div>
					<div class="col">
						<input type="text" name="lastname" class="form-control" placeholder="Last name" value="${lastname}">
					</div>
				</div>
				
				<input type="text" name="username" class="form-control mb-3" placeholder="Username" value="${username}">
				
				<input type="email" name="email" class="form-control mb-3" placeholder="E-mail" value="${email}">
				
				<input type="password" name="currentpassword" class="form-control mb-2" placeholder="Current password">
				<input type="password" name="newpassword" class="form-control mb-2" placeholder="New password">
				<input type="password" name="confirmnew" class="form-control" placeholder="Confirm password">
			    <small class="form-text text-muted mb-4">
			        At least 8 characters and 1 digit
			    </small>
			    
			    <input type="text" name="phone" class="form-control" placeholder="Phone number" value="${phone}">
			    <small class="form-text text-muted mb-3">
			        Optional - for two step authentication
			    </small>
			    
			    <button class="btn btn-dark my-3 btn-block" type="submit">Save</button>
			</form>
		</div>
		<footer class="page-footer font-small bg-dark">
			<div class="footer-copyright text-center py-3">&copy; 2019 Copyright: <a href="https://jaredible.net"> Jaredible</a></div>
		</footer>
		<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.7.6/js/mdb.min.js"></script>
		<script type="text/javascript" color="0,0,0" opacity='0.7' zIndex="-2" count="99" src="js/canvas-nest.js"></script>
		<script src="js/main.js"></script>
	</body>
</html>