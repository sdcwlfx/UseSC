package water.ustc.bean;

import com.sun.xml.internal.bind.v2.model.core.ID;

import jdk.nashorn.internal.ir.Flags;
import net.sf.cglib.proxy.Enhancer;
import water.ustc.dao.UserDAO;
import water.ustc.lazyLoad.LazyLoadProxy;

public class UserBean {
	
	private String userId;
	private String userName;
	//userPass��Ҫ������
	private String userPass;
	
	
	public UserBean() {
		
	}
	
	public UserBean(String userName) {
		this.userName=userName;
		//this.userPass=userPassProxy();
		
	}
	
	
	
	
	//�������ʵ��������
	@SuppressWarnings("static-access")
	public Object getUserPassLazy() {
		Enhancer enhancer=new Enhancer();
		enhancer.setSuperclass(Object.class);
		return enhancer.create(Object.class,new LazyLoadProxy(this));
	}
	
	
	
	
	
	
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
	
	/**
	 * e6
	 * @param userInputPass  �û���������
	 * @return
	 */
	public boolean signIn(String userInputPass) {
		UserDAO userDAO=new UserDAO();
		Boolean isValid=(Boolean)userDAO.query(this);
		//�û�id��Ч
		if(isValid) {
			//�Ƚ�����
			Object pass=getUserPassLazy();//�����ݿ��������ؼ��ظ��û�����
			if(userInputPass.equals(pass.toString())) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
		
		
		
		
		
		
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
