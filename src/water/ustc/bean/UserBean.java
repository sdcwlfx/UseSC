package water.ustc.bean;

import com.sun.xml.internal.bind.v2.model.core.ID;

import water.ustc.dao.UserDAO;

public class UserBean {
	
	private String userId;
	private String userName;
	private String userPass;
	
	
	
	//处理登陆逻辑,调用UserDAO从数据库中获取该用户对象，比较获取的用户的密码和输入的密码是否一致，一致返回true,不一致返回false
	public boolean signIn() {
		//boolean success=false;	
		String querySQL="select * from User where userName='"+userName+"'";
		UserDAO userDAO=new UserDAO();
		Object queryResult=userDAO.query(querySQL);
		if(queryResult==null) {
			return false;
		}else {
			UserBean resultBean=(UserBean)queryResult;
			String password=resultBean.getUserPass();
			if(password.equals(userPass)) {//验证密码相同
				System.out.println("用户名密码相同，登陆成功！！");
				return true;
			}else {
				return false;
				
			}
		}
		//return success;
	}



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getUserPass() {
		return userPass;
	}



	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	
	

}
