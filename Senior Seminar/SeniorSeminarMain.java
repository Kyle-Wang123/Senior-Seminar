/** Kyle Wang
 * 
 * 
 * 
 **/
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
 
public class SeniorSeminarMain
{
	public static void main(String[] args)
	{
		try 
		{
			int i= 0;
			File myObj = new File("seminarTest.csv");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) 
			{	
				String[] data = myReader.nextLine().split(",");//delimiting the data by commas and storing them into an array
				String nameHold = data[1];//the company id in string form
				String emailHold = data[0];
				Person perHold = new Person(nameHold, emailHold, Integer.parseInt(data[2]),Integer.parseInt(data[3]),
													   Integer.parseInt(data[4]),Integer.parseInt(data[5]),Integer.parseInt(data[6]));
				System.out.println(perHold);
			}//while
			myReader.close();
		}//try
		catch (FileNotFoundException e)//exception
		{
			System.out.println("An error has occured");
			e.printStackTrace();
		}//catch
	}//main
}//SeniorSeminarMain
