<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<title>Edit Category | Mathbank</title>
		<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon.ico">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
	</head>
	<body>
		<div class="wrapper">
			<div class="main">
				<!-- BEGIN TABS -->
				<header>
					<ul id="pills-tab" class="nav nav-pills justify-content-center my-3" role="tablist">
						<li class="nav-item">
							<a id="pills-home-tab" class="nav-link" href="${pageContext.request.contextPath}">Home</a>
						</li>
						<li class="nav-item">
							<a id="pills-problems-tab" class="nav-link" href="problem">Problems</a>
						</li>
						<li class="nav-item">
							<a id="pills-categories-tab" class="nav-link" href="category">Categories</a>
						</li>
					</ul>
				</header>
				<!-- END TABS -->
				
				<!-- BEGIN MAIN CONTENT -->
				<div class="container">
					<!-- BEGIN EDIT-CATEGORY FORM -->
					<form class="text-center my-3" action="editCategory?id=${id}" method="post">
						<div class="h5 text-center mb-3">Edit problem</div>
						
						<div class="form-row justify-content-center align-items-center">
							<div class="form-group col-sm-12 col-md-6">
								<input class="form-control${errors.name != null ? ' is-invalid' : ''}" name="name" placeholder="Name" value="${name}" maxlength="20" autofocus>
								<c:if test="${errors.name != null}">
									<div class="invalid-feedback">${errors.name}</div>
								</c:if>
							</div>
						</div>
						<div class="form-row justify-content-center align-items-center">
							<div class="form-group col-sm-12 col-md-6">
								<textarea class="form-control${errors.description != null ? ' is-invalid' : ''}" name="description" rows="10" placeholder="Type a description" maxlength="420">${description}</textarea>
								<c:if test="${errors.description != null}">
									<div class="invalid-feedback">${errors.description}</div>
								</c:if>
							</div>
						</div>
						
						<div class="d-flex justify-content-center align-items-center">
							<input class="btn btn-lg btn-primary" type="submit" value="Update">
						</div>
					</form>
					<!-- END EDIT-CATEGORY FORM -->
				</div>
				<!-- END MAIN CONTENT -->
			</div>
			
			<%@ include file="footer.jsp" %>
		</div>
	</body>
	
	<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
	<script id="MathJax-script" async src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js"></script>
	<script src="${pageContext.request.contextPath}/js/main.js"></script>
</html>