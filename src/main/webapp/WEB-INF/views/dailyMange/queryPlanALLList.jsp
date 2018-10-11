<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>项目成员工作计划、日志查询</div>
<div class="List" id="List">
	<query-list></query-list>
	<!-- <show-list></show-list> -->
</div>
<!-- 查询条件 -->
<template id="queryList">
	<div class="query">
		<table class="table table-bordered" style="width:100%">
			<tr class="query-title info">
				<td colspan="4">查询条件</td>
			</tr>
			<tr>
				<th width="30%">项目组:</th>
				<td colspan="3">
					<select class="form-control" v-model="form.team">
						<option value="">请选择</option>
						<option value="1">11</option>
						<option value="2">12</option>
					</select>
				</td>
			</tr>
			<tr>
				<th >项目组成员:</th>
				<td colspan="3">
					<select class="form-control" v-model="form.member"><!-- style="height:38px;" -->
						<option value="">请选择</option>
						<option>12</option>
						<option>12</option>
					</select>
				</td>
			</tr>
			<tr class="form-inline">
				<th>日期:</th>
				<td colspan="3">
					<input type="text" class="form-control" id="all-startDay" ref="start"/> 至 
					<input type="text" class="form-control" id="all-endDay" ref="end"/>
					
				</td>
			</tr>
			<tr >
				<td >
				<label>查询内容:</label>
					<label class="radio-inline">
					  <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1" v-model="form.queryContent">计划
					</label>
					<label class="radio-inline">
					  <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1" v-model="form.queryContent">日志
					</label>
					<label class="radio-inline">
					  <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1" v-model="form.queryContent">综合
					</label>
				</td>
				<td >
					<label>
					  查询方式:
					</label>
					<label class="radio-inline">
					  <input type="radio" name="inlineRadioOptionsl" id="inlineRadio1" value="option1" v-model="form.queryStyle">日志
					</label>
					<label class="radio-inline">
					  <input type="radio" name="inlineRadioOptionsl" id="inlineRadio2" value="option1" v-model="form.queryStyle">综合
					</label>
				</td>
				<td class="" colspan="2">
					<input type="text" class="form-control" placeholder="关键词搜索" v-model.trim ="form.keywords"/>
				</td>
			</tr>
			<tr>
				<td colspan="4" class="text-center"><button type="button" class="btn btn-primary btn-sm" @click="serachPlan">查询</button></td>
			</tr>
		</table>
		<!--列表查询  -->
	<div class="showList">
	     <div class="show-list-title">工作日志,查询列表</div>
		<div class="show-list-item">
			<div class="show-item-date"><span>工作日期:</span>2018-2-9</div>
			<table class="table table-bordered" style="width:100%">
				<thead>
					<tr class="info">
						<th>姓名</th>
						<th>序号</th>
						<th>部门</th>
						<th>项目名称</th>
						<th>重大里程碑</th>
						<th>任务编号</th>
						<th>工作内容</th>
						<th>工作类型</th>
						<th>占用时间</th>
						<th>工作结果</th>
						<th>是否加班</th>
						<th>备注</th>
					</tr>
				</thead>
				<tbody>
					<tr  v-for="(item,index) in arr" >
						<td>{{item.userName}}</td>
						<td>{{index+1}}</td>
						<td>{{item.userName}}</td>
						<td>{{item.planProjectName}}</td>
						<td>{{item.planGoal}}</td>
						<td>{{item.userName}}</td>
						<td>{{item.planContent}}</td>
						<td>{{item.report_type_id}}</td>
						<td>{{item.planTime}}</td>
						<td>{{item.report_goal}}</td>
						<td>{{item.report_timeStr}}</td>
						<td>{{item.remark}}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	</div>
	
</template>

<!-- 列表内容展示 -->
<!-- <template id="showList">
	
</template> -->
<script>

   	var queryList ={
   		template:'#queryList',
   		data:function(){
   			return {
   				/* arr:[{
   					'userName':'fabf',
   					'reportProjectName':'ffada',
   					'reportContent':'dsfdf',
   					'reportTime':'2018-8-15',
   					'reportTimestr':'20180501',
   					'reportTypeId':'2dsf',
   					'reportGoal':'的沙发斯蒂芬是法定ds',
   					'remark':'的所得税的所',
   					'reportDay':'0215-05-16'
   				}], */
   				arr:[],
   				form:{
   					team:"", // 项目组名称
   					member:"" ,//项目组成员
   				     queryContent:"",//查询内容
   				     queryStyle:"",//查询方式
   				     keywords:"" //关键词查询
   				}
   			}
   		},
   		methods:{
   			serachPlan:function(){
   				var that =this;
   				//console.log(this.$refs)
   				var obj ={
   					 beginDate:this.$refs.start.value,
    					endDate:this.$refs.end.value,
    					userName:'fang',
    					pageNo:1,
    					pageSize:10 
   				}
   				that.getDataList(obj).then(function(datas){
   					console.log(that.$data)
   					that.$data.arr = datas.list;
   					
   				}).catch(function(err){
   					console.log(err)
   				});
   				
   			},
   			getDataList:function(obj){
   				var index;
   				return new Promise(function(resolve,reject){
   					$.ajax({
   						url:host+'/dailyQueryAll/getPlanALLList',
   						type:'post',
   						data:JSON.stringify(obj),
	   					 success: function(data){
	   		                resolve(data);
	   		            },
	   		            beforeSend:function(){
	   		            	index =layer.load(2);
	   		            },
	   		            error: function(error){
	   		                reject(error)
	   		            },
	   		            complete:function(){
	   		            	layer.close(index)
	   		            }
   					})
   				})
   			}
   		}
   	};
   	var vm =new Vue({
   		el:'#List',
   		components:{
   			'queryList':queryList,
   		}
   	})
	layui.use('laydate',function(){
		var laydate =layui.laydate;
		
		laydate.render({
			elem:'#all-startDay',
			
		})
		 laydate.render({
			elem:'#all-endDay',
			
		}) 
	})
	
</script>