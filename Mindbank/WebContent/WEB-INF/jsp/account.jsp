<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" value="Account" scope="request" />

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
							<h2>Your account</h2>
							<form class="ui large form" action="<c:url value='/account' />" method="post">
								<div class="ui segment">
									<div style="margin: 3vh 0;">
										<div class="ui field">
											<div class="ui fluid input">
												<input class="disabled" type="text" name="email" placeholder="Email" value="<c:out value="${email}" />" disabled>
											</div>
										</div>
									</div>
									<div style="margin: 3vh 0;">
										<div class="ui field">
											<div class="ui fluid input">
												<input class="disabled" type="text" name="userName" placeholder="Username" value="<c:out value="${userName}" />" disabled>
											</div>
										</div>
									</div>
									<div style="margin: 3vh 0;">
										<span>Last logged in on <fmt:formatDate type="date" dateStyle="long" value="${lastLoginTime}" /> at <fmt:formatDate type="time" timeStyle="short" value="${lastLoginTime}" /></span>
									</div>
									<div style="margin: 3vh 0;">
										<span>Registered on <fmt:formatDate type="date" dateStyle="long" value="${registeredTime}" /> at <fmt:formatDate type="time" timeStyle="short" value="${registeredTime}" /></span>
									</div>
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