<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/static/common/default.jsp"%>
	
	<form class="form-horizontal"  id="sysApplicationModifyForm"  name="sysApplicationModifyForm" >
		<input type="hidden" id="id" name="id" value="${sysApplication.id}">
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 租户编号 </label>

			<div class="col-sm-9">
				<input id="tenantId" name="tenantId" placeholder="租户编号" class="form-control" type="text" value="${sysApplication.tenantId}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 应用名称 </label>

			<div class="col-sm-9">
				<input id="appName" name="appName" placeholder="应用名称" class="form-control" type="text" value="${sysApplication.appName}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 应用标识码(访问KEY) </label>

			<div class="col-sm-9">
				<input id="appCode" name="appCode" placeholder="应用标识码(访问KEY)" class="form-control" type="text" value="${sysApplication.appCode}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 应用密码(签名KEY) </label>

			<div class="col-sm-9">
				<input id="secretKey" name="secretKey" placeholder="应用密码(签名KEY)" class="form-control" type="text" value="${sysApplication.secretKey}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 应用地址 </label>

			<div class="col-sm-9">
				<input id="appUrl" name="appUrl" placeholder="应用地址" class="form-control" type="text" value="${sysApplication.appUrl}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 状态 </label>
			<div class="col-sm-9">
				<select class="form-control" id="select_status" name="status">
					<option value="1">启用</option>
					<option value="2">停用</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 备注 </label>

			<div class="col-sm-9">
				<input id="note" name="note" placeholder="备注" class="form-control" type="text" value="${sysApplication.note}">
			</div>
		</div>
	</form>
	
	<script type="text/javascript">
		$("#select_status option[value='${sysApplication.status}']").attr("selected",true);	
		
		//修改应用接入管理
		function sysApplicationModify(){
			
			$.ajax({
		        type: "POST",
		        dataType: "json",
		        async: false,
		        url: '${ctx}/sysApplication/modify',
		        data: $('#sysApplicationModifyForm').serialize(),
		        success: function (data) {
		        	if(data.status!='0'){
		        		alert(data.error);
		        	}
		        },
		        error: function(data) {
		            alert("error:"+data.error);
		         }
		    });
			
		}
	</script>
	
