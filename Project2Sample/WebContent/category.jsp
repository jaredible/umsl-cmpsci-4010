<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<title>Mathbank</title>
		<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon.ico">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
	</head>
	<body>
		<div class="wrapper">
			<div class="main">
				<div class="container">
					<!-- BEGIN PILL TABS -->
					<ul id="pills-tab" class="nav nav-pills justify-content-center my-3" role="tablist">
						<li class="nav-item">
							<a id="pills-home-tab" class="nav-link active" href="#pills-home" data-toggle="pill" role="tab" aria-contols="pills-home" aria-selected="true">Home</a>
						</li>
						<li class="nav-item">
							<a id="pills-problems-tab" class="nav-link" href="#pills-problems" data-toggle="pill" role="tab" aria-contols="pills-problems" aria-selected="true">Problems</a>
						</li>
						<li class="nav-item">
							<a id="pills-categories-tab" class="nav-link" href="#pills-categories" data-toggle="pill" role="tab" aria-contols="pills-categories" aria-selected="true">Categories</a>
						</li>
					</ul>
					<!-- END PILL TABS -->
					
					<!-- BEGIN PILL CONTENT -->
					<div id="pills-content" class="tab-content">
						<!-- BEGIN HOME CONTENT -->
						<div id="pills-home" class="tab-pane fade show active" role="tabpanel" aria-labelledby="pills-home-tab">
							<div class="container">
								<div class="row">
									<div class="col-6">
										Test1
									</div>
									<div class="col-6">
										Test2
									</div>
								</div>
							</div>
						</div>
						<!-- END HOME CONTENT -->
						
						<!-- BEGIN PROBLEM CONTENT -->
						<div id="pills-problems" class="tab-pane fade" role="tabpanel" aria-labelledby="pills-problems-tab">
							<div class="container">
								<!-- BEGIN VIEW-PROBLEM MODAL -->
								<div id="modal-view" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modalView" aria-hidden="true">
									<div class="modal-dialog" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<h5 id="modalView" class="modal-title">Modal title</h5>
												<button class="close" type="button" data-dismiss="modal" aria-label="Close">
													<span aria-hidden="true"><i class="fas fa-times"></i></span>
												</button>
											</div>
											<div class="modal-body">
												Testing
												<div id="disqus_thread"></div>
											</div>
											<div class="modal-footer">
												<button class="btn btn-secondary" type="button" data-dismiss="modal">Close</button>
											</div>
										</div>
									</div>
								</div>
								<!-- END VIEW-PROBLEM MODEL -->
								
								<!-- BEGIN ADD-PROBLEM MODAL -->
								<div id="modal-addProblem" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modalAddProblem" aria-hidden="true">
									<div class="modal-dialog modal-dialog-centered" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<h5 id="modalAddProblem" class="modal-title">New problem</h5>
												<button class="close" type="button" data-dismiss="modal" aria-label="Close">
													<span aria-hidden="true"><i class="fas fa-times"></i></span>
												</button>
											</div>
											<div class="modal-body">
												<form id="problemForm" action="problem" method="post">
													<div class="form-row">
														<div class="form-group col-12">
															<input class="form-control" placeholder="Title">
														</div>
														<div class="form-group col-12">
															<select id="inputState" class="form-control">
																<option selected>Select a category</option>
																<option>...</option>
															</select>
														</div>
														<div class="form-group col-12">
															<textarea class="form-control" rows="10" placeholder="Type a problem"></textarea>
														</div>
													</div>
												</form>
											</div>
											<div class="modal-footer">
												<button class="btn btn-secondary" type="button" data-dismiss="modal">Close</button>
												<button id="problemSubmit" class="btn btn-success" type="button">Submit</button>
											</div>
										</div>
									</div>
								</div>
								<!-- END ADD-PROBLEM MODAL -->
								
								<div class="row justify-content-between align-items-center">
									<div class="col-6">
										<form action="" method="post">
											<div class="form-row">
												<div class="form-group col-6">
													<select id="inputState" class="form-control">
														<option selected>Filter by a category</option>
														<option>...</option>
													</select>
												</div>
											</div>
										</form>
									</div>
									<div>
										<button class="btn btn-secondary" type="button" data-toggle="modal" data-target="#modal-addProblem">Add problem</button>
									</div>
								</div>
								
								<!-- BEGIN PROBLEM TABLE -->
								<table class="table">
									<thead>
										<tr>
											<th scope="col">PID</th>
											<th scope="col">CID</th>
											<th scope="col">Title</th>
											<th scope="col"></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<th scope="row">1</th>
											<td>2</td>
											<td>Testing</td>
											<td align="right"><button class="btn btn-sm btn-secondary" type="button" data-toggle="modal" data-target="#modal-view">View</button></td>
										</tr>
										<tr>
											<th scope="row">1</th>
											<td>2</td>
											<td>Testing Testing</td>
											<td align="right"><button class="btn btn-sm btn-secondary">View</button></td>
										</tr>
										<tr>
											<th scope="row">1</th>
											<td>2</td>
											<td>Testing Testing Testing</td>
											<td align="right"><button class="btn btn-sm btn-secondary">View</button></td>
										</tr>
									</tbody>
								</table>
								<!-- END PROBLEM TABLE -->
							</div>
						</div>
						<!-- END PROBLEM CONTENT -->
						
						<!-- BEGIN CATEGORY CONTENT -->
						<div id="pills-categories" class="tab-pane fade" role="tabpanel" aria-labelledby="pills-categories-tab">
						<!-- END CATEGORY CONTENT -->
						</div>
					</div>
					<!-- END PILL CONTENT -->
				</div>
			</div>
		</div>
	</body>
	
	<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
	<script id="MathJax-script" async src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js"></script>
	<script src="${pageContext.request.contextPath}/js/main.js"></script>
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