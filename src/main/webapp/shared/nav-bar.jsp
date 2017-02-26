<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%--<nav class="navbar navbar-dark bg-inverse elevated">
<a class="navbar-brand" style="padding-left: 13rem;" href="/dareu/">
    <img src="/dareu/resources/img/icon.png" width="30" height="30" class="d-inline-block align-top" alt="">
</a>
<ul class="nav navbar-nav float-sm-right" style="padding-right: 13rem;">
    <sec:authorize access="isAnonymous()">
        <li class="nav-item">
            <a class="nav-link" href="/dareu/signin">Sign in</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/dareu/signup">Sign up</a>
        </li>

        </sec:authorize>
        <sec:authorize access="hasAuthority('ADMIN')">
            <li class="nav-item">
                <a class="nav-link" href="/dareu/admin/users/">Users</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/dareu/admin/dares">Dares</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/dareu/admin/configuration">Configuration</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="http://dareu.com" id="moreContent" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">More</a>
                <div class="dropdown-menu" aria-labelledby="moreContent">
                    <a class="dropdown-item" href="#">Settings</a>
                    <form action="/dareu/security/signout" method="POST">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <button type="submit" class="dropdown-item">Sign out</button>
                    </form>
                    <a class="dropdown-item" href="#">Something else here</a>
                </div>
            </li>
        </sec:authorize>
        <sec:authorize access="hasAuthority('USER')">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="http://dareu.com" id="discoverDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Discover</a>
                <div class="dropdown-menu" aria-labelledby="discoverDropdown">
                    <a class="dropdown-item" href="/dareu/member/discover/users">Users</a>
                    <a class="dropdown-item" href="/dareu/member/discover/dares">Dares</a>
                    <!--<a class="dropdown-item" href="/dareu/member/discover/responses">Responses</a>-->
                    <!--<a class="dropdown-item" href="/dareu/member/discover/trending">Trending</a>-->
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/dareu/member/hot">Hot</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/dareu/member/anchored">Anchored</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="http://dareu.com" id="createDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dares</a>
                <div class="dropdown-menu" aria-labelledby="createDropdown">
                    <a class="dropdown-item" href="/dareu/member/dare/create">Create Dare</a>
                    <a class="dropdown-item" href="/dareu/member/dare/active">Currently active dare</a>
                    <!--<a class="dropdown-item" href="/dareu/member/dare/unacceptedDares">Unaccepted dares</a>-->
                    <!--<a class="dropdown-item" href="/dareu/member/dare/createdDares">Created Dares</a>-->
                </div>
            </li>
            
            
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="http://dareu.com" id="discoverDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Settings</a>
                <div class="dropdown-menu" aria-labelledby="discoverDropdown">
                    <a class="dropdown-item" href="/dareu/member/settings">Settings</a>
                    <a class="dropdown-item" href="/dareu/member/profile/">Profile</a>
                    <form action="/dareu/security/signout" method="POST">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <button type="submit" class="dropdown-item">Sign out</button>
                    </form>
                </div>
            </li>
        </sec:authorize>
        <sec:authorize access="hasAuthority('SPONSOR')">
            <li class="nav-item">
            <a class="nav-link" href="/sponsor/dares">Dares</a>
        </li>
                <li><a href="/sponsor/dares">Dares</a></li>
                <li><a href="/sponsor/souvenirs">Souvenirs</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">More <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Settings</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="/security/signout">Sign out</a></li>
                    </ul>
                </li>
            </sec:authorize>
    </ul>
</div>
</nav>--%>
<header class="header navbar-fixed-top" role="banner">
    <div class="container">


        <a href="/dareu/" class="logo">
            <img src="/dareu//resources/img/white-icon.svg" alt="Logo">
        </a>
        <!-- menu buttons -->
        <button type="button" class="search-button menu-button" data-toggle="collapse" data-target="#search-block" aria-expanded="false">
            <i class="material-icons ripple">search</i>
        </button>
        <button type="button" class="menu-button hidden-md-up" data-toggle="collapse" data-target="#principal-menu" aria-expanded="false">
            <i class="material-icons ripple">menu</i>
        </button>

        <!-- search block -->
        <form action="#" id="search-block" class="collapse">
            <div class="search-container-block">
                <input type="text" placeholder="Search...">
                <input type="submit" value="Search">
            </div>
        </form>

        <nav id="principal-menu" class="collapse">
            <ul>
                <sec:authorize access="isAnonymous()">
                    <li class="ripple"><a href="/dareu/signin">Sign in</a></li>
                    <li class="ripple"><a href="/dareu/signup"">Sign up</a></li>
                    <li class="ripple"><a href="/dareu/contact">Contact</a></li>
                    <li class="ripple"><a href="/dareu/about">About</a></li>

                </sec:authorize>
                <sec:authorize access="hasAuthority('USER')">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="http://dareu.com" id="discoverDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Discover</a>
                        <div class="dropdown-menu" aria-labelledby="discoverDropdown">
                            <a class="ripple dropdown-item font-weight-bold" href="/dareu/member/discover/users">Users</a>
                            <a class="ripple dropdown-item font-weight-bold" href="/dareu/member/discover/dares">Dares</a>
                            <!--<a class="dropdown-item" href="/dareu/member/discover/responses">Responses</a>-->
                            <!--<a class="dropdown-item" href="/dareu/member/discover/trending">Trending</a>-->
                        </div>
                    </li>
                    <li class="ripple">
                        <a class="nav-link" href="/dareu/member/hot">Hot</a>
                    </li>
                    <li class="ripple">
                        <a href="/dareu/member/anchored">Anchored</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="http://dareu.com" id="createDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dares</a>
                        <div class="dropdown-menu" aria-labelledby="createDropdown">
                            <a class="ripple dropdown-item font-weight-bold" href="/dareu/member/dare/create">Create Dare</a>
                            <a class="ripple dropdown-item font-weight-bold" href="/dareu/member/dare/active">Currently active dare</a>
                            <!--<a class="dropdown-item" href="/dareu/member/dare/unacceptedDares">Unaccepted dares</a>-->
                            <!--<a class="dropdown-item" href="/dareu/member/dare/createdDares">Created Dares</a>-->
                        </div>
                    </li>


                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="http://dareu.com" id="discoverDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Settings</a>
                        <div class="dropdown-menu" aria-labelledby="discoverDropdown">
                            <a class="dropdown-item font-weight-bold" href="/dareu/member/settings">Settings</a>
                            <a class="dropdown-item font-weight-bold" href="/dareu/member/profile/">Profile</a>
                            <form id="signout-form" action="/dareu/security/signout" method="POST">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                <button type="submit" class="dropdown-item center-text font-weight-bold">Sign out</button>
                            </form>
                        </div>
                    </li>
                </sec:authorize>
            </ul>
        </nav>
    </div>
</header>
