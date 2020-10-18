<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="javabean.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%	//用户名和密码比对
		String userName=request.getParameter("name");
		String userPassword=request.getParameter("password");
		//使用getAttribute获得的都是Object对象，要转换
		Account loginUser=(Account)(application.getAttribute(userName));
		if(loginUser!=null && loginUser.getPassword().equals(userPassword)){
	%>
		<jsp:forward page="success.jsp"></jsp:forward>		
		<%
		}else{
		%>
			<jsp:forward page="fail.jsp"></jsp:forward>
		<%}
	%>
</body>
</html>