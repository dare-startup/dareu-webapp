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
            <div class="card notification-card elevated">
                <h4 class="center-text">Sign in to Dare‹</h4>
                <form action="/dareu/security/authenticate" method="POST">
                    <div class="form-group short-form-group center">
                        <label for="emailInput">Email</label>
                        <input class="form-control" id="nameInput" type="email" placeholder="Email" name="email">
                    </div>
                    <div class="form-group short-form-group center">
                        <label for="passwordInput">Password</label>
                        <input class="form-control" id="passwordInput" type="password" placeholder="Password" name="password">
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <button type="submit" class="btn btn-info btn-lg center short-form-button elevated">Sign in</button>
                </form>
            </div>
        </div>
    </body>
</html>
