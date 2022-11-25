package moonaframework.dynamic.process;

import moonaframework.base.Mirror;
import moonaframework.base.Moona;
import moonaframework.base.Natural;
import moonaframework.dynamic.ProcessCondition;
import moonaframework.dynamic.ProcessStatus;
import moonaframework.util.annotations.Deadlined;

public abstract class AbstractProcess implements Process {
	
	private final long id;
	@Override
	public final long id() {
		return this.id;
	}
	@Override
	public int nature() {
		return Natural.PROCESS;
	}

	private final ProcessClock clock;
	@Override
	public final ProcessClock getClock() {
		return clock;
	}
	
	private final ProcessStatus status;
	@Override
	public final ProcessStatus getStatus() {
		return this.status;
	}
	
	@Deadlined
	public void onPause() {
	}
	@Deadlined
	public void onUnpause() {
	}
	
	@Override
	public abstract void initialize();
	@Override
	public abstract void update();
	@Override
	public abstract void end();

	@Override
	public void run() {
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
		Mirror.reflectProcess(this);
		
		this.id = Moona.generateID();
		this.clock = new ProcessClock(this);
		this.status = new ProcessStatus(ProcessCondition.DEAD);
	}
}
