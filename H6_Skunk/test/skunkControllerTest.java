import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class skunkControllerTest {

	@BeforeEach
	void setUp() throws Exception
	{
		
	}
	

	@Test
	public void test_skunk_controller_score_skunk_turn() //new skunk controller test
	{
		SkunkController sc = new SkunkController(new SkunkUI());
		
		//refactored: new Player created with name as part of the player object
		String playerName = "testPlayer";
		Player activePlayer = new Player(playerName);
		activePlayer.setRollScore(0);
		Die d1 = new Die(new int[] {1});
		Die d2 = new Die(new int[] {1});
		Dice skunkDice = new Dice(d1,d2);
		
		skunkDice.roll(); //double skunk
		
		sc.scoreRoll(activePlayer, 4); //added active player as parameter
		
		assertEquals(activePlayer.getNumberChips(),46);
	}
	
	//added JUnit test to get player name
	@Test
	public void test_get_CurrentPlayer_name() 
	{
		String playerName = "testPlayer";
		Player activePlayer = new Player(playerName);
		
		assertEquals(activePlayer.getName(), "testPlayer");
	}
	
	//added JUnit test to obtain the index of the active player
	@Test
	public void test_get_Active_Player_index() 
	{
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Player1"));
		players.add(new Player("Player2"));
		players.add(new Player("Player3"));

		int activePlayerIndex = 0;
		for (int i = 0; i < players.size(); i++)
		{
			assertEquals(players.indexOf(players.get(i)), activePlayerIndex);
			activePlayerIndex++;
		}
	}

}
