package com.cosine.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cosine.domain.Student;
import com.cosine.services.StudentServices;


@WebServlet("/StudentServlet")
public class StudentServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
   
    public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	System.out.println("add");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String sclass = request.getParameter("sclass");
		String sex = request.getParameter("sex");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String party = request.getParameter("party");

		Student add = new Student(id,name,sclass,sex,phone,address,party);
		StudentServices.getInstance().addStudent(add);
		StudentServices.getInstance().commit();
		return null;
	}
    public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Student remove = new Student(id);
		StudentServices.getInstance().removeStudent(remove);
		return null;
	}
    public String edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String sclass = request.getParameter("sclass");
		String sex = request.getParameter("sex");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String party = request.getParameter("party");

		Student edit = new Student(id,name,sclass,sex,phone,address,party);
		StudentServices.getInstance().editStudent(edit);
		return null;
	}
    public String commit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StudentServices.getInstance().commit();
		return null;
	}
    public String cancel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StudentServices.getInstance().rollback();
		return null;
	}
    public String commitAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	System.out.println("commitAll");
		String str = request.getParameter("data");
    	System.out.println(str);

		StudentServices.getInstance().commitAllStudents(str);
		response.getWriter().append("true");
		return null;
	}
    



}
