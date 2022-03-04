<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="../include/header.jsp"/>

<%-- Everything in .invoice will be set to display: none; while media is screen --%>
<%-- Everything outside of .invoice will be hidden when printed --%>
<div class="invoice">
    <div class="invoiceHeader">
        <h2>INVOICE</h2>
    </div>
    <div class="invoiceTitle">
        <h3>WI-FI NETWORK CONFIGURATION</h3>
        <p>Wi-fi network was established in client's home</p>
    </div>
    <div class="userInfo">
        <p id="invoiceUserName">Valerie Smith</p>
        <p>123 W Main Street</p>
        <p>Charlotte, NC 28210</p>
        <p>(800) 555-2300</p>
        <p>valeriesmith0202@gmail.com</p>
    </div>
    <div class="clientInfo">
        <h3>BILL TO</h3>
        <p id="invoiceClientName">Joe Edwards</p>
        <p>321 E Main Street</p>
        <p>Charlotte, NC 28210</p>
        <p>(888) 555-6400</p>
        <p>jedwards@gmail.com</p>
    </div>
    <div class="invoiceReference">
        <table>
            <tr>
                <th>INVOICE ID:</th>
                <td>62509</td>
            </tr>
            <tr>
                <th>CLIENT ID:</th>
                <td>32500</td>
            </tr>
            <tr>
                <th>DATE:</th>
                <td>02-16-2022</td>
            </tr>
        </table>
    </div>
    <div class="invoiceTable">
        <table>
            <tr>
                <th>ITEM</th>
                <th>QTY</th>
                <th>UNIT PRICE</th>
                <th>NOTES</th>
                <th>TOTAL</th>
            </tr>
            <tr>
                <td>Modem</td>
                <td>1</td>
                <td>$65</td>
                <td> </td>
                <td>$65</td>
            </tr>
            <tr>
                <td>Router</td>
                <td>1</td>
                <td>$79.50</td>
                <td> </td>
                <td>$79.50</td>
            </tr>
            <tr>
                <td>Wi-fi repeater</td>
                <td>2</td>
                <td>$22.37</td>
                <td>Purchase receipt attached in email</td>
                <td>$44.74</td>
            </tr>
            <tr>
                <td>Hours</td>
                <td>2.25</td>
                <td>$40</td>
                <td> </td>
                <td>$90</td>
            </tr>
        </table>
    </div>
    <div class="invoiceTotal">
        <table>
            <tr>
                <th>SUBTOTAL</th>
                <th>$297.24</th>
            </tr>
            <tr>
                <th>TAX RATE</th>
                <th>8%</th>
            </tr>
            <tr>
                <th>TOTAL TAX</th>
                <th>$22.34</th>
            </tr>
        </table>
        <p>TOTAL DUE: <span id="invoiceFinalTotal">$319.58</span></p>
    </div>
</div>

<div class="printButton">
    <button onClick="window.print()">Print this page
</button></div>


<jsp:include page="../include/footer.jsp"/>