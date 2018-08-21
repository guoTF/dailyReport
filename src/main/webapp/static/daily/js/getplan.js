layui.use(['layer','common','form','laydate'],function(){
	var layer =layui.layer,
	common =layui.common,
	laydate =layui.laydate,
	form =layui.form;
	
laydate.render({
	  elem: '#test2'
	  ,position: 'static'
	  ,change: function(value, date){ //监听日期被切换
	    lay('#testView').html(value);
	  }
	});
//列表查询计划
    $.post(host+'/dailyPlan/getPlan',{day:GetDateStr(0)},function(data){
    	console.log(data)
    });
    //添加
    $("#addBtn").on('click',function(){
    	var len =$("#planList tr","#getplan").length;
    	var len =len++;
    	var html ='<tr class="editplan" id="trwrap">'+
    	'<td><a href="javascript:void(0);">删除</a></td>'+
        	'<td>'+(len+1)+'</td>'+
        	'<td><textarea name="planContent" id="" cols="25" style="width: 100%;height: 74px;resize: none;"></textarea></td>'+
        	'<td><textarea name="planGoal" id="" cols="25" style="width: 100%;height: 74px;resize: none;"></textarea></td>'+
        	'<td><input type="number" name="planTime"/></td>'+
    '</tr> ';
    	$("#planList").append(html);
    });
    
    //保存
    $("#saveBtn").on('click',function(){
    	var len =$("#planList .editplan").lenth;
    	if(len!=0){
    		var planContent =$("#planList .editplan").find('[name="planContent"]').val();
    		var planGoal =$("#planList .editplan").find('[name="planGoal"]').val();
    		var planTime =$("#planList .editplan").find('[name="planTime"]').text();
    		var obj={
    				planContent:planContent,
    				planGoal:planGoal,
    				planTime:planTime,
    				planDay:'2018-08-11',
    				planProjectName:'sdffsd',
    				planNum:2,
    				planTime:24
    				
    		};
    		var planStr =[];
    		planStr.push(obj);
    		$.post(host+'/dailyPlan/savePlan',{planStr:JSON.stringify(planStr)},function(data){
    	    	console.log(data)
    	    });
    	}
    	
    })
    //工作日志填写
    laydate.render({
    	 elem: '#planday'
    });
    //个人日志查询
    laydate.render({
   	 elem: '#planStartday'
   });
    laydate.render({
      	 elem: '#planEndday'
      });
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
