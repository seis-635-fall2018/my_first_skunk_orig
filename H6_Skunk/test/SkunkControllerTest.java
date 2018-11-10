import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SkunkControllerTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testEndOfTurn() {
		int[] predictableDie = new int[2];
		predictableDie[0]= 3;
		predictableDie[1]= 5;
		
		Die die = new Die(predictableDie);
		Dice dice = new Dice(die, die);
		
		SkunkUI ui = new SkunkUI();
	SkunkController skunk = new SkunkController(ui, dice);
	skunk.run();
	assertEquals(true, skunk.endOfTurn(skunk.run()));
	
	}
	@Test
	void testScoreSkunkRoll() {
		int[] predictableDie = new int[2];
		predictableDie[0]= 1;
		predictableDie[1]= 5;
		
		Die die = new Die(predictableDie);
		Dice dice = new Dice(die, die);
		
		
		SkunkUI ui = new SkunkUI();
	SkunkController skunk = new SkunkController(ui, dice);
	
	skunk.run();
	assertNotEquals(46, skunk.kitty);
	
	}
	
	
	

}
