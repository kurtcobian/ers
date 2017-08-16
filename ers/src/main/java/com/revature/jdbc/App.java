package com.revature.jdbc;

import org.mindrot.jbcrypt.*;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.revature.data.DataService;
import com.revature.obj.Reimbursement;
import com.revature.obj.Status;
import com.revature.obj.Type;
import com.revature.obj.User;
import com.revature.obj.Role;

@SuppressWarnings("unused")
public class App {
	static DataService dservice;
	
	public static void main(String[] args) {
		java.util.Date today = new java.util.Date();
		System.out.println("Attempting");
		try(DataService dservice = new DataService()){
			List<Reimbursement> li = dservice.readAllReimb();
			for(Reimbursement r : li){
				System.out.println(r);
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Donion rings");
	}
	
	public static User authenticate(String user, String pass) throws SQLException{
		if(user != null && pass != null){
			User u = dservice.readByUsername(user);
			if(Password.checkPass(pass, u.getPassword())){
				u.setPassword(null);
				return u;
			}
		}
		return null;
	}

}
