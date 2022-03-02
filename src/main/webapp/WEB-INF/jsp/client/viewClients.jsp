<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="../include/header.jsp"/>

<h2>Clients list</h2>
<table border="1">
    <tr>
        <td><b>First Name</b></td>
        <td><b>Last Name</b></td>
        <td><b>Email</b></td>
        <td><b>Home Phone Number</b></td>
        <td><b>Cell Phone Number</b></td>
        <td><b>Address</b></td>
        <td><b>Notes</b></td>
        <td><b>Edit</b></td>
    </tr>
    <c:forEach items="${clients}" var="client">
        <tr>
            <td>${client.firstName}</td>
            <td>${client.lastName}</td>
            <td>${client.email}</td>
            <td>${client.homePhone}</td>
            <td>${client.cellPhone}</td>
            <td>${client.address}</td>
            <td>${client.notes}</td>
            <td><a role="button" href="/client/createClient?id=${client.id}">Edit</a></td>
        </tr>
    </c:forEach>
</table>

<jsp:include page="../include/footer.jsp"/>