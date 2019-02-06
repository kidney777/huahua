<%@page import="java.security.SecureRandom"%>
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
<title>User Login</title>

</head>
<body>
	<div align="center">
		<h2>User Login</h2>
		<form action="<%=basePath%>/user/login.shtml" method="post">
			<table>
				<tr>
					<td>用户名：</td>
					<td><input type="text" name="username" /></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td><input name="remember-me" type="checkbox">7天内自动登录</input></td>
				</tr>
				<tr>
					<td align="center"><input type="submit" value="登录" /> <input type="reset" value="重置" /></td>
				</tr>			
			</table>
		</form>
		
	</div>
</body>
</html>