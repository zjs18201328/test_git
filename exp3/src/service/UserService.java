package service;

import java.util.*;

import beans.*;
import db.*;

public class UserService {
	public boolean login(String account,String password)
	{
		UserDAO userDao=new UserDAO();
		return userDao.valid(account, password);
	}
	
	public List<UserBean> browse(){
		UserDAO dao =new UserDAO();
		return dao.getAllRecords();
	}
	
	public boolean addUser(UserBean user)
	{
		UserDAO userDao=new UserDAO();
		return userDao.insertRecord(user);
	}
	
	public UserBean getRecord(int userId)
	{
		UserDAO userDao=new UserDAO();
		return userDao.getRecord(userId);
	}
	
	public int delUser(UserBean user)
	{
		UserDAO userDao=new UserDAO();
		if(user.getType()==1)
		{
			System.out.println("teacher can't be deleted");
			return 0;
		}
		else
		{
			if(userDao.deleteRecord(user))
				return 1;
			else
				return 2;
		}
	}
}
