package com.cosine.test;

import org.junit.Test;

import com.cosine.domain.User;
import com.cosine.services.UserServices;
import com.cosine.utils.ParseMD5;

public class UserServiceTest {
	@Test
	public void add(){
		
		UserServices us = UserServices.getInstance();
		User power = new User("cosine","cosine");
		
		//User adduser = new User("3114004923",ParseMD5.parseStrToMd5L16("123456"),"student");
		User adduser = new User("xiaoxian",ParseMD5.parseStrToMd5L16("123456"),"student");

		us.addUser(power, adduser);
		us.commit();
	}
	@Test
	public void login(){
		
		UserServices us = UserServices.getInstance();
		User login = new User("xiaoxian","22");
		String role = us.login(login);
		System.out.println(role);
	
	}
}
