package com.revature.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserController uCont;
	private ReimbController rCont;

	@Override
	public void init() throws ServletException {
		uCont = new UserController();
		rCont = new ReimbController();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doDispatch(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doDispatch(req, resp);
	}

	protected void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		switch (uri) {
			case "/ers/login.do": {
				uCont.login(req, resp);
				break;
			}
			case "/ers/display.do": {
				rCont.displayAll(req, resp);
				break;
			}
		}
	}

}
