<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Input Page</title>
	<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css" rel="stylesheet">
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link href="css/animate.css" rel="stylesheet">
	<link href="css/styles.css" rel="stylesheet">
</head>
<body class="text-center">
	<a href="https://github.com/jaredible/umsl-cmpsci-4010/tree/master/Project1" class="position-fixed" target="_blank"><img width="149" height="149" src="https://github.blog/wp-content/uploads/2008/12/forkme_left_white_ffffff.png?resize=149%2C149" alt="Fork me on GitHub" data-recalc-dims="1"></a>
	<!--<div>
		<input type="checkbox" id="chk">
		<label for="chk" class="show-menu-btn position-fixed" style="right: 0; padding: 0 20px;">
			<i class="fas fa-ellipsis-h"></i>
		</label>
		
		<div class="home-menu">
		<label for="chk" class="hide-menu-btn">
			<i class="fas fa-times"></i>
        </label>
			<div class="card bg-sohbet border-0 m-0 p-0" style="height: 100vh;">
				<div id="sohbet" class="card border-0 m-0 p-0 position-relative bg-transparent" style="overflow-y: auto; height: 100vh;">
					<div class="balon1 p-2 m-0 position-relative" data-is="You - 3:20 pm">
						<a class="float-right"> Hey there! What's up? </a>
					</div>
					<div class="balon2 p-2 m-0 position-relative" data-is="Yusuf - 3:22 pm">
						<a class="float-left sohbet2"> Checking out iOS7 you know.. </a>
					</div>
				</div>
			</div>
			<div class="w-100 card-footer p-0 bg-light border border-bottom-0 border-left-0 border-right-0">
				<form class="m-0 p-0" action="" method="POST" autocomplete="off">
					<div class="row m-0 p-0">
						<div class="col-9 m-0 p-1">
							<input id="text" class="mw-100 border rounded form-control" type="text" name="text" title="Type a message..." placeholder="Type a message..." required>
						</div>
						<div class="col-3 m-0 p-1">
							<button class="btn btn-outline-secondary rounded border w-100" title="Gönder!" style="padding-right: 16px;"><i class="fa fa-paper-plane" aria-hidden="true"></i></button>		
						</div>
					</div>
				</form>	
			</div>
		</div>
	</div>-->
	<%
		String os = (String) request.getAttribute("os");
		String[] cities = (String[]) request.getAttribute("cities");
		String[] errors = (String[]) request.getAttribute("errors");
		boolean cityValid = true;
		boolean numberValid = true;
		int cityIndex = -1;

		try {
			cityIndex = Integer.parseInt((String) request.getAttribute("cityIndex"));
		} catch (NumberFormatException e) {
		}

		if (errors != null && errors.length > 1) {
			if (errors[0] != null) {
				cityValid = errors[0].length() == 0;
			}
			if (errors[1] != null) {
				numberValid = errors[1].length() == 0;
			}
		}
	%>
	<div class="cover-container d-flex h-100 p-3 mx-auto flex-column">
		<header class="masthead mb-auto">
			<div class="inner">
				<h3 class="masthead-brand animated fadeIn delay-1s fast"><a href="<%= request.getContextPath() %>">Computing Service</a></h3>
			</div>
		</header>
		<main role="main" class="inner cover">
		<div class="cover-heading">
			<%
				if (os == "Windows") {
			%>
			<img class="cover-logo animated fadeInDown" src="https://s3.amazonaws.com/hs-wordpress/wp-content/uploads/2017/12/12151712/windows-logo-HS1.png">
			<%
				} else if (os == "Mac") {
			%>
			<img class="cover-logo animated fadeInDown" src="https://www.amacusg.gatech.edu/wiki/images/e/e5/Apple_Logo.png">
			<%
				} else {
			%>
			<img class="cover-logo animated fadeInUpBig" src="https://i.imgflip.com/28j2cg.jpg">
			<%
				}
			%>
		</div>
		<form action="result" method="post">
			<div class="form-row align-items-center justify-content-center">
				<div class="col-auto w-75 m-3">
					<select class="form-control animated fadeInLeft <% if (!cityValid) { %> is-invalid <% } %>" name="city">
						<option value="-1" <% if (cityIndex == -1) { %> selected <% } %>> --- Select a City --- </option>
						<%
							for (int i = 0; i < cities.length; i++) {
						%>
							<option value="<%= i %>" <% if (i == cityIndex) { %> selected <% } %>><%= cities[i].trim() %></option>
						<%
							}
						%>
					</select>
					<div class="invalid-tooltip animated delay-1s slow">${ errors[0].trim() }</div>
				</div>
				<div class="col-auto w-75 m-3">
					<input type="text" class="form-control animated fadeInRight <% if (!numberValid) { %> is-invalid <% } %>" name="number" value="${ number }" placeholder="A very big integer">
					<div class="invalid-tooltip animated delay-1s slow">${ errors[1].trim() }</div>
				</div>
				<div class="col-auto w-75 m-3">
					<input type="submit" class="btn btn-lg btn-secondary animated fadeInUp" value="Calculate" >
				</div>
			</div>
		</form>
		</main>
		<footer class="mastfoot mt-auto animated fadeIn fast">
			<div class="inner">
				<p>
					Made with <i class="fas fa-heart animated fadeIn slow"></i> by <a href="https://www.jaredible.net" target="_blank">Jaredible</a>
				</p>
			</div>
		</footer>
	</div>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>
	<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/userinfo/1.1.0/userinfo.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/socket.io/2.3.0/socket.io.js"></script>
	<script src="js/main.js"></script>
	<script src="js/input.js"></script>
</body>
</html>