package app.exceptions;

public class AlreadyExisitingTeamException extends Exception {

	private static final long serialVersionUID = 1L;

	public AlreadyExisitingTeamException(String message) {
		super(message);
	}

}
