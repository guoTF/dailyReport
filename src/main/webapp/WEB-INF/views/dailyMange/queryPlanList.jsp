<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
     <%@include file="/commons/global.jsp" %>
<div class="text-canter">个人日志查询</div>

<div class="row form-inline">
	<div class="form-group ">
    <label for="planStartday">开始日期：</label>
     <input type="text" class="form-control" id="planStartday">
  </div>
	<div class="form-group">
    <label for="planEndday">结束日期：</label>
     <input type="text" class="form-control" id="planEndday">
  </div>
	<div class="form-group">
    <button type="button" class="btn btn-info">搜索</button>
  </div>
</div>
<div class="row" style="padding:20px 0;">
<label class="radio-inline">
  <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1">计划
</label>
<label class="radio-inline">
  <input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2">日志
</label>
<label class="radio-inline">
  <input type="radio" name="inlineRadioOptions" id="inlineRadio3" value="option3">综合
</label>
<label class="radio-inline">
  <input type="radio" name="inlineRadioOptions" id="inlineRadio4" value="option4">工作类型
</label>
</div>
<div class="table-responsive">
			  <table class="table  table-bordered" id="plantable">
			    <thead>
			    	<tr class="warning">
				    	<th>工作日期</th>
					    <th>序号</th>
					    <th>部门</th>
					    <th>项目名称</th>
					    <th>重大里程碑</th>
					    <th>工作内容</th>
					    <th>工作类型</th>
					    <th>工作时间</th>
					    <th>工作结果</th>
					    <th>是否加班</th>
					    <th>备注</th>
			    	</tr>
			    </thead>
			   <tbody id="planList">
			
			   </tbody>
			   
			  </table>
		</div>
<script type="text/javascript" src="${staticPath }/static/daily/js/getplan.js"></script>
