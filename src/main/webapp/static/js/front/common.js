var menuArray = null; //菜单数组
var btns = null; //按钮数组
var ownBtns = null; //当前页面所有的按钮数组
var isShowMenu = true;
var currentLoginUser = null;
/**
 * 将菜单从数组转化成树
 */
function formatMenu(data,root){
    function getChildren(pid) {
        var children = [];
        for(var i = 0; i < data.length; i++) {
            if (data[i].pId == pid){
                children.push(data[i]);
                data[i]["children"] = getChildren(data[i].id);
            }
        }
        if (children.length > 0){
            return children;
        }else {
            return null;
        }
    }
    return getChildren(root);
}
//小数点位数限制
function fixNumber(num, pointNum){
    var number = parseFloat(num);
    if (!number){
        return '0.00';
    }else{
        return number.toFixed(pointNum);
    }
}
//验证 产品维护-宽限期限
$.extend($.fn.validatebox.defaults.rules, {
    AddlimitDays: {
        validator: function(value, param){
            return  /^[0-9]\d*$/.test(value);
        },
        message: '宽限期限不合法'
    }
});
//验证手机号
$.extend($.fn.validatebox.defaults.rules, {
    mobile: {
        validator: function(value, param){
            return /^1(3|4|5|6|7|8|9)[0-9]\d{8}$/.test(value);
        },
        message: '手机号输入有误'
    }
});
//验证 产品维护-年化利率
$.extend($.fn.validatebox.defaults.rules, {
    AddannumRate: {
        validator: function(value, param){
            return  /^\d+(\.(?!.*0$)\d{1,3})?$/.test(value);
        },
        message: '年化利率不合法'
    }
});
//验证 不超过500个字符
$.extend($.fn.validatebox.defaults.rules, {
    Addremark: {
        validator: function(value, param){
            return value.trim().length <= param[0];
        },
        message: '最多500个字符'
    }
});

//验证 不超过20个字符
$.extend($.fn.validatebox.defaults.rules, {
    Addcode: {
        validator: function(value, param){
            return value.trim().length <= param[0];
        },
        message: '最多20个字符'
    }
});
//发送表单数据
function ajaxSendForm(url,formSelector,successMsg,errorMsg,type){
    if ($(formSelector).form("validate")){
        var layerIndex = undefined;
        $.ajax({
            url: url,
            type: 'post',
            async: false,
            data: $(formSelector).serialize(),
            dataType: "json",
            beforeSend: function(){
                layerIndex = layer.load();
            },
            success: function(data){
                successError(data);
                if (data.success){
                    layer.msg(data.errorMessage,{icon: 6});
                }else{
                    layer.msg(data.errorMessage,{icon: 5});
                }
                return true;
            },
            error: function(err){
                layer.msg("请求出错",{icon: 2});
                return false;
            },
            complete: function () {
                layer.close(layerIndex);
            }
        });
    }else{
        layer.msg("信息录入不全或有误",{icon: 5});
        return false;
    }

}
//预览图片
function showPreviewImg(imgUrl, $imgSelector) {
    var imgPreview = $imgSelector.find(".preview");
    if (imgPreview.length > 0 && imgPreview.find("img").attr("src").indexOf(imgUrl) > 0){
        var contentHeight = ''+imgPreview.height()+'px';
        var contentWidth = ''+imgPreview.width()+'px';
        layer.open({
            type: 1,
            title: false,
            area: [contentWidth,contentHeight],
            //skin: 'layui-layer-nobg', //没有背景色
            shadeClose: true,
            content: imgPreview.html(),
            closeBtn: false,
            zIndex: 800
        });
    }else{
        imgPreview.remove();
        $imgSelector.append("<div class='preview'></div>");
        imgPreview = $imgSelector.find(".preview");
        var layerIndex = layer.load();
        var previewImg = $("<img >").attr("src",host + "/common/showImg?bizSource=2&f=" + imgUrl);
        imgPreview.append(previewImg);
        previewImg.one('load',function(){
            layer.close(layerIndex);
            var contentHeight = ''+imgPreview.height()+'px';
            var contentWidth = ''+imgPreview.width()+'px';
            layer.open({
                type: 1,
                title: false,
                area: [contentWidth,contentHeight],
                //skin: 'layui-layer-nobg', //没有背景色
                shadeClose: true,
                content: imgPreview.html(),
                closeBtn: false,
                zIndex: 800
            });
        }).one("error", function(){
            layer.close(layerIndex);
            layer.msg("图片加载失败",{icon: 0});
        });
    }
    // if (imgPreview.length == 0){
    //     $imgSelector.append("<div class='preview'></div>");
    //     imgPreview = $imgSelector.find(".preview");
    //     var layerIndex = layer.load();
    //     var previewImg = $("<img >").attr("src",host + "/common/showImg?bizSource=2&f=" + imgUrl);
    //     imgPreview.append(previewImg);
    //     previewImg.one('load',function(){
    //         layer.close(layerIndex);
    //         var contentHeight = ''+imgPreview.height()+'px';
    //         var contentWidth = ''+imgPreview.width()+'px';
    //         layer.open({
    //             type: 1,
    //             title: false,
    //             area: [contentWidth,contentHeight],
    //             //skin: 'layui-layer-nobg', //没有背景色
    //             shadeClose: true,
    //             content: imgPreview.html(),
    //             closeBtn: false,
    //             zIndex: 550
    //         });
    //     }).one("error", function(){
    //         layer.close(layerIndex);
    //         layer.msg("图片加载失败",{icon: 0});
    //     });
    // }else{
    //     var contentHeight = ''+imgPreview.height()+'px';
    //     var contentWidth = ''+imgPreview.width()+'px';
    //     layer.open({
    //         type: 1,
    //         title: false,
    //         area: [contentWidth,contentHeight],
    //         //skin: 'layui-layer-nobg', //没有背景色
    //         shadeClose: true,
    //         content: imgPreview.html(),
    //         closeBtn: false,
    //         zIndex: 550
    //     });
    // }
}
/**
 * ajax 获取页面
 */
