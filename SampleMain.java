import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.coderscampus.service.UserService;
import com.coderscampus.userpojo.User;

public class SampleMain
{
	static String userEmail;
	static String userPassword;
	public static void main(String[] args)
	{
		
		User[] users = new User[4];
		User user = new User();
		UserService us = new UserService();
		String username;
		String password;					
		String name;
		
		// prompt the user for login credentials
				Scanner input = new Scanner(System.in);
				System.out.println("Enter your email: ");
				userEmail = input.next();
				System.out.println("Enter your password: ");
				userPassword = input.next();
				
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
						
						username = properties[0];
						password = properties[1];					
						name = properties[2];
						
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
				
				int numberOfUnsuccessfulAttempts = 0;
				while (numberOfUnsuccessfulAttempts < 5)
				{
					for (int i = 0; i < 4; i++)
					{
						if(userEmail.equalsIgnoreCase(user.getUsername()) && userPassword.equals(user.getPassword()))
						{
							System.out.println("Welcome: " + user.getName());
						}
						else
						{
							System.out.println("Invalid login, please try again");
							numberOfUnsuccessfulAttempts++;
							
							if (numberOfUnsuccessfulAttempts == 5)
							{
								System.out.println("Too many failed login attempts, "
										+ "you are now locked out.");
								break;
							}
							
						}
					}
				}	
			}	

	
	// createUser() method used to load/set properties from data.txt
		public User createUser(String username, String password, String name)
		{
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setName(name);
					
			return user;		
		}

}
