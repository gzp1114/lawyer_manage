<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/default.jsp"%>
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <title>案源邮件联系添加</title>
	    <style type="text/css">
			.form_textarea{
			max-width: 400px;
			max-height: 100px;
			min-width: 400px;
			min-height: 100px;
			border: 1px solid #DFE7F0;
		}
	    </style>
	    <link type="text/css" rel="stylesheet" href="${ctx}/static/bootstrap/bootstrap.min.css"></link>
		<link type="text/css" rel="stylesheet" href="${ctx}/static/tools/jquery-validationEngine/validationEngine.jquery.css"></link>
		<script type="text/javascript" src="${ctx}/static/tools/jquery-validationEngine/jquery.validationEngine.js"></script>
		<script type="text/javascript" src="${ctx}/static/tools/jquery-validationEngine/jquery.validationEngine-cn.js"></script>
	</head>
	<body>
		<div class="cinema_tip">
		    <div class="cinema_subject clearfix">
		        <div class="cinema_title">
		            <a href="javascript:void(0);">案源中心</a> >
		            <a id="userinfo_list_href" href="${ctx}/sysLawyerSource/toSearch">案源信息管理</a> >
		            <a href="javascript:void(0);" class="cinema_option">新建邮件联系信息</a>
		        </div>
		        <a id="lawyerSourceInfo" href="${ctx}/sysLawyerSource/toShow/${lawyerSourceId}" class="movie_return">返回案源信息</a>
		    </div>
		</div>
		<div class="cinema_list">
			<form class="form-horizontal" onsubmit="return sysContectEmailAdd();" role="form" id="sysContectEmailAddForm" name="sysContectEmailAddForm" >
				<input type="hidden" id="sourceId" name="sourceId" value="${lawyerSourceId}">
				<div class="movie_list">
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>邮件地址：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="emailAddress" type="text" name="emailAddress" value="" class="validate[required,maxSize[200]]" maxlength="100" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>发送人：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<select name="userId" id="userId" class="fufong validate[required,maxSize[100]]" >
									<option value="0" selected="selected">请选择发送人</option>
								</select>
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>发送日期：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="sendTime" type="text" name="sendTime" value="" onclick="laydate()" maxlength="20" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>发送结果：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="sendResult" type="text" name="sendResult" value=""  maxlength="50" />
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
								<textarea rows="6" cols="60" class="form_textarea" id="note" name="note" maxlength="400" ></textarea>
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
		
		<script type="text/javascript" src="${ctx}/static/tools/laydate/laydate.js"></script>
		<script type="text/javascript">
			function sysContectEmailAdd(){
				if($("#sysContectEmailAddForm").validationEngine("validate")){
					$.ajax({
				        type: "POST",
				        dataType: "json",
				        async: false,
				        url: '${ctx}/sysContectEmail/add',
				        data: $('#sysContectEmailAddForm').serialize(),
				        success: function (data) {
				        	if(data!=undefined&&data.status!=undefined&&data.status.trim()=='0'){
				        		alert("保存成功！");
				        		window.location.href = $("#lawyerSourceInfo").attr("href");
				        	}else{
				        		alert(data.error);
				        	}
				        },
				        error: function(data) {
			        		alert(data.error);
				         }
				    });
					return false;
				}
			};$.ajax({
				type : "POST",
				dataType : "json",
				async : false,
				url : '${ctx}/sysUser/all',
				success : function(result) {
					if (result.status != '0') {
						alert(result.error);
						return false;
					}
					var html = "";
					for (var i = 0; i < result.data.length; i++) {
						var user = result.data[i];
						if (user != undefined) {
							html = "<option value='"+user.id+"'>"+user.username+"</option>";
							$("#userId").append(html);
						}
					}
				},
				error : function(data) {
					alert(data.error);
				}
			});
			
			$("#sysContectEmailAddForm").validationEngine();
		</script>
	</body>
</html>