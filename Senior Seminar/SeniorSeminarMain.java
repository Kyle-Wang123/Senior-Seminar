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
		ArrayList<Person> attendeesList = new ArrayList<Person>();
		Sorter sorter = new Sorter();
		try 
		{
			File myObj = new File("seminar.csv");
			File sessions = new File("sessions.csv");
			Scanner scan = new Scanner(sessions);
			Scanner myReader = new Scanner(myObj);
			while (scan.hasNextLine())
			{
				String[] data = scan.nextLine().split(",");
				sessionList.add(new Session(data[2], Integer.parseInt(data[1]), data[0], false));
			}//while
			while (myReader.hasNextLine()) 
			{	
				String[] data = myReader.nextLine().split(",");//delimiting the data by commas and storing them into an array
				String nameHold = data[1];//the name
				String emailHold = data[0];//the first part of email
				Person perHold = new Person(nameHold, emailHold, Integer.parseInt(data[2]),Integer.parseInt(data[3]),
													   Integer.parseInt(data[4]),Integer.parseInt(data[5]),Integer.parseInt(data[6]));//creating a person object		
				if (Integer.parseInt(data[2])==0)
				{reassignList.add(perHold);}
				else
				{
					sessionList.get(perHold.getChoice(0)-1).upPop(5);
					sessionList.get(perHold.getChoice(1)-1).upPop(4);
					sessionList.get(perHold.getChoice(2)-1).upPop(3);
					sessionList.get(perHold.getChoice(3)-1).upPop(2);
					sessionList.get(perHold.getChoice(4)-1).upPop(1);	
					attendeesList.add(perHold);
				}//else
				
			}//while
			myReader.close();
		}//try
		catch (FileNotFoundException e)//exception
		{
			System.out.println("An error has occured");
			e.printStackTrace();
		}//catch
		
		sessionList = sorter.selection(sessionList);
		//System.out.println(sessionList);
		for (int i = 0; i < 7; i++)
		{
			sessionList.add(sessionList.get(i));
			sessionList.get(i+18).changeSecond(true);
		}//for
		//System.out.println(sessionList);
		Schedule mostPop = new Schedule(sessionList, attendeesList);
		mostPop.mostPop();
		System.out.println("");
		for (int i = 0; i < 1; i++)
		{
			mostPop.schedulePer(attendeesList.get(i));
			for (int j = 0; j < 5; j++)
			{
				System.out.print(attendeesList.get(i).getFinal(j));
			}
			System.out.println("");
			System.out.println(attendeesList.get(i));
		}//for 
	}//main
}//SeniorSeminarMain
