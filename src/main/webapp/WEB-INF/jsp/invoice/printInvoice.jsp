<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../include/header.jsp"/>

<%-- Everything in .invoice will be set to display: none; while media is screen --%>
<%-- Everything outside of .invoice will be hidden when printed --%>
<div class="invoice">
    <div class="invoiceHeader">
        <h2>INVOICE</h2>
    </div>
    <div class="invoiceTitle">
        <h3>${invoice.title}</h3>
        <p>${invoice.notes}</p>
    </div>
    <div class="userInfo">
        <p id="invoiceUserName">${user.firstName} ${user.lastName}</p>
        <p>${user.address1}</p>
        <p>${user.address2}</p>
        <p>${user.phone}</p>
        <p>${user.email}</p>
    </div>
    <div class="clientInfo">
        <h3>BILL TO</h3>
        <p id="invoiceClientName">${client.firstName} ${client.lastName}</p>
        <p>${client.address1}</p>
        <p>${client.address2}</p>
        <p>${client.phone}</p>
        <p>${client.email}</p>
    </div>
    <div class="invoiceReference">
        <table>
            <tr>
                <th>INVOICE ID:</th>
                <td>${invoice.id}</td>
            </tr>
            <tr>
                <th>CLIENT ID:</th>
                <td>${client.id}</td>
            </tr>
            <tr>
                <th>DATE:</th>
                <td>${invoice.date}</td>
            </tr>
        </table>
    </div>
    <div class="invoiceTable">
        <table>
            <tr>
                <th>ITEM</th>
                <th>UNIT PRICE</th>
                <th>QTY</th>
                <th>NOTES</th>
                <th>TOTAL</th>
            </tr>
            <c:set var="subtotal" scope="request" value="0"/>
            <c:forEach items="${invoiceLines}" var="line">
                <tr>
                    <td>${line.item}</td>
                    <td><fmt:formatNumber value="${line.price}" pattern="$######0.00"/></td>
                    <td><fmt:formatNumber value="${line.quantity}" pattern="###0.00"/></td>
                    <td>${line.notes}</td>
                    <td class="itemSubtotal">
                        <c:set var="lineTotal" scope="request" value="${line.price * line.quantity}"/>
                        <c:set var="subtotal" scope="request" value="${subtotal + lineTotal}"/>
                        <fmt:formatNumber value="${lineTotal}" pattern="$######0.00"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="invoiceTotal">
        <table>
            <tr>
                <th>SUBTOTAL</th>
                <th><fmt:formatNumber value="${subtotal}" pattern="$######0.00"/></th>
            </tr>
            <tr>
                <th>TAX RATE</th>
                <th><fmt:formatNumber value="${invoice.tax}" pattern="##0.00%"/></th>
            </tr>
            <tr>
                <th>TOTAL TAX</th>
                <th><fmt:formatNumber value="${invoice.tax * subtotal}" pattern="$######0.00"/></th>
            </tr>
        </table>
        <p>TOTAL DUE:
            <span id="invoiceFinalTotal">
            <fmt:formatNumber value="${(invoice.tax + 1) * subtotal}" pattern="$######0.00"/>
            </span>
        </p>
    </div>
</div>

<div class="printButton">
    <button onClick="window.print()">
        <h2 class="section-header">Print</h2>
    </button>
</div>


<jsp:include page="../include/footer.jsp"/>