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
        <script src="${pageContext.request.contextPath}/resources/js/app/create-dare.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/js/knockout.js" type="text/javascript"></script>
        <title>Create dare</title>
    </head>
    <body>
        <%@include file="/shared/nav-bar.jsp"%>
        <div class="container">
            <div class="card main-card">
                <h4 class="center-text">Create a new dare</h4>

                <form:form action="/member/dare/create" method="POST" commandName="dare">
                    <div class="form-group short-form-group center">
                        <form:label path="name">Dare name</form:label>
                        <form:input cssClass="form-control" path="name" type="text"></form:input>
                        </div>
                        <div class="form-group short-form-group center">
                        <form:label path="description">Dare description</form:label>
                        <form:textarea cssClass="form-control" path="description"></form:textarea> 
                        </div>
                        <div class="form-group short-form-group center">
                        <form:label path="categoryId">Category</form:label>
                        <form:select path="categoryId" cssClass="form-control">
                            <form:option value="NONE" label="Select a category"></form:option>
                            <form:options items="${categories.items}" itemLabel="name" itemValue="id"></form:options>
                        </form:select>
                    </div>
                    <div class="form-group short-form-group center">
                        <form:label path="timer">Dare timeout in hours</form:label>
                        <form:input path="timer" type="number" cssClass="form-control"></form:input>
                        </div>
                        <div class="form-group short-form-group center">
                            <button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#findFriends">Find friends</button>
                        </div>
                        <input id="csrfToken" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <c:choose>
                        <c:when test="${not empty friends.items}">
                            <button type="submit" class="btn btn-info btn-lg short-form-button center">Create</button>
                        </c:when>
                        <c:otherwise>
                            <button type="submit" class="btn btn-info btn-lg short-form-button center" disabled>Create</button>
                        </c:otherwise>
                    </c:choose>
                </form:form>
            </div>
        </div>

        <!-- Find friends Modal -->
        <div class="modal fade" id="findFriends" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel">Find friends</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group center" style="width:70%;">
                            <input placeholder="Search your friends by name" id="findFriendsSearch" class="form-control" data-bind="text: searchText, value: searchText,event: { valueUpdate: 'afterkeydown'}, text: searchText">
                        </div>
                        
                        <table id="friendsTable" class="table table-hover" data-bind="visible: friendDescriptions.length > 0 ">
                                    <thead>
                                        <tr>
                                            <td class="table-data"></td>
                                            <td class="table-data">Name</td>
                                            <td class="table-data"></td>
                                        </tr>
                                    </thead>
                                    <tbody data-bind="foreach: {data: friendDescriptions, as 'desc'} ">
                                        <tr>
                                            <td>
                                                <img data-bind="attr: { src: desc.imageUrl }" width="50" height="50">
                                            </td>
                                            <td data-bind="text: desc.name"></td>
                                            <td>
                                                <button type="button" class="btn btn-success" data-bind="click: $parent.selectUser">Select</button>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                        
                        <h5 class="center-text" data-bind="visible: friendDescriptions.length === 0">No results</h5>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
