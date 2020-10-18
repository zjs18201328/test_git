<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="beans.*,service.*" %><!-- 数据库及javabean操作需要 -->     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="Main" method="get">
		将要删除的用户信息是：<br/>
		编号<input type="text" name="id" value=${user.id} readonly/><br/>
		帐号<input type="text" name="account" value=${user.account} readonly/><br/>
		密码<input type="text" name="password" value=${user.password} readonly/><br/>
		类型<input type="text" name="type" value=${user.type} readonly/><br/>
		姓名<input type="text" name="name" value=${user.name} readonly/><br/>
		<input type="hidden" name="action" value="delUser"/>
		<input type="submit" value="确定"/>
	</form>
</body>
</html>