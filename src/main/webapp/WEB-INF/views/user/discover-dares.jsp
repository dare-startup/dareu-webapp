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
            <div class="card main-card">
                <c:choose>
                    <c:when test="${empty dares}">
                        <h5 class="center-text">There are no available dares right now :( Come back later</h5>
                    </c:when>
                    <c:otherwise>
                        <h5 class="center-text">Discover some dares</h5>
                        <table class="table table-hovered dareu-table">
                            <thead>
                                <tr>
                                    <td>Name</td>
                                    <td>Description</td>
                                    <td>Category</td>
                                    <td>Creation date</td>
                                    <td>Challenger</td>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${dares.items}" var="dare">
                                    <tr>
                                        <td>${dare.name}</td>
                                        <td>${dare.description}</td>
                                        <td>${dare.category}</td>
                                        <td>${dare.creationDate}</td>
                                        <td>${dare.challenger.name}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                       </table>
                        
                        <c:set var="pagination" value="${paginationData}" scope="request"/>
                        <jsp:include page="/shared/pagination.jsp" flush="false"/>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </body>
</html>