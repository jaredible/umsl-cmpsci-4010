<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login</title>
	</head>
	<body>
		<form action="login" method="post">
			<div>
				<label for="identifier">Identifier: </label>
				<input type="text" name="username" placeholder="Username or Email">
			</div>
			<div>
				<label for="password">Password: </label>
				<input type="password" name="password">
			</div>
			<div>
				<input type="submit" value="Login">
			</div>
		</form>
	</body>
</html>