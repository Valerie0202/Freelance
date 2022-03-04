<jsp:include page="../include/header.jsp"/>

<form method="POST" action="/registration/registerSubmit">

    <h2>Create an account</h2>

    <div class="registerform">
        <input type="text" name="username" value="${form.username}" placeholder="Username">
        <input type="text" name="email" value="${form.email}" placeholder="Email">
        <input type="text" name="firstName" value="${form.firstName}" placeholder="First Name">
        <input type="text" name="lastName" value="${form.lastName}" placeholder="Last Name">
        <input class="formInput" type="text" name="phone" placeholder="Phone Number"  value="${form.phone}">
        <input class="formInput" type="text" name="address1" placeholder="Address Line 1" value="${form.address1}">
        <input class="formInput" type="text" name="address2" placeholder="Address Line 2" value="${form.address2}">
        <input type="password" name="password" placeholder="Password"></td>
        <input type="password" name="confirmPassword" placeholder="Re-type Password"></td>

        <button type="submit">Register</button>
    </div>
</form>

<jsp:include page="../include/footer.jsp"/>