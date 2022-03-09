<jsp:include page="../include/header.jsp" />

<!-- form submission must align with security config see .loginProcessingUrl in Security Config -->
<form action="/login/loginSecurityPost" method="POST">
    <h1 style="color:red">${errorMessage}</h1>

    <!-- username and password input text fields must have the names username and password
         for spring boot to be able to authenticate the user -->
    <div class="registerForm segment">
        <h2 class="section-header">Log in</h2>
        <input type="text" name="username" placeholder="Email">
        <input type="password" name="password" placeholder="Password">

        <button type="submit"><h2 class="section-header">Log in</h2></button>
    </div>
</form>
<jsp:include page="../include/footer.jsp" />