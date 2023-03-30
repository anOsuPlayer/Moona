package moonaframework.hallway;

import moonaframework.base.Moona;

public class HallwayAccessException extends RuntimeException {
	
	private static final long serialVersionUID = -7;
	
	public HallwayAccessException() {
		super((Moona.enableHallwayAccess.evaluate()) ? "Moona needs to be active in order to access Hallway features." :
				"In order to access the Hallway Interface you need to enable the Moona.enableHallwayAccess setting.");
	}

}
