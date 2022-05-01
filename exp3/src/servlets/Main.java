package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserBean;
import service.UserService;
//С���ɣ������������˻������г���ԭ���Ļ��棬���ò˵�project-clean
//ע�͵�һ�δ���ctrl+alt+/,�ָ�Ϊctrl+alt+\
/**
 * Servlet implementation class Main
 */
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		if("login".equals(action))
		{
			login(request, response);
		}
		if("addUser".equals(action))
		{
			addUser(request, response);
		}
		if("toDel".equals(action))
		{
			toDel(request, response);
		}
		if("delUser".equals(action))
		{
			delUser(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String account=request.getParameter("account");
		String password=request.getParameter("password");
		UserService userService=new UserService();

		if(userService.login(account, password))
		{
			UserBean user =new UserBean();
			user.setAccount(account);
			HttpSession session = request.getSession(); 
			session.setAttribute("user", user);
			List <UserBean> list=userService.browse();
			request.setAttribute("allUsers", list);
			String path="/success.jsp";
		    response.setContentType("text/html;charset=UTF-8");//��ֹ���벻һ�³����������룬�˾�ǰ������out���	
			RequestDispatcher requestDistpatcher=request.getRequestDispatcher(path);
			requestDistpatcher.forward(request, response);
		}
		else
		{
			String path="fail.jsp";
		    response.setContentType("text/html;charset=UTF-8");//��ֹ���벻һ�³����������룬�˾�ǰ������out���	
			RequestDispatcher requestDistpatcher=request.getRequestDispatcher(path);
			requestDistpatcher.forward(request, response);
		}
	}
	
	public void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		UserService userService=new UserService();
		//��ȡ������ת��Ϊ��Ӧ������
		String id=request.getParameter("id");
		int idInt=Integer.parseInt(id);
		String account=request.getParameter("account");
		String password=request.getParameter("password");
		String type=request.getParameter("type");
		int typeInt=Integer.parseInt(type);
		String name=request.getParameter("name");
		//�����û����󣬲����뵽���ݱ�users��
		UserBean user=new UserBean();
		user.setId(idInt);
		user.setAccount(account);
		user.setPassword(password);
		user.setType(typeInt);
		user.setName(name);
		if(userService.addUser(user))
		{
			List <UserBean> list=userService.browse();
			request.setAttribute("allUsers", list);
			String path="/success.jsp";
		    response.setContentType("text/html;charset=UTF-8");//��ֹ���벻һ�³����������룬�˾�ǰ������out���	
			RequestDispatcher requestDistpatcher=request.getRequestDispatcher(path);
			requestDistpatcher.forward(request, response);			
		}
		else
		{
			String path="fail.jsp";
		    response.setContentType("text/html;charset=UTF-8");//��ֹ���벻һ�³����������룬�˾�ǰ������out���	
			RequestDispatcher requestDistpatcher=request.getRequestDispatcher(path);
			requestDistpatcher.forward(request, response);
		}
	}
	
	public void delUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		UserService userService=new UserService();
		//��ȡ������ת��Ϊ��Ӧ������
		String id=request.getParameter("id");
		int idInt=Integer.parseInt(id);
		String account=request.getParameter("account");
		String password=request.getParameter("password");
		String type=request.getParameter("type");
		int typeInt=Integer.parseInt(type);
		String name=request.getParameter("name");
		//�����û����󣬲����뵽���ݱ�users��
		UserBean user=new UserBean();
		user.setId(idInt);
		user.setAccount(account);
		user.setPassword(password);
		user.setType(typeInt);
		user.setName(name);
		int delResult=userService.delUser(user);
		if(delResult==1)
		{
			List <UserBean> list=userService.browse();
			request.setAttribute("allUsers", list);
			String path="/success.jsp";
		    response.setContentType("text/html;charset=UTF-8");//��ֹ���벻һ�³����������룬�˾�ǰ������out���	
			RequestDispatcher requestDistpatcher=request.getRequestDispatcher(path);
			requestDistpatcher.forward(request, response);				
		}
		else if(delResult==2)
		{
			String path="fail.jsp";
		    response.setContentType("text/html;charset=UTF-8");//��ֹ���벻һ�³����������룬�˾�ǰ������out���	
			RequestDispatcher requestDistpatcher=request.getRequestDispatcher(path);
			requestDistpatcher.forward(request, response);
		}
		else
		{
			String path="cantdel.jsp";
		    response.setContentType("text/html;charset=UTF-8");//��ֹ���벻һ�³����������룬�˾�ǰ������out���	
			RequestDispatcher requestDistpatcher=request.getRequestDispatcher(path);
			requestDistpatcher.forward(request, response);			
		}
	}
	public void toDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String userid=request.getParameter("id");//ȡ�ô��ݹ������û����������ڲ�ѯ��ɾ��
		UserBean user=null;
		UserService userSer=new UserService();
		user=userSer.getRecord(Integer.parseInt(userid));
		request.setAttribute("user", user);
		String path="/delete.jsp";
	    response.setContentType("text/html;charset=UTF-8");//��ֹ���벻һ�³����������룬�˾�ǰ������out���	
		RequestDispatcher requestDistpatcher=request.getRequestDispatcher(path);
		requestDistpatcher.forward(request, response);					
	}
	
}
