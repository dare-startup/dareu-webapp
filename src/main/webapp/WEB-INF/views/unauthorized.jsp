<%-- 
    Document   : unauthorized
    Created on : Oct 27, 2016, 10:55:21 AM
    Author     : jose.rubalcaba
--%>

<!DOCTYPE html>
<html>
    <head>
        <%@include file="/shared/resources-import.jsp"%>
        <title>Unauthorized</title>
    </head>
    <body>
        <%@include file="/shared/empty-nav-bar.jsp"%>
        <div class="container">
            <div class="card notification-card elevated">
                <img src="/dareu/resources/img/unauthorized.png" width="300" height="300" class="img-thumbnail center">
                <h4 class="center-text">Looks like you are not supposed to be here</h4>
                <a href="${request.getContextPath()}/" class="center-text">Return to where you belong now</a>
            </div>
        </div>
    </body>
</html>

