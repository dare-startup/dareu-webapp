<%-- 
    Document   : application-error
    Created on : Dec 11, 2016, 11:17:41 PM
    Author     : jose.rubalcaba
--%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/shared/resources-import.jsp"%>
        <title>Application Error</title>
    </head>
    <body>
        <%@include file="/shared/empty-nav-bar.jsp"%>
        <div class="container">
            <div class="card notification-card elevated">
                <img src="${pageContext.request.contextPath}/resources/img/sad.png" width="300" height="300" class="img-thumbnail center">
                <h4 class="center-text">There has been an error processing your request</h4>
                <p class="center-text">${errorMessage}</p>
                <a href="${redirect}" class="center">Return and try again</a>
            </div>
        </div>
    </body>
</html>
