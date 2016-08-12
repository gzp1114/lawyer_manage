<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/default.jsp"%>
<html>
	<head>
		<title>新建角色</title>
		<link rel="stylesheet" href="${ctx}/static/threadui/thirdtree/zTreeStyle/metro.css" />
		<style type="text/css">
			.form_textarea{
				border: 1px solid #DFE7F0;
				max-height: 50px;
				min-height: 50px;
				max-width: 300px;
				min-width: 300px;
			}
		</style>
		<link type="text/css" rel="stylesheet" href="${ctx}/static/tools/jquery-validationEngine/validationEngine.jquery.css"></link>
	</head>
	<body>
		<script type="text/javascript" src="${ctx}/static/tools/jquery-validationEngine/jquery.validationEngine.js"></script>
		<script type="text/javascript" src="${ctx}/static/tools/jquery-validationEngine/jquery.validationEngine-cn.js"></script>
		<script src="${ctx}/static/threadui/thirdtree/ztree/js/jquery.ztree.core.js"></script>
		<script src="${ctx}/static/threadui/thirdtree/ztree/js/jquery.ztree.excheck.js"></script>
		<div class="cinema_tip">
		    <div class="cinema_subject clearfix">
		        <div class="cinema_title">
		            <a href="javascript:void(0);">用户中心</a> >
		            <a id="userinfo_list_href" href="${ctx}/sysRole/toSearch">角色管理</a> >
		            <a href="javascript:void(0);" class="cinema_option">新建角色</a>
		        </div>
		        <a href="${ctx}/sysRole/toSearch" class="movie_return">返回列表</a>
		    </div>
		</div>
		<div class="cinema_list">
			<form class="form-horizontal" role="form" id="sysRoleAddForm" onsubmit="return sysRoleAdd();" name="sysRoleAddForm">
				<input id="menuids" name="menuids" class="form-control" type="hidden" value="">
				<div class="movie_list">
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>角色名称：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input type="text" id="roleName" maxlength="50" class="validate[required],maxSize[50]" name="roleName" placeholder="角色名称" value=""/>
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
					<dl class="movie_fill">
						<div class="zTreeDemoBackground">
							<ul id="menuTree" class="ztree"></ul>
						</div>				
					</dl>
					<div class="clearfix"></div>
					<a class="movie_continue">
						<input type="submit" name="submit" id="submit" value="提交" class="movie_new cinema_inquiry" style="background-color: rgb(87, 149, 245);"/>
					</a>
				</div>
			</form>
		</div>
		<script type="text/javascript">
			var setting = {
				check : {
					chkboxType : {
						"Y" : "ps",
						"N" : "ps"
					},
					enable : true
				},
				data : {
					simpleData : {
						enable : true
					}
				}
			};
			function filter(treeId, parentNode, childNodes) {
				if (!childNodes)
					return null;
				for (var i = 0, l = childNodes.length; i < l; i++) {
					childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
				}
				return childNodes;
			}
	
			function getTreeData() {
				var zNodes = new Array();
				$.ajax({
					type : "POST",
					dataType : "json",
					async : false,
					url : '${ctx}/sysMenuFunction/allTree/',
					success : function(data) {
						if (data.status != '0') {
							alert(data.error);
						}
						for (var i = 0; i < data.data.length; i++) {
							var node = new Object();
							node.id = data.data[i].id;
							node.pId = data.data[i].parentId;
							node.name = data.data[i].menuName;
							node.open = false;
							zNodes.push(node);
						}
					},
					error : function(data) {
						alert("error:" + data.error);
					}
				});
				return zNodes;
			}
			var code;
			function setCheck() {
				var zTree = $.fn.zTree.getZTreeObj("menuTree"), type = {
					"Y" : 'ps',
					"N" : 'ps'
				};
				zTree.setting.check.chkboxType = type;
				showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
			}
			function showCode(str) {
				if (!code)
					code = $("#code");
				code.empty();
				code.append("<li>" + str + "</li>");
			}
			function getCheckNodes() {
				var menuids = '';
				var zTree = $.fn.zTree.getZTreeObj("menuTree");
				var checkNodes = zTree.getCheckedNodes(true);
				checkNodes.forEach(function(node) {
					menuids += node.id + ",";
				});
				var len = menuids.length;
				menuids = menuids.substr(0, len - 1);
				return menuids;
			}
			//增加角色表
			function sysRoleAdd() {
				if($("#sysRoleAddForm").validationEngine("validate")){
					var menuids = getCheckNodes();
					$('#menuids').val(menuids);
					$.ajax({
						type : "POST",
						dataType : "json",
						async : false,
						url : '${ctx}/sysRole/add',
						data : $('#sysRoleAddForm').serialize(),
						success : function(data) {
							if (data.status != '0') {
								alert(data.error);
								return false;
							}
							alert("保存成功！");
							window.location.href = '${ctx}/sysRole/toSearch?rows=10';
						},
						error : function(data) {
							alert("error:" + data.error);
						}
					});
				}
				return false;
			}
			jQuery(function($) {
				$.fn.zTree.init($("#menuTree"), setting, getTreeData());
			});
			$("#sysRoleAddForm").validationEngine();
		</script>
	</body>
</html>