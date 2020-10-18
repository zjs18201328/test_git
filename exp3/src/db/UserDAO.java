package db;
import java.sql.*;
import java.util.*;
import beans.*;

public class UserDAO extends ConnBean{
	private Connection connection=null;
	public List<UserBean> getAllRecords(){
		ResultSet rs=null;
		Statement stmt=null;
		ArrayList <UserBean> list=new ArrayList<UserBean>();
		try {
			//获取数据库连接(url,username,password)			
			connection = getConnection();
			if(connection==null)
				System.out.println("error");
			//准备执行数据库查询
			String sql="select * from users";
			//准备SQL执行;
			stmt =connection.createStatement();
			//执行数据库查询，获取结果集
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				UserBean user=new UserBean();
				user.setId(rs.getInt(1));
				user.setAccount(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setType(rs.getInt(4));
				user.setName(rs.getString(5));
				list.add(user);
			}
			//关闭表达式对象
			stmt.close();			
			//关闭数据库			
			connection.close();			
			
	}catch (SQLException e) {
		e.printStackTrace();
	} finally{
		try{
			if (connection!=null && (!connection.isClosed())){
				connection.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
		return list;
	}
	
	public boolean valid(String account,String password)//新增，用于到数据库中验证通过输入的用户名和密码是否能找到相应的记录
	{
		ResultSet rs=null;
		Statement stmt=null;
		boolean result=false;
		try {
			//获取数据库连接(url,username,password)			
			connection = getConnection();
			if(connection==null)
				System.out.println("error");
			//准备执行数据库查询
			String sql="select * from users where account='"+account+"' and password='"+password+"'";
			//准备SQL执行;
			stmt =connection.createStatement();
			//执行数据库查询，获取结果集
			rs=stmt.executeQuery(sql);
			if(!rs.next())//结果集为空则没有找到
				result=false;
			else
				result=true;
		    //关闭表达式对象
			stmt.close();			
			//关闭数据库			
			connection.close();	
			//return user;
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
		try{
			if (connection!=null && (!connection.isClosed())){
				connection.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
		return result;		
  }
	public UserBean getRecord(int userid){
		ResultSet rs=null;
		Statement stmt=null;
		UserBean user=null;
		try {
			//获取数据库连接(url,username,password)			
			connection = getConnection();
			if(connection==null)
				System.out.println("error");
			//准备执行数据库查询
			String sql="select * from users where id='"+userid+"'";
			//准备SQL执行;
			stmt =connection.createStatement();
			//执行数据库查询，获取结果集
			rs=stmt.executeQuery(sql);
			if(rs!=null && rs.next()){//执行完sql语句后，rs指向第一条结果的前面，所以必须执行一次next()操作
				System.out.println("right");
				user=new UserBean();
				user.setId(rs.getInt(1));
				user.setAccount(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setType(rs.getInt(4));
				user.setName(rs.getString(5));
			}
			else
			{	
				System.out.println("null");
				user=null;
			}
		    //关闭表达式对象
			stmt.close();			
			//关闭数据库			
			connection.close();	
			//return user;
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
		try{
			if (connection!=null && (!connection.isClosed())){
				connection.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
		return user;
  }	
	
	public boolean insertRecord(UserBean record)
	{
		PreparedStatement pstmt=null;
		String sql="insert into users values(?,?,?,?,?)";
		if(record==null) 
			return false;
		boolean result=false;
		int number=0;
		try{
			connection=getConnection();
/*			System.out.println(record.getId());
			System.out.println( record.getAccount());
			System.out.println(record.getPassword());
			System.out.println(record.getType());
			System.out.println(record.getName());	*/		

			pstmt=connection.prepareStatement(sql);
			pstmt.setInt(1, record.getId());
			pstmt.setString(2, record.getAccount());
			pstmt.setString(3, record.getPassword());
			pstmt.setInt(4, record.getType());
			pstmt.setString(5, record.getName());
			number=pstmt.executeUpdate();
			//关闭表达式对象
			pstmt.close();			
			//关闭数据库			
			connection.close();	
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try{
				if (connection!=null && (!connection.isClosed())){
					connection.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		//System.out.println("***"+number);
		if(number>0)
			result=true;
		return result;
	}

	public boolean deleteRecord(UserBean record)
	{
		PreparedStatement pstmt=null;
		String sql="delete from users where id=?";
		
		if(record==null) 
			return false;
		int result=0;
		try{
			connection=getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setInt(1, record.getId());
			result=pstmt.executeUpdate();
			
			System.out.println(record.getId());
			//关闭表达式对象
			pstmt.close();			
			//关闭数据库			
			connection.close();	
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try{
				if (connection!=null && (!connection.isClosed())){
					connection.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(result>0)
			return true;
		else
			return false;
	}
	
	public boolean updateRecord(UserBean beforeRecord,UserBean afterRecord)
	{
		if(beforeRecord==null || afterRecord==null) 
			return false;
		PreparedStatement pstmt=null;
		String sql="update users set account=?,password=?,type=?,name=?"+"where id="+beforeRecord.getId();
		int result=0;
		try{
			connection=getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setInt(1, afterRecord.getId());
			pstmt.setString(2, afterRecord.getAccount());
			pstmt.setString(3, afterRecord.getPassword());
			pstmt.setInt(4, afterRecord.getType());
			pstmt.setString(5, afterRecord.getName());
			result=pstmt.executeUpdate();
			//关闭表达式对象
			pstmt.close();			
			//关闭数据库			
			connection.close();	
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try{
				if (connection!=null && (!connection.isClosed())){
					connection.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(result>0)
			return true;
		else
			return false;
		
	}
	
	
}
