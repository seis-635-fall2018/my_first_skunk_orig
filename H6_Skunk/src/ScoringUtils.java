
public class ScoringUtils {	

	/*
	 * Update scores and chips when any skunk is rolled
	 */
	static void scoreSkunkRoll(SkunkType type, int kitty, Player activePlayer) { //refactored: Extract Method to remove duplicate scoring code
		int chipChange = 0;
		switch(type) {
			case SINGLE_SKUNK:
				chipChange = 1;
				break;
			case DOUBLE_SKUNK:
				chipChange = 4;
				break;
			case DEUCE_SKUNK:
				chipChange = 2;
				break;
		}
		kitty += chipChange;
		activePlayer.setNumberChips(activePlayer.getNumberChips() - chipChange);
		activePlayer.setTurnScore(0);
		if(type == SkunkType.DOUBLE_SKUNK) {
			activePlayer.setRoundScore(0);
		}
	}

}
