package com.cosine.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cosine.domain.Student;
import com.cosine.services.StudentService;

/**
 * Web层
 * @author cxf
 *
 */
public class StudentServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8078609838013981040L;
	private StudentService ss = StudentService.getInstance();
	
	/**
	 * 转换bean
	 * 添加唯一标示
	 * 
	 */
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Student student = CommonUtils.toBean(request.getParameterMap(), Student.class);
		student.setUuid(CommonUtils.uuid());
		ss.add(student);
		return null;
	}
	public String commit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ss.commit();
		return null;
	}
	
	public String edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Student student = CommonUtils.toBean(request.getParameterMap(), Student.class);
		student.setUuid(CommonUtils.uuid());
		ss.edit(student);
		return null;
	}
	

}