<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="beans.*" %>
<%@page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<marquee>
	登录成功，欢迎${user.account}
</marquee>
<br/>
**学院学生信息管理
<%
	List <UserBean> list=(List<UserBean>)request.getAttribute("allUsers");
%>
<!-- 以下HTML代码段用于显示查询出来的数据 -->
<table border="1">
	<tr>
	<td>编号</td><td>帐号</td><td>密码</td><td>类型</td><td>姓名</td>
	<td>修改</td><td>删除</td>
	<tr/>
	<%			
		if(list!=null){
			for(int i=0;i<list.size();i++){ 
	%>	
	<tr>
	<td><%=list.get(i).getId()%></td><td><%=list.get(i).getAccount() %></td>
	<td><%=list.get(i).getPassword() %></td><td><%=list.get(i).getType() %></td>
	<td><%=list.get(i).getName() %></td>
	
	<%String editURL="edit.jsp?action=1&id="+list.get(i).getId(); %>
	<td><a href=<%=editURL %>>修改</a></td>
	<%String deleteURL="Main?action=toDel&id="+list.get(i).getId(); %>	
	<td><a href=<%=deleteURL %>>删除</a></td>
	</tr>
<%  }} //对应以上的for语句%>
</table>
<a href="add.jsp">新增用户</a>

</body>
</html>