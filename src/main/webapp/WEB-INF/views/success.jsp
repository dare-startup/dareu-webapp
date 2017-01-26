<%-- 
    Document   : success
    Created on : Jan 25, 2017, 10:16:55 AM
    Author     : Alberto Rubalcaba <arubalcaba@24hourfit.com>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/shared/resources-import.jsp"%>
        <title></title>
    </head>
    <body>
        <%@include file="/shared/nav-bar.jsp" %>
        <div class="container">
            <div class="card main-card">
                <c:choose>
                    <c:when test="${not empty registrationResponse}">
                        <p class="center-text">${registrationResponse.registrationType}</p>
                        <p class="center-text">${registrationResponse.message}</p>
                    </c:when>
                    <c:otherwise>
                        <p class="center-text">Could not retrieve success data</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </body>
</html>
