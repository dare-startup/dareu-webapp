$(function(){
    //Load WOW animations
    new WOW().init(); 
    
    //smooth scroll 
    smoothScroll.init({
        speed: 1000, //how fast to complete scroll MS 
        offset: 0 // how far to offset the scrolling anchor location in pixels 
    }); 
    
    //scroll event 
    $(window).scroll(function(){
        var scrollTop = $(this).scrollTop(); 
        if(scrollTop >= 50)
            $(".go-up").fadeIn(); 
        else 
            $(".go-up").fadeOut(); 
    }); 
    $(window).scroll(function(){
        var nav = $(".header"); 
        var scroll = $(window).scrollTop(); 
        if(scroll >= 100)
            nav.addClass("nav-menu")
        else 
            nav.removeClass("nav-menu"); 
    }); 
}); 