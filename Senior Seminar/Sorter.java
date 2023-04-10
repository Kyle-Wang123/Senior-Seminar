/**Kyle Wang
 * Sorter Class
 * This is a sorter meant to sort the a list from greatest to least by either the popularity
 * of the sessions or by the size of the list in eah session. The sorting method is selection sort.
 **/
import java.util.ArrayList;

public class Sorter
{
	/* selection sort that sorts by the popularity of each session */
	public ArrayList<Session> selection(ArrayList<Session> sessionList)
	{
		/* initializing the arrLength variable to the size of the list and declaring the max variable */
		int arrLength = sessionList.size();
		int max;
		for (int i = 0; i <= arrLength-1; i++)//outer loop which uses 'i' as what "divides" the sorted and unsorted section
		{
			max = i;//the maximum value is as at index i
			for (int j = i + 1; j < arrLength; j++)//goes through the unsorted section
			{
				if (sessionList.get(j).getPop() > sessionList.get(max).getPop())//if the a session at j is greater than a session at index max
				{
					max = j;//the new max value is at index j
				}//if
			}//for	
			if (max != i)//if the greatest value isn't at index i
			{
				/* swapping the values at index i/max and j */
				Session holder = sessionList.get(i);
				sessionList.set(i, sessionList.get(max));
				sessionList.set(max, holder);
			}//if	
		}//for
		return sessionList;//returns the sorted list
	}//selection
	
	/* This is the same method but it sorts by the size of each session so refer to the method above for my method */ 
	public ArrayList<Session> selectionSize(ArrayList<Session> sessionList)
	{
		int arrLength = sessionList.size();
		int max;
		for (int i = 0; i <= arrLength-1; i++)
		{
			max = i;
			for (int j = i + 1; j < arrLength; j++)
			{
				if (sessionList.get(j).getSize() > sessionList.get(max).getSize())
				{
					max = j;
				}//if
			}//for	
			if (max != i)
			{
				Session holder = sessionList.get(i);
				sessionList.set(i, sessionList.get(max));
				sessionList.set(max, holder);
			}//if	
		}//for
		return sessionList;
	}//selection
}