function ajaxGetHtml(url, selector){
    var layerIndex = layer.load();
    $.ajax({
        type:"get",
        async:false,
        url: url + '&bizSource=1',
        // beforeSend:function () {
        //     layerIndex = layer.load();
        // },
        success:function (data) {
            if (data.indexOf("<div") >= 0){
                $(selector).html(data);
            }else{
                data = JSON.parse(data);
                successError(data);
            }
            layer.close(layerIndex);
        },
        error: function (e) {
            layer.msg("请求出错",{icon:2});
            layer.close(layerIndex);
        },
        complete:function () {
            //layer.close(layerIndex);
        }
    });
}
/**
 * 异常捕获信息展示
 */
function showExErrorMsg(){
    layer.msg("js执行异常",{icon: 2});
}
/**
 * 从服务器端获取菜单
 */
function getMenu(){
    $.ajax({
        type:"get",
        url: host + "/sessionData/getPrivilegeData",
        data:{
            bizSource:1,
        },
        async: false,
        beforeSend:function(){

        },
        success:function(data){
            var returnData = data;
            if (typeof data != "object") {
                returnData = JSON.parse(data);
            }
            if (returnData.data.frontPages!="" && returnData.data.frontPages!=""){
                menuArray = formatMenu(returnData.data.frontPages,"");
/*                btns = returnData.data.frontMenufuncsMap;
                currentLoginUser = returnData.data.frontCurrLoginUser;*/
                $("#frontCurrLoginUser").html(returnData.data.frontCurrLoginUser.username);
                $("#entNameTop").html(returnData.data.frontCurrLoginUser.realName);

            }else{
                layer.msg("动态菜单为空！",{icon:2});
            }
        },
        error: function () {
            layer.msg("菜单加载错误",{icon:2});
        }
    });
}

//请求成功后的错误判断
function successError(obj){
	if (obj.hasOwnProperty('success') && !obj.success){
        if (obj.hasOwnProperty('errorCode') && obj.errorCode == 'timeout' ){
            layer.msg("操作超时",{icon: 2,time:2000},function(){
                if (obj.hasOwnProperty('errorMessage')){
                    switch (obj.errorMessage){
                        case '1': //前台登陆页面
                            window.location.href = host + "/login/port";
                            break;
                        case '2': //后台登录页面
                            window.location.href = host + "/login/sys";
                            break;
                        default: //默认跳转前台首页
                            window.location.href = host + "/login/port";
                            break;
                    }
                }
            });
        } else {
            if (obj.hasOwnProperty('errorMessage')){
                layer.msg(obj.errorMessage);
            }
        }
        return true;
    }
}

