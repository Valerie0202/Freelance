<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="../include/header.jsp" />


<c:choose>
    <%-- If the ID is empty, we create a new invoice --%>
    <c:when test="${empty form.id}">
        <h1>Create New Invoice</h1>
        <form method="POST" action="/invoice/createInvoiceSubmit">
            <input type="hidden" name="id" value="${form.id}">
            <div class="registerform">
                <select class="formInput" name="clientId">
                    <c:forEach items="${clients}" var="client">
                        <option value="${client.id}">${client.firstName} ${client.lastName}</option>
                    </c:forEach>
                </select>
                <input class="formInput" type="text" name="title" placeholder="Project Title">
                <input class="formInput" type="text" name="date" placeholder="Date">
                <input class="formInput" type="text" name="tax" placeholder="Tax">
                <textarea class="formInput" maxlength="500" name="notes" placeholder="Notes (max 100 char)"></textarea>

                <button class="formInput" type="submit">Create</button>
            </div>
        </form>
    </c:when>
    <%-- If the ID is not empty, we display the invoice and allow user to add lines --%>
    <c:otherwise>
        <h1>Add to Invoice</h1>
        <div class="printable">
            <table border="1">
                <tr>
                    <td><b>Client</b></td>
                    <td><b>Invoice ID</b></td>
                    <td><b>Project Title</b></td>
                    <td><b>Date</b></td>
                    <td><b>Notes</b></td>
                </tr>
                <tr>
                    <td>${form.clientName}</td>
                    <td>${form.id}</td>
                    <td>${form.title}</td>
                    <td>${form.date}</td>
                    <td>${form.notes}</td>
                </tr>
                <tr>
                    <td><b>Line ID</b></td>
                    <td><b>Line Item</b></td>
                    <td><b>Unit Price</b></td>
                    <td><b>Quantity</b></td>
                    <td><b>Notes</b></td>
                    <td><b>Item Subtotal</b></td>
                </tr>
                <c:set var="subtotal" scope="request" value="0"/>
                <c:forEach items="${invoiceLines}" var="line">
                    <tr>
                        <td>${line.id}</td>
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
                </c:forEach>
                <tr>
                    <th>Grand total</th>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>
                        <strong><c:out value="$${subtotal}"/></strong>
                    </td>
                </tr>
            </table>
        </div>
        <form method="POST" action="/invoice/createInvoiceLineSubmit">
            <input type="hidden" name="invoiceId" value="${form.id}">
            <div class="registerform">
                <input class="formInput" type="text" name="item" placeholder="Line Item">
                <input class="formInput" type="text" name="price" placeholder="Unit Price">
                <input class="formInput" type="text" name="quantity" placeholder="Item Quantity">
                <textarea class="formInput" maxlength="500" name="notes" placeholder="Notes (max 200 char)"></textarea>

                <button class="formInput" type="submit">Add Line</button>
            </div>
        </form>
    </c:otherwise>
</c:choose>

<jsp:include page="../include/footer.jsp"/>