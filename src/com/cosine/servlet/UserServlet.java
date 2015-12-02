package com.cosine.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cosine.domain.Group;
import com.cosine.domain.Student;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		return null;
	}

	public String append(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		return null;
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");//处理响应编码
		request.setCharacterEncoding("UTF-8");
		Group<Student> group = new Group<Student>();
		Student stu3 = new Student("3114004922","御坂3号","2班","男","1323220000","广州",true);
		Student stu2 = new Student("3114004923","御坂2号","2班","男","1323220000","广州",true);
		Student stu1 = new Student("3114004921","御坂1号","2班","男","1323220000","广州",true);

		group.add(stu1);
		group.add(stu2);
		group.add(stu3);

		response.getWriter().append(group.toJsonString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
