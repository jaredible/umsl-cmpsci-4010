<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Login</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.7.6/css/mdb.min.css">
		<link rel="stylesheet" href="css/styles.css">
	</head>
	<body>
		<div class="container">
			<form class="text-center p-5" action="login" method="post">
				<p class="h4 mb-4">Log in</p>

				<input type="email" name="email" class="form-control mb-2" placeholder="E-mail">
				
				<input type="password" name="password" class="form-control mb-4" placeholder="Password">
								
				<div class="d-flex justify-content-between">
        			<div>
            			<div class="custom-control custom-checkbox">
                			<input type="checkbox" name="remember" class="custom-control-input">
                			<label class="custom-control-label" for="remember">Remember me</label>
            			</div>
        			</div>
       			 	<div>
            			<a href="">Forgot password?</a>
        			</div>
    			</div>
			    
			    <button class="btn btn-dark my-4 btn-block" type="submit">Log in</button>
			    
			    <p>Not a member?
        			<a href="register">Register</a>
    			</p>
			</form>
		</div>
		<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.7.6/js/mdb.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.7.6/js/mdb.min.js"></script>
		<script type="text/javascript" color="0,0,0" opacity='0.7' zIndex="-2" count="99" src="js/canvas-nest.js"></script>
		<script src="js/main.js"></script>
	</body>
</html>