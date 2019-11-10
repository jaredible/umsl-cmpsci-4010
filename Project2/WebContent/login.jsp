<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="main.java.mindbank.util.StringMap" %>
<%@ page import="main.java.mindbank.model.User" %>
<%
Map<String, String> errors = (StringMap) request.getAttribute("errors");
String error = null;

if (errors != null) {
	error = errors.get("error");
}

String remember = (String) request.getAttribute("remember");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<title>Login | Mindbank</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.7.6/css/mdb.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
	</head>
	<body>
		<div class="wrapper">
			<div class="main d-flex justify-content-center align-items-center">
				<div class="container mw-300">
					<form class="text-center" action="login" method="post" novalidate>
						<p class="h4 mb-4">Log in</p>
						
						<% if (error != null) { %><div class="d-block invalid-feedback mb-3"><%= error %></div><% } %>
						
						<div class="form-row">
							<div class="col-12 mb-3">
								<input class="form-control" type="email" name="email" placeholder="E-mail" value="${email}">
							</div>
							<div class="col-12 mb-3">
								<input class="form-control" type="password" name="password" placeholder="Password" value="${password}">
							</div>
						</div>
														
						<div class="d-flex justify-content-around align-items-center">
		        			<div>
		            			<div class="custom-control custom-checkbox">
		                			<input id="remember" class="custom-control-input" type="checkbox" name="remember" <% if (remember != null && remember.equals("on")) { %>checked<% } %>>
		                			<label class="custom-control-label" for="remember">Remember me</label>
		            			</div>
		        			</div>
		       			 	<div>
		            			<a href="forgot">Forgot password?</a>
		        			</div>
		    			</div>
					    
					    <button class="btn btn-dark my-4 btn-block rounded" type="submit">Log in</button>
					    
					    <p>Not a member?<a href="register">Register</a></p>
		    			
		    			<div class="mb-2">
					    	<p class="mb-1">or log in with:</p>
					    	
							<a href="#" class="mx-2" role="button"><i class="fab fa-facebook-f blue-text"></i></a>
							<a href="#" class="mx-2" role="button"><i class="fab fa-twitter light-blue-text"></i></a>
							<a href="#" class="mx-2" role="button"><i class="fab fa-linkedin-in dark-blue-text"></i></a>
							<a href="#" class="mx-2" role="button"><i class="fab fa-github black-text"></i></a>
					    </div>
					</form>
				</div>
			</div>
		</div>
		<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.7.6/js/mdb.min.js"></script>
		<script type="text/javascript" color="0,0,0" opacity='0.3' zIndex="-2" count="99" src="${pageContext.request.contextPath}/js/canvas-nest.js"></script>
		<script src="${pageContext.request.contextPath}/js/main.js"></script>
	</body>
</html>