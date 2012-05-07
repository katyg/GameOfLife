package life.controller;

import java.io.Console;
import java.io.PrintWriter;

import life.board.GameBoard;
import life.board.GameBoardImpl;

public class GameController {

	// get user input

	// loop through game board and apply rules

	// print new board to screen

	public static void main(String[] args) {

		Console con = System.console();
		if (con == null) {
			System.err.println("No console.");
			System.exit(1);
		}
		GameController controller = new GameController();
		GameBoard initialBoard = controller.getInitialBoard(con);

		if (initialBoard != null) {
			GameBoard nextGen = initialBoard.getNextGeneration();
			controller.printNextGen(con, nextGen);
		}

	}

	private GameBoard getInitialBoard(Console con) {

		PrintWriter writer = con.writer();
		writer.print("--- Welcome to the Game of Life ---\n");
		writer.print("1 = Alive and 0 = Dead \n");
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

		writer.close();
		return board;
	}

	private void printNextGen(Console con, GameBoard nextGen) {
		PrintWriter writer = con.writer();
		writer.print("--- Next Generation ---\n");
		writer.print(nextGen.printGameBoard());
		writer.flush();
		writer.close();
	}

	protected GameBoard getNextGeneration(GameBoard board) {
		return board.getNextGeneration();
	}
}
