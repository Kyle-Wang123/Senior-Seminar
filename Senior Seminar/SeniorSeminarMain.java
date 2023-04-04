/** Kyle Wang
 * 
 * 
 * 
 **/
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
 
public class SeniorSeminarMain
{
	public static void main(String[] args)
	{
		ArrayList<Session> sessionList = new ArrayList<Session>();
		ArrayList<Person> reassignList = new ArrayList<Person>();
		Sorter sorter = new Sorter();
		try 
		{
			File myObj = new File("seminar.csv");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) 
			{	
				String[] data = myReader.nextLine().split(",");//delimiting the data by commas and storing them into an array
				if (data.length > 7)
				{sessionList.add(new Session(data[9], Integer.parseInt(data[8]), data[7], false));}
				String nameHold = data[1];//the name
				String emailHold = data[0];//the first part of email
				Person perHold = new Person(nameHold, emailHold, Integer.parseInt(data[2]),Integer.parseInt(data[3]),
													   Integer.parseInt(data[4]),Integer.parseInt(data[5]),Integer.parseInt(data[6]));//creating a person object										   
				if (Integer.parseInt(data[2])!=0)
				{
					sessionList.get(perHold.getChoice(0)-1).upPop();
					sessionList.get(perHold.getChoice(0)-1).addWait(perHold);
				}//if
				else
				{reassignList.add(perHold);}
				
			}//while
			myReader.close();
		}//try
		catch (FileNotFoundException e)//exception
		{
			System.out.println("An error has occured");
			e.printStackTrace();
		}//catch
		
		sessionList = sorter.selection(sessionList);
		System.out.println(sessionList);
		Schedule mostPop = new Schedule(sessionList, reassignList);
		mostPop.mostPop();
	}//main
}//SeniorSeminarMain
