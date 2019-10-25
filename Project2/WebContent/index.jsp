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
							<a class="nav-link" href="settings">Settings</a>
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
					<div class="col-xs-12 col-lg-4 mb-2">
						<input class="form-control" type="text" placeholder="Search anything">
					</div>
					<div class="list-group test">
						<%
							int length = 6;
							for (int i = 0; i < length; i++) {
						%>
							<div class="list-group-item list-group-item-action flex-column justify-content-center align-items-center p-0 test <% if (i == 0) { %>rounded-top<% } else if (i == length - 1) { %>rounded-bottom<% } %>">
								<div class="d-flex w-100 justify-content-between align-items-center">
									<h5 class="mb-1 p-2">
										Testing
									</h5>
									<div class="d-flex justify-content-between align-items-center">
										<button type="button" class="btn btn-sm btn-outline-grey px-1 waves-effect">Edit</button>
										<button type="button" class="btn btn-sm btn-outline-grey px-1 waves-effect">Delete</button>
									</div>
								</div>
								<p class="mb-1">
									$$ \sum_{i=0}^n $$
									$$ \int_1^\infty $$
									$$ \iint_1^\infty $$
									
									$$ \lim_{x \to 0} $$
									
									$$\begin{array}{c|lcr}
									n & \text{Left} & \text{Center} & \text{Right} \\\\
									\hline
									1 & 1.97 & 5 & 12 \\\\
									2 & -11 & 19 & -80 \\\\
									3 & 70 & 209 & 1+i \\\\
									\end{array}$$
									
									$$\left[
									\begin{matrix}
									V_A \\\\
									V_B \\\\
									V_C \\\\
									\end{matrix}
									\right] =
									\left[
									\begin{matrix}
									1 & 0 & L \\\\
									-cosψ & sinψ & L \\\\
									-cosψ & -sinψ & L
									\end{matrix}
									\right]
									\left[
									\begin{matrix}
									V_x \\\\
									V_y \\\\
									W \\\\
									\end{matrix}
									\right] $$
									
									$$\frac{\partial u}{\partial t}
									= h^2 \left( \frac{\partial^2 u}{\partial x^2} +
									\frac{\partial^2 u}{\partial y^2} +
									\frac{\partial^2 u}{\partial z^2}\right)$$
								</p>
								<div class="d-flex w-100 justify-content-between align-items-center">
									<small class="mb-1">Donec id elit non mi porta.</small>
									<small class="mb-1">3 days ago</small>
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
									<a class="page-link">Next&nbsp;&nbsp;&nbsp;&nbsp;</a>
								</li>
							</ul>
						</nav>
					</div>
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
	</body>
</html>