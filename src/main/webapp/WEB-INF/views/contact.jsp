<%-- 
    Document   : contact
    Created on : Feb 26, 2017, 6:34:10 PM
    Author     : Alberto Rubalcaba <arubalcaba@24hourfit.com>
--%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/shared/resources-import.jsp"%>
        <title>Contact us</title>
    </head>
    <body>
        <section>
            <%@include file="/shared/nav-bar.jsp"%>  
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 col-md-4 col-lg-6 contact-happy">
                        <h4 class="h4 text-xs-center font-weight-bold">Want to ask something personally?</h4>
                        <img src="${pageContext.request.contextPath}/resources/img/so-happy.png">
                    </div>
                    <div class="col-xs-12 col-md-8 col-lg-6">
                        <c:if test="${not empty sentRequest and not empty sentRequest.message}">
                            <div class="alert alert-warning alert-dismissible fade show">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <strong>Contact message sent</strong> We sent you contact message, wait for our email answer in a bit :).
                            </div>
                        </c:if>
                        <form class="contact-form" action="contact" method="POST">
                            <div class="form-group">
                                <label for="inputName" class="form-control-label">Name</label>
                                <input name='name' type="text" class="form-control" id="inputName" placeholder="Your name">
                            </div>
                            <div class="form-group">
                                <label for="inputEmail" class="form-control-label">Email</label>
                                <input name="email" type="email" class="form-control" id="inputEmail" placeholder="Your email">
                            </div>
                            <div class="form-group">
                                <label for="inputComment" class="form-control-label">Comment/Message</label>
                                <textarea name='comment' lines="5" class="form-control" id="inputComment" placeholder="Your comment/message"></textarea>
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

                            <button type="submit" class="btn btn-primary btn-lg">Contact now</button>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
