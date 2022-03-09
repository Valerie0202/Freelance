<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="./include/header.jsp"/>

<div class="banner">
    <h1 class="banner-text">Welcome to Freelance.</h1>
</div>

<!-- content-wrapper contains About Us, Hours, and Find Us panels -->
<div class="content-wrapper">

    <!--Appears on the left side of the homepage -->
    <div class="left-side">

        <!-- About Us -->
        <div class="about segment">
            <h2 class="section-header">About</h2>
            <br>
            <p class="paragraph">
                Never reach for that messy collection of notebooks and planners again; we've got you covered.
            </p>
            <br>
            <p class="paragraph">
                Freelance is an application designed by freelance workers, for freelance workers. Self-employed individuals know that it's a struggle to keep track of appointments alone. Add in client contact information, notes, invoices, and pricing, and you've got a whirlwind of information to manage.
            </p>
            <br>
            <p class="paragraph">
                Our objective is to empower you to take the power of consolidation into your own hands. Keep track of all your clients, their contact information, your past projects for them, and any notes you have about them with one friendly tool. Use the same tool to generate and keep track of invoices with the push of a button.
            </p>
        </div>
    </div>

    <!-- Appears on the right side of the homepage -->
    <div class="right-side">

        <!-- Hours -->
        <div class="hours segment">
            <h2 class="section-header">Features</h2>
            <p class="paragraph">Freelance provides you with countless features, including the following:</p>
            <ul>
                <li>Ability to create client profiles</li>
                <li>Account registration</li>
                <li>Invoice generation</li>
                <li>Browse clients</li>
                <li>Browse invoices</li>
                <li>Secure login</li>
            </ul>
        </div>

        <!-- Address and image of location on map are displayed -->
        <sec:authorize access="!isAuthenticated()">

         <div class="more-info segment">
            <h2 class="section-header">Get started</h2>
            <p class="paragraph">Sign up for an account today to get started with us!</p>
            <a href="./registration/register"><button><h2 class="section-header">Register</h2></button></a>
        </div>

        </sec:authorize>
    </div>
</div>

<jsp:include page="./include/footer.jsp"/>