package moonaframework.hallway;

import moonaframework.base.Moona;

public class NativeAccessException extends RuntimeException {
	
	private static final long serialVersionUID = -7;
	
	public NativeAccessException() {
		super((Moona.enableHallwayAccess.evaluate()) ? "Moona needs to be active in order to access native features." :
				"In order to access the Moona Native Interface you need to enable the Moona.enableInterludeInterface setting.");
	}

}
