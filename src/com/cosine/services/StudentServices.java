package com.cosine.services;

import org.junit.Test;

import com.cosine.dao.StudentDao;
import com.cosine.domain.Group;
import com.cosine.domain.Student;

/**
 * 一定要搞清楚服务的使用场景 用户服务 应该只暴露一个修改密码给所有者 其他都是超级管理员的操作服务
 * 
 * @author codecosine
 *
 */
public class StudentServices {
	StudentDao studentdao = null;
	private static StudentServices us = null;

	private StudentServices() {
		init();
	}

	public static StudentServices getInstance() {
		if (us == null) {
			us = new StudentServices();
		}
		return us;
	}

	@Test
	public void init() {
		String path = StudentServices.class.getResource("/").getFile();
		path = path.substring(0, path.indexOf("WEB-INF") + "WEB-INF".length()) + "/data" + "/students.json";
		System.out.println(path);
		studentdao = new StudentDao(path);
	}

	public boolean addStudent(Student addstu) {
		return studentdao.add(addstu);

	}

	public boolean removeStudent(Student removestu) {
		return studentdao.remove(removestu);

	}

	public boolean editStudent(Student editstu) {

		return studentdao.edit(editstu);
	}

	public boolean commit() {
		return studentdao.commit();
	}

	public boolean rollback() {
		return studentdao.rollback();
	}

	public Group<Student> getALLStudents() {
		return studentdao.getgroup();
	}
	public void commitAllStudents(String str) {
		 studentdao.upload(str);
	}
}
