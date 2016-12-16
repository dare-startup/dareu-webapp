<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
    <head>
        <%@include file="/shared/resources-import.jsp" %>
    </head>
    <body>
        <sec:authorize access="hasAuthority('ADMIN')">
            <c:redirect url="/admin/"/>
        </sec:authorize>
        <sec:authorize access="hasAuthority('USER')">
            <c:redirect url="/member/"/>
        </sec:authorize>
        <sec:authorize access="hasAuthority('SPONSOR')">
            <c:redirect url="/sponsor/"/>
        </sec:authorize>
        <%@include file="/shared/nav-bar.jsp" %>
        <div class="container">
            <div class="card main-card">
                <h3 class="center-text">This site is under construction right now :) CI is complete.. </h3>
            </div>
        </div>
    </body>
</html>
