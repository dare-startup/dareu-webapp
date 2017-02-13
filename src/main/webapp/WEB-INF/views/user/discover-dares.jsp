<%-- 
    Document   : discover-dares
    Created on : Dec 12, 2016, 3:02:49 PM
    Author     : Alberto Rubalcaba <arubalcaba@24hourfit.com>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/shared/resources-import.jsp"%>
        <title>Discover dares</title>
    </head>
    <body>
        <%@include file="/shared/nav-bar.jsp"%>
        <div class="container">
            <c:choose>
                <c:when test="${not empty dares}">
                    <c:forEach items="${dares.items}" var="dare">
                        <div class="card notification-card center elevated">
                            <div class="row">
                                <div class="col-xs-2 col-md-2 col-lg-2" style="padding: 1.25rem;">
                                    <c:choose>
                                        <c:when test="${dare.challenger.profileImageAvailable}">
                                            <img class="card-img-top" width="50" height="50" src="${pageContext.request.contextPath}/dare/rest/client/profile/image?userId=${dare.challenger.id}">
                                        </c:when>
                                        <c:otherwise>
                                            <img class="card-img-top" width="50" height="50" src="${pageContext.request.contextPath}/resources/img/account.png">
                                        </c:otherwise>
                                    </c:choose>

                                </div>
                                <div class="col-xs-8 col-md-8 col-lg-8" style="padding:1.25rem;">
                                    <h4 class="card-title" style="margin:0;"><a ref="#">${dare.challenger.name}</a>  ${dare.name}</h4>
                                    <p data-toggle="tooltip" data-placement="right" title="Dare description" class="card-text">${dare.description}</p>
                                    <a href="#" class="btn btn-primary">Check challenged user response</a>
                                </div>

                                <div class="col-xs-2 col-md-2 col-lg-2" style="padding:1.25rem;">
                                    ${dare.creationDate}
                                </div>
                                
                                
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <h4>No discoverable dares available, try again later</h4>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>