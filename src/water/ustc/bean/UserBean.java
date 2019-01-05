package water.ustc.bean;

import com.sun.xml.internal.bind.v2.model.core.ID;

import jdk.nashorn.internal.ir.Flags;
import net.sf.cglib.proxy.Enhancer;
import water.ustc.dao.UserDAO;
import water.ustc.lazyLoad.LazyLoadProxy;

public class UserBean {
	
	private String userId;
	private String userName;
	//userPass需要懒加载
	private String userPass;
	
	
	public UserBean() {
		
	}
	
	public UserBean(String userName) {
		this.userName=userName;
		//this.userPass=userPassProxy();
		
	}
	
	
	
	
	//代理机制实现懒加载
	@SuppressWarnings("static-access")
	public Object getUserPassLazy() {
		Enhancer enhancer=new Enhancer();
		enhancer.setSuperclass(Object.class);
		return enhancer.create(Object.class,new LazyLoadProxy(this));
	}
	
	
	
	
	
	
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
	
	/**
	 * e6
	 * @param userInputPass  用户输入密码
	 * @return
	 */
	public boolean signIn(String userInputPass) {
		UserDAO userDAO=new UserDAO();
		Boolean isValid=(Boolean)userDAO.query(this);
		//用户id有效
		if(isValid) {
			//比较密码
			Object pass=getUserPassLazy();//从数据库中懒加载加载该用户密码
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
