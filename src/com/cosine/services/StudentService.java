package com.cosine.services;


import com.cosine.dao.BeanDao;
import com.cosine.dao.StudentDaoimpl;
import com.cosine.domain.Student;

public class StudentService {
	BeanDao<Student> dao = StudentDaoimpl.getInstance();
	private static StudentService instance = null;
	private StudentService(){}
	public void add(Student student){
		dao.setPath(student.getSclass());
		dao.start();
		dao.insert(student);	
	}
	public void commit(){
		dao.commit();
	}
	public void delete(Student student){
		dao.setPath(student.getSclass());
		dao.start();
		dao.delete(student);

	}
	public void edit(Student student){
		dao.setPath(student.getSclass());
		dao.start();
		dao.edit(student);		
	}
	public static StudentService getInstance() {
		if(instance==null){
			instance = new StudentService();
		}
		return instance;
	}


}
