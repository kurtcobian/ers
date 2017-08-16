package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.obj.Reimbursement;
import com.revature.obj.Role;
import com.revature.obj.Status;
import com.revature.obj.Type;
import com.revature.obj.User;

/**
 * ReimbDAO is responsible for handling all interaction between the app and the
 * Reimbursement table within the database. Comes with several overloaded read
 * functions to filter results of reimbursement look-ups for display purposes.
 * 
 * @author Cobian
 *
 */
public class ReimbursementDAO {
	private Connection conn;

	public ReimbursementDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	/**
	 * Takes in a Reimbursement object and adds it to the database. Assumes that
	 * the database has implemented sequences and triggers for the table and
	 * ignores the id in the insert.
	 * 
	 * @param reimb
	 *            A reimbersuement object that we want to pass into the database
	 * @throws SQLException
	 */
	public void create(Reimbursement reimb) throws SQLException {
		String sql = "insert into reimb (amount, submitted, resolved, "
				+ "description, receipt, author, resolver, status_id, type_id) values (?,?,?,?,?,?,?,?,?)";
		PreparedStatement pst = conn.prepareStatement(sql);

		pst.setDouble(1, reimb.getAmount());
		pst.setTimestamp(2, reimb.getSubmitted());
		pst.setTimestamp(3, reimb.getResolved());
		pst.setString(4, reimb.getDesc());
		pst.setBlob(5, reimb.getReceipt());
		pst.setInt(6, reimb.getAuthor().getId());

		if (reimb.getResolver() == null) {
			pst.setNull(7, Types.INTEGER);
		} else {
			pst.setInt(7, reimb.getResolver().getId());
		}

		pst.setInt(8, reimb.getStatus().getId());
		pst.setInt(9, reimb.getType().getId());

		pst.executeUpdate(); // compiles and sends to the DB
	}

	public List<Reimbursement> readAuthor(User author) throws SQLException {
		List<Reimbursement> reimbs = new ArrayList<Reimbursement>();
		String sql = "select r.r_id, r.amount, r.submitted, r.resolved, r.description, r.receipt, r.author, "
				+ "u.u_id, u.firstname, u.lastname, u.email, ro.r_id, ro.role, "
				+ "r.status_id, s.status, r.type_id, t.type from reimb r "
				+ "inner join ers_status s on s.s_id = r.status_id inner join ers_type t on t.t_id = r.type_id "
				+ "left join ers_users u on u.u_id = r.resolver "
				+ "left join roles ro on u.role_id = ro.r_id where r.author = ?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, author.getId());
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			User resolver;
			if(rs.getInt(8) == 0){
				resolver = null;
			} else {
				resolver = new User(rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11),
						new Role(rs.getInt(12), rs.getString(13)), new ArrayList<Reimbursement>());
			}
			Reimbursement r = new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4),
					rs.getString(5), rs.getBlob(6), author, resolver, new Status(rs.getInt(14), rs.getString(15)),
					new Type(rs.getInt(16), rs.getString(17)));
			reimbs.add(r);
		}
		return reimbs;
	}

	/**
	 * Assumes that the reimbursement object as been updated with the new
	 * information and then writes it to the database.
	 * 
	 * @param reimb
	 * @throws SQLException
	 */
	public void update(Reimbursement reimb) throws SQLException {
		if (reimb.getId() <= 0)
			throw new IllegalArgumentException();
		String sql = "update reimb set resolver = ?, resolved = ?, status_id = ? where r_id = ?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, reimb.getResolver().getId());
		pst.setTimestamp(2, reimb.getResolved());
		pst.setInt(3, reimb.getStatus().getId());
		pst.setInt(4, reimb.getId());

		pst.executeUpdate();
	}

}
