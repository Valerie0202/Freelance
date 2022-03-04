<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="../include/header.jsp"/>

<h2>500 internal server error</h2>

<c:if test="${not empty message}">
    <br />
    <p>${message}</p>
</c:if>
<c:if test="${not empty stackTrace}">
    <p>${stackTrace}</p>

</c:if>

<jsp:include page="../include/footer.jsp"/>