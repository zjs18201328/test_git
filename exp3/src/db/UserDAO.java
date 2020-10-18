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
			//��ȡ���ݿ�����(url,username,password)			
			connection = getConnection();
			if(connection==null)
				System.out.println("error");
			//׼��ִ�����ݿ��ѯ
			String sql="select * from users";
			//׼��SQLִ��;
			stmt =connection.createStatement();
			//ִ�����ݿ��ѯ����ȡ�����
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
			//�رձ��ʽ����
			stmt.close();			
			//�ر����ݿ�			
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
	
	public boolean valid(String account,String password)//���������ڵ����ݿ�����֤ͨ��������û����������Ƿ����ҵ���Ӧ�ļ�¼
	{
		ResultSet rs=null;
		Statement stmt=null;
		boolean result=false;
		try {
			//��ȡ���ݿ�����(url,username,password)			
			connection = getConnection();
			if(connection==null)
				System.out.println("error");
			//׼��ִ�����ݿ��ѯ
			String sql="select * from users where account='"+account+"' and password='"+password+"'";
			//׼��SQLִ��;
			stmt =connection.createStatement();
			//ִ�����ݿ��ѯ����ȡ�����
			rs=stmt.executeQuery(sql);
			if(!rs.next())//�����Ϊ����û���ҵ�
				result=false;
			else
				result=true;
		    //�رձ��ʽ����
			stmt.close();			
			//�ر����ݿ�			
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
			//��ȡ���ݿ�����(url,username,password)			
			connection = getConnection();
			if(connection==null)
				System.out.println("error");
			//׼��ִ�����ݿ��ѯ
			String sql="select * from users where id='"+userid+"'";
			//׼��SQLִ��;
			stmt =connection.createStatement();
			//ִ�����ݿ��ѯ����ȡ�����
			rs=stmt.executeQuery(sql);
			if(rs!=null && rs.next()){//ִ����sql����rsָ���һ�������ǰ�棬���Ա���ִ��һ��next()����
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
		    //�رձ��ʽ����
			stmt.close();			
			//�ر����ݿ�			
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
			//�رձ��ʽ����
			pstmt.close();			
			//�ر����ݿ�			
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
			//�رձ��ʽ����
			pstmt.close();			
			//�ر����ݿ�			
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
			//�رձ��ʽ����
			pstmt.close();			
			//�ر����ݿ�			
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
