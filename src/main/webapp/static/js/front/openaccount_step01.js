//倒计时
        var countdown=60,timeCount;
        var account_flag=false;//企业管理账号标识
        var password_flag=false;
        var confirm_password_flag=false;
        var phone_flag=false;
        var phonecode_flag=false;
        var valicode_flag=false;
        var service_flag=false;
        function setTime(obj) {
        if (countdown == 0) {
            clearTimeout(timeCount);
            $("#getCode").click(getMobileCode);
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
            setTime(obj)
            },1000);
        }
        //获取手机验证码
        function getMobileCode(){
                if(phone_flag){
                    $.ajax({
                                type:'post',
                                dataType:'json',
                                data:{
                                    mobile:$("#phone").val(),
                                    bizSource:1,
                                },
                                url:'${path}/customer/getMobileCode',
                                success:function(data){
                                    if(data==0){
                                        layer.msg("用户已注册!",{icon:6,time:3000});
                                    }else if(data==1){
                                        layer.msg("验证码发送成功!",{icon:6,time:3000});
                                        $(this).unbind("click");
                                        setTime($("#getCode"));
                                    }else if(data==2){
                                        layer.msg("验证码发送失败!",{icon:6,time:3000});
                                    }else{
                                        layer.msg("同一手机号发送超过10条!",{icon:6,time:3000});
                                    }
                                }
                    });
                }else{
                    layer.msg("请输入手机号!",{icon:5,time:3000});
                }
            }
        $(function(){
            //企业管理账号验证
            var account=$("#account");
            account.keyup(function(){
                var testRegExp=/^\w{6,20}$/;
                if($(this).val().length>20){
                    $(this).val($(this).val().substr(0,20));
                }
                if(testRegExp.test($(this).val())){
                    $(this).parent().siblings().eq(1).find("span span").removeClass().addClass("right").html("管理账号输入正确！");
                    account_flag=true;

                }else{
                    $(this).parent().siblings().eq(1).find("span span").removeClass().addClass("wrong").html("请输入正确的企业管理账号");
                    account_flag=false;

                }
            });
            //管理密码验证
            var password=$("#password");
            var confirm_password=$("#confirm_password");
            password.keyup(function(){
                var testRegExp=/^\w{6,20}$/;
                if($(this).val().length>20){
                    $(this).val($(this).val().substr(0,20));
                }
                if(confirm_password.val()==""){
                    if(testRegExp.test($(this).val())){
                        $(this).parent().siblings().eq(1).find("span span").removeClass().addClass("right").html("密码输入正确！");
                        password_flag=true;

                    }else{
                        $(this).parent().siblings().eq(1).find("span span").removeClass().addClass("wrong").html("请输入正确的密码");
                        password_flag=false;

                    }
                }else{
                    if(testRegExp.test($(this).val()) && password.val()==confirm_password.val()){
                        $(this).parent().siblings().eq(1).find("span span").removeClass().addClass("right").html("密码输入正确！");
                        $("#confirm_password").parent().siblings().eq(1).find("span span").removeClass().addClass("right").html("密码输入正确！");
                        password_flag=true;
                        confirm_password_flag=true;

                    }else if(testRegExp.test($(this).val()) && password.val()!=confirm_password.val()){
                        $(this).parent().siblings().eq(1).find("span span").removeClass().addClass("wrong").html("两次密码输入不一致！");
                        password_flag=false;

                    }else{
                        $(this).parent().siblings().eq(1).find("span span").removeClass().addClass("wrong").html("请输入正确的密码");
                        password_flag=false;

                    }
                }
            });
            confirm_password.keyup(function(){
                var testRegExp=/^\w{6,20}$/;
                if($(this).val().length>20){
                    $(this).val($(this).val().substr(0,20));
                }
                if(confirm_password.val()==""){
                    if(testRegExp.test($(this).val())){
                        $(this).parent().siblings().eq(1).find("span span").removeClass().addClass("right").html("密码输入正确！");
                        confirm_password_flag=true;

                    }else{
                        $(this).parent().siblings().eq(1).find("span span").removeClass().addClass("wrong").html("请输入正确的密码");
                        confirm_password_flag=false;

                    }
                }else{
                    if(testRegExp.test($(this).val()) && password.val()==confirm_password.val()){
                        $(this).parent().siblings().eq(1).find("span span").removeClass().addClass("right").html("密码输入正确！");
                        $("#password").parent().siblings().eq(1).find("span span").removeClass().addClass("right").html("密码输入正确！");
                        confirm_password_flag=true;
                        password_flag=true;

                    }else if(testRegExp.test($(this).val()) && password.val()!=confirm_password.val()){
                        $(this).parent().siblings().eq(1).find("span span").removeClass().addClass("wrong").html("两次密码输入不一致！");
                        confirm_password_flag=false;

                    }else{
                        $(this).parent().siblings().eq(1).find("span span").removeClass().addClass("wrong").html("请输入正确的密码");
                        confirm_password_flag=false;

                    }
                }
            });
            //管理员手机号验证
            var phone=$("#phone");
            phone.keyup(function(){
                var testRegExp=/^\d{11}$/;
                if($(this).val().length>11){
                    $(this).val($(this).val().substr(0,11));
                }
                if(testRegExp.test($(this).val())){
                    $(this).parent().siblings().eq(1).find("span span").removeClass().addClass("right").html("手机号输入正确！");
                    phone_flag=true;

                }else{
                    $(this).parent().siblings().eq(1).find("span span").removeClass().addClass("wrong").html("请输入正确的手机号");
                    phone_flag=false;

                }
            });
            //短信验证码验证
            var phonecode=$("#phonecode");
            phonecode.keyup(function(){
                var testRegExp=/^\d{6}$/;
                if($(this).val().length>6){
                    $(this).val($(this).val().substr(0,6));
                }
                if(testRegExp.test($(this).val())){
                    phonecode_flag=true;

                }else{
                    phonecode_flag=false;

                }
            });
            //图片验证码验证
            var valicode=$("#valicode");
            valicode.keyup(function(){
                var testRegExp=/\d/;
                if(testRegExp.test($(this).val())){
                    valicode_flag=true;

                }else{
                    valicode_flag=false;

                }
            });
            //下一步点击事件
            $("#a_nextsubmit").click(function(){
                var checkvalue = false;
                if (account_flag == true) {
                    checkvalue = true;
                } else {
                    layer.msg("请输入企业管理账号",{icon:6,time:3000});
                    return false;
                }
                if (password_flag == true && confirm_password_flag==true) {
                    checkvalue = true;
                } else {
                    layer.msg("请输入管理密码",{icon:6,time:3000});
                    return false;
                }
                if (phone_flag == true) {
                    checkvalue = true;
                } else {
                    layer.msg("请输入管理员手机号",{icon:6,time:3000});
                    return false;
                }
                if (phonecode_flag == true) {
                    checkvalue = true;
                } else {
                    layer.msg("请输入短信验证码",{icon:6,time:3000});
                    return false;
                }
                if (valicode_flag == true) {
                    checkvalue = true;
                } else {
                    layer.msg("请输入图片验证码",{icon:6,time:3000});
                    return false;
                }
                if ($('#service').is(':checked')) {
                    checkvalue = true;
                } else {
                    layer.msg("请阅读联信融平台用户服务协议！",{icon:6,time:3000});
                    return false;
                }
                if(checkvalue){
                    $.ajax({
                            type:'post',
                            dataType:'json',
                            data:{
                                entId:location.href.split("=")[1], //获取企业ID
                                entMngAccount:$("#account").val(),
                                password:$("#password").val(),
                                mobile:$("#phone").val(),
                                ValidateCode:$("#valicode").val(),
                                mobileCoad:$("#phonecode").val(),
                                bizSource:1,
                            },
                            url:'${path}/customer/submitAcountInfo',
                            success:function(data){
                                if(data.success){
                                    window.location.href="${path}/port/openaccount_step02.jsp"
                                }else{
                                    layer.msg(data.errorCode,{icon:5,time:3000});
                                }
                            }
                        });
                    }
            });
            //手机验证码
            $("#getCode").click(getMobileCode);
            //图片验证码
            $("#img_validate").click(function(){
                $(this).attr("src","${path }/customer/getInviteValidateCode?bizSource=1&timestamp="+(new Date()).valueOf());
            });
        });