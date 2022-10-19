package moonaFramework.event;

import moonaFramework.Natural;
import moonaFramework.Serial;
import moonaFramework.process.Task;

public class EventSpace extends Task implements Serial {

	@Override
	public int nature() {
		return Natural.EVENTSPACE;
	}
	
	public void update() {
		
	}
}
