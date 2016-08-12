<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/static/common/default.jsp"%> 
<div class="cinema_tip">
	<div class="cinema_subject clearfix">
	    <div class="cinema_title">
	        <a href="#">租户管理</a> >
	        <a href="#" class="cinema_option">租户应用管理</a>
	    </div>
	</div>
	
	<form method="get" action="toSearch" id="sysTenantAppSearchForm">
		<div class="cinema_search clearfix">
		        <div class="cinema_button">
		            <input style="background-color: rgb(87, 149, 245);" value="查询" name="" class="cinema_inquiry movie_no" type="submit"/>
		            <input style="background-color: rgb(255, 255, 255);" value="重置" name="" class="cinema_reset movie_no" id="reset" type="button"/>
		        </div>
			<div class="cinema_condition">
				<div class="cinema_smart">
                    <div class="cinema_inter">
                        <input id="businessTenantId" type="text" name="search_businessTenantId" value="${search.businessTenantId }" placeholder="业务租户">
                    </div>
                    <div class="cinema_inter">
                        <input id="appName" type="text" name="search_appName" value="${search.appName }" placeholder="应用名称">
                    </div>
                    <div class="cinema_inter">
                        <input id="appCode" type="text" name="search_appCode" value="${search.appCode }" placeholder="应用标识码">
                    </div>
                </div>
			</div>
		    
		</div>
	</form>
</div>

<div class="cinema_list">
	<div class="movie_button">
	    <a href="${ctx}/sysTenantApp/toAdd" class="movie_new">添加</a>
	</div>
	<!--列表-->
	<div class="column_content">
	    <table  width="100%" height="" border="0" cellpadding="0" cellspacing="1">
	        <tr class="cinema_caption clearfix">
		        <th class="kaquan_pici" style="width: 18%;">应用名称</th>
		        <th class="kaquan_pici" style="width: 15%;">应用标识码</th>
		        <th class="kaquan_pici" style="width: 20%;">业务数据库驱动</th>
		        <th class="kaquan_pici" style="width: 24%;">业务数据库URL</th>
		        <th class="kaquan_pici" style="width: 15%;">操作</th>			        	
	    	</tr>
	    	<!--循环重复-->
	     	<c:forEach var="res" items="${res.data.rows}">
            <tr class="column_list clearfix">
            	<td class="kaquan_pici over_hiden" style="width: 18%;">&nbsp;${res.appName}</td>
            	<td class="kaquan_pici over_hiden" style="width: 15%;">&nbsp;${res.appCode}</td>
            	<td class="kaquan_pici over_hiden" style="width: 20%;">&nbsp;${res.businessJdbcDriver}</td>
            	<td class="kaquan_pici over_hiden" style="width: 24%;">&nbsp;${res.businessJdbcUrl}</td>
            	<td class="column_type over_hiden" style="width: 15%;">
            		<a class="list_mess" href="${ctx}/sysTenantApp/toModify/${res.id}"></a>
            		<a href="javascript:void(0)" class="list_delet" onclick="updateStatus('delete','${res.id}')"></a>
            		<input type="hidden" id="del${res.id}" value="${res.appName}"/>
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
       	<div class="movie_subject"><img src="${ctx}/static/threadui/images/movie_close.png" alt=""/>删除租户应用</div>
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
	$("#businessTenantId").val("");
	$("#appName").val("");
	$("#appCode").val("");
});
function updateStatus(flag,id){
	if(flag == "delete"){ //删除
		//显示删除提示框
		$(".movie_shade").removeClass("hidden");
		$(".advance_tip").removeClass("hidden");
		//删除卖品名称
		var delName = $("#del"+id).val();
		//显示提示内容
		$(".del_tip").html("确定删除租户应用：<p style='font-size:16px;display: inline-block;'>"+delName+"</p> 吗？");
		//点击确认执行删除操作
		$(".cinema_inquiry").click(function(){
			window.location.href="${ctx}/sysTenantApp/delete/"+id;
		});
	}
}
</script>


