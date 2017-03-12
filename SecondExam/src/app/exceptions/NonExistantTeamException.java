package app.exceptions;

public class NonExistantTeamException extends Exception {

	private static final long serialVersionUID = 1L;

	public NonExistantTeamException(String message) {
		super(message);
	}
}
