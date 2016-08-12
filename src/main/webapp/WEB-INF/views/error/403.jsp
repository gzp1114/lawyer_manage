<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory" %>
<%	
	//设置返回码200，避免浏览器自带的错误页面
	response.setStatus(200);
	//记录日志
	Logger logger = LoggerFactory.getLogger("403.jsp");
	logger.error(exception.getMessage(), exception);
	exception.printStackTrace();
%> --%>

<!DOCTYPE html>
<html>
<head>
	<title>403 - 无权限访问</title>
</head>
<body>
	<span>403 - 没有操作权限</span>
</body>
</html>
