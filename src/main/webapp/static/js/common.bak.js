// JavaScript Document

$(function () {
    $("#dg").datagrid({
        rowStyler: function (index, row) {
            if (index % 2 == 1) {
                return 'background-color:#F4F4F4';
            }
        }
    });
    //动态自适应tab区域的高度
    var dheight = document.documentElement.clientHeight - 110;
    $("#tt").tabs({
        height: dheight
    });

    var userId = $("input[name='userId']").val();
    //获取菜单
    $.ajax({
        url: "../login/queryForPage.do",
        type: "post",
        dataType: "json",
        success: function (data) {
            var pid;
            var name;
            var url;
            var length = data.length;
            var html = "<ul>";
            var type;

            for (var i = 0; i < length; i++) {
                pid = data[i].pid;
                var id = data[i].id;
                name = data[i].name;
                url = data[i].url;
                type = data[i].type;
                if (id != 1000 && id != 1004 && type != "2") {
                    if (pid == 0 && url != "" && url != null && url != "#") {
                        html +=
                            '<li><a class="level0" href="#" pId=' + id
                            + ' onClick="addTab(\'' + name + '\',\'' + url + '\')">' + name
                            + '</a></li>'
                        /*html += "<li><a class='level0' href=" + url + " pId=" + id + ">" + name + "</a></li>";*/
                    } else if (pid == 0) {
                        html += "<li><a class='level0' pId=" + id + ">" + name + "</a></li>";
                    }

                }

            }
            html = html + "</ul>";

            $("#smoothmenu1").append(html);

            $(".level0").each(function (index, element) {
                //获取目标的父id
                var html1 = "<ul>";
                var pId = $(this).attr("pId");

                for (var i = 0; i < length; i++) {
                    pid = data[i].pid;    // 获取数据的父id
                    id = data[i].id;
                    name = data[i].name;
                    url = data[i].url;
                    type = data[i].type;

                    if (pId == pid && id != 42 && type != "2") {    //如果数据的父id 和 目标的父id相同，说明该数据是 目标的子li
                        if (url == "cityMap.jsp") {
                            html1 +=
                                '<li><a class="level1" target="_blank" href="cityMap.jsp" pId='
                                + id + ' >' + name + '</a></li>';
                        } else if (url == "http://ditu.amap.com/dir") {
                            html1 +=
                                '<li><a class="level1" target="_blank" href="http://ditu.amap.com/dir" pId='
                                + id + ' >' + name + '</a></li>';
                        } else {
                            html1 +=
                                '<li><a class="level1" href="#" pId=' + id
                                + ' onClick="addTab(\'' + name + '\',\'' + url + '\')">'
                                + name + '</a></li>';
                        }

                        /* html1 += "<li><a class='level1' href=" + url + " pId=" + id + ">" + name + "</a></li>";*/
                    }
                }

                html1 = html1 + "</ul>";

                $(this).parent("li").append(html1);

            });

/*            ddsmoothmenu.init({
                mainmenuid: "smoothmenu1", 		//菜单div的ID
                orientation: 'h', 				//水平菜单or垂直菜单 : 请设置"h" or "v"
                classname: 'ddsmoothmenu', 		//导航菜单的ul标签的class样式名，在这里是ddsmoothmenu.css的样式名
                contentsource: "markup" 	 	//"markup" or ["container_id",
                // "path_to_menu_file"]
                //customtheme: ["#1c5a80", "#18374a"] //菜单背景色和鼠标移过去时的颜色
            });*/

        }

    });

    //默认当天时间
    var d = new Date();
    var month = d.getMonth() + 1;
    var day = d.getDate();
    month = month < 10 ? "0" + month : month;
    day = day < 10 ? "0" + day : day;
    var str = d.getFullYear() + "-" + month + "-" + day;
    $(".currentTime").val(str);


    //重置
    $("#reset").click(function () {
        $(".conditions input").val("");
        $(".conditions select").val("");
        $(".conditions .select").val(null).trigger("change");
    });
    $("#reset1").click(function () {
        $(".conditions input").val("");
        $(".conditions select").val("");
        $(".conditions .select").val(null).trigger("change");
    });
    $("#reset2").click(function () {
        $(".conditions input").val("");
        $(".conditions select").val("");
        $(".conditions .select").val(null).trigger("change");
    });

    $("input[type='radio']").click(function () {
        $(this).attr("checked", "checked").siblings("input[type='radio']").removeAttr("checked");
    });

    //推广维护 时段选择

    $(".select1").html(
        '<option value="00:00">00:00</option><option value="00:00">00:00</option><option value="00:30">00:30</option><option value="01:00">01:00</option><option value="01:30">01:30</option><option value="02:00">02:00</option><option value="02:30">02:30</option><option value="03:00">03:00</option><option value="03:30">03:30</option><option value="04:00">04:00</option><option value="04:30">04:30</option><option value="05:00">05:00</option><option value="05:30">05:30</option><option value="06:00">06:00</option><option value="06:30">06:30</option><option value="07:00">07:00</option><option value="07:30">07:30</option><option value="08:00">08:00</option><option value="08:30">08:30</option><option value="09:00">09:00</option><option value="09:30">09:30</option><option value="10:00">10:00</option><option value="10:30">10:30</option><option value="11:00">11:00</option><option value="11:30">11:30</option><option value="12:00">12:00</option><option value="12:30">12:30</option><option value="13:00">13:00</option><option value="13:30">13:30</option><option value="14:00">14:00</option><option value="14:30">14:30</option><option value="15:00">15:00</option><option value="15:30">15:30</option><option value="16:00">16:00</option><option value="16:30">16:30</option><option value="17:00">17:00</option><option value="17:30">17:30</option><option value="18:00">18:00</option><option value="18:30">18:30</option><option value="19:00">19:00</option><option value="19:30">19:30</option><option value="20:00">20:00</option><option value="20:30">20:30</option><option value="21:00">21:00</option><option value="21:30">21:30</option><option value="22:00">22:00</option><option value="22:30">22:30</option><option value="23:00">23:00</option><option value="23:30">23:30</option>');

    var sy = $.extend({}, sy);
    /*定义一个全局变量*/
    sy.serializeObject = function (form) { /*将form表单内的元素序列化为对象，扩展Jquery的一个方法*/
        var o = {};
        $.each(form.serializeArray(), function (index) {
            if (o[this['name']]) {
                o[this['name']] = o[this['name']] + "," + this['value'];
            } else {
                o[this['name']] = this['value'];
            }
        });
        return o;
    };

    //获取下拉选项框的值
    getName();

    //点击获取拉下选择框
    $(".selectPoint").on("click", function () {
        var $em = $(this).children(".emcls");
        $em.toggleClass("emclsp");
        $(".selectArea").toggleClass("fn-hide");
    });

    $(".selectArea input").on("click", function () {
        var dataId = $(this).attr("dataId");
        var checked = $(this).attr("checked");
        if (checked == "checked") {
            $('#dg').datagrid('hideColumn', dataId);
            $(this).removeAttr("checked");
        } else {
            $('#dg').datagrid('showColumn', dataId);
            $(this).attr("checked", "checked");
        }
    });

    //后台权限设置
    $(".icon-switchP").on("click", function () {
        $(this).toggleClass("icon-switchA").siblings(".secondTree").slideToggle(200);
    });

    $(".parentTree").click(function () {

        var hasCheck = $(this).hasClass("icon-check");
        var childlist = $(this).siblings("ul").find(".icon-uncheck");
        if (!hasCheck) {
            childlist.addClass("icon-check");
        } else {
            childlist.removeClass("icon-check");
        }
    });

    $(".icon-uncheck").on("click", function () {
        $(this).toggleClass("icon-check");
        var hasCheck = $(this).hasClass("icon-check");
        if (!hasCheck) {
            $(this).next("input").attr("checked", "checked");
        } else {
            $(this).next("input").removeAttr("checked");
        }
    });

    /*$(".priviLcls3").on("dblclick",function(){
     $(".priviLcls2").show();
     });*/

    /*// 获取菜单数据
     var userId = $("input[name='userId']").val();

     $.ajax({
     url:"../menu/usermenu.do?userId="+userId,
     type:"post",
     dataType:"json",
     success:function(data){
     menuP(data);
     },error:function(){
     layer.alert("网络异常,请稍后重试");
     }
     });*/

    //伸缩搜索栏
    $(".searchBox").click(function () {
        $("#searchBox").slideToggle();
        $(this).toggleClass("down");
    })
});

