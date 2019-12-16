<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="pageTitle" value="New Problem" scope="request" />

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
					<div class="ui center aligned grid" style="height: 100%;">
						<div class="column">
							<h2>Add a new problem</h2>
							<form class="ui large form" action="<c:url value='/problem/new' />" method="post">
								<div class="ui segment">
									<div style="margin: 3vh 0;">
										<div class="ui required field ${not empty errors.title ? 'error' : ''}">
											<div class="ui fluid input">
												<input type="text" name="title" placeholder="Title" value="<c:out value="${title}" />">
											</div>
											<c:if test="${not empty errors.title}">
												<div class="ui pointing above red basic label"><c:out value="${errors.title}" /></div>
											</c:if>
										</div>
									</div>
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
											<div class="ui fluid input">
												<textarea name="content" placeholder="What's the problem?"><c:out value="${content}" /></textarea>
											</div>
											<c:if test="${not empty errors.content}">
												<div class="ui pointing above red basic label"><c:out value="${errors.content}" /></div>
											</c:if>
										</div>
									</div>
									<button class="ui large primary fluid button" type="submit">Add problem</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</main>
		</div>
		
		<jsp:include page="partial/scripts.jsp" />
	</body>
</html>