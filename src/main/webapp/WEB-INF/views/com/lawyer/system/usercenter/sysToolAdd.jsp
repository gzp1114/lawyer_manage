<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/default.jsp"%>
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <title>新建LDAP</title>
		<style type="text/css">
			.form_textarea{
				max-width: 400px;
				max-height: 100px;
				min-width: 400px;
				min-height: 100px;
				border: 1px solid #DFE7F0;
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
		            <a id="tool_list_href" href="${ctx}/sysTool/toSearch">LDAP管理</a> >
		            <a href="javascript:void(0);" class="cinema_option">新建LDAP</a>
		        </div>
		        <a id="bak_toSearch_href" href="${ctx}/sysTool/toSearch" class="movie_return">返回列表</a>
		    </div>
		</div>
		<div class="cinema_list">
			<form class="form-horizontal" role="form"  id="sysToolAddForm" onsubmit="return sysToolAdd();"  name="sysToolAddForm" >
				<input id="type" name="type" class="form-control" type="hidden" value="1">
				<div class="movie_list">
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>名称：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="name" name="name" maxlength="25" class="validate[required,maxSize[25]]" type="text" value="" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>OU名称：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="ouName" name="ouName" maxlength="25" class="validate[required,maxSize[25]]" type="text" value="" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>状态: </p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type clearfix">
								<div>
									<label>
										<input style="width: 0px;"  type="radio" class="ace" id="status" name="status" value="1" checked="checked">
										<span class="lbl">正常</span>
									</label>
									<label>
										<input style="width: 0px;"  type="radio" class="ace" id="status" name="status" value="2">
										<span class="lbl">无效</span>
									</label>
								</div>
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p style="background-image: none;">备注：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<textarea rows="6" cols="60" class="form_textarea" id="note" name="note" maxlength="200" ></textarea>
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
			//增加应用工具管理
			function sysToolAdd() {
				if($("#sysToolAddForm").validationEngine("validate")){
					$.ajax({
				        type: "POST",
				        dataType: "json",
				        async: false,
				        url: '${ctx}/sysTool/add',
				        data: $('#sysToolAddForm').serialize(),
				        success: function (data) {
				        	if(data.status!='0'){
				        		alert(data.error);
				        		return false;
				        	}
				        	alert("保存成功！");
							window.location.href = $("#bak_toSearch_href").attr("href");
							return false;
				        },
				        error: function(data) {
				            alert("error:"+data.error);
				        }
				    });
				}
				return false;
			}
			$("#sysToolAddForm").validationEngine();
		</script>
	</body>
</html>

