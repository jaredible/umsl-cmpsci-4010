<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Profile" scope="request" />
<c:set var="profileImage" scope="page">
	<c:out value="${profileImage}" default="${pageContext.request.contextPath}/img/missing.png" />
</c:set>

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="../../partial/head.jsp" %>
	</head>
	<body>
		<%@ include file="../../partial/modals.jsp" %>
		<%@ include file="../../partial/sidemenu.jsp" %>
		
		<div class="pusher">
			<%@ include file="../../partial/header.jsp" %>
			
			<main class="mb-3">
				<div class="ui vertical segment">
					<div class="ui container">
						<div class="ui grid">
							<div class="row">
								<div class="wide column">
									<div class="ui secondary raised top attached segment">
										<h3 class="ui header">Public profile</h3>
									</div>
									<form class="ui form raised stacked bottom attached segment" action="${pageContext.request.contextPath}/settings/profile" method="post" enctype="multipart/form-data">
										<div class="ui stackable two column grid">
											<div class="six wide column">
												<div class="ui field">
													<div class="m-auto">
														<div class="ui basic segment">
															<img id="profilePicture" class="ui medium segment rounded image m-auto p-0 mb-1" src="${profileImage}">
															<button id="profileEdit" class="ui secondary button d-block m-auto" type="button">
																<i class="pencil alternate icon"></i>
																Edit
															</button>
														</div>
													</div>
													<input type="file" name="profileImage" accept="image/*" value="Edit">
												</div>
											</div>
											<div class="ten wide column m-auto">
												<div class="ui stackable centered grid">
													<div class="row">
														<div class="column">
															<div class="ui field">
																<label>Email</label>
																<input class="ui disabled input" type="email" name="email" value="${email}" disabled>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="column">
															<div class="ui field">
																<label>Name</label>
																<input type="text" name="name" value="${name}">
															</div>
														</div>
													</div>
													<div class="row">
														<div class="column">
															<div class="ui field">
																<label>Status</label>
																<div class="ui labeled input">
																	<div class="ui dropdown label">
																		<input type="hidden" name="statusEmoji" value="${statusEmoji}">
																		<div class="text">
																			<i class="grin outline icon"></i>
																		</div>
																		<i class="dropdown icon"></i>
																		<div class="menu">
																			<div class="item" data-value="confused">
																				<em data-emoji="confused" class="tiny"></em>
																			</div>
																			<div class="item" data-value="expressionless">
																				<em data-emoji="expressionless" class="tiny"></em>
																			</div>
																			<div class="item" data-value="relaxed">
																				<em data-emoji="relaxed" class="tiny"></em>
																			</div>
																		</div>
																	</div>
																	<input type="text" name="statusText" value="${statusText}" placeholder="What's happening?">
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="sixteen wide column">
												<div class="ui field">
													<label>Bio</label>
													<textarea name="bio" placeholder="Tell us a little about yourself"><c:out value="${bio}" /></textarea>
												</div>
												<div class="ui center aligned basic segment">
													<button class="ui positive button" type="submit">Update</button>
												</div>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<%@ include file="../../partial/footer.jsp" %>
			</main>
		</div>
		
		<jsp:include page="../../partial/scripts.jsp" />
	</body>
</html>