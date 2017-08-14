package com.revature.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import com.revature.obj.Reimbursement;
import com.revature.obj.User;

public class DataService implements AutoCloseable {
	public static Connection conn;
	private static ReimbursementDAO reimbDAO;
	private static UserDAO userDAO;

	public DataService() {
		super();
		conn = ConnectionManager.getConnection();
		reimbDAO = new ReimbursementDAO(conn);
		userDAO = new UserDAO(conn);
	}

	public void close() throws Exception {
		if (conn != null && !conn.isClosed())
			conn.close();
	}

	/*
	 * For interaction with the connection
	 */
	public void commit() throws SQLException {
		conn.commit();
	}

	/* <Reimbursement DAO Methods> */
	public void createReimb(Reimbursement reimb) throws SQLException {
		reimbDAO.create(reimb);
	}

	public List<Reimbursement> readByAuthor(User user) throws SQLException {
		return reimbDAO.readAuthor(user);
	}


	//Only can be used by resolvers
	public void updateReimb(Reimbursement reimb) throws SQLException {
		reimbDAO.update(reimb);
	}

	/* </Reimbursement DAO Methods> */
	/********************************/
	/********************************/
	/* <User DAO Methods> */
	public void createUser(User user) throws SQLException {
		userDAO.create(user);
	}
	
	public User readByUsername(String username) throws SQLException {
		return userDAO.readByUsername(username);
	}
	
	public User readByEmail(String email) throws SQLException {
		return userDAO.readByEmail(email);
	}


}
