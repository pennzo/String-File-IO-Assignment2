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
	static User[] users = new User[4];

	// createUser() method used to load/set properties from data.txt
	public User createUser(String username, String password, String name)
	{
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setName(name);

		return user;
	}

	// method to read and store input from text file into 
	// an array of user objects
	public User[] readInputFromTextFile()
	{
		UserService us = new UserService();

		BufferedReader br = null;
		try
		{
			// create a reader to obtain input from each line
			br = new BufferedReader(new FileReader("data.txt"));
			String line = "";
			int i = 0;

			while ((line = br.readLine()) != null)
			{
				// split each line read from the data.txt
				// into an array of three properties
				// named username, password and name
				String[] properties = line.split(",");
				String username = properties[0];
				String password = properties[1];
				String name = properties[2];
				
				// assign each of the String elements from the properties 
				// array into each Object 'users' into the User array
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
		return users;
	}

	// method to validate the input given from the console
	// and iterate through the users array to determine if
	// input is valid 
	public void promptUserAndValidateInput()
	{
		String userEmail;
		String userPassword;
		
		// instantiate attempts to zero
		// instantiate match to false
		int numberOfUnsuccessfulAttempts = 0;
		boolean match = false;
		Scanner input = new Scanner(System.in);
		while (numberOfUnsuccessfulAttempts < 5)
		{
			if (match == true)
			{
				break;
			}
			// prompt the user for login credentials
			System.out.println("Enter your email: ");
			userEmail = input.next();
			System.out.println("Enter your password: ");
			userPassword = input.next();

			// traverse the array for a match
			for (int i = 0; i < users.length; i++)
			{
				// checking to see if the username is equal regardless and 
				if (userEmail.equalsIgnoreCase(users[i].getUsername()) && userPassword.equals(users[i].getPassword()))
				{
					System.out.println("Welcome: " + users[i].getName());
					match = true;
					break;
				}
			}
			// if unable to find a match, prompt user to try again
			if (!match)
			{
				System.out.println("Invalid login, please try again");
				numberOfUnsuccessfulAttempts++;
				// check to see if user has attempted 5 tries
				if (numberOfUnsuccessfulAttempts == 5)
				{
					System.out.println("Too many failed login attempts, " + "you are now locked out.");
				}
			}
		}
		// close the Scanner
		input.close();
	}
}
