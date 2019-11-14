<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
							<a id="pills-problems-tab" class="nav-link active" href="problemList">Problems</a>
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
					<form class="text-center" action="problemList" method="get">
						<div class="form-row justify-content-center align-items-center">
							<div class="form-group col-sm-12 col-md-6 my-3">
								<div class="input-group">
									<select class="custom-select${errors.categoryId != null ? ' is-invalid' : ''}" name="id">
										<option value="0"${categoryId == null ? ' selected' : ''}>Any category</option>
										<c:forEach var="category" items="${categories}">
											<option value="${category.key}"${category.key}"${categoryId == category.key ? ' selected' : ''}>${category.value.name}</option>
										</c:forEach>
									</select>
									<div class="input-group-append">
										<input class="btn btn-outline-secondary rounded-right" type="submit" value="Filter">
									</div>
									<c:if test="${errors.categoryId != null}">
										<div class="invalid-feedback">${errors.categoryId}</div>
									</c:if>
								</div>
							</div>
						</div>
					</form>
					<!-- END FILTER -->
					
					<!-- BEGIN PROBLEM-LIST TABLE -->
					<div class="border rounded overflow-auto vh-50">
						<table class="table table-striped">
							<thead>
								<tr>
									<th scope="col">PID</th>
									<th scope="col">CID</th>
									<th scope="col">Title</th>
									<th scope="col">Edited</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="problem" items="${problems}">
									<tr>
										<th scope="row" width="5%"><a href="problem?id=${problem.id}">${problem.id}</a></th>
										<td width="5%"><a href="category?id=${problem.categoryId}">${problem.categoryId}</a></td>
										<td width="85%">${problem.title}</td>
										<td width="5%"><c:out value="${fn:toUpperCase(problem.edited)}">Unknown</c:out></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- END PROBLEM-LIST TABLE -->
					
					<div class="d-flex justify-content-center align-items-center mt-3">
						<a class="btn btn-lg btn-primary" href="addProblem">Add problem</a>
					</div>
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