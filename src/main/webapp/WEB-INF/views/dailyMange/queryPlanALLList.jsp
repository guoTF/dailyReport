<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>项目成员工作计划、日志查询</div>
<div class="List" id="List">
	<query-list></query-list>
	<show-list></show-list>
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
				<td colspan="3"><!-- style="padding:0" -->
					<select class="form-control" ><!-- style="height:38px;" -->
						<option>12</option>
						<option>12</option>
						<option>12</option>
					</select>
				</td>
			</tr>
			<tr>
				<th >项目组成员:</th>
				<td class="form-inline" colspan="3">
					<input type="text" class="form-control"/>
					<button type="button" class="btn btn-default" @click="choseItem"><i class="glyphicon glyphicon-search" aria-hidden="true"></i></button>
				</td>
			</tr>
			<tr class="form-inline">
				<th>日期:</th>
				<td colspan="3">
					<input type="text" class="form-control" id="startDay"/> 至 
					<input type="text" class="form-control" id="endDay"/>
				</td>
			</tr>
			<tr >
				<td >
				<label>查询内容:</label>
					<label class="radio-inline">
					  <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1">计划
					</label>
					<label class="radio-inline">
					  <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1">日志
					</label>
					<label class="radio-inline">
					  <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1">综合
					</label>
				</td>
				<td >
					<label>
					  查询方式
					</label>
					<label class="radio-inline">
					  <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1">日志
					</label>
					<label class="radio-inline">
					  <input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option1">综合
					</label>
				</td>
				<td class="" colspan="2">
					<input type="text" class="form-control" placeholder="关键词搜索"/>
				</td>
			</tr>
			<tr>
				<td colspan="4" class="text-center"><button type="button" class="btn btn-primary btn-sm">查询</button></td>
			</tr>
		</table>
	</div>
</template>

<!-- 列表内容展示 -->
<template id="showList">
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
					<tr v-for="(item,index) in arr">
						<td>{{item.userName}}</td>
						<td>{{index+1}}</td>
						<td>{{item.userName}}</td>
						<td>{{item.userName}}</td>
						<td>{{item.userName}}</td>
						<td>{{item.userName}}</td>
						<td>{{item.userName}}</td>
						<td>{{item.userName}}</td>
						<td>{{item.userName}}</td>
						<td>{{item.userName}}</td>
						<td>{{item.userName}}</td>
						<td>{{item.userName}}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</template>
<script>
   	const queryList ={
   		template:'#queryList',
   		data:function(){
			return {
				a:12
			}
		},
		methods:{
			choseItem:function(){
				alert(1)
			}
		}
   	};
   	const showList ={
   		template:'#showList',
   		data:function(){
   			return {
   				arr:[{
   					'userName':'fabf',
   					'reportProjectName':'ffada',
   					'reportContent':'dsfdf',
   					'reportTime':'2018-8-15',
   					'reportTimestr':'20180501',
   					'reportTypeId':'2dsf',
   					'reportGoal':'的沙发斯蒂芬是法定ds',
   					'remark':'的所得税的所',
   					'reportDay':'0215-05-16'
   				}]
   			}
   		}
   	};
	new Vue({
		el:'#List',
		components:{
			'queryList':queryList,
			'showList':showList
		}
	})
	layui.use('laydate',function(){
		var laydate =layui.laydate;
		laydate.render({
			elem:'#startDay'
		})
		laydate.render({
			elem:'#endDay'
		})
	})
	
</script>