// 日期比较
function compare(startTime, endTime) {
    var a = 0;
    if (startTime != "" && endTime != "") {
        startTime = split(startTime);
        endTime = split(endTime);
        if (endTime < startTime) {
            a = 1;
        } else {
            a = 0
        }
    }
    return a;
}

//日期比较
function split(time) {
    time = time;
    var arrTime = time.split("-");
    var sum = "";
    for (var i = 0; i < arrTime.length; i++) {
        sum += arrTime[i];
    }
    return parseInt(sum);
}

//菜单栏当前选项
function current1(id, index) {
    var $id = $("#" + id);
    $id.addClass("currentcls").children(".secondMenu")
        .children("li").eq(index - 1).addClass("currentcls2");

}

function current2(id, index1, index2) {
    var $id = $("#" + id);
    $id.addClass("currentcls").children(".secondMenu").children("li")
        .eq(index1 - 1).addClass("currentcls1").children(".thirdMenu").children("li")
        .eq(index2 - 1).addClass("currentcls2");
}

//获取相应长度字符
function fix(num, length) {
    return ('' + num).length < length ? ((new Array(length + 1)).join('0') + num).slice(-length)
        : '' + num;
}

String.prototype.trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, "");
};

//获取下拉框的值
function getName() {
    var $th = $("#dg thead th");
    var name = "";
    var html = "";
    var startIndex = 7;
    var $selectArea = $(".selectArea ul");
    for (var i = 0; i < $th.length; i++) {
        var data = $th.eq(i).attr("data-options");
        var endIndex = data.indexOf("',width");
        html = $th.eq(i).html();
        name = data.substring(startIndex, endIndex).trim();
        if (html.indexOf("id") < 0) {
            $selectArea.append(
                '<li><input type="checkbox"  dataId=' + name + '  checked="checked"/>' + html
                + '</li>');
        }
    }
}

