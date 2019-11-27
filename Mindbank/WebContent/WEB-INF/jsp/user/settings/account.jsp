<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Account" scope="request" />

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="../../partial/head.jsp" %>
	</head>
<body>
	<%@ include file="../../partial/sidemenu.jsp" %>
	
	<div class="pusher">
		<div class="main">
			<div class="ui container">
				<%@ include file="../../partial/header.jsp" %>
				
				<form class="ui form" action="account" method="post">
					<div class="ui field ${(errors.name != null) ? 'error' : ''}">
						<label>Name</label>
						<input type="text" name="name" placeholder="Name" value="${name}">
						<c:if test="${errors.name != null}">
							<div class="ui pointing above red basic label"><c:out value="${errors.name}" /></div>
						</c:if>
					</div>
					<div class="ui field ${(errors.bio != null) ? 'error' : ''}">
						<label>Bio</label>
						<input type="text" name="bio" placeholder="Bio" value="${bio}">
						<c:if test="${errors.bio != null}">
							<div class="ui pointing above red basic label"><c:out value="${errors.bio}" /></div>
						</c:if>
					</div>
					<button class="ui button" type="submit">Update account</button>
				</form>
			</div>
			
			<%@ include file="../../partial/footer.jsp" %>
		</div>
	</div>
	
	<jsp:include page="../../partial/scripts.jsp" />
</body>
</html>