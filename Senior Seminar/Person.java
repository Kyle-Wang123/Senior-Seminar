/**Kyle Wang
	* Person Class */


public class Person
{
	private String name;
	private int f1, f2,f3,f4,f5;
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
	}//Person
	
	public int getChoice(int index)
	{return choices[index];}
	
	public String toString()
	{
		return name+" "+email;
	}
}//Person
