package moonaframework.hallway.dynamo;

public class EmptyGenerationException extends RuntimeException {

	private static final long serialVersionUID = -10;
	
	public EmptyGenerationException(String message) {
		super(message);
	}
}
