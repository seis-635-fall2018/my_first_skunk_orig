import java.util.ArrayList;
import edu.princeton.cs.introcs.*;

public class SkunkDomain
{
	/** Refactoring: make 3 methods to check if the rolls were skunk, double, or duece.
	 * 				 make 3 methods to handle the the score and the chips when skunk, double skunk and duece
	 * **/
	
	public SkunkUI skunkUI;
	public UI ui;
	public int numberOfPlayers;
	public String[] playerNames;
	public ArrayList<Player> players;
	public int kitty;

	public Player activePlayer;
	public int activePlayerIndex;

	public boolean wantsToQuit;
	public boolean oneMoreRoll;
	
	//make the wantsToRoll global so I can use it in the helper methods
	private boolean wantsToRoll;
	
	public Dice skunkDice;

	public SkunkDomain(SkunkUI ui)
	{
		this.skunkUI = ui;
		this.ui = ui; // hide behind the interface UI
		
		this.playerNames = new String[20];
		this.players = new ArrayList<Player>();
		this.skunkDice = new Dice();
		this.wantsToQuit = false;
		this.oneMoreRoll = false;
	}

	public boolean run()
	{
		ui.println("Welcome to Skunk 0.47\n");

		String numberPlayersString = skunkUI.promptReadAndReturn("How many players?");
		this.numberOfPlayers = Integer.parseInt(numberPlayersString);

		for (int playerNumber = 0; playerNumber < numberOfPlayers; playerNumber++)
		{
			ui.print("Enter name of player " + (playerNumber + 1) + ": ");
			playerNames[playerNumber] = StdIn.readLine();
			this.players.add(new Player(50));
		}
		activePlayerIndex = 0;
		activePlayer = players.get(activePlayerIndex);

		ui.println("Starting game...\n");
		boolean gameNotOver = true;
		
		while (gameNotOver)
		{
			ui.println("Next player is " + playerNames[activePlayerIndex] + ".");
			String wantsToRollStr = ui.promptReadAndReturn("Want to roll? [true or false]");
			wantsToRoll = wantsToRoll(wantsToRollStr);
			activePlayer.setTurnScore(0);

			while (wantsToRoll)
			{
				activePlayer.setRollScore(0);
				skunkDice.roll();
				if (checkDoubleSkunk())
				{
					doubleSkunk();
					break;
				}
				else if (checkDeuceSkunk())
				{
					dueceSkunk();
					break;
				}
				else if (checkSkunk())
				{
					skunk();
					break;

				}

				activePlayer.setRollScore(skunkDice.getLastRoll());
				activePlayer.setTurnScore(activePlayer.getTurnScore() + skunkDice.getLastRoll());
				ui.println(
						"Roll of " + skunkDice.toString() + ", gives new turn score of " + activePlayer.getTurnScore());

				wantsToRollStr = ui.promptReadAndReturn("Keep rolling? [true or false]");
				wantsToRoll = wantsToRoll(wantsToRollStr);

			}

			ui.println("End of turn for " + playerNames[activePlayerIndex]);
			ui.println("Score for this turn is " + activePlayer.getTurnScore() + ", added to...");
			ui.println("Previous round score of " + activePlayer.getRoundScore());
			activePlayer.setRoundScore(activePlayer.getRoundScore() + activePlayer.getTurnScore());
			ui.println("Giving new round score of " + activePlayer.getRoundScore());

			ui.println("");
			if (activePlayer.getRoundScore() >= 100)
				gameNotOver = false;

			ui.println("Scoreboard: ");
			ui.println("Kitty has " + kitty);
			ui.println("player name -- turn score -- round score -- chips");
			ui.println("-----------------------");

			for (int i = 0; i < numberOfPlayers; i++)
			{
				ui.println(playerNames[i] + " -- " + players.get(i).turnScore + " -- " + players.get(i).roundScore
						+ " -- " + players.get(i).getNumberChips());
			}
			ui.println("-----------------------");

			ui.println("Turn passes to right...");

			activePlayerIndex = (activePlayerIndex + 1) % numberOfPlayers;
			activePlayer = players.get(activePlayerIndex);

		}
		// last round: everyone but last activePlayer gets another shot

		ui.println("Last turn for all...");

		for (int i = activePlayerIndex, count = 0; count < numberOfPlayers-1; i = (i++) % numberOfPlayers, count++)
		{
			ui.println("Last round for player " + playerNames[activePlayerIndex] + "...");
			activePlayer.setTurnScore(0);

			String wantsToRollStr = ui.promptReadAndReturn("Want to roll? [true or false]");
			wantsToRoll = wantsToRoll(wantsToRollStr);

			while (wantsToRoll)
			{
				skunkDice.roll();
				ui.println("Roll is " + skunkDice.toString() + "\n");

				if (checkDoubleSkunk())
				{
					doubleSkunk();
					activePlayer.setRoundScore(0);
					break;
				}
				else if (checkDeuceSkunk())
				{	
					dueceSkunk();

				}
				else if (checkSkunk())
				{
					skunk();
					activePlayer.setRoundScore(0);
				}
				else
				{
					activePlayer.setTurnScore(activePlayer.getRollScore() + skunkDice.getLastRoll());
					ui.println("Roll of " + skunkDice.toString() + ", giving new turn score of "
							+ activePlayer.getTurnScore());

					ui.println("Scoreboard: ");
					ui.println("Kitty has " + kitty);
					ui.println("player name -- turn score -- round score -- total chips");
					ui.println("-----------------------");

					for (int pNumber = 0; pNumber < numberOfPlayers; pNumber++)
					{
						ui.println(playerNames[pNumber] + " -- " + players.get(pNumber).turnScore + " -- "
								+ players.get(pNumber).roundScore + " -- " + players.get(pNumber).getNumberChips());
					}
					ui.println("-----------------------");

					wantsToRollStr = ui.promptReadAndReturn("Keep rolling? [true or false]");
					wantsToRoll = wantsToRoll(wantsToRollStr);
				}

			}

			activePlayer.setTurnScore(activePlayer.getRollScore() + skunkDice.getLastRoll());
			ui.println("Last roll of " + skunkDice.toString() + ", giving final round score of "
					+ activePlayer.getRollScore());

		}

		int winner = 0;
		int winnerScore = 0;

		for (int player = 0; player < numberOfPlayers; player++)
		{
			Player nextPlayer = players.get(player);
			ui.println("Final round score for " + playerNames[player] + " is " + nextPlayer.getRoundScore() + ".");
			if (nextPlayer.getRoundScore() > winnerScore)
			{
				winner = player;
				winnerScore = nextPlayer.getRoundScore();
			}
		}

		ui.println(
				"Round winner is " + playerNames[winner] + " with score of " + players.get(winner).getRoundScore());
		players.get(winner).setNumberChips(players.get(winner).getNumberChips() + kitty);
		ui.println("\nRound winner earns " + kitty + ", finishing with " + players.get(winner).getNumberChips());

		ui.println("\nFinal scoreboard for this round:");
		ui.println("player name -- round score -- total chips");
		ui.println("-----------------------");

		for (int pNumber = 0; pNumber < numberOfPlayers; pNumber++)
		{
			ui.println(playerNames[pNumber] + " -- " + players.get(pNumber).roundScore + " -- "
					+ players.get(pNumber).getNumberChips());
		}

		ui.println("-----------------------");
		return true;
	}
	
