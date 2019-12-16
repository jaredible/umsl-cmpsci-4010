<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="pageTitle" value="New Category" scope="request" />

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
							<h2>Add a new category</h2>
							<form class="ui large form" action="<c:url value='/category/new' />" method="post">
								<div class="ui segment">
									<div style="margin: 3vh 0;">
										<div class="ui required field ${not empty errors.name ? 'error' : ''}">
											<div class="ui fluid input">
												<input type="text" name="name" placeholder="Name" value="<c:out value="${name}" />">
											</div>
											<c:if test="${not empty errors.name}">
												<div class="ui pointing above red basic label"><c:out value="${errors.name}" /></div>
											</c:if>
										</div>
									</div>
									<button class="ui large primary fluid button" type="submit">Add category</button>
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