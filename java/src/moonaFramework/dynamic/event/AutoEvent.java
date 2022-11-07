package moonaFramework.dynamic.event;

import moonaFramework.base.Processor;
import moonaFramework.dynamic.Dynamic;
import moonaFramework.dynamic.process.Task;

public abstract class AutoEvent extends Task implements Event, Dynamic {
	
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
