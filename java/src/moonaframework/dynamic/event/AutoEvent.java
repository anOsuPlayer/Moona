package moonaframework.dynamic.event;

import moonaframework.dynamic.Dynamic;
import moonaframework.dynamic.Processor;
import moonaframework.dynamic.process.Task;
import moonaframework.util.annotation.Deadlined;
import moonaframework.util.annotation.Functional;

public @Functional abstract class AutoEvent extends Task implements Event, Dynamic {
	
	public @Override abstract void trigger();
	
	public @Deadlined void initialize() {
		
	}
	public @Deadlined void end() {
		
	}
	
	public @Override void update() {
		trigger();
		Processor.terminate(this);
	}
	
	public AutoEvent() {
		super();
	}
}
