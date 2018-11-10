import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SkunkControllerTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test_skunk_controller_score_skunk_roll() {
		SkunkController sc = new SkunkController(new SkunkUI());
		Player activePlayer = new Player(50);
		activePlayer.setTurnScore(0);
		
		sc.scoreSkunkRoll(activePlayer, 4);
		
		assertEquals(46, activePlayer.getNumberChips());
	}

	@Test
	void test_skunk_controller_get_first_player() {
		ArrayList<Player> players = new ArrayList<Player>();;
		
		SkunkController sc = new SkunkController(new SkunkUI());
		Player firstPlayer = new Player(1);
		Player secondPlayer = new Player(2);
		
		players.add(firstPlayer);
		players.add(secondPlayer);
		
		Player testPlayer = sc.getFirstPlayer(players);

		assertEquals(1, testPlayer.getNumberChips());
	}

}
