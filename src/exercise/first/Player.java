package exercise.first;

class Player {

	private String name;
	private State usedSymbol;

	public Player(String name, State usedSymbol) {
		this.name = name;
		this.usedSymbol = usedSymbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public State getUsedSymbol() {
		return usedSymbol;
	}

	public void setUsedSymbol(State usedSymbol) {
		this.usedSymbol = usedSymbol;
	}

	public boolean isWinner(int x, int y, Board board) {

		State[][] checkBoard = board.getBoard();

		// looking on the row (we increase the index of the columns)
		for (int i = 0; i < checkBoard.length; i++) {
			if (checkBoard[x][i] != usedSymbol)
				break;
			if (i == checkBoard.length - 1) {
				return true;
			}
		}

		// looking on the column (we increase the index of the rows)
		for (int i = 0; i < checkBoard.length; i++) {
			if (checkBoard[i][y] != usedSymbol)
				break;
			if (i == checkBoard.length - 1) {
				return true;
			}
		}

		// looking on the diagonal (we increase the columns)
		if (x == y) {
			for (int i = 0; i < checkBoard.length; i++) {
				if (checkBoard[i][i] != usedSymbol)
					break;
				if (i == checkBoard.length - 1) {
					return true;
				}
			}
		}

		// looking on the secondary diagonal
		for (int i = 0; i < checkBoard.length; i++) {
			if (checkBoard[i][(checkBoard.length - 1) - i] != usedSymbol)
				break;
			if (i == checkBoard.length - 1) {
				return true;
			}
		}
		return false;
	}

	public void printWinner() {
		System.out.println("*** The winner is " + name + " ***");
	}
}
