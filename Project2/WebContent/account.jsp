<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="main.java.mindbank.model.User" %>
<%@ page import="java.util.Map" %>
<%@ page import="main.java.mindbank.util.StringMap" %>
<%
User user = (User) session.getAttribute("user");
Map<String, String> errors = (StringMap) request.getAttribute("errors");
String userNameError = null;
String firstNameError = null;
String lastNameError = null;
String emailError = null;

if (errors != null) {
	userNameError = errors.get("userName");
	firstNameError = errors.get("firstName");
	lastNameError = errors.get("lastName");
	emailError = errors.get("email");
}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<title>Account | Mindbank</title>
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
							<a class="nav-link rounded mx-1" href="newProblem"><i class="fas fa-plus"></i> New problem </a>
						</li>
						<li class="nav-item">
							<a class="nav-link rounded mx-1" href="settings"><i class="fas fa-cogs"></i> Settings </a>
						</li>
						<li class="nav-item dropdown">
							<a id="navbarDropdown" class="nav-link dropdown-toggle rounded" data-toggle="dropdown"><i class="fas fa-user"></i> Profile </a>
							<div class="dropdown-menu dropdown-menu-right dropdown-info">
								<a class="dropdown-item" href="${pageContext.request.contextPath}">Home</a>
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
				<div class="container">
					<div class="d-flex h-100 justify-content-center align-items-center">
						<form class="d-fixed p-1 needs-validation" action="account" method="post" novalidate>
							<div class="row">
								<div class="col-md-4">
									<div class="profile-img text-center">
										<img src="https://mdbootstrap.com/img/Photos/Others/placeholder-avatar.jpg" class="img-fluid rounded-circle border avatar-pic" alt="example placeholder avatar">
									</div>
								</div>
								<div class="col-md-6">
									<div class="profile-head">
										<h5>Jared Diehl</h5>
										<h6>Web Developer</h6>
										<ul id="tab" class="nav nav-tabs" role="tablist">
											<li class="nav-item">
												<a id="about-tab" href="#about" class="nav-link active show" data-toggle="tab" role="tab">Test1</a>
											</li>
											<li class="nav-item">
												<a id="stats-tab" href="#stats" class="nav-link" data-toggle="tab" role="tab">Test2</a>
											</li>
										</ul>
									</div>
								</div>
								<div class="col-md-2">
									<button type="submit" class="btn btn-sm btn-outline-grey px-1 waves-effect mt-1 pt-1">Edit</button>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">
								</div>
								<div class="col-md-8">
									<div id="tab-content" class="tab-content profile-content">
										<div id="about" class="tab-pane fade" role="tabpanel">
											<div class="row">
												<div class="col-md-6">
													<label>Username</label>
												</div>
												<div class="col-md-6">
													<p>Jaredible</p>
												</div>
											</div>
										</div>
										<div id="stats" class="tab-pane fade" role="tabpanel">
											<div class="row">
												<div class="col-md-6">
													<label>Test</label>
												</div>
												<div class="col-md-6">
													<p>Testing</p>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</form>
					</div>
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