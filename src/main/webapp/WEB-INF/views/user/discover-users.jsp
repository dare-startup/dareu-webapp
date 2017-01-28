<%-- 
    Document   : discover-users
    Created on : Dec 12, 2016, 2:58:33 PM
    Author     : Alberto Rubalcaba <arubalcaba@24hourfit.com>
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/shared/resources-import.jsp"%>
        <title>Discover users</title>
    </head>
    <body>
        <%@include file="/shared/nav-bar.jsp"%>
        <div class="container">
            <div class="card main-card">
                <c:if test="${not empty friendshipRegistration}">
                    <div class="alert alert-success center main-alert" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <strong>Request sent!</strong> ${friendshipRegistration.message}
                    </div>
                </c:if>
                <c:choose>
                    <c:when test="${empty users.items}">
                        <h5 class="center-text">There is no content to show right now :/ come back later</h5>
                    </c:when>
                    <c:otherwise>
                        <h5 class="center-text">Discover Users</h5>
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <td></td>
                                    <td>Name</td>
                                    <td>Dares</td>
                                    <td>Responses</td>
                                    <td>Coins</td>
                                    <td>UScore</td>
                                    <td></td>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${users.items}" var="user">
                                    <tr>
                                        <td style="padding:0;">
                                            <c:choose>
                                                <c:when test="${not empty user.imageUrl}">
                                                    <img class="img-thumbnail" width="50" height="50" src="${user.imageUrl}">
                                                </c:when>
                                                <c:otherwise>
                                                    <img class="img-thumbnail" width="50" height="50" src="${pageContext.request.contextPath}/resources/img/icon.png">
                                                </c:otherwise>
                                            </c:choose></td>
                                        <td>${user.name}</td>
                                        <td>${user.dares}</td>
                                        <td>${user.responses}</td>
                                        <td>${user.coins}</td>
                                        <td>${user.uscore}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${user.requestSent}">
                                                    <button class="btn btn-success btn-md" disabled>Request sent</button> 
                                                </c:when>
                                                <c:otherwise>
                                                    <c:choose>
                                                        <c:when test="${user.requestReceived}">

                                                            <div class="row">
                                                                <div class="col-md-4">
                                                                    <form class="form-inline" action="users/request/${user.id}">
                                                                        <input type="hidden" name="accepted" value="true">
                                                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                                                        <button type="submit" class="btn btn-success btn-md">Accept</button>
                                                                    </form>
                                                                </div>
                                                                <div class="col-md-4">
                                                                    <form class="form-inline" action="users/request/${user.id}">
                                                                        <input type="hidden" name="accepted" value="false">
                                                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                                                        <button type="submit" class="btn btn-danger btn-md">Reject</button>
                                                                    </form>  
                                                                </div>
                                                            </div>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <form action="users/request/${user.id}" method="POST">
                                                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                                                <button type="submit" class="btn btn-info btn-md">Contact</button>
                                                            </form>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:otherwise>
                                            </c:choose>    
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <c:set var="pagination" value="${paginationData}" scope="request"/>
                        <jsp:include page="/shared/pagination.jsp"/>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </body>
</html>
