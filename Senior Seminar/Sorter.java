//Kyle Wang

import java.util.ArrayList;

public class Sorter
{
	public ArrayList<Session> selection(ArrayList<Session> sessionList)
	{
		int arrLength = sessionList.size();
		int max;
		for (int i = 0; i <= arrLength-1; i++)
		{
			max = i;
			for (int j = i + 1; j < arrLength; j++)
			{
				if (sessionList.get(j).getPop() > sessionList.get(max).getPop())
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