	//this method is to convert a string to a boolean wantsToRoll
	private boolean wantsToRoll(String wantsToRollStr) {
		return Boolean.parseBoolean(wantsToRollStr);
		 
	}
	
	private boolean checkSkunk() {
		return skunkDice.getDie1().getLastRoll() == 1 || skunkDice.getDie2().getLastRoll() == 1;
	}
	
	private boolean checkDoubleSkunk() {
		return skunkDice.getLastRoll() == 2;
	}
	
	private boolean checkDeuceSkunk() {
		return skunkDice.getLastRoll() == 3;
	}
	
	private void skunk() {
			ui.println("One Skunk! You lose the turn, the turn score, plus pay 1 chip to the kitty");
			kitty += 1;
			activePlayer.setNumberChips(activePlayer.getNumberChips() - 1);
			activePlayer.setTurnScore(0);
			wantsToRoll = false;
	}
		
	
	private void doubleSkunk() {
			ui.println("Two Skunks! You lose the turn, the round score, plus pay 4 chips to the kitty");
			kitty += 4;
			activePlayer.setNumberChips(activePlayer.getNumberChips() - 4);
			activePlayer.setTurnScore(0);
			wantsToRoll = false;
	}
	
	private void dueceSkunk() {
			ui.println(
					"Skunks and Deuce! You lose the turn, the turn score, plus pay 2 chips to the kitty");
			kitty += 2;
			activePlayer.setNumberChips(activePlayer.getNumberChips() - 2);
			activePlayer.setTurnScore(0);
			wantsToRoll = false;
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

}
