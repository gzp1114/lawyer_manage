<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/static/common/default.jsp"%>
	
	<form class="form-horizontal"  id="sysDebtorCompanyModifyForm"  name="sysDebtorCompanyModifyForm" >
		<input type="hidden" id="id" name="id" value="${sysDebtorCompany.id}">
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 公司名称 </label>

			<div class="col-sm-9">
				<input id="name" name="name" class="form-control" type="text" value="${sysDebtorCompany.name}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 组织结构代码 </label>

			<div class="col-sm-9">
				<input id="organizationCode" name="organizationCode" class="form-control" type="text" value="${sysDebtorCompany.organizationCode}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 注册号 </label>

			<div class="col-sm-9">
				<input id="registerNumber" name="registerNumber" class="form-control" type="text" value="${sysDebtorCompany.registerNumber}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 公司类型 </label>

			<div class="col-sm-9">
				<input id="type" name="type" class="form-control" type="text" value="${sysDebtorCompany.type}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 法人 </label>

			<div class="col-sm-9">
				<input id="corporateName" name="corporateName" class="form-control" type="text" value="${sysDebtorCompany.corporateName}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 法人身份证号 </label>

			<div class="col-sm-9">
				<input id="corporateCardnum" name="corporateCardnum" class="form-control" type="text" value="${sysDebtorCompany.corporateCardnum}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 成立日期 </label>

			<div class="col-sm-9">
				<input id="establishDate" name="establishDate" class="form-control" type="text" value="${sysDebtorCompany.establishDate}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 注册资金 </label>

			<div class="col-sm-9">
				<input id="registerCapital" name="registerCapital" class="form-control" type="text" value="${sysDebtorCompany.registerCapital}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 营业开始日期 </label>

			<div class="col-sm-9">
				<input id="businessStartDate" name="businessStartDate" class="form-control" type="text" value="${sysDebtorCompany.businessStartDate}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 营业结束日期 </label>

			<div class="col-sm-9">
				<input id="businessEndDate" name="businessEndDate" class="form-control" type="text" value="${sysDebtorCompany.businessEndDate}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 登记机关 </label>

			<div class="col-sm-9">
				<input id="registrationAuthority" name="registrationAuthority" class="form-control" type="text" value="${sysDebtorCompany.registrationAuthority}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 公司地址 </label>

			<div class="col-sm-9">
				<input id="address" name="address" class="form-control" type="text" value="${sysDebtorCompany.address}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 企业状态 </label>

			<div class="col-sm-9">
				<input id="operateStatus" name="operateStatus" class="form-control" type="text" value="${sysDebtorCompany.operateStatus}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 备注 </label>

			<div class="col-sm-9">
				<input id="note" name="note" class="form-control" type="text" value="${sysDebtorCompany.note}">
			</div>
		</div>
	</form>
	
	<script type="text/javascript">
	
		//修改债务人信息
		function sysDebtorCompanyModify(){
			
			$.ajax({
		        type: "POST",
		        dataType: "json",
		        async: false,
		        url: '${ctx}/sysDebtorCompany/modify',
		        data: $('#sysDebtorCompanyModifyForm').serialize(),
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
	
