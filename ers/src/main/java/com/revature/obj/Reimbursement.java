package com.revature.obj;

import java.sql.Blob;
import java.sql.Timestamp;

public class Reimbursement {
	private int id;
	private double amount;
	private Timestamp submitted;
	private Timestamp resolved = null;
	private String desc = null;
	private Blob receipt = null;
	private User author;
	private User resolver;
	private Status status;
	private Type type;

	public Reimbursement() {
		super();
	}

	public Reimbursement(int id, double amount, Timestamp submitted, Timestamp resolved, String desc, Blob receipt, User author,
			User resolver, Status status, Type type) {
		super();
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.desc = desc;
		this.receipt = receipt;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Blob getReceipt() {
		return receipt;
	}

	public void setReceipt(Blob receipt) {
		this.receipt = receipt;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public User getResolver() {
		return resolver;
	}

	public void setResolver(User resolver) {
		this.resolver = resolver;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Reimb [id=" + id + ", amount=" + amount + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", desc=" + desc + ", receipt=" + receipt + ", author=" + author.getFirstname() + " " + author.getLastname() + ", resolver=" + resolver
				+ ", status=" + status + ", type=" + type + "]";
	}
	
	

}
