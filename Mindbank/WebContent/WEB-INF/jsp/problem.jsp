<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="pageTitle" scope="request">
	<c:out value="${title}" default="Problem" />
</c:set>

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="partial/head.jsp" %>
	</head>
	<body>
		<%@ include file="partial/sidemenu.jsp" %>
		
		<div class="pusher">
			<%@ include file="partial/header.jsp" %>
			
			<main>
				<div class="ui container" style="margin-top: 3vh; margin-bottom: 3vh;">
					<h2 class="ui dividing header"><c:out value="${problemDetail.problem.title}" /></h2>
					<h4 class="ui header">
						Publisher: 
						<a href="#">
							<small>
								<c:out value="${problemDetail.createdByUser.userName}" />
							</small>
						</a>
					</h4>
					<h4 class="ui header">
						Published: 
						<a href="#">
							<small>
								<fmt:formatDate type="date" dateStyle="long" value="${problemDetail.problem.createdTime}" /> 
								<fmt:formatDate type="time" timeStyle="short" value="${problemDetail.problem.createdTime}" />
							</small>
						</a>
					</h4>
					<c:if test="${not empty problemDetail.categories}">
						<h4>
							Categories: 
							<small>
								<c:forEach var="category" items="${problemDetail.categories}" varStatus="status">
									<a href="#"><c:out value="${category.name}" /></a>
									<c:if test="${not status.last}">, </c:if>
								</c:forEach>
							</small>
						</h4>
					</c:if>
					<c:if test="${not empty problemDetail.tags}">
						<h4>
							Tags: 
							<small>
								<c:forEach var="tag" items="${problemDetail.tags}" varStatus="status">
									<a href="#"><c:out value="${tag.name}" /></a>
									<c:if test="${not status.last}">, </c:if>
								</c:forEach>
							</small>
						</h4>
					</c:if>
					<c:if test="${not empty sessionScope.userName and sessionScope.userName eq problemDetail.createdByUser.userName}">
						<a class="ui button" href="<c:url value='/problem/edit?id=${problemDetail.problem.id}' />">Edit</a>
					</c:if>
					<div class="ui basic segment">
						<p><c:out value="${problemDetail.problem.content}" /></p>
					</div>
				</div>
			</main>
		</div>
		
		<jsp:include page="partial/scripts.jsp" />
	</body>
</html>