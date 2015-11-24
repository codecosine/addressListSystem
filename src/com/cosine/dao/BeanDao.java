package com.cosine.dao;

import java.util.List;

import com.cosine.domain.Student;
/**
 * 
 * 操作学生数据
 * 操作数据，有事务处理
 * open() commit() rollback开启一个事务，提交一个事务
 * 期间，有增删改查的方法
 * insertStudent deleteStudent editStudent queryStudent
 * 为了应对加载，还应该有获得所有数据的方法
 * queryALL load
 * 为了应对加载，还应该有设置加载数据路径的方法
 * setPath getPath
 *
 */
public interface BeanDao<T> {
	public boolean insert(T c);
	public boolean delete(T c);
	public boolean edit(T c);
	public Student query(T c);
	public void load();
	public List<T> queryAll();
	public void setPath(String path);
	public String getPath();
	public void start();
	public void commit();
	public void rollback();


	
}