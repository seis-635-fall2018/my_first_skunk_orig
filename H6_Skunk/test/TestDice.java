import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestDice
{
	private Dice dice;
	private Die die1, die2;
	private int[] initValuesDie1, initValuesDie2;

	@Before
	public void setUp() throws Exception
	{
 
		this.initValuesDie1 = new int[]
		{ 1, 2, 3 };
		this.die1 = new Die(initValuesDie1);

		this.initValuesDie2 = new int[]
		{ 4, 5, 6 };
		this.die2 = new Die(initValuesDie2);

		this.dice = new Dice(die1, die2);

	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void test_Init_PredictableDie()
	{

		dice.roll();
		int value = dice.getLastRoll();

		assertEquals("First Value Not 5", 5, value);

	}
	
	@Test
	public void test_dice_double_skunk2() {
		setDice(new int[] {2, 4,1},
				new int[] {3,6,5});
		assertEquals(3, dice.getLastRoll());
		
	}

	private void setDice(int[] is, int[] is2) {
		// TODO Auto-generated method stub
		
	}

}