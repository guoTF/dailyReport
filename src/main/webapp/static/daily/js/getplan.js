layui.use(['layer','common','form','laydate'],function(){
	var layer =layui.layer,
	common =layui.common,
	laydate =layui.laydate,
	form =layui.form;
	
laydate.render({
	  elem: '#test2'
	  ,position: 'static'
	  ,ready:function(data){
		  console.log(data)
		 $("#testView","#getplan").text(data.year+"-"+data.month+"-"+data.date)
		  //lay('#testView').html(value.toString());
	  }
	  ,change: function(value, date){ //监听日期被切换
	    lay('#testView').html(value);
	  }
	});
//列表查询计划
    $.post(host+'/dailyPlan/getPlan',{day:GetDateStr(0)},function(data){
    	console.log(data)
    });
   
    //工作计划添加
    $("#addBtn","#getplan").on('click',function(){
    	var len =$("#planList tr","#getplan").length;
    	var len =len++;
    	var html ='<tr class="editplan" id="trwrap">'+
    	'<td><a href="javascript:void(0);" class="delone">删除</a></td>'+
        	'<td name="planNum">'+(len+1)+'</td>'+
        	'<td><input type="text" name="planProjectName"/></td>'+
        	'<td><textarea name="planContent" id="" cols="25" style="width: 100%;height: 74px;resize: none;"></textarea></td>'+
        	'<td><textarea name="planGoal" id="" cols="25" style="width: 100%;height: 74px;resize: none;"></textarea></td>'+
        	'<td><input type="number" name="planTime"/></td>'+
    '</tr> ';
    	$("#planList",'#getplan').append(html);
    	$(".delone",'#getplan #planList').on('click',function(){
    		$(this).parents('tr').remove();
    	})
    });
   
    //保存
    $("#saveBtn","#getplan").on('click',function(){
    	var editTr=$("#planList .editplan",'#getplan');
    	var len =$("#planList .editplan",'#getplan').length;
    
    	var planStr =[];
    	if(len!=0){
    		$.each(editTr,function(index,item){
    		var planContent =$(item).find('[name="planContent"]').val();
    		var planGoal =$(item).find('[name="planGoal"]').val();
    		var planTime =$(item).find('[name="planTime"]').val();
    		var planProjectName =$(item).find('[name="planProjectName"]').val();
    		var planNum =$(item).find('[name="planNum"]').text();
    		var planDay  =$("#testView").text();
    		var obj={
    				planContent:planContent,
    				planGoal:planGoal,
    				planTime:planTime,
    				planDay:planDay,
    				planProjectName:planProjectName,
    				planNum:planNum
    		};
    		
    		planStr.push(obj);
    		});
    		
    		$.post(host+'/dailyPlan/savePlan',{planStr:JSON.stringify(planStr)},function(data){
    	    	if(data.success){
    	    		layer.msg(data.data,{time:2000},function(){
    	    			$("#planList").empty();
    	    		})
    	    	}
    	    });
    	}
    	
    })
    //工作日志填写
    laydate.render({
    	 elem: '#reportPlanday'
    });
    //个人日志查询
    laydate.render({
   	 elem: '#planStartday'
   });
    laydate.render({
      	 elem: '#planEndday'
      });
  /* 工作日志填写*/
    $("#writeReportList").on('click',function(){
    	var planday =$("#planday").val();
    	
    	if(planday!=""){
    		if($(".tablePlan").hasClass('layui-hide')){
    			$(".tablePlan").removeClass('layui-hide').addClass('layui-show')
    		}
    	}else{
    		layer.msg('请选择日期',{icon:5})
    	}
    });
    $("#addBtnReport",'.tablePlan').on('click',function(){
    	var len =$("#planList tr",".tablePlan").length;
    	var len =len++;
    	var html ='<tr class="editplan" id="trwrap">'+
    	'<td><a href="javascript:void(0);" class="delone">删除</a></td>'+
        	'<td name="reportNum">'+(len+1)+'</td>'+
        	'<td><input type="text" name="reportProjectName"/></td>'+
        	'<td><textarea name="reportContent" id=""  style="width: 100%;height: 74px;resize: none;"></textarea></td>'+
        	'<td><input type="number" name="reportTime"/></td>'+
        	'<td><select name="reportTimestr"><option value="正常">正常</option><option value="加班">加班</option></select></td>'+
        	'<td><input type="text" name="reportTypeId"/></td>'+
        	'<td><textarea name="reportGoal" id="" style="width: 100%;height: 74px;resize: none;"></textarea></td>'+
        	'<td><textarea name="remark" id=""  style="width: 100%;height: 74px;resize: none;"></textarea></td>'+
    '</tr> ';
    	$("#planList",'.tablePlan').append(html);
    	$(".delone",'.tablePlan #planList').on('click',function(){
    		$(this).parents('tr').remove();
    	})
    });
    //保存个人日志
    $("#saveReport").on('click',function(){
    	var editTr=$("#planList .editplan",'.tablePlan');
    	var len =$("#planList .editplan",'.tablePlan').length;
    
    	var planStr =[];
    	if(len!=0){
    		$.each(editTr,function(index,item){
    		var reportContent =$(item).find('[name="reportContent"]').val();
    		var reportGoal =$(item).find('[name="reportGoal"]').val();
    		var reportTime =$(item).find('[name="reportTime"]').val();
    		var reportProjectName =$(item).find('[name="reportProjectName"]').val();
    		var reportNum =$(item).find('[name="reportNum"]').text();
    		var reportDay  =$("#reportPlanday").val();
    		var reportTimestr =$(item).find('[name="reportTimestr"]').find('option:selected').val();
    		var reportTypeId =$(item).find('[name="reportTypeId"]').val();
    		var remark =$(item).find('[name="remark"]').val();
    		var obj={
    				reportContent:reportContent,
    				reportGoal:reportGoal,
    				reportTime:reportTime,
    				reportDay:reportDay,
    				reportProjectName:reportProjectName,
    				reportNum:reportNum,
    				reportTimestr:reportTimestr,
    				reportTypeId:reportTypeId,
    				remark:remark
    		};
    		
    		planStr.push(obj);
    	})
    	
    	common.sendRequest('/dailyReport/saveReport',{reportStr:JSON.stringify(planStr)},function(data){
    		if(data.success){
	    		layer.msg(data.data,{time:2000},function(){
	    			$("#planList",'.tablePlan').empty();
	    		})
	    	}
    	})
    	}
    })
})    




//拿到当前日期
function GetDateStr(AddDayCount) {
	var dd = new Date();
	dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
	var y = dd.getFullYear();
	var m = dd.getMonth()+1;//获取当前月份的日期
	var d = dd.getDate();
	return y+"-"+m+"-"+d;
}
