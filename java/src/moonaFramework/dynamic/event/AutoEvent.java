package moonaFramework.dynamic.event;

import moonaFramework.base.Natural;
import moonaFramework.dynamic.Dynamic;
import moonaFramework.dynamic.Processor;
import moonaFramework.dynamic.process.Task;

public abstract class AutoEvent extends Task implements Event, Dynamic {
	
	@Override
	public int nature() {
		return Natural.AUTOEVENT;
	}
	
	@Override
	public abstract void trigger();
	
	@Override
	public void update() {
		trigger();
		Processor.interrupt(this);
	}
	
	public AutoEvent() {
		super();
	}
}
