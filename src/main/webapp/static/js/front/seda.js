//////////////////////用户验证的start

/*
*此部分代码为验证用户输入框的通用代码。
*checkList 控件验证对象列表
*ClassOK 验证OK显示class
*ClassNG 验证NG显示class
*/

//input textarea select 控件增加rule 属性进行验证 msg为验证错误时候提示信息。
// ajaxAction为后台动态验证信息，可以使用ashx文件方式验证
//rule规则内容可以多个验证方式进行，比如 int;nullable 多个验证之间用;隔开
SimpoValid = {
    //验证规则
    rules: {
        len: /\w{5,17}$/,//长度验证
        int: /^[1-9]\d*$/,
        number: /^[+-]?\d*\.?\d+$/,
        chs: /^[\u0391-\uFFE5]+$/, //验证中文字输入
        email: /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/, //Email正则表达式
        string: /^[\u0391-\uFFE5\w]+$/,//验证能输入 中文，英文字母，数字，和下划线
        stringsub: /^[a-zA-Z0-9\u4E00-\u9FA5]+$/,//只能包括中文字、英文字母、数字
        englishsub: /^[a-zA-Z0-9]+$/,//只能包括英文字母、数字.
        mobile: /^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/,//手机号码验证
        telephone: /^\d{3,4}?\d{7,8}$/,//请正确填写您的电话号码
        zipCode: /^[1-9]\d{5}$/,//邮编验证
        idCardNo: /^\d{15}(\d{2}[A-Za-z0-9])?$/,//请正确输入您的身份证号码
        intOrFloat: /^\d+(\.\d+)?$/,//请输入数字，并确保格式正确
        currency: /^\d+(\.\d+)?$/,//货币格式
        english: /^[A-Za-z]+$/,  //验证英文
        ip: /d+.d+.d+.d+/,//IP地址格式
        require: true
    },
    rulestr: "checkrule",
    //定义正取与否的样式 基本样式格式如下
    //{"rules":"","msg","","ajaxAction":"ssss","list":[{"id":"text1","ok":"cs1","ng":"cs2","default":"cs0"},{"id":"text1","ok":"cs3","ng":"cs4","default":"cs0"}]}
    //仅仅是验证本身控件，也可以直接多个属性定义
    //此属性必须制定。无法默认此属性
    jsonStr: true,//默认ruleStr传入为Json对面，false时候传入的是 字符串对象
    ruleList: "",//所有的需要修改规则的列表 
    checkList: function (data) {
        var str = eval("(" + data + ")"); // eval(SimpoValid.rules);
        SimpoValid.ruleList = str.list;
    },
    //初始化
    init: function () {
        $("input[" + SimpoValid.rulestr + "],select[" + SimpoValid.rulestr + "],textarea[" + SimpoValid.rulestr + "]").each(function () {
            //遍历需要验证的控件，一个控件的多个验证使用;隔开
            $(this).blur(function () { //控件失去焦点时进行验证
                SimpoValid.validOne($(this));
            });
        });
    },
    //自定义验证区域，的所有验证对象
    validall: function (str) {
        SimpoValid.ajaxCheckResult = true;
        var bl = true;
        $(str).each(function () { //遍历span 
            var validSpan = $(this);
            var to = validSpan.attr("id");
            var target;
            if (to) {
                target = $("input[id='" + to + "'][" + SimpoValid.rulestr + "],select[id='" + to + "'][" + SimpoValid.rulestr + "],textarea[id='" + to + "'][" + SimpoValid.rulestr + "]");
            }
            if (target) {
                if (validSpan.attr("" + SimpoValid.rulestr + "") != undefined)
                    if (!SimpoValid.validOne(target, validSpan)) {
                        bl = false;
                    }
            }
        });
        return bl && SimpoValid.ajaxCheckResult;
    },
    //验证全部，验证成功返回true
    valid: function () {
        SimpoValid.ajaxCheckResult = true;
        var bl = true;
        $("input[" + SimpoValid.rulestr + "],select[" + SimpoValid.rulestr + "],textarea[" + SimpoValid.rulestr + "]").each(function () { //遍历span
            var validSpan = $(this);
            var to = validSpan.attr("id");
            var target;
            if (to) {
                target = $("input[id='" + to + "'][rule],select[id='" + to + "'][rule],textarea[id='" + to + "'][" + SimpoValid.rulestr + "]");
            }
            if (target) {
                if (validSpan.attr(SimpoValid.rulestr) != undefined)
                    if (!SimpoValid.validOne(target, validSpan)) {
                        bl = false;
                    }
            }
        });
        return bl && SimpoValid.ajaxCheckResult;
    },

    //单个验证，验证成功返回true
    validOne: function (validSpan) {
        //SimpoValid.hilight(validSpan, 'default');//验证时先删除已有的高亮效果
        //如果rule是字符串直接验证。如果是json对象则需要正则验证        
        var rule = validSpan.attr(SimpoValid.rulestr);
        var msg;
        try {
            var str = eval("(" + rule + ")");//规则验证。
            SimpoValid.checkList(rule);
            SimpoValid.jsonStr = true;
            rule = str.rules;
            msg = str.msg == undefined ? "" : str.msg;
        } catch (e) {
            SimpoValid.jsonStr = false;
            msg = "";
        }
        var nullable = validSpan.attr(SimpoValid.rulestr).indexOf("require") == -1 ? false : true; //是否可为空

        var to = validSpan.attr("id");//获取id 控件的唯一标示
        var ajaxAction = validSpan.attr("ajaxAction");//定义ajax后台验证方法 使用此方法验证时候可以不写rule属性
        var restr = true;

        var target = validSpan;
        if (target) {
            //checkbox或radio  必须具备name属性 而且之验证是否为空
            if (target[0].tagName.toLowerCase() == "input" && target.attr("type") && (target.attr("type").toLowerCase() == "checkbox" || target.attr("type").toLowerCase() == "radio")) {
                var checkedInput = $("input[name='" + validSpan.attr("name") + "']:checked");
                if (nullable) {
                    if (checkedInput.length == 0) {
                        restr = restr && false;
                    }
                }
            }
                //input或select
            else if (target[0].tagName.toLowerCase() == "input" || target[0].tagName.toLowerCase() == "select" || target[0].tagName.toLowerCase() == "textarea") {
                var val = target.val();
                if (nullable)
                    if ($.trim(val) == "")
                        restr = restr && false;

                if (rule) {//nullable 不进行验证
                    $.each(rule.split(";"), function (i, n) {
                        if (n != "require") {//require 为非空验证。已经在上面进行验证，此处不在验证
                            if (n.substr(0, 3) != "len") {//针对非长度进行验证。
                                var reg = new RegExp(SimpoValid.getRule(n));
                                if (!reg.test(val))
                                    restr = restr && false;
                            }
                            else {
                                //长度验证  5,17    /\w{5,17}$/ 
                                var n1 = n.replace("len", "").split("_");
                                if (!(val.length >= n1[0] && val.length <= n1[1]))
                                    restr = restr && false;
                            }
                        }
                    });
                }
            }
        }
        if (!restr)
            SimpoValid.hilight(validSpan, "ng", msg);
        else {
            //只有上面验证都通过事后，才进行异步后台验证 
            if (ajaxAction) {
                SimpoValid.ajaxCheck(target, val, ajaxAction);
                if (!SimpoValid.ajaxCheckResult == true)
                    restr = restr && false;
            }
            else
                SimpoValid.hilight(validSpan, "ok");
        }
        return restr;
    },

    ajaxCheckResult: true,
    ajaxCheck: function (target, value, ajaxAction) {  
        var targetName = target.attr("name");
        var data = {};
        data[targetName] = value;

        $.ajax({
            url: ajaxAction,
            type: "POST",
            data: data,
            async: false,
            success: function (data) {
                if (data == "OK") {
                    SimpoValid.hilight(target, 'ok');
                    SimpoValid.ajaxCheckResult = true;
                }
                else {
                    SimpoValid.ajaxCheckResult = false;
                    SimpoValid.msg = data;
                    SimpoValid.hilight(target, 'ajax', data);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(textStatus);
            }
        });

    },

    //获取验证规则
    getRule: function (rulestr) {
        return this.rules[rulestr];
    },
    hilight: function (target, status, msg) {
        if (msg == undefined) msg = "";
        if (SimpoValid.jsonStr) {
            var strlist = SimpoValid.ruleList;
            if (strlist != undefined) {
                $.each(strlist, function (i, n) {
                    var spid = n.id;
                    var classng = n[status];
                    var cks = classng.indexOf(":") == -1 ? false : true;//判断是否需要修改span的显示内容
                    if (classng != "") {
                        if (cks) {
                            var cksb = classng.split(":");
                            var ckvalue = "";
                            if (cksb.length > 2) {
                                for (i = 1; i < cksb.length; i++)
                                    ckvalue += cksb[i];
                            }
                            else
                                ckvalue = cksb[1];
                            $("#" + spid).attr("class", cksb[0]);
                            $("#" + spid).html(ckvalue);
                        }
                        else {
                            $("#" + spid).attr("class", classng);
                        }
                    }
                    else
                        $("#" + spid).removeClass("class");
                });
            }
            if (msg != "" && status == 'ng') {
                target.bind("mouseover", function (e) {
                    SimpoValid.tips(target, msg, e);
                });
                target.bind("mouseout", function () {
                    SimpoValid.removetips();
                });
            }
            if (status == "ok" || status == "default")
                SimpoValid.removetips();
        } else {
            if (status == "ng")
                target.addClass("failvalid");
            else
                target.removeClass("failvalid");
        }
    },

    //显示提示
    tips: function (target, text, e) {
        var divtipsstyle = "position: absolute; z-index:99999; left: 0; top: 0; background-color: #dceaf2; padding: 3px; border: solid 1px #6dbde4; visibility: hidden; line-height:20px; font-size:12px;";
        $("body").append("<div class='div-tips' style='" + divtipsstyle + "'>" + text + "</div>");

        var divtips = $(".div-tips");
        divtips.css("visibility", "visible");

        var top = e.clientY + $(window).scrollTop() - divtips.height() - 18;
        var left = e.clientX;
        divtips.css("top", top);
        divtips.css("left", left);

        $(target).mousemove(function (e) {
            var top = e.clientY + $(window).scrollTop() - divtips.height() - 18;
            var left = e.clientX;
            divtips.css("top", top);
            divtips.css("left", left);
        });
    },

    //移除提示
    removetips: function () {
        $(".div-tips").remove();
    }
};

$(function () {//引用页面首先进行初始化
    SimpoValid.init();
});

(function ($) {
    $.fn.checkrule = function (data) { 
        $(this).attr("checkrule", data);
        $(this).blur(function () { //控件失去焦点时进行验证
            SimpoValid.validOne($(this));
        });
    };
})(jQuery);



//////////////////////用户验证的end