<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="../include/header.jsp"/>

<h2>Invoice list</h2>
<table border="1">
    <tr>
        <td><b>Invoice ID</b></td>
        <td><b>Client</b></td>
        <td><b>Project title</b></td>
        <td><b>Date</b></td>
        <td><b>Notes</b></td>
        <td><b>Edit</b></td>
    </tr>
    <c:forEach items="${invoices}" var="invoice">
        <tr>
            <td>${invoice.id}</td>
            <td>${invoice.client.firstName} ${invoice.client.lastName}</td>
            <td>${invoice.title}</td>
            <td>${invoice.date}</td>
            <td>${invoice.notes}</td>
            <td><a role="button" href="/invoice/createInvoice?id=${invoice.id}">Edit</a></td>
        </tr>
    </c:forEach>
</table>

<jsp:include page="../include/footer.jsp"/>