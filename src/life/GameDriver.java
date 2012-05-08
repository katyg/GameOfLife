package life;

import life.controller.GameController;

public class GameDriver {

	// Main function that run the game
	public static void main(String[] args) {

		GameFactory factory = new GameFactory();
		GameController controller = factory.getGameController();
		controller.playGame();

	}
}
