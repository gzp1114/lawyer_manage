<%@ page pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/static/common/default.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%  //获取项目名  
    String path = request.getContextPath();  
    //http://localhost:8080/项目名/  
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
 %>
 
 <html>  
	<head>  
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">  
		<title>角色管理</title>
	</head> 
	<script type="text/javascript">
	

	</script>
	<body>
		<div class="cinema_tip">
			<div class="cinema_subject clearfix">
			        <div class="cinema_title">
			            <a href="#">用户中心</a>
			            >
			            <a href="#" class="cinema_option">角色管理</a>
			        </div>
			</div>
			<form id="sysRoleSearchForm" method="get">
			       <div class="cinema_search clearfix">
						<div class="cinema_button">
							<shiro:hasPermission name="sysRole:search">
								<input type="submit" id="sysRoleSearch" class="cinema_inquiry movie_no" name="" value="查询" style="background-color: rgb(87, 149, 245);">
							</shiro:hasPermission>
						</div>
						<div class="cinema_condition width_84">
							<div class="cinema_smart">
								<div class="cinema_inter">
									<input type="text" id="search_roleName" name="search_roleName" placeholder="角色名称" class="col-xs-10 col-sm-2"  value="${data.data.roleName}"/> 
								</div>
							</div>
						</div>
					</div>
			</form>
		</div>
		<script type="text/javascript">
		//删除角色操作
		function deleteRowById(rowId){
			$.ajax({
					type: "GET",
					dataType: "json",
					async: false,
					url: '${ctx}/sysRole/delete/'+rowId,
					success: function (data) {
						if(data!=undefined&&data.status!=undefined&&data.status.trim()=="0"){
							$("#role_row_"+rowId).remove();
							alert("删除成功。");
						}else{
							alert("error:"+data.error);
						}
					},
				error: function(data) {
					alert("error:"+data.error);
				}
			});
			return false;
		}
		function deleteClick(rowId) {
			if(rowId==undefined||rowId<=0||(rowId+"").trim()==""){
				alert("请选择需要修改记录！");
				return false;
			}
			if(confirm('确定删除吗 ？')) {
				//调用删除操作
				deleteRowById(rowId);
			}
		}
		</script>
		
		<div class="cinema_list">
		    <div class="movie_button">
			    <shiro:hasPermission name="sysRole:add">
			    	<a class="movie_new" onclick="window.location='${ctx}/sysRole/toAdd';" style="background-color: rgb(75, 208, 131); color: rgb(255, 255, 255);">新建</a>
			    </shiro:hasPermission>
			</div>
		    <div  class="column_content">
				 <table width="100%" height="" border="0" cellpadding="0" cellspacing="1" class="border_top">
					<tr class="cinema_caption clearfix" style="width: 100%;">
					    <th class="column_roleName" style="width: 20%;">角色名称</th>
					    <th class="column_note" style="width: 30%;">备注</th>
					    <th class="column_createTime" style="width: 30%;">创建时间</th>
					    <th class="column_caozuo" style="width: 19%;border-right: none;">操作</th>
					</tr>
					<!--循环重复-->
					
					<c:if test="${!empty data}">
						<c:forEach var="res" items="${data.data.rows}">
							<tr class="column_list clearfix" id="role_row_${res.id}" style="width: 100%;">
								<td class="column_sale" style="width: 20%;" title="${res.roleName}">&nbsp;${res.roleName}</td>
								<td class="column_note" style="width: 30%;" title="${res.note==null||res.note==''?' ':res.note}">&nbsp;${res.note==null||res.note==''?' ': res.note}</td>
								<td class="column_createTime" style="width: 30%;" title="<fmt:formatDate value="${res.createTime}" type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">&nbsp;<fmt:formatDate value="${res.createTime}" type="date" pattern="yyyy-MM-dd hh:mm:ss"/></td>
								<td class="column_caozuo" style="width: 19%;border-right: none;">&nbsp;
									<%-- <shiro:hasPermission name="sysRole:modify"> --%>
										<a class="list_mess" onclick="window.location='${ctx}/sysRole/toModify/${res.id}';" title="编辑角色信息"></a>
									<%-- <shiro:hasPermission name="sysRole:delete"> --%>
										<a class="list_close" href="javaScript:deleteClick(${res.id});" title="删除角色信息"></a>
									<%-- </shiro:hasPermission> --%>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
				<div id="manager_page" class="manager_page clearfix">
					<!-- 分页跳转 -->
					<div class="manager_show">
						<p><span>跳转至</span><input id="pagenum" type="text" name="" value="${data.data.page}"/></p>
						<span id="toPage" class="manager_true"></span>
					</div>
					<!-- 分页数字导航栏 -->
					<div id="manager_number" class="manager_number"></div>
					<input type="hidden"  id=records  value="${data.data.records}"/>			<!-- 总记录数 -->
					<input type="hidden"  id="pageSize"  value="${data.data.pageSize}"/>  <!-- 每页个数 -->
					<input type="hidden"  id="pageCurrent"  value="${data.data.page}"/>  <!-- 当前页page -->
					<input type="hidden"  id="totalPage"  value="${data.data.total}"/>	    <!-- 总页数 -->
				</div>
			</div>
		</div>
	</body>
</html>
