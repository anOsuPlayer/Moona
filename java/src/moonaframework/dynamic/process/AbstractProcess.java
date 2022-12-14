package moonaframework.dynamic.process;

import moonaframework.base.Moona;
import moonaframework.base.Nature;
import moonaframework.dynamic.Handler;
import moonaframework.dynamic.ProcessCondition;
import moonaframework.dynamic.ProcessStatus;
import moonaframework.util.annotation.Deadlined;

public abstract class AbstractProcess implements Process {
	
	private final long id;
	
	public @Override final long id() {
		return this.id;
	}
	public @Override Nature nature() {
		return Nature.PROCESS;
	}
	
	public @Override AbstractProcess clone() {
		return Handler.cloneProcess(this);
	}
	
	private final ProcessClock clock;
	
	public @Override final ProcessClock getClock() {
		return clock;
	}
	
	private final ProcessStatus status;
	
	public @Override final ProcessStatus getStatus() {
		return this.status;
	}
	
	public @Deadlined void onPause() {
		
	}
	public @Deadlined void onUnpause() {
		
	}
	
	public @Override abstract void initialize();
	
	public @Override abstract void update();
	
	public @Override abstract void end();

	public @Override void run() {
		while (!ProcessCondition.DEAD.check(this) && Moona.isOn()) {
			synchronized (clock) {
				clock.pauseHolder();
				if (ProcessCondition.RUNNING.check(this)) {
					update();
				}
			}
		}
	}
	
	public AbstractProcess() {
		this.id = Moona.generateID();
		this.clock = new ProcessClock(this);
		this.status = new ProcessStatus(ProcessCondition.DEAD);
	}
}
