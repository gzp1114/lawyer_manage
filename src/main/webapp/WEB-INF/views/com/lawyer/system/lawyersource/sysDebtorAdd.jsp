<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/default.jsp"%>
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <title>被执行信息添加</title>
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
		        <a id="lawyerSourceInfo" href="${ctx}/sysDebtorCompany/toShow/${debtorCompanyId}" class="movie_return">返回债务信息</a>
		    </div>
		</div>
		<div class="cinema_list">
			<form class="form-horizontal" onsubmit="return sysDebtorAdd();" role="form" id="sysDebtorAddForm" name="sysDebtorAddForm" >
				<input type="hidden" id="debtorCompanyId" name="debtorCompanyId" value="${debtorCompanyId}">
				<div class="movie_list">
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p style="background-image: none;">案件标号：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="caseId" type="text" name="caseId" value="" maxlength="100" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>执行法院：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<select name="lawyer_court" id="lawyer_court" class="fufong" >
									<option value="0" selected="selected">请选择省份</option>
								</select>
								<select name="lawyer_court1" id="lawyer_court1" class="fufong validate[required,maxSize[100]]" >
									<option value="0" selected="selected">请选择一级法院</option>
								</select>
								<select name="lawyer_court2" id="lawyer_court2" class="fufong validate[required,maxSize[100]]" >
									<option value="0" selected="selected">请选择二级法院</option>
								</select>
								<input type="hidden" id="courtcode" name="courtcode" value="" maxlength="100" />
								<input type="hidden" id="execCourtname" name="execCourtname" value="" maxlength="100" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>立案日期：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="caseCreatetime" type="text" name="caseCreatetime" value="" onclick="laydate()" maxlength="20" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>案号：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="casecode" type="text" name="casecode" value="" class="validate[required,maxSize[100]]"  maxlength="50" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>执行标的：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="executeMoney" type="text" name="executeMoney" value="" class="validate[required,maxSize[100]]"   maxlength="50" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p style="background-image: none;">案件状态：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<select name="caseState" id="caseState" class="fufong" >
									<option value="0" selected="selected">正常</option>
									<option value="1" >已结束</option>
								</select>
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
			function sysDebtorAdd(){
				if($("#sysDebtorAddForm").validationEngine("validate")){
					$.ajax({
				        type: "POST",
				        dataType: "json",
				        async: false,
				        url: '${ctx}/sysDebtor/add',
				        data: $('#sysDebtorAddForm').serialize(),
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
			};
			
			$.ajax({
				type : "POST",
				dataType : "json",
				async : false,
				url : '${ctx}/lawyerCourt/find/0',
				success : function(result) {
					if (result.status != '0') {
						alert(result.error);
						return false;
					}
					var html = "";
					for (var i = 0; i < result.data.length; i++) {
						var court = result.data[i];
						if (court != undefined) {
							html = "<option number='"+court.number+"' value='"+court.id+"'>"+court.courtName+"</option>";
							$("#lawyer_court").append(html);
						}
					}
				},
				error : function(data) {
					alert(data.error);
				}
			});
			
			$("#lawyer_court").bind("change",function(){
				 var id = $("#lawyer_court").find("option:selected").val();
				$.ajax({
					   type: "POST",
					   cache: false,
					   url: "${ctx}/lawyerCourt/find/"+id,
					   success : function(result) {
							if (result.status != '0') {
								alert(result.error);
								return false;
							}
							var html = "";
							for (var i = 0; i < result.data.length; i++) {
								var court = result.data[i];
								if (court != undefined) {
									html = "<option number='"+court.number+"' value='"+court.id+"'>"+court.courtName+"</option>";
									$("#lawyer_court1").append(html);
								}
							}
						},
						error : function(data) {
							alert(data.error);
						}
					});	
			});
			
			$("#lawyer_court1").bind("change",function(){
				$("#execCourtname").val($("#lawyer_court1").find("option:selected").text());
				$("#courtcode").val($("#lawyer_court1").find("option:selected").attr("number"));
				var id = $("#lawyer_court1").find("option:selected").val();
				$.ajax({
					   type: "POST",
					   cache: false,
					   url: "${ctx}/lawyerCourt/find/"+id,
					   success : function(result) {
							if (result.status != '0') {
								alert(result.error);
								return false;
							}
							var html = "";
							for (var i = 0; i < result.data.length; i++) {
								var court = result.data[i];
								if (court != undefined) {
									html = "<option number='"+court.number+"' value='"+court.id+"'>"+court.courtName+"</option>";
									$("#lawyer_court2").append(html);
								}
							}
						},
						error : function(data) {
							alert(data.error);
						}
					});	
			});
			
			$("#lawyer_court2").bind("change",function(){
				$("#execCourtname").val($("#lawyer_court2").find("option:selected").text());
				$("#courtcode").val($("#lawyer_court2").find("option:selected").attr("number"));
			});
			
			
			$("#sysDebtorAddForm").validationEngine();
		</script>
	</body>
</html>