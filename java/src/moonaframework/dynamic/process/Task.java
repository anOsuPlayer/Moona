package moonaframework.dynamic.process;

import moonaframework.dynamic.Handler;
import moonaframework.util.annotation.Deadlined;

public abstract class Task extends AbstractProcess {

	public @Override Task clone() {
		return (Task) Handler.cloneProcess(this);
	}
	
	public @Deadlined void initialize() {
		
	}
	public @Deadlined void end() {
		
	}
	
	public @Override abstract void update();
	
	public Task() {
		super();
	}
}
