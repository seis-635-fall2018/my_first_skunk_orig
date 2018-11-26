import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Categories.ExcludeCategory;

import edu.princeton.cs.introcs.StdOut;

public class DiceTest
{
	private Dice dice;

	@Before
	public void setUp() throws Exception
	{
		dice = new Dice();
		dice.roll();
	}

	

	private void setDice(int[] intA1, int[] intA2)
	{
		Die die1 = new Die(intA1);
		Die die2 = new Die(intA2);
		dice = new Dice(die1, die2);
		dice.roll();
	}
	@Test
	public void test_dice_double_skunk1() {
		setDice(new int[] {1, 2,3},
				new int[] {1,2,3});
		assertEquals(2, dice.getLastRoll());
		
	}
	@Test
	public void test_dice_double_skunk2() {
		setDice(new int[] {4, 5,6},
				new int[] {1,2,3});
		assertEquals(5, dice.getLastRoll());
		
	}
	@After
	public void tearDown() throws Exception
	{
	}

}