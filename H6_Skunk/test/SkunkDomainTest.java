import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SkunkDomainTest
{

	@BeforeEach
	void setUp() throws Exception
	{
	}

	@Test
	void test_skunk_domain_score_skunk_turn() // new SkunkDomain test
	{
		SkunkDomain sd = new SkunkDomain(new SkunkUI(null));
		
		Player activePlayer = new Player(50);
		activePlayer.setRollScore(0);
		Die d1 = new Die(new int[] {1});
		Die d2 = new Die(new int[] {1});
		Dice skunkDice = new Dice(d1,d2);
		
		skunkDice.roll();
		
		sd.scoreSkunkRoll(activePlayer,4); // added activePlayer as a parameter
		
		assertEquals(activePlayer.getNumberChips(), 46);
	}

}
