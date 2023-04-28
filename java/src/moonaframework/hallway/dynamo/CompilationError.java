package moonaframework.hallway.dynamo;

public class CompilationError extends Error {

	private static final long serialVersionUID = -10;
	
	public CompilationError(String message) {
		super(message);
	}
}
