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
        <section class="signin-section">
            <%@include file="/shared/nav-bar.jsp"%>  
            <div class="container">
                <div class="signin-card">
                    <h4 class="text-xs-center text-sm-center text-md-center text-lg-center font-weight-bold">Sign in to Dare‹</h4>
                    <form id="signin-form" action="/dareu/security/authenticate" method="POST" class='signin-form'>
                        <div class="form-group">
                            <label for="emailInput">Email</label>
                            <input class="form-control" id="nameInput" type="email" placeholder="Email" name="email">
                        </div>
                        <div class="form-group">
                            <label for="passwordInput">Password</label>
                            <input class="form-control" id="passwordInput" type="password" placeholder="Password" name="password">
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <button type="submit" class="btn btn-primary btn-lg center btn-block text-md-center text-lg-center ripple">Sign in</button>
                    </form>
                </div>
            </div>
        </section>
    </body>
</html>
