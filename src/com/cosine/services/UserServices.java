package com.cosine.services;
import org.junit.Test;

import com.cosine.dao.UserDao;
import com.cosine.domain.User;
/**
 * 一定要搞清楚服务的使用场景
 * 用户服务
 * 应该只暴露一个修改密码给所有者
 * 其他都是超级管理员的操作服务
 * @author codecosine
 *
 */
public class UserServices {
	UserDao userdao = null;
	private static UserServices us= null; 
	private UserServices(){
		init();
	}
	public static UserServices getInstance(){
		if(us==null){
			us = new UserServices();
		}
		return us;
	}
	@Test
	public void init(){
		String path = UserServices.class.getResource("/").getFile();
		path = path.substring(0,path.indexOf("WEB-INF")+"WEB-INF".length())+"/data"+"/user.json";
		System.out.println(path);
		userdao = new UserDao(path);
	}
	public String login(User loginuser){
		return userdao.check(loginuser);
	}
	public boolean addUser(User power,User adduser){
			return userdao.add(power,adduser);

	
	}
	public boolean removeUser(User power,User removeuser){
			return userdao.remove(power,removeuser);

	}
	public boolean editRole(User power,User edituser){
			
			return userdao.editRole(power, edituser);
	}

	public boolean editPassword(String username,String oldpwd,String newpwd){
		User old = new User();
		old.setPassword(oldpwd);
		old.setUsername(username);
		User now = new User();
		now.setPassword(newpwd);
		now.setUsername(username);
		
		return userdao.editPassWord(old, now);
	}
	public boolean commit(){
		return userdao.commit();
	}
	public boolean rollback(){
		return userdao.rollback();
	}
	
	
}
