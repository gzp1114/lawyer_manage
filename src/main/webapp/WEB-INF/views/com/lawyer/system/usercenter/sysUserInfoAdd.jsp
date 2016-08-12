<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/default.jsp"%>
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <title>新建用户</title>
	    <style type="text/css">
			.select_option{
				width: 180px;
				z-index: 1;
				position: absolute;
			}
			.select_showbox{
				width: 175px;
			}
			.checkRole{
				width: 45%;
				float: left;
				border-radius: 3px;
				box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
				cursor: text;
				line-height:22px;
				padding: 4px 6px;
				border: 1px solid #CAC5C5;
				margin-top: 5px;
				min-height: 100px;
				height: auto;
			}
			.checkRole attr{
				color: red;
			}
			.tag{
				color: white;
				margin-right: 2px;
				padding: 0.2em 0.6em 0.3em;
				border-radius: 0.25em;
				color: white;
				font-size: 95%;
				line-height: 20px;
				text-align: center;
				white-space: nowrap;
				margin-top: 1px;
			}
			.label-success{
				background-color: #29C129;
			}
			.label-default{
				background-color: #7E7575;
			}
			.tag [class="tag-remove"]::after{
				content: "x";
				padding: 0.2px;
				cursor: pointer;
				font-weight: bold;
				margin-left: 5px;
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
		            <a href="javascript:void(0);" class="cinema_option">新建用户</a>
		        </div>
		        <a href="${ctx}/sysUser/toSearch" class="movie_return">返回列表</a>
		    </div>
		</div>
		<div class="cinema_list">
			<form class="form-horizontal" onsubmit="return sysUserInfoAdd();" role="form" id="sysUserInfoAddForm" name="sysUserInfoAddForm" >
				<input type="hidden" name="roleids" id="roleids" value=""/>
				<div class="movie_list">
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>登录账号：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="loginAccount" type="text" name="loginAccount" value="" class="validate[required,maxSize[100]]" maxlength="100" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>密码：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="loginPassword" type="password" name="loginPassword" value="" class="validate[required,maxSize[100]]" maxlength="100" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>用户姓名 ：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="username" type="text" name="username" value="" class="validate[required,maxSize[50]]" maxlength="50" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>联系电话 ：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="phone" type="text" name="phone" value="" class="validate[required,maxSize[50]]" maxlength="50" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<div style="margin-top: 50px;">
						<div class="checkRole">
							<div style="width: 100%; height: 26px; line-height: 24px;">
								<span> 已关联角色关系 : 共 <attr id="checkedRoleNum">0</attr> 个 </span>
							</div>
							<div id="spanRoleTags" style="width: 100%"></div>
						</div>
						<div class="unCheckRole checkRole" style="margin-left: 2%;">
							<div style="width: 100%; height: 26px; line-height: 24px;">
								<span> 未关联角色关系 : 共 <attr id="unCheckedRoleNum">0</attr> 个 </span>
							</div>
							<div id="unSpanRoleTags" style="width: 100%"></div>
						</div>
					</div>
					<div class="clearfix"></div>
					<div class="clearfix"></div>
					<a class="movie_continue">
						<input type="submit" name="submit" id="submit" value="提交" class="cinema_inquiry" />
					</a>
				</div>
			</form>
		</div>
		<script type="text/javascript">
			function sysUserInfoAdd(){
				if($("#sysUserInfoAddForm").validationEngine("validate")){
					var result = true;
					var checkRoles = $(".label-success").find(".roleId");
					var roleids = "";
					for (var i = 0; i < checkRoles.length; i++) {
						roleids += $(checkRoles[i]).val() + ",";
					}
					$("#roleids").val(roleids);
					$.ajax({
				        type: "POST",
				        dataType: "json",
				        async: false,
				        url: '${ctx}/sysUser/add',
				        data: $('#sysUserInfoAddForm').serialize(),
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
			function removeTag(t, roleId) {
				var span = $(t).parent().clone(true);
				$(span).removeClass("label-success");
				$(span).addClass("label-default");
				$(span).find("span").attr("onclick","unRemoveTag(this,\"" + roleId + "\")");
				$(t).parent().remove();
				$("#unSpanRoleTags").append(span);
				//var checkedRoleNum = $("#checkedRoleNum").text();
				$("#checkedRoleNum").text(parseInt($("#checkedRoleNum").text()) - 1);//改变已选角色(*)的数量
				$("#unCheckedRoleNum").text(parseInt($("#unCheckedRoleNum").text()) + 1);//改变未选角色(*)的数量
			}
			function unRemoveTag(t, roleId) {
				var span = $(t).parent().clone(true);
				$(span).removeClass("label-default");
				$(span).addClass("label-success");
				$(span).find("span").attr("onclick","removeTag(this,\"" + roleId + "\")");
				$(t).parent().remove();
				$("#spanRoleTags").append(span);
				//var unCheckedRoleNum = $("#unCheckedRoleNum").text();
				$("#unCheckedRoleNum").text(parseInt($("#unCheckedRoleNum").text()) - 1);//改变未选角色(*)的数量
				$("#checkedRoleNum").text(parseInt($("#checkedRoleNum").text()) + 1);//改变已选角色(*)的数量
			}
			$.ajax({
				type : "POST",
				dataType : "json",
				async : false,
				url : '${ctx}/sysRole/allRole',
				data : 'userInfoId=' + $("#sysUserInfo_id").val(),
				success : function(data) {
					if (data.status != '0') {
						alert(data.error);
						return false;
					}
					var html = "";
					var checkCount = 0;
					var unCheckCount = 0;
					for (var i = 0; i < data.data.length; i++) {
						var node = data.data[i];
						if (node != undefined) {
							if (node.userHaved) {//当前用户已经拥有的角色
								html = "<span class='tag spanTag label-success' style='display:inline-block;'>"
										+ node.roleName
										+ "<span onclick='removeTag(this,\""
										+ node.id
										+ "\");' class='tag-remove' ></span>"
										+ "<input type='hidden' class='roleName' value='"+node.roleName+"'/>"
										+ "<input type='hidden' class='roleId' value='"+node.id+"'/>"
										+ "</span>";
								$("#spanRoleTags").append(html);
								checkCount++;
							} else {
								html = "<span class='tag spanTag label-default' style='display:inline-block;'>"
										+ node.roleName
										+ "<span onclick='unRemoveTag(this,\""
										+ node.id
										+ "\");' class='tag-remove' ></span>"
										+ "<input type='hidden' class='roleName' value='"+node.roleName+"'/>"
										+ "<input type='hidden' class='roleId' value='"+node.id+"'/>"
										+ "</span>";
								$("#unSpanRoleTags").append(html);
								unCheckCount++;
							}
							//$('#roleids').append("<option value='"+node.id+"' "+selected+" >"+node.roleName+"</option>");
						}
					}
					$("#checkedRoleNum").text(checkCount);
					$("#unCheckedRoleNum").text(unCheckCount);
				},
				error : function(data) {
					alert(data.error);
				}
			});
			$("#sysUserInfoAddForm").validationEngine();
		</script>
	</body>
</html>