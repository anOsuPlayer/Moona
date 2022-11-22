package moonaframework.dynamic.event;

import moonaframework.base.Natural;
import moonaframework.dynamic.Dynamic;
import moonaframework.dynamic.Processor;
import moonaframework.dynamic.process.Task;

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
