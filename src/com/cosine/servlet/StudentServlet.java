package com.cosine.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cosine.domain.Group;
import com.cosine.domain.Student;
import com.cosine.domain.User;
import com.cosine.services.UserServices;
import com.cosine.utils.ParseMD5;


@WebServlet("/StudentServlet")
public class StudentServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

    public StudentServlet() {
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



}
