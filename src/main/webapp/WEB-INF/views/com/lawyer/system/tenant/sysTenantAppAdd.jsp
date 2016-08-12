<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/static/common/default.jsp"%>
	
<link rel="stylesheet" href="${ctx}/static/threadui/css/select.css" />

<div class="cinema_tip">
	<div class="cinema_subject clearfix">
	    <div class="cinema_title">
	        <a href="#">租户管理</a> >
	        <a href="#">租户应用管理</a> >
	        <a href="#" class="cinema_option">租户应用添加</a>
	    </div>
	    <a href="${ctx}/sysTenantApp/toSearch" class="movie_return bg_blue">返回列表</a>
	</div>
</div>

<div class="cinema_list">
<form action="add" method="post" id="sysTenantAppAddForm" name="sysTenantAppAddForm">
	<div class="movie_list">
		<dl class="movie_fill">
			<dt class="movie_word clearfix">
				<p>应用名称：</p>
			 </dt>
			<dd class="mleft_99">
				<div class="movie_type cinema_feed">
					<input type="text" name="appName" value="" class="width_300">
				</div>
			</dd>
		</dl>
		<dl class="movie_fill">
			<dt class="movie_word movie_suoshu clearfix">
				<p>业务租户编号：</p>
			</dt>
			<dd>
				<div class="cinema_select mleft_15">
					<select name="businessTenantId" id="businessTenantId" class="fufong">
						<c:forEach var="res" items="${res.data.rows }">
							<option value="${res.id }" >${res.tenantName }</option>
						</c:forEach>
					</select>
				</div>
			</dd>
		</dl>
		<div class="clearfix"></div>
		<dl class="movie_fill">
			<dt class="movie_word movie_suoshu clearfix">
				<p>应用模块：</p>
			 </dt>
			<dd>
				<div class="cinema_select mleft_15">
					<select name="menuFunctionId" id="menuFunctionId" class="fufong">
						<c:forEach var="res1" items="${res1.data.rows }">
							<option value="${res1.id }" >${res1.menuName }</option>
						</c:forEach>
					</select>
				</div>
			</dd>
		</dl>
		<div class="clearfix"></div>
		<dl class="movie_fill">
			<dt class="movie_word clearfix">
				<p>应用标识码：</p>
			 </dt>
			<dd class="mleft_99">
				<div class="movie_type cinema_feed">
					<input type="text" name="appCode" value="" class="width_300">
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
					<input type="text" name="appUrl" value="" class="width_300">
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
					<input type="text" name="businessJdbcDriver" value="" class="width_300">
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
					<input type="text" name="businessJdbcUrl" value="" class="width_300">
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
					<input type="text" name="businessJdbcUsername" value="" class="width_300">
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
					<input type="text" name="businessJdbcPassword" value="" class="width_300">
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

	