//控制输入数字长度 （手机号，身份证）
function chekNum(obj, num) {
    num = parseInt(num);
    obj.value = obj.value.replace(/[^\d.]/g, "");
    obj.value = obj.value.replace(/^\./g, "");
    obj.value = obj.value.replace(/\.{2,}/g, ".");
    obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
    if (obj.value.length > num) {
        obj.value = obj.value.substring(0, num);
    }
}

//控制只能是正数 （薪资发放）
function chekNumSalary(obj, num) {
    num = parseInt(num);
    obj.value = obj.value.replace(/[^\d.]/g, "");
    obj.value = obj.value.replace(/^\./g, "");
    obj.value = obj.value.replace(/\.{2,}/g, ".");
    /*	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");*/
    if (obj.value.length > num) {
        obj.value = obj.value.substring(0, num);
    }
}

//添加tab栏
function tabs(data) {
    var cookieStr = $.cookie("con");
    var cookieJson = JSON.parse(cookieStr);
    cookieJson = Splice(cookieJson);
    if (cookieJson.length > 6) {
        cookieJson.splice(0, 1);   //如果cookie里的个数大于6个，就把cookie中的第一个jsp删了
    }

    var s = cookielist(cookieJson, data);
    s.push(data);
    $(".mainCon h3 ul").append("<li><a href=" + data.url + " class='red' >" + data.title
        + "</a><em onclick='emdelete(this)'></em></li>");
    var cookieJsonStr = JSON.stringify(s);  //转化成字符串
    $.cookie("con", cookieJsonStr); //将值存入cookie
}

//删除tabs
function deltabs(obj) {
    var $this = $(obj);
    var title = $this.html();
    var $ul = $this.parent("li").parent("ul");
    var Alist = $ul.children("li").children("a");
    for (var b = 0; b < Alist.length; b++) {
        if (Alist.eq(b).html() == title) {
            var index = b;
        }
    }
    $ul.children("li").eq(index).remove();
    var cookieStr = $.cookie("con");
    var cookieJson = JSON.parse(cookieStr);
    cookieJson = Splice(cookieJson);
    var s = [];
    var l = {};
    for (var i = 0; i < cookieJson.length; i++) {
        if (title != cookieJson[i].title) {
            l = cookieJson[i];

        }
        s[i] = l;
    }
    var cookieJsonStr = JSON.stringify(s);  //转化成字符串
    $.cookie("con", cookieJsonStr); //将值存入cookie

}

