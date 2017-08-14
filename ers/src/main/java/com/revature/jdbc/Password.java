package com.revature.jdbc;

import org.mindrot.jbcrypt.BCrypt;

/**
 * A custom password encrypting class that encrypts and compares passwords
 * using JBCrypt.
 * @author Cobian
 *
 */
public class Password{
    //workload for jbcrypt, can be between 10-31
    private static int workload = 15;
    
    public static String hashPassword(String plainTextPassword){
        String salt = BCrypt.gensalt(workload);
        String hashedPassword = BCrypt.hashpw(plainTextPassword, salt);
        return (hashedPassword);    
    }
    
    public static Boolean checkPass(String plaintext, String hashtext){
    	return BCrypt.checkpw(plaintext, hashtext);
    }
}