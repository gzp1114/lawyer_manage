<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/default.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>案源信息管理</title>
		<style type="text/css">
			#sidebar{
				min-height: 200px;
			}
			.select_option{
				width: 185px;
				margin-top: -5px;
				z-index: 1;
				position: absolute;
			}
			.select_showbox{
				width: 180px;
				margin-top: 0px;
				height: 28px;
				line-height: 30px;
			}
			.select_box{
				margin-top: 0px;
			}
		</style>
	</head>
	<body>
		<div class="cinema_tip">
		    <div class="cinema_subject clearfix">
		        <div class="cinema_title">
		            <a href="#">案源中心</a>
		            >
		            <a id="userinfo_list_href" href="${ctx}/sysLawyerSource/toSearch">案源信息管理</a>
		        </div>
		    </div>
			<form id="sysLawyerSourceSearchForm"  method="get">
			       <div class="cinema_search clearfix">
						<div class="cinema_button">
							<%-- <shiro:hasPermission name="sysLawyerSource:search"> --%>
								<input type="submit" id="sysLawyerSourceSearch" class="cinema_inquiry movie_no" name="" value="查询" style="background-color: rgb(87, 149, 245);">
						</div>
						<div class="cinema_condition width_84">
							<div class="cinema_smart">
								<div class="cinema_inter">
									<input type="text" id="search_debtorName" name="search_debtorName" placeholder="债务主体名称" class="col-xs-10 col-sm-2"  value="${result.data.debtorName}"/> 
								</div>
								<div class="cinema_inter">
									<input type="text" id="search_claimName" name="search_claimName" placeholder="债权主体名称" class="col-xs-10 col-sm-2"  value="${result.data.claimName}"/> 
								</div>
							</div>
						</div>
					</div>
			</form>
		</div>
		<div class="cinema_list">
		    <div class="movie_button">
			    <%-- <shiro:hasPermission name="sysLawyerSource:add"> --%>
			    	<a class="movie_new" onclick="window.location='${ctx}/sysLawyerSource/toAdd';" style="background-color: rgb(75, 208, 131); color: rgb(255, 255, 255);">新建</a>
			</div>
		    <div  class="column_content">
				 <table width="100%" height="" border="0" cellpadding="0" cellspacing="1" class="border_top">
					<tr class="cinema_caption clearfix" style="width: 100%;">
					    <th class="column_name" style="width: 25%;">债务主体</th>
					    <th class="column_name" style="width: 25%;">债权主体</th>
					    <th class="column_name" style="width: 10%;">联系人</th>
					    <th class="column_createTime" style="width: 20%;">联系时间</th>
					    <th class="column_caozuo" style="width: 19%;border-right: none;">操作</th>
					</tr>
					<!--循环重复-->
					
					<c:if test="${!empty result}">
						<c:forEach var="res" items="${result.data.rows}">
							<tr class="column_list clearfix" id="role_row_${res.id}" style="width: 100%;">
								<td class="column_name" style="width: 30%;" title="${res.debtorName}">&nbsp;${res.debtorName}</td>
								<td class="column_name" style="width: 10%;" title="${res.claimName}">&nbsp;${res.claimName}</td>
								<td class="column_name" style="width: 20%;" title="${res.userName}">&nbsp;${res.userName}</td>
								<td class="column_createTime" style="width: 20%;" title="<fmt:formatDate value="${res.contectTime}" type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">&nbsp;<fmt:formatDate value="${res.contectTime}" type="date" pattern="yyyy-MM-dd hh:mm:ss"/></td>
								<td class="column_caozuo" style="width: 19%;border-right: none;">&nbsp;
									<%-- <shiro:hasPermission name="sysLawyerSource:modify"> --%>
										<a class="list_op" href="${ctx}/sysLawyerSource/toShow/${res.id}" >详情</a>
									<%-- <shiro:hasPermission name="sysLawyerSource:delete"> --%>
										<a class="list_op" href="javaScript:deleteClick(${res.id});" >删除</a>
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
		<script type="text/javascript">
		function deleteRowById(rowId){
			$.ajax({
					type: "GET",
					dataType: "json",
					async: false,
					url: '${ctx}/sysLawyerSource/delete/'+rowId,
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
	</body>
</html>
