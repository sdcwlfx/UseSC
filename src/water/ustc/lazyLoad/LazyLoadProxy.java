package water.ustc.lazyLoad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import net.sf.cglib.proxy.LazyLoader;
import sc.ustc.dao.Configuration;
import water.ustc.bean.UserBean;

//���ݳ�ʼ��userId�Ķ����������û�����
public class LazyLoadProxy implements LazyLoader {
	
	private UserBean userBean;
	
	public LazyLoadProxy(UserBean userBean) {
		this.userBean=userBean;
	}

	@Override
	public Object loadObject() throws Exception {
		
		//��ȡJDBC����
		Map<String, String> jdbcMap=Configuration.getJDBCConfig();
		//��ȡ���ݿ�����
		Class.forName(jdbcMap.get("driver_class"));
		//���ݿ��û���������
		Connection connection=DriverManager.getConnection(jdbcMap.get("url_path"), jdbcMap.get("db_username"), jdbcMap.get("db_userpassword"));
		
		//�����ѯ���,���Զ����userName
		String sqlQuery="select * from user where userName=?";
		PreparedStatement pStatement=connection.prepareStatement(sqlQuery);
		pStatement.setString(1, userBean.getUserName());
		
		ResultSet resultSet=pStatement.executeQuery();
		
		while(resultSet.next()) {
			//��ȡ����,������  ���ݿ��д�1��ʼ
			System.out.println("�û�����Ϊ��"+resultSet.getString(3));
			return resultSet.getString(3);
		}
		return null;
	}

}
