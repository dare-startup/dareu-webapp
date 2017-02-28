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
        <section class="">
            <%@include file="/shared/nav-bar.jsp" %>
            <div class="container">
                <c:choose>
                    <c:when test="${not empty users}">

                        <h4 class="h4 mb-4 font-weight-bold text-xs-center text-sm-center text-md-center">Discover users to dare</h4>
                        <%-- Create cards --%>
                        <c:forEach items="${users.items}" var="user" varStatus="i">
                            <div class="media user-card my-3">
                                <c:choose>
                                    <c:when test="${user.profileImageAvailable}">
                                    <img class="d-flex align-self-center mx-3" width="60" src="${pageContext.request.contextPath}/rest/client/profile/image?userId=${user.id}">
                                </c:when>
                                <c:otherwise>
                                    <img class="d-flex align-self-center mx-3" width="60" src="${pageContext.request.contextPath}/resources/img/account.svg">
                                </c:otherwise>
                                </c:choose>
                                <div class="media-body py-3 user-card-body">
                                    <div class="user-card-menu">
                                        <h5 class="h5 mt-0 text-xs-start font-weight-bold mb-0">${user.name}</h5>
                                        <c:if test="${user.requestReceived}">
                                            <i class="material-icons">info</i>
                                        </c:if>
                                        <div class="dropdown mr-3 menu-dropdown">
                                            <button class="btn btn-secondary dropdown-toggle ripple"
                                                    type="button" id="dropdownMenu1" data-toggle="dropdown"
                                                    aria-haspopup="true" aria-expanded="false">
                                                <i class="material-icons">more_vert</i>
                                            </button>
                                            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenu1">
                                                <c:choose>
                                                    <c:when test="${user.requestSent}">
                                                        <button type="button" class="dropdown-item btn btn-info">A connection request has been set</button>
                                                    </c:when>
                                                    <c:when test="${user.requestReceived}">
                                                        <form class="dropdown-item form-inline" action="users/request/${user.id}">
                                                            <input type="hidden" name="accepted" value="true">
                                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                                            <button type="submit" class="btn btn-primary btn-md btn-block">Accept</button>
                                                        </form>
                                                        <form class="dropdown-item form-inline" action="users/request/${user.id}">
                                                            <input type="hidden" name="accepted" value="false">
                                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                                            <button type="submit" class="btn btn-info  btn-md btn-block">Reject</button>
                                                        </form>  
                                                    </c:when>
                                                    <c:otherwise>
                                                        <form class="dropdown-item" action="users/request/${user.id}" method="POST">
                                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                                            <button type="submit" class="btn btn-info btn-md btn-block">Contact</button>
                                                        </form>
                                                    </c:otherwise>
                                                </c:choose>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="user-card-data">
                                            <p class="m-0 text-xs-start">
                                                <img src="${pageContext.request.contextPath}/resources/img/dareu.svg" class="mr-1"> <span>${user.dares}</span> <span>dares      </span>
                                                <img src="${pageContext.request.contextPath}/resources/img/video_upload.svg" class="mx-1"> <span>${user.responses}</span> <span>uploads </span>
                                                <img class="hidden-sm-down mx-1" src="${pageContext.request.contextPath}/resources/img/score.svg"> <span class="hidden-sm-down">${user.uscore}</span><span class="hidden-sm-down">points</span>
                                            </p>
                                        </div>

                                    </div>
                                </div>
                        </c:forEach> 
                    </c:when>
                    <c:otherwise>
                        <%-- No users detected --%>
                        <h4 class="h4 font-weight-bold text-xs-center">There are no users right now, please, try again later</h4>
                    </c:otherwise>
                </c:choose>
            </div>
        </section>
    </body>
</html>
