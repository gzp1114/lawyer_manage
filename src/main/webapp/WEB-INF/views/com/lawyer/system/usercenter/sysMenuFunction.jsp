<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/default.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>菜单管理</title>
		<link rel="stylesheet" href="${ctx}/static/threadui/thirdtree/zTreeStyle/metro.css" />
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
		<script src="${ctx}/static/threadui/thirdtree/ztree/js/jquery.ztree.core.js"></script>
		<div class="cinema_tip">
		    <div class="cinema_subject clearfix">
		        <div class="cinema_title">
		            <a href="#">用户中心</a>
		            >
		            <a href="#" class="cinema_option">菜单管理</a>
		        </div>
		    </div>
			<form  id="sysMenuFunctionSearchForm" method="get">
				<input type="hidden" id="search_parentId" name="search_parentId" placeholder="上级菜单id" class="col-xs-10 col-sm-2"  value="${res.data.parentId}"/>
				<div class="cinema_search clearfix">
					<div class="cinema_button">
						<shiro:hasPermission name="sysMenuFunction:search">
							<input id="sysMenuFunctionSearch" style="float: none;margin-left: 10px;" type="submit" value="查询" name="" class="cinema_inquiry movie_no">
						</shiro:hasPermission>
					</div>
					<div class="cinema_condition width_84">
						<div class="cinema_smart">
							<div class="cinema_inter">
								<input type="text" id="search_menuName" name="search_menuName" placeholder="菜单名" class="col-xs-10 col-sm-2"  value="${res.data.menuName}"/>
							</div>
							<div style="display: inline-block;line-height: 30px;height: 30px;">
								<select name="search_menuType" id="search_menuType">
									<option value="">菜单类型</option>
									<option value="1" ${res.data.menuType==1?"selected='selected'":'' }>模块</option>
									<option value="2" ${res.data.menuType==2?"selected='selected'":'' }>菜单</option>
									<option value="3" ${res.data.menuType==3?"selected='selected'":'' }>功能</option>
								</select>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
		<div class="cinema_list" style="min-height: 400px;">
		    <div class="movie_button">
				<%-- <shiro:hasPermission name="sysMenuFunction:add"> --%>
		    		<a class="movie_new" href="javascript:hrefToAdd('${ctx}/sysMenuFunction/toAdd/');" style="background-color: rgb(75, 208, 131); color: rgb(255, 255, 255);">新建</a>
			</div>
			<div id="sidebar" class="sidebar responsive" style="display: inline-block;width: 19%;float: left;">
				<div class="zTreeDemoBackground left">
					<ul id="menuTree" class="ztree"></ul>
				</div>
			</div>
			<div class="column_content" style="display: block;width: 80%;">
		        <table width="100%" id="sysMenuFunctionDatas" style="table-layout:fixed;" height="" border="0" cellpadding="0" cellspacing="1" class="border_top">
		            <tr class="cinema_caption clearfix" style="width: 100%;">
		                <th class="column_menuName" style="width: 25%;">菜单名</th>
		                <th class="column_menuType" style="width: 10%;">菜单类型</th>
		                <th class="column_isValid" style="width: 10%;">是否有效</th>
		                <th class="column_menuUrl" style="width: 40%;">菜单URL</th>
		                <th class="column_type" style="width: 14%;border-right: none;">操作</th>
		            </tr>
		            <c:forEach var="res" items="${res.data.rows}">
			            <tr class="column_list clearfix" id="menu_row_${res.id}" style="width: 100%;" >
							<td style="width: 25%;" class="column_menuName" title="${res.menuName}">&nbsp;${res.menuName}</td>
							<td style="width: 10%;" class="column_menuType" title="<selftag:dictag type="SYS_MENU_FUNCTION_MENU_TYPE" value="${res.menuType}"/>">&nbsp;
								<selftag:dictag type="SYS_MENU_FUNCTION_MENU_TYPE" value="${res.menuType}"/>
							</td>
							<td style="width: 10%;" class="column_isValid" title="<selftag:dictag type="SYS_MENU_FUNCTION_IS_VALID" value="${res.isValid}"/>">&nbsp;
								<selftag:dictag type="SYS_MENU_FUNCTION_IS_VALID" value="${res.isValid}"/>
							</td>
							<td style="width: 40%;" class="column_menuUrl" title="${res.menuUrl}">&nbsp;${res.menuUrl}</td>
							<td style="width: 14%;border-right: none;" class="column_type">&nbsp;
								<shiro:hasPermission name="sysMenuFunction:modify">
									<a class="list_mess" title="编辑菜单信息" href="${ctx }/sysMenuFunction/toModify/${res.id}" style="margin-left: 0px;;float: none;margin-top: 0px;"></a>
								</shiro:hasPermission>
								<shiro:hasPermission name="sysMenuFunction:delete">
									<a class="list_delet" title="删除菜单信息" href="javascript:menuFunDel(${res.id});" style="margin-left: 0px;float: none;margin-top: 0px;"></a>
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
			function menuFunDel(menuId){
				if(menuId==undefined||menuId+"".trim()==""||menuId<=0){
					alert("未知错误，请重新登录。");
					return false;
				}
				if(confirm("删除该菜单以及所有子菜单？")){
					deleteRowById(menuId);
				}
			}
			function deleteRowById(menuId){
				$.ajax({
					type: "DELETE",
					dataType: "json",
					async: false,
					url: '${ctx}/sysMenuFunction/delete/'+menuId,
					success: function (data) {
								console.log(data);
								if(data!=undefined&&data.status!=undefined&&data.status.trim()=="0"){
									$("#menu_row_"+menuId).remove();
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
			//查询按键操作事件
			$( "#sysMenuFunctionSearch" ).on('click', function(e) {
				return search();
			});
			function hrefToAdd(hrefUrl){
				var search_parentId = $("#search_parentId").val();
				if(search_parentId==undefined||search_parentId.trim()==''){
					search_parentId = 0;
				}
				window.location.href = hrefUrl+search_parentId;
			}
			function beforeClick(treeId, treeNode, clickFlag) {
				return (treeNode.click != false);
			}
			function onClick(event, treeId, treeNode, clickFlag) {
				$('#search_parentId').val(treeNode.id);
				return search();
			}
			//查询菜单功能
			function search(){
				$("form").submit();
				return false;
			}
			var setting = {
				data: {
					simpleData: {
						enable: true
					}
				},
				callback: {
					beforeClick: beforeClick,
					onClick: onClick
				}
		 	};
			function getTreeData(){
				var zNodes = new Array();
				$.ajax({
			        type: "POST",
			        dataType: "json",
			        async: false,
			        url: '${ctx}/sysMenuFunction/allTree/',
			        success: function (data) {
			        	if(data.status!='0'){
			        		alert(data.error);
			        		return false;
			        	}
			        	for (var i = 0; i < data.data.length; i++) {
			        		var menuId = $("#search_parentId").val();
			        		var node =new Object();
			        		node.id=data.data[i].id;
			        		node.pId=data.data[i].parentId;
			        		node.name=data.data[i].menuName;
			        		node.open = false;
			        		if(menuId!=undefined&&menuId==data.data[i].id){
				        		node.open = true;
			        		}
			        		zNodes.push(node);
			            }
			        },
			        error: function(data) {
			            alert("error:"+data.error);
			         }
			    });
				return zNodes;
			}
			function initTree(){
				$.fn.zTree.init($("#menuTree"), setting,getTreeData());
				var zTree = $.fn.zTree.getZTreeObj("menuTree");
				var node = zTree.getNodeByParam("id",$("#search_parentId").val());
				zTree.selectNode(node);
			}
			initTree();
			//查询菜单功能
		</script>
	</body>
</html>
