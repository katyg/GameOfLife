package life;

import life.board.GameBoard;
import life.board.GameBoardImpl;
import life.controller.GameController;
import life.controller.SimpleGameController;

/**
 * The purpose of this class is to serve up implementations of 
 * the various classes need for the game of life
 * @author Katy Groves
 *
 */
public class GameFactory {
	
	/**
	 * Get a GameBoard implementation with the given dimension
	 * @param row - number of rows on the gameboard
	 * @param col - number of column on the gameboard
	 * @return - GameBoard implementation
	 */
	public GameBoard getGameBoard(int row, int col)
	{
		return new GameBoardImpl(row, col);
	}

	/**
	 * Get a GameController implementation.
	 * @return GameController implementation
	 */
	public GameController getGameController()
	{
		 return new SimpleGameController();
	}
}
