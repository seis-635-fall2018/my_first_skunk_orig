/** seperate normal die and predictable die**/

public class PredictibleDie {

	private int lastRoll;
	private int[] rolls;
	private int index_of_next_roll;

	public PredictibleDie() {
		this.roll();
	}
	
	public PredictibleDie(int[] predictable_rolls) {
		if (predictable_rolls == null)
		{
			throw new RuntimeException("null initializing int[] array");
		}

		this.rolls = predictable_rolls;
		this.index_of_next_roll = 0;
	}
	
	public int getLastRoll() {
		return this.lastRoll;
	}
	
	public void roll() {
		this.lastRoll = this.rolls[index_of_next_roll];
		index_of_next_roll++;
		if (index_of_next_roll >= this.rolls.length)
		{
			index_of_next_roll = 0; // back to start
		}
	}
	
}
