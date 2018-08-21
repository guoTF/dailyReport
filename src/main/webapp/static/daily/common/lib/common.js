/**
 * lxr自定义公共基础信息提示模块
 * @author fang
 */
layui.define([
    'layer'
], function(exports) {
    'use strict';
   var $ =layui.jquery,
        layer = layui.layer;

    var lxrCommon ={
        /**
         * 错误提示模块
         */
        lxrErrormsg:function(msg){
        	layer.msg(msg,{
        		icon:2,
        		resize:false,
        		time: 2000,
        		zIndex: layer.zIndex,
        		anim: Math.ceil(Math.random() * 6)
        	})
        	return;
        },
        reqError:function(data){
        	if(typeof data!="object"){
        		var data = JSON.parse(data);
        	}
        	if(!data.success){
        		if(data.hasOwnProperty('errorMessage')){
        			this.lxrErrormsg(data.errorMessage);
            	}else{
            		this.lxrErrormsg('执行失败')
            	}
        		return true
        	}else{
        		return false;
        	}
        	
        },
        lxrInfo: function(msg,title){
            layer.alert(msg, {
                title: title,
                icon: 6,
                time: 0,
                resize: false,
                zIndex: layer.zIndex,
                anim: Math.ceil(Math.random() * 6)
            });
            return;
        },
        logOut:function(){

        },
        //发送ajax请求
        sendRequest:function(url,params,cb){
        	var indexlyer;
            $.ajax({
               type:'post',
               url: host + url,
               data:params,
               beforeSend:function () {
                   indexlyer= layer.load();
               },
               success:function(data){
            	   layer.close(indexlyer);
            	   if(typeof cb =='function'){
            		   cb(data)
            	   }else{
            		   this.lxrErrormsg('传参错误')
            	   }
               },
               error: function (err) {
                   layer.msg('请求出错',{icon:2,time:2000});
                   layer.close(indexlyer);
               },
               complete:function(){
            	   layer.close(indexlyer);
               }
           })
        }
    }
    exports('common',lxrCommon);
});
