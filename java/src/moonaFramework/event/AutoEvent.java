package moonaFramework.event;

import moonaFramework.Processor;
import moonaFramework.essentials.Dynamic;
import moonaFramework.process.Task;

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
