package com.cosine.services;

import java.util.List;

import com.cosine.dao.ClientDaoimpl;
import com.cosine.dao.StudentDaoimpl;
import com.cosine.domain.Student;

public class PageServices {
	ClientDaoimpl cd = ClientDaoimpl.getInstance();
	StudentDaoimpl sd = StudentDaoimpl.getInstance();
	private static PageServices instance;
	private PageServices(){}
	/**
	 * 在页面启动时候的初始化方法
	 * 应该加载json文件
	 * 获得所有的学生,获得所有的用户名
	 */
	public void init(){
		sd.loadAllStudent();
		cd.load();
	}
	public List<Student> getAllStudent() {
		return sd.queryAll();
	}
	public List<Student> getsClassStudent(String username) {
		String sclass = sd.queryStuclass(username);
		sd.setPath(sclass);
		sd.loadStudent();
		return sd.getLocalList();
	}
	public static PageServices getInstance() {
		if(instance==null){
			instance= new PageServices();
		}
		return instance;
	}
}
