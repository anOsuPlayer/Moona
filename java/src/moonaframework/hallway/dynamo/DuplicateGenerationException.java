package moonaframework.hallway.dynamo;

public class DuplicateGenerationException extends RuntimeException {

	private static final long serialVersionUID = -9;
	
	public DuplicateGenerationException(String message) {
		super(message);
	}
	
	public DuplicateGenerationException(NativeGeneration ng) {
		super(Dynamo.generateName(ng) + " : Another NativeGeneration already points to the same native"
				+ " method(s).");
	}
}
