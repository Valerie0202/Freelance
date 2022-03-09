<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="../include/header.jsp"/>
<br>
<div class="segment">
    <div class="tableTop">
        <h2 class="section-header">Invoices</h2>
        <a href="/invoice/createInvoice"><button class="section-header createClient">Create new invoice</button></a>
    </div>
<table class="styled-table">
    <thead>
    <tr>
        <td><b>Invoice ID</b></td>
        <td><b>Client</b></td>
        <td><b>Project title</b></td>
        <td><b>Date</b></td>
        <td><b>Notes</b></td>
        <td><b>Edit</b></td>
        <td><b>Delete</b></td>
    </tr>
    </thead>
    <c:forEach items="${invoices}" var="invoice">
        <tr>
            <td>${invoice.id}</td>
            <td>${invoice.client.firstName} ${invoice.client.lastName}</td>
            <td>${invoice.title}</td>
            <td>${invoice.date}</td>
            <td>${invoice.notes}</td>
            <td><a href="/invoice/createInvoice?id=${invoice.id}"><button>Edit</button></a></td>
            <td><a href="/invoice/deleteInvoice?id=${client.id}"><button class="delete">Delete</button></a></td>
        </tr>
    </c:forEach>
</table>
</div>

<jsp:include page="../include/footer.jsp"/>