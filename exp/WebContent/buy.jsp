<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="javabean.*" %>
    <%@page import="java.util.ArrayList" %>
    <%@page import="java.util.List" %>
    <%@page import="java.util.Map" %>
    <%@page import="java.util.HashMap" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1px">
<%
	Account account=(Account)(application.getAttribute("loginUser"));
	Book book=null;
	List<Book> bookList=(ArrayList<Book>)(application.getAttribute("booklist"));//书籍列表
	int sum=0;
	int buyNumber=0;
	for(int i=0;i<6;i++){
		String bookIDString="book"+(i+1);//用于从session中取出购买书籍的数量
		if(session.getAttribute(bookIDString)==null){//购物车中没有
			System.out.println("no book");
			buyNumber=0;
		}else{
			buyNumber=(Integer)(session.getAttribute(bookIDString));
			System.out.println(buyNumber);
		}
		for(int j=0;j<bookList.size();j++){
			if((i+1)==bookList.get(j).getBookID()){
				book=bookList.get(j);
				break;
			}
		}
		if(buyNumber>0){
		%>
		<tr>
			<td><%=book.getBookName() %></td>
			<td><%=book.getBookPrice() %></td>
			<td><%=buyNumber %></td>
		</tr>
		<%sum=sum+book.getBookPrice()*buyNumber; %>
	<%}
	}
%>
<tr>
	<td colspan="2">总支付金额：<%=sum %></td>
	<%int s=account.getMoney()-sum;
	if(s>0){%>
		<td>余额不足</td>	
	<%}else{		
		%>
	<td>剩余金额：<%=s %></td>
	<%} %>
</tr>
</table>
</body>
</html>