package tk.monkeycode.blogapi.exception;

public class ModelAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ModelAlreadyExistException(String message) {
		super(message);
	}

}
