var menuArray = null; //菜单数组
var btns = null; //按钮数组
var ownBtns = null; //当前页面所有的按钮数组
var isShowMenu = true;
var loginUser = null;//当前登录的用户
var aCity = {//身份证城市
    11: "北京",
    12: "天津",
    13: "河北",
    14: "山西",
    15: "内蒙古",
    21: "辽宁",
    22: "吉林",
    23: "黑龙 江",
    31: "上海",
    32: "江苏",
    33: "浙江",
    34: "安徽",
    35: "福建",
    36: "江西",
    37: "山东",
    41: "河南",
    42: "湖 北",
    43: "湖南",
    44: "广东",
    45: "广西",
    46: "海南",
    50: "重庆",
    51: "四川",
    52: "贵州",
    53: "云南",
    54: "西 藏",
    61: "陕西",
    62: "甘肃",
    63: "青海",
    64: "宁夏",
    65: "新疆",
    71: "台湾",
    81: "香港",
    82: "澳门",
    91: "国 外"
}
//身份证号验证
function isCardID(sId) {
    var iSum = 0;
    var info = "";
    if (!/^\d{17}(\d|x)$/i.test(sId)) return false;
    if (aCity[parseInt(sId.substr(0, 2))] == null) return false;
    sId = sId.replace(/x$/i, "a");
    sBirthday = sId.substr(6, 4) + "-" + Number(sId.substr(10, 2)) + "-" + Number(sId.substr(12, 2));
    var d = new Date(sBirthday.replace(/-/g, "/"));
    if (sBirthday != (d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate())) return false;
    for (var i = 17; i >= 0; i--) iSum += (Math.pow(2, i) % 11) * parseInt(sId.charAt(17 - i), 11);
    if (iSum % 11 != 1) return false;
    //aCity[parseInt(sId.substr(0,2))]+","+sBirthday+","+(sId.substr(16,1)%2?"男":"女");//此次还可以判断出输入的身份证号的人性别
    return true;
}

/**
 * 当前页面是否存在此按钮
 * @param btnName
 * @returns {boolean}
 */
function hasBtn(btnName){
    if (ownBtns && ownBtns.length >0){
        for (var i = 0; i < ownBtns.length; i++){
            if (ownBtns[i].urlName == btnName){
                return true;
            }
        }
    }
    return false;
}
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
/**
 * 从服务器端获取菜单
 */
function getMenu(){
    $.ajax({
        type:"post",
        url: host + "/sessionData/getPrivilegeData?bizSource=2",
        async: false,
        beforeSend:function(){

        },
        success:function(data){
            var returnData = data;
            if (typeof data != "object") {
                returnData = JSON.parse(data);
            }
            loginUser = returnData.data.backCurrLoginUser;
            if (returnData.data.backPages && returnData.data.backPages.length > 0){
                menuArray = formatMenu(returnData.data.backPages,'');
                btns = returnData.data.buttonMap;
            }

        },
        error: function () {
            layer.msg("菜单加载错误",{icon:2});
        }
    });
}
getMenu();
/**
 * 构建一级菜单
 * @param headerMenu
 */
function renderHeaderMenu(headerMenu){
  for (var i = 0; i < headerMenu.length; i++) {
      var $li = $('<li><div class="icon"></div><div class="desc"></div></li>');
      switch (headerMenu[i].name){
          case '会员管理':
              $li.find(".icon").append($('<i class="fa fa-user-secret fa-2x"></i>'));
              break;
          case '资金管理':
              $li.find(".icon").append($('<i class="fa fa-cny fa-2x"></i>'));
              break;
          case '产品管理':
              $li.find(".icon").append($('<i class="fa fa-pie-chart fa-2x"></i>'));
              break;
          case '借款人管理':
        	  $li.find(".icon").append($('<i class="fa fa-address-card-o fa-2x"></i>'));
              break;
          case '账户管理':
        	  $li.find(".icon").append($('<i class="fa fa-address-book-o fa-2x"></i>'));
              break;
          case '日志管理':
        	  $li.find(".icon").append($('<i class="fa fa-file-text fa-2x"></i>'));
              break;
          default:
             break;
      }

      $li.find(".desc").html(headerMenu[i].name);

      $(".headerMenu").find("ul").append($li);
      if( i == 0){
          $li.addClass("active");
      }
  }
    $(".headerMenu").find("ul").append('<li class="clear"></li>');
}
/**
 * 构建左边菜单
 * @param leftMenu
 * @returns {*}
 */
