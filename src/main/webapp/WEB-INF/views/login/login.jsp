<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
	<title>债转管理平台</title>
	<meta name="decorator" content="login"/>
	 <style type="text/css">
    	body{
    		width:100%;
    	}
   	 </style>
   	 <link rel="shortcut icon" type="image/x-icon" href="${ctx}/static/favicon.ico" media="screen" /> 
</head>
<body>
   <div class="login_wapper">
        <div class="login_title">
            <img src="${ctx}/static/tools/login/img/logintitle.png" />
        </div>
        <div class="content">
            <div class="user">
                <form onsubmit="return load();" method="post" id="loginForm" name="loginForm" >
                
	                <input type="text" class="username" value="admin"   name = "userName" placeholder="请输入您的登录账号">
	
	                <input type="password" class="password" value="123456" name="password" placeholder="请输入您的登录密码">
	                   <div class="login_height">
	                	 <p  class="warnning"></p>	                   
	                   </div>
	                <input type="submit" name="submit" class="login_btn" value="登 录" />
                </form>
            </div>
            <p class="login_footer">债转管理平台&nbsp;&nbsp;CopyRight2013&nbsp;&nbsp;技术支持</p>
        </div>
    </div>
    <script type="text/javascript">
		function load(){
			if($("input[name='userName']").val()==""||$("input[name='password']").val()==""){
        		$(".warnning").text("用户名或密码不可为空");
        		return false;
			}
 			$("input[name='submit']").addClass("btn_grey");
 			$("input[name='submit']").val("登 录 中");
			$("input[name='submit']").attr("disabled",true);
			$.ajax({
		        type: "post",
		        dataType: "json",
		        url: '${ctx}/login',
		        data: {"userName": $("input[name='userName']").val(),"password":$("input[name='password']").val()},
		        success: function (data) {
		 			$("input[name='submit']").removeClass("btn_grey");
		        	$("input[name='submit']").attr("disabled",false);
		 			$("input[name='submit']").val("登 录");
		        	if(data.status!='0'){
		        		$(".warnning").text(data.error);
		        		return false;
		        	}
		        	window.location.href = '${ctx}'+ data.data;
		        	return false;
	        	},
		        error: function(data) {
		 			$("input[name='submit']").removeClass("btn_grey");
		        	$("input[name='submit']").attr("disabled",false);
		 			$("input[name='submit']").val("登 录");
	        		$(".warnning").text(msg.error);
		        }
		    });
        	return false;
		}
	</script>
</body>
</html>
