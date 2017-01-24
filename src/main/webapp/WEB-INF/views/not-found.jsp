<%-- 
    Document   : not-found
    Created on : Oct 26, 2016, 9:34:05 AM
    Author     : jose.rubalcaba
--%>

<!DOCTYPE html>
<html>
    <head>
        <%@include file="/shared/resources-import.jsp"%>
        <title>Not found</title>
    </head>
    <body>
        <%@include file="/shared/empty-nav-bar.jsp"%>
        <div class="container">
            <div class="card main-card">
                <img src="/dareu/resources/img/notfound.png" width="300" height="300" class="img-thumbnail center">
                <h4 class="center-text">The resource you are looking for does not exists</h4>
                <a href="${request.getContextPath()}/" class="center-text">Return to home page</a>
            </div>
        </div>
    </body>
</html>
