package com.cosine.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cosine.domain.User;
import com.cosine.services.StudentServices;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/SessionServlet")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");//处理响应编码
		request.setCharacterEncoding("UTF-8");
		String role = (String)request.getSession().getAttribute("role");
		String username = (String)request.getSession().getAttribute("username");
		String uuid = (String)request.getSession().getAttribute("uuid");

		if(username!=null){
			User reuser = new User(username,uuid,role);
			response.getWriter().append(reuser.toJsonString());
			StudentServices.getInstance().rollback();


		}else{
			System.out.println("session中并没有记录到用户登录。");

		}
		
	}

}
