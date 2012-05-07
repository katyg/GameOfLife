package life.board;

public class GameBoardImpl implements GameBoard {

	private int[][] initBoard;
	private int[][] nextGen;
	private int width;
	private int height;
	private int numCells;
	private final Integer alive = 1;
	private final Integer dead = 0;

	public GameBoardImpl(int numRow, int numCol) {
		this.height = numRow;
		this.width = numCol;
		this.numCells = this.height * this.width;

		this.initBoard = new int[this.height][this.width];
	}

	// used for testing
	protected GameBoardImpl(int[][] testBoard, int numRow, int numCol) {
		this.initBoard = testBoard;
		this.height = numRow;
		this.width = numCol;
		this.numCells = this.height * this.width;
	}

	public void setBoard(String data) throws Exception {

		if (validInitData(data)) {
			char[] dataArray = data.toCharArray();
			int counter = 0;
			int col;
			for (int row = 0; row < this.height; row++) {
				for (col = 0; col < this.width; col++) {
					String value = String.valueOf(dataArray[counter]);
					setCell(row, col, Integer.valueOf(value), this.initBoard);
					counter++;
				}
			}
		}
	}

	public GameBoard getNextGeneration() {
		this.nextGen = new int[this.height][this.width];
		
		for (int row = 0; row < this.height; row++) {
			for (int col = 0; col < this.width; col++) {
				Integer numLiveNeighbors = getLiveNeighbors(row, col);
				Integer cell = getCell(row, col);
				if (cell.equals(this.alive)) {
					if (numLiveNeighbors < 2)
						setCell(row, col, this.dead, this.nextGen);
					if (numLiveNeighbors.equals(2))
						setCell(row, col, this.alive, this.nextGen);
					if (numLiveNeighbors.equals(3))
						setCell(row, col, this.alive, this.nextGen);
				}
				if (cell.equals(this.dead)) {
					if (numLiveNeighbors.equals(3))
						setCell(row, col, this.alive, this.nextGen);
				}
			}
		}
		return new GameBoardImpl(this.nextGen, this.height, this.width);
	}
	
	public String printGameBoard()
	{
		StringBuilder builder = new StringBuilder();
		for (int row = 0; row < this.height; row++) {
			for (int col = 0; col < this.width; col++) {
				builder.append(String.valueOf(getCell(row, col)));
			}
			builder.append("\n");
		}

		return builder.toString();
	}
	private void setCell(int row, int col, int value, int[][] board) {
		board[row][col] = value;
	}

	private int getCell(int row, int col) {
		return initBoard[row][col];
	}

	private Boolean validInitData(String data) throws Exception {
		// Test for len to match game board size
		Integer strLen = data.length();
		if (!strLen.equals(this.numCells)) {
			throw new Exception("Input data does not match board size of "
					+ this.height + "x" + this.width);
		}

		// Test for alpha characters and 0 and 1
		if(data.matches("[0-1]*[a-zA-Z]+[0-1]*"))
		{
			throw new Exception("Input data must only consist of 1s and 0s");
		}
		if(data.matches("[0-1]*[2-9]+[a-zA-Z]+[0-1]*"))
		{
			throw new Exception("Input data must only consist of 1s and 0s");
		}

		return true;
	}

	protected Integer getLiveNeighbors(int row, int col) {
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

	private Boolean validRow(int row) {
		Boolean valid = false;
		if (row >= 0 && row < this.height)
			valid = true;

		return valid;
	}

	private Boolean validCol(int col) {
		Boolean valid = false;
		if (col >= 0 && col < this.width)
			valid = true;

		return valid;
	}

	private Boolean validCell(int row, int col) {
		return validRow(row) && validCol(col);
	}

	private int getNeighbor(int row, int col) {
		int neighbor = 0;
		if (validCell(row, col))
			neighbor = getCell(row, col);
		return neighbor;
	}

	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		for (int row = 0; row < this.height; row++) {
			for (int col = 0; col < this.width; col++) {
				builder.append(String.valueOf(getCell(row, col)));
			}
		}

		return builder.toString();
	}
}
