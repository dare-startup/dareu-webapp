<%-- 
    Document   : index
    Created on : Oct 26, 2016, 1:56:50 PM
    Author     : jose.rubalcaba
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/shared/resources-import.jsp"%>
        <title>Dashboard</title>
    </head>
    <body>
        <%@include file="/shared/nav-bar.jsp"%>
        <div class="container">
            <c:if test="${not empty unacceptedDare}">
                <div class="card notification-card">
                    <h5 class="center-text">You have an unaccepted dare waiting for you</h5>
                    <ul class="dare-description-list">
                        <li><b>Dare Name:</b> ${unacceptedDare.name}</li>
                        <li><b>Created by: </b>${unacceptedDare.challenger.name}</li>
                        <li><b>Dare Description:</b> ${unacceptedDare.description}</li>
                        <li><b>Creation date:</b> ${unacceptedDare.creationDate}</li>
                    </ul>
                    <form action="dare/confirm" method="POST" style="width:80%; margin:auto; padding: 2%;">
                        <input type="hidden" name="dareId" value="${unacceptedDare.id}">
                        <input id="csrfToken" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <input type="hidden" name="accepted" value="true">
                        <button class="btn btn-success btn-lg" type="submit" style="width: 90%;">Accept</button>
                    </form>
                    <form action="dare/confirm" method="POST" style="width:80%; margin:auto; padding: 2%;">
                        <input type="hidden" name="dareId" value="${unacceptedDare.id}">
                        <input id="csrfToken" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <input type="hidden" name="accepted" value="true">
                        <button class="btn btn-danger btn-lg" type="submit" style="width: 90%;">Decline</button>
                    </form>
                </div>
            </c:if> 
        </div>
    </body>
</html>