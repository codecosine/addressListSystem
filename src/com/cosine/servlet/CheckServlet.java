package com.cosine.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cosine.domain.Client;
import com.cosine.services.ClientServices;
import com.cosine.services.PageServices;

/**
 * Servlet implementation class CheckServlet
 */

public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ClientServices cs = ClientServices.getInstance();
	private PageServices ps = PageServices.getInstance();
	
	@Override
	public void init() throws ServletException {
		super.init();
		ps.init();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String username = request.getParameter("username");  
	     String password = request.getParameter("password"); 
	     Client c = new Client(username,password);
	     boolean result = cs.check(c);
	     int power = cs.getPower(c);
	     if(result){
		     c.setPower(power);
		     request.getSession().setAttribute("client", c);
	     }
		 String jsonre = "{\"result\":"+result+",\"power\":"+power+"}";
		 response.getWriter().print(jsonre);
	}

}
