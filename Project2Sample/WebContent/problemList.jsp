<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<title>Problems | Mathbank</title>
		<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon.ico">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
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
							<a id="pills-problems-tab" class="nav-link active" href="problem">Problems</a>
						</li>
						<li class="nav-item">
							<a id="pills-categories-tab" class="nav-link" href="category">Categories</a>
						</li>
					</ul>
				</header>
				<!-- END TABS -->
				
				<!-- BEGIN MAIN CONTENT -->
				<div class="container">
					<!-- BEGIN FILTER -->
					<form action="problem" method="post">
						<div class="form-row justify-content-center align-items-center">
							<div class="form-group col-sm-12 col-md-6 my-3">
								<div class="input-group">
									<select class="custom-select">
										<option value="0" selected>Filter by a category</option>
										<option value="1">...</option>
									</select>
									<div class="input-group-append">
										<input class="btn btn-outline-secondary" type="submit" value="Filter">
									</div>
								</div>
							</div>
						</div>
					</form>
					<!-- END FILTER -->
					
					<!-- BEGIN PROBLEM-LIST TABLE -->
					<div class="border rounded overflow-auto" style="height: 300px;">
						<table class="table table-striped">
							<thead>
								<tr>
									<th scope="col">PID</th>
									<th scope="col">CID</th>
									<th scope="col">Title</th>
									<th scope="col"></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="i" begin="1" end="20">
									<tr>
										<th scope="row">${i}</th>
										<td>2</td>
										<td>Testing</td>
										<td align="right"><button class="btn btn-sm btn-secondary" type="button" data-toggle="modal" data-target="#modal-view">View</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- END PROBLEM-LIST TABLE -->
					
					<!-- BEGIN ADD-PROBLEM FORM -->
					<form class="my-3" action="problem" method="post">
						<div class="h5 text-center mb-3">New problem</div>
						
						<div class="form-row justify-content-center align-items-center">
							<div class="form-group col-sm-12 col-md-6">
								<input class="form-control" name="title" placeholder="Title">
							</div>
							<div class="form-group col-sm-12 col-md-6">
								<select class="custom-select" name="categoryId">
									<option value="0" selected>Select a category</option>
									<option value="1">...</option>
								</select>
							</div>
							<div class="form-group col-12">
								<textarea class="form-control" name="content" rows="10" placeholder="Type a problem"></textarea>
							</div>
						</div>
						
						<div class="d-flex justify-content-center">
							<input class="btn btn-primary justify-content-center" type="submit" value="Submit">
						</div>
					</form>
					<!-- END ADD-PROBLEM FORM -->
				</div>
				<!-- END MAIN CONTENT -->
			</div>
		</div>
	</body>
	
	<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
	<script id="MathJax-script" async src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js"></script>
	<script src="${pageContext.request.contextPath}/js/main.js"></script>
</html>