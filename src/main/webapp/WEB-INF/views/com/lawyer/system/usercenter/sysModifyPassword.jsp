<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/default.jsp"%>

	<form class="form-horizontal"  id="sysModifyPasswordForm"  name="sysModifyPasswordForm" >
			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 密码 </label>

				<div class="col-sm-9">
					<input id="password" name="password" class="form-control" type="text" value="">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right" for="form-field-1">确认密码</label>

				<div class="col-sm-9">
					<input id="password2" name="password2" class="form-control" type="text" value="">
				</div>
			</div>
		</form>

		
		<script type="text/javascript">
		
		//用户基本信息增加
		function sysModifyPassword(){
			var result = true;
			$.ajax({
		        type: "POST",
		        dataType: "json",
		        async: false,
		        url: '${ctx}/sysUserInfo/password',
		        data: $('#sysModifyPasswordForm').serialize(),
		        success: function (data) {
		        	if(data.status!='0'){
		        		result = false;
		        		messagePrompt(data.error);
		        	}
		        },
		        error: function(data) {
		        	result = false;
		        	messagePrompt("error:"+data.error);
		         }
		    });
			return result;
		}
	</script>