import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Main 
{
	private static ArrayList<WordPair> words = new ArrayList<WordPair>();
	
	public static void main(String[] args) 
	{
		System.out.println("hllo");
		for (int i = 0; i < numberOfWords(); i++)
		{
			
		}
		
	}
	
	private static int numberOfWords()
	{
		Scanner input = new Scanner(System.in);
		System.out.println("How Many Words will there be?");
		return Integer.parseInt(input.nextLine());
	}
}
