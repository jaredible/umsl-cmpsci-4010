<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:parseDate value="${problem.createdTime}" pattern="yyyy-MM-dd HH:mm:ss" var="createdTime"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<title>${problem.title} | Mathbank</title>
		<link rel="icon" type="image/x-icon" href="favicon.ico">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/styles.css">
	</head>
	<body>
		<div id="deleteModal" class="modal fade" tabindex="-1" role="dialog">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Are you sure?</h5>
						<button class="close" type="button" data-dismiss="modal">
							<span>&times;</span>
						</button>
					</div>
					<div class="alert alert-warning mb-0" role="alert">
						Please read this!
					</div>
					<div class="modal-body">
						<form action="deleteProblem" method="post">
							<p>
								This action <strong>cannot</strong> be undone. 
								This will permanently delete the <strong><c:out value="${problem.title}" /></strong> problem.
							</p>
							
							<p>Please type in the title of the problem to confirm.</p>
							
							<input type="hidden" name="id" value="${problem.id}">
							<input id="deleteTitle" class="form-control is-invalid" name="title" data-title="<c:out value="${problem.title}" />" autofocus>
							
							<div class="modal-footer justify-content-center">
								<button id="deleteButton" class="btn btn-danger" type="submit" disabled>Delete this problem</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="wrapper">
			<a class="position-fixed forkMe" href="https://github.com/jaredible/umsl-cmpsci-4010/tree/master/Mathbank" target="_blank"><img width="149" height="149" src="https://github.blog/wp-content/uploads/2008/12/forkme_left_white_ffffff.png?resize=149%2C149" class="attachment-full size-full" alt="Fork me on GitHub" data-recalc-dims="1"></a>
			
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
					<p class="mb-0">
						Title: 
						<a href="#">
							<c:out value="${problem.title}" />
						</a>
						<c:if test="${problem.edited}">
							<small class="text-muted"><i> (edited)</i></small>
						</c:if>
					</p>
					<p class="mb-0">
						Category: 
						<a href="category?id=${problem.categoryId}">
							<c:out value="${categoryName}" />
						</a>
					</p>
					<p class="mb-0">
						Created: 
						<a href="#">
							<fmt:formatDate value="${createdTime}" pattern="MMMM d, yyyy h:mm a" />
						</a>
					</p>
					<p class="mb-0">
						Views: 
						<a href="#">
							${problem.viewCount}
						</a>
					</p>
					
					<br>
					
					<button class="btn btn-sm btn-danger" type="button" data-toggle="modal" data-target="#deleteModal">Delete</button>
					<a class="btn btn-sm btn-secondary" href="editProblem?id=${problem.id}">Edit</a>
					
					<hr>
					
					<div class="d-flex justify-content-center align-items-center">
						<p class="mb-0"><c:out value="${problem.content}" /></p>
					</div>
					
					<hr>
					
					<div id="disqus_thread"></div>
				</div>
				<!-- END MAIN CONTENT -->
			</div>
			
			<%@ include file="../footer.jsp" %>
		</div>
	</body>
	
	<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript" src="https://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
	<script type="text/javascript">
		window.MathJax = {
			tex2jax: {
				inlineMath: [['$', '$'], ["\\(", "\\)"]],
				processEscapes: true
			}
		};
	</script>
	<script type="text/javascript" color="0,0,0" opacity='0.3' zIndex="-2" count="60" src="js/canvas-nest.js"></script>
	<script src="js/main.js"></script>
	<script id="dsq-count-scr" src="//mathbank.disqus.com/count.js" async></script>
	<script>
		var PAGE_URL = window.location.href;
		var PAGE_IDENTIFIER = window.location.href;
		
		var disqus_config = function () {
			this.page.url = PAGE_URL;
			this.page.identifier = PAGE_IDENTIFIER; 
		};
		
    	(function() {
			var d = document, s = d.createElement('script');
			s.src = 'https://mathbank.disqus.com/embed.js';
			s.setAttribute('data-timestamp', +new Date());
			(d.head || d.body).appendChild(s);
		})();
	</script>
	<noscript>
		Please enable JavaScript to view the <a href="https://disqus.com/?ref_noscript" rel="nofollow">comments powered by Disqus.</a>
	</noscript>
</html>