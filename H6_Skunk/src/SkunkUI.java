import java.util.ArrayList;

import edu.princeton.cs.introcs.*;

public class SkunkUI implements UI
{

	public SkunkDomain skunkDomain;

	public void setDomain(SkunkDomain skunkDomain)
	{
		this.skunkDomain = skunkDomain;

	}

	public int numberOfPlayers;
	public String[] playerNames;
	public ArrayList<Player> players;
	public int kitty;

	public Player activePlayer;
	public int activePlayerIndex;

	public boolean wantsToQuit;
	public boolean oneMoreRoll;

	public Dice skunkDice;

	public SkunkUI(SkunkUI ui)
	{	
	}

	public boolean run()
	{
		this.println("Welcome to Skunk 0.47\n");

		String numberPlayersString = promptReadAndReturn("How many players?");
		this.numberOfPlayers = Integer.parseInt(numberPlayersString);

		for (int playerNumber = 0; playerNumber < numberOfPlayers; playerNumber++)
		{
			this.print("Enter name of player " + (playerNumber + 1) + ": ");
			skunkDomain.addPlayer(StdIn.readLine());
		}
		activePlayerIndex = 0;
		activePlayer = players.get(activePlayerIndex);

		this.println("Starting game...\n");
		boolean gameNotOver = true;

		while (gameNotOver)
		{
			this.println("Next player is " + playerNames[activePlayerIndex] + ".");
			String wantsToRollStr = this.promptReadAndReturn("Roll? [true or false]");
			boolean wantsToRoll = Boolean.parseBoolean(wantsToRollStr);
			activePlayer.setTurnScore(0);

			while (wantsToRoll)
			{
				activePlayer.setRollScore(0);
				skunkDice.roll();
				if (skunkDice.getLastRoll() == 2)
				{
					this.println("Two Skunks! You lose the turn, the round score, plus pay 4 chips to the kitty");
					scoreSkunkRoll(activePlayer,4);
					wantsToRoll = false;
					break;
				}
				else if (skunkDice.getLastRoll() == 3)
				{
					this.println(
							"Skunks and Deuce! You lose the turn, the turn score, plus pay 2 chips to the kitty");
					scoreSkunkRoll(activePlayer,2);
					wantsToRoll = false;
					break;
				}
				else if (skunkDice.getDie1().getLastRoll() == 1 || skunkDice.getDie2().getLastRoll() == 1)
				{
					this.println("One Skunk! You lose the turn, the turn score, plus pay 1 chip to the kitty");
					scoreSkunkRoll(activePlayer,1);
					wantsToRoll = false;
					break;

				}

				activePlayer.setRollScore(skunkDice.getLastRoll());
				activePlayer.setTurnScore(activePlayer.getTurnScore() + skunkDice.getLastRoll());
				this.println(
						"Roll of " + skunkDice.toString() + ", gives new turn score of " + activePlayer.getTurnScore());

				wantsToRollStr = this.promptReadAndReturn("Roll again? [true or false]");
				wantsToRoll = Boolean.parseBoolean(wantsToRollStr);

			}

			this.println("End of turn for " + playerNames[activePlayerIndex]);
			this.println("Score for this turn is " + activePlayer.getTurnScore() + ", added to...");
			this.println("Previous round score of " + activePlayer.getRoundScore());
			activePlayer.setRoundScore(activePlayer.getRoundScore() + activePlayer.getTurnScore());
			this.println("Giving new round score of " + activePlayer.getRoundScore());

			this.println("");
			if (activePlayer.getRoundScore() >= 100)
				gameNotOver = false;

			this.println("Scoreboard: ");
			this.println("Kitty has " + kitty);
			this.println("player name -- turn score -- round score -- chips");
			this.println("-----------------------");

			for (int i = 0; i < numberOfPlayers; i++)
			{
				this.println(playerNames[i] + " -- " + players.get(i).turnScore + " -- " + players.get(i).roundScore
						+ " -- " + players.get(i).getNumberChips());
			}
			this.println("-----------------------");

			this.println("Turn passes to right...");

			activePlayerIndex = (activePlayerIndex + 1) % numberOfPlayers;
			activePlayer = players.get(activePlayerIndex);

		}
		// last round: everyone but last activePlayer gets another shot

		this.println("Last turn for all...");

		for (int i = activePlayerIndex, count = 0; count < numberOfPlayers-1; i = (i++) % numberOfPlayers, count++)
		{
			this.println("Last round for player " + playerNames[activePlayerIndex] + "...");
			activePlayer.setTurnScore(0);

			String wantsToRollStr = this.promptReadAndReturn("Roll? [true or false]");
			boolean wantsToRoll = Boolean.parseBoolean(wantsToRollStr);

			while (wantsToRoll)
			{
				skunkDice.roll();
				this.println("Roll is " + skunkDice.toString() + "\n");

				if (skunkDice.getLastRoll() == 2)
				{
					this.println("Two Skunks! You lose the turn, the turn score, plus pay 4 chips to the kitty");
					scoreSkunkRoll(activePlayer, 4);
					wantsToRoll = false;
					break;
				}
				else if (skunkDice.getLastRoll() == 3)
				{
					this.println(
							"Skunks and Deuce! You lose the turn, the turn score, plus pay 2 chips to the kitty");
					scoreSkunkRoll(activePlayer,2);
					wantsToRoll = false;

				}
				else if (skunkDice.getDie1().getLastRoll() == 1 || skunkDice.getDie2().getLastRoll() == 1)
				{
					this.println("One Skunk! You lose the turn, the turn core, plus pay 1 chip to the kitty");
					scoreSkunkRoll(activePlayer,1);
					wantsToRoll = false;
				}
				else
				{
					activePlayer.setTurnScore(activePlayer.getRollScore() + skunkDice.getLastRoll());
					this.println("Roll of " + skunkDice.toString() + ", giving new turn score of "
							+ activePlayer.getTurnScore());

					this.println("Scoreboard: ");
					this.println("Kitty has " + kitty);
					this.println("player name -- turn score -- round score -- total chips");
					this.println("-----------------------");

					for (int pNumber = 0; pNumber < numberOfPlayers; pNumber++)
					{
						this.println(playerNames[pNumber] + " -- " + players.get(pNumber).turnScore + " -- "
								+ players.get(pNumber).roundScore + " -- " + players.get(pNumber).getNumberChips());
					}
					this.println("-----------------------");

					wantsToRollStr = this.promptReadAndReturn("Roll again? [true or false]");
					wantsToRoll = Boolean.parseBoolean(wantsToRollStr);
				}

			}

			activePlayer.setTurnScore(activePlayer.getRollScore() + skunkDice.getLastRoll());
			this.println("Last roll of " + skunkDice.toString() + ", giving final round score of "
					+ activePlayer.getRollScore());

		}

		int winner = 0;
		int winnerScore = 0;

		for (int player = 0; player < numberOfPlayers; player++)
		{
			Player nextPlayer = players.get(player);
			this.println("Final round score for " + playerNames[player] + " is " + nextPlayer.getRoundScore() + ".");
			if (nextPlayer.getRoundScore() > winnerScore)
			{
				winner = player;
				winnerScore = nextPlayer.getRoundScore();
			}
		}

		this.println(
				"Round winner is " + playerNames[winner] + " with score of " + players.get(winner).getRoundScore());
		players.get(winner).setNumberChips(players.get(winner).getNumberChips() + kitty);
		this.println("\nRound winner earns " + kitty + ", finishing with " + players.get(winner).getNumberChips());

		this.println("\nFinal scoreboard for this round:");
		this.println("player name -- round score -- total chips");
		this.println("-----------------------");

		for (int pNumber = 0; pNumber < numberOfPlayers; pNumber++)
		{
			this.println(playerNames[pNumber] + " -- " + players.get(pNumber).roundScore + " -- "
					+ players.get(pNumber).getNumberChips());
		}

		this.println("-----------------------");
		return true;
	}

	// refactored: removed all repetitive code and made into a method
	// since each round is scored differently we have to add a parameter to account for each chip amount lost
	public void scoreSkunkRoll(Player activePlayer, int chipsLost)
	{
		kitty += chipsLost;
		activePlayer.setNumberChips(activePlayer.getNumberChips() - chipsLost);
		activePlayer.setTurnScore(0);
		if(chipsLost == 4)
			activePlayer.setRoundScore(0);
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

	public String promptReadAndReturn(String question)
	{
		StdOut.print(question + " => ");
		String result = StdIn.readLine();
		return result;
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
