/**
 * Created by Administrator on 2017/5/18.
 */

$('.sort-file').hover(function () {
    $('.sort-typ').fadeIn(200)
},function () {
    $('.sort-typ').fadeOut(200)
});
/*----  文件排序显示 ----*/


$('.plan_container').children('.basic_info').fadeOut(100);
$('.plan_container').children('.basic_info').eq(0).fadeIn(100);
$('.ul_menu').children('li').click(function () {
    $('.plan_container').children('.basic_info').eq($(this).index()).fadeIn(100).siblings().hide();
});
$('.child_menu').click(function () {
    $(this).addClass('active').siblings().removeClass('active');
});
//            ---------------nav tab切换（导航栏切换）-----------------------------
$('.set_plan').find('input').focus(function () {
    $(this).css({'border':'#4883f6 1px solid','transition':'all .2s'})
}).blur(function () {
    $(this).css({'border':'#bbbbbb 1px solid','transition':'all .2s'})
});
/*---  表单聚焦该样式 ---*/


$('.setting-seco').click(function () {
    /*$('.model-poll').fadeIn(200);*/
    $('.set_plan').fadeIn(200)
});
$('.footerbar').find('.del-btn').click(function () {
    /*$('.model-poll').fadeOut(200);*/
    $('.set_plan').fadeOut(200)
});
$('.item-reso').find('.empty-reso').click(function () {
    /*$('.model-poll').fadeOut(200);*/
    $('.resource').find('input').val('')
});



