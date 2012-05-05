package life.board;

public class GameBoardImpl implements GameBoard {

	private int[][] board;

	public GameBoardImpl(int row, int col) {
		board = new int[row][col];
	}

	// used for testing
	protected GameBoardImpl() {
		int[][] testboard = { 
				{ 1, 0, 0, 0, 1 },
				{ 0, 1, 0, 0, 0 },
				{ 1, 1, 0, 0, 1 },
				{ 1, 0, 0, 1, 1 },
				{ 0, 1, 0, 0, 0 }};
		board = testboard;
	}

	private void intiBoard() {
		// TODO
	}

	@Override
	public Integer getLiveNeighbors(int row, int col) {
		int neighbors = 0;
		int nRight = lookRight(row, col);
		int nLeft = lookLeft(row, col);
		int nUp = lookUp(row, col);
		int nDown = lookDown(row, col);
		int nUpRight = lookUpRight(row, col);
		int nDownRight = lookDownRight(row, col);
		int nUpLeft = lookUpLeft(row, col);
		int nDownLeft = lookDownLeft(row, col);
		
		neighbors = nRight + nLeft + nUp + nDown + nUpRight + nDownRight + nUpLeft + nDownLeft;
		return neighbors;
	}

	private int lookRight(int row, int col) {
		int newCol = col + 1;
		return board[row][newCol];
	}

	private int lookLeft(int row, int col) {
		int newCol = col - 1;
		return board[row][newCol];
	}

	private int lookUp(int row, int col) {
		int newRow = row + 1;
		return board[newRow][col];
	}

	private int lookDown(int row, int col) {
		int newRow = row - 1;
		return board[newRow][col];
	}

	private int lookUpRight(int row, int col) {
		int newRow = row + 1;
		int newCol = col + 1;
		return board[newRow][newCol];
	}

	private int lookDownRight(int row, int col) {
		int newRow = row - 1;
		int newCol = col + 1;
		return board[newRow][newCol];
	}

	private int lookUpLeft(int row, int col) {
		int newRow = row + 1;
		int newCol = col - 1;
		return board[newRow][newCol];
	}

	private int lookDownLeft(int row, int col) {
		int newRow = row - 1;
		int newCol = col - 1;
		return board[newRow][newCol];
	}
}
