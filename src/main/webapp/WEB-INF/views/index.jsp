<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>

    <head>
        <%@include file="/shared/resources-import.jsp" %>
    </head>

    <body>
        <sec:authorize access="hasAuthority('ADMIN')">
            <c:redirect url="${request.getContextPath()}/admin/" />
        </sec:authorize>
        <sec:authorize access="hasAuthority('USER')">
            <c:redirect url="${request.getContextPath()}/member/" />
        </sec:authorize>
        <sec:authorize access="hasAuthority('SPONSOR')">
            <c:redirect url="${request.getContextPath()}/sponsor/" />
        </sec:authorize>


        <section id="welcome-section" class="welcome-section">
            <%@include file="/shared/nav-bar.jsp" %>
            
                <div class="header-text wow lightSpeedIn container" data-wow-duration="2s">
                    <h2 class="h2 font-weight-bold">Get dared wherever you are</h2>
                    <h3>Create your dares widely, earn rewards, have fun</h3>
                    <a class="btn btn-lg btn-primary" href="#">Sign up now!</a>
                </div>
                <div class="arrow-down">
                    <a data-scroll href="#dare-section"><i class="material-icons">keyboard_arrow_down</i></a>
                </div>
            
        </section>
        <!-- Dare section (orange) -->
        <section class="dare-section py-3" id="dare-section">
            <div class="container">
                    <div class="col-xs-12 col-md-8 col-xl-9 my-4">
                        <h2 class="h3 text-xs-center text-md-left font-weight-bold">Dare everyone you know</h2>
                        <p class="my-2 text-xs-center text-md-left">Get to know people, dare them and have lot of fun</p>
                        <p class="text-xs-center text-md-left">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Velit enim saepe pariatur neque, nihil accusamus minima libero quasi eaque officiis dolorum possimus vitae, placeat explicabo ducimus quas voluptatibus quam veniam?</p>
                    </div>
                    <div class="col-xs-12 col-md-4 col-xl-3 my-4 wow tada" data-wow-duration="2s">
                        <h5 class="h4 text-center">Available on</h5>
                        <a href="android" data-toggle="tooltip" data-placenement="top" title="This mobile version is still on development"><img class="my-3" src="${pageContext.request.contextPath}/resources/img/google_play.svg" alt="Play-Services logo"></a>
                    </div>
                </div>
            </div>
        </section>

        <!-- How does it works -->
        <main class="mechanism-section py-2">
            <div class="container">
                <h2 class="h2 my-3 text-xs-center text-md-center font-weight-bold wow pulse" data-wow-duration="2s">How <span>DareU</span> works?</h2>
                <p class="text-xs-center text-md-center wow pulse" data-wow-duration="2s">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Debitis ratione distinctio ipsam animi placeat voluptas</p>
                <div class="row">
                    <div class="col-6">
                        <ul class="text-xs-center text-sm-center text-lg-center mechanism-step">
                            <li class="wow bounceInLeft" data-wow-delay=".3s">
                                <i class="material-icons right">people</i>
                                <div class="step-container ">
                                    <h4 class="font-weight-bold my-2">Add people</h4>
                                    <p class="hidden-md-down">Some subtitle long description out here look at me</p>
                                </div>

                            </li>
                            <li class="wow bounceInLeft" data-wow-delay=".7s">
                                <i class="material-icons right">file_upload</i>
                                <div class="step-container">
                                    <h4 class="font-weight-bold my-2">Upload</h4>
                                    <p class="hidden-md-down">Some subtitle long description out here look at me</p>
                                </div>

                            </li>
                            <li class="wow bounceInLeft" data-wow-delay="1.1s">
                                <i class="material-icons right">comment</i>
                                <div class="step-container">
                                    <h4 class="font-weight-bold my-2">Comment</h4>
                                    <p class="hidden-md-down">Some subtitle long description out here look at me</p>
                                </div>

                            </li>
                        </ul>
                    </div>

                    <div class="col-6">
                        <ul class="text-xs-center text-lg-center mechanism-step">
                            <li class="wow bounceInRight" data-wow-delay=".5s">
                                <img src="${pageContext.request.contextPath}/resources/img/dareu.svg" alt="">
                                <div class="step-container">
                                    <h4 class="font-weight-bold my-2 text-xs-center text-md-center  text-sm-center">Dare them!</h4>
                                    <p class="hidden-md-down">Some subtitle long description out here look at me</p>
                                </div>
                            </li>
                            <li class="wow bounceInRight" data-wow-delay=".9s">
                                <i class="material-icons left">thumb_up</i>
                                <div class="step-container">
                                    <h4 class="text-xs-center text-md-center  text-sm-center font-weight-bold my-2">Get thumbs</h4>
                                    <p class="hidden-md-down">Some subtitle long description out here look at me</p>
                                </div>

                            </li>
                            <li class="wow bounceInRight" data-wow-delay="1.3s">
                                <i class="material-icons  left">add_shopping_cart</i>
                                <div class="step-container">
                                    <h4 class="text-xs-center text-md-center  text-sm-center font-weight-bold my-2">Earn prizes!</h4>
                                    <p class="hidden-md-down">Some subtitle long description out here look at me</p>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <!--<div class="col-xs-6">
                    
                </div>
                <div class="col-xs-6">
                    
                </div>-->
                </div>
            </div>


        </main>

        <!-- Developers section -->
        <section class="developers-section py-2">
            <h2 class="my-3 text-xs-center text-md-center text-lg-center font-weight-bold">Who's working on this?</h2>
            <div class="row">


                <div class="col-md-6 developer" >
                    <img src="${pageContext.request.contextPath}/resources/img/alberto.svg" alt="">
                    <h4><a href="#">Alberto Rubalcaba</a></h4>
                    <p class="hidden-sm-down ">Skateboarder, software engineer and beer lover.</p>
                </div>

                <div class="col-md-6 developer" >
                    <img src="${pageContext.request.contextPath}/resources/img/hector.svg" alt="">
                    <h4><a href="#">Hector Mendoza</a></h4>
                    <p class="hidden-sm-down">Software engineer, tech, life and world lover.</p>
                </div>
            </div>
        </section>

        <a data-scroll href="#welcome-section" class="go-up"><i class="material-icons">keyboard_arrow_up</i></a>

    </body>
</html>
