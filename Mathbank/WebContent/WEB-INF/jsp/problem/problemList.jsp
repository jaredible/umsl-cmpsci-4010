<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<title>Problems | Mathbank</title>
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
							<a id="pills-problems-tab" class="nav-link active" href="problem">Problems</a>
						</li>
						<li class="nav-item">
							<a id="pills-categories-tab" class="nav-link" href="category">Categories</a>
						</li>
						<li class="nav-item">
							<a id="pills-tags-tab" class="nav-link" href="tag">Tags</a>
						</li>
					</ul>
				</header>
				<!-- END TABS -->
				
				<!-- BEGIN MAIN CONTENT -->
				<div class="container">
					<!-- BEGIN FILTER -->
					<form class="text-center" action="problemList" method="get">
						<div class="form-row justify-content-around align-items-center my-3">
							<div class="col-sm-12 col-md-5 my-2">
								<select class="custom-select${errors.categoryId != null ? ' is-invalid' : ''}" name="categoryId">
									<option value=""${categoryId == null ? ' selected' : ''} disabled hidden>Select a category</option>
									<option value="-1${(categoryId != null && categoryId eq '-1') ? ' selected' : ''}">Any category</option>
									<option value="0"${(categoryId != null && categoryId eq '0') ? ' selected' : ''}>No category</option>
									<c:forEach var="category" items="${categories}">
										<option value="${category.id}"${categoryId == category.id ? ' selected' : ''}><c:out value="${category.name}" /></option>
									</c:forEach>
								</select>
								<c:if test="${errors.categoryId != null}">
									<div class="invalid-feedback">${errors.categoryId}</div>
								</c:if>
							</div>
							<div class="col-sm-12 col-md-5 my-2">
								<input class="form-control" type="text" name="tagNames" placeholder="Search by tag(s)" value="${tagNames}">
							</div>
							<div class="col-sm-12 col-md-2 text-right my-2">
								<button class="btn btn-outline-secondary w-100" type="submit">Filter</button>
							</div>
						</div>
					</form>
					<!-- END FILTER -->
					
					<!-- BEGIN PROBLEM-LIST TABLE -->
					<div class="border rounded overflow-auto vh-50">
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th scope="col">PID</th>
									<th scope="col">CID</th>
									<th scope="col">Title</th>
									<th scope="col">Password</th>
									<th scope="col">Edited</th>
									<th scope="col">Views</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="problem" items="${problems}">
									<tr>
										<th scope="row" width="5%"><a href="problem?id=${problem.id}">${problem.id}</a></th>
										<td width="5%"><a href="category?id=${problem.categoryId}">${problem.categoryId}</a></td>
										<td width="75%"><c:out value="${problem.title}" /></td>
										<td width="5%"><c:out value="No" /></td>
										<td width="5%"><c:out value="${problem.edited ? 'Yes' : 'No'}" /></td>
										<td width="5%">${problem.viewCount}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- END PROBLEM-LIST TABLE -->
					
					<div class="d-flex justify-content-center align-items-center mt-3">
						<a class="btn btn-lg btn-primary" href="newProblem">Add problem</a>
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