package exercise.first;

class Board {

	private static final int FORMAT_NUMBER = 3;
	private static final int SIZE = 3;
	private State board[][];

	public Board() {
		this.board = new State[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				board[i][j] = State.EMPTY;
			}
		}
	}

	public State[][] getBoard() {
		return board;
	}

	public void setBoard(State[][] board) {
		this.board = board;
	}

	public boolean isMoveValid(int x, int y) {
		if (x >= 0 && x < SIZE && y >= 0 && y < SIZE) {
			if (board[x][y] != State.X && board[x][y] != State.O) {
				return true;
			}
		}
		return false;
	}

	public boolean isThereEmptySpaceOnTheBoard() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (board[i][j] == State.EMPTY) {
					return true;
				}
			}
		}
		return false;
	}

	public State[][] makeMove(int x, int y, State usedSymobl) {
		State[][] updatedBoard = new State[SIZE][SIZE];
		updatedBoard = board;
		updatedBoard[x][y] = usedSymobl;
		return updatedBoard;
	}

	public void print() {
		System.out.println("\t BOARD");
		System.out.print("  x/y   ");
		for (int i = 0; i < SIZE; i++) {
			System.out.print(i + "  ");
		}
		System.out.println();
		System.out.print("\t");
		for (int i = 0; i < SIZE * FORMAT_NUMBER - 1; i++) {
			System.out.print("-");
		}
		System.out.println();
		for (int i = 0; i < SIZE; i++) {
			System.out.print("   " + i + "\t");
			for (int j = 0; j < SIZE; j++) {
				System.out.print(board[i][j].getSymbol() + "  ");
			}
			System.out.println("");
		}
		System.out.print("\t");
		// Format of the table
		for (int i = 0; i < SIZE * FORMAT_NUMBER - 1; i++) {
			System.out.print("-");
		}
		System.out.println();
	}
}
