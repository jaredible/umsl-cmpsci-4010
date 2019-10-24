<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Register</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.7.6/css/mdb.min.css">
		<link rel="stylesheet" href="css/styles.css">
	</head>
	<body>
		<div class="container">
			<form class="d-fixed text-center p-5" action="register" method="post">
				<p class="h4 mb-3">Register</p>

				<div class="form-row mb-3">
					<div class="col">
						<input type="text" name="firstname" class="form-control" placeholder="First name">
					</div>
					<div class="col">
						<input type="text" name="lastname" class="form-control" placeholder="Last name">
					</div>
				</div>
								
				<input type="email" name="email" class="form-control mb-3" placeholder="E-mail">
				
				<input type="password" name="password" class="form-control mb-2" placeholder="Password">
				<input type="password" name="confirm" class="form-control" placeholder="Confirm password">
			    <small class="form-text text-muted mb-4">
			        At least 8 characters and 1 digit
			    </small>
			    
			    <button class="btn btn-dark my-3 btn-block" type="submit">Register</button>
			    
			    <p>Already a member?
        			<a href="login">Login</a>
    			</p>
			</form>
		</div>
		<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.7.6/js/mdb.min.js"></script>
		<script type="text/javascript" color="0,0,0" opacity='0.7' zIndex="-2" count="99" src="js/canvas-nest.js"></script>
		<script src="js/main.js"></script>
	</body>
</html>