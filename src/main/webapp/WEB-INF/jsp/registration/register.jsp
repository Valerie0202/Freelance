<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>


<form method="POST" action="/registration/registerSubmit">
    <div class="segment center">
        <h2 class="section-header">Sign Up</h2>
        <br>
        <div class="registerForm">
            <input type="text" name="username" value="${form.username}" placeholder="Username">
            <input type="text" name="email" value="${form.email}" placeholder="Email">
            <input type="text" name="firstName" id="firstName" value="${form.firstName}" placeholder="First Name">
            <input type="text" name="lastName" id="lastName" value="${form.lastName}" placeholder="Last Name">
            <input class="formInput" type="text" name="phone" placeholder="Phone Number"  value="${form.phone}">
            <input class="formInput" type="text" name="address1" placeholder="Address Line 1" value="${form.address1}">
            <input class="formInput" type="text" name="address2" placeholder="Address Line 2" value="${form.address2}">
            <input type="password" name="password" placeholder="Password"></td>
            <input type="password" name="confirmPassword" placeholder="Re-type Password"></td>

            <button type="submit"><h2 class="section-header">Register</h2></button>
        </div>
    </div>
</form>


<div class="errorMessages">
    <c:forEach items="${errorMessages}" var="message">
        <span style="color:red">${message}</span><br>
    </c:forEach>
</div>

<jsp:include page="../include/footer.jsp"/>