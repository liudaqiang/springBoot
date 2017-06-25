/**
 * Created by Administrator on 2017/5/9.
 */




$('.add-plan-btn').children('.cancel-btn').click(function () {
    $('.modal_box').fadeOut(200)
});


/*---  提交数据 -----*/


$('.options').children('.tab-head').click(function () {
    $(this).addClass('active').siblings().removeClass('active')
});
$('.options').children('.infor').click(function () {
    $('.add-resource').hide().siblings('.add-informat').fadeIn(300);
});
$('.options').children('.resource').click(function () {
    $('.add-informat').hide().siblings('.add-resource').fadeIn(300);
});
/*----- 进度管理 ----*/



$('.file-manage').bind('contextmenu',function(){
    return false;
});
/*---阻止浏览器页面默认右击事件---*/

$('.main-body').click(function () {
    $('.rg-click').fadeOut()
});

$('.file-manage').find('.file-arr').mousedown(function(e){
    var rg_clicktop = $(this).offset().top + 50;
    var rg_clickleft =$(this).offset().left + 50;
    if(3 == e.which){
        if ( $(this).hasClass('active')){
            $('.file-manage').find('.rg-click').css({'top':rg_clicktop,'left':rg_clickleft}).fadeIn(200)
        }else {
            $('.file-manage').find('.rg-click').fadeOut()
        }
    }else if(1 == e.which){
        if ( !$(this).hasClass('active')){
            $('.file-manage').find('.rg-click').fadeOut()
        }
        $(this).addClass('active').siblings('.file-arr').removeClass('active');
    }
});

/*---  文件右击事件 ---*/



$('.rg-click').children('p').click(function () {
    $('.rg-click').fadeOut(200);
    $(this).addClass('active').siblings().removeClass('active');
});

$('.rg-click').children('.remove-btn').click(function () {
    $('.remove-file').fadeIn(300);
    deleteFile();
});

$('.remove-file').find('.cancel-btn').click(function () {
    $('.file-manage').find('.rg-click').fadeOut(200);
    $('.remove-file').fadeOut(300)
    
});
/*----  删除文件弹出框 ----*/



$('.upload').click(function () {
    $('.file-manage').find('.file-info').fadeIn(300)
});
$('.file-info').find('.cancel-btn').click(function () {
    $('.file-manage').find('.file-info').fadeOut(200)
});
/*------  上传文件弹窗 -----*/

$('.rg-click').children('.details-btn').click(function () {
	getFileDetail();
});
$('.close-file-deta').click(function () {
    $('.filearr-detail').css({'width':'0','transition':'all .3s'})
    $('.rg-click').fadeOut()
});

/*----  文件详情 右弹框 ----*/

$('.rg-click').children('.rename-btn').click(function () {
    var oldFileName = $('.file-arr.active').children('.file-name').children().text();
    $('.file-arr.active').children('.file-name').children().text('');
    $('<input type="text" class="file-newname">').appendTo($('.file-arr.active').children('.file-name').children('a')).focus().blur(function () {
        if ($(this).val()){
        	fileRename($(this).val());
            $('.file-arr.active').children('.file-name').children('a').text($(this).val())
        }else {
            alert('文件名不能为空');
            $('.file-arr.active').children('.file-name').children('a').text(oldFileName)
        }
    }).remove($(this));
    var newFileName = $('.file-arr.active').children('.file-name').children().text();
    console.log(oldFileName,newFileName)
});
var id;
$('.file-arr').click(function(){
	 id = $(this).children("input").attr("value");
})


/*----  重命名 -----*/


$('.subpage-head').find('.screen-ico').click(function () {
    $('.model-poll').fadeIn(200);
    $('.screen-box').fadeIn(200)
});
$('.screen-btn').children('button').click(function () {
    $('.model-poll').fadeOut(200);
    $('.screen-box').fadeOut(200)
});

