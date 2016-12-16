<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<nav class="navbar navbar-dark bg-inverse">
    <a class="navbar-brand" style="padding-left: 13rem;" href="/">
        <img src="/resources/img/icon.png" width="30" height="30" class="d-inline-block align-top" alt="">
        Dare‹
    </a>
    <ul class="nav navbar-nav float-sm-right" style="padding-right: 13rem;">
        <sec:authorize access="isAnonymous()">
            <li class="nav-item">
                <a class="nav-link" href="/signin">Sign in</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/signup">Sign up</a>
            </li>

        </sec:authorize>
        <sec:authorize access="hasAuthority('ADMIN')">
            <li class="nav-item">
                <a class="nav-link" href="/admin/users/">Users</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/admin/dares">Dares</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/admin/configuration">Configuration</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="http://dareu.com" id="moreContent" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">More</a>
                <div class="dropdown-menu" aria-labelledby="moreContent">
                    <a class="dropdown-item" href="#">Settings</a>
                    <a class="dropdown-item" href="#">Sign out</a>
                    <a class="dropdown-item" href="#">Something else here</a>
                </div>
            </li>
        </sec:authorize>
        <sec:authorize access="hasAuthority('USER')">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="http://dareu.com" id="createDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Create</a>
                <div class="dropdown-menu" aria-labelledby="createDropdown">
                    <a class="dropdown-item" href="/member/dare/create">Dare</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/member/anchored">Anchored</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="http://dareu.com" id="discoverDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Discover</a>
                <div class="dropdown-menu" aria-labelledby="discoverDropdown">
                    <a class="dropdown-item" href="/member/discover/users">Users</a>
                    <a class="dropdown-item" href="/member/discover/dares">Dares</a>
                    <a class="dropdown-item" href="/member/discover/responses">Responses</a>
                    <a class="dropdown-item" href="/member/discover/trending">Trending</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/member/hot">Hot</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="http://dareu.com" id="discoverDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Discover</a>
                <div class="dropdown-menu" aria-labelledby="discoverDropdown">
                    <a class="dropdown-item" href="/member/discover/users">Users</a>
                    <a class="dropdown-item" href="/member/discover/dares">Dares</a>
                    <a class="dropdown-item" href="/member/discover/responses">Responses</a>
                    <a class="dropdown-item" href="/member/discover/trending">Trending</a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="http://dareu.com" id="discoverDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Settings</a>
                <div class="dropdown-menu" aria-labelledby="discoverDropdown">
                    <a class="dropdown-item" href="/member/discover/users">Settings</a>
                    <a class="dropdown-item" href="/member/profile/">Profile</a>
                    <a class="dropdown-item" href="#">Sign out</a>
                </div>
            </li>
        </sec:authorize>
        <%--<sec:authorize access="hasAuthority('SPONSOR')">
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
            </sec:authorize>--%>
    </ul>
    <%--<div class="container-fluid">
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
            </ul>
            <ul class="nav navbar-nav navbar-right">
                

                

                <sec:authorize access="isAnonymous()">
                    <li><a href="/signin">Sign in</a></li>
                    <li><a href="/signup">Sign up</a></li>
                    </sec:authorize>
                <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                  <ul class="dropdown-menu">
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Something else here</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a href="#">Separated link</a></li>
                  </ul>
                </li>
            </ul>
        </div>--%>
</div>
</nav>