function renderLeftMenu(leftMenu){
  var $returnDom = $("<div></div>");
  var level = 1;
  function renderMenu(leftMenu,level) {
      for (var i = 0; i <leftMenu.length; i++ ){
          var $menuItem = $('<div><span></span></div>');
          $menuItem.attr("data-id",leftMenu[i]['id']);
          if (level == 1){
              $menuItem.addClass("levelOne");
          }else if(level == 2){
              $menuItem.addClass("levelTwo");
          }
          if (leftMenu[i].children && leftMenu[i].children.length > 0){
              $menuItem.find("span").html(leftMenu[i].name);
              $returnDom.append($menuItem);
              renderMenu(leftMenu[i].children,level+1);
          }else{
              if (level == 1){
                  $menuItem.addClass("link").append('<i class="fa fa-angle-right fa-lg"></i>');
              }else if(level == 2){
                  $menuItem.addClass("link").prepend('<i class="fa fa-angle-right fa-lg"></i>');
              }
              if (leftMenu[i].url){
            	  $menuItem.attr("data-url",leftMenu[i].url);
              }
              $menuItem.find("span").html(leftMenu[i].name);
              $returnDom.append($menuItem);
          }
      }
      return $returnDom;
  }
  return renderMenu(leftMenu,level);
}
/**
 *  获取当前可用操作按钮
 */
function getOwnBtns(manuId){
    return menuArray.data.buttonMap[manuId];
}
/**
 * 状态查询条件为空 未提交 未审核 变色
 */
function highLightState(selector){
	var stateNode = $(selector);
	return function(value,row,index){
		if ( stateNode.val() == "" && (value == "未审核" || value == "未提交")){
			 return '<span class="unSubmit">'+ value +'</span>';
		 }else
			 return value;
	};

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
/**
 * ajax 获取页面
 */
function ajaxGetHtml(url, selector){
    var layerIndex;
    $.ajax({
        type:"get",
        async:false,
        url: url,
        beforeSend:function () {
            layerIndex = layer.load();
        },
        success:function (data) {
            if (data.indexOf("<div") >= 0){
                $(selector).html(data);
            }else{
                data = JSON.parse(data);
                successError(data);
            }
        },
        error: function (e) {
            layer.msg("请求出错",{icon:2});
        },
        complete:function () {
            layer.close(layerIndex);
        }
    });
}

//ajax提交form表单 type=1显示邀请码
function ajaxSendForm(url,formSelector,successMsg,errorMsg,type){
    var returnFlag = false;
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
                    if (type == 1){
                        layer.open({
                            type: 0,
                            title: false,
                            content: data.data,
                            zIndex: 600
                        });
                    }else{
                        layer.msg(successMsg,{icon:1});
                    }
                    returnFlag = true;
                }else{
                    layer.msg(data.errorMessage,{icon: 2});
                    returnFlag = false;
                }
            },
            error: function(err){
                layer.msg("请求出错",{icon: 2});
                returnFlag = false;
            },
            complete: function () {
                layer.close(layerIndex);
            }
        });
    }else{
        layer.msg("信息录入不全或有误",{icon: 2});
        returnFlag = false;
    }
    return returnFlag;
}

//预览图片
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
        imgPreview.find('img').css({'height':contentHeight,'width':contentWidth});
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

//按照名称设置默认下拉选项
function setDefaultSelect($selector, name, nameKey, valueKey){
    var nodes = $selector.combobox("getData");
    if (nameKey == undefined || valueKey== undefined){
        nameKey = "text";
        valueKey = "value";
    }
    if (nodes.length > 0){
        $.each(nodes,function(key,val){
            if (val[nameKey] == name){
                $selector.combobox("setValue", val[valueKey]);
                return false;
            }
        });
    }
}

