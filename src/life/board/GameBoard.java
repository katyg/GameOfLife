package life.board;

public interface GameBoard {
	
	public void setBoard(String data) throws Exception;
	
	public GameBoard getNextGeneration();
	
	public String printGameBoard();
	
}
