<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/default.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/static/common/default.jsp"%>
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <title>编辑用户信息</title> 
		<style type="text/css">
			.select_option{
				width: 180px;
				z-index: 1;
				position: absolute;
			}
			.select_showbox{
				width: 175px;
			}
		</style>
		<link type="text/css" rel="stylesheet" href="${ctx}/static/tools/jquery-validationEngine/validationEngine.jquery.css"></link>
	</head>
	<body>
		<script type="text/javascript" src="${ctx}/static/tools/jquery-validationEngine/jquery.validationEngine.js"></script>
		<script type="text/javascript" src="${ctx}/static/tools/jquery-validationEngine/jquery.validationEngine-cn.js"></script>
		<div class="cinema_tip">
		    <div class="cinema_subject clearfix">
		        <div class="cinema_title">
		            <a href="javascript:void(0);">用户中心</a> >
		            <a id="userinfo_list_href" href="${ctx}/sysUser/toSearch">用户管理</a> >
		            <a href="javascript:void(0);" class="cinema_option">编辑用户信息</a>
		        </div>
		        <!-- <a onclick="javascript:history.back(-1);" class="movie_return">返回列表</a> -->
		        <a href="${ctx}/sysUser/toSearch" class="movie_return">返回列表</a>
		    </div>
		</div>
		<div class="cinema_list">
			<form class="form-horizontal" onsubmit="return sysUserInfoModify();" id="sysUserInfoModifyForm"  name="sysUserInfoModifyForm" >
				<input type="hidden" id="id" name="id" value="${sysUserInfo.id}">
				<div class="movie_list">
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>用户名：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="userName" type="text" name="userName" value="${sysUserInfo.userName}" class="validate[required,maxSize[50]]" maxlength="50" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p style="background-image: none;">用户昵称：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="nickname" type="text" name="nickname" value="${sysUserInfo.nickname}" class="width_320" maxlength="100" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p style="background-image: none;">工号：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="workNumber" type="text" name="workNumber" value="${sysUserInfo.workNumber}" class="width_320" maxlength="50" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p style="background-image: none;">性别: </p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<div>
									<label>
										<input style="width: 0px;" type="radio" class="ace" id="gender" name="gender"
											 value="1" ${(sysUserInfo.gender=='1')?'checked' : ''}>
										<span class="lbl">男</span>
									</label>
									&nbsp;&nbsp;&nbsp;
									<label>
										<input style="width: 0px;" type="radio" class="ace" id="gender" name="gender"
										 	value="2" ${(sysUserInfo.gender=='2')?'checked' : ''}>
										<span class="lbl">女</span>
									</label>
								</div>
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p style="background-image: none;">证件类型：</p>
						</dt>
						<dd>
							<div class="cinema_select mleft_15">
								<select name="identityType" id="identityType" class="fufong" >
									<option value="">请选择证件类型</option>
									<option value="1" ${(sysUserInfo.identityType=='1')?'selected' : ''}>身份证</option>
								</select>
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p style="background-image: none;">证件号码 ：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="idNumber" type="text" name="idNumber" value="${sysUserInfo.idNumber}" class="width_320" maxlength="50" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p style="background-image: none;">住址 ：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="address" type="text" name="address" value="${sysUserInfo.address}" class="width_320" maxlength="200" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p style="background-image: none;">邮箱 ：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="email" type="text" name="email" value="${sysUserInfo.email}" class="width_320" maxlength="50" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p style="background-image: none;">出生日期 ：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="datepicker" type="text" name="dateBirth" value="${sysUserInfo.dateBirth}" class="width_320" data-date-format="dd-mm-yyyy" maxlength="20" />
								</span>
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<a class="movie_continue">
						<input type="submit" name="submit" id="submit" value="提交" class="cinema_inquiry" />
					</a>
				</div>
			</form>
		</div>
		<script type="text/javascript">
			//用户基本信息修改
			function sysUserInfoModify(){
				if($("#sysUserInfoModifyForm").validationEngine("validate")){
					$.ajax({
				        type: "POST",
				        dataType: "json",
				        async: false,
				        url: '${ctx}/sysUserInfo/modify',
				        data: $('#sysUserInfoModifyForm').serialize(),
				        success: function (data) {
				        	if(data!=undefined&&data.status!=undefined&&data.status.trim()=='0'){
				        		result = true;
				        		alert("保存成功！");
				        		window.location.href = $("#userinfo_list_href").attr("href");
				        	}else{
					        	result = false;
				        		alert(data.error);
				        	}
				        },
				        error: function(data) {
				        	result = false;
			        		alert(data.data);
				         }
				    });
				}
				return false;
			}
			$("#sysUserInfoModifyForm").validationEngine();
		</script>
	</body>
</html>