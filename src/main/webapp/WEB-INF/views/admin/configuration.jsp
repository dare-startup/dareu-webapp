<%-- 
    Document   : configuration
    Created on : Oct 27, 2016, 4:09:12 PM
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
        <div class="container">
            <div class="mdl-card mdl-shadow--4dp center-block main-card">
                <h5 class="text-center">Registered dares: <b>1,000,000</b></h5>
                <h5 class="text-center">Registered users: <b>500,000</b></h5>
                <h5 class="text-center">Registered categories: <b>10</b></h5>
                <h5 class="text-center">Flagged dares: <b>17</b></h5>

            </div>
            <div class="mdl-card mdl-shadow--4dp center-block main-card">
                <!-- creates a table -->
                <c:choose>
                    <c:when test="${not empty categories.items}">
                        <h4 class="text-center">Available categories</h4>
                        <h5>Need to add a new category? <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--raised" data-target="#newCategoryModal" data-toggle="modal">Create dare category</button></h5>
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <td>Name</td>
                                    <td>Description</td>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${categories.items}" var="category">
                                    <tr>
                                        <td>${category.name}</td>
                                        <td>${category.description}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <h4 class="text-center">There are not categories yet  <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--raised" data-target="#newCategoryModal" data-toggle="modal">Create dare category</button></h4>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="modal fade" id="newCategoryModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">Create a dare category</h4>
                        </div>
                        <div class="modal-body">
                            <form:form action="/dareu/admin/dare/category" method="POST" commandName="category">
                                <div class="form-group">
                                    <form:label path="name">Name</form:label>
                                    <form:input cssClass="form-control" path="name" type="text"></form:input>
                                    </div>
                                    <div class="form-group">
                                    <form:label path="description">Description</form:label>
                                    <form:textarea cssClass="form-control" path="description"></form:textarea>
                                    </div>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                <button type="submit" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">Create</button>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
