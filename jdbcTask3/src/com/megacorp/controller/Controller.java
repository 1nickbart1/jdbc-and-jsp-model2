package com.megacorp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller
 */
@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actionName = getActionName(request);
		Router router = null;
		
		request.setCharacterEncoding("UTF-8");
		ActionFactory factory = new ActionFactory();
		Action action = factory.getAction(actionName);
		router = action.perform(request, response);
		router.route(request, response);
	}
	
	private String getActionName(HttpServletRequest request){
		String url = request.getServletPath();
		int slash = url.lastIndexOf("/");
		int dot = url.lastIndexOf(".");
		
		if(dot > slash){
			url = url.substring(slash + 1, dot);
		}
		
		return url;
	}

}
