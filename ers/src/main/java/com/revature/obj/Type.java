package com.revature.obj;

public class Type {
	private int id;
	private String type;
	
	public Type() {
		super();
	}

	public Type(int reimb_type_id, String type) {
		super();
		this.id = reimb_type_id;
		this.type = type;
	}
	
	public Type(String type) {
		super();
		if(type.equals("Lodging")){
			this.type = type;
			this.id = 1;
		} else if(type.equals("Travel")){
			this.type = type;
			this.id = 2;
		} else if(type.equals("Food")){
			this.type = type;
			this.id = 3;
		} else if(type.equals("Other")){
			this.type = type;
			this.id = 4;
		} else
			throw new IllegalArgumentException("Type not recognized!");
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int reimb_type_id) {
		this.id = reimb_type_id;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String reimb_type) {
		this.type = reimb_type;
	}

	@Override
	public String toString() {
		return "Type [id=" + id + ", type=" + type + "]";
	}
	
}
