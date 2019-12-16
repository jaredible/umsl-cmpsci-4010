<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="ui large top fixed hidden menu">
	<div class="ui container">
		<a class="borderless header item" href="<c:url value='/' />">
			<img class="ui mini rounded logo image" src="<c:url value='/img/brand.png' />">Mindbank
		</a>
		<c:choose>
			<c:when test="${not empty sessionScope.userName}">
				<div class="right menu">
					<div class="ui borderless dropdown item">
						<i class="plus icon"></i>
						<i class="dropdown icon"></i>
						<div class="menu">
							<a class="item" href="<c:url value='/problem/new' />">New problem</a>
							<a class="item" href="<c:url value='/category/new' />">New category</a>
							<a class="item" href="<c:url value='/tag/new' />">New tag</a>
						</div>
					</div>
					<div class="ui borderless dropdown item">
						<img class="ui mini rounded image" src="<c:url value='/img/profile/${fn:toLowerCase(sessionScope.userName)}.png' />">
						<i class="dropdown icon"></i>
						<div class="menu">
							<div class="item">Logged in as <strong>${sessionScope.userName}</strong></div>
							<a class="item" href="<c:url value='/profile' />">Your profile</a>
							<a class="item" href="<c:url value='/account' />">Your account</a>
							<a class="item" href="<c:url value='/logout' />">Log out</a>
						</div>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="right item">
					<a class="ui button" href="<c:url value='/login' />">Log in</a>
					<a class="ui button" href="<c:url value='/register' />">Register</a>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>

<div class="ui vertical inverted sidebar menu">
	<a class="header item" href="<c:url value='/' />">
		<img class="ui mini rounded logo image" src="<c:url value='/img/brand.png' />">Mindbank
	</a>
	<c:choose>
		<c:when test="${not empty sessionScope.userName}">
			<div class="header item">
				<img class="ui mini rounded logo image" src="<c:url value='/img/profile/${fn:toLowerCase(sessionScope.userName)}.png' />">${sessionScope.userName}
			</div>
			<div class="item">
				<a class="item" href="<c:url value='/problem/new' />">New problem</a>
				<a class="item" href="<c:url value='/category/new' />">New category</a>
				<a class="item" href="<c:url value='/tag/new' />">New tag</a>
			</div>
			<div class="item">
				<a class="item" href="<c:url value='/profile' />">Your profile</a>
				<a class="item" href="<c:url value='/account' />">Your account</a>
				<a class="item" href="<c:url value='/logout' />">Log out</a>
			</div>
		</c:when>
		<c:otherwise>
			<a class="ui button" href="<c:url value='/login' />">Log in</a>
			<a class="ui button" href="<c:url value='/register' />">Register</a>
		</c:otherwise>
	</c:choose>
</div>