//图片预览
function showPreviewImg(imgUrl, $imgSelector,maxWidth,maxHeight) {
    var imgPreview = $imgSelector.find(".preview");
    if (imgPreview.length > 0 && imgPreview.find("img").attr("src").indexOf(imgUrl) > 0){
        var imgW = imgPreview.width();
        var imgH = imgPreview.height();
        if (!maxWidth){
            maxWidth = 900;
            maxHeight = 600;
        }
        var ratio = imgW/imgH;
        var ratioH = imgH,ratioW = imgW;
        if (imgW > maxWidth){
            ratioH = maxWidth/ratio;
            ratioW = maxWidth;
            if (ratioH > maxHeight){
                ratioW = maxHeight*ratio;
                ratioH = maxHeight;
            }
        }
        var contentHeight = ''+ ratioH +'px';
        var contentWidth = ''+ ratioW +'px';
        layer.open({
            type: 1,
            title: false,
            area: [contentWidth,contentHeight],
            //skin: 'layui-layer-nobg', //没有背景色
            shadeClose: true,
            content: imgPreview.html(),
            closeBtn: false,
            zIndex: 800
        });
    }else{
        imgPreview.remove();
        $imgSelector.append("<div class='preview'></div>");
        imgPreview = $imgSelector.find(".preview");
        var layerIndex = layer.load();
        var previewImg = $("<img >").attr("src",host + "/common/showImg?bizSource=2&f=" + imgUrl);
        imgPreview.append(previewImg);
        previewImg.one('load',function(){
            layer.close(layerIndex);
            var imgW = imgPreview.width();
            var imgH = imgPreview.height();
            if (!maxWidth){
                maxWidth = 900;
                maxHeight = 600;
            }
            var ratio = imgW/imgH;
            var ratioH = imgH,ratioW = imgW;
            if (imgW > maxWidth){
                ratioH = maxWidth/ratio;
                ratioW = maxWidth;
                if (ratioH > maxHeight){
                    ratioW = maxHeight*ratio;
                    ratioH = maxHeight;
                }
            }
            var contentHeight = ''+ ratioH +'px';
            var contentWidth = ''+ ratioW +'px';
            previewImg.css({'height':contentHeight,'width':contentWidth});
            layer.open({
                type: 1,
                title: false,
                area: [contentWidth,contentHeight],
                //skin: 'layui-layer-nobg', //没有背景色
                shadeClose: true,
                content: imgPreview.html(),
                closeBtn: false,
                zIndex: 800
            });
        }).one("error", function(){
            layer.close(layerIndex);
            layer.msg("图片加载失败",{icon: 0});
        });
    }

}

/**
 * 构建一级菜单
 * @param headerMenu
 */
function renderHeaderMenu(headerMenu){
    // for (var i = 0; i < headerMenu.length; i++) {
    //     var $li = $('<li></li>');
    //     $li.html(headerMenu[i].name).attr("data-pId",headerMenu[i].id).attr("data-url",headerMenu[i].url);
    //     $(".nav_container").find("ul").append($li);
    //     if( i == 0){
    //         $li.addClass("current");
    //     }
    // }

    for (var i = 0; i < headerMenu.length; i++) {
        var $li = $('<li></li>');
        switch (headerMenu[i].name){
            case '我的主页':
                $li.prepend($('<i class="fa fa-home fa-lg"></i>'));
                break;
            case '投资管理':
                $li.prepend($('<i class="fa fa-group fa-lg"></i>'));
                break;
            case '资金管理':
                $li.prepend($('<i class="fa fa-rmb fa-lg"></i>'));
                break;
            case '账户管理':
                $li.prepend($('<i class="fa fa-address-card fa-lg"></i>'));
                break;
            case '我要投资':
                $li.prepend($('<i class="fa fa-legal fa-lg"></i>'));
                break;
            default:
                break;
        }

        $li.append(headerMenu[i].name);

        $(".nav_container").find("ul").append($li);
        if( i == 0){
            $li.addClass("current");
        }
    }
    $(".nav_container").find("ul").append('<li class="clear"></li>');
}
/**
 * 构建左边菜单
 * @param leftMenu
 * @returns {*}
 */
