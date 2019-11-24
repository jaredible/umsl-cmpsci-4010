<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="ui vertical inverted sidebar menu">
	<div class="ui dropdown item">
		<img class="ui mini circular image" src="https://fomantic-ui.com//images/avatar/small/matt.jpg">
	</div>
	<a class="active item" href="problems">Problems</a>
	<a class="item" href="categories">Categories</a>
	<a class="item" href="tags">Tags</a>
	<c:choose>
		<c:when test="${sessionScope.user != null}">
			<a class="item" href="profile">Profile</a>
			<a class="item" href="account">Account</a>
		</c:when>
		<c:otherwise>
			<a class="item" href="login">Log in</a>
			<a class="item" href="register">Register</a>
		</c:otherwise>
	</c:choose>
</div>