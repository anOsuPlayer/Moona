package moonaFramework.process;

import moonaFramework.Moona;
import moonaFramework.Status;

public abstract class AbstractProcess implements Process {
	
	public abstract long id();
	public int nature() {
		return Moona.PROCESS;
	}
	
	private final Object lock;
	public final Object getLock() {
		return lock;
	}
	
	private final Status isRunning;
	public final Status isRunning() {
		return isRunning;
	}
	
	private final Status isPaused;
	public final Status isPaused() {
		return isPaused;
	}
	
	public void onPause() {
	}
	public void onUnpause() {
	}
	
	public abstract void initialize();
	public abstract void run();
	public abstract void end();
	
	public AbstractProcess() {
		this.lock = new Object();
		this.isRunning = new Status(false);
		this.isPaused = new Status(false);
	}
}
