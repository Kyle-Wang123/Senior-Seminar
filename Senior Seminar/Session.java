	/**Kyle Wang
	* Session Class */
import java.util.ArrayList;
public class Session
{
	private final int SIZE = 16;
	private String proctor;
	private int sessionID;
	private String sessionName;
	private boolean hasSecond;
	private int popular = 0;
	private ArrayList<Person> finalList = new ArrayList<Person>();
	private ArrayList<Person> waitlist = new ArrayList<Person>();
	public Session(String proctor,int  id,String name, boolean second)
	{
		this.proctor = proctor;
		sessionID = id;
		sessionName = name;
		hasSecond = second;
	}//Session
	
	public boolean canAdd()
	{ 
		if (finalList.size() + 1 <= SIZE)
			return true;
		return false;
	}//canAdd
	
	public int getPop()
	{return popular;}
	
	public boolean getSecond()
	{return hasSecond;}
	
	public String getProctor()
	{return proctor;}
	
	public ArrayList<Person> getFinal()
	{return finalList;}
	
	public ArrayList<Person> getWaitlist()
	{return waitlist;}
	
	public int getID()
	{return sessionID;}
	
	public int getSize()
	{return finalList.size();}
	
	public ArrayList<Person> getFinalList()
	{return finalList;}
	
	public void second(boolean second)
	{hasSecond = second;}
	
	public void addPerson(Person per)
	{finalList.add(per);}//addPerson
	
	public void addWait(Person per)
	{waitlist.add(per);}
	
	public void upPop(int num)
	{popular+= num;}
	
	public void changeSecond(boolean second)
	{hasSecond = second;}
	
	public String toString()
	{
		return sessionID+" "+proctor+" ";//+finalList;
	}//toString
}//Session
