/**Kyle Wang
	* Person Class */


public class Person
{
	private String name;
	private Session[] schedule = new Session[5];
	private String email;
	private int[] choices = new int[5];
	public Person(String name,String emailStart, int c1, int c2, int c3, int c4, int c5)
	{
		this.name = name;
		email = emailStart + "@countryday.net";
		choices[0] = c1;
		choices[1] = c2;
		choices[2] = c3;
		choices[3] = c4;
		choices[4] = c5;
		for (int i = 0; i < 5; i++)
			schedule[i] = new Session(" ", 0," ",false);
	}//Person
	
	public int getChoice(int index)
	{return choices[index];}
	
	public Session getFinal(int index)
	{return schedule[index];}

	public void setFinal(int index, Session session)
	{schedule[index] = session;}
	
	public String toString()
	{
		String str = name+" ";
		for (int i = 0; i < 5; i++)
		{
			str+=choices[i]+" ";
		}
		return str;
	}
}//Person
