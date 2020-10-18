<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="javabean.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	//本来该从数据库中读出用户的账户信息，因此处还未讲到数据库的读写，用application.setAttribute代替。
	if(application.getAttribute("jack")==null){
		Account jack=new Account("jack","123",200);
		application.setAttribute("jack", jack);
	}
	if(application.getAttribute("rose")==null){
		Account rose=new Account("rose","456",300);
		application.setAttribute("rose", rose);
	}		
%>

<form action="verify.jsp">
	用户：<input type="text" name="name"><br>
	密码：<input type="text" name="password"><br>
	<input type="reset" value="重置"><input type="submit" value="提交">
</form>
</body>
</html>