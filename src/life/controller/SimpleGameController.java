package life.controller;

import java.io.Console;
import java.io.PrintWriter;

import life.GameFactory;
import life.board.GameBoard;

/**
 * This Class controls the "Game of Life" game.  It is responsible for
 * getting the game board from the user and then generating 
 * the next generation output board.
 * 
 * @author Katy Groves
 *
 */
public class SimpleGameController implements GameController {

	private static final int DEFAULT_WIDTH = 5;
	private static final int DEFAULT_HEIGHT = 5;
	
	@Override
	public void playGame() {
		Console con = System.console();
		if (con == null) {
			System.err.println("No console.");
			System.exit(1);
		}
		PrintWriter writer = con.writer();
		
		startGame(con, writer);
		
		writer.close();
		
	}
	//This method drives the game play
	private void startGame(Console con, PrintWriter writer)
	{
		Boolean keepPlaying = true;
		
		printGameStartupMessage(writer);
		while (keepPlaying) {
			GameBoard initialBoard = getBoard(writer, con);

			if (initialBoard != null) {
				GameBoard nextGen = initialBoard.getNextGeneration();
				printNextGen(writer, nextGen);
			}
			keepPlaying = askPlayAgain(con);
		}

	}
	
	// Print the greeting and rules to user
	private void printGameStartupMessage(PrintWriter writer) {
		writer.print("--- Welcome to the Game of Life ---\n");
		writer.print("How to play:  Please enter a game board consisting of 0s and 1s: \n");
		writer.print("1 = Alive and 0 = Dead \n");
		writer.print("Then the game engine with determine the next generation \nbased on the following rules \n");
		writer.print("------------------------------------------------------------------------\n");
		writer.print("Under-Population: Any live cell with fewer than 2 live neighbors dies\n");
		writer.print("Survival: Any live cell with 2 or 3 live neighbors lives on to the next generation\n");
		writer.print("Overcrowding: Any live cell with more than 3 live neighbors dies\n");
		writer.print("Reproduction: Any dead cell with exactly 3 neighbors becomes a live cell\n");
		writer.print("------------------------------------------------------------------------\n");
		writer.flush();
	}

	//Get the board from the user
	private GameBoard getBoard(PrintWriter writer, Console con) {

		writer.print("Please enter your 5 x 5 game board: \n");
		writer.flush();

		String row1 = con.readLine("Row1: ");
		String row2 = con.readLine("Row2: ");
		String row3 = con.readLine("Row3: ");
		String row4 = con.readLine("Row4: ");
		String row5 = con.readLine("Row5: ");

		StringBuilder builder = new StringBuilder();
		builder.append(row1);
		builder.append(row2);
		builder.append(row3);
		builder.append(row4);
		builder.append(row5);
		
		GameBoard board = new GameFactory().getGameBoard(DEFAULT_HEIGHT, DEFAULT_WIDTH);

		try {
			board.setBoard(builder.toString());
		} catch (Exception e) {
			writer.print(e.getMessage() + "\n");
			writer.flush();
			board = null;
		}

		return board;
	}

	//print the resulting next generation
	private void printNextGen(PrintWriter writer, GameBoard nextGen) {
		writer.print("--- Next Generation ---\n");
		writer.print(getGameBoardPrintString(nextGen));
		writer.flush();
	}

	//Get the next generation board from the model.
	protected GameBoard getNextGeneration(GameBoard board) {
		return board.getNextGeneration();
	}
	
	//Ask user if they would like to play again.
	private Boolean askPlayAgain(Console con)
	{
		Boolean playAgain = false;
		String playAgainStr = con.readLine("Would you like to play again? (y/n)");
		if(playAgainStr.equals("y") || playAgainStr.equals("Y"))
		{
			playAgain = true;
		}
		
		return playAgain;
	}
	
	// Build a new String to print the given gameboard to the screen
	private String getGameBoardPrintString(GameBoard board)
	{
			StringBuilder builder = new StringBuilder();
			for (int row = 0; row < DEFAULT_HEIGHT; row++) {
				for (int col = 0; col < DEFAULT_WIDTH; col++) {
					builder.append(String.valueOf(board.getCell(row, col)));
				}
				builder.append("\n");
			}

			return builder.toString();
	}
}
