<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header class="mdl-layout__header mdl-layout__header--waterfall">
    <!-- Top row, always visible -->
    <div class="mdl-layout__header-row">
        <!-- Title -->
        <span class="mdl-layout-title">Dare‹</span>
        <div class="mdl-layout-spacer"></div>
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--expandable
             mdl-textfield--floating-label mdl-textfield--align-right">
            <label class="mdl-button mdl-js-button mdl-button--icon"
                   for="waterfall-exp">
                <i class="material-icons">search</i>
            </label>
            <div class="mdl-textfield__expandable-holder">
                <input class="mdl-textfield__input" type="text" name="sample"
                       id="waterfall-exp">
            </div>
        </div>
    </div>
    <!-- Bottom row, not visible on scroll -->
    <div class="mdl-layout__header-row">
        <div class="mdl-layout-spacer"></div>
        <!-- Navigation -->
        <nav class="mdl-navigation">
            <!-- check roles -->
            <sec:authorize access="hasAuthority('ADMIN')">
                <a class="mdl-navigation__link" href="/admin/users">Users</a>
                <a class="mdl-navigation__link" href="/admin/dares">Dares</a>
                <a class="mdl-navigation__link" href="/admin/configuration">Configurations</a>
            </sec:authorize>
            <sec:authorize access="hasAuthority('USER')">
                <a class="mdl-navigation__link" href="/member/dares">Dares</a>
                <a class="mdl-navigation__link" href="/member/discover">Discover</a>
                <a class="mdl-navigation__link" href="/member/profile">Profile</a>
            </sec:authorize>
            <sec:authorize access="hasAuthority('SPONSOR')">
                <a class="mdl-navigation__link" href="/sponsor/dares">Dares</a>
                <a class="mdl-navigation__link" href="/sponsor/souvenirs">Souvenirs</a>
            </sec:authorize>
            <sec:authorize access="isAnonymous()">
                <a class="mdl-navigation__link" href="/signin">Sign in</a>
                <a class="mdl-navigation__link" href="/signup">Sign up</a>
            </sec:authorize>
        </nav>
    </div>
</header>