$(function () {


    $('.home-video').height(window.innerHeight);
    $('.header').height(window.innerHeight);
    $(window).resize(function() {
        $('.home-video').attr('height', window.innerHeight);
        $('.home-video').attr('width', window.innerWidth);
        $('.header').height(window.innerHeight);
    });
    $(".nav-sy").click(function () {
        $(".login").css("display","none")
        $(".ifr").fadeIn(0)
        $(".ifrcj").fadeOut(0)
        $(".ifrjs").fadeOut(0)
        $(".ifrwl").fadeOut(0)
    })
    $(".nav-wlzw").click(function () {
        $(".login").css("display","none")
        $(".ifrwl").fadeIn(0)
        $(".ifrcj").fadeOut(0)
        $(".ifrjs").fadeOut(0)
        $(".ifr").fadeOut(0)
    })
    $(".nav-bmjj").click(function () {
        $(".tx").css("display","none")
        $(".ifrjs").fadeIn(0)
        $(".ifrcj").fadeOut(0)
        $(".ifrwl").fadeOut(0)
        $(".ifr").fadeOut(0)
    })
    $(".nav-gshd").click(function () {
        $(".login").css("display","none")
        $(".ifrcj").fadeIn(0)
        $(".ifrjs").fadeOut(0)
        $(".ifrwl").fadeOut(0)
        $(".ifr").fadeOut(0)
    })
    $(".nav-bmsq").click(function () {
        $(".login").fadeIn(0)
        $(".ifr").fadeOut(0)
        $(".ifrjs").fadeOut(0)
        $(".ifrwl").fadeOut(0)
        $(".ifrcj").fadeOut(0)
    })
    $(".barrier").click(function () {
        $(".nxo").remove()
        $(".barrier").remove()
        $(".barrier_black").remove()
        $(" body").css("overflow","inherit")
    })

    $(".homeVideo").click(function () {
        $(".barrier").fadeIn(0)
        $(" video").attr("controls","none")
        $(".video_button").fadeOut(0)
        }
    )
})