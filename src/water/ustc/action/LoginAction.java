package water.ustc.action;

import water.ustc.bean.UserBean;

/**
 * POJO��
 * POJO��һ��Լ�����������ݿ���ʵ���࣬һ��һ��POJO���Ӧһ�ű�POJOҲ��java����
 * @author asus
 *
 */
public class LoginAction {
	
	String userName;//�û���
	String password;//����
	
	/**
	 * �����½�߼�
	 * @param userName �û�������û���
	 * @param userPassword �û����������
	 * @return
	 */
	public String handleLogin(String userName,String userPassword) {
		String result="failure";
		UserBean userBean=new UserBean();
		userBean.setUserName(userName);
		userBean.setUserPass(userPassword);
		//result=userBean.signIn();
		System.out.println("UseSC��HandleLogin�в���Ϊ��  "+userName+"  "+userPassword);
		boolean signInResult=userBean.signIn();
		if(signInResult) {
			result="success";
		}
		return result;
	}
	
	
	
	//������֤��½
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
