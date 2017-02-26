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
        <script src="${pageContext.request.contextPath}/resources/js/knockout.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/js/app/create-dare.js" type="text/javascript"></script>
        <title>Create dare</title>
    </head>
    <body>
        <section class="new-dare-section">
            <%@include file="/shared/nav-bar.jsp"%>
            <div class="container">
                <div class="new-dare-card">
                    <h4 class="text-xs-center text-sm-center text-md-center text-lg-center font-weight-bold">Create a new dare</h4>

                    <form:form action="/dareu/member/dare/create" method="POST" commandName="dare">
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
                                <button type="button" data-bind="visible: !selectedUser()" class="btn btn-secondary ripple" data-toggle="modal" data-target="#findFriends">Find friends</button>
                                <div class="alert alert-info" data-bind="visible: selectedUser()"> 
                                    <h5>Selected user</h5>
                                    <p data-bind="text: selectedUserName"></p>
                                    <button id="removeSelectedUserButton" data-bind="click: removeSelectedUser" type="button" class="btn btn-danger btn-sm">Remove</button>
                                </div>
                                <input name="friendId" type="hidden" data-bind="value: selectedUserId"/>
                            </div>
                            <input id="csrfToken" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <c:choose>
                            <c:when test="${not empty friends.items}">
                                <button type="submit" class="btn btn-primary btn-lg btn-block ripple">Create</button>
                            </c:when>
                            <c:otherwise>
                                <button type="submit" class="btn btn-primary btn-lg btn-block" disabled>Create</button>
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
                            <h4 class="modal-title font-weight-bold" id="myModalLabel">Find friends</h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group center">
                                <input placeholder="Search your friends by name" id="findFriendsSearch" class="form-control" data-bind="textInput: searchText, value: searchText,event: { valueUpdate: 'afterkeydown'}, text: searchText">
                            </div>

                            <table id="friendsTable" class="table table-hover" data-bind="visible: friendDescriptions().length !== 0">
                                <thead>
                                    <tr>
                                        <td class="table-data"></td>
                                        <td class="table-data"></td>
                                        <td class="table-data"></td>
                                    </tr>
                                </thead>
                                <tbody data-bind="foreach: friendDescriptions ">
                                    <tr>
                                        <td class="table-data">
                                            <img class="image-profile" data-bind="attr: { src: imageUrl, id: id}" width="50" height="50">
                                        </td>
                                        <td class="table-data" data-bind="text: name"></td>
                                        <td class="table-data">
                                            <button type="button" class="btn btn-primary" data-bind="click: $parent.selectUser">Select</button>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>

                            <h5 class="center-text" data-bind="visible: friendDescriptions().length === 0">Provide a search key</h5>
                        </div>
                    </div>
                </div>
            </div>
        </section>


    </body>
</html>
