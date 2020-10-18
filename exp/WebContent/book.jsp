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
<!-- 本网页的加载有两个来源：success.jsp和本网页，在本网页中form表单中使用了一个hidden属性的input来标识 -->
<jsp:include page="showcart.jsp"></jsp:include>
<div align="center">
	<%
		Book book=null;
		List<Book> bookList=(ArrayList<Book>)(application.getAttribute("booklist"));//书籍列表
		
		int buyNumber=0;//购买数量	
		int bookID=Integer.parseInt(request.getParameter("bookid"));
		if("self".equals(request.getParameter("from"))){//来源是本网页
			buyNumber=(Integer.parseInt(request.getParameter("buynumber")));
			//System.out.println(bookID+"   "+buyNumber);	
			if(session.getAttribute("book"+bookID)!=null){//如果原来已有相同的书籍，此次增加数量
				int formerNumber=(Integer)(session.getAttribute("book"+bookID));//不要用数字做属性名
				int totalNumber=buyNumber+formerNumber;
				session.setAttribute("book"+bookID, totalNumber);
				//System.out.println("book"+bookID+"former:"+formerNumber+" add  "+buyNumber+" total:"+totalNumber);
			}else{
				session.setAttribute("book"+bookID, buyNumber);
			}			
		}
        //从书籍列表中找出书籍编号对应的书
		for(int i=0;i<bookList.size();i++){
			if(bookID==bookList.get(i).getBookID()){
				book=bookList.get(i);
				break;
			}
		}
		//System.out.println(book.getBookID());
	%>
	<form action="">
		<img src=<%=book.getImgPath() %>><br>
		<input type="hidden" name="bookid" value="<%=book.getBookID() %>">
		<input type="hidden" name="from" value="self">
		数量<input type="text" name="buynumber" value="<%=buyNumber %>">
		<input type="reset" value="重置"><input type="submit" value="加入购物车">
	</form>
	<div>
	<%=book.getIntroduction() %>
	</div>
</div>	
</body>
</html>