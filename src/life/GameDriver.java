package life;

import life.controller.GameController;

/**
 * This class holds the main and is responsible for
 * starting up the "Game of Life"
 * @author Katy Groves
 *
 */
public class GameDriver {

	// Main function that run the game
	public static void main(String[] args) {

		GameFactory factory = new GameFactory();
		GameController controller = factory.getGameController();
		controller.playGame();

	}
}
