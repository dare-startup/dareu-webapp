<%-- 
    Document   : index
    Created on : Oct 26, 2016, 1:56:50 PM
    Author     : jose.rubalcaba
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/shared/resources-import.jsp"%>
        <script>
            $(function () {
                $('[data-toggle="tooltip"]').tooltip()
            });
        </script>
        <title>Dashboard</title>
    </head>
    <body>
        <%@include file="/shared/nav-bar.jsp"%>
        <div class="container">
            <c:if test="${not empty unacceptedDare}">
                <div class="row">
                    <h5 style="margin-left: 20% !important; margin-bottom: 0;">Awaiting dare</h5>
                    <div class="card notification-card center text-center elevated">
                    <div class="row">
                        <div class="col-xs-3 col-md-3 col-lg-3" style="padding: 1.25rem 1.25rem 0 1.25rem;">
                            <img class="card-img-top center" width="100" height="100" src="${unacceptedDare.challenger.imageUrl}">ed
                        </div>
                        <div class="col-xs-8 col-md-8 col-lg-8" style="padding:1.25rem 0 0 0;">
                            <h4 class="card-title" style="margin:0;"><a ref="#">${unacceptedDare.challenger.name}</a></h4>
                            <h4 style="margin:0;">${unacceptedDare.name}</h4>
                            <p class="card-text" style="margin:0;margin: 8px 0 8px 0;">${unacceptedDare.description}</p>
                            <p class="card-text">Creation date ${unacceptedDare.creationDate}</p>
                            <h5 class="card-text">This dare must be completed within ${unacceptedDare.timer} hours</h5>
                            <form action="dare/confirm" method="POST">
                                <input type="hidden" name="dareId" value="${unacceptedDare.id}">
                                <input id="csrfToken" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                <input type="hidden" name="accepted" value="true">
                                <button data-toggle="tooltip" data-placement="bottom" title="Accept this dare to start ticking" class="btn btn-primary btn-log" type="submit" style="width: 100%;">Accept</button>
                            </form>
                        </div>

                        <div class="col-xs-1 col-md-1 col-lg-1 center-text" style="padding: 1.25rem 0 0 0">
                            <form action="dare/confirm" method="POST">
                                <input type="hidden" name="dareId" value="${unacceptedDare.id}">
                                <input id="csrfToken" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                <input type="hidden" name="accepted" value="true">
                                <button title="The dare will be declined and the next one will be loaded, once this is declined, you will be able to receive different ones" data-placement="left" data-toggle="tooltip" class="btn btn-secondary material-button" type="submit" style=""><i class="material-icons md-18 red">clear</i></button>
                            </form>
                        </div>


                    </div>
                </div>
                </div>
            </c:if> 
        </div>
    </body>
</html>