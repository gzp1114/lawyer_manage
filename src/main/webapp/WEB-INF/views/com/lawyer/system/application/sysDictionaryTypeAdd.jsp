<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/static/common/default.jsp"%>

	<form class="form-horizontal" role="form"  id="sysDictionaryTypeAddForm" name="sysDictionaryTypeAddForm" >
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 租户编号 </label>

			<div class="col-sm-9">
				<input id="tenantId" name="tenantId" placeholder="租户编号" class="form-control" type="text" value="">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 应用接入管理外键 </label>

			<div class="col-sm-9">
				<select class="form-control" id="select_status" name="sysApplicationId">
					<c:forEach var="app"  items="${appList}">
						<option value="${app.id}">${app.appName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 类型名称 </label>

			<div class="col-sm-9">
				<input id="typeName" name="typeName" placeholder="类型名称" class="form-control" type="text" value="">
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
	
		//增加字典类型
		function sysDictionaryTypeAdd(){
			$.ajax({
		        type: "POST",
		        dataType: "json",
		        async: false,
		        url: '${ctx}/sysDictionaryType/add',
		        data: $('#sysDictionaryTypeAddForm').serialize(),
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
	
