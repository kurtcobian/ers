package com.revature.controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.data.DataService;
import com.revature.obj.Reimbursement;
import com.revature.obj.Status;
import com.revature.obj.User;

public class ReimbController {

	protected void displayAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User loggedIn = (User) req.getSession().getAttribute("loggedIn");
		if (loggedIn == null) {
			System.out.println("Not logged in");
			resp.getWriter().println("You ain't logged in ninja. Go somewhere else");
			req.getRequestDispatcher("index.html").forward(req, resp);
		} else if (loggedIn.getRole().getRole().equals("Financier")) {
			List<Reimbursement> reimbursements = null;
			try(DataService dservice = new DataService()){
				reimbursements = dservice.readAllReimb();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Object[] response = {loggedIn.getRole(), reimbursements};
			new ObjectMapper().writeValue(resp.getWriter(), response);
		} else {
			Object[] response = {loggedIn.getRole(), loggedIn.getReimbs()};
			new ObjectMapper().writeValue(resp.getWriter(), response);
		}
	}
	
	protected void approve(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Reimbursement reimbursement = new ObjectMapper().readValue(req.getInputStream(), Reimbursement.class);
		try(DataService dservice = new DataService()){
			reimbursement.setStatus(new Status(2, "Approved"));
			reimbursement.setResolver((User) req.getSession().getAttribute("loggedIn"));
			reimbursement.setResolved(new Timestamp(System.currentTimeMillis()));
			dservice.updateReimb(reimbursement);
			dservice.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deny(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Reimbursement reimbursement = new ObjectMapper().readValue(req.getInputStream(), Reimbursement.class);
		try(DataService dservice = new DataService()){
			reimbursement.setStatus(new Status(3, "Denied"));
			reimbursement.setResolver((User) req.getSession().getAttribute("loggedIn"));
			reimbursement.setResolved(new Timestamp(System.currentTimeMillis()));
			dservice.updateReimb(reimbursement);
			dservice.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Reimbursement reimbursement = new ObjectMapper().readValue(req.getInputStream(), Reimbursement.class);
		try(DataService dservice = new DataService()){
			reimbursement.setAuthor((User)req.getSession().getAttribute("loggedIn"));
			reimbursement.setStatus(new Status(1, "Pending"));
			reimbursement.setSubmitted(new Timestamp(System.currentTimeMillis()));
			dservice.createReimb(reimbursement);
			System.out.println("Done");
			dservice.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
