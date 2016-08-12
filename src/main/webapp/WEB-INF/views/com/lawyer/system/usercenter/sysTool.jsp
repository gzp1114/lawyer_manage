<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/default.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>LDAP管理</title>
	</head>
	<body>
		<div class="cinema_tip">
		    <div class="cinema_subject clearfix">
		        <div class="cinema_title">
		            <a href="#">用户中心</a>
		            >
		            <a href="#" class="cinema_option">LDAP管理</a>
		        </div>
		    </div>
			<form  id="sysToolSearchForm" method="get">
				<div class="cinema_search clearfix">
					<div class="cinema_button">
						<shiro:hasPermission name="sysTool:search">
							<input id="sysToolSearch" type="submit" value="查询" name="" class="cinema_inquiry movie_no" style="margin-left: 10px;float: none;" />
						</shiro:hasPermission>
					</div>
					<div class="cinema_condition width_84">
						<div class="cinema_smart">
							<div class="cinema_inter">
								<input type="text" id="search_name" placeholder="名称" name="search_name" class="col-xs-10 col-sm-2" value="${res.data.name}" />
							</div>
							<div class="cinema_inter">
								<input type="text" id="search_ouName" placeholder="OU名称" name="search_ouName" class="col-xs-10 col-sm-2" value="${res.data.ouName}" />
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
		<!-- /.page-header -->
		<div class="cinema_list">
			<div class="movie_button">
				<shiro:hasPermission name="sysTool:add">
					<a class="movie_new" href="${ctx}/sysTool/toAdd" style="background-color: rgb(75, 208, 131); color: rgb(255, 255, 255);">新建</a>
				</shiro:hasPermission>
			</div>
			<div class="column_content">
				<table id="sysToolDatas" width="100%" height="" border="0" cellpadding="0" cellspacing="1" class="border_top">
					<tr class="cinema_caption clearfix" style="width: 100%;">
						<th class="column_name" style="width: 20%;">名称</th>
						<th class="column_ouName" style="width: 20%;">OU名称</th>
						<th class="column_status" style="width: 10%;">状态</th>
						<th class="column_note" style="width: 20%;">备注</th>
						<th class="column_createTime" style="width: 20%;">创建时间</th>
						<th class="column_type" style="width: 9%;border-right: none;">操作</th>
					</tr>
					<c:forEach var="res" items="${res.data.rows}">
						<tr class="column_list clearfix" style="width: 100%;" id="tool_row_${res.id}">
							<td class="column_name" style="width: 20%;" title="${res.name}">&nbsp;${res.name}</td>
							<td class="column_ouName" style="width: 20%;" title="${res.ouName}">&nbsp;${res.ouName}</td>
							<td class="column_status" style="width: 10%;" title="<selftag:dictag type="SYS_TOOL_STATUS" value="${res.status}"/>">
								&nbsp;<selftag:dictag type="SYS_TOOL_STATUS" value="${res.status}"/>
							</td>
							<td class="column_note" style="width: 20%;" title="${res.note}">&nbsp;${res.note}</td>
							<td class="column_createTime" style="width: 20%;" title="<fmt:formatDate value="${res.createTime}" type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">&nbsp;<fmt:formatDate value="${res.createTime}" type="date" pattern="yyyy-MM-dd hh:mm:ss" /></td>
							<td class="column_type" style="width: 9%;border-right: none;float: none;">
								<shiro:hasPermission name="sysTool:delete">
									<a class="list_delet" title="删除菜单信息" href="javascript:toolDel(${res.id});" style="margin-left: 0px;float: none;margin-top: 15px;"></a>
								</shiro:hasPermission>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
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
		<script type="text/javascript">
			//菜单删除JS方法
			function toolDel(toolId){
				if(toolId==undefined||toolId+"".trim()==""||toolId<=0){
					alert("未知错误，请重新登录。");
					return false;
				}
				if(confirm("删除该菜单以及所有子菜单？")){
					deleteRowById(toolId);
				}
			}
			function deleteRowById(toolId){
				$.ajax({
					type: "DELETE",
					dataType: "json",
					async: false,
					url: '${ctx}/sysTool/delete/'+toolId,
					success: function (data) {
								if(data!=undefined&&data.status!=undefined&&data.status.trim()=="0"){
									$("#tool_row_"+toolId).remove();
									alert("删除成功。");
									window.location.reload();
								}else{
									alert("error:"+data.error);
								}
							 },
					error: function(data) {
								alert("删除失败。");
							}
				});
			}
		</script>
	</body>
</html>
