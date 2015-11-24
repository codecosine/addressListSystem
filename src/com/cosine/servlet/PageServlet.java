package com.cosine.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cosine.domain.Client;
import com.cosine.domain.Student;
import com.cosine.services.PageServices;

/**
 * Servlet implementation class PageServlet
 */
public class PageServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private PageServices ps = PageServices.getInstance();
	public String pageHome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getSession().getAttribute("client")==null){
			return "r:/welcome.jsp";
		}else{
			Client c = (Client)request.getSession().getAttribute("client");
			if(c.getPower()==1||c.getPower()==0){
			List<Student> list = ps.getAllStudent();
			request.setAttribute("studata", list);
			}else if(c.getPower()==2){
				String username = c.getUsername();
				List<Student> list = ps.getsClassStudent(username);
				request.setAttribute("studata", list);
			}
			return "f:/WEB-INF/pageHome.jsp";
		}
	}
	public String power(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Client c = (Client)request.getSession().getAttribute("client");
		String jsonre = "{\"power\":"+c.getPower()+"}";
		response.getWriter().print(jsonre);
		return null;
	}
	public String manager(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Client c = (Client)request.getSession().getAttribute("client");
		if(c==null){
			return "r:/welcome.jsp";
		}else if(c.getPower()==2){
			request.getSession().setAttribute("msg", "nopower");
			return "f:/WEB-INF/pageHome.jsp";
		}else{
			List<Student> list = ps.getAllStudent();
			request.setAttribute("studata", list);
			return "f:/WEB-INF/manage.jsp";
		}
	}
	public String welcome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "f:/welcome.jsp";
	}
}
