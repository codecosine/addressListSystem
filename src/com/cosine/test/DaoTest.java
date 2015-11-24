package com.cosine.test;

import org.junit.Test;

import com.cosine.domain.Group;
import com.cosine.domain.User;

public class DaoTest {
	@Test
	public void groupTest(){
		User user = new User("cosine","123","teacher");
		Group<User> group = new Group<User>();
		group.add(user);
		
		System.out.println(group.toJsonString());
	}
}
