<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${sessionScope.userId != null}">
		<a href="profile">Profile</a>
		<a href="addProblem">Add problem</a>
		<a href="addCategory">Add category</a>
		<a href="addTag">Add tag</a>
	</c:when>
	<c:otherwise>
		<a href="login">Login</a>
		<a href="register">Register</a>
	</c:otherwise>
</c:choose>