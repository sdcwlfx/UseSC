package water.ustc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sc.ustc.dao.BaseDAO;
import sc.ustc.dao.Conversation;
import water.ustc.bean.UserBean;

public class UserDAO extends BaseDAO {
	
	private Statement stmt;//sql语句执行对象
	private String J2EEDB;//数据库名字
	
	public UserDAO() {
		//数据库驱动类Mysql
		driver="com.mysql.jdbc.Driver";
		//数据库访问路径
		url="jdbc:mysql://localhost:3306";
		//数据库用户名
		userName="root";
		//数据库用户密码
		userPassword="admin";
		//数据库名字
		J2EEDB="use j2eedb";
		
	}

	@Override
	public boolean delete(String sql) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(String sql) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 找到用户名，将用户对象返回，找不到返回null
	 */
	@Override
	public Object query(String sql) {
		// TODO Auto-generated method stub
		Connection connection=openDBConnection();//链接数据库
		ResultSet rSet=null;
		UserBean userBean=new UserBean();
		try {
			//sql语句执行对象
			stmt=connection.createStatement();
			stmt.execute(J2EEDB);
			rSet=stmt.executeQuery(sql);
			if(rSet.next()) {//找到时，将用户对象返回
				String userId=String.valueOf(rSet.getInt("userId"));
				String userName=rSet.getString("userName");
				String userPassword=rSet.getString("userPassword");
				userBean.setUserId(userId);
				userBean.setUserName(userName);
				userBean.setUserPass(userPassword);
			}
			stmt.close();
			closeDBConnection();//关闭数据库链接
			return userBean;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	//e6 依据用户对象从数据库中查找，找到该用户，则返回true,否则返回false
	public Object query(UserBean userBean) {
		System.out.println("UserBean对象的用户名和密码： "+userBean.getUserName()+"  "+userBean.getUserPass());
		ResultSet rSet=(ResultSet)Conversation.selectObject(userBean);
		try {
			if(rSet!=null&&rSet.next()) {
				System.out.println("找到了该用户！！！");
//				String userId=rSet.getString(0);
//				String userName=rSet.getString(1);
				return true;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	
	
	
	
	

	@Override
	public boolean update(String sql) {
		// TODO Auto-generated method stub
		return false;
	}

}
