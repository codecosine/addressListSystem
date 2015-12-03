package com.cosine.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.cosine.domain.Group;
import com.cosine.domain.Student;
import com.cosine.utils.FileUtils;

public class StudentDao {
	/**
	 * 增删读写
	 */
	Group<Student> group = null;
	String path = "";
	boolean isCommit = true;
	public StudentDao(String path){
		 this.path = path;
		 group = JSON.parseObject(FileUtils.readJson(path),new TypeReference<Group<Student>>(){});
		 
	}


	public boolean commit(){
		if(!isCommit){
			FileUtils.writeJson(path, group.toJsonString());
		}
		isCommit = true;
		return true;
		
	}
	public boolean add(Student stu){
		return group.add(stu);
	}
	public boolean remove(Student stu){
		return group.remove(stu);
	}
	public boolean edit(Student stu){
		group.remove(stu);
		return group.add(stu);
	}
	public String download(){
		return FileUtils.readJson(path);
	}
	public void upload(String str){
		FileUtils.writeJson(path,str);
	}
	public boolean rollback(){
		group = null;
		group = JSON.parseObject(FileUtils.readJson(path),new TypeReference<Group<Student>>(){});
		return true;
	}
	public Group<Student> getgroup() {
		return group;
	}
	
}
