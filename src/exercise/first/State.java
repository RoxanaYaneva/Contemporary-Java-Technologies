package exercise.first;

public enum State {
	X('X'), O('O'), EMPTY('-');

	private char symbol;

	State(char symbol) {
		this.symbol = symbol;
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
}
