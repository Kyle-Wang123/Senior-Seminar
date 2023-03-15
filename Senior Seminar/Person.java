/**Kyle Wang
	* Person Class */


public class Person
{
	private int c1,c2,c3,c4,c5;
	private String name;
	private int f1, f2,f3,f4,f5;
	private String email;
	public Person(String name,String emailStart, int c1, int c2, int c3, int c4, int c5)
	{
		this.name = name;
		email = emailStart + "@countryday.net";
		this.c1 = c1;
		this.c2 = c2;
		this.c3=c3;
		this.c4=c4;
		this.c5=c5;
	}//Person
	
	public String toString()
	{
		return name+" "+email+" "+c5;
	}
}//Person
