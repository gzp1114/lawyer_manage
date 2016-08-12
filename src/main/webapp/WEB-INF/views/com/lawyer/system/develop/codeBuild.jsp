<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/static/common/default.jsp"%>

<div class="cinema_tip">
	<div class="cinema_subject clearfix">
	    <div class="cinema_title">
	        <a href="#">集成开发环境</a> >
	        <a href="#" class="cinema_option">代码构建</a>
	    </div>
	</div>
	<div  class="form-group">
		<form id="fileUploadForm" name="fileUploadForm" action="uploadPDM" method="post"
			enctype="multipart/form-data" >
			<div style="margin-top:10px;">
				<input id="file" type="file" name="myfile" style="margin-left: 30%;">			
				<input id="upload" type="submit" class="btn btn-sm btn-info" onclick ="return upPdm();">
					<span class="glyphicon 	glyphicon glyphicon-upload" aria-hidden="true"></span>
					<span class="bigger-110 no-text-shadow"  >上  传PowerDesigner文件</span>
				</input>
			</div>		
		</form>
	</div>
	<div class="form-group">
		<div id="download"></div>
	</div>
<!-- /.page-header -->
<!--列表-->
<div class="cinema_list">
	<div class="movie_button">
		    <a href="javascript:void(0)" class="movie_new" style="width:150px;" onclick="create()">生成开发环境</a>
	</div>
	<div class="column_content">
	    <table  width="100%" height="" border="0" cellpadding="0" cellspacing="1">
	        <tr class="cinema_caption clearfix">
	        	<th class="kaquan_pici" style="width: 5%;">&nbsp;</th>
		        <th class="kaquan_pici" style="width: 22%;">表编码</th>
		        <th class="kaquan_pici" style="width: 22%;">表名</th>
		        <th class="kaquan_pici" style="width: 22%;">项目名</th>
		        <th class="kaquan_pici" style="width: 22%;">备注</th>       
	    	</tr>
	    	<!--循环重复-->
	     	<c:forEach var="res" items="${res }">
	           <tr class="column_list clearfix">
	           	<td class="kaquan_pici over_hiden" style="width: 5%;">
	            	<input type="checkbox" name="checkbox" id='check${res.code }' class="hidden"/>
	            	<label class="movie_checkbox" value="${res.code }" for="check${res.code }"></label>
	            </td>
	           	<td class="kaquan_pici over_hiden" style="width: 22%;">&nbsp;${res.code }</td>
	           	<td class="kaquan_pici over_hiden" style="width: 22%;">&nbsp;${res.name }</td>
	           	<td class="kaquan_pici over_hiden" style="width: 22%;">&nbsp;${res.projectName }</td>
	           	<td class="kaquan_pici over_hiden" style="width: 22%;">&nbsp;${res.comment }</td>
	           </tr>
	        </c:forEach>
		</table>
	</div>
<div class="clearfix"></div>
</div>
<!--弹出输入框-->
<div class="movie_shade hidden"></div>
<div class="advance_tip hidden">
 	<div id="subject" class="movie_subject">
 		<img src="${ctx }/static/threadui/images/movie_close.png" alt=""/>请输入包路径</div>
    <div class="movie_center">
           <textarea name="packageName" id="packageName" cols="10" rows="4" class="xadd" placeholder="请输入包路径" style="height:80px"></textarea>
           <a id="down" class="movie_new" href="javascript:void(0)" style="display: none;">
           	<span class="glyphicon glyphicon glyphicon-download" aria-hidden="true"></span>下载代码文件</a>
    </div>
    <div class="clear_button">
        <label id="inquiry"><a id="cinema_inquiry" class="cinema_inquiry">确定</a></label>
        <a id="cinema_quiry" class="cinema_reset movie_none rule_cancel">取消</a>
    </div>
</div>
<!--弹出输入框-->
<!--提示弹框-->
<div class="advance_tip1 hidden">
    <div id="movie_subject" class="movie_subject">
    	<img id="tip1_canel" src="${ctx }/static/threadui/images/movie_close.png" alt=""/></div>
    <div class="rule_button">
        <span id="cinema_inquiry1" class="cinema_inquiry inquiry1">确定</span>
    </div>
</div>
<!--弹出判断框-->
</div>

<style type="text/css">
	.ui-jqgrid .ui-userdata{height:39px}
	.page-header{padding-bottom:0px}
	.page-header{margin:0 0 4px}
	.btn-sm{padding:3px 9px}
	.ace-nav{height:0}
	#file {display: inline-block!important;}
</style>
<script type="text/javascript">
function upPdm(){
	var oFile = document.getElementById('file');
	if(oFile.value == ""){
		$(".movie_shade").removeClass("hidden");		
    	$(".advance_tip1").removeClass("hidden");
    	$("#movie_subject").text("请选择pdm格式文件");
    	return false;
	}
}
//提示栏x
$("#tip1_canel").click(function(){
	$(".advance_tip1").addClass("advance_tip1 hidden");
});
//取消
$("#cinema_quiry").click(function(){
	$(".advance_tip").addClass("advance_tip hidden");
	$("#packageName").css({"display":"block"});
	$("#down").css({"display":"none"});
});
//确定
$("#cinema_inquiry1").click(function(){
	$(".movie_shade").addClass("movie_shade hidden");
	$(".advance_tip1").addClass("advance_tip1 hidden");
});
//生成开发环境
function create() {
	$("#subject").text("请输入包路径");
	var tbNames = "";
	var rowData_length = $(".movie_check").length;
	console.log(rowData_length);
    if(rowData_length != 0) {
        for(var i=0;i<rowData_length;i++){
           var name= $(".movie_check")[i].getAttribute("value");//name是colModel中的一属性
           tbNames+=","+name;
        }
        console.log(tbNames);
    }
    if(tbNames==""){
    	$(".movie_shade").removeClass("hidden");		
    	$(".advance_tip1").removeClass("hidden");
    	$("#movie_subject").text("请选择表名");
    	return;
    }
  	//如果勾选了数据
	if(tbNames != ""){
		$(".movie_shade").removeClass("hidden");		
		$(".advance_tip").removeClass("hidden");
		$("#inquiry").css({"display":"inline-block"});
		$("#packageName").val("");
		//点击填写备注弹框的确定按钮
		$("#cinema_inquiry").click(function(){
			var packageName = $("#packageName").val();
			console.log(packageName);
			if (packageName === "") {	
				$(".advance_tip").addClass("advance_tip hidden");
		    	$(".advance_tip1").removeClass("hidden");
		    	$("#movie_subject").text("包路径呢？");
				return;
			}
			//生成代码，并提供下载地址
			$.ajax({
	            type: "post", 
	            async: false,
	            url: "${ctx}/autoDevelop/engine?tableNames="+tbNames+"&fileName=${pdmFile}"+"&packagePath="+packageName, 
	            dataType: "text", 
	            success: function (data) {
	            	console.log(data);
	            	$("#subject").text("下载");
	            	$("#packageName").css({"display":"none"});
	            	$("#down").css({"display":"block"});
	            	$("#inquiry").css({"display":"none"});
	            	//点击下载
	            	$("#down").click(function(){
	            		$(".movie_shade").addClass("movie_shade hidden");
		            	$(".advance_tip").addClass("advance_tip hidden");
		            	$("#packageName").css({"display":"block"});
		            	$("#down").css({"display":"none"});
	            		window.location.href="${ctx}/autoDevelop/download?fileName="+data;            		
	            	});	            	
	            },error: function(e) { 
	            	alert("系统错误"); 
	            }
	    	});
		});
	}
}
</script>
