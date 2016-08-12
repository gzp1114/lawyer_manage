<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/default.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>债务信息管理</title>
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
		            <a href="#">案源中心</a>
		            >
		            <a href="#" class="cinema_option"> 批量操作</a>
		        </div>
		    </div>
		</div>
		<div class="cinema_list">
		    <div class="listxiangqing_content">
		    	<div class="xiangqing_list1">
                    <h1>债务主体信息</h1>
                </div>
                <div class="xiangqing_list2">
                    <h1>债务信息</h1>
                    <div class="list1 clearfix">
                    <ul class="list1_left">
                        <li> 
	                        <div class="cinema_button">
	                        	<input type="button" id="debtor" class="cinema_inquiry" onclick="javaScript:importdebtors(1);" value="被执行信息" style="background-color: rgb(87, 149, 245);">
	                    		<sapn id="debtor_message" style="color:#ec0d20;display:none;">执行中。。。</sapn>
	                    	</div>
                    	</li>
                    </ul>
                    <ul class="list1_right">
                        <li>   
                        	<div class="cinema_button">
	                        	<input type="button" id="announcement" class="cinema_inquiry" onclick="javaScript:importdebtors(2);" value="公告信息" style="background-color: rgb(87, 149, 245);">
	                    		<sapn id="announcement_message" style="color:#ec0d20;display:none;">执行中。。。</sapn>
	                    	</div>
                        </li>
                    </ul>
                    </div>
                </div>
                
		</div>
		<script type="text/javascript">
		function importdebtors(type) {
			
			if(confirm('确定进行被执行信息数据导入吗 ？')) {
				$('#debtor_message').show();
				
				$.ajax({
					type: "GET",
					dataType: "json",
					async: false,
					url: '${ctx}/sysDebtorCompany/import/'+type,
					success: function (result) {
						if(result!=undefined&&result.status!=undefined&&result.status.trim()=="0"){
							alert(result.data);
						}else{
							alert("error:"+result.error);
						}
						$('#debtor_message').hide();
					},
					error: function(result) {
						alert("error:"+result.error);
						$('#debtor_message').hide();
					}
				});
				return false;
			}
		}
		</script>
	</body>
</html>
