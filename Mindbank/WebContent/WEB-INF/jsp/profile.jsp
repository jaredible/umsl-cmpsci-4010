<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="pageTitle" value="Profile" scope="request" />

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
							<h2>Your profile</h2>
							<form class="ui large form" action="<c:url value='/profile' />" method="post">
								<div class="ui segment">
									<div style="margin: 3vh 0;">
										<div class="ui field">
											<div class="ui basic segment">
												<img id="profilePicture" class="ui medium segment rounded image p-0 m-auto" src="<c:url value='/img/profile/${fn:toLowerCase(sessionScope.userName)}.png' />">
											</div>
											<input type="file" name="profileImage" accept="image/*" value="Edit">
										</div>
									</div>
									<div style="margin: 3vh 0;">
										<div class="ui field ${not empty errors.statusText ? 'error' : ''}">
											<div class="ui fluid labeled input">
												<div class="ui dropdown label">
													<input type="hidden" name="statusEmoji" value="${statusEmoji}">
													<div class="text">
														<i class="grin outline icon"></i>
													</div>
													<i class="dropdown icon"></i>
													<div class="menu">
														<div class="item" data-value="angry">
															<em class="tiny" data-emoji="angry"></em>
														</div>
														<div class="item" data-value="confused">
															<em class="tiny" data-emoji="confused"></em>
														</div>
														<div class="item" data-value="expressionless">
															<em class="tiny" data-emoji="expressionless"></em>
														</div>
														<div class="item" data-value="frowning">
															<em class="tiny" data-emoji="frowning"></em>
														</div>
														<div class="item" data-value="heart_eyes">
															<em class="tiny" data-emoji="heart_eyes"></em>
														</div>
														<div class="item" data-value="joy">
															<em class="tiny" data-emoji="joy"></em>
														</div>
														<div class="item" data-value="smile">
															<em class="tiny" data-emoji="smile"></em>
														</div>
														<div class="item" data-value="smiley">
															<em class="tiny" data-emoji="smiley"></em>
														</div>
														<div class="item" data-value="zany_face">
															<em class="tiny" data-emoji="zany_face"></em>
														</div>
														
													</div>
												</div>
												<input type="text" name="statusText" placeHolder="What's happening?" value="${statusText}">
											</div>
											<c:if test="${not empty errors.statusText}">
												<div class="ui pointing above red basic label"><c:out value="${errors.statusText}" /></div>
											</c:if>
										</div>
									</div>
									<div style="margin: 3vh 0;">
										<div class="ui field ${not empty errors.name ? 'error' : ''}">
											<div class="ui fluid input">
												<input type="text" name="name" placeholder="Name" value="<c:out value="${name}" />">
											</div>
											<c:if test="${not empty errors.name}">
												<div class="ui pointing above red basic label"><c:out value="${errors.name}" /></div>
											</c:if>
										</div>
									</div>
									<div style="margin: 3vh 0;">
										<div class="ui field ${not empty errors.bio ? 'error' : ''}">
											<div class="ui fluid input">
												<textarea name="bio" placeholder="Tell us a little about yourself"><c:out value="${bio}" /></textarea>
												<c:if test="${not empty errors.bio}">
													<div class="ui pointing above red basic label"><c:out value="${errors.bio}" /></div>
												</c:if>
											</div>
										</div>
									</div>
									<button class="ui large positive fluid button" type="submit">Update profile</button>
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