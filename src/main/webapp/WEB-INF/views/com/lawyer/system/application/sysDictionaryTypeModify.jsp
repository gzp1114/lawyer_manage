<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/static/common/default.jsp"%>
	
	<form class="form-horizontal"  id="sysDictionaryTypeModifyForm"  name="sysDictionaryTypeModifyForm" >
		<input type="hidden" id="id" name="id" value="${sysDictionaryType.id}">
		 
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 租户编号 </label>

			<div class="col-sm-9">
				${sysDictionaryType.tenantId}
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 应用名称 </label>
			<div class="col-sm-9">
				<div class="col-sm-9">
					${sysApplication.appName}
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 类型名称 </label>

			<div class="col-sm-9">
				<input id="typeName" name="typeName" placeholder="类型名称" class="form-control" type="text" value="${sysDictionaryType.typeName}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 备注 </label>

			<div class="col-sm-9">
				<input id="note" name="note" placeholder="备注" class="form-control" type="text" value="${sysDictionaryType.note}">
			</div>
		</div>

	</form>
	
	<script type="text/javascript">

		//修改字典类型
		function sysDictionaryTypeModify(){
			
			$.ajax({
		        type: "POST",
		        dataType: "json",
		        async: false,
		        url: '${ctx}/sysDictionaryType/modify',
		        data: $('#sysDictionaryTypeModifyForm').serialize(),
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
	
