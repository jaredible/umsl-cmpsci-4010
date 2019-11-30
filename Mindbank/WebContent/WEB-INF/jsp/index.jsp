<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Home" scope="request" />

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="partial/head.jsp" %>
	</head>
	<body>
		<%@ include file="partial/sidemenu.jsp" %>
		
		<div class="pusher">
			<%@ include file="partial/header.jsp" %>
			
			<main class="mb-3">
				<div class="ui vertical segment">
					<div class="ui container">
						<div class="ui secondary raised top attached segment">
							<h3 class="ui header">Problem search</h3>
						</div>
						<form class="ui form raised bottom attached segment" action="${pageContext.request.contextPath}/" method="post">
							<div class="field">
								<input type="text" name="title" value="${title}" placeholder="Title">
							</div>
							<div class="field">
								<div class="ui fluid multiple search normal selection dropdown">
									<input type="hidden" name="categoryIds" value="${categoryIds}">
									<i class="dropdown icon"></i>
									<div class="default text">Categories</div>
									<div class="menu">
										<c:forEach var="category" items="${categories}">
											<div class="item" data-value="${category.id}"><c:out value="${category.name}" /></div>
										</c:forEach>
									</div>
								</div>
							</div>
							<div class="field">
								<div class="ui fluid multiple search normal selection dropdown">
									<input type="hidden" name="tagIds" value="${tagIds}">
									<i class="dropdown icon"></i>
									<div class="default text">Tags</div>
									<div class="menu">
										<c:forEach var="tag" items="${tags}">
											<div class="item" data-value="${tag.id}"><c:out value="${tag.name}" /></div>
										</c:forEach>
									</div>
								</div>
							</div>
							<div class="field">
								<input type="text" name="content" value="${content}" placeholder="Content">
							</div>
							<div class="two fields">
								<div class="field">
									<div id="datetimeRangeStart" class="ui calendar">
										<div class="ui input left icon">
											<i class="calendar icon"></i>
											<input type="text" name="datetimeStart" value="${datetimeStart}" placeholder="Minimum time created">
										</div>
									</div>
								</div>
								<div class="field">
									<div id="datetimeRangeEnd" class="ui calendar">
										<div class="ui input left icon">
											<i class="calendar icon"></i>
											<input type="text" name="datetimeEnd" value="${datetimeEnd}" placeholder="Maximum time created">
										</div>
									</div>
								</div>
							</div>
							<div class="field">
								<div class="ui fluid multiple search normal selection dropdown">
									<input type="hidden" name="userIds" value="${userIds}">
									<i class="dropdown icon"></i>
									<div class="default text">Users</div>
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
								<button class="ui primary submit button" type="submit">Search problems</button>
							</div>
						</form>
						
						<c:forEach var="problem" items="${problems}">
							<div class="ui piled raised segment">
								<h3 class="ui header">
									<a href="${pageContext.request.contextPath}/problem?id=${problem.id}"><c:out value="${problem.title}" /></a>
								</h3>
								<p><c:out value="${problem.content}" /></p>
							</div>
						</c:forEach>
					</div>
				</div>
				
				<%@ include file="partial/footer.jsp" %>
			</main>
		</div>
		
		<jsp:include page="partial/scripts.jsp" />
	</body>
</html>