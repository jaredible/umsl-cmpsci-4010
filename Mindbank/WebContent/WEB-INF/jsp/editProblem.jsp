<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="pageTitle" scope="request">
	<c:out value="Edit Problem - ${title}" default="Edit Problem" />
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
					<h2 class="ui dividing header"><a href="<c:url value='/problem?id=${param.id}' />"><c:out value="${title}" /></a></h2>
					<h4 class="ui header">
						Publisher: 
						<a href="#">
							<c:out value="${createdByUserName}" />
						</a>
					</h4>
					<h4 class="ui header">
						Published: 
						<a href="#">
							<fmt:formatDate type="date" dateStyle="long" value="${createdTime}" /> 
							<fmt:formatDate type="time" timeStyle="short" value="${createdTime}" />
						</a>
					</h4>
					<div class="ui basic segment">
						<form class="ui large form" action="<c:url value='/problem/edit?id=${param.id}' />" method="post">
							<div style="margin: 3vh 0;">
								<div class="ui two fields">
									<div class="ui field">
										<div class="ui fluid clearable multiple search normal selection dropdown">
											<input type="hidden" name="cid" value="${cid}">
											<i class="dropdown icon"></i>
											<div class="default text">Categories</div>
											<div class="menu">
												<c:forEach var="category" items="${categories}">
													<div class="item" data-value="${category.id}"><c:out value="${category.name}" /></div>
												</c:forEach>
											</div>
										</div>
									</div>
									<div class="ui field">
										<div class="ui fluid clearable multiple search normal selection dropdown">
											<input type="hidden" name="tid" value="${tid}">
											<i class="dropdown icon"></i>
											<div class="default text">Tags</div>
											<div class="menu">
												<c:forEach var="tag" items="${tags}">
													<div class="item" data-value="${tag.id}"><c:out value="${tag.name}" /></div>
												</c:forEach>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div style="margin: 3vh 0;">
								<div class="ui required field ${not empty errors.content ? 'error' : ''}">
									<textarea name="content" placeholder="What's the problem?"><c:out value="${content}" /></textarea>
									<c:if test="${not empty errors.content}">
										<div class="ui pointing above red basic label"><c:out value="${errors.content}" /></div>
									</c:if>
								</div>
							</div>
							<button class="ui large primary fluid button" type="submit">Update problem</button>
						</form>
					</div>
				</div>
			</main>
		</div>
		
		<jsp:include page="partial/scripts.jsp" />
	</body>
</html>