<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="javabean.*" %>
    <%@page import="java.util.ArrayList" %>
    <%@page import="java.util.List" %>
    <%@include file="showcart.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% //生成六本收的信息，放入list（正常情况下应该从数据库中读取）
	List<Book> bookList=new ArrayList<Book>(); 
	Book book1=new Book(1,"java编程思想",78,"商品名称：Java编程思想（第4版）开本:128开 作者：[美] Bruce Eckel",".\\img\\1.jpg");
	bookList.add(book1);
	Book book2=new Book(2,"疯狂Java讲义",69,"商品名称：疯狂Java讲义（第5版）（含DVD光盘一张）作者：李刚",".\\img\\2.jpg");
	bookList.add(book2);
	Book book3=new Book(3,"深入分析Java Web技术内幕",74,"商品名称：深入分析Java Web技术内幕（修订版） 作者：许令波",".\\img\\3.jpg");
	bookList.add(book3);
	Book book4=new Book(4,"码出高效：java开发手册",84,"码出高效-Java开发手册 作者：杨冠宝",".\\img\\4.jpg");
	bookList.add(book4);
	Book book5=new Book(5,"Effective Java中文版",119,"商品名称：Effective Java中文版(原书第3版) 作者：[美]约书亚·布洛克",".\\img\\5.jpg");
	bookList.add(book5);
	Book book6=new Book(6,"Java并发编程实战",41,"商品名称：java并发编程实战(平装) 作者：[美]盖茨",".\\img\\6.jpg");
	bookList.add(book6);

	%>
	<table border="1px">
		<%
			int k=0;
			for(int i=0;i<2;i++){
			%>
			<tr>
				<%for(int j=0;j<3;j++){ %>
				<td><!--除form表单外，还可以直接在网址上写参数  -->
					<a href="book.jsp?bookid=<%=bookList.get(k).getBookID() %>" target="_blank">
						<img src=<%=bookList.get(k).getImgPath() %> ><br><%=bookList.get(k).getBookName() %>
					</a>
				</td>
				<%k++;} %>
			</tr>
		<%}
			application.setAttribute("booklist", bookList);	
		%>
	</table>
</body>
</html>