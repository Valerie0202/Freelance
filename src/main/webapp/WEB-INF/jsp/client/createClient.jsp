<jsp:include page="../include/header.jsp"/>

<form method="POST" action="/client/createClientSubmit">

    <h2>Create an client</h2>

    <div class="registerform">
        <input type="text" name="firstName" placeholder="First Name">
        <input type="text" name="lastName" placeholder="Last Name">
        <input type="text" name="email" placeholder="Email">
        <input type="text" name="homePhone" placeholder="Home Phone Number">
        <input type="text" name="cellPhone" placeholder="Cell Phone Number">
        <input type="text" name="address" placeholder="Address">
        <textarea maxlength="500" name="notes">Notes (max 500 char)</textarea>

        <button type="submit">Create client</button>
    </div>
</form>

<jsp:include page="../include/footer.jsp"/>