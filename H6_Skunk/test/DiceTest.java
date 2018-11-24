import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DiceTest {
	@Test
	public void test_dice_get_first_roll() {
		Die die1 = new Die(new int[] {1,2,3});
		Die die2 = new Die(new int[] {2,3,4});

		Dice testDice = new Dice(die1, die2);
		testDice.roll();
		assertEquals(testDice.getLastRoll(), 3);
	}
	
	@Test
	public void test_dice_get_random_first_roll() {
		Dice testDice = new Dice();
		assertTrue("First roll within valid range", 2<=testDice.getLastRoll() && testDice.getLastRoll()<=12);
	}
	
	@Test
	public void test_to_string() {
		Die die1 = new Die(new int[] {1,2,3});
		Die die2 = new Die(new int[] {2,3,4});

		Dice testDice = new Dice(die1, die2);
		testDice.roll();
		assertEquals(testDice.toString(), "Dice with last roll: 3 => 1 + 2");
	}
}
