package water.ustc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sc.ustc.dao.BaseDAO;
import sc.ustc.dao.Conversation;
import water.ustc.bean.UserBean;

public class UserDAO extends BaseDAO {
	
	private Statement stmt;//sql���ִ�ж���
	private String J2EEDB;//���ݿ�����
	
	public UserDAO() {
		//���ݿ�������Mysql
		driver="com.mysql.jdbc.Driver";
		//���ݿ����·��
		url="jdbc:mysql://localhost:3306";
		//���ݿ��û���
		userName="root";
		//���ݿ��û�����
		userPassword="admin";
		//���ݿ�����
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
	 * �ҵ��û��������û����󷵻أ��Ҳ�������null
	 */
	@Override
	public Object query(String sql) {
		// TODO Auto-generated method stub
		Connection connection=openDBConnection();//�������ݿ�
		ResultSet rSet=null;
		UserBean userBean=new UserBean();
		try {
			//sql���ִ�ж���
			stmt=connection.createStatement();
			stmt.execute(J2EEDB);
			rSet=stmt.executeQuery(sql);
			if(rSet.next()) {//�ҵ�ʱ�����û����󷵻�
				String userId=String.valueOf(rSet.getInt("userId"));
				String userName=rSet.getString("userName");
				String userPassword=rSet.getString("userPassword");
				userBean.setUserId(userId);
				userBean.setUserName(userName);
				userBean.setUserPass(userPassword);
			}
			stmt.close();
			closeDBConnection();//�ر����ݿ�����
			return userBean;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	//e6 �����û���������ݿ��в��ң��ҵ����û����򷵻�true,���򷵻�false
	public Object query(UserBean userBean) {
		System.out.println("UserBean������û��������룺 "+userBean.getUserName()+"  "+userBean.getUserPass());
		ResultSet rSet=(ResultSet)Conversation.selectObject(userBean);
		try {
			if(rSet!=null&&rSet.next()) {
				System.out.println("�ҵ��˸��û�������");
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
