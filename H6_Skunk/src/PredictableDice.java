
public class PredictableDice {

	private int lastRoll;
	private PredictibleDie die1;
	private PredictibleDie die2;
	private int[] rolls;

	public PredictableDice()
	{

		this.die1 = new PredictibleDie(rolls);
		this.die2 = new PredictibleDie(rolls);
		this.roll();
	}
	
	public PredictableDice(PredictibleDie die1, PredictibleDie die2)
	{
		this.die1 = die1;
		this.die2 = die2;
	}
	
	public int getLastRoll()
	{
		return this.lastRoll;
	}

	public void roll()
	{
		die1.roll();
		die2.roll();
		this.lastRoll = die1.getLastRoll() + die2.getLastRoll();

	}
	
	public PredictibleDie getDie1()
	{
		return this.die1;
	}

	public PredictibleDie getDie2()
	{
		return this.die2;
	}

	public void setDie1(PredictibleDie d)
	{
		this.die1 = d;
	}

	public void setDie2(PredictibleDie d)
	{
		this.die2 = d;
	}
}
