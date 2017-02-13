<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
    <head>
        <%@include file="/shared/resources-import.jsp" %>
    </head>
    <body>
        <sec:authorize access="hasAuthority('ADMIN')">
            <c:redirect url="${request.getContextPath()}/admin/"/>
        </sec:authorize>
        <sec:authorize access="hasAuthority('USER')">
            <c:redirect url="${request.getContextPath()}/member/"/>
        </sec:authorize>
        <sec:authorize access="hasAuthority('SPONSOR')">
            <c:redirect url="${request.getContextPath()}/sponsor/"/>
        </sec:authorize>
        <%@include file="/shared/nav-bar.jsp" %>
        <div class="container">
            <div class="card notification-card elevated">
                <h3 class="center-text">This site is under construction right now</h3>
            </div>
        </div>
    </body>
</html>
