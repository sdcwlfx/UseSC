package water.ustc.action;

/**
 * POJO类
 * POJO是一种约定的描述数据库表的实体类，一般一个POJO会对应一张表，POJO也是java对象
 * @author asus
 *
 */
public class LoginAction {
	
	String userName;//用户名
	String password;//密码
	
	//处理验证登陆
	public String handleLogin() {
		
		return "success";
	}
	
//	public String getUserName() {
//		return userName;
//	}
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
	
	

}
