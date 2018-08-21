$(function(){
	//定义弹出层变量
	var changePswForm;
	$("#change_password").click(function(){
		$("#confirm_changepsw").css("background-color","#a2a2a2").css("border-color","#a2a2a2");
		$("input","#changepswForm").each(function(){
    		$(this).val("");
    	});
		changePswForm=layer.open({
		  title:'修改密码',
		  type: 1,
		  content: $('.passwordForm') 
		});
	});

	$("#oldPassword").change(function(){

		if($.trim($(this).val())==""){
			layer.msg('原密码不能为空！', {icon: 5});
			$(this).addClass('am-form-flag');
			$("#confirm_changepsw").css("background-color","#a2a2a2").css("border-color","#a2a2a2");
			$("#confirm_changepsw").unbind('click');
			return false;
		}
		var changePswPatt = /\w{4,20}/;
        if(!changePswPatt.test($(this).val())){
			layer.msg('原密码输入不正确！', {icon: 5});
			$(this).addClass('am-form-flag');
			$("#confirm_changepsw").css("background-color","#a2a2a2").css("border-color","#a2a2a2");
			$("#confirm_changepsw").unbind('click');
			return false;       	
        }
        $(this).removeClass('am-form-flag');
	});

	$("#newPassword").change(function(){

		if($(this).val()==""){
			layer.msg('密码不能为空！', {icon: 5});
			$(this).addClass('am-form-flag');
			$("#confirm_changepsw").css("background-color","#a2a2a2").css("border-color","#a2a2a2");
			$("#confirm_changepsw").unbind('click');			
			return false;
		}
		var changePswPatt = /\w{4,20}/;
        if(!changePswPatt.test($(this).val())){
			layer.msg('密码输入不正确！', {icon: 5});
			$(this).addClass('am-form-flag');
			$("#confirm_changepsw").css("background-color","#a2a2a2").css("border-color","#a2a2a2");
			$("#confirm_changepsw").unbind('click');
			return false;       	
        }
        if($("#confirmPassword").val()!="" && $(this).val()!=$("#confirmPassword").val()){
        	layer.msg('两次密码输入不一致', {icon: 5});
			$(this).addClass('am-form-flag');
			$("#confirm_changepsw").css("background-color","#a2a2a2").css("border-color","#a2a2a2");
			$("#confirm_changepsw").unbind('click');
        	return false;
        }
        $(this).removeClass('am-form-flag');

	});
    $("#confirmPassword").change(function(){

		if($(this).val()==""){
			layer.msg('密码不能为空！', {icon: 5});
			$(this).addClass('am-form-flag');
			$("#confirm_changepsw").css("background-color","#a2a2a2").css("border-color","#a2a2a2");
			$("#confirm_changepsw").unbind('click');
			return false;
		}
		var changePswPatt = /\w{4,20}/;
        if(!changePswPatt.test($(this).val())){
			layer.msg('密码输入不正确！', {icon: 5});
			$(this).addClass('am-form-flag');
			$("#confirm_changepsw").css("background-color","#a2a2a2").css("border-color","#a2a2a2");
			$("#confirm_changepsw").unbind('click');
			return false;       	
        }
        if($("#newPassword").val()!="" && $(this).val()!=$("#newPassword").val()){
        	layer.msg('两次密码输入不一致', {icon: 5});
			$(this).addClass('am-form-flag');
			$("#confirm_changepsw").css("background-color","#a2a2a2").css("border-color","#a2a2a2");
			$("#confirm_changepsw").unbind('click');
        	return false;
        }
        $(this).removeClass('am-form-flag');

	});
    $("input","#changepswForm").change(function(){
    	var goFlag=true;
    	$("input","#changepswForm").each(function(){
    		if($(this).hasClass('am-form-flag')){
    			goFlag=false;
    		}
    	});
    	if(goFlag){
    		$("#confirm_changepsw").css("background-color","#297d9f").css("border-color","#297d9f");
    		$("#confirm_changepsw").unbind('click');
	    	$("#confirm_changepsw").on('click',function(){
	    		// console.log("原密码："+$("#oldPassword").val());
	    		// console.log("新密码："+$("#newPassword").val());
					$.ajax({
						dataType:'json',
						type:'post',
						url:globalPath +'/userManager/resetPassword.do',
						data:{
							oldPassword:$("#oldPassword").val(),
							newPassword:$("#newPassword").val()
						},
						success:function(data){
							layer.close(changePswForm);
							if(data.success){
								layer.msg('密码修改成功！',{ icon:6 });
							}else{
								layer.msg(data.errorMessage,{ icon:5 })
							}
						}

					});
				});
    	}
    });
});