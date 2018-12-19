import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DiceTest {

	PredictibleDie die1;
	PredictibleDie die2;
	PredictableDice dice;
	
	@Before
	public void setUp() throws Exception
	{
		die1 = new PredictibleDie(new int[]{1,2,3});
		die2 = new PredictibleDie(new int[]{4,5,6});
		dice = new PredictableDice(die1, die2);
	}
	
	@Test
	public void testGetDie1() {
		assertEquals(die1, dice.getDie1());
	}
	
	@Test
	public void testGetDie2() {
		assertEquals(die2, dice.getDie2());
	}
	
	@Test
	public void testRoll() {
		dice.roll();
		assertEquals(5, dice.getLastRoll());
	}
	
	@Test
	public void testGetLastRoll() {
		assertEquals(die1.getLastRoll()+die2.getLastRoll(),dice.getLastRoll());
	}
	
	@Test
	public void testsetDie1() {
		PredictibleDie d = new PredictibleDie(new int[] {10,11,12});
		dice.setDie1(d);
		assertEquals(d, dice.getDie1());
	}
}
