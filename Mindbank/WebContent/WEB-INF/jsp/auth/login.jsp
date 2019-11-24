<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Login" scope="request" />

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="../partial/head.jsp" %>
	</head>
	<body>
		<main>
			<div class="ui vertical segment">
				<div class="ui container">
					<div class="ui centered grid">
						<div class="row">
							<div class="wide column">
								<form class="ui form" action="profile" method="post">
									<div class="ui field">
										<label>Profile picture</label>
										<img id="profilePicture" class="ui small rounded image" src="https://fomantic-ui.com/images/wireframe/image.png">
										<button id="profileEdit" class="mini ui secondary button" type="button">
											<i class="pencil alternate icon"></i>
											Edit
										</button>
										<input type="file" name="profileImage" accept="image/*" value="Edit">
									</div>
									<div class="ui field ${(errors.name != null) ? 'error' : ''}">
										<label>Name</label>
										<input type="text" name="name" value="${name}">
									</div>
									<div class="ui field ${(errors.bio != null) ? 'error' : ''}">
										<label>Bio</label>
										<textarea name="bio" placeholder="Tell us a little about yourself"><c:out value="${bio}" /></textarea>
									</div>
									<div class="ui center aligned basic segment">
										<button class="ui positive button" type="submit">Update profile</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<%@ include file="../partial/footer.jsp" %>
		</main>
		
		<jsp:include page="../partial/scripts.jsp" />
	</body>
</html>