package life.board;

/**
 * This class is a model for the gameboard for the "Game of Life" It is
 * responsible for holding the board information and all the game play behavior
 * such as calculating the next Generation base on a set of rules.
 * 
 * @author Katy Groves
 * 
 */
public class GameBoardImpl implements GameBoard {

	// initial game board
	private int[][] initBoard;

	// next generation game board
	private int[][] nextGen;

	// dimensions of the board
	private final int width;
	private final int height;

	// total number of cell on the board
	private final int numCells;

	// Constants representing the different states on the game board
	private static final int ALIVE = 1;
	private static final int DEAD = 0;

	// Constructor
	public GameBoardImpl(int numRow, int numCol) {
		this(null, numRow, numCol);

	}

	// For internal use and testing
	protected GameBoardImpl(int[][] testBoard, int numRow, int numCol) {
		this.height = numRow;
		this.width = numCol;
		this.numCells = this.height * this.width;

		if (testBoard != null) {
			this.initBoard = testBoard;
		} else {
			this.initBoard = new int[this.height][this.width];
		}
	}

	public void setBoard(String data) throws Exception {

		// Validate input before populating board
		if (validInitData(data)) {
			char[] dataArray = data.toCharArray();
			int counter = 0;

			// loop through board setting values
			for (int row = 0; row < this.height; row++) {
				for (int col = 0; col < this.width; col++) {
					String value = String.valueOf(dataArray[counter]);
					setCell(row, col, Integer.parseInt(value), this.initBoard);
					counter++;
				}
			}
		}
	}

	public GameBoard getNextGeneration() {
		// initialize next generation board
		this.nextGen = new int[this.height][this.width];

		// loop through current board applying game rules
		for (int row = 0; row < this.height; row++) {
			for (int col = 0; col < this.width; col++) {

				// Get the current cell
				int cell = getCell(row, col, this.initBoard);

				// Get the number of live neighbors
				int numLiveNeighbors = getLiveNeighbors(row, col);

				// Get the next generation value by applying the rules
				int nextGenCell = applyRules(cell, numLiveNeighbors);

				// Set the cell in the next Generation board
				setCell(row, col, nextGenCell, this.nextGen);

			}
		}
		// returns a new gameboard of the next Generation
		return new GameBoardImpl(this.nextGen, this.height, this.width);
	}

	public int getCell(int row, int col) {
		return getCell(row, col, this.initBoard);
	}

	// This method applies the rules to the cell and returns the next generation
	// value
	private int applyRules(int cell, int numLiveNeighbors) {
		int result = DEAD;
		if (cell == ALIVE) {
			if (numLiveNeighbors < 2)
				result = DEAD;
			if (numLiveNeighbors == 2 || numLiveNeighbors == 3)
				result = ALIVE;
		}
		if (cell == DEAD) {
			if (numLiveNeighbors == 3)
				result = ALIVE;
		}
		return result;
	}

	// Sets the value on the given board at the given location.
	private void setCell(int row, int col, int value, int[][] board) {
		board[row][col] = value;
	}

	// Gets the value on the given board at the given location
	private int getCell(int row, int col, int[][] board) {
		return board[row][col];
	}

	//Validate that the string input consists of 0s and 1s only and 
	//will completely fill the board 
	private Boolean validInitData(String data) throws Exception {

		// Test to make sure data only consists of 0s and 1s.
		if (!data.matches("[0-1]+")) {
			throw new Exception("Input data must only consist of 1s and 0s");
		}
		
		// Test for length to match game board size
		int strLen = data.length();
		if (strLen != this.numCells) {
			throw new Exception("Input data does not match board size of "
					+ this.height + "x" + this.width);
		}
		//if no exception was thrown return true
		return true;
	}

	//return a count of the live neighbors around the given cell location
	protected int getLiveNeighbors(int row, int col) {
		int neighbors = 0;

		if (validRow(row) && validCol(col)) {
			neighbors += lookRight(row, col);
			neighbors += lookLeft(row, col);
			neighbors += lookUp(row, col);
			neighbors += lookDown(row, col);
			neighbors += lookUpRight(row, col);
			neighbors += lookDownRight(row, col);
			neighbors += lookUpLeft(row, col);
			neighbors += lookDownLeft(row, col);
		}

		return neighbors;
	}

	private int lookRight(int row, int col) {
		return getNeighbor(row, col + 1);
	}

	private int lookLeft(int row, int col) {
		return getNeighbor(row, col - 1);
	}

	private int lookUp(int row, int col) {
		return getNeighbor(row + 1, col);
	}

	private int lookDown(int row, int col) {
		return getNeighbor(row - 1, col);
	}

	private int lookUpRight(int row, int col) {
		return getNeighbor(row + 1, col + 1);
	}

	private int lookDownRight(int row, int col) {
		return getNeighbor(row - 1, col + 1);
	}

	private int lookUpLeft(int row, int col) {
		return getNeighbor(row + 1, col - 1);
	}

	private int lookDownLeft(int row, int col) {
		return getNeighbor(row - 1, col - 1);
	}

	// Returns true if row is valid for this board
	private Boolean validRow(int row) {
		Boolean valid = false;
		if (row >= 0 && row < this.height)
			valid = true;

		return valid;
	}

	// Returns true if col is valid for this board
	private Boolean validCol(int col) {
		Boolean valid = false;
		if (col >= 0 && col < this.width)
			valid = true;

		return valid;
	}

	// Is this a valid cell for the board
	private Boolean validCell(int row, int col) {
		return validRow(row) && validCol(col);
	}

	// Gets the cell value at the given row and col
	private int getNeighbor(int row, int col) {
		int neighbor = 0;
		// Check to make sure this cell is on the board
		if (validCell(row, col))
			neighbor = getCell(row, col, this.initBoard);
		return neighbor;
	}

	// This method has been overridden for testing purposes
	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		for (int row = 0; row < this.height; row++) {
			for (int col = 0; col < this.width; col++) {
				builder.append(String
						.valueOf(getCell(row, col, this.initBoard)));
			}
		}

		return builder.toString();
	}
}
