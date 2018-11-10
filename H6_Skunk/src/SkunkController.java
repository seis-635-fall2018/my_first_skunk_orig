import java.util.ArrayList;
import edu.princeton.cs.introcs.*;

public class SkunkController
{
	public SkunkUI skunkUI;
	public UI ui;
	public int numberOfPlayers;
	public ArrayList<Player> players;
	//refactored removed array of names to have player own name
	public int kitty;

	public Player activePlayer;
	public int activePlayerIndex;
	
	public final String ROLL_QUESTION = "Roll? [true or false]"; //refactored question that was being asked over and over again
	

	public boolean wantsToQuit;
	public boolean oneMoreRoll;

	public Dice skunkDice;

	public SkunkController(SkunkUI ui, Dice dice)
	{
		this.skunkUI = ui;
		this.ui = ui; // hide behind the interface UI
		
		
		this.players = new ArrayList<Player>();
		this.skunkDice = dice;
		this.wantsToQuit = false;
		this.oneMoreRoll = false;
	}

	public boolean run()
	{
		ui.println("Welcome to Skunk 0.47\n");

		String numberPlayersString = skunkUI.promptReadAndReturn("How many players?"); //take this apart and move into UI directly and make it reactive
		this.numberOfPlayers = Integer.parseInt(numberPlayersString);

		for (int playerNumber = 0; playerNumber < numberOfPlayers; playerNumber++)
		{
			ui.print("Enter name of player " + (playerNumber + 1) + ": ");
			String playerName = StdIn.readLine();
			this.players.add(new Player(50, playerName));
		}
		activePlayerIndex = 0;
		activePlayer = players.get(activePlayerIndex);

		ui.println("Starting game...\n");
		boolean gameNotOver = true;

		while (gameNotOver)
		{
			ui.println("Next player is " + players.get(activePlayerIndex).playerName + ".");
			String wantsToRollStr = ui.promptReadAndReturn(ROLL_QUESTION);
			boolean wantsToRoll = Boolean.parseBoolean(wantsToRollStr);
			activePlayer.setTurnScore(0);

			while (wantsToRoll)
			{
				activePlayer.setRollScore(0);
				skunkDice.roll();
				
				if (skunkDice.getLastRoll() == 2)
				{
					ui.println("Two Skunks! You lose the turn, the round score, plus pay 4 chips to the kitty");
					kitty += 4;
					scoreSkunkRoll(4);
					wantsToRoll = false;
					break;
				}
				else if (skunkDice.getLastRoll() == 3)
				{
					ui.println(
							"Skunks and Deuce! You lose the turn, the turn score, plus pay 2 chips to the kitty");
					kitty += 2;
					scoreSkunkRoll(2);
					wantsToRoll = false;
					break;
				}
				else if (skunkDice.getDie1().getLastRoll() == 1 || skunkDice.getDie2().getLastRoll() == 1)
				{
					ui.println("One Skunk! You lose the turn, the turn score, plus pay 1 chip to the kitty");
					kitty += 1;
					scoreSkunkRoll(1);
					wantsToRoll = false;
					break;

				}

				activePlayer.setRollScore(skunkDice.getLastRoll());
				activePlayer.setTurnScore(activePlayer.getTurnScore() + skunkDice.getLastRoll());
				ui.println(
						"Roll of " + skunkDice.toString() + ", gives new turn score of " + activePlayer.getTurnScore());

				wantsToRollStr = ui.promptReadAndReturn(ROLL_QUESTION);
				wantsToRoll = Boolean.parseBoolean(wantsToRollStr);

			}

			gameNotOver = endOfTurn(gameNotOver);

			activePlayerIndex = (activePlayerIndex + 1) % numberOfPlayers;
			activePlayer = players.get(activePlayerIndex);

		}
		// last round: everyone but last activePlayer gets another shot

		ui.println("Last turn for all...");

		for (int i = activePlayerIndex, count = 0; count < numberOfPlayers-1; i = (i++) % numberOfPlayers, count++)
		{
			ui.println("Last round for player " + players.get(activePlayerIndex).playerName + "...");
			activePlayer.setTurnScore(0);

			String wantsToRollStr = ui.promptReadAndReturn(ROLL_QUESTION);
			boolean wantsToRoll = Boolean.parseBoolean(wantsToRollStr);

			while (wantsToRoll)
			{
				skunkDice.roll();
				ui.println("Roll is " + skunkDice.toString() + "\n");

				if (skunkDice.getLastRoll() == 2)
				{
					ui.println("Two Skunks! You lose the turn, the turn score, plus pay 4 chips to the kitty");
					kitty += 4;
					scoreSkunkRoll(4);
					wantsToRoll = false;
					break;
				}
				else if (skunkDice.getLastRoll() == 3)
				{
					ui.println(
							"Skunks and Deuce! You lose the turn, the turn score, plus pay 2 chips to the kitty");
					kitty += 2;
					scoreSkunkRoll(2);
					wantsToRoll = false;

				}
				else if (skunkDice.getDie1().getLastRoll() == 1 || skunkDice.getDie2().getLastRoll() == 1)
				{
					ui.println("One Skunk! You lose the turn, the turn core, plus pay 1 chip to the kitty");
					kitty += 1;
					scoreSkunkRoll(1);
					wantsToRoll = false;
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
						ui.println(players.get(pNumber).playerName + " -- " + players.get(pNumber).turnScore + " -- "
								+ players.get(pNumber).roundScore + " -- " + players.get(pNumber).getNumberChips());
					}
					ui.println("-----------------------");

					wantsToRollStr = ui.promptReadAndReturn(ROLL_QUESTION);
					wantsToRoll = Boolean.parseBoolean(wantsToRollStr);
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
			ui.println("Final round score for " + players.get(player).playerName + " is " + nextPlayer.getRoundScore() + ".");
			if (nextPlayer.getRoundScore() > winnerScore)
			{
				winner = player;
				winnerScore = nextPlayer.getRoundScore();
			}
		}

		ui.println(
				"Round winner is " + players.get(winner).playerName + " with score of " + players.get(winner).getRoundScore());
		players.get(winner).setNumberChips(players.get(winner).getNumberChips() + kitty);
		ui.println("\nRound winner earns " + kitty + ", finishing with " + players.get(winner).getNumberChips());

		ui.println("\nFinal scoreboard for this round:");
		ui.println("player name -- round score -- total chips");
		ui.println("-----------------------");

		for (int pNumber = 0; pNumber < numberOfPlayers; pNumber++)
		{
			ui.println(players.get(pNumber).playerName + " -- " + players.get(pNumber).roundScore + " -- "
					+ players.get(pNumber).getNumberChips());
		}

		ui.println("-----------------------");
		return true;
	}

	public boolean endOfTurn(boolean gameNotOver) {
		ui.println("End of turn for " + players.get(activePlayerIndex).playerName);
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
			ui.println(players.get(i).playerName + " -- " + players.get(i).turnScore + " -- " + players.get(i).roundScore
					+ " -- " + players.get(i).getNumberChips());
		}
		ui.println("-----------------------");

		ui.println("Turn passes to right...");
		return gameNotOver;
	}

	public void scoreSkunkRoll(int chipsLost) { //refactored removed redundant duplicate code above
		activePlayer.setNumberChips(activePlayer.getNumberChips() - chipsLost);
		activePlayer.setTurnScore(0);
		activePlayer.setRoundScore(0);
		if (chipsLost == 4) {
			activePlayer.setRoundScore(0);
		}
	}
	


	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

}
