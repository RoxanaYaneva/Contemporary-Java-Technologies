package exercise.first;

//import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {

		String nameOne, nameTwo;
		State symbolOne, symbolTwo;

		System.out.println("Player one should enter his/her name: ");
		nameOne = in.nextLine();
		System.out.println("Player one should enter his/her symbol (X or O):");
		String firstState = in.nextLine();
		while (!firstState.equals("X") && !firstState.equals("O")) {
			System.out.println("Symbol should be either X or O. Try again... ");
			firstState = in.nextLine();
		}

		if (firstState.equals("X")) {
			symbolOne = State.X;
			symbolTwo = State.O;
		} else {
			symbolOne = State.O;
			symbolTwo = State.X;
		}

		Player playerOne = new Player(nameOne, symbolOne);

		System.out.println("Player two should enter his/her name: ");
		nameTwo = in.nextLine();
		System.out.println("Player two plays with symbol " + symbolTwo);

		Player playerTwo = new Player(nameTwo, symbolTwo);

		Board board = new Board();
		board.print();

		Player firstPlayer;
		Player secondPlayer;

		if (playerOne.getUsedSymbol() == State.X) {
			firstPlayer = playerOne;
			secondPlayer = playerTwo;
		} else {
			firstPlayer = playerTwo;
			secondPlayer = playerOne;
		}

		System.out.println("\n* Player with X symbol starts first. *\n");

		while (board.isThereEmptySpaceOnTheBoard()) {
			int x, y;
			do {
				System.out.println("Player " + firstPlayer.getName() + " should enter his/her move.");
				System.out.print("Enter x : ");
				x = in.nextInt();
				System.out.print("Enter y : ");
				y = in.nextInt();
			} while (!board.isMoveValid(x, y));
			board.setBoard(board.makeMove(x, y, firstPlayer.getUsedSymbol()));
			board.print();
			if (firstPlayer.isWinner(x, y, board)) {
				firstPlayer.printWinner();
				break;
			}
			// since the first player makes the last move, we should check if
			// the board is full before asking the second player for his move
			if (!board.isThereEmptySpaceOnTheBoard()) {
				System.out.println("Draw!");
				break;
			}
			do {
				System.out.println("Player " + secondPlayer.getName() + " should enter his/her move.");
				System.out.print("Enter x: ");
				x = in.nextInt();
				System.out.print("Enter y: ");
				y = in.nextInt();

				// Player two is the computer
				// Random computer = new Random();
				// System.out.println("Computer's move.");
				// x = computer.nextInt(3);
				// y = computer.nextInt(3);

			} while (!board.isMoveValid(x, y));

			board.setBoard(board.makeMove(x, y, secondPlayer.getUsedSymbol()));
			board.print();
			if (secondPlayer.isWinner(x, y, board)) {
				secondPlayer.printWinner();
				break;
			}
		}
	}
}
