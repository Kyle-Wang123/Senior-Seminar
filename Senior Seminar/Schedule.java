/* Kyle Wang
 * Schedule Class
 * This is the Schedule class. It contains an ArrayList of sessions in the schedule and an ArrayList of attendees. It also constains a 2D 
 * array of session objects which is the schedule. The methods contained are mostPop(), which organizes the list of sessions into a schedule
 * based on a sorted list from greatest popularity to least(this is initialized to sessionList in the constructor), swap(), which switches two sessions in the schedule 
 * in the case that either the same session or sessions with the same proctor are going on at the same time, schedulePer(Person per), which 
 * gives the schedule to each Person object, forceAssign(Person per), which in the case that they are not scheduled for anything at a certain time, 
 * will automatically schedule a session for them, and a toString method, and alrTaken(Person per), which makes sure the person hasn't taken the seminar before. **/
import java.util.ArrayList;
import java.util.Arrays;

public class Schedule
{
	/* declaring the session and attendees list as well as declaring the schedule and timeSlot ArrayList.
	 * They are static so they can be used across methods*/ 
	private static ArrayList<Session> sessionList = new ArrayList<Session>();
	private static ArrayList<Person> attendeesList = new ArrayList<Person>();
	private static Session[][] schedule = new Session[5][5];
	private static ArrayList<Session> timeSlot;
	/* constructor which initializes the sessionList and the schedule */
	public Schedule(ArrayList<Session> sessionList)
	{
		this.sessionList = sessionList;
		for (int h = 0; h < 5; h++)//going through the different "time" periods
		{
			for(int g = 0; g < 5; g++)//going through the individual rooms
			{
				schedule[h][g] = new Session(" ", 0, " ");//populating each room at the specified time with blank sessions
			}//inner for
		}//outer for
	}//Schedule
	
	/* This is method that creates a schedule based on the most popular sessions.
	 * It does this by using the sorted sessions in sessionList adding them to each room 
	 * at each time. To keep track of where the method is in sessionList, counter is used and
	 * incremented everytime a session is schedule for a room and time. In case a proctor
	 * has to be at two places at the same time, the swap method is used */ 
	public void mostPop()
	{
		int counter = 0;
		for (int r = 0; r < 5; r++)//goes through the different time
		{
			for (int c = 0; c < 5; c++)//goes through each of the rooms
			{
				schedule[r][c] = sessionList.get(counter);//initializes each of rooms in the schedule to a a session in sessionList
				counter++;//increments counter to keep place in sessionList
				for (int n = 0; n < 5; n++)//goes through the rooms in the current row
				{
					if (schedule[r][c].getProctor().equals(schedule[r][n].getProctor()) && n!=c)//if a proctor is teaching two seminars at the same time
					{
						Schedule.swap(c, r);//swaps the current session with another
						break;//breaks out of the innermost for loop
					}
				}//innermost for
			}//middle for
		}//outer for
	}//mostPop	
				
	/* In the case that a proctor is needed at two places during the same block, this method is used to
	 * fix that. It does this by going through each of the time blocks and then checking if the proctor
	 * of the session that needs to be switched(session that needs swapping = swap) is available at that 
	 * specific time period. Next, it checks if the proctor of the session that will replace swap is available
	 * during swap's time slot. If that is the case, the two sessions will swap. */
	public static void swap(int room, int time)
	{
		for (int l = 0; l < 5; l++)//goes through each of the time blocks
		{
			/* initializing boolean variables and hold and swap */
			boolean tarRowClear = true;
			boolean swapRowClear = true;
			int hold = 0;
			Session swap = schedule[time][room];
			for (int m = 0; m < 5; m++)// checks the rooms at time slot l 
			{
				if (swap.getProctor().equals(schedule[l][m].getProctor())==true)//if the proctor of swap is also teaching at this time slot/target time
				{
					tarRowClear = false;//targetRowClear is false
					break;//breaks out of the m for loops
				}//if
			}//for
			
			if (tarRowClear == true)//if swap can be switched to the targeted time
			{
				for (int n = 0; n < 5; n++)//goes through the rooms in targeted time slot
				{
					swapRowClear = true;
					for (int k = 0; k < 5; k++)//goes through rooms at swap's time
					{
						if (schedule[l][n].getProctor().equals(schedule[time][k].getProctor())==true)//if the proctor at schedule[l][n] is proctoring at time slot "time"
						{
							swapRowClear = false;//swapRowClear = false;
						}//if
					}//inner for
					if (swapRowClear == true)
					{
						hold = n;//holder
						break;//breaks out of n for loop
					}//if
				}//outer for
			}//if
			if (tarRowClear ==true && swapRowClear==true)//if both sessions that will be swapped can be swapped
			{
				// swapping process
				Session temp = swap;
				schedule[time][room] = schedule[l][hold];
				schedule[l][hold] = temp;
				break;//breaks out of l for loop
			}//if
		}//for
	}//swap 
	
