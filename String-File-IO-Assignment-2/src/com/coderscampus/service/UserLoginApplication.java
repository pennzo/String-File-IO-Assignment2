package com.coderscampus.service;

import com.coderscampus.service.UserService;
import com.coderscampus.userpojo.User;

public class UserLoginApplication
{
	//static variables that belong to the class
	static User[] users = new User[4];
	static String userEmail;
	static String userPassword;
	
	/* the main method which executes the program */
	public static void main(String[] args)
	{
		// creation of new UserService object
		UserService us = new UserService();
		
		// static users array to retrieve users from data.txt file
		users = us.readInputFromTextFile();
		
		// prompt the user for input to compare against the 
		// array of user objects
		us.promptUserAndValidateInput();		
	}
}
