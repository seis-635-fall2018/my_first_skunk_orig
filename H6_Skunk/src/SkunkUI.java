import edu.princeton.cs.introcs.*;

public class SkunkUI implements UI
{

	public SkunkDomain skunkDomain;

	public void setDomain(SkunkDomain skunkDomain)
	{
		this.skunkDomain = skunkDomain;

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
	
	public void showEndOfTurn() {
		println("Scoreboard: ");
		println("Kitty has " + skunkDomain.kitty);
		println("player name -- turn score -- round score -- chips");
		println("-----------------------");

		for (int i = 0; i < skunkDomain.numberOfPlayers; i++)
		{
			println(skunkDomain.playerNames[i] + " -- " + skunkDomain.players.get(i).turnScore + " -- " + skunkDomain.players.get(i).roundScore
					+ " -- " + skunkDomain.players.get(i).getNumberChips());
		}
		println("-----------------------");
	}

}
