<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<title>Categories | Mathbank</title>
		<link rel="icon" type="image/x-icon" href="favicon.ico">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/styles.css">
	</head>
	<body>
		<div class="wrapper">
			<%@ include file="../ribbon.jsp" %>
			
			<div class="main">
				<!-- BEGIN TABS -->
				<header>
					<ul id="pills-tab" class="nav nav-pills justify-content-center my-3" role="tablist">
						<li class="nav-item">
							<a id="pills-home-tab" class="nav-link" href="home">Home</a>
						</li>
						<li class="nav-item">
							<a id="pills-problems-tab" class="nav-link" href="problem">Problems</a>
						</li>
						<li class="nav-item">
							<a id="pills-categories-tab" class="nav-link active" href="category">Categories</a>
						</li>
					</ul>
				</header>
				<!-- END TABS -->
				
				<!-- BEGIN MAIN CONTENT -->
				<div class="container">					
					<!-- BEGIN CATEGORY-LIST TABLE -->
					<div class="border rounded overflow-auto mt-5 vh-50">
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th scope="col">ID</th>
									<th scope="col">Name</th>
									<th scope="col">Edited</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="category" items="${categories}">
									<tr>
										<th scope="row" width="5%"><a href="category?id=${category.id}">${category.id}</a></th>
										<td width="90%"><c:out value="${category.name}" /></td>
										<td width="5%"><c:out value="${fn:toUpperCase(category.edited)}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- END CATEGORY-LIST TABLE -->
					
					<div class="d-flex justify-content-center align-items-center mt-3">
						<a class="btn btn-lg btn-primary" href="newCategory">Add category</a>
					</div>
				</div>
				<!-- END MAIN CONTENT -->
			</div>
			
			<%@ include file="../footer.jsp" %>
		</div>
	</body>
	
	<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
	<script id="MathJax-script" async src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js"></script>
	<script type="text/javascript" color="0,0,0" opacity='0.3' zIndex="-2" count="60" src="js/canvas-nest.js"></script>
	<script src="js/main.js"></script>
</html>