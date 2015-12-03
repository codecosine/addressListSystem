package com.cosine.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cosine.domain.User;
import com.cosine.services.UserServices;
import com.cosine.utils.ParseMD5;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/UserServlet")
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
    	String powerrole = (String)request.getSession().getAttribute("role");
		String powername = (String)request.getSession().getAttribute("username");
		User power = new User(powername,null,powerrole);
		
		String addname = request.getParameter("username");
		String password = ParseMD5.parseStrToMd5L16(request.getParameter("password"));
		String addrole = request.getParameter("role");
		User adduser = new User(addname,password,addrole);

		UserServices.getInstance().addUser(power, adduser);
    	UserServices.getInstance().commit();
		return null;
	}

	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String powerrole = (String)request.getSession().getAttribute("role");
		String powername = (String)request.getSession().getAttribute("username");
		User power = new User(powername,null,powerrole);
		
		String removename = request.getParameter("username");
		String removerole = request.getParameter("role");
		User removeuser = new User(removename,null,removerole);

		UserServices.getInstance().removeUser(power, removeuser);
    	UserServices.getInstance().commit();
		return null;
	}
	public String editRole(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String powerrole = (String)request.getSession().getAttribute("role");
		String powername = (String)request.getSession().getAttribute("username");
		User power = new User(powername,null,powerrole);
		
		String name = request.getParameter("username");
		String role = request.getParameter("role");
		User editUser = new User(name,null,role);

		UserServices.getInstance().editRole(power, editUser);
    	UserServices.getInstance().commit();
		return null;
	}
	public String editPass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String)request.getSession().getAttribute("username");
		String oldpassword = ParseMD5.parseStrToMd5L16(request.getParameter("oldpass"));
		String newpassword = ParseMD5.parseStrToMd5L16(request.getParameter("newpass"));
		System.out.println("editpass:"+username);
		boolean a = UserServices.getInstance().editPassword(username,oldpassword,newpassword);
		Boolean result = new Boolean(a);
    	UserServices.getInstance().commit();
    	System.out.println(result);
		response.getWriter().append(result.toString());
		return null;
	}
	


}
