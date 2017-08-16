package com.revature.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.data.DataService;
import com.revature.jdbc.Password;
import com.revature.obj.Reimbursement;
import com.revature.obj.User;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ReimbController {

	protected void displayAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User loggedIn = (User) req.getSession().getAttribute("loggedIn");
		if (loggedIn == null) {
			System.out.println("Not logged in");
			resp.getWriter().println("You ain't logged in ninja. Go somewhere else");
			req.getRequestDispatcher("index.html").forward(req, resp);
		} else if (loggedIn.getRole().getRole().equals("Financier")) {
			// get all user's reimburesments from the DAO
			System.out.println("Financier");
			List<Reimbursement> reimbursements = null;
			try(DataService dservice = new DataService()){
				reimbursements = dservice.readAllReimb();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Object[] response = {loggedIn.getRole(), reimbursements};
			new ObjectMapper().writeValue(resp.getWriter(), response);
		} else {
			System.out.println("Employee");
			Object[] response = {loggedIn.getRole(), loggedIn.getReimbs()};
			new ObjectMapper().writeValue(resp.getWriter(), response);
		}
	}
}
