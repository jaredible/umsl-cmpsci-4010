<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<title>Mathbank</title>
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
							<a id="pills-home-tab" class="nav-link active" href="home">Home</a>
						</li>
						<li class="nav-item">
							<a id="pills-problems-tab" class="nav-link" href="problem">Problems</a>
						</li>
						<li class="nav-item">
							<a id="pills-categories-tab" class="nav-link" href="category">Categories</a>
						</li>
						<li class="nav-item">
							<a id="pills-categories-tab" class="nav-link" href="tag">Tags</a>
						</li>
					</ul>
				</header>
				<!-- END TABS -->
				
				<!-- BEGIN HOME CONTENT -->
				<div class="container">
					<div class="d-flex justify-content-center align-items-center">
						<fmt:formatNumber type="number" value="${viewCount}" /> views
					</div>
				</div>
				<!-- END HOME CONTENT -->
			</div>
			
			<%@ include file="../footer.jsp" %>
		</div>
	</body>
	
	<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
	<script id="MathJax-script" async src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js"></script>
	<script type="text/javascript" color="0,0,0" opacity='0.3' zIndex="-2" count="60" src="js/canvas-nest.js"></script>
	<script src="js/main.js"></script>
	<script>
		var PAGE_URL = window.location.href;
		var PAGE_IDENTIFIER = window.location.pathname;
		
		var disqus_config = function () {
			this.page.url = PAGE_URL;
			this.page.identifier = PAGE_IDENTIFIER; 
		};
		
    	(function() {
			var d = document, s = d.createElement('script');
			s.src = 'https://jaredible.disqus.com/embed.js';
			s.setAttribute('data-timestamp', +new Date());
			(d.head || d.body).appendChild(s);
		})();
	</script>
	<noscript>
		Please enable JavaScript to view the <a href="https://disqus.com/?ref_noscript" rel="nofollow">comments powered by Disqus.</a>
	</noscript>
</html>