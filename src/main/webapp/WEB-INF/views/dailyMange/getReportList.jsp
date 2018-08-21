<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
         <%@include file="/commons/global.jsp" %>
<div>工作日志填写</div>
<div class="row">
	<div class="form-group form-inline">
    <label for="planday">工作日期：</label>
     <input type="text" class="form-control" id="planday">
  </div>
</div>
<div class="row tablePlan">
     	
		<div class="table-responsive">
			  <table class="table  table-bordered" id="plantable">
			    <thead>
			    	<tr class="warning">
				    	<th>操作</th>
					    <th>序号</th>
					    <th>工作内容</th>
					    <th>计划目标</th>
					    <th>工作量(小时)</th>
			    	</tr>
			    </thead>
			   <tbody id="planList">
			
			   </tbody>
			   <tfoot>
			   		<tr>
			   			<td colspan="5">
			   				<a href="javascript:void(0);" class="btn btn-primary" id="addBtn">添加</a>
			   				<a href="javascript:void(0);" style="padding: 0px 15px;"></a>
			   				<a href="javascript:void(0);" class="btn btn-success" id="saveBtn">保存</a>
			   			</td>
			   		</tr>
			   </tfoot>
			  </table>
		</div>
	</div>
<script type="text/javascript" src="${staticPath }/static/daily/js/getplan.js"></script>