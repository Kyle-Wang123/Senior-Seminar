/** Kyle Wang
 *	Main Class
 * The methodology of this program is as follows. It starts by creating all the sessions which are loaded through sessions.csv 
 * and then it creates all the Person objects from seminar.csv. While creating the Person objects, each of the sessions have their
 * popularities increased based on their choice number from each person. If it's a first choice, it is incremented by five, if it's a second choice,
 * it's incremented by 4, etc. Then they are added into the ArrayList attendeesList which is an ArrayList of Person objects. Those that didn't
 * fill out the form(choice of 0) are just added directly to attendeesList. Then, I sorted the list using a Sorter object based on popularity. I then took
 * the top 7 most popular seminars and added them into ArrayList sessionList. I then created a Schedule object and created a schedule based on 
 * sessionList. I then scheduled each of the Person objects using schedulePer. Afterwards, using the toString methods in the Session and Person
 * classes, I used FileWriter to write the master roster in Roster.txt and RosterbySeminar.txt. **/
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
 
public class SeniorSeminarMain
{
	public static void main(String[] args)
	{
		/* Initializing each ArrayList */
		ArrayList<Session> sessionList = new ArrayList<Session>();
		ArrayList<Person> attendeesList = new ArrayList<Person>();
		Sorter sorter = new Sorter();//creating a Sorter object
		try 
		{
			/* creating Scanners to go through each file */
			File myObj = new File("seminar.csv");
			File sessions = new File("sessions.csv");
			Scanner scan = new Scanner(sessions);
			Scanner myReader = new Scanner(myObj);
			while (scan.hasNextLine())//while there is another line in sessions.csv/while there are more sessions
			{
				String[] data = scan.nextLine().split(",");//delimiting the data by commas and storing them into an array
				sessionList.add(new Session(data[2], Integer.parseInt(data[1]), data[0]));//creating a new session object and adding it to sessionList
			}//while
			while (myReader.hasNextLine())//while there is another line in seminar.csv/while there are more people
			{	
				String[] data = myReader.nextLine().split(",");//delimiting the data by commas and storing them into an array
				String nameHold = data[1];//the name
				String emailHold = data[0];//the first part of email
				Person perHold = new Person(nameHold, emailHold, Integer.parseInt(data[2]),Integer.parseInt(data[3]),
													   Integer.parseInt(data[4]),Integer.parseInt(data[5]),Integer.parseInt(data[6]));//creating a person object		
				if (Integer.parseInt(data[2])!=0)//if their choices aren't 0(they filled out the form)
				{
					for (int z = 0; z < 5; z++)//goes through each of the choices
					{
						sessionList.get(perHold.getChoice(z)-1).upPop(5-z);//incrementing based on choice number
					}
				}//else
				attendeesList.add(perHold);//adding each person object to attendeesList
			}//while
			myReader.close();
		}//try
		catch (FileNotFoundException e)//exception
		{
			System.out.println("An error has occured");
			e.printStackTrace();
		}//catch
		
		sessionList = sorter.selection(sessionList);//sorting the sessionList based on popularity
		for (int i = 0; i < 7; i++)
		{
			Session ses = new Session(sessionList.get(i).getProctor(), sessionList.get(i).getID(), sessionList.get(i).getName());//creating a new session object 
																																												 //based on the 7 most popular seminars
			sessionList.add(ses);//adding the new Session object to sessionList
		}//for
		Schedule mostPop = new Schedule(sessionList);//creating a new Schedule object 
		mostPop.mostPop();//creating a schedule based on the most popular seminars
		for (int i = 0; i < attendeesList.size(); i++)//goes through each of the attendees
		{
			mostPop.schedulePer(attendeesList.get(i));//gives them a schedule
		}//for 
		try 
		{
			/* creating new FileWriter objects */
			FileWriter rosterWriter = new FileWriter("Roster.txt");
			FileWriter seminarWriter = new FileWriter("RosterbySeminar.txt");
			for (int i = 0; i < attendeesList.size(); i++)//going through each of the attendees
				rosterWriter.write("Name: "+attendeesList.get(i).toString(0));//writing in Roster.txt the name of the attendee 
																																							  //and their schedule
			rosterWriter.close();
			for(int i = 0; i < sessionList.size(); i++)//goes through each of the sessions
				seminarWriter.write("Seminar Name: "+sessionList.get(i).toString(0)+"\n");//writing in RosterbySeminar.txt
																									//the seminar name, proctor, and the list of attendees
			seminarWriter.close();
			System.out.println("Successfully wrote the rosters");	
		}//try
		catch (IOException e) 
		{
			System.out.println("An error occurred.");
			e.printStackTrace();
		}//catch
	}//main
}//SeniorSeminarMain
