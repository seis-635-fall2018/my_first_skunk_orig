
public class Player
{
	public int rollScore;
	public int turnScore;
	public int roundScore;
	public int gameScore; // for now, same as roundScore
	public int numberChips;
	public String name;

	public Player(String name)
	{
		// Refactored:
		// player object is in charge of its name
		// a player has to be given a name when created
		this.name = name;
		this.rollScore = 0;
		this.turnScore = 0;
		this.roundScore = 0;
		this.gameScore = 0;
		this.numberChips = 50; // for now
	}

	// refactored: this method is not necessary anymore
//	public Player(int startingChipsPerPlayer)
//	{
////		this(name);
//		this.numberChips = startingChipsPerPlayer;
//	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
	}

	public void addToRollScore(int lastTotal)
	{
		rollScore += lastTotal;
	}

	public void setRollScore(int newRollScore)
	{
		this.rollScore = newRollScore;
	}

	public int getRollScore()
	{
		return this.rollScore;
	}

	public int getNumberChips()
	{
		return this.numberChips;
	}

	public void setNumberChips(int newChips)
	{
		this.numberChips = newChips;
	}

	public void setTurnScore(int newScore)
	{
		this.turnScore = newScore;
	}

	public int getTurnScore()
	{
		return this.turnScore;
	}

	public String getName()
	{
		return this.name;
	}

	public void setRoundScore(int i)
	{
		this.roundScore = i;
	}

	public int getRoundScore()
	{
		return this.roundScore;
	}


	public void setName(String name) 
	{
		this.name = name;
	}


}