function renderLeftMenu(leftMenu){
    var $returnDom = $("<ul></ul>");
    function renderMenu(leftMenu) {

        for (var i = 0; i < leftMenu.length; i++) {
            var $li = $('<li></li>');
            $li.html('<span>'+leftMenu[i].name+'</span>').attr("data-id",leftMenu[i].id).attr("data-url",leftMenu[i].url);
            $returnDom.append($li);
            if( i == 0){
                $li.addClass("active");
            }
        }
        // $returnDom.append('<li class="clear"></li>');
        return $returnDom;
    }
    return renderMenu(leftMenu);
}
//高度和滚动条设置
function reSize(deltaHeight){
    if (!deltaHeight){
        deltaHeight = 70;
    }
    var clientHeight=document.body.clientHeight;
    if($(".main_content").length>0){
        $(".main_content").css("height",clientHeight-186);
        // $(".mCustomScrollbar").mCustomScrollbar({
        //     theme:"inset-dark",
        // });
    }else if($(".main_list").length>0){
        var searchHeight=$(".main_search").outerHeight();
        $(".main_list").css("height",clientHeight-161-searchHeight);
        $(".detail_content").css("height",clientHeight-161-searchHeight-deltaHeight);
        // $(".mCustomScrollbar").mCustomScrollbar({
        //     theme:"inset-dark",
        // });
    }
}

/**
 * 倒计时
 * @param obj
 */
function setTimeCommon(obj,countdown) {
    if (countdown == 0) {
        clearTimeout(timeCount);
        obj.removeClass().addClass("available");
        obj.html("获取验证码");
        countdown = 60;
        return;
    } else {
        obj.removeClass().addClass("unavailable");
        obj.html("重新发送(" + countdown + ")");
        countdown--;
    }
    timeCount=setTimeout(function() {
        setTimeCommon(obj,countdown)
    },1000);
}
/**
 * 获取手机验证码
 * @param phone 手机号
 * @param bizSource 区分前后台请求
 */
function getPhoneCodeCommon(phone,url,operation, bizSource,obj){
    var layerIndex;
    $.ajax({
        type:'post',
        dataType:'json',
        data:{
            mobile:phone,
            bizSource:bizSource,
            operation: operation
        },
        url:host + url,
        beforeSend:function(){
            layerIndex=layer.load();
        },
        success:function(data){
            if(data==0){
                layer.msg("用户已注册!",{icon:6,time:3000});
            }else if(data==1){
                layer.msg("验证码发送成功!",{icon:6,time:3000});
                setTimeCommon(obj,60);
            }else if(data==2){
                layer.msg("验证码发送失败!",{icon:6,time:3000});
            }else{
                layer.msg("同一手机号发送超过10条!",{icon:6,time:3000});
            }
            layer.close(layerIndex);
        },
        error:function(){
            layer.msg("请求出错!",{icon:6,time:3000});
            layer.close(layerIndex);
        }
    });
}

$(function(){
    $.ajaxSetup({ cache: false }); //设置ajax请求不要使用缓存
    getMenu();
    renderHeaderMenu(menuArray);
    $(".nav_container").delegate("li",'click',function(){
        $(".nav_container li").removeClass("current");
        $(this).addClass("current");
        // $(".main_content").mCustomScrollbar("destroy");
        $(".right_container").html("");
        $(".direction span").html("").append($(this).html());
        var index = $(this).index();
        var content_left = menuArray[index]["children"];
        if(content_left!=null){
            $(".content_left ul").html("");
            $(".content_left ul").append(renderLeftMenu(content_left).children());
            $(".content_left").show();
            $(".content_left li.active").click();
        }
    });
    $(".content_left").delegate("li",'click',function(){
        $(".content_left li").removeClass('active');
        // $(".main_content").mCustomScrollbar("destroy");
        $(this).addClass('active');
        if ($(this).attr("data-url")){
            var sendUrl = host + $(this).attr("data-url");
            var layerIndex;
            $.ajax({
                type: "post",
                url: sendUrl,
                async: false,
                data:{
                    bizSource:1,
                },
                beforeSend: function(){
                    layerIndex = layer.load();
                },
                success: function(data){
                    if (data.indexOf("<div") < 0){
                        successError(JSON.parse(data));
                    }else{
                        $(".right_container").empty();
                        $(".right_container").undelegate();
                        $(".panel.combo-p.panel-htop").remove();
                        $(".layui-layer-shade").remove();
                        data = data.replace(/\${path}/g,host);
                        try{
                            $(".right_container").append(data);
                        }catch(ex){
                            layer.msg("登录超时[0000]",{icon:2},function(){
                                // window.location.href = host;
                            });
                        }
                    }

                },
                error: function(e){
                    layer.msg("请求错误："+ e);
                },
                complete: function(){
                    layer.close(layerIndex);
                }
            });
        }
    });
    $(".nav_container li.current").click();
});
