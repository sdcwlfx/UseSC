package water.ustc.lazyLoad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import net.sf.cglib.proxy.LazyLoader;
import sc.ustc.dao.Configuration;
import water.ustc.bean.UserBean;

//依据初始化userId的对象懒加载用户密码
public class LazyLoadProxy implements LazyLoader {
	
	private UserBean userBean;
	
	public LazyLoadProxy(UserBean userBean) {
		this.userBean=userBean;
	}

	@Override
	public Object loadObject() throws Exception {
		
		//获取JDBC配置
		Map<String, String> jdbcMap=Configuration.getJDBCConfig();
		//获取数据库驱动
		Class.forName(jdbcMap.get("driver_class"));
		//数据库用户名和密码
		Connection connection=DriverManager.getConnection(jdbcMap.get("url_path"), jdbcMap.get("db_username"), jdbcMap.get("db_userpassword"));
		
		//定义查询语句,语句对对象的userName
		String sqlQuery="select * from user where userName=?";
		PreparedStatement pStatement=connection.prepareStatement(sqlQuery);
		pStatement.setString(1, userBean.getUserName());
		
		ResultSet resultSet=pStatement.executeQuery();
		
		while(resultSet.next()) {
			//获取密码,并返回  数据库列从1开始
			System.out.println("用户密码为："+resultSet.getString(3));
			return resultSet.getString(3);
		}
		return null;
	}

}
