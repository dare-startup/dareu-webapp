<%-- 
    Document   : upload-dare-response
    Created on : Feb 9, 2017, 7:28:31 PM
    Author     : Alberto Rubalcaba <arubalcaba@24hourfit.com>
--%>

<!DOCTYPE HTML>
<html>
    <head>
        <%@include file="/shared/resources-import.jsp" %>
    </head>
    <body>
        <%@include file="/shared/nav-bar.jsp" %>
        <div class="container">
            <div class="card notification-card elevated">
                <h5 class="center-text">Upload your dare response</h5>
                <form action="upload?${_csrf.parameterName}=${_csrf.token}" method="POST" enctype="multipart/form-data">
                    <div class="form-group short-form-group center">
                        <label for="commentInput">Comment</label>
                        <textarea class="form-control" id="commentInput"  placeholder="Comment" name="comment"></textarea>
                    </div>
                    <input type="hidden" name="dareId" value="${dareId}">
                    <div class="form-group short-form-group center">
                        <label for="fileInput">Select your dare response</label>
                        <input class="btn btn-secondary btn-file" id="fileInput" type="file" name="file" accept=".mp4, .vga">
                    </div>
                    <button type="submit" class="btn btn-info btn-lg center short-form-button elevated">Upload dare response</button>
                </form>
            </div>
        </div>
    </body>
</html>
