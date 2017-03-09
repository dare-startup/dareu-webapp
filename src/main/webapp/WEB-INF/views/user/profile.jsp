<%-- 
    Document   : profile
    Created on : Feb 12, 2017, 7:49:51 PM
    Author     : Alberto Rubalcaba <arubalcaba@24hourfit.com>
--%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/shared/resources-import.jsp"%>
        <script src="${pageContext.request.contextPath}/resources/js/knockout.js" type="text/javascript"></script>
        <title></title>
    </head>
    <body>
        <%@include file="/shared/nav-bar.jsp"%>
        <div class="container">
            <c:if test="${not empty message}">
                <div class="alert alert-success alert-card">
                    <strong>Message </strong> ${message}
                </div>
            </c:if>
            <h5 style="margin-left: 20% !important; margin-bottom: 0;">Personal data</h5>
            <div class="card notification-card elevated">
                <img class="card-img-top center rounded-circle" width="150" height="150" src="${profile.imageUrl}">
                <h5 class="center-text">${profile.name}</h5>
                <p class="center-text">${profile.email} <span><button type="button" data-toggle="modal" data-target="#changeEmailModal" class="btn btn-secondary material-button"><i class="material-icons">edit</i></button></span></p>
                <div class="row" style="margin: 0 auto;">
                    <div class="col-md-6 col-sm-6 col-lg-6">
                        <p class="float-md-right" style="margin: 0 !important;"><strong>Coins: </strong>${profile.coins}</p>
                    </div>
                    <div class="col-md-6 col-sm-6 col-lg-6">
                        <p class="float-md-left" style="margin: 0 !important;"><strong>Score: </strong>${profile.uscore}</p>
                    </div>
                </div>
                <p class="center-text"><strong>User since: </strong>${profile.userSinceDate}</p>
            </div>

            <h5 style="margin-left: 20% !important; margin-bottom: 0;">created dares</h5>
            <div class="card notification-card elevated">
                <c:choose>
                    <c:when test="${not empty profile.createdDares.items}">
                        <div id="daresCarouselIndicators" class="carousel slide" data-ride="carousel" style="height: 25rem;">
                            <ol class="carousel-indicators">
                                <c:forEach items="${profile.createdDares.items}" var="dare" varStatus="i">
                                    <c:if test="${i.index == 0}">
                                        <li data-target="#daresCarouselIndicators" data-slide-to="${i.index}" class="active"></li>
                                        </c:if>
                                    <li data-target="#daresCarouselIndicators" data-slide-to="${i.index}"></li>
                                    </c:forEach>
                            </ol>
                            <div class="carousel-inner" role="listbox" style="height: 100%;">
                                <c:forEach items="${profile.createdDares.items}" var="dare" varStatus="i">
                                    <c:if test="${i.index == 0}">
                                        <div class="carousel-item active " style="height: 100%;">
                                            <img class="d-block img-fluid center" width="100" height="100" src="${pageContext.request.contextPath}/resources/img/account.png" alt="First slide"></div>
                                        </div>
                                    </c:if>
                                    <div class="carousel-item" style="height: 100%;">
                                        <img class="d-block img-fluid center" width="100" height="100" src="${pageContext.request.contextPath}/resources/img/account.png" alt="First slide"></div>
                                    </div>
                                </c:forEach>
                            </div>
                            <a class="carousel-control-prev" href="#daresCarouselIndicators" role="button" data-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="sr-only">Previous</span>
                            </a>
                            <a class="carousel-control-next" href="#daresCarouselIndicators" role="button" data-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="sr-only">Next</span>
                            </a>
                        </div>
                    </c:when>
                    <c:otherwise>

                    </c:otherwise>
                </c:choose>
            </div>

            <h5 style="margin-left: 20% !important; margin-bottom: 0;">created dares</h5>
            <div class="card notification-card elevated">
                <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel" style="height: 25rem;">
                    <ol class="carousel-indicators">
                        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                    </ol>
                    <div class="carousel-inner" role="listbox">
                        <div class="carousel-item active">
                            <img class="d-block img-fluid" src="${pageContext.request.contextPath}/resources/img/account.png" alt="First slide">
                        </div>
                    </div>
                    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>
        </div>

        <!-- Change email modal -->
        <div class="modal fade" id="changeEmailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel">Change email address</h4>
                    </div>
                    <div class="modal-body">
                        <form action="${pageContext.request.contextPath}/member/profile/changeEmail" method="POST">
                            <div class="form-group center" style="width: 70%;margin-bottom: 1.25rem;">
                                <input name="newEmail" type="email" class="form-control" placeholder="Your new email address">
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            <div class="form-group center" style="width: 70%;margin-bottom: 1.25rem;">
                                <button type="submit" class="btn btn-info elevated center btn-block">Save</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
