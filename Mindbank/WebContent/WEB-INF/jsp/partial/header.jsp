<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${sessionScope.userId != null}">
		Logged In
	</c:when>
	<c:otherwise>
		Not Logged In
	</c:otherwise>
</c:choose>

<c:out value="${sessionScope.userId}" />