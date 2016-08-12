<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/static/common/default.jsp"%>  
<div class="cinema_tip">
	<div class="cinema_subject clearfix">
	    <div class="cinema_title">
	        <a href="#">租户管理</a> >
	        <a href="#" class="cinema_option">租户管理</a>
	    </div>
	</div>
	<c:set var="statusNormal" value="<%=SystemContents.SYS_TENANT_STATUS.NORMAL.value()%>" /> 
    <c:set var="statusDisable" value="<%=SystemContents.SYS_TENANT_STATUS.DISABLE.value()%>" />
	<form method="get" action="toSearch" id="sysTenantSearchForm">
		<div class="cinema_search clearfix">
		        <div class="cinema_button">
		            <input style="background-color: rgb(87, 149, 245);" value="查询" name="" class="cinema_inquiry movie_no" type="submit"/>
		            <input style="background-color: rgb(255, 255, 255);" value="重置" name="" class="cinema_reset movie_no" id="reset" type="button" />
		        </div>
			<div class="cinema_condition">
				<div class="cinema_smart">
                    <div class="cinema_inter">
                        <input id="tenanName" type="text" name="search_tenantName" value="${search.tenantName}" placeholder="租户名称">
                    </div>
                </div>
			</div>
		    
		</div>
	</form>
</div>

<div class="cinema_list">
	<div class="movie_button">
	    <a href="${ctx}/sysTenant/toAdd" class="movie_new">添加</a>
	</div>
	
	<!--列表-->
	<div class="column_content">
	    <table  width="100%" height="" border="0" cellpadding="0" cellspacing="1">
	        <tr class="cinema_caption clearfix">
		        <th class="kaquan_pici" style="width: 13%;">租户名称</th>
		        <th class="kaquan_pici" style="width: 10%;">类型</th>
		        <th class="kaquan_pici" style="width: 16%;">基础数据库驱动</th>
		        <th class="kaquan_pici" style="width: 20%;">基础数据库URL</th>
		        <th class="kaquan_pici" style="width: 13%;">基础数据库用户名</th>
		        <th class="kaquan_pici" style="width: 8%;">租户状态</th>		        
		        <th class="kaquan_pici" style="width: 13%;">操作</th>		        
	    	</tr>
	    	<!--循环重复-->
	     	<c:forEach var="res" items="${res.data.rows}">
            <tr class="column_list clearfix">
            	<td class="kaquan_pici over_hiden" style="width: 13%;">&nbsp;${res.tenantName}</td>
            	<td class="kaquan_pici over_hiden" style="width: 10%;">&nbsp;<selftag:dictag type="SYS_TENANT_TYPE" value="${res.type}"/></td>
            	<td class="kaquan_pici over_hiden" style="width: 16%;">&nbsp;${res.dataJdbcDriver}</td>
            	<td class="kaquan_pici over_hiden" style="width: 20%;">&nbsp;${res.dataJdbcUrl}</td>
            	<td class="kaquan_pici over_hiden" style="width: 13%;">&nbsp;${res.dataJdbcUsername}</td>
            	<td class="kaquan_pici over_hiden" style="width: 8%;">&nbsp;<selftag:dictag type="SYS_TENANT_STATUS" value="${res.status}"/></td>
            	<td class="column_type" style="width: 13%;">
            		<a class="list_mess" href="${ctx}/sysTenant/toModify/${res.id}"></a>
            		<c:if test="${res.status == statusDisable}">
            			<a href="javascript:void(0)" class="list_close" onclick="updateStatus('NORMAL','${res.id}')"></a>
            			<a href="javascript:void(0)" class="list_delet" onclick="updateStatus('delete','${res.id}')"></a>
            			<input type="hidden" id="del${res.id}" value="${res.tenantName}"/>
            		</c:if>
                    <c:if test="${res.status == statusNormal}">
                    	<a href="javascript:void(0)" class="list_open" onclick="updateStatus('DISABLE','${res.id}')"></a>
                    </c:if>
            	</td>
            </tr>
         	</c:forEach>
		</table>
	</div>   
    <div class="clearfix"></div>
    <!-- 分页导航栏 --> 
   	<div id="manager_page" class="manager_page clearfix">
      	<!-- 分页跳转 -->
        <div class="manager_show">
        	<p><span>跳转至</span><input id="pagenum" type="text" name="" value="${res.data.page}"/></p>
        	<span id="toPage" class="manager_true"></span>
        </div>
        <!-- 分页数字导航栏 -->
        <div id="manager_number" class="manager_number"></div>
        	<input type="hidden"  id=records  value="${res.data.records}"/>		<!-- 总记录数 -->
     		<input type="hidden"  id="pageSize"  value="${res.data.pageSize}"/>  <!-- 每页个数 -->
     		<input type="hidden"  id="pageCurrent"  value="${res.data.page}"/>  <!-- 当前页page -->
        <input type="hidden"  id="totalPage"  value="${res.data.total}"/>	    <!-- 总页数 -->
    </div>
	
	<!--查询无结果-->
	<!--提示弹框-->
    <div class="movie_shade hidden"></div>
   	<div class="advance_tip hidden">
       	<div class="movie_subject"><img src="${ctx}/static/threadui/images/movie_close.png" alt=""/>删除租户</div>
       	<div class="movie_center">
           	<p class="del_tip"></p>
       	</div>	
       	<div class="rule_button">
        	<span class="cinema_inquiry">确定</span>
        	<span class="cinema_reset movie_none rule_cancel">取消</span>
       	</div>
   	</div>
    <!--提示弹框结束-->
</div>

<script type="text/javascript">
//清空表单项
$("#reset").click(function(){
	$("#tenanName").val("");
});
function updateStatus(flag,id){
	if(flag == "delete"){ //删除
		//显示删除提示框
		$(".movie_shade").removeClass("hidden");
		$(".advance_tip").removeClass("hidden");
		//删除卖品名称
		var delName = $("#del"+id).val();
		//显示提示内容
		$(".del_tip").html("确定删除租户：<p style='font-size:16px;display: inline-block;'>"+delName+"</p> 吗？");
		//点击确认执行删除操作
		$(".cinema_inquiry").click(function(){
			window.location.href="${ctx}/sysTenant/delete/"+id;
		});
	}
	else if(flag == "DISABLE"){ //停用
		var status = "<%=SystemContents.SYS_TENANT_STATUS.DISABLE.value()%>";
		window.location.href="${ctx}/sysTenant/modify?status="+status+"&id="+id;
	}
	else if(flag == "NORMAL"){ //正常
		var status = "<%=SystemContents.SYS_TENANT_STATUS.NORMAL.value()%>";
		window.location.href="${ctx}/sysTenant/modify?status="+status+"&id="+id;
	}
}

</script>
	
            
	

