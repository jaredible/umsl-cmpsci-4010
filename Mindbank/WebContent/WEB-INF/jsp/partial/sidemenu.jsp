<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="ui large top fixed hidden menu">
	<div class="ui container">
		<a class="borderless header item" href="${pageContext.request.contextPath}">
			<img class="ui mini rounded logo image" src="${pageContext.request.contextPath}/img/brand.png">
			Mindbank
		</a>
		<c:choose>
			<c:when test="${sessionScope.user != null}">
				<div class="right menu">
					<div class="ui borderless dropdown item">
						<i class="plus icon"></i>
						<i class="dropdown icon"></i>
						<div class="menu">
							<a class="item" href="${pageContext.request.contextPath}/problem/new">New problem</a>
							<a class="item" href="${pageContext.request.contextPath}/category/new">New category</a>
							<a class="item" href="${pageContext.request.contextPath}/tag/new">New tag</a>
						</div>
					</div>
					<div class="ui borderless dropdown item">
						<img class="ui mini rounded image" src="https://fomantic-ui.com/images/avatar/small/matt.jpg">
						<i class="dropdown icon"></i>
						<div class="menu">
							<a class="item" href="${pageContext.request.contextPath}">Home</a>
							<a class="item" href="${pageContext.request.contextPath}/settings/profile">Profile</a>
							<a class="item" href="${pageContext.request.contextPath}/settings/account">Account</a>
							<a class="item" href="${pageContext.request.contextPath}/settings/security">Security</a>
							<a class="item" href="${pageContext.request.contextPath}/logout">Log out</a>
						</div>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="right item">
					<a class="ui button" href="${pageContext.request.contextPath}/login">Log in</a>
					<a class="ui button" href="${pageContext.request.contextPath}/register">Register</a>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>

<div class="ui vertical inverted sidebar menu">
	<a class="header item" href="${pageContext.request.contextPath}">
		<img class="ui mini rounded logo image" src="${pageContext.request.contextPath}/img/brand.png">
		Mindbank
	</a>
	<c:choose>
		<c:when test="${sessionScope.user != null}">
			<div class="ui dropdown item">
				<img class="ui mini rounded image" src="https://fomantic-ui.com/images/avatar/small/matt.jpg">
			</div>
			<a class="item" href="${pageContext.request.contextPath}">Home</a>
			<a class="item" href="${pageContext.request.contextPath}/settings/profile">Profile</a>
			<a class="item" href="${pageContext.request.contextPath}/settings/account">Account</a>
			<a class="item" href="${pageContext.request.contextPath}/settings/security">Security</a>
			<a class="item" href="${pageContext.request.contextPath}/logout">Log out</a>
		</c:when>
		<c:otherwise>
			<a class="item" href="${pageContext.request.contextPath}/login">Log in</a>
			<a class="item" href="${pageContext.request.contextPath}/register">Register</a>
		</c:otherwise>
	</c:choose>
</div>