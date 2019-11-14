<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:parseDate value="${category.createdTime}" pattern="yyyy-MM-dd HH:mm:ss" var="createdTime"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<title>${category.name} | Mathbank</title>
		<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon.ico">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
	</head>
	<body>
		<a class="position-fixed forkMe" href="https://github.com/jaredible/umsl-cmpsci-4010/tree/master/Mathbank" target="_blank"><img width="149" height="149" src="https://github.blog/wp-content/uploads/2008/12/forkme_left_white_ffffff.png?resize=149%2C149" class="attachment-full size-full" alt="Fork me on GitHub" data-recalc-dims="1"></a>
		
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
					<p>
						Name: 
						<a href="#">
							<c:out value="${category.name}">Unknown</c:out>
						</a>
						<c:if test="${category.edited}">
							<small class="text-muted"><i> (edited)</i></small>
						</c:if>
					</p>
					<p>
						Created: 
						<a href="#">
							<fmt:formatDate value="${createdTime}" pattern="MMMM d, yyyy h:mm a" />
						</a>
					</p>
					
					<a class="btn btn-sm btn-secondary" href="editCategory?id=${category.id}">Edit</a>
					
					<hr>
					
					<div class="d-flex justify-content-center align-items-center">
						<p class="mb-0"><c:out value="${category.description}">Unknown</c:out></p>
					</div>
					
					<hr>
				</div>
				<!-- END MAIN CONTENT -->
			</div>
			
			<%@ include file="footer.jsp" %>
		</div>
	</body>
	
	<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
	<script id="MathJax-script" async src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js"></script>
	<script type="text/javascript" color="0,0,0" opacity='0.3' zIndex="-2" count="30" src="js/canvas-nest.js"></script>
	<script src="${pageContext.request.contextPath}/js/main.js"></script>
</html>