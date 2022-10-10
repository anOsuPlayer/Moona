package moonaFramework.process;

import moonaFramework.Deadlined;

public abstract class Task extends AbstractProcess {

	@Deadlined
	public void initialize() {
	}
	@Deadlined
	public void end() {
	}
	
	public abstract void update();
}
