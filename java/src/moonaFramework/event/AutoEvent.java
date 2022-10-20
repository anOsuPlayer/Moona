package moonaFramework.event;

import moonaFramework.Moona;
import moonaFramework.process.Task;

public abstract class AutoEvent extends Task implements Event {
	
	@Override
	public abstract void trigger();
	
	@Override
	public void update() {
		trigger();
		Moona.interrupt(this);
	}
	
	public AutoEvent() {
		
	}
}
