package water.ustc.bean;

import com.sun.xml.internal.bind.v2.model.core.ID;

import water.ustc.dao.UserDAO;

public class UserBean {
	
	private String userId;
	private String userName;
	private String userPass;
	
	
	
	//�����½�߼�,����UserDAO�����ݿ��л�ȡ���û����󣬱Ƚϻ�ȡ���û������������������Ƿ�һ�£�һ�·���true,��һ�·���false
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
			if(password.equals(userPass)) {//��֤������ͬ
				System.out.println("�û���������ͬ����½�ɹ�����");
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
