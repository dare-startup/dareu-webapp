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
            <div class="card main-card">
                <img src="/resources/img/sad.png" width="300" height="300" class="img-thumbnail center">
                <h4 class="text-center">There has been an error processing your request</h4>
                <h5 class="text-center">${errorMessage}</h5>
                <a href="${redirect}">Return and try again</a>
            </div>
        </div>
    </body>
</html>
