package water.ustc.action;

import water.ustc.bean.UserBean;

/**
 * POJO类
 * POJO是一种约定的描述数据库表的实体类，一般一个POJO会对应一张表，POJO也是java对象
 * @author asus
 *
 */
public class LoginAction {
	
	String userName;//用户名
	String password;//密码
	
	/**
	 * 处理登陆逻辑
	 * @param userName 用户输入的用户名
	 * @param userPassword 用户输入的密码
	 * @return
	 */
	public String handleLogin(String userName,String userPassword) {
		String result="failure";
		UserBean userBean=new UserBean();
		userBean.setUserName(userName);
		userBean.setUserPass(userPassword);
		//result=userBean.signIn();
		System.out.println("UseSC中HandleLogin中参数为：  "+userName+"  "+userPassword);
		boolean signInResult=userBean.signIn();
		if(signInResult) {
			result="success";
		}
		return result;
	}
	
	
	
	//处理验证登陆
//	public String handleLogin() {
//		
//		return "success";
//	}
	
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
