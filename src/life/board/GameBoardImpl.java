package life.board;

public class GameBoardImpl implements GameBoard {

	private int[][] board;
	private int width;
	private int height;

	public GameBoardImpl(int numRow, int numCol) {
		this.height = numRow;
		this.width = numCol;
		this.board = new int[height][width];

	}

	// used for testing
	protected GameBoardImpl(int[][] testBoard, int numRow, int numCol) {
		this.board = testBoard;
		this.height = numRow;
		this.width = numCol;

	}

	private void intiBoard() {
		// TODO
	}

	@Override
	public Integer getLiveNeighbors(int row, int col) {
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

	private int getCell(int row, int col) {
		return board[row][col];
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

	private Boolean validCell(int row, int col)
	{
		return validRow(row) && validCol(col);
	}
	
	private int getNeighbor(int row, int col) {
		int neighbor = 0;
		if (validCell(row, col))
			neighbor = getCell(row, col);
		return neighbor;
	}
}
