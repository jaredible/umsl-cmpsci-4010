<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Register</title>
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
				<input type="text" name="age" value="-1">
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
				<label for="password">Password: </label>
				<input type="password" name="password">
				<label for="confirm">Confirm: </label>
				<input type="password" name="confirm">
			</div>
			<div>
				<input type="submit" value="Register">
			</div>
		</form>
	</body>
</html>