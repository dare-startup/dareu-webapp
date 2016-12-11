<%-- 
    Document   : users
    Created on : Oct 27, 2016, 4:11:23 PM
    Author     : jose.rubalcaba
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/shared/resources-import.jsp"%>
        <title>Dare‹ Configurations</title>
    </head>
    <body>
        <%@include file="/shared/nav-bar.jsp" %>
        <div class="mdl-card mdl-shadow--4dp center-block main-card">
            <h3 class="text-center">Registered users</h3>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <td>Name</td>
                        <td>Email</td>
                        <td>UScore</td>
                        <td>Coins</td>
                        <td>User since</td>
                        <td>Verified</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${users.items}" var="user">
                        <tr>
                            <td>${user.name}</td>
                            <td>${user.email}</td>
                            <td>${user.uscore}</td>
                            <td>${user.coins}</td>
                            <td>${user.userSinceDate}</td>
                            <c:choose>
                                <c:when test="${user.verified}">
                                    <td><input type="checkbox" class="checkbox" checked disabled></td>
                                    </c:when>
                                    <c:otherwise>
                                    <td><input type="checkbox" class="checkbox" disabled></td>
                                    </c:otherwise>
                                </c:choose>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <nav aria-label="Page navigation" class="center">
                <ul class="pagination">
                    <li>
                        <a href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <li><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li>
                        <a href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
    </body>
</html>
