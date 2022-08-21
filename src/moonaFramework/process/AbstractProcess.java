package moonaFramework.process;

public abstract class AbstractProcess implements Process {
	
	private final Object lock;
	public final Object getLock() {
		return lock;
	}
	
	public abstract void initialize();
	public abstract void run();
	public abstract void end();
	
	public AbstractProcess() {
		this.lock = new Object();
	}
}
