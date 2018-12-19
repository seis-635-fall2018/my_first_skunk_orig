import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {
	
	Player player = new Player();
	int expectedRollScore = 10;
	int expectedChip = 50;
	int expectedTurnScore = 50;
	int expectedRoundScore = 90;

	@Test
	public void testSetRollScore() {
		player.setRollScore(expectedRollScore);
		assertEquals(expectedRollScore, player.getRollScore());
	}
	
	@Test
	public void testGetChips() {
		assertEquals(expectedChip,player.getNumberChips());
	}
	
	@Test
	public void testSetChips() {
		player.setNumberChips(70);
		assertEquals(70, player.getNumberChips());
	}
	
	@Test
	public void testGetTurnScore() {
		player.setTurnScore(expectedTurnScore);
		assertEquals(expectedTurnScore, player.getTurnScore());
	}
	
	@Test
	public void testGetRoundScore() {
		player.setRoundScore(expectedRoundScore);
		assertEquals(expectedRoundScore, player.getRoundScore());
	}
}
