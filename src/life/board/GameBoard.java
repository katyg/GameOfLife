package life.board;

public interface GameBoard {
	
	/**
	 * This method sets up the board using the passed in string
	 * @param data - The values to populate the gameboard with.
	 * @throws Exception - If the board can not be populated with 
	 * the passed in data.
	 */
	public void setBoard(String data) throws Exception;
	
	/**
	 * This method returns the gameboard for the next generation
	 * @return a new gameboard populated with the next generation.
	 */
	public GameBoard getNextGeneration();
	
	/**
	 * 
	 * @return
	 */
	public String printGameBoard();
	
}
