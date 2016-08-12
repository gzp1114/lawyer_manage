<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/default.jsp"%>

	<form class="form-horizontal"  id="sysUserInfoModifyForm"  name="sysUserInfoModifyForm" >
			<input type="hidden" id="id" name="id" value="${sysUserInfo.id}">
			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 工号 </label>

				<div class="col-sm-9">
					<input id="workNumber" name="workNumber" class="form-control" type="text" value="${sysUserInfo.workNumber}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 用户名 </label>

				<div class="col-sm-9">
					<input id="userName" name="userName" class="form-control" type="text" value="${sysUserInfo.userName}">
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 用户昵称 </label>

				<div class="col-sm-9">
				    <input id="nickname" name="nickname" class="form-control" type="text" value="${sysUserInfo.nickname}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 性别 </label>
				<div class="col-sm-9">
					<label>
						<input type="radio" class="ace" id="gender" name="gender"
							 value="1" ${(sysUserInfo.gender=='1')?'checked' : ''}>
						<span class="lbl">男</span>
					</label>
					<label>
						<input type="radio" class="ace" id="gender" name="gender"
						 value="2" ${(sysUserInfo.gender=='2')?'checked' : ''}>
						<span class="lbl">女</span>
					</label>
			   </div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right" for="form-field-1">证件类型 </label>
				<div class="col-sm-9">
				    <select class="form-control" id="form-field-select-1" id="identityType" name="identityType">
						<option value=""></option>
						<option value="1" ${(sysUserInfo.identityType=='1')?'selected' : ''} >身份证</option>
					</select>
				</div>
			</div>
			
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 证件号码 </label>
			<div class="col-sm-9">
				<input id="idNumber" name="idNumber" class="form-control" type="text" value="">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 住址 </label>
			<div class="col-sm-9">
				<input id="address" name="address" class="form-control" type="text" value="">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 邮箱 </label>
			<div class="col-sm-9">
				<input id="email" name="email" class="form-control" type="text" value="">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 出生日期 </label>
			<div class="input-group input-group-sm">
				<input type="text" id="datepicker" name="dateBirth" placeholder="出生日期" class="form-control hasDatepicker" data-date-format="dd-mm-yyyy">
				<span class="input-group-addon">
					<i class="ace-icon fa fa-calendar"></i>
				</span>
			</div>
		</div>

		</form>
