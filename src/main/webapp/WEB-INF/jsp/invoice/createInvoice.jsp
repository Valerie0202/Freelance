<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="../include/header.jsp" />


<c:choose>
    <%-- If the ID is empty, we create a new invoice --%>
    <c:when test="${empty form.id}">
        <div class="segment condensed">
        <h2 class="section-header">Create New Invoice</h2>
        <form method="POST" action="/invoice/createInvoiceSubmit" id="invoiceForm">
            <input type="hidden" name="id" value="${form.id}">
            <div class="registerForm">
                <select class="formInput" name="clientId">
                    <c:forEach items="${clients}" var="client">
                        <option value="${client.id}">${client.firstName} ${client.lastName}</option>
                    </c:forEach>
                </select>
                <input class="formInput" type="text" name="title" placeholder="Project Title">
                <input class="formInput" type="text" name="date" placeholder="Date">
                <input class="formInput" type="text" name="tax" placeholder="Tax">
                <textarea class="formInput" maxlength="500" name="notes" placeholder="Notes (max 100 char)"></textarea>

            </div>
        </form>
        <button class="formInput section-header submitButton" type="submit" form="invoiceForm">Create</button>
    </c:when>
    <%-- If the ID is not empty, we display the invoice and allow user to add lines --%>
    <c:otherwise>
        <div class="segment condensedTable">
        <h2 class="section-header">Add to Invoice</h2>
        <div class="styled-table">
            <table>
                <thead>
                    <tr>
                        <td><b>Client</b></td>
                        <td><b>Invoice ID</b></td>
                        <td><b>Project Title</b></td>
                        <td><b>Date</b></td>
                        <td><b>Notes</b></td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${form.clientName}</td>
                        <td>${form.id}</td>
                        <td>${form.title}</td>
                        <td>${form.date}</td>
                        <td>${form.notes}</td>
                    </tr>
                </tbody>
                <thead>
                    <tr>
                        <td><b>Line Item</b></td>
                        <td><b>Unit Price</b></td>
                        <td><b>Quantity</b></td>
                        <td><b>Notes</b></td>
                        <td><b>Item Subtotal</b></td>
                    </tr>
                </thead>
                <c:set var="subtotal" scope="request" value="0"/>
                <c:forEach items="${invoiceLines}" var="line">
                <tbody>
                    <tr>
                        <td>${line.item}</td>
                        <td>$${line.price}</td>
                        <td>${line.quantity}</td>
                        <td>${line.notes}</td>
                        <td class="itemSubtotal">
                            <c:set var="lineTotal" scope="request" value="${line.price * line.quantity}"/>
                            <c:set var="subtotal" scope="request" value="${subtotal + lineTotal}"/>
                            <c:out value="$${lineTotal}"/>
                        </td>
                    </tr>
                </tbody>
                </c:forEach>
                <thead>
                <tr>
                    <th>Subtotal</th>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>
                        <strong><c:out value="$${subtotal}"/></strong>
                    </td>
                </tr>
                </thead>
            </table>
        </div>
        <form method="POST" action="/invoice/createInvoiceLineSubmit" id="invoiceLineForm">
            <input type="hidden" name="invoiceId" value="${form.id}">
            <div class="registerForm">
                <input class="formInput" type="text" name="item" placeholder="Line Item">
                <input class="formInput" type="text" name="price" placeholder="Unit Price">
                <input class="formInput" type="text" name="quantity" placeholder="Item Quantity">
                <textarea class="formInput" maxlength="500" name="notes" placeholder="Notes (max 200 char)"></textarea>

            </div>
        </form>
        <button class="formInput section-header submitButton" type="submit" form="invoiceLineForm">Add line</button>
        <a href="/invoice/printInvoice?id=${form.id}"><button class="section-header submitButton">Print invoice</button></a>
    </c:otherwise>
</c:choose>

</div>

<jsp:include page="../include/footer.jsp"/>