<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/static/common/default.jsp"%>

<link rel="stylesheet" href="${ctx}/static/threadui/css/select.css" />

<div class="cinema_tip">
	<div class="cinema_subject clearfix">
	    <div class="cinema_title">
	        <a href="#">租户管理</a> >
	        <a href="#">租户管理</a> >
	        <a href="#" class="cinema_option">租户添加</a>
	    </div>
	    <a href="${ctx}/sysTenant/toSearch" class="movie_return bg_blue">返回列表</a>
	</div>
</div>

<div class="cinema_list">
<form action="add" method="post" id="sysTenantAddForm" name="sysTenantAddForm">
	<div class="movie_list">
		<dl class="movie_fill">
			<dt class="movie_word clearfix">
				<p>租户名称：</p>
			 </dt>
			<dd class="mleft_99">
				<div class="movie_type cinema_feed">
					<input type="text" name="tenantName" value="" class="width_300">
				</div>
			</dd>
		</dl>
		<div class="clearfix"></div>
		<dl class="movie_fill">
			<dt class="movie_word movie_suoshu clearfix">
				<p>类型：</p>
			 </dt>
			<dd>
				<div class="cinema_select mleft_15">
					<select name="type" id="type" class="fufong">
						<option value="1" selected>实体租户</option>
						<option value="2">虚拟租户</option>
					</select>
				</div>
			</dd>
		</dl>
		<div class="clearfix"></div>
		<dl class="movie_fill">
			<dt class="movie_word clearfix">
				<p>数据库驱动 ：</p>
			 </dt>
			<dd class="mleft_99">
				<div class="movie_type cinema_feed">
					<input type="text" name="dataJdbcDriver" value="" class="width_300">
				</div>
			</dd>
		</dl>
		<div class="clearfix"></div>
		<dl class="movie_fill">
			<dt class="movie_word clearfix">
				<p>数据库URL：</p>
			 </dt>
			<dd class="mleft_99">
				<div class="movie_type cinema_feed">
					<input type="text" name="dataJdbcUrl" value="" class="width_300">
				</div>
			</dd>
		</dl>
		<div class="clearfix"></div>
		<dl class="movie_fill">
			<dt class="movie_word clearfix">
				<p>数据库用户名：</p>
			 </dt>
			<dd class="mleft_99">
				<div class="movie_type cinema_feed">
					<input type="text" name="dataJdbcUsername" value="" class="width_300">
				</div>
			</dd>
		</dl>
		<div class="clearfix"></div>
		<dl class="movie_fill">
			<dt class="movie_word clearfix">
				<p>数据库密码：</p>
			 </dt>
			<dd class="mleft_99">
				<div class="movie_type cinema_feed">
					<input type="text" name="dataJdbcPassword" value="" class="width_300">
				</div>
			</dd>
		</dl>
		<input id="status" name="status" type="hidden" value="2"/>
		<div class="clearfix"></div>
		<a class="movie_continue">
			<input type="submit" name="" value="提交" class="cinema_inquiry" />
		</a>
	</div>
</form>
</div>