/**
 * 倒计时
 * @param obj
 */
function setTimeCommon(obj,countdown) {
    if (countdown == 0) {
        clearTimeout(timeCount);
        obj.removeClass("unavailable").addClass("available");
        obj.html("获取验证码");
        countdown = 60;
        return;
    } else {
        obj.removeClass("available").addClass("unavailable");
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
function getPhoneCodeCommon(phone,url,operation,bizSource, obj){
        var layerIndex;
        $.ajax({
            type:'post',
            dataType:'json',
            data:{
                mobile:phone,
                bizSource:bizSource,
                operation:operation
            },
            url: url,
            beforeSend:function(){
                layerIndex=layer.load();
            },
            success:function(data){
                successError(JSON.parse(data));
                if(data==0){
                    layer.msg("用户已注册!",{icon:6,time:3000});
                }else if(data==1){
                    layer.msg("验证码发送成功!",{icon:6,time:3000});
                    setTimeCommon(obj,60);
                }else if(data==2){
                    layer.msg("验证码发送失败!",{icon:6,time:3000});
                }else if(data == 3){
                    layer.msg("同一手机号发送超过10条!",{icon:6,time:3000});
                }else if(data == -100){
                    layer.msg("操作类型无效!",{icon:6,time:3000});
                }
                layer.close(layerIndex);
            },
            error:function(){
                layer.msg("请求出错!",{icon:6,time:3000});
                layer.close(layerIndex);
            }
        });
}

/*$(function(){
	try {
        //设置登录用户
        if (loginUser && loginUser.hasOwnProperty("cname")){
            $(".loginRole").find("span").html(loginUser.cname);
        }
        //控制菜单显示
        $(".context").delegate(".menuControl",'click',function(){
            if (isShowMenu){
                isShowMenu = false;
                $(".leftMenu").animate({left:'-260px'});
                $(".rightContext").animate({left:'0'},function(){
                    $('.dg').datagrid("resize");
                });
            }else{
                isShowMenu = true;
                $(".leftMenu").animate({left:'0'});
                $(".rightContext").animate({left:'260px'},function(){
                    $('.dg').datagrid("resize");
                });
            }
        });*/
        //登出
       /* $("#loginOut").click(function(){
            $.ajax({
                type:'post',
                data:{
                    bizSource:2,
                },
                url:host+'/login/logout',
                success:function(data){
                    if(typeof data !="object"){
                        data= JSON.parse(data);
                    }
                    if(data.success){
                        layer.msg(data.data,{ icon:6 });
                        location.href=host+"/login/sys"
                    }else{
                        layer.msg(data.errorCode,{ icon:5 });
                    }
                }
            })
        });*/
       
        //修改密码
       /* $("#changePassword").click(function(){
        	
            $("#changePWDForm").find(".easyui-textbox").textbox('setValue','');
            if (loginUser && loginUser.hasOwnProperty("mobile")){
                $('#changePWDForm .mobile').textbox('setValue',loginUser.mobile);
            }
        	debugger;
            layer.open({
                type: 1,
                title: '修改密码',
                area: ['500px'],
                content: $("#changePWD"),
                shadeClose: false,
                resize: false,
                zIndex: 1000,
                btn: ["确定","取消"],
                btnAlign: "c",
                yes: function(index){
                   if ($("#changePWDForm").form("validate")){
                        var layerIndex = undefined;
                        $.ajax({
                            url: host+'/entInfo/updatePwd?bizSource=2',
                            type: 'post',
                            async: false,
                            data: $("#changePWDForm").serialize(),
                            dataType: "json",
                            beforeSend: function(){
                                layerIndex = layer.load();
                            },
                            success: function(data){
                                successError(data);
                                if (data.success){
                                    layer.close(index);
                                    layer.msg("修改成功，即将登出",{icon:1,time:2000}, function () {
                                            $("#loginOut").click();
                                        });
                                }else{
                                    layer.msg(data.errorMessage,{icon: 2});
                                }
                            },
                            error: function(err){
                                layer.msg("请求出错",{icon: 2});
                            },
                            complete: function () {
                                layer.close(layerIndex);
                            }
                        });
                    }else{
                        layer.msg("信息录入不全或有误",{icon: 2});
                    }
                },
                btn2: function (index,layero){
                    layer.close(index);
                }
            });
        });*/
/*        //图片验证码更新
        $(".validateImg").click(function(){
            $(this).attr("src",host + "/customer/getInviteValidateCode?bizSource=2&timeStamp="+ (new Date()).valueOf());
        });*/
/*        //获取手机验证码
        $("#getCode").click(function(){
            var phone = $("#changePWDForm").find("input[name='mobile']").val();
            if (/^1(3|4|5|6|7|8|9)[0-9]\d{8}$/.test(phone)){
                if ($(this).hasClass('available')){
                    getPhoneCodeCommon(phone, host + '/entInfo/getMobileCode', 1 ,2,$(this));
                }
            }else{
                layer.msg("手机号非法",{icon: 2});
            }
        });*/
        //角色菜单显示
       /* $(".loginRole").mouseenter(function(){
            $(".loginMenu").css("display","block");
        });
        $(".loginMsg").mouseleave(function(){
            $(".loginMenu").css("display","none");
        });
         renderHeaderMenu(menuArray);
        $(".headerMenu").delegate("li",'click',function(){
            $(".headerMenu li").removeClass("active");
            $(this).addClass("active");
            var index = $(this).index();
            var leftMenu = menuArray[index]["children"];
            $(".leftMenu").html("");//.append('<div class="title"></div>')&nbsp;<i class="fa fa-outdent fa-lg"></i>
            $(".leftMenu").append(renderLeftMenu(leftMenu).children());
            //$(".leftMenu div.link").eq(0).addClass('active');
            $(".leftMenu div.link").eq(0).click();
        });
        $(".leftMenu").delegate("div.link",'click',function(){
            $(".leftMenu div.link").removeClass('active');
            $(this).addClass('active');
            //ownBtns = btns[$(this).attr("data-id")]; //按钮未加入控制
            if ($(this).attr("data-url")){
                var sendUrl = host + $(this).attr("data-url");
                var layerIndex;
                $.ajax({
                    type: "post",
                    url: sendUrl,
                    async: false,
                    data:{
                        bizSource:2,
                    },
                    beforeSend: function(){
                        layerIndex = layer.load();
                    },
                    success: function(data){
                        if (data.indexOf("<div") < 0){
                            if (typeof data != "object"){
                                data = JSON.parse(data);
                                successError(data);
                            }
                        }else{
                            $(".rightContext").empty();
                            $(".rightContext").undelegate();
                            $(".panel.combo-p.panel-htop").remove();
                            $(".layui-layer-shade").remove();
                            data = data.replace(/\${path}/g,host);
                            try{
                                $(".rightContext").append(data);
                                //上传文件格式限制
                                //$(".fileUpload").attr("accept",'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel');
                            }catch(ex){
                                layer.msg("登录超时[00xFE]",{icon:2},function(){
                                   // window.location.href = host;
                                });
                                //layer.msg("js执行异常",{icon:2});
                            }
                        }
*/
//        		  data = data.replace(/\${path}/g,host);
//        		  $(".rightContext").html(data);

                  /*  },
                    error: function(e){
                        debugger;
                        layer.msg("请求错误："+ e);
                    },
                    complete: function(){
                        layer.close(layerIndex);
                    }
                });
            }
        });
*/
       /* $(".headerMenu li.active").click();

        $(".context").delegate(".navTab",'click',function(){
            $(".navTabs .navTab").removeClass("active");
            $(this).addClass("active");
            $(".tabs .tab").removeClass("active");
            $(".tabs .tab").eq($(this).index()).addClass("active");
        });
    }catch (e){
	    showExErrorMsg();
    }
});*/
/**
 * 异常捕获信息展示
 */
function showExErrorMsg(){
    layer.msg("js执行异常",{icon: 2});
}

//时间设置
function getBeforeDate(n){
    var n = n;
    var d = new Date();
    var year = d.getFullYear();
    var mon=d.getMonth()+1;
    var day=d.getDate();
    if(day <= n){
        if(mon>1) {
            mon=mon-1;
        }
        else {
            year = year-1;
            mon = 12;
        }
    }
    d.setDate(d.getDate()-n);
    year = d.getFullYear();
    mon=d.getMonth()+1;
    day=d.getDate();
    s = year+"-"+(mon<10?('0'+mon):mon)+"-"+(day<10?('0'+day):day);
    return s;
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
//拓展easyui验证
//日期解析
$.fn.datebox.defaults.parser = function(s){
	if (!s) return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0],10);
	var m = parseInt(ss[1],10);
	var d = parseInt(ss[2],10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
		return new Date(y,m-1,d);
	} else {
		return new Date();
	}
}
//格式化日期
$.fn.datebox.defaults.formatter=function(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}
//验证手机号
/*$.extend($.fn.validatebox.defaults.rules, {
    mobile: {
		validator: function(value, param){
			return /^1(3|4|5|6|7|8|9)[0-9]\d{8}$/.test(value);
		},
		message: '手机号输入有误'
    }
});
//验证固定电话
$.extend($.fn.validatebox.defaults.rules, {
    phone: {
        validator: function(value, param){
            return /^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/.test(value);
        },
        message: '电话输入有误'
    }
});
//验证传真
$.extend($.fn.validatebox.defaults.rules, {
    fax: {
        validator: function(value, param){
            return /^(\d{3,4}-)?\d{7,8}$/.test(value);
        },
        message: '传真号码输入有误'
    }
});
//验证字段长度
$.extend($.fn.validatebox.defaults.rules, {
    minLength: {
        validator: function(value, param){
            return value.trim().length >= param[0];
        },
        message: '长度至少为 {0} 位'
    }
});
//验证密码
$.extend($.fn.validatebox.defaults.rules, {
    mypassword: {
        validator: function(value, param){
            return /^[A-Za-z0-9]{6,14}$/.test(value);
        },
        message: '6-14位字母数字组合'
    }
});
//验证输入框内不允许输入空格
$.extend($.fn.validatebox.defaults.rules, {
    noblank: {
        validator: function(value, param){
            return /^\S+$/.test(value);
        },
        message: '只可输入非空字符'
    }
});
//验证邮箱
$.extend($.fn.validatebox.defaults.rules, {
    myemail: {
		validator: function(value, param){
			return /^[a-z_0-9.-]{1,64}@([a-z0-9-]{1,200}.){1,5}[a-z]{1,6}$/i.test(value);
		},
		message: '请输入正确的邮箱'
    }
});
//验证金额数字正数 小数点后两位
$.extend($.fn.validatebox.defaults.rules, {
    moneyNumber: {
		validator: function(value, param){
			return /^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/.test(value);
		},
		message: '金额字段输入错误'
    }
});
//验证币种长度必须是三位
$.extend($.fn.validatebox.defaults.rules, {
    currencyValidate: {
		validator: function(value, param){
			return  value.length == 3;
		},
		message: '币种长度必须是3位'
    }
});
//验证统一社会信用代码
$.extend($.fn.validatebox.defaults.rules, {
    unifiedCode: {
        // validator: function(value, param){
        //     return  /^[^_IOZSVa-z\W]{2}\d{6}[^_IOZSVa-z\W]{10}$/.test(value);
        // },
        validator: function(value, param){
            return  /^[a-zA-Z0-9]{1,18}$/.test(value);
        },
        message: '统一社会信用代码不合法'
    }
});*/

//身份证验证
// $.extend($.fn.validatebox.defaults.rules, {
//   idCard: {
//         validator: function(value, param){
//             return  /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/.test(value) || /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/.test(value);
//         },
//         message: '身份证输入有误'
//     }
// });
/*$.extend($.fn.validatebox.defaults.rules, {
    idCard: {
        validator: function(value, param){
            return  isCardID(value)
        },
        message: '身份证输入有误'
    }
});*/

