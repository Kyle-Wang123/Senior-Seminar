//Kyle Wang
import java.util.ArrayList;
import java.util.Arrays;

public class Schedule
{
	private static ArrayList<Session> sessionList = new ArrayList<Session>();
	private static ArrayList<Person> attendeesList = new ArrayList<Person>();
	private static Session[][] schedule = new Session[5][5];
	public Schedule(ArrayList<Session> sessionList, ArrayList<Person> attendeesList)
	{
		this.sessionList = sessionList;
		this.attendeesList = attendeesList;
		for (int h = 0; h < 5; h++)
		{
			for(int g = 0; g < 5; g++)
			{
				schedule[h][g] = new Session(" ", 0, " ", false);
			}//inner for
		}//outer for
	}//Schedule
	
	public void mostPop()
	{
		int counter = 0;
		for (int r = 0; r < 5; r++)
		{
			for (int c = 0; c < 5; c++)
			{
				schedule[r][c] = sessionList.get(counter);
				counter++;
				for (int n = 0; n < 5; n++)
				{
					if (schedule[r][c].getProctor().equals(schedule[r][n].getProctor()) && n!=c)
					{
						//System.out.println("lol");
						Schedule.swap(c, r);
						break;
					}
				}//for
			}//inner for
		}//outer for
	}//mostPop	
				
				
	public static void swap(int room, int time)
	{
		for (int l = 0; l < 5; l++)
		{
			boolean tarRowClear = true;
			boolean swapRowClear = true;
			int hold = 0;
			for (int m = 0; m < 5; m++)
			{
				if (schedule[time][room].getProctor().equals(schedule[l][m].getProctor())==true)
				{
					tarRowClear = false;
					break;
				}//if
			}//for
			
			if (tarRowClear)
			{
				for (int n = 0; n < 5; n++)
				{
					swapRowClear = true;
					for (int k = 0; k < 5; k++)
					{
						if (schedule[l][n].getProctor().equals(schedule[time][k].getProctor()))
						{
							swapRowClear = false;
						}//if
					}//inner for
					if (swapRowClear)
					{
						hold = n;
						break;
					}//if
				}//outer for
			}//if
			if (tarRowClear && swapRowClear)
			{
				
				Session temp = schedule[time][room];
				schedule[time][room] = schedule[l][hold];
				schedule[l][hold] = temp;
				break;
			}//if
		}//for
	}//swap 
	
	public void schedulePer(Person per)
	{
		Sorter sorter = new Sorter();
		for (int i = 0; i < 5; i++)//for each different time slot
		{
			ArrayList<Session> timeSlot = new ArrayList<Session>(Arrays.asList(schedule[i]));
			timeSlot = sorter.selectionSize(timeSlot);
			Session[] finalized = new Session[5];
			int[] priorities = new int[5];
			Session sesHold = new Session(" ", 0, " ", false);
			boolean canAdd = true;
			boolean alrAdd = false;
			int counter = 0;
			int hold1 = 0;
			int hold2 = 0;
			for (int j = 0; j < 5; j++)//to get an array of the current schedule of the student
			{
				finalized[j] = per.getFinal(j);
			}//for
			for (int j = 0; j < 5; j++)//going through 1st choice, then 2nd choice, then 3rd choice, etc.
			{
				for (int l = 0; l < 5; l++)//going through the seminars in this time slot
				{
					sesHold = timeSlot.get(l);
					//System.out.println(per.getChoice(j));
					/*if (per.getChoice(j)==0)
					{
						while (true)
						{
							counter = 4;
							sesHold = timeSlot.get(counter);
							canAdd=true;
							for (int k = 0; k < 5; k ++)//checks if the person has already attended the same seminar previously
							{
								if (finalized[k].getID() == sesHold.getID())
								{
									canAdd = false;
									break;
								}//if
							}//for
							if (canAdd)
							{
								sesHold.addPerson(per);
								per.setFinal(i, sesHold);
								alrAdd = true;
								l =5;
								j=5;
								break;
							}//if
							counter--;
						}//while
					}//if */
					if (sesHold.canAdd() && sesHold.getID() == per.getChoice(j))
					{
						canAdd= true;
						for (int k = 0; k < 5; k ++)//checks if the person has already attended the same seminar previously
						{
							if (finalized[k].getID() == sesHold.getID())
							{
								canAdd = false;
								break;
							}//if
						}//innermost for
						if (canAdd)
						{
							hold1 = j;
							l=5;
							j=5;
						}//if
					}//elif
					else if(sesHold.getID() != per.getChoice(j) && l==4)
					{
						alrAdd = true;
						j=5;
						timeSlot.get(l).addPerson(per);
						per.setFinal(i,sesHold);
						break;
					}//elif
					
				}//inner middle for	
				if (canAdd)
				{
					j = hold1;
					System.out.println(sesHold.getID());
					System.out.println("choice "+per.getChoice(j)+" "+j);
					j = 5;
				}
			}//middle for
			if (canAdd && !alrAdd)
			{
				sesHold.addPerson(per);
				per.setFinal(i, sesHold);
			}//if
		}//outermost for
		
	}//schedulePer
				
	
	public String toString()
	{
		String finalStr="";
		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				finalStr+=schedule[i][j];
			}//for
			finalStr+="\n";
		}//for
		return finalStr;
	}//toString
}//Schedule
