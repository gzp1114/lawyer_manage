<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/static/common/default.jsp"%>
	
	<form class="form-horizontal"  id="sysDictionaryValueModifyForm"  name="sysDictionaryValueModifyForm" >
		<input type="hidden" id="id" name="id" value="${sysDictionaryValue.id}">
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 租户编号 </label>

			<div class="col-sm-9">
				${sysDictionaryValue.tenantId}
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 字典类型</label>

			<div class="col-sm-9">
				${sysDictionaryType.typeName}
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 字典名称 </label>

			<div class="col-sm-9">
				<input id="name" name="name" placeholder="字典名称" class="form-control" type="text" value="${sysDictionaryValue.name}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 字典值 </label>

			<div class="col-sm-9">
				<input id="value" name="value" placeholder="字典值" class="form-control" type="text" value="${sysDictionaryValue.value}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 排序 </label>

			<div class="col-sm-9">
				<input id="index" name="index" placeholder="排序" class="form-control" type="text" value="${sysDictionaryValue.index}">
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
				<input id="note" name="note" placeholder="备注" class="form-control" type="text" value="${sysDictionaryValue.note}">
			</div>
		</div>
	</form>
	
	<script type="text/javascript">
		$("#select_status option[value='${sysApplication.status}']").attr("selected",true);	
	
		//修改字典值
		function sysDictionaryValueModify(){
			
			$.ajax({
		        type: "POST",
		        dataType: "json",
		        async: false,
		        url: '${ctx}/sysDictionaryValue/modify',
		        data: $('#sysDictionaryValueModifyForm').serialize(),
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
	
