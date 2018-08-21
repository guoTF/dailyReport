layui.config({
		base:host+'/static/daily/common/lib/'
	}).extend({
	    common:'common'
	});
layui.use(['layer','common','form','laydate'],function(){
	var layer =layui.layer,
	common =layui.common,
	laydate =layui.laydate,
	form =layui.form;
	
	//拿到页面
	function getHtml(url,selector){
		let indexlyer;
		$.ajax({
			 type:'get',
			 async:false,
	         url: host+url,
	         data:{},
	         beforeSend:function () {
	             indexlyer= layer.load();
	         },
	         success:function(data){
	        	 $(selector).html(data)
	         },
	         complete:function(){
	        	 layer.close(indexlyer);
	         }
		})
	}
	
	$(function(){
		getHtml("/login/toMain","#page-wrapper");
		//切换
		var oldTime=0;
		$("#toggleIndex").on('click',function(){
			var date =new Date();
			if((date-oldTime)>500){
				oldTime =new Date();
				if($("#sidebar").is(":hidden")){
					$("#page-wrapper").animate({
						'margin-left':'250px'
					},'500','linear',function(){
						$("#sidebar").show();
					});
					
				}else{
					$("#sidebar").hide(function(){
						$("#page-wrapper").animate({
							'margin-left':'0px'
						},'500','linear');
					})
				}
				
			}
			
		});
		//左侧菜单绑定点击事件
		$("#side-menu ul").on('click','li',function(e){
			var e =e||window.event;
			
			var url =$(e.currentTarget).data('url');
			getHtml(url,"#page-wrapper");
		});
	})
});
