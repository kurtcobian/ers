package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.obj.User;
import com.revature.obj.Reimbursement;
import com.revature.obj.Role;

public class UserDAO {
	private Connection conn;

	public UserDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	/**
	 * Creates the user in the database. Assumes that triggers and sequences are
	 * in the database. Ignores the id for the User object.
	 * 
	 * @param user
	 * @throws SQLException
	 */
	public void create(User user) throws SQLException {
		String sql = "insert into ers_users (username, password, firstname, lastname, email, role_id) "
				+ "values (?,?,?,?,?,?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, user.getUsername());
		pst.setString(2, user.getPassword());
		pst.setString(3, user.getFirstname());
		pst.setString(4, user.getLastname());
		pst.setString(5, user.getEmail());
		pst.setInt(6, user.getRole().getId());
		pst.executeUpdate();
	}

	/**
	 * Load all users for financiers pov. Only has the bare minimum of
	 * information of what financiers need from employees.
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<User> readEmployees() throws SQLException {
		List<User> users = new ArrayList<>();
		String sql = "select * from ers_users where role_id = 2";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			User u = new User(rs.getInt("u_id"), rs.getString("firstname"), rs.getString("lastname"),
					rs.getString("email"), new Role(2, "Employee"), new ArrayList<Reimbursement>());
			users.add(u);
		}
		return null;
	}

	/**
	 * Used for logging in. Looks for a specific username
	 * 
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public User readByUsername(String username) throws SQLException {
		String sql = "select u.u_id, u.password, u.firstname, u.lastname, u.email, u.role_id, r.role "
				+ "from ers_users u inner join roles r on u.role_id = r.r_id "
				+ "where u.username = ?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, username);
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			User u = new User(rs.getInt(1), username, rs.getString(3), rs.getString(4),
						rs.getString(5), new Role(rs.getInt(6), rs.getString(7)), new ArrayList<Reimbursement>());
			u.setPassword(rs.getString(2));
			return u;
		}
		return null;
	}

	/**
	 * Used for logging in. Looks for a specific email Not used at the moment.
	 * 
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public User readByEmail(String email) throws SQLException {
		String sql = "select u.u_id, u.username, u.password, u.firstname, u.lastname, u.role_id, r.role "
				+ "from ers_users u inner join roles r on u.role_id = r.r_id "
				+ "where u.email = ?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, email);
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			User u = new User(rs.getInt(1), rs.getString(2), email, rs.getString(4),
						rs.getString(5), new Role(rs.getInt(6), rs.getString(7)), new ArrayList<Reimbursement>());
			u.setPassword(rs.getString(3));
			return u;
		}
		return null;
	}

	/**
	 * Will implement a way to call this function and update a user's
	 * information
	 * 
	 * @param user
	 * @throws SQLException
	 */
	public void update(User user) throws SQLException {
		String sql = "update ers_users set username = ?, password = ?, firstname = ?, "
				+ "lastname = ?, email = ?, role_id = ? where u_id = ?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, user.getUsername());
		pst.setString(2, user.getPassword());
		pst.setString(3, user.getFirstname());
		pst.setString(4, user.getLastname());
		pst.setString(5, user.getEmail());
		pst.setInt(6, user.getRole().getId());
		pst.setInt(7, user.getId());

		pst.executeUpdate();
	}

	public void delete(User user) throws SQLException {
		String sql = "delete from ers_users where u_id = ?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, user.getId());
	}

}