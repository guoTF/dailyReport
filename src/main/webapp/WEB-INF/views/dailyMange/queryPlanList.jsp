<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/global.jsp"%>
<div class="text-canter">个人日志查询</div>
<div class="queryPlanList">
<div class="row form-inline">
	<div class="form-group ">
		<label for="planStartday">开始日期：</label> <input type="text"
			class="form-control" id="planStartday">
			<span class="layui-hide" id="planStartdayCopy"></span>
	</div>
	<div class="form-group">
		<label for="planEndday">结束日期：</label> <input type="text"
			class="form-control" id="planEndday">
			<span class="layui-hide" id="planEnddayCopy"></span>
	</div>
	<div class="form-group">
		<button type="button" class="btn btn-info" id="planSearchperson">搜索</button>
	</div>
</div>
<div class="row" style="padding: 20px 0;">
	<label class="radio-inline"> <input type="radio"
		name="inlineRadioOptions" id="inlineRadio1" value="option1">计划
	</label> <label class="radio-inline"> <input type="radio"
		name="inlineRadioOptions" id="inlineRadio2" value="option2">日志
	</label> <label class="radio-inline"> <input type="radio"
		name="inlineRadioOptions" id="inlineRadio3" value="option3">综合
	</label> <label class="radio-inline"> <input type="radio"
		name="inlineRadioOptions" id="inlineRadio4" value="option4">工作类型
	</label>
</div>
<div class="table-responsive">
	<table class="table  table-bordered" id="queryPlanList_plantable">
		<thead>
			<tr class="info">
				<th width="7%">序号</th>
				<th width="12%">工作日期</th>
			<!-- 	<th width="7%">用户名</th> -->
				<th width="15%">项目名称</th>
				<th width="15%">工作内容</th>
				<th width="13%">工作量（小时）</th>
				<th width="18%">工作时间（正常/加班）</th>
				<th >工作类型</th>
				<th >工作结果</th>
				<th width="">备注</th>
			</tr>
		</thead>
		<tbody id="planList">

		</tbody>

	</table>
</div>
</div>
<script type="text/javascript" src="${staticPath }/static/daily/js/vue.js"></script>
<script type="text/javascript" src="${staticPath }/static/daily/js/getplan.js?ver=4"></script>
