/**
 * Created by Administrator on 2017/4/18.
 */

var userlogin = 'http://139.196.180.7:82/public/';




    $.ajax({
        type:'get',
        url:userlogin+'/api/select_type_api/list',
        crossDomain: true,
        /*contentType:'application/json; charset=utf-8',*/
        data:{"type":'1'},
        dataType:'json',
        beforeSend:function(){},
        success:function (res) {
            for(var i = 0;i<res.selectType.length;i++){
            	if(i == 0){
            		 var sss = "<option value='"+res.selectType[i]._id.$oid+"' selected ='selected'>"+res.selectType[i].name+"</option>";
            		 jQuery(".major-arr").append(sss);
            	}else{
            		var sss = "<option value='"+res.selectType[i]._id.$oid+"'>"+res.selectType[i].name+"</option>";
            		 jQuery(".major-arr").append(sss);
            	}
            	var sss = "<option value='"+res.selectType[i]._id.$oid+"'>"+res.selectType[i].name+"</option>";
            	jQuery("#profession_option").append(sss);
            }
        },
        error:function () {
            console.log("请检查网路");
        }
    });


    $.ajax({
        type:'get',
        url:userlogin+'/api/select_type_api/list',
        crossDomain: true,
        /*contentType:'application/json; charset=utf-8',*/
        data:{"type":'2'},
        dataType:'json',
        beforeSend:function(){},
        success:function (res) {
        	for(var i = 0;i<res.selectType.length;i++){
            	if(i == 0){
            		 var sss = "<option value='"+res.selectType[i]._id.$oid+"' selected ='selected'>"+res.selectType[i].name+"</option>";
            		 jQuery(".file-infoarr").append(sss);
            	}else{
            		var sss = "<option value='"+res.selectType[i]._id.$oid+"'>"+res.selectType[i].name+"</option>";
            		 jQuery(".file-infoarr").append(sss);
            	}
            	var sss = "<option value='"+res.selectType[i]._id.$oid+"'>"+res.selectType[i].name+"</option>";
            	 jQuery("#section_option").append(sss);
            }
        },
        error:function () {
            console.log("请检查网路");
        }
    });


    $.ajax({
        type:'get',
        url:userlogin+'/api/select_type_api/list',
        crossDomain: true,
        /*contentType:'application/json; charset=utf-8',*/
        data:{"type":'3'},
        dataType:'json',
        beforeSend:function(){},
        success:function (res) {
        	for(var i = 0;i<res.selectType.length;i++){
            	if(i == 0){
            		 var sss = "<option value='"+res.selectType[i]._id.$oid+"' selected ='selected'>"+res.selectType[i].name+"</option>";
            		 jQuery(".project-unitarr").append(sss);
            	}else{
            		var sss = "<option value='"+res.selectType[i]._id.$oid+"'>"+res.selectType[i].name+"</option>";
            		 jQuery(".project-unitarr").append(sss);
            	}
            	var sss = "<option value='"+res.selectType[i]._id.$oid+"'>"+res.selectType[i].name+"</option>";
            	jQuery("#unit_project_option").append(sss);
            }
        },
        error:function () {
            console.log("请检查网路");
        }
    });
/*
$.ajax({
    type:'post',
    url:userlogin+'/api/file_api/createDir',
    crossDomain: true,
    /!*contentType:'application/json; charset=utf-8',*!/
    data:{"path":'/a','parent_id':'1'},
    dataType:'json',
    beforeSend:function(){},
    success:function (res) {
        console.log(res);

    },
    error:function () {
        console.log("请检查网路");
    }
});
/!*--------  创建文件夹 --------*!/
$.ajax({
    type:'get',
    url:userlogin+'/api/file_api/list',
    crossDomain: true,
    /!*contentType:'application/json; charset=utf-8',*!/
    data:{'id':'1'},
    dataType:'json',
    beforeSend:function(){},
    success:function (res) {
        console.log(res);
        for(i=0;i<res.fileDirList.length;i++){
            var filename = res.fileDirList[i].path;
            $('<div class="file-arr"> ' +
                '<div class="file-image">' +
                    '<img src="images/feil.png">' +
                '</div> ' +
                '<div class="file-name">' +
                    '<a href="#"></a>' +
                '</div>' +
              '</div>').appendTo($('.file-manage'));

            $('.file-arr').children('.file-name').children('a').text(filename.replace('/',''));
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
        }

    },
    error:function () {
        console.log("请检查网路");
    }
});*/

$('')

/*---- 查询文件列表  ----*/









/*$('#confirm-login').click(function () {
    var account =$('#account').val();
    var passwor =$('#passwor').val();

    $.ajax({
        type:'POST',
        url:userlogin,
        crossDomain: true,
        /!*contentType:'application/json; charset=utf-8',*!/
        data:{"username":'15000818050',"password":'111'},
        dataType:'json',
        beforeSend:function(){},
        success:function (res) {
            console.log(res);
            console.log(res.data.code);
            if (res.data.code == 3001){
                $('.login-point-text').text('密码错误，或用户名不存在，请重新输入');
                $('.login-point-text').fadeIn(200);
                setTimeout(function () {
                    $('.login-point-text').fadeOut(200)
                },2000)
            }else if (res.data.code == 2002){
                $('.login-point-text').text('登录成功');
                $('.login').fadeOut(200);
                $('.model-poll').fadeOut(200);
                $('.login-button').fadeOut(200);
                $('.user-core').fadeIn(200);
                $('.login-point-text').fadeIn(200);
                setTimeout(function () {
                    $('.login-point-text').fadeOut(200)
                },2000)
            }else {
                $('.login-point-text').text('账号或密码不能为空');
                $('.login-point-text').fadeIn(200);
                setTimeout(function () {
                    $('.login-point-text').fadeOut(200)
                },2000)
            }
        },
        error:function () {
            console.log("请检查网路");
            $('.login-point-text').text('请检查网路');
            $('.login-point-text').fadeIn(200);
            setTimeout(function () {
                $('.login-point-text').fadeOut(200)
            },2000)
        }
    });

});*/


/*$.ajax({
    type:'POST',
    url:userlogin+'/project/finddynamic',
    crossDomain: true,
    /!*contentType:'application/json; charset=utf-8',*!/
    data:{id:''},
    dataType:'json',
    beforeSend:function(){},
    success:function (res) {
        console.log(res);
        console.log(res);
    },
    error:function () {
        console.log("请检查网路");
    }
});*/




/*$.ajax({
    url: "http://api.map.baidu.com/place/v2/suggestion?query=天安门&region=北京市&city_limit=true&output=json&ak=VOT08z0XvaL2EqOCKUFwD0qKwhcqgBSr",
    type: "GET",
    dataType: 'jsonp',
    /!*headers: {'Referer': 'maiunsoft.com'},*!/
    beforeSend: function (xhr) {
        xhr.setRequestHeader("Referer", "maiunsoft.com");
    },
    success: function (data) {
        console.log(data);
    },
    error: function (xhr, textStatus, errorThrow) {
        alert(xhr.readyState);
    }
});*/




















