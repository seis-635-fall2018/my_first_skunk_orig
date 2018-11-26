import edu.princeton.cs.introcs.*;

public class SkunkUI implements UI
{

	public SkunkController skunkController; //change domain to controller. 

	public void setDomain(SkunkController skunkController)
	{
		this.skunkController = skunkController;

	}

	public String promptReadAndReturn(String question)
	{
		StdOut.print(question + " => ");
		String result = StdIn.readLine();
		return result;
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

	public void print(String toPrint)
	{
		StdOut.print(toPrint);
		
	}

	public void println(String toPrint)
	{
		StdOut.println(toPrint);
		
	}

}
