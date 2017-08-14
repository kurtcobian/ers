package com.revature.obj;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.revature.jdbc.Password;

public class User {
	private int id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private Role role;
	@JsonIgnore
	private List<Reimbursement> reimbs;

	public User() {
		super();
	}

	public User(int id, String username, String password, String firstname, String lastname, String email, Role role,
			List<Reimbursement> reimbs) {
		super();
		this.id = id;
		this.username = username;
		this.password = Password.hashPassword(password);
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.reimbs = reimbs;
	}

	/**
	 * Constructor that doesn't take in a password to avoid double hashing.
	 */
	public User(int id, String username, String firstname, String lastname, String email, Role role,
			List<Reimbursement> reimbs) {
		super();
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.reimbs = reimbs;
	}

	public User(int id, String firstname, String lastname, String email, Role role, List<Reimbursement> reimbs) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.reimbs = reimbs;
	}

	public User(int i) {
		this.id = i;
	}

	public int getId() {
		return id;
	}

	public void setId(int user_id) {
		this.id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String pass) {
		this.password = pass;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String first_name) {
		this.firstname = first_name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String last_name) {
		this.lastname = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Reimbursement> getReimbs() {
		return reimbs;
	}

	public void setReimbs(List<Reimbursement> reimbs) {
		this.reimbs = reimbs;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", email=" + email + ", role=" + role + ", reimbs=" + reimbs + "]";
	}

}
