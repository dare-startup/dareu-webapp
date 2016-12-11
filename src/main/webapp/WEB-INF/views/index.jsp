<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
    <head>
        <%@include file="/shared/resources-import.jsp" %>
    </head>
    <body>
        <sec:authorize access="hasAuthority('ADMIN')">
            <c:redirect url="/admin/index"/>
        </sec:authorize>
        <sec:authorize access="hasAuthority('USER')">
            <c:redirect url="/member/index"/>
        </sec:authorize>
        <sec:authorize access="hasAuthority('SPONSOR')">
            <c:redirect url="/sponsor/index"/>
        </sec:authorize>
        <%@include file="/shared/nav-bar.jsp" %>
        <div class="container">
            <div class="mdl-card mdl-shadow--4dp center-block main-card">
                <h3 class="text-center">This site is under construction right now :) </h3>
            </div>
        </div>
    </body>
</html>
