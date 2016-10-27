<%-- 
    Document   : signin
    Created on : Oct 25, 2016, 3:24:26 PM
    Author     : jose.rubalcaba
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Signin</title>
    </head>
    <body>
        <h1>Signin!</h1>
        <form action="/security/authenticate" method="POST">
            <input type="email" name="email" placeholder="Email"><br/>
            <input type="password" name="password" placeholder="Password"><br/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button type="submit">Signin</button>
        </form>
    </body>
</html>
