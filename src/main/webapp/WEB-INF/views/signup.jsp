<%-- 
    Document   : signup
    Created on : Oct 25, 2016, 3:24:36 PM
    Author     : jose.rubalcaba
--%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/shared/resources-import.jsp"%>
        <title>Sign up</title>
    </head>
    <body>
        <section class="signup-section">
            <%@include file="/shared/nav-bar.jsp" %>
            <div class="container">
            <div class="signup-card">
                <h4 class="text-xs-center text-sm-center text-md-center text-lg-center font-weight-bold">Sign up to Dare‹</h4>
                <form:form action="${pageContext.request.contextPath}/signup" method="POST" commandName="signup">
                    <div class="form-group">
                        <form:label path="name">Name</form:label>
                        <form:input cssClass="form-control" path="name" type="text"></form:input>
                    </div>
                    <div class="form-group">
                        <form:label path="email">Email</form:label>
                        <form:input cssClass="form-control" path="email" type="email"></form:input>
                    </div>
                    <div class="form-group">
                        <form:label path="password">Password</form:label>
                        <form:input cssClass="form-control" path="password" type="password"></form:input>
                    </div>
                    <div class="form-group">
                        <form:label path="birthday">Birthday</form:label>
                        <form:input cssClass="form-control" path="birthday" type="date"></form:input>
                    </div>
                    <div class="form-group">
                    <form:checkbox path="sponsor" label="I want to sign up as sponsor"></form:checkbox>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <button type="submit" class="btn btn-primary btn-lg btn-block ripple">Sign up</button>
                </form:form>
            </div>
        </div>
        </section>
    </body>
</html>
