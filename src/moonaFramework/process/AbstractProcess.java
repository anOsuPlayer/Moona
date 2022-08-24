package moonaFramework.process;

import moonaFramework.Moona;
import moonaFramework.ProcessCondition;
import moonaFramework.Status;

public abstract class AbstractProcess implements Process {
	
	public abstract long id();
	public int nature() {
		return Moona.PROCESS;
	}
	
	private final Clock clock;
	public final Clock getClock() {
		return clock;
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
	public abstract void update();
	public abstract void end();
	
	public void run() {
		while (!ProcessCondition.DEAD.check(this)) {
			synchronized (clock) {
				clock.pauseHolder();
				update();
			}
		}
	}
	
	public AbstractProcess() {
		this.clock = new Clock(this);
		this.isRunning = new Status(false);
		this.isPaused = new Status(false);
	}
}
