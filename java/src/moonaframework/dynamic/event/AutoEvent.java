package moonaframework.dynamic.event;

import moonaframework.base.Moona;
import moonaframework.dynamic.Dynamic;
import moonaframework.dynamic.Processor;
import moonaframework.dynamic.process.Task;
import moonaframework.util.annotations.Functional;

public @Functional abstract class AutoEvent extends Task implements Event, Dynamic {
	
	public @Override int nature() {
		return Moona.AUTOEVENT;
	}
	
	public @Override abstract void trigger();
	
	public @Override void update() {
		trigger();
		Processor.interrupt(this);
	}
	
	public AutoEvent() {
		super();
	}
}
