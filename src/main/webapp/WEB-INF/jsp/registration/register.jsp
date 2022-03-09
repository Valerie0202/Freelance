<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>


<form method="POST" onsubmit="return validate();" action="/registration/registerSubmit">
    <div class="segment condensed">
        <h2 class="section-header">Sign Up</h2>
        <br>
        <div class="registerForm">

            <div class="errorMessages">
                <c:forEach items="${errorMessages}" var="message">
                    <span class="errorMessage">${message}</span><br>
                </c:forEach>
            </div>
            <input type="text" class="formInput" name="username" value="${form.username}" placeholder="Username">
            <input type="text" class="formInput" name="email" value="${form.email}" placeholder="Email">
            <input type="text" class="formInput" name="firstName" id="firstName" value="${form.firstName}" placeholder="First Name">
            <input type="text" class="formInput" name="lastName" id="lastName" value="${form.lastName}" placeholder="Last Name">
            <input class="formInput" type="text" name="phone" placeholder="Phone Number"  value="${form.phone}">
            <input class="formInput" type="text" name="address1" placeholder="Address Line 1" value="${form.address1}">
            <input class="formInput" type="text" name="address2" placeholder="Address Line 2" value="${form.address2}">
            <input type="password" class="formInput" name="password" placeholder="Password"></td>
            <input type="password" class="formInput" name="confirmPassword" placeholder="Re-type Password"></td>

            <button type="submit"><h2 class="section-header">Register</h2></button>
        </div>
    </div>
</form>

<script type="text/javascript" src="/pub/js/index.js"></script>

<jsp:include page="../include/footer.jsp"/>