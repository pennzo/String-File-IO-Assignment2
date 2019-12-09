package com.coderscampus.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.coderscampus.userpojo.User;
import com.coderscampus.service.UserService;

public class UserLoginApplication
{
	/* the main method which executes the program */
	public static void main(String[] args)
	{
		// creation of new user object and new service object
		User user = new User();
		UserService us = new UserService();
		
		// initialize unsuccessful attempts
		int numberOfUnsuccessfulAttempts = 0;
		
		// begin the program by loading 'data.txt' file into
		// an array of User objects
		us.readInputFromTextFile();
		
		// continue to prompt user if login unsuccessful
		while (numberOfUnsuccessfulAttempts < 5)
		{
		// prompt the user for input
		us.promptUser();
		}
	
	}
}
