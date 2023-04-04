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
	
	public void second(boolean second)
	{hasSecond = second;}
	
	public boolean addPerson(Person per)
	{
		if (finalList.size() + 1 <= SIZE)
		{
			finalList.add(per);
			return true;
		}//if
		return false;
	}//addPerson
	
	public void addWait(Person per)
	{waitlist.add(per);}
	
	public void upPop()
	{popular++;}
	
	public String toString()
	{
		return sessionID+" ";
	}//toString
}//Session
