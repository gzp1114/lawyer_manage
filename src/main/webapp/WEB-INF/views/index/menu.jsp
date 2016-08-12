<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/static/common/default.jsp"%>

<script type="text/javascript">
	try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
</script>
<script type="text/javascript" src="${ctx}/static/threadui/js/jquery-1.11.3.js"></script>
<script type="text/javascript" src="${ctx}/static/threadui/js/style.js"></script>
<script type="text/javascript" src="${ctx}/static/threadui/datejs/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/static/threadui/js/jquery.select.js"></script>
<script type="text/javascript" src="${ctx}/static/threadui/js/demo.js"></script>
<script type="text/javascript">

function menuClick(url){
	$("#page-content").load('${ctx}'+url);
};

</script>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>

    <link type="text/css"  rel="stylesheet" href="${ctx}/static/threadui/css/reset.css">
    <link type="text/css"  rel="stylesheet" href="${ctx}/static/threadui/css/style.css">
    <link rel="stylesheet" href="${ctx}/static/threadui/css/select_tuikuan.css" />
</head>
<body>
    <div class="manager_content">
<!--左侧开始-->
        <div class="manager_left">
            <ul>
            
                <li class="manager_tip">
                    <span class="manager_down">用户中心</span>
                </li>
                <li class="manager_list">
                <a href="#" onclick="menuClick('/sysUserInfo/toSearch');">
					用户管理
				</a>
                    <a href="#" onclick="menuClick('/sysMenuFunction/toSearch');">
					菜单管理
				</a>
				<!-- <a href="#" onclick="">
					部门管理
				</a> -->
                <a href="#" onclick="menuClick('/sysRole/toSearch');">
					角色管理
				</a>
				<a href="#" onclick="menuClick('/sysTool/toSearch');">
					应用工具管理
				</a>

                </li>
                <li class="manager_tip manager_disc">
                    <span>租户管理</span>
                </li>
                <li class="manager_list hidden">
                    <a href="#" onclick="menuClick('/sysTenant/toSearch')">
					租户管理
				</a>
                    <a href="#" onclick="menuClick('/sysTenantApp/toSearch')">
					租户应用管理
				</a>
                </li>
                <li class="manager_tip manager_disc">
                    <span>集成开发环境</span>
                </li>
                <li class="manager_list hidden">
                    <a href="#" onclick="menuClick('/autoDevelop/toCodeBuild');">
					代码构建
				</a>
                </li>
            </ul>
        </div>
        </div>

</body>