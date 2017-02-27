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
                    <div class="col-xs-12 col-md-4 contact-happy">
                        <h4 class="h44 font-weight-bold">Want to ask something personally?</h4>
                        <img src="${pageContext.request.contextPath}/resources/img/so-happy.png">
                    </div>
                    <div class="col-xs-12 col-md-8">
                        <form class="contact-form">
                            <div class="form-group">
                                <label for="inputName" class="form-control-label">Name</label>
                                <input type="text" class="form-control" id="inputName" placeholder="Your name">
                            </div>
                            <div class="form-group">
                                <label for="inputEmail" class="form-control-label">Email</label>
                                <input type="email" class="form-control" id="inputEmail" placeholder="Your email">
                            </div>
                            <div class="form-group">
                                <label for="inputComment" class="form-control-label">Comment/Message</label>
                                <textarea lines="5" class="form-control" id="inputComment" placeholder="Your comment/message"></textarea>
                            </div>
                            
                            <button type="submit" class="btn btn-primary">Contact now</button>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