//cookie 去重
function Splice(cookie) {
    var a = cookie;
    for (var i = 0; i < a.length; i++) {
        for (var j = i + 1; j < a.length; j++) {
            if (a[i].title == a[j].title) {
                a.splice(j, 1);

            }
        }
    }

    return a;

}

/*Array.prototype.removeRepeatAttr=function(){
 var tmp={},a=this.slice();
 for(var i=j=0;i<a.length;i++){
 if(!tmp[a[i].id]){
 tmp[a[i].id]=!0;
 j++;
 }else{
 this.splice(j,1);
 }
 };
 }
 var arr=[{"id":"1"}, {"id":"2"}, {"id":"1"}, {"id":"3"}, {"id":"2"}, {"id":"5"}, {"id":"3"}, {"id":"4"}, {"id":"2"}, {"id":"4"}];
 arr.removeRepeatAttr();*/


// 添加cookie
function cookielist(obj, data) {
    var cookieJson = obj;
    var s = [];
    var l = {};

    for (var i = 0; i < cookieJson.length; i++) {
        if (cookieJson[i].title != data.title && cookieJson[i].title != undefined) {
            l = cookieJson[i];
            var title = cookieJson[i].title;
            var url = cookieJson[i].url;
            $(".mainCon h3 ul").append("<li><a href=" + url + ">" + title
                + "</a><em  onclick='emdelete(this)'></em></li>");
        }
        s[i] = l;

    }
    return s;
}

//删除tabs

function emdelete(obj) {
    $this = $(obj);
    var a = $this.prev("a");
    deltabs(a);
}

//datagrid 自适应
function reSize(id, percent) {
    var $obj = $("#" + id);
    var rightArea = $(".rightArea");
    $obj.datagrid('resize', {
        width: getWidth(percent)
    });
    $(window).resize(function () {
        $obj.datagrid('resize', {
            width: getWidth(percent)
        });
    });
    //rightArea.css("height","2400px")
    //var p =  $obj.datagrid('getPager');
    //p.pagination({
    //    pageSize:100,
    //    pageList:[10,20,30,40,50,100]
    //});
}

//datagrid获取宽度
function getWidth(percent) {
    return $(window).width() * percent;
}

/*
 //生成菜单
 function menuP(data){
 var data = data;
 var pid ;
 var name;
 var url;
 var length=data.length;
 var html ="<ul>";
 for(var i = 0; i<length;i++){
 pid = data[i].pid;
 id= data[i].id;
 name = data[i].name;
 if(pid==0&& (i==length-1)){
 html+="<li><a class='level0' pId="+id+">"+name+"</a></li></ul>";
 }else if(pid==0){
 html+="<li><a class='level0' pId="+id+">"+name+"</a></li>";
 }

 }
 $("#smoothmenu1").append(html);
 var html1="<ul>";
 $(".level0").each(function(index, element) {
 //获取目标的父id
 var pId = $(this).attr("pid");
 for(var i = 0;i<length;i++){
 pid = data[i].pid;    // 获取数据的父id
 id= data[i].id;

 name = data[i].name;
 url = data[i].url;
 if(pId==pid){    //如果数据的父id 和 目标的父id相同，说明该数据是 目标的子li
 if(url!="null"){
 html1+="<li><a class='level1' href="+url+" pId="+id+">"+name+"</li>";
 }else{
 html1+="<li><a class='level1'  pId="+id+">"+name+"</li>";
 }

 }
 }
 $(this).parent("li").append(html1);
 $(this).parent("li").append("</ul>");
 html1="<ul>";
 });

 var html2="<ul>";
 $(".level1").each(function(index,element){
 var pId = $(this).attr("pid");
 for(var i = 0;i<length;i++){
 pid = data[i].pid;    // 获取数据的父id
 id= data[i].id;
 name = data[i].name;
 url = data[i].url;
 if(pId==pid){    //如果数据的父id 和 目标的父id相同，说明该数据是 目标的子li
 html2+="<li><a class='level2' href="+url+" pId="+pid+">"+name+"</li>";
 }
 }
 if(html2=="<ul>"){
 $(this).parent("li").append("");
 $(this).parent("li").append("</ul>");
 }else{
 $(this).parent("li").append(html2);
 $(this).parent("li").append("</ul>");
 }

 html2="<ul>";

 });



 };*/



//返回指定页面
function backUrl(url) {
    window.location.href = url;
}

