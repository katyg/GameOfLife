package life.board;

/**
 * This interface defines the methods that make up a GameBoard
 * for the "Game of Life"
 * @author Katy Groves
 *
 */
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
	 * Get the value of cell on the gameboard at the given location
	 * @param row 
	 * @param col
	 * @return value of the cell
	 */
	public int getCell(int row, int col);
	
}
