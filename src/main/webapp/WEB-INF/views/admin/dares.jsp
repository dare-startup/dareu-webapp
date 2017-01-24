<%-- 
    Document   : dares
    Created on : Oct 27, 2016, 4:10:17 PM
    Author     : jose.rubalcaba
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/shared/resources-import.jsp"%>
        <title>Dares</title>
    </head>
    <body>
        <%@include file="/shared/nav-bar.jsp" %>
        <div class="container">
            <div class="mdl-card mdl-shadow--4dp center-block main-card">
                <h4 class="text-center">Unapproved dares</h4>
                <c:choose>
                    <c:when test="${not empty dares.items}">
                        <table class="table table-hovered">
                            <thead>
                                <tr>
                                    <td>Name</td>
                                    <td>Description</td>
                                    <td>Category</td>
                                    <td>Dare Timeout</td>
                                    <td>Creation date</td>
                                    <td>Action</td>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${dares.items}" var="dare">
                                    <tr>
                                        <td>${dare.name}</td>
                                        <td>${dare.description}</td>
                                        <td>${dare.category}</td>
                                        <td>${dare.estimatedDareTime}</td>
                                        <td>${dare.creationDate}</td>
                                        <td>
                                            <button type="submit" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent">Approve</button>
                                            <button type="submit" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent">Unapprove</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <h5>There are no unapproved dares right now, try again later :)</h5>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="mdl-card mdl-shadow--4dp center-block main-card">
                <h4 class="text-center">Flagged dares</h4>
            </div>
        </div>
    </body>
</html>