layui.config({
		base:host+'/static/daily/common/lib/'
	}).extend({
	    common:'common'
	});
layui.use(['layer','common','form'],function(){
	var layer =layui.layer,
		common =layui.common,
		form =layui.form;

	$(function(){
		getImgCode();
		$("#validateImg").on('click',function(){
			getImgCode();
		});
		//请求部门和监听部门选择
		requestRep(function(data){
				var html;
			
			if($("#departmentId").length>0){
					$.each(data,function(index,item){
						html+='<option value='+item.name+' data-id='+item.id+'>'+item.name+'</option>'
					})
					$("#departmentId").append(html);

					$("#departmentId").on('change',function(res){
							var id =$(this).find('option:checked').attr("data-id");
							if(id){
								var post="";
								common.sendRequest('/login/getPost',{'depId':id},function(result){
									$.each(result,function(index,item){
										post+='<option value='+item.name+' data-id='+item.id+'>'+item.name+'</option>'
									})
									$("#post").html(post);
								})
							}
					})
				}
			});
		//
		//登录
		$("#submitForm").on('click',function(){
			
			var userName =$("#userName").val();
			var password =$("#password").val();
			var identifyingCode =$("#identifyingCode").val();
			if(userName ==''){
				common.lxrErrormsg('用户名不能为空');
				getImgCode();//图片验证码
				return false;
			}
			if(password ==''){
				common.lxrErrormsg('登录密码不能为空');
				getImgCode();//图片验证码
				return false;
			}
			if(identifyingCode ==''){
				common.lxrErrormsg('验证码不能为空');
				getImgCode();//图片验证码
				return false;
			}
			if(userName.length>25){
				common.lxrErrormsg('用户名长度不能超过25位');
				getImgCode();//图片验证码
				return false;	
				}
			if(password.length<6){
				common.lxrErrormsg('登录密码长度不少于6位');
				getImgCode();//图片验证码
				return false;
			}
			
			var serializeResult =$("#serializeForm").serialize();
			
			common.sendRequest('/login/login',serializeResult,function(data){
				if(common.reqError(data)){	getImgCode();return false;}
				layer.msg(data.data,{icon:6},function(){
					window.location.href=host+'/login/toIndex'
				})
			})
			return false;
		});
		//发送手机验证码
		$("#sendMobile").on('click',function(){
			var phone =$('#phoneNum').val();
			debugger
			 var that = this;
			  $.post(host+"/login/sendMobileCode", {
                phone: $('#phoneNum').val()
            }, function (data, textStatus, jqXHR) {
              if(!data.success){layer.msg(data.errorMessage);return false;}
                // 发送成功
                $(that).off('click');
                var time = 60; // 发送间隔
                $(that).css({
                    'background-color': '#ccc',
                    'cursor': 'not-allowed'
                }).text('已发送 '+ time +' s');
                let jishi = setInterval(function () {
                    $(that).text('已发送 '+ --time +' s');
                    if (time == 0) {
                        clearInterval(jishi);
                        $(that).on('click', yancodeInterval).text('重发验证码').css({
                            'background-color': '',
                            'cursor': 'pointer'
                        });
                    }
                }, 1000);
                layer.msg("短信验证码发送成功！", { icon: 1, time: 1500 });
	          });
			  return false;
		});
		//注册
		$("#submitResiger").on('click',function(){
			
			var flag =notNull();
			if(!flag){return false};
			if(!chkMobile($("#phoneNum").val())){
				common.lxrErrormsg("手机格式不正确");
				getImgCode();//图片验证码
				return false;
			}
			if($("#passwordResiger").val()!=$("#password2").val()){
				common.lxrErrormsg("两次密码不一致");
				getImgCode();//图片验证码
				return false;
			}
			if(!checkEmail($("#mail").val())){
				common.lxrErrormsg("邮箱格式不正确");
				getImgCode();//图片验证码
				return false;
			}
			if($("#departmentId").val()!=""){
				common.lxrErrormsg("请选择部门");
				getImgCode();//图片验证码
				return false;
			}
			var mail =$("#mail").val();
			var serializeResult =$("#serializeResiger").serialize();
			var result = decodeURI(serializeResult);
			var resultO =result+'&'+'mail='+mail;
		
			console.log(serializeResult);
			var obj =conveterParamsToJson(resultO);
			
			common.sendRequest('/login/register',obj,function(data){
				if(common.reqError(data)){getImgCode();return false;}
				layer.msg(data.data,function(){
					window.location.href=host+"/login";
				})
			})
			return false;
		});
		
		
		
	})
	//请求部门
		function requestRep(cb){
			
			common.sendRequest('/login/getDepartment','',function(data){
				
				typeof cb =='function'&&cb(data)
			});
		}

//序列化的参数转对象
function conveterParamsToJson(paramsAndValues) {
	var jsonObj = {};
 
	var param = paramsAndValues.split("&");
	for ( var i = 0; param != null && i < param.length; i++) {
		var para = param[i].split("=");
		jsonObj[para[0]] = para[1];
	}
 
	return jsonObj;
}
//验证非空‘
function notNull(){
	var flag =true;
	$(".required").each(function(item){
		if($.trim($(this).val())===''){
			layer.msg($(this).attr('placeholder')+"为空",{icon:2});
			flag =false;
			return false;
		}
		if($.trim($(this).val()).length>25){
			layer.msg("字符串长度不能过长",{icon:2});
			flag =false;
			return false;
		}
	});
	
	return flag;
}
 //手机验证
    function chkMobile(mobileNum){
        var mobile =/^1[3|5|8]\d{9}$/ ,phone = /^0\d{2,3}-?\d{7,8}$/;
        if(mobile.test(mobileNum)||phone.test(mobileNum)){
            return true;
        }else{
            return false;
            
        }
    }
    //验证邮箱
    function checkEmail(email){
    	var pattern =/^\w+@\w+(\.\w+)+$/;
    	if(!pattern.test(email)){
    		return false;
    	}else{
    		return true;
    	}
    }

	//点击切换验证码
	function getImgCode(){
		$("#validateImg").attr("src",host+"/common/getImgCode?timestamp="+ (new Date()).valueOf())
	}


})