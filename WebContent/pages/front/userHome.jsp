<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<title>User Home</title>
</head>
<body>
	<div align="center">
		<h2>User Home</h2>
		<div align="right">
			<a href="<%=basePath%>/user/logout.shtml">退出登录</a>
		</div>
	Hello <b>${user.getUserName()}</b>,welcome to user home page! 
	</div>
</body>
</html>