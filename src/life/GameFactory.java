package life;

import life.board.GameBoard;
import life.board.GameBoardImpl;
import life.controller.GameController;
import life.controller.SimpleGameController;

/**
 * The purpose of this class is to serve up implementations of 
 * the various classes need for the game of life
 * @author Kate
 *
 */
public class GameFactory {
	
	public GameBoard getGameBoard(int row, int col)
	{
		return new GameBoardImpl(row, col);
	}

	public GameController getGameController()
	{
		 return new SimpleGameController();
	}
}
