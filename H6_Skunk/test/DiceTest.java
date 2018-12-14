import static org.junit.Assert.*;

import org.junit.Test;

public class DiceTest {

	
	@Test
	public void test_Initialization_of_predictable_dice() {
		int[] init_values1 = new int[] {1,2,3,4,5,6};
		int[] init_values2 = new int[] {6,5,4,3,2,1};
		Die die1 = new Die(init_values1);
		Die die2 = new Die(init_values2);
		Dice dice = new Dice(die1, die2);
		
		
		dice.roll();
		int value = dice.getLastRoll();
		assertEquals("value is not 7", init_values1[0] + init_values2[0], value);
	}
	
	@Test
	public void test_rolling_of_predictable_dice() {
		int[] init_values1 = new int[] {1,2,3,4,5,6};
		int[] init_values2 = new int[] {6,5,4,3,2,1};
		Die die1 = new Die(init_values1);
		Die die2 = new Die(init_values2);
		Dice dice = new Dice(die1, die2);
		
		
		for (int i = 0; i < init_values1.length; i++){
			dice.roll();
			int value = dice.getLastRoll();
			assertEquals("value is not " + (init_values1[i] + init_values2[i]), init_values1[i] + init_values2[i], value);
		}
		
		
	}
	
	@Test
	public void test_wrapping_of_predictable_dice() {
		int[] init_values1 = new int[] {1,2,3};
		int[] init_values2 = new int[] {3,2,1};
		Die die1 = new Die(init_values1);
		Die die2 = new Die(init_values2);
		Dice dice = new Dice(die1, die2);
		// 4 rolls, value should be four
		dice.roll();
		dice.roll();
		dice.roll();
		dice.roll();
		
		int value = dice.getLastRoll();
		assertEquals("value is not 4", init_values1[0] + init_values2[0], value);	
		
	}

}
