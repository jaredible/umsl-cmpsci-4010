<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Register</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.7.6/css/mdb.min.css" />
	</head>
	<body>
		<form action="register" method="post">
			<div>
				<label for="firstname">Firstname: </label>
				<input type="text" name="firstname">
				<label for="lastname">Lastname: </label>
				<input type="text" name="lastname">
			</div>
			<div>
				<label for="age">Age: </label>
				<input type="text" name="age">
			</div>
			<div>
				<label for="username">Username: </label>
				<input type="text" name="username">
			</div>
			<div>
				<label for="email">Email: </label>
				<input type="text" name="email">
			</div>
			<div>
				<label for="phone">Phone: </label>
				<input type="text" name="phone">
			</div>
			<div>
				<label for="password">Password: </label>
				<input type="password" name="password">
				<label for="confirm">Confirm: </label>
				<input type="password" name="confirm">
			</div>
			<div>
				<input type="submit" value="Register">
			</div>
			<div>
				<p>Already have an account? <a href="login">Login here!</a></p>
			</div>
		</form>
		<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.7.6/js/mdb.min.js"></script>
	</body>
</html>