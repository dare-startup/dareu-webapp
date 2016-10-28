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
        <div class="mdl-js-layout mdl-layout">
            <%@include file="/shared/nav-bar.jsp"%>
            <main class="mdl-layout__content">
                <div class="page-content">

                    <div class="container-fluid">
                        <!-- Your content goes here -->
                        <div class="mdl-card mdl-shadow--4dp center-block">
                            <form action="/security/authenticate" method="POST">
                                 <div class="mdl-textfield mdl-js-textfield center">
                                    <input id="emailField" type="email" name="email" class="mdl-textfield__input">
                                    <label class="mdl-textfield__label" for="emailField">Email</label>
                                 </div><br/>
                                 <div class="mdl-textfield mdl-js-textfield center">
                                    <input id="passwordField" type="password" name="password" class="mdl-textfield__input">
                                    <label class="mdl-textfield__label" for="passwordField">Password</label>
                                 </div>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                <button type="submit" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect full-button">Signin</button>
                            </form>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    </body>
</html>