	/* This method is meant to schedule a Person object for five sessions for each time slot.
	 * I designed it so that it would go through each time slot and find a session for each time slot. 
	 * In order to do it, I had a for loop that would go through each choice the person had; within that, 
	 * I had another for loop that went through each of the seminars within that specific time period. 
	 * If the choice was 0(the person didn't fill the form out), I automatically assigned them a session.
	 * Otherwise, if a person selected a seminar and the seminar can have more people, they are assigned to 
	 * that seminar. */ 
	public void schedulePer(Person per)
	{
		Sorter sorter = new Sorter();//creating a sorter object
		/* declaring variables */ 
		int time;
		int choice;
		boolean alrTaken;
		int counter = 0;
		int id;
		int seminar;
		int lastSched = -1;
		Session sesHold = new Session(" ", 0, " ");
		for (int i = 0; i < 5; i++)//goes through each different time slot
		{
			timeSlot = new ArrayList<Session>(Arrays.asList(schedule[i]));//timeSlot is an ArrayList of all the sessions going on during time i
			timeSlot = sorter.selectionSize(timeSlot);//sorts based on num of people in each session from greatest num to least
			time = i;//time is i
			for (int j = 0; j < 5; j++)//going through 1st choice, then 2nd choice, then 3rd choice, etc.
			{
				choice = per.getChoice(j);//choice is the persons jth choice
				for (int l = 0; l < 5; l++)//going through the seminars in this time slot
				{
					sesHold = timeSlot.get(l);
					id = sesHold.getID();
					if (choice==0)//if they didn't fill out the form
					{
						if (lastSched != time)//making sure that they don't have another session scheduled for this time
						{
							Schedule.forceAssign(per, time, timeSlot);//force assigns
							l=5;//breaks out of the seminar loop
							j=5;//breaks out of the choices loop
							lastSched = time;//lastSched is i so it doesn't schedule another session for this time
						}//if
					}//if
					else if (sesHold.canAdd() == true && id == choice)//if session can have more people and if it's a choice
					{
						if (lastSched != time)//refer above
						{
							alrTaken = Schedule.alrTaken(per, id);//sets the boolean var alrTaken to the result of the call to the function
							if (alrTaken == false)//if the person hasn't already taken the seminar earlier
							{
								sesHold.addPerson(per);//adds the person to the seminar
								per.setFinal(time, sesHold);//adds the seminar to the person's schedule array
								per.upNumSes();//increases the number of sessions the person is taking
								j = 5;//refer above
								l = 5;//refer above
								lastSched = time;//refer above
							}//if
						}//if
					}//elif
					else if (sesHold.canAdd() == true && (id != choice && (j == 4&& l ==4)))//if a session can add more and that session 
																				 //isn't a preference and they are out choices and seminars to choose from
					{
						if (lastSched != time)//refer above
						{
							Schedule.forceAssign(per, time, timeSlot);//assigns them to a session
							l=5;//refer above
							j=5;//refer above
							lastSched = time;//refer above
						}//if
					}//elif
				}//inner for(l)
			}//middle for(j)
		}//outermost for(i)
	}//schedulePer
	/* This method is meant to automatically assign a person to a session. This is done by
	 * first getting an ArrayList of all the sessions during the time that the person needs scheduling.
	 * I created a counter that starts at 4 and sorted the ArrayList by num of people attending it. 
	 * Using a while loop that continues looping until counter is less than 0, I initialized sesHold to the 
	 * session at index counter because that session will always have the least number of people. I then made
	 * sure the person had never attended that session before by using alrTaken and making sure people can be added to it.
	 * If that was the case, they were assigned to the session and I broke out of the while loop by initializing counter to -1. 
	 * This is quranteed to work since there's a maximum of 80 total students(16 students per session*5 sessions) and there 
	 * are a total of 74 students. */
	public static void forceAssign(Person per, int time, ArrayList<Session> slotTime)
	{
		Sorter sorter = new Sorter();
		int counter = 4;
		boolean alrTaken = false;
		Session sesHold;
		slotTime = sorter.selectionSize(slotTime);//sorting the arraylist
		int id;
		while(counter >= 0)//while counter is above 0;
		{
			sesHold = slotTime.get(counter);//setting sesHold to the session at index counter
			id = sesHold.getID();//getting the ID of that session
			alrTaken = Schedule.alrTaken(per, id);//refer above
			if (alrTaken == false && sesHold.canAdd() == true)//if they haven't taken it already and more people can be added
			{
				sesHold.addPerson(per);//refer above
				per.setFinal(time, sesHold);//refer above
				counter = -1;//breaking out of the while loop
				per.upNumSes();//refer above
			}//if
			else
			{counter--;}//decreases by 1
		}//while
	}//forceAssign
	
	/* This method is used to check if a person has already taken the given seminar or not.
	 *  id is the id of the seminar that the person wants to attend and to check if the session has been
	 * taken or not, a for loop goes through the schedule of the person by using getFinal(index). */
	public static boolean alrTaken(Person per, int id)
	{
		for (int k = 0; k < 5; k ++)//goes through the schedule
		{
			if (per.getFinal(k).getID() == id)//checks if they are the same seminar, if they are, it returns true. Otherwise it returns false
				return true;
		}//for
		return false;
	}//alrTaken
	
	public String toString()
	{
		String finalStr="";
		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				finalStr+=schedule[i][j].getSize()+" ";
			}//for
			finalStr+="\n";
		}//for
		return finalStr;
	}//toString
}//Schedule
