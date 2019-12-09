package com.coderscampus.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.coderscampus.userpojo.User;

public class UserService
{
	// new user object
	User user = new User();
	
	// createUser() method used to load/set properties from data.txt
	public User createUser(String username, String password, String name)
	{
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setName(name);
				
		return user;		
	}
	
	public void readInputFromTextFile()
	{
		User[] users = new User[4];
		UserService us = new UserService();
		
		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new FileReader("data.txt"));
			String line = "";
			int i = 0;
		
			while ((line = br.readLine()) != null)
			{	
				System.out.println(line);
				String[] properties = line.split(",");
				
				String username = properties[0];
				String password = properties[1];					
				String name = properties[2];
				
				users[i] = us.createUser(username, password, name);
				i++;
			}
													
		} catch (FileNotFoundException fnfe)
		{
			System.out.println("Oops, the file wasn't found.");
			fnfe.printStackTrace();
		} catch (IOException ioe)
		{
			System.out.println("Oops, there was an IO Exception.");
			ioe.printStackTrace();
		} finally
		{
			try
			{
				System.out.println("Closing file reader");
				br.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		
	}
	
	public void validateInput()
	{
		int numberOfUnsuccessfulAttempts = 0;
		boolean match = true;
		if (!match)
		{
			System.out.println("Invalid login, please try again");
			numberOfUnsuccessfulAttempts++;
			if (numberOfUnsuccessfulAttempts == 5)
			{
				System.out.println("Too many failed login attempts, "
						+ "you are now locked out.");
			}
			//promptUser();
		}
		else
		{
			System.out.println("Welcome: John Doe");
		}
	}	
	
	public void promptUser()
	{
		// prompt the user for login credentials
		Scanner input = new Scanner(System.in);
		System.out.println("Enter your email: ");
		String userEmail = input.next();
		System.out.println("Enter your password: ");
		String userPassword = input.next();
	}
		

}
