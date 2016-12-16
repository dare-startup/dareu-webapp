<%-- 
    Document   : create-dare
    Created on : Dec 11, 2016, 9:41:36 PM
    Author     : jose.rubalcaba
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/shared/resources-import.jsp"%>
        <title>Create dare</title>
    </head>
    <body>
        <%@include file="/shared/nav-bar.jsp"%>
        <div class="container">
            <div class="card main-card">
                <h5 class="text-center">Create a new dare</h5>
                <c:set var="friendships" value="${not empty friends.items}"/>
                <c:choose>
                    <c:when test="${friendships}">
                        <select class="selectpicker" data-live-search="true" data-max-options="4" title="Search a friend">
                            <c:forEach items="${friends.items}" var="user">

                            </c:forEach>
                        </select>

                    </c:when>
                    <c:otherwise>
                        <h5 class="text-center">Looks like you do not have any contacts yet, you can discover people from <a href="/member/discover/users">here</a></h5>
                    </c:otherwise>
                </c:choose>
                <form:form action="/member/dare/create" method="POST" commandName="dare">
                    <%--<div class="form-group">
                        <form:label path="asd">Dared friends</form:label>
                        <form:textarea path="asd"></form:textarea>
                        </div>--%>
                        <div class="form-group">
                        <form:label path="name">Dare name</form:label>
                        <form:input cssClass="form-control" path="name" type="text"></form:input>
                        </div>
                        <div class="form-group">
                        <form:label path="description">Dare description</form:label>
                        <form:textarea cssClass="form-control" path="description"></form:textarea> 
                        </div>
                        <div class="form-group">
                        <form:label path="categoryId">Category</form:label>
                        <form:select path="categoryId" cssClass="form-control">
                            <form:option value="NONE" label="Select a category"></form:option>
                            <form:options items="${categories.items}" itemLabel="name" itemValue="id"></form:options>
                        </form:select>
                    </div>
                    <div class="form-group">
                        <form:label path="timer">Dare timeout in hours</form:label>
                        <form:input path="timer" type="number" cssClass="form-control"></form:input>
                    </div>
                    <c:choose>
                        <c:when test="${friends}">
                            <form:button type="submit" cssClass="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">Create</form:button>
                        </c:when>
                        <c:otherwise>
                            <button type="submit" cssClass="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent" disabled>Create</button>
                        </c:otherwise>
                    </c:choose>
                </form:form>
            </div>
        </div>
    </body>
</html>
