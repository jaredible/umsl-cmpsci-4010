<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="New Problem" scope="request" />

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="../partial/head.jsp" %>
	</head>
	<body>
		<%@ include file="../partial/sidemenu.jsp" %>
		
		<div class="pusher">
			<%@ include file="../partial/header.jsp" %>
			
			<main class="mb-3">
				<div class="ui vertical segment">
					<div class="ui container">
						<div class="ui grid">
							<div class="row">
								<div class="wide column">
									<div class="ui secondary raised top attached segment">
										<h3 class="ui header">New problem</h3>
									</div>
									<form class="ui form raised stacked bottom attached segment" action="${pageContext.request.contextPath}/problem/new" method="post" >
										<div class="ui required field ${(errors.title != null) ? 'error' : ''}">
											<label>Title</label>
											<input type="text" name="title" value="${title}">
											<c:if test="${errors.title != null}">
												<div class="ui pointing above red basic label"><c:out value="${errors.title}" /></div>
											</c:if>
										</div>
										<div class="ui required field ${(errors.categoryIds != null) ? 'error' : ''}">
											<label>Categories</label>
											<div class="ui fluid multiple search normal selection dropdown">
												<input type="hidden" name="categoryIds" value="${categoryIds}">
												<i class="dropdown icon"></i>
												<div class="default text"></div>
												<div class="menu">
													<c:forEach var="category" items="${categories}">
														<div class="item" data-value="${category.id}"><c:out value="${category.name}" /></div>
													</c:forEach>
												</div>
											</div>
											<c:if test="${errors.categoryIds != null}">
												<div class="ui pointing above red basic label"><c:out value="${errors.categoryIds}" /></div>
											</c:if>
										</div>
										<div class="ui required field ${(errors.tagIds != null) ? 'error' : ''}">
											<label>Tags</label>
											<div class="ui fluid multiple search normal selection dropdown">
												<input type="hidden" name="tagIds" value="${tagIds}">
												<i class="dropdown icon"></i>
												<div class="default text"></div>
												<div class="menu">
													<c:forEach var="tag" items="${tags}">
														<div class="item" data-value="${tag.id}"><c:out value="${tag.name}" /></div>
													</c:forEach>
												</div>
											</div>
											<c:if test="${errors.tagIds != null}">
												<div class="ui pointing above red basic label"><c:out value="${errors.tagIds}" /></div>
											</c:if>
										</div>
										<div class="ui required field ${(errors.content != null) ? 'error' : ''}">
											<label>Content</label>
											<textarea name="content">${content}</textarea>
											<c:if test="${errors.content != null}">
												<div class="ui pointing above red basic label"><c:out value="${errors.content}" /></div>
											</c:if>
										</div>
										<div class="ui center aligned basic segment">
											<button class="ui primary submit button" type="submit">Add problem</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<%@ include file="../partial/footer.jsp" %>
			</main>
		</div>
		
		<jsp:include page="../partial/scripts.jsp" />
	</body>
</html>