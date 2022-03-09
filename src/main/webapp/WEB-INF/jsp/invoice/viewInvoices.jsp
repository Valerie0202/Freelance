<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="../include/header.jsp"/>
<br>
<div class="segment">
    <h2 class="section-header">Invoices</h2>
<table class="styled-table">
    <thead>
    <tr>
        <td><b>Invoice ID</b></td>
        <td><b>Client</b></td>
        <td><b>Project title</b></td>
        <td><b>Date</b></td>
        <td><b>Notes</b></td>
        <td><b>Edit</b></td>
    </tr>
    </thead>
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
</div>

<jsp:include page="../include/footer.jsp"/>