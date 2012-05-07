package life.controller;

import java.io.Console;
import java.io.PrintWriter;

import life.board.GameBoard;
import life.board.GameBoardImpl;

/**
 * This Class drives the "Game of Life" game.  It is responsible for
 * getting the game board from the user and then generating 
 * the next generation output board.
 * 
 * @author Katy Groves
 *
 */
public class GameController {

	// Main function that run the game
	public static void main(String[] args) {
		Console con = System.console();
		if (con == null) {
			System.err.println("No console.");
			System.exit(1);
		}
		PrintWriter writer = con.writer();

		GameController controller = new GameController();
		controller.playGame(con, writer);
		
		writer.close();

	}

	//This method drives the game play
	private void playGame(Console con, PrintWriter writer)
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
		GameBoard board = new GameBoardImpl(5, 5);

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
		writer.print(nextGen.printGameBoard());
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
}