//通过ajax获取指定城市
function getCity(id, url) {
    var select = $("#" + id);
    var html = "";
    $.ajax({
        url: url,
        dataType: 'json',
        async: false,
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var name = data[i].name;
                html += "<option value=" + name + ">" + name + "</option>";
            }
            select.append(html);
        }, error: function () {
            layer.alert("网络异常，稍后再试");
        }
    })
}

//通过ajax获取指定城市
function getCityCodes(id, url, id2) {
    var select = $("#" + id);
    var html = "";
    $.ajax({
        url: url,
        dataType: 'json',
        async: false,
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var name = data[i].name;
                var code = data[i].code;
                html += "<option value=" + code + ">" + name + "</option>";
            }
            select.append(html);
            if (id2) {
                $('#id2').append(html);
            }
        }, error: function () {
            layer.alert("网络异常，稍后再试");
        }
    })
}

//通过ajax获取品牌
function getBrand(id, url) {
    var select = $("#" + id);
    var html = "";
    $.ajax({
        url: url,
        dataType: 'json',
        async: false,
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                if (data[i] != null) {
                    var brand = data[i].brand;
                    html += "<option value=" + brand + ">" + brand + "</option>";
                }

            }
            select.append(html);
        }, error: function () {
            layer.alert("网络异常，稍后再试");
        }

    })
}

//获取车型1
function getType(obj, id, url) {
    var $this = $(obj);
    var brandValue = $this.serialize();
    var value = $this.value;
    var select = $("#" + id);
    var html = "<option value=''>--请选择--</option>";
    $.ajax({
        url: url,
        type: "post",
        dataType: 'json',
        data: brandValue,
        async: false,
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                if (data[i] != null) {
                    //var type = data[i].mode;
                    html += "<option value=" + data[i].id + ">" + data[i].mode + "</option>";
                }
            }
            select.val(null).trigger("change").html(html);
        }

    })
}

//获取公司
function getCompany(id, url) {
    var select = $("#" + id);
    var html = "";
    $.ajax({
        url: url,
        dataType: 'json',
        async: false,
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                if (data[i] != null) {
                    var id = data[i].id;
                    var name = data[i].name;
                    html += "<option value=" + id + ">" + name + "</option>";
                }
            }
            select.append(html);
        }, error: function () {
            layer.alert("网络异常，稍后再试");
        }

    })
}

//获取处理日志
function getLog(id, url, type) {
    var $div = $("#" + id);
    var labName;
    if (type == "1") {
        labName = "处理";
    } else {
        labName = "回访";
    }

    var html = "";
    $.ajax({
        url: url,
        dataType: 'json',
        type: 'post',
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var column = type == '1' ? 'deal' : 'return';
                var time = data[i][column + 'TimeStr'];
                var name = data[i][column + 'Person'];
                var suggest = data[i][column + 'Result'];

                if (i != data.legnth - 1) {
                    html +=
                        '<div class="complaintcls1"><div class="mainConcls2 clearfix1">' +
                        '<label>' + labName + '时间：</label><label class="mainConcls5">' + time
                        + '</label></div>' +
                        '<div class="mainConcls2 clearfix1"><label>' + labName
                        + '人：</label> <label class="mainConcls5" style="width:80%">' + name
                        + '</label></div>' +
                        '<div class="mainConcls2 clearfix1"><label>' + labName
                        + '意见：</label> <label class="mainConcls5" style="width:80%">'
                        + suggest + '</label></div></div>';
                } else {
                    html +=
                        '<div class="complaintcls1 complaintcls2"><div class="mainConcls2 clearfix1">'
                        +
                        '<label>' + labName + '时间：</label><label class="mainConcls5">' + time
                        + '</label></div>' +
                        '<div class="mainConcls2 clearfix1"><label>' + labName
                        + '人：</label> <label class="mainConcls5" style="width:80%">' + name
                        + '</label></div>' +
                        '<div class="mainConcls2 clearfix1"><label>' + labName
                        + '意见：</label> <label class="mainConcls5" style="width:">' + suggest
                        + '</label></div></div>';
                }

            }
            $div.html(html);
        }
    })
}

//转换成时间戳
function getDate(time) {

    var date = time;
    date = new Date(Date.parse(date.replace(/-/g, "/")));
    date = date.getTime().toString().substring(0, 10);

    return date;

}

