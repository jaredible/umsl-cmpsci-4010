<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
			
			<main>
				<div class="ui container" style="margin-top: 3vh; margin-bottom: 3vh;">
					<div class="ui center aligned grid" style="height: 100%;">
						<div class="column" style="max-width: 450px;">
							<h2>Search for problems</h2>
							<form class="ui large form" action="<c:url value='/' />" method="get">
								<div class="ui segment">
									<div style="margin: 3vh 0;">
										<div class="ui field">
											<div class="ui fluid clearable multiple search normal selection filter dropdown">
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
									</div>
									<div style="margin: 3vh 0;">
										<div class="ui field">
											<div class="ui fluid clearable multiple search normal selection filter dropdown">
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
									<div style="margin: 3vh 0;">
										<div class="ui field">
											<div class="ui fluid clearable multiple search normal selection filter dropdown">
												<input type="hidden" name="uid" value="${uid}">
												<i class="dropdown icon"></i>
												<div class="default text">Users</div>
												<div class="menu">
													<c:forEach var="user" items="${users}">
														<div class="item" data-value="${user.id}" data-text="${user.userName}">
															<img class="ui right spaced avatar centered image" src="<c:url value='/img/profile/${fn:toLowerCase(user.userName)}.png' />">
															<c:out value="${user.userName}" />
														</div>
													</c:forEach>
												</div>
											</div>
										</div>
									</div>
									<button class="ui large fluid button" type="submit">Filter</button>
								</div>
							</form>
						</div>
					</div>
					<div class="ui grid" style="height: 100%;">
						<div class="column">
							<c:choose>
								<c:when test="${not empty problemDetails}">
									<div class="ui segment">
										<div class="ui divided items">
											<c:forEach var="problemDetail" items="${problemDetails}">
												<div class="item">
													<div class="content">
														<a class="header" href="<c:url value='/problem?id=${problemDetail.problem.id}' />"><c:out value="${problemDetail.problem.title}" /></a>
														<div class="meta">
															<c:if test="${not empty problemDetail.categories}">
																<div>
																	Categories: 
																	<small>
																		<c:forEach var="category" items="${problemDetail.categories}" varStatus="status">
																			<c:out value="${category.name}" />
																			<c:if test="${not status.last}">, </c:if>
																		</c:forEach>
																	</small>
																</div>
															</c:if>
															<c:if test="${not empty problemDetail.tags}">
																<div>
																	Tags: 
																	<small>
																		<c:forEach var="tag" items="${problemDetail.tags}" varStatus="status">
																			<c:out value="${tag.name}" />
																			<c:if test="${not status.last}">, </c:if>
																		</c:forEach>
																	</small>
																</div>
															</c:if>
														</div>
														<div class="description">
															<p><c:out value="${problemDetail.problem.content}" /></p>
														</div>
														<div class="extra">
															Created by <a class="m-0" href="#"><c:out value="${problemDetail.createdByUser.userName}" /></a> 
															on <fmt:formatDate type="date" dateStyle="long" value="${problemDetail.problem.createdTime}" /> 
															at <fmt:formatDate type="time" timeStyle="short" value="${problemDetail.problem.createdTime}" /> 
														</div>
													</div>
												</div>
											</c:forEach>
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<div class="ui center aligned segment">
										No problems matched
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</main>
		</div>
		
		<jsp:include page="partial/scripts.jsp" />
	</body>
</html>