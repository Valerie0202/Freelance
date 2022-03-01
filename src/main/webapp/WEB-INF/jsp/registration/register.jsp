<jsp:include page="../include/header.jsp"/>

<form method="POST" action="/registration/registerSubmit">

    <table>
        <tr>
            <td>Username</td>
            <td><input type="text" name="username" value="${form.username}">
            <td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="text" name="email" value="${form.email}"></td>
        </tr>
        <tr>
            <td>First Name:</td>
            <td><input type="text" name="firstName" value="${form.firstName}"></td>
        </tr>
        <tr>
            <td>Last Name:</td>
            <td><input type="text" name="lastName" value="${form.lastName}"></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td>Confirm Password</td>
            <td><input type="password" name="confirmPassword"></td>
        </tr>
    </table>
    <button type="submit">Register</button>
</form>

<jsp:include page="../include/footer.jsp"/>