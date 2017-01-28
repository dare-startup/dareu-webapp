<%-- 
    Document   : pagination
    Created on : Jan 27, 2017, 4:28:35 PM
    Author     : Alberto Rubalcaba <arubalcaba@24hourfit.com>
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty pagination}">

    <ul class="pager">
        <c:choose>
            <c:when test="${pagination.backwardEnabled}">
                <li class="previous"><a href="?pageNumber=${pagination.pageNumber - 1}"><span aria-hidden="true">&larr;</span> Previous</a></li>
                </c:when>
                <c:otherwise>
                <li class="previous disabled"><a href="#"><span aria-hidden="true">&larr;</span> Previous</a></li>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${pagination.forwardEnabled}">
                <li class="next"><a href="?pageNumber=${pagination.pageNumber + 1}">Next<span aria-hidden="true">&rarr;</span></a></li>
                </c:when>
                <c:otherwise>
                <li class="next disabled"><a href="#">Next<span aria-hidden="true">&rarr;</span></a></li>
                </c:otherwise>
            </c:choose>
    </ul>
</c:if>
