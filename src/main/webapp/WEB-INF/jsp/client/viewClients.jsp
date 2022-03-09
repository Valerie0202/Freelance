<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="../include/header.jsp"/>

<br>

<%-- TODO fix these buttons then format the rest of the tables and forms --%>

<div class="segment tableContainer">
    <h2 class="section-header">Clients</h2>
    <td><a href="/client/createClient"><button class="section-header">Create new client</button></a></td>
    <table class="styled-table">
        <thead>
            <tr>
                <td><b>First Name</b></td>
                <td><b>Last Name</b></td>
                <td><b>Email</b></td>
                <td><b>Phone Number</b></td>
                <td><b>Address</b></td>
                <td><b>Notes</b></td>
                <td><b>Edit</b></td>
                <td><b>Delete</b></td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${clients}" var="client">
                <tr>
                    <td>${client.firstName}</td>
                    <td>${client.lastName}</td>
                    <td>${client.email}</td>
                    <td>${client.phone}</td>
                    <td>${client.address1}, ${client.address2}</td>
                    <td>${client.notes}</td>
                    <td><a href="/client/createClient?id=${client.id}"><button>Edit</button></a></td>
                    <td><a href="/client/deleteClient?id=${client.id}"><button class="delete">Delete</button></a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="../include/footer.jsp"/>