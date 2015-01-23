/*
 * Written by Brian Tracy
 * AP Computer Science
 * 
 * Entry point for application, sets up the CowFrame and sets some of its specific properties
 */

public class Main 
{

	public static void main(String[] args) 
	{
		CowFrame c = new CowFrame();
		c.setDefaultCloseOperation(c.EXIT_ON_CLOSE);
		c.setVisible(true);
		c.setResizable(false);
	}

}