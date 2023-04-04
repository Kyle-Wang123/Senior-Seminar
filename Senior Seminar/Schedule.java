//Kyle Wang
import java.util.ArrayList;

public class Schedule
{
	private static ArrayList<Session> sessionList = new ArrayList<Session>();
	private static ArrayList<Person> reassignList = new ArrayList<Person>();
	private static ArrayList<Person> attendeesList = new ArrayList<Person>();
	private static Session[][] schedule = new Session[5][5];
	public Schedule(ArrayList<Session> sessionList, ArrayList<Person> reassignList, ArrayList<Person> attendeesList)
	{
		this.sessionList = sessionList;
		this.reassignList = reassignList;
		this.attendeesList = attendeesList;
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
					if (schedule[r][c].getProctor().equals(schedule[r][n].getProctor()))
					{
						Schedule.swap(r, c);
						break;
					}
				}//for
			}//inner for
		}//outer for
	}//mostPop	
				
				
	public static void swap(int room, int time)
	{
		Session swap = schedule[time][room];
		for (int l = 0; l < 5; l++)
		{
			boolean tarRowClear = true;
			boolean swapRowClear = true;
			int hold = 0;
			for (int m = 0; m < 5; m++)
			{
				if (swap.getProctor().equals(schedule[l][m].getProctor()))
					tarRowClear = false;
			}//for
			
			if (tarRowClear)
			{
				for(int n = 0; n < 5; n++)
				{
					swapRowClear = (schedule[time][n].getProctor().equals(schedule[l][n].getProctor())) ? (true) : (false);
					hold = n;
					n = 5;
				}//for
			}//if
			
			if (tarRowClear && swapRowClear)
			{
				Session temp = swap;
				swap = schedule[l][hold];
				schedule[l][hold] = swap;
			}//if
		}//for
	}//swap 
	
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
	
	public void schedulePer(Person per)
	{
		
}//Schedule
