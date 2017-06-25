/**
 * Created by Administrator on 2017/5/19.
 */



var windowHight = $(window).height();  //屏幕高度

$('.login-user').height(windowHight*0.47);
 var login_userHeight = $('.login-user').height();
$('.project-new').height(login_userHeight);

$('.login-user').find('input').focus(function () {
    $(this).css({'border':' #1480ec solid 1px','transition':'all .3s'})
}).blur(function () {
    $(this).css({'border':' #cccccc solid 1px','transition':'all .3s'})
});











