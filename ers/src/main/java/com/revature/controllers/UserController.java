package com.revature.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.data.DataService;
import com.revature.jdbc.Password;
import com.revature.obj.User;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserController {

	/**
	 * Handler for logging in users. Fetches the username and password for
	 * authentification and forwards the user to the home page if its a valid
	 * signin or sends the user back to the log in page.
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		User u;
		try {
			u = authenticate(username, password);
			if (u != null) {
				req.getSession().setAttribute("loggedIn", u);
				// redirect to secure pages after authentication
				resp.sendRedirect("dash.html");
			} else {
				// redirect to login page
				req.setAttribute("loginfail", "Authentication Failure");
				req.getRequestDispatcher("index.html").forward(req, resp);
			}
		} catch (SQLException e) {
			System.out.println("Something went wrong!");
		}
	}

	/**
	 * Authenticates the User with the password and then, if the user and
	 * password match, then we make the next dataservice call to create and fill
	 * in the users List<Reimbursement>
	 * 
	 * @param usr
	 * @param pass
	 * @return
	 * @throws SQLException
	 */
	private User authenticate(String usr, String pass) throws SQLException {
		User u = null;
		try (DataService dservice = new DataService()) {
			u = dservice.readByUsername(usr);
			if (u != null && Password.checkPass(pass, u.getPassword())) {
				u.setPassword(null);
				u.setReimbs(dservice.readByAuthor(u));
				return u;
			}
		} catch (SQLException e) {
			System.out.println("Error during Authentication");
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}
}
