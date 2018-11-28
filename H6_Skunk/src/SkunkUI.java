import java.util.ArrayList;

import edu.princeton.cs.introcs.*;

public class SkunkUI implements UI
{
	public int numberOfPlayers;
	public String[] playerNames;
	public ArrayList<Player> players;
	public int kitty;

	public Player activePlayer;
	public int activePlayerIndex;

	public boolean wantsToQuit;
	public boolean oneMoreRoll;

	public Dice skunkDice;

	public SkunkUI()
	{		

	}

	// refactored: deleted run() method, which is to be implemented in skunkController class

	public void scoreRoll(Player activePlayer, int chipsLost) //refactored: removed duplicate code above
	{
		kitty += chipsLost;
		activePlayer.setNumberChips(activePlayer.getNumberChips() - chipsLost);
		activePlayer.setTurnScore(0);
		if (chipsLost == 4)
				activePlayer.setRoundScore(0);
	}
	public SkunkController skunkDomain;

	public void setDomain(SkunkController skunkDomain)
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

}
