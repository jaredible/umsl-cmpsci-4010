<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../partial/head.jsp">
			<jsp:param name="title" value="Problems" />
		</jsp:include>
	</head>
<body>
	<div class="main">
		<div class="ui container">
			<header>
				<%@ include file="../partial/header.jsp" %>
			</header>
			
			<form class="ui form raised segment" action="problems" method="post">
				<div class="field">
					<div class="ui sub header">Search by category</div>
					<div class="ui fluid multiple search normal selection dropdown">
						<input type="hidden" name="tagIds">
						<i class="dropdown icon"></i>
						<div class="default text">Select categories</div>
						<div class="menu">
							<c:forEach var="category" items="${categories}">
								<option class="item" value="${category.id}"><c:out value="${category.name}" /></option>
							</c:forEach>
						</div>
					</div>
				</div>
				<div class="field">
					<div class="ui sub header">Search by tag</div>
					<div class="ui fluid multiple search normal selection dropdown">
						<input type="hidden" name="tagIds">
						<i class="dropdown icon"></i>
						<div class="default text">Select tags</div>
						<div class="menu">
							<c:forEach var="tag" items="${tags}">
								<option class="item" value="${tag.id}"><c:out value="${tag.name}" /></option>
							</c:forEach>
						</div>
					</div>
				</div>
				<button class="ui blue submit button" type="submit">Submit</button>
			</form>
			<c:forEach var="i" begin="1" end="6">
				<div class="ui piled raised segment">
					<h4 class="ui header">A header</h4>
					<p>Te eum doming eirmod, nominati pertinacia argumentum ad his. Ex eam alia facete scriptorem, est autem aliquip detraxit at. Usu ocurreret referrentur at, cu epicurei appellantur vix. Cum ea laoreet recteque electram, eos choro alterum definiebas in. Vim dolorum definiebas an. Mei ex natum rebum iisque.</p>
					<p>Audiam quaerendum eu sea, pro omittam definiebas ex. Te est latine definitiones. Quot wisi nulla ex duo. Vis sint solet expetenda ne, his te phaedrum referrentur consectetuer. Id vix fabulas oporteat, ei quo vide phaedrum, vim vivendum maiestatis in.</p>
					<p>Eu quo homero blandit intellegebat. Incorrupte consequuntur mei id. Mei ut facer dolores adolescens, no illum aperiri quo, usu odio brute at. Qui te porro electram, ea dico facete utroque quo. Populo quodsi te eam, wisi everti eos ex, eum elitr altera utamur at. Quodsi convenire mnesarchum eu per, quas minimum postulant per id.</p>
					<div class="ui footer">A footer</div>
				</div>
			</c:forEach>
		</div>
		
		<%@ include file="../partial/footer.jsp" %>
	</div>
	
	<jsp:include page="../partial/scripts.jsp" />
</body>
</html>