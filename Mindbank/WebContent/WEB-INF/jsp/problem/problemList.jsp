<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Problems" scope="request" />

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="../partial/head.jsp" %>
	</head>
	<body>
		<%@ include file="../partial/sidemenu.jsp" %>
		
		<div class="pusher">
			<main>
				<%@ include file="../partial/header.jsp" %>
				
				<div class="ui vertical segment">
					<div class="ui container">
						<div class="ui secondary raised top attached segment">
							<h3 class="ui header">Problem search</h3>
						</div>
						<form class="ui form raised bottom attached segment" action="problems" method="post">
							<div class="field">
								<div class="ui sub header">Title</div>
								<input type="text" name="title" value="${title}">
							</div>
							<div class="field">
								<div class="ui sub header">Category</div>
								<div class="ui fluid multiple search normal selection dropdown">
									<input type="hidden" name="categoryIds">
									<i class="dropdown icon"></i>
									<div class="default text">Select categories</div>
									<div class="menu">
										<c:forEach var="category" items="${categories}">
											<div class="item" data-value="${category.id}"><c:out value="${category.name}" /></div>
										</c:forEach>
									</div>
								</div>
							</div>
							<div id="tag" class="field">
								<div class="ui sub header">Tag</div>
								<div class="ui fluid multiple search normal selection dropdown">
									<input type="hidden" name="tagIds">
									<i class="dropdown icon"></i>
									<div class="default text">Select tags</div>
									<div class="menu">
										<c:forEach var="tag" items="${tags}">
											<div class="item" data-value="${tag.id}"><c:out value="${tag.name}" /></div>
										</c:forEach>
									</div>
								</div>
							</div>
							<div class="field">
								<div class="ui sub header">Content</div>
								<input type="text" name="content" value="${content}">
							</div>
							<div class="two fields">
								<div class="field">
									<div class="ui sub header">Date created</div>
									<div id="rangeStart" class="ui calendar">
										<div class="ui input left icon">
											<i class="calendar icon"></i>
											<input type="text" name="dateStart" placeholder="Start date">
										</div>
									</div>
								</div>
								<div class="field">
									<div class="ui sub header">Date created</div>
									<div id="rangeEnd" class="ui calendar">
										<div class="ui input left icon">
											<i class="calendar icon"></i>
											<input type="text" name="dateEnd" placeholder="End date">
										</div>
									</div>
								</div>
							</div>
							<div class="field">
								<div class="ui sub header">User</div>
								<div class="ui fluid multiple search normal selection dropdown">
									<input type="hidden" name="userIds">
									<i class="dropdown icon"></i>
									<div class="default text">Select users</div>
									<div class="menu">
										<c:forEach var="user" items="${users}">
											<div class="item" data-value="${user.id}" data-text="${user.userName}">
												<img class="ui right spaced avatar centered image" src="https://fomantic-ui.com/images/avatar/small/matt.jpg">
												<c:out value="${user.userName}" />
											</div>
										</c:forEach>
									</div>
								</div>
							</div>
							<div class="ui center aligned basic segment">
								<button class="ui primary submit button" type="submit">Search</button>
							</div>
						</form>
						
						<c:forEach var="problem" items="${problems}">
							<div class="ui piled raised segment">
								<h3 class="ui header">
									<a href="problem?id=${problem.id}"><c:out value="${problem.title}" /></a>
								</h3>
								<p><c:out value="${problem.content}" /></p>
							</div>
						</c:forEach>
					</div>
				</div>
				
				<%@ include file="../partial/footer.jsp" %>
			</main>
		</div>
		
		<jsp:include page="../partial/scripts.jsp" />
	</body>
</html>