package moonaFramework.event;

import moonaFramework.Moona;
import moonaFramework.Natural;
import moonaFramework.process.Task;

public abstract class AutoEvent extends Task implements Event {

	@Override
	public int nature() {
		return Natural.EVENT;
	}
	
	@Override
	public abstract void trigger();
	
	@Override
	public void update() {
		trigger();
		Moona.interrupt(this);
	}
	
	public AutoEvent() {
		super();
	}
}
