<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/default.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/static/common/default.jsp"%>
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <title>编辑债务信息</title> 
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
		            <a href="javascript:void(0);">案源中心</a> >
		            <a id="userinfo_list_href" href="${ctx}/sysDebtorCompany/toSearch">债务信息管理</a> >
		            <a href="javascript:void(0);" class="cinema_option">编辑债务信息</a>
		        </div>
		        <a href="${ctx}/sysDebtorCompany/toSearch" class="movie_return">返回列表</a>
		    </div>
		</div>
		<div class="cinema_list">
			<form class="form-horizontal" onsubmit="return sysDebtorCompanyInfoModify();" id="sysDebtorCompanyInfoModifyForm"  name="sysDebtorCompanyInfoModifyForm" >
				<input type="hidden" id="id" name="id" value="${sysDebtorCompany.id}">
				<div class="movie_list">
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>主体名称：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="name" type="text" name="name" value="${sysDebtorCompany.name}" class="validate[required,maxSize[50]]" maxlength="50" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>组织结构代码：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="organizationCode" type="text" name="organizationCode" value="${sysDebtorCompany.organizationCode}" maxlength="50" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>注册号：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="registerNumber" type="text" name="registerNumber" value="${sysDebtorCompany.registerNumber}"  maxlength="50" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>公司类型：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="type" type="text" name="type" value="${sysDebtorCompany.type}" maxlength="50" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>法人：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="corporateName" type="text" name="corporateName" value="${sysDebtorCompany.corporateName}" maxlength="50" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>法人身份证号：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="corporateCardnum" type="text" name="corporateCardnum" value="${sysDebtorCompany.corporateCardnum}" maxlength="50" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>成立日期：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="establishDate" type="text" name="establishDate" value="${sysDebtorCompany.establishDate}" onclick="laydate()" maxlength="20" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>注册资金：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="registerCapital" type="text" name="registerCapital" value="${sysDebtorCompany.registerCapital}"  maxlength="50" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>营业开始日期：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="businessStartDate" type="text" name="businessStartDate" value="${sysDebtorCompany.businessStartDate}" onclick="laydate()" maxlength="50" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>营业结束日期：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="businessEndDate" type="text" name="businessEndDate" value="${sysDebtorCompany.businessEndDate}" onclick="laydate()"  maxlength="50" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>登记机关：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="registrationAuthority" type="text" name="registrationAuthority" value="${sysDebtorCompany.registrationAuthority}"  maxlength="50" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>公司地址：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="address" type="text" name="address" value="${sysDebtorCompany.address}"  maxlength="50" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>经营状态：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="operateStatus" type="text" name="operateStatus" value="${sysDebtorCompany.operateStatus}"  maxlength="50" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>备注：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="note" type="text" name="note" value="${sysDebtorCompany.note}" maxlength="50" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					
					<div class="clearfix"></div>
					<a class="movie_continue">
						<input type="submit" name="submit" id="submit" value="提交" class="cinema_inquiry" />
					</a>
				</div>
			</form>
		</div>
		
		<script type="text/javascript" src="${ctx}/static/tools/laydate/laydate.js"></script>
		<script type="text/javascript">
			//用户基本信息修改
			function sysDebtorCompanyInfoModify(){
				if($("#sysDebtorCompanyInfoModifyForm").validationEngine("validate")){
					$.ajax({
				        type: "POST",
				        dataType: "json",
				        async: false,
				        url: '${ctx}/sysDebtorCompany/modify',
				        data: $('#sysDebtorCompanyInfoModifyForm').serialize(),
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
			$("#sysDebtorCompanyInfoModifyForm").validationEngine();
		</script>
	</body>
</html>