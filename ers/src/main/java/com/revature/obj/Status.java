package com.revature.obj;

public class Status {
	private int id;
	private String status;

	public Status() {
		super();
	}

	public Status(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int reimb_status_id) {
		this.id = reimb_status_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String reimb_status) {
		this.status = reimb_status;
	}

}
