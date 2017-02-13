<%-- 
    Document   : active
    Created on : Feb 8, 2017, 3:46:25 PM
    Author     : Alberto Rubalcaba <arubalcaba@24hourfit.com>
--%>

<!DOCTYPE html>
<html>
    <head>
        <%@include file="/shared/resources-import.jsp"%>
        <script src="${pageContext.request.contextPath}/resources/js/knockout.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/js/app/active-dare.js" type="text/javascript"></script>
        <title>Current active dare</title>
    </head>
    <body>
        <%@include file="/shared/nav-bar.jsp"%>
        <div class="container">
            <div class="card notification-card elevated">
                <h4>${activeDare.name}</h4>
                <p>${activeDare.description}</p>
                <p id="acceptedDate">${activeDare.acceptedDate}</p>
                <input type="hidden" id="timer" value="${activeDare.timer}">
                <p id="timerMessage"></p>
            </div>
        </div>
    </body>
</html>