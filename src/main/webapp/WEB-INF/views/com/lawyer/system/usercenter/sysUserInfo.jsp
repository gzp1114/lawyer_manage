<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/default.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户管理</title>
	</head>
	<body>
		<div class="cinema_tip">
		    <div class="cinema_subject clearfix">
		        <div class="cinema_title">
		            <a href="#">用户中心</a>
		            >
		            <a href="#" class="cinema_option">用户管理</a>
		        </div>
		    </div>
			<form  id="sysUserSearchForm" method="get" >
		       <div class="cinema_search clearfix">
					<div class="cinema_button">
				        <input id="sysUserSearch" type="submit" value="查询" name="" style="float: none;" class="cinema_inquiry movie_no">
					</div>
					<div class="cinema_condition width_84">
						<div class="cinema_smart">
							<div class="cinema_inter">
								<input type="text" id="search_userName" name="search_username" maxlength="50" placeholder="用户名" class="col-xs-10 col-sm-2" value="${res.data.username}"/>
							</div>
							<div class="cinema_inter">
								<input type="text" id="search_phone" name="search_phone" maxlength="50" placeholder="联系电话" class="col-xs-10 col-sm-2" value="${res.data.phone}"/>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
		<div class="cinema_list">
		    <div class="movie_button">
				<a class="movie_new" href="${ctx}/sysUser/toAdd" style="background-color: rgb(75, 208, 131); color: rgb(255, 255, 255);">新建</a>
		    <div  class="column_content">
		        <table id="sysUserDatas" width="100%" height="" border="0" cellpadding="0" cellspacing="1" class="border_top">
		            <tr class="cinema_caption clearfix" style="width: 100%;">
		                <th class="column_loginAccount over_hiden" style="width: 20%;">登录账号</th>
		                <th class="column_username over_hiden" style="width: 20%;">用户名</th>
		                <th class="column_phone over_hiden" style="width: 20%;">联系电话</th>
		                <th class="column_status over_hiden" style="width: 15%;">状态</th>
		                <th class="column_type over_hiden" style="width: 24%;border-right: none;">操作</th>
		            </tr>
		            <c:forEach var="res" items="${res.data.rows}" >
			            <tr class="column_list clearfix" id="userinfo_row_${res.id}" style="width: 100%;" >
			                 <td class="column_loginAccount over_hiden" style="width: 20%;" title="${res.loginAccount}">&nbsp;${res.loginAccount}</td>
			                 <td class="column_userName over_hiden" style="width: 20%;" title="${res.username}">&nbsp;${res.username}</td>
			                 <td class="column_userName over_hiden" style="width: 20%;" title="${res.phone}">&nbsp;${res.phone}</td>
			                 <td class="column_status over_hiden" style="width: 15%;" title="<selftag:dictag type="SYS_USER_SESSION_STATUS" value="${res.status}"/>">&nbsp;<selftag:dictag type="SYS_USER_SESSION_STATUS" value="${res.status}"/></td>
			                 <td class="column_type over_hiden" style="width: 24%;border-right: none;">
			                 	<a class="list_mess" style="margin-left: 10%;" href="${ctx}/sysUser/toModify/${res.id}" title="编辑用户信息"></a>
			                 	<a class="list_mess" style="margin-left: 5%;" href="${ctx}/sysUser/toRole/${res.id}" title="修改角色关系"></a>
			                 	<a class="list_mess" style="margin-left: 5%;" href="javascript:updatePWD('${res.username}',${res.id});" title="修改用户密码"></a>
			                 	<c:if test="${res.status!=3}">
				                 	<a href="javascript:userDel(${res.id});" style="margin-left: 5%;" class="user_delete ui-pg-div list_delet" title="删除用户"></a>
			                 	</c:if>
							</td>
			             </tr>
		            </c:forEach>
				</table>
			</div><!-- /.row -->
			<div id="manager_page" class="manager_page clearfix">
				<!-- 分页跳转 -->
				<div class="manager_show">
					<p><span>跳转至</span><input id="pagenum" type="text" name="" value="${res.data.page}"/></p>
					<span id="toPage" class="manager_true"></span>
				</div>
				<!-- 分页数字导航栏 -->
				<div id="manager_number" class="manager_number"></div>
				<input type="hidden"  id=records  value="${res.data.records}"/>			<!-- 总记录数 -->
				<input type="hidden"  id="pageSize"  value="${res.data.pageSize}"/>  <!-- 每页个数 -->
				<input type="hidden"  id="pageCurrent"  value="${res.data.page}"/>  <!-- 当前页page -->
				<input type="hidden"  id="totalPage"  value="${res.data.total}"/>	    <!-- 总页数 -->
			</div>
		</div>
		<!--提示弹框-->
		<div class="movie_shade hidden"></div>
		<div class="advance_tip hidden">
			<input type="hidden" value="" id="up_pwd_userId" name="up_pwd_userId" />
			<div class="movie_subject"><a href="javascript:void(0);" id="modal_close"><img src="${ctx }/static/threadui/images/movie_close.png" alt="关闭"/></a>修改用户密码：<span id="up_pwd_userName"></span></div>
			<div class="movie_center">
				<div class="cinema_inter" style="float: none;display: block;text-align: center;">
					<!-- <label class="col-sm-1 control-label no-padding-right" for="form-field-1"> 用户名： </label> -->
					<input type="password" id="userpassword" name="userpassword" maxlength="50" placeholder="请输入密码" class="col-xs-10 col-sm-2 pwd_input" value="" style="width: 300px;"/>
				</div>
				<div class="cinema_inter" style="float: none;display: block;text-align: center;margin-top: 10px;">
					<input type="password" id="re_userpassword" name="re_userpassword" maxlength="50" placeholder="请确认密码" class="col-xs-10 col-sm-2 pwd_input" value="" style="width: 300px;"/>
				</div>
			</div>
			<div class="rule_button">
				<span class="cinema_inquiry" id="changePWD_sub">确定</span>
			</div>
		</div>
		<!--提示弹框-->
		<script type="text/javascript">
		$("#modal_close").on("click",function(){
			$("#up_pwd_userName").text("");
			$("#up_pwd_userId").val("");
			$("#userpassword").val("");
			$("#re_userpassword").val("");
			$(".movie_shade").addClass("hidden");
			$(".advance_tip").addClass("hidden");
			return false;
		});
		function updatePWD(userName,id){
			//显示删除提示框
			$(".movie_shade").removeClass("hidden");
			$(".advance_tip").removeClass("hidden");
			//动态更改用户名
			$("#up_pwd_userName").text(userName);
			//动态更改用户ID
			$("#up_pwd_userId").val(id);
		}

		$("#userpassword").on("blur",function(){
			var userpassword = $("#userpassword").val();
			if(userpassword==undefined||userpassword==""){
				$("#userpassword").css("border","1px solid red");
			}else{
				$("#userpassword").css("border","1px solid #dfe7f0");
			}
			var re_userpassword = $("#re_userpassword").val();
			if(re_userpassword!=undefined&&re_userpassword!=""){
				if(userpassword!=re_userpassword){
					$("#re_userpassword").css("border","1px solid red");
				}else{
					$("#re_userpassword").css("border","1px solid #dfe7f0");
				}
			}
			return;
		});

		$("#re_userpassword").on("blur",function(){
			var re_userpassword = $("#re_userpassword").val();
			if(re_userpassword==undefined||re_userpassword==""){
				$("#re_userpassword").css("border","1px solid red");
				return;
			}else{
				$("#re_userpassword").css("border","1px solid #dfe7f0");
			}
			var userpassword = $("#userpassword").val();
			if(userpassword!=undefined&&userpassword!=""){
				if(userpassword!=re_userpassword){
					$("#re_userpassword").css("border","1px solid red");
				}else{
					$("#re_userpassword").css("border","1px solid #dfe7f0");
				}
			}
			return;
		});

		//点击确认执行更改操作
		$("#changePWD_sub").on("click",function(){
			var userpassword = $("#userpassword").val();
			if(userpassword==undefined||userpassword==""){
				alert("请输入密码。");
				return false;
			}
			var re_userpassword = $("#re_userpassword").val();
			if(re_userpassword==undefined||re_userpassword==""){
				alert("请输入确认密码。");
				return false;
			}
			if(re_userpassword!=userpassword){
				alert("两次输入密码不一致。");
				return false;
			}
			var userId = $("#up_pwd_userId").val();
			if(userId==undefined||userId==""){
				alert("未知的错误发生了。");
				return false;
			}
			//更改密码请求提交
			$.ajax({
		        type: "POST",
		        dataType: "json",
		        async: false,
		        url: '${ctx}/sysUser/password',
		        data: {userId:userId,password:userpassword,password2:re_userpassword},
		        success: function (data) {
		        	if(data!=undefined&&data.status!=undefined&&data.status.trim()=='0'){
		        		alert("保存密码成功！");
		        		$("#modal_close").click();
		        	}else{
		        		alert(data.error);
		        	}
		        },
		        error: function(data) {
	        		alert(data.data);
		         }
		    });
			return false;
		});
		//用户基本信息删除
		function userDel(rowId){
			if(rowId==undefined||(rowId+"").trim()==""||rowId<=0){
				alert("请选择需要修改记录！");
				return false;
			}
			if(confirm("确认删除该条用户信息？")){
				deleteRowById(rowId);
			}
		}
		
		//删除用户ajax请求
		function deleteRowById(rowId){
			$.ajax({
				type: "DELETE",
				dataType: "json",
				async: false,
				url: '${ctx}/sysUser/delete/'+rowId,
				success: function (data) {
							if(data!=undefined&&data.status!=undefined&&data.status.trim()=="0"){
								//$("#userinfo_row_"+rowId).remove();
								//将状态栏置为未启用状态
								$("#userinfo_row_"+rowId).find(".column_status").text(" 未启用");
								$($("#userinfo_row_"+rowId).find(".column_status")[0]).attr("title","未启用");
								//删除‘LDAP管理’及‘删除用户’两个操作功能
								$("#userinfo_row_"+rowId).find(".user_delete").remove();
								alert("删除成功。");
							}else{
								alert("error:"+data.error);
							}
						 },
				error: function(data) {
							alert("删除失败。");
						}
			});
			return false;
		}
		</script>
	</body>
</html>
