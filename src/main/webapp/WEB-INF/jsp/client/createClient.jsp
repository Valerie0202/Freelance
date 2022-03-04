<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="../include/header.jsp" />

<c:choose>
    <c:when test="${empty form.id}">
        <h1>Create New Client</h1>
    </c:when>
    <c:otherwise>
        <h1>Edit Client Information</h1>
    </c:otherwise>
</c:choose>

<form method="POST" action="/client/createClientSubmit" onsubmit="return validate();" id="clientForm">

    <input type="hidden" name="id" value="${form.id}">
    <div class="registerform">
        <input class="formInput nameSubmission" type="text" name="firstName" placeholder="First Name" value="${form.firstName}">
        <input class="formInput nameSubmission" type="text" name="lastName" placeholder="Last Name"  value="${form.lastName}">
        <input class="formInput" type="text" name="email" id="email" placeholder="Email"  value="${form.email}">
        <input class="formInput phone" type="text" name="phone" id="phone" placeholder="Phone Number"  value="${form.phone}">
        <input class="formInput" type="text" name="address1" id="address1" placeholder="Address Line 1" value="${form.address1}">
        <input class="formInput" type="text" name="address2" id="address2" placeholder="Address Line 2" value="${form.address2}">
        <textarea class="formInput" maxlength="500" name="notes" placeholder="Notes (max 500 char)">${form.notes}</textarea>

        <c:choose>
            <c:when test="${empty form.id}">
                <button class="formInput" type="submit">Create</button>
            </c:when>
            <c:otherwise>
                <button class="formInput" type="submit">Update</button>
            </c:otherwise>
        </c:choose>

    </div>
</form>
<script type="text/javascript" src="/pub/js/index.js"></script>

<jsp:include page="../include/footer.jsp"/>