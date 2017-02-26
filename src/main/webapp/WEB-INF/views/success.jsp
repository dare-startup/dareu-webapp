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
        <section class="success-section">
            <%@include file="/shared/nav-bar.jsp" %>
            <div class="container">
                <div class="card elevated">

                    <img src="${pageContext.request.contextPath}/resources/img/success.png" width="100" height="100" class="img img-circle center">
                    <c:choose>
                        <c:when test="${not empty registrationResponse}">
                            <h5 class="center-text">${registrationResponse.message}</h5>
                        </c:when>
                        <c:otherwise>
                            <p class="center-text">Could not retrieve success data</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </section>
    </body>
</html>
