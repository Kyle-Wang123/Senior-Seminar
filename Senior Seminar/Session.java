	/**Kyle Wang
	* Session Class 
	* This is the session class and it contains the SIZE variable which is always 16, the name of the proctor,
	* the session ID, the name of the session, the popularity, and the ArrayList of the finalized attendees. 
	* It also constains various methods including the needed getter and setters, as well as two 
	* toString methods. 
	**/
import java.util.ArrayList;
public class Session
{
	/* intializing the private instance variables */
	private final int SIZE = 16;
	private String proctor;
	private int sessionID;
	private String sessionName;
	private int popular = 0;
	private ArrayList<Person> finalList = new ArrayList<Person>();
	/* Constructor which initializes the proctor, session ID, and session name */ 
	public Session(String proctor,int  id,String name)
	{
		this.proctor = proctor;
		sessionID = id;
		sessionName = name;
	}//Session
	/* a method that returns true if another person can be added */
	public boolean canAdd()
	{ 
		if (finalList.size() + 1 <= SIZE)//if the current size of the list plus one(simulating adding another person) is <= SIZE(16)
			return true;//returns true if that is the case
		return false;//returns false
	}//canAdd
	/* getter that returns the popularity */
	public int getPop()
	{return popular;}
	/* getter that returns the proctor name */
	public String getProctor()
	{return proctor;}
	/* getter that returns the session name */
	public String getName()
	{return sessionName;}
	/* getter that returns the list of all the Person objects */
	public ArrayList<Person> getFinal()
	{return finalList;}
	/* getter that returns the ID */
	public int getID()
	{return sessionID;}
	/* getter that returns the size of final list of attendees */
	public int getSize()
	{return finalList.size();}
	/* adds a person object to the list of attendees */ 
	public void addPerson(Person per)
	{finalList.add(per);}
	/* increases the popularity of the session by num */
	public void upPop(int num)
	{popular+= num;}
	/* toString that returns the session name */
	public String toString()
	{return sessionName;}
	/* toString that returns the session name, proctor, and all the students enrolled */
	public String toString(int i)
	{
		String str = sessionName+"\nProctor: "+proctor+"\nStudents Enrolled:\n";
		for (int j = 0; j < finalList.size(); j++)//adds each sttudents' name in the roster to the String
			str+= (j+1)+" "+finalList.get(j);
		return str;//returns the string
	}//toString
}//Session
