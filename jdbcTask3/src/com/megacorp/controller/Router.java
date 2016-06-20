package com.megacorp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Router {

	private String url;
	private boolean redirect;

	public Router(String url, boolean redirect) {
		this.url = url;
		this.redirect = redirect;
	}

	public Router(String url) {
		this.url = url;
		redirect = false;
	}

	public Router() {

	}

	public void route(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher rd = request.getRequestDispatcher(url);

		if (redirect) {
			response.sendRedirect(url);
			return;
		}

		rd.forward(request, response);

	}

}
