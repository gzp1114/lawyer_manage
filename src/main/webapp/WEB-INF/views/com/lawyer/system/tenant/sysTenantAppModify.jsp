<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/static/common/default.jsp"%>
	
<link rel="stylesheet" href="${ctx}/static/threadui/css/select.css" />
<script type="text/javascript" src="${ctx}/static/threadui/datejs/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/static/threadui/js/jquery.select.js"></script>    

<div class="cinema_tip">
	<div class="cinema_subject clearfix">
	    <div class="cinema_title">
	        <a href="#">租户管理</a> >
	        <a href="#">租户应用管理</a> >
	        <a href="#" class="cinema_option">租户应用修改</a>
	    </div>
	    <a href="${ctx}/sysTenantApp/toSearch" class="movie_return bg_blue">返回列表</a>
	</div>
</div>

<div class="cinema_list">
<form action="../modify" method="get" id="sysTenantAppModifyForm" name="sysTenantAppModifyForm">
	<input type="hidden" id="id" name="id" value="${sysTenantApp.id}">
	<div class="movie_list">
		
		<div class="clearfix"></div>
		<dl class="movie_fill">
			<dt class="movie_word clearfix">
				<p>业务租户编号：</p>
			 </dt>
			<dd class="mleft_99">
				<div class="movie_type cinema_feed">
					<input type="text" name="businessTenantId" value="${sysTenantApp.businessTenantId}" class="width_300" readonly/>
				</div>
			</dd>
		</dl>
		
		<div class="clearfix"></div>
		<dl class="movie_fill">
			<dt class="movie_word clearfix">
				<p>菜单功能外键ID：</p>
			 </dt>
			<dd class="mleft_99">
				<div class="movie_type cinema_feed">
					<input type="text" name="menuFunctionId" value="${sysTenantApp.menuFunctionId}" class="width_300" readonly />
				</div>
			</dd>
		</dl>

		<div class="clearfix"></div>
		<dl class="movie_fill">
			<dt class="movie_word clearfix">
				<p>应用名称：</p>
			 </dt>
			<dd class="mleft_99">
				<div class="movie_type cinema_feed">
					<input type="text" name="appName" value="${sysTenantApp.appName}" class="width_300">
				</div>
			</dd>
		</dl>
		<div class="clearfix"></div>
		<dl class="movie_fill">
			<dt class="movie_word clearfix">
				<p>应用标识码(访问KEY)：</p>
			 </dt>
			<dd class="mleft_99">
				<div class="movie_type cinema_feed">
					<input type="text" name="appCode" value="${sysTenantApp.appCode}" class="width_300">
				</div>
			</dd>
		</dl>
		<div class="clearfix"></div>
		<dl class="movie_fill">
			<dt class="movie_word clearfix">
				<p>应用密码(签名KEY)：</p>
			 </dt>
			<dd class="mleft_99">
				<div class="movie_type cinema_feed">
					<input type="text" name="secretKey" value="${sysTenantApp.secretKey}" class="width_300">
				</div>
			</dd>
		</dl>
		<div class="clearfix"></div>
		<dl class="movie_fill">
			<dt class="movie_word clearfix">
				<p>应用地址：</p>
			 </dt>
			<dd class="mleft_99">
				<div class="movie_type cinema_feed">
					<input type="text" name="appUrl" value="${sysTenantApp.appUrl}" class="width_300">
				</div>
			</dd>
		</dl>
		<div class="clearfix"></div>
		<dl class="movie_fill">
			<dt class="movie_word clearfix">
				<p>业务数据库驱动：</p>
			 </dt>
			<dd class="mleft_99">
				<div class="movie_type cinema_feed">
					<input type="text" name="businessJdbcDriver" value="${sysTenantApp.businessJdbcDriver}"" class="width_300">
				</div>
			</dd>
		</dl>
		<div class="clearfix"></div>
		<dl class="movie_fill">
			<dt class="movie_word clearfix">
				<p>业务数据库URL：</p>
			 </dt>
			<dd class="mleft_99">
				<div class="movie_type cinema_feed">
					<input type="text" name="businessJdbcUrl" value="${sysTenantApp.businessJdbcUrl}" class="width_300">
				</div>
			</dd>
		</dl>
		<div class="clearfix"></div>
		<dl class="movie_fill">
			<dt class="movie_word clearfix">
				<p>业务数据库用户名：</p>
			 </dt>
			<dd class="mleft_99">
				<div class="movie_type cinema_feed">
					<input type="text" name="businessJdbcUsername" value="${sysTenantApp.businessJdbcUsername}" class="width_300">
				</div>
			</dd>
		</dl>
		<div class="clearfix"></div>
		<dl class="movie_fill">
			<dt class="movie_word clearfix">
				<p>业务数据库密码：</p>
			 </dt>
			<dd class="mleft_99">
				<div class="movie_type cinema_feed">
					<input type="text" name="businessJdbcPassword" value="${sysTenantApp.businessJdbcPassword}" class="width_300">
				</div>
			</dd>
		</dl>
		<div class="clearfix"></div>
		<dl class="movie_fill">
			<dt class="movie_word clearfix">
				<p>状态：</p>
			 </dt>
			<dd class="mleft_99">
				<div class="movie_type cinema_feed">
					<c:set var="statusEnble" value="<%=SystemContents.SYS_APPLICATION_STATUS.ENABLE.value()%>" /> 
					<c:set var="statusStop" value="<%=SystemContents.SYS_APPLICATION_STATUS.STOP.value()%>" /> 
					<select name="status" id="status" class="fufong required" required="true">
	                    <option value='${sysTenantApp.status}'><selftag:dictag type="SYS_APPLICATION_STATUS" value="${sysTenantApp.status}"/></option>
	                    <c:if test="${sysTenantApp.status == statusEnble}">
	                    	<option value="2">停用</option>
	                    </c:if>
	                    <c:if test="${sysTenantApp.status == statusStop}">
	                    	<option value="1">启用</option>
	                    </c:if>    
                    </select>
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
					<input type="text" name="note" value="${sysTenantApp.note}" class="width_300">
				</div>
			</dd>
		</dl>
		<div class="clearfix"></div>
		<a class="movie_continue">
			<input type="submit" name="" value="提交" class="cinema_inquiry" />
		</a>
	</div>
</form>
</div>

	
