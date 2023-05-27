package moonaframework.hallway.dynamo;

public class DynamoAccessException extends RuntimeException {

	private static final long serialVersionUID = -8;
	
	public DynamoAccessException() {
		super("Enable the Moona.enableDynamo Setting to access Dynamo.");
	}
}
