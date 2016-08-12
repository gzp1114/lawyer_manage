<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/static/common/default.jsp"%>
	
	<form class="form-horizontal" role="form"  id="sysApplicationAddForm" name="sysApplicationAddForm" >
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 租户编号 </label>

			<div class="col-sm-9">
				<input id="tenantId" name="tenantId" placeholder="租户编号" class="form-control" type="text" value="">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 应用名称 </label>

			<div class="col-sm-9">
				<input id="appName" name="appName" placeholder="应用名称" class="form-control" type="text" value="">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 应用标识码(访问KEY) </label>

			<div class="col-sm-9">
				<input id="appCode" name="appCode" placeholder="应用标识码(访问KEY)" class="form-control" type="text" value="">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 应用密码(签名KEY) </label>

			<div class="col-sm-9">
				<input id="secretKey" name="secretKey" placeholder="应用密码(签名KEY)" class="form-control" type="text" value="">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 应用地址 </label>

			<div class="col-sm-9">
				<input id="appUrl" name="appUrl" placeholder="应用地址" class="form-control" type="text" value="">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 备注 </label>

			<div class="col-sm-9">
				<input id="note" name="note" placeholder="备注" class="form-control" type="text" value="">
			</div>
		</div>
	</form>
		
	<script type="text/javascript">
	
		//增加应用接入管理
		function sysApplicationAdd(){
			
			$.ajax({
		        type: "POST",
		        dataType: "json",
		        async: false,
		        url: '${ctx}/sysApplication/add',
		        data: $('#sysApplicationAddForm').serialize(),
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
	
