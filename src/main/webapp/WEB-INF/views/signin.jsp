<%-- 
    Document   : signin
    Created on : Oct 25, 2016, 3:24:26 PM
    Author     : jose.rubalcaba
--%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/shared/resources-import.jsp"%>
        <title>Signin</title>
    </head>
    <body>
        <%@include file="/shared/nav-bar.jsp"%>
        <div class="container">
            <div class="mdl-card mdl-shadow--4dp center-block main-card">
                <h3 class="text-center">Sign in to Dare‹</h3>
                <form action="/security/authenticate" method="POST">
                    <div class="form-group">
                        <label for="emailInput">Email</label>
                        <input class="form-control" id="nameInput" type="email" placeholder="Email" name="email">
                    </div>
                    <div class="form-group">
                        <label for="passwordInput">Email</label>
                        <input class="form-control" id="passwordInput" type="password" placeholder="Password" name="password">
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <button type="submit" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">Sign in</button>
                </form>
            </div>
        </div>
    </body>
</html>
