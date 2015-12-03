package com.cosine.dao;

import java.util.Iterator;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.cosine.domain.Group;
import com.cosine.domain.User;
import com.cosine.utils.FileUtils;

public class UserDao {
	/**
	 * 增删读写
	 */
	Group<User> group = null;
	String path = "";
	boolean isCommit = true;
	public UserDao(String path){
		 this.path = path;
		 group = JSON.parseObject(FileUtils.readJson(path),new TypeReference<Group<User>>(){});
		 
	}
	public boolean add(User power,User user){
		isCommit = false;
		if(isAdmin(power)){
			return group.add(user);

		}
		return false;
	}
	public boolean remove(User power,User user){
		isCommit = false;
		if(isAdmin(power)){
			return group.remove(user);

		}
		return false;
	}
	public String check(User user){
		if(group.contains(user)){
			Iterator<User> it = group.iterator();
			User edituser = it.next();
			while(it.hasNext() && !edituser.equals(user)){
				edituser = it.next();
			}
			System.out.println(edituser.toJsonString());
			return edituser.getRole();
		}
		return "guest";
	}
	public boolean isAdmin(User user){
		
		return user.getRole().equals("admin");
	}
	public boolean editPassWord(User oldpass,User newpass){	
		isCommit = false;

		if(group.contains(oldpass)){
			Iterator<User> it = group.iterator();
			User edituser = it.next();
			while(it.hasNext() && edituser!=oldpass){
				edituser = it.next();
			}
			edituser.setPassword(newpass.getPassword());
			group.remove(oldpass);
			group.add(edituser);
			return true;
		}
		return false;

	}
	public boolean editRole(User power,User newuser){
		isCommit = false;
		if(isAdmin(power)){
			Iterator<User> it = group.iterator();
			User edituser = it.next();
			while(it.hasNext() && !edituser.getUsername().equals(newuser.getUsername())){
				edituser = it.next();
			}
			edituser.setRole(newuser.getRole());
			group.remove(edituser);
			group.add(edituser);
			return true;
		}
		return false;

	}
	public boolean commit(){
		if(!isCommit){
			FileUtils.writeJson(path, group.toJsonString());
		}
		isCommit = true;
		return true;
		
	}
	public boolean rollback(){
		group = null;
		group = JSON.parseObject(FileUtils.readJson(path),new TypeReference<Group<User>>(){});
		return true;
	}
	
}
