/**Kyle Wang
	* Session Class */
import java.util.ArrayList;
public class Session
{
	private final int SIZE = 16;
	private String proctor;
	private int sessionID;
	private String sessionName;
	ArrayList<Person> sessionList = new ArrayList<Person>();
	public Session(String proctor,int  id,String name)
	{
		this.proctor = proctor;
		sessionID = id;
		sessionName = name;
	}//Session
	
	public void addPerson(Person per)
	{
		if (sessionList.size() + 1 <= SIZE)
		{
			sessionList.add(per);
		}//if
	}//addPerson
	
	public String toString()
	{
		return proctor+" "+sessionName+" "+sessionID+" ";
	}//toString
}//Session
