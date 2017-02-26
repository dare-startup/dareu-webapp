<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header class="header navbar-fixed-top" role="banner">
    <div class="container">


        <a href="/dareu/" class="logo">
            <img src="/dareu//resources/img/white-icon.svg" alt="Logo">
        </a>
        <!-- menu buttons -->
        <button type="button" class="search-button menu-button" data-toggle="collapse" data-target="#search-block" aria-expanded="false">
            <i class="material-icons">search</i>
        </button>

        <!-- search block -->
        <form action="#" id="search-block" class="collapse">
            <div class="search-container-block">
                <input type="text" placeholder="Search...">
                <input type="submit" value="Search">
            </div>
        </form>
    </div>
</header>
