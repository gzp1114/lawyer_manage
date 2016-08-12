<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/static/common/default.jsp"%>
	
	<form class="form-horizontal" role="form"  id="sysDictionaryValueAddForm" name="sysDictionaryValueAddForm" >
		
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 租户编号 </label>

			<div class="col-sm-9">
				<input id="tenantId" name="tenantId" placeholder="租户编号" class="form-control" type="text" value="">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 字典类型 </label>
			<div class="col-sm-9">
				<select class="form-control" id="select_status" name="sysApplicationId">
					<c:forEach var="dic"  items="${dicList}">
						<option value="${dic.id}">${dic.typeName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 字典值 </label>
			<div class="col-sm-9">
				<input id="value" name="value" placeholder="字典值" class="form-control" type="text" value="">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 字典名称 </label>

			<div class="col-sm-9">
				<input id="name" name="name" placeholder="字典名称" class="form-control" type="text" value="">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 排序 </label>

			<div class="col-sm-9">
				<input id="index" name="index" placeholder="排序" class="form-control" type="text" value="">
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
		//增加字典值
		function sysDictionaryValueAdd(){
			$.ajax({
		        type: "POST",
		        dataType: "json",
		        async: false,
		        url: '${ctx}/sysDictionaryValue/add',
		        data: $('#sysDictionaryValueAddForm').serialize(),
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
	
