/**Kyle Wang
	* Person Class 
	* This is the Person class and it holds valuable information such as the name, an array the choices the person selected,
	* an array of the final seminars they will be attending, their email, and the number of sessions they are attending. 
	* The class also contains an assortment of getter and setter functions as well as two toString methods to help with 
	* writing the rosters. 
	**/


public class Person
{
	/* private instance variables */
	private String name;
	private Session[] schedule = new Session[5];
	private String email;
	private int[] choices = new int[5];
	private int numSessions = 0;
	/* Constructor that initializes the name, email, and array of choices */
	public Person(String name,String emailStart, int c1, int c2, int c3, int c4, int c5)
	{
		this.name = name;
		numSessions = 0;
		email = emailStart + "@countryday.net";
		choices[0] = c1;
		choices[1] = c2;
		choices[2] = c3;
		choices[3] = c4;
		choices[4] = c5;
		for (int i = 0; i < 5; i++)//fills the schedule with "blank" sessions
			schedule[i] = new Session(" ", 0," ");
	}//Person
	/* getter that returns the session choice at a certain index */
	public int getChoice(int index)
	{return choices[index];}
	/* getter that returns the session they are attending at a certain index(time) */
	public Session getFinal(int index)
	{return schedule[index];}
	/* getter that returns the entire schedule */
	public Session[] getFinalList()
	{return schedule;}
	/* getter that returns the number of sessions they are attending */
	public int getNumSes()
	{return numSessions;}
	/* setter that changes the session at the specified "time"(index) */
	public void setFinal(int index, Session session)
	{schedule[index] = session;}
	/* increases the number of sessions the person is taking by 1 */
	public void upNumSes()
	{numSessions++;}
	/* toString that returns the name */
	public String toString()
	{return name+"\n";}
	/* toString that returns the name and seminars they are takign */
	public String toString(int j)
	{
		String str = name+"\nSeminars:\n";
		for (int i = 0; i < 5; i++)//loop adds each session name to the string
		{
			str+="Block "+(i+1)+": "+schedule[i]+" \n";
		}//for
		return str+"\n";//returns the final String
	}//toString
}//Person