//根据年获取周
function getWeek(id, url) {
    var time = getDate($("#year").val());
    url = url + "?time=" + time;
    var select = $("#" + id);
    var html = "<option value=''>--请选择--</option>";
    $.ajax({
        url: url,
        dataType: 'json',
        success: function (data) {
            //console.log(data)
            for (var i = 0; i < data.length; i++) {
                if (data[i] != null) {
                    var week = data[i].weekOfYear;
                    var start = data[i].startStr.substring(5).replace("-", ".");
                    var end = data[i].endStr.substring(5).replace("-", ".");
                    html +=
                        "<option value=" + week + " >" + week + "&nbsp;&nbsp;&nbsp;[ "
                        + start + "--" + end + " ]</option>";
                }

            }
            select.val(null).trigger("change").html(html);
        }, error: function () {
            layer.alert("网络异常，稍后再试");
        }

    })
}

//根据年月获取获取周
function getWeek1(id, url) {
    var year = $("#year").val();
    var month = $("#month").val();
    var time = year + "-" + month + "-01";
    time = getDate(time);
    url = url + "?time=" + time;
    var select = $("#" + id);
    var html = "<option value=''>--请选择--</option>";
    $.ajax({
        url: url,
        dataType: 'json',
        success: function (data) {
            //console.log(data)
            for (var i = 0; i < data.length; i++) {
                if (data[i] != null) {
                    var week = data[i];
                    html += "<option value=" + week + ">" + week + "</option>";
                }

            }
            select.val(null).trigger("change").html(html);
        }, error: function () {
            layer.alert("网络异常，稍后再试");
        }

    })
}

//获取按钮权限
function getBtn(pageName) {
    var data = "";
    var url = "../privilege/queryButtonForPage.do?pageName=" + pageName;
    $.ajax({
        url: url,
        type: 'post',
        dataType: 'json',
        async: false,
        success: function (result) {

            for (var i = 0; i < result.length; i++) {
                data += result[i];
            }
            $("#hidden").html(data);
        }, error: function () {
            layer.alert("网络异常，请稍后再试")
        }

    });

    return data;
}

function imgBigger() {
    setTimeout("showBigger()", 3000);
}

function showBigger() {

    $(".photoCls").each(function () {

        var srcImg = new Image(); //新建一个图像对象
        var src = $(this).attr("src");

        srcImg.src = $(this).attr("src"); //指定新图像对象源为当前图片的src

        var srcWidth = srcImg.width; //图像原尺寸宽度，这是js，非jQuery

        if (srcImg.width >= $(this).width()) { //如果原尺寸宽度大于等于当前显示的宽度
            $(this).attr('title', '点击查看大图'); //鼠标在当前图片上时提示
            $(this).bind("click", function () { //左键点击图片
                $("body").append(
                    '<div class="viewer viewer1"><img src="' + src + '" title="点击关闭" /></div>'); //页面底部增加原图的元素
                //设置高度小于窗口高度的图片居中显示
                if ($(".viewer").height() > $(".viewer").children("img").height()) {
                    //设置图片margin-top属性等于窗口高度/2-图片高度/2
                    $(".viewer").children("img").css("margin-top",
                        $(".viewer").height() * 0.5 - $(".viewer")
                            .children(
                                "img")
                            .height()
                        * 0.5);
                }

                //单击查看视图移除增加的查看视图元素
                $(".viewer").click(function () {
                    $(this).remove();

                })
            });
        }
    });
}

$.ajaxSetup({
    complete: function (XMLHttpRequest, textStatus) {
        var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus"); //通过XMLHttpRequest取得响应头,sessionstatus，
        if (sessionstatus == 'timeout') {
            //如果超时就处理 ，指定要跳转的页面
            var top = getTopWindow(); //获取当前页面的顶层窗口对象
            /*alert('登录超时, 请重新登录.'); */
            top.location.href = "../backend/login.jsp?sessionstatus=timeout"; //跳转到登陆页面
        }
    }
});

/**
 * 在页面中任何嵌套层次的窗口中获取顶层窗口
 * @return 当前页面的顶层窗口对象
 */
function getTopWindow() {
    var p = window;
    while (p != p.parent) {
        p = p.parent;
    }
    return p;
}

function getFormData(select) {
    var jq = $(select).find('input,select,textarea');
    var param = {};
    jq.each(function () {
        param[this.name] = this.value;
    });
    return param;
}
