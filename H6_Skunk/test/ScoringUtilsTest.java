import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ScoringUtilsTest {
	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void test_score_single_skunk()
	{
		int kitty = 0;
		Player active = new Player();
		active.setTurnScore(10);
		active.setRoundScore(20);
		ScoringUtils.scoreSkunkRoll(SkunkType.SINGLE_SKUNK, kitty, active);
		assertEquals(active.getTurnScore(), 0);
		assertEquals(active.getRoundScore(), 20);
		assertEquals(active.getNumberChips(), 49);
	}
	
	@Test
	public void test_score_double_skunk()
	{
		int kitty = 0;
		Player active = new Player();
		active.setTurnScore(10);
		active.setRoundScore(20);
		ScoringUtils.scoreSkunkRoll(SkunkType.DOUBLE_SKUNK, kitty, active);
		assertEquals(active.getTurnScore(), 0);
		assertEquals(active.getRoundScore(), 0);
		assertEquals(active.getNumberChips(), 46);
	}
	
	@Test
	public void test_score_deuce_skunk()
	{
		int kitty = 0;
		Player active = new Player();
		active.setTurnScore(10);
		active.setRoundScore(20);
		ScoringUtils.scoreSkunkRoll(SkunkType.DEUCE_SKUNK, kitty, active);
		assertEquals(active.getTurnScore(), 0);
		assertEquals(active.getRoundScore(), 20);
		assertEquals(active.getNumberChips(), 48);
	}
}
