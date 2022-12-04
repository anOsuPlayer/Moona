package moonaframework.dynamic.process;

import moonaframework.util.annotations.Deadlined;

public abstract class Task extends AbstractProcess {

	public @Deadlined void initialize() {
		
	}
	public @Deadlined void end() {
		
	}
	
	public @Override abstract void update();
	
	public Task() {
		super();
	}
}
