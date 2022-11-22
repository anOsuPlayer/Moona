package moonaframework.dynamic.process;

import moonaframework.base.Mirror;
import moonaframework.base.Moona;
import moonaframework.base.Natural;
import moonaframework.dynamic.ProcessCondition;
import moonaframework.dynamic.Status;
import moonaframework.util.annotations.Deadlined;
import moonaframework.util.annotations.Timeless;
import moonaframework.util.annotations.Unique;
import moonaframework.util.reflection.Annotated;

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
	
	private final Status isRunning;
	@Override
	public final Status isRunning() {
		return isRunning;
	}

	private final Status isPaused;
	@Override
	public final Status isPaused() {
		return isPaused;
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
		Mirror.add(new Annotated.Type(this.getClass(), Unique.class));
		Mirror.add(new Annotated.Method(this.getClass(), Timeless.class, "initialize", Annotated.NO_ARGS));
		Mirror.add(new Annotated.Method(this.getClass(), Timeless.class, "end", Annotated.NO_ARGS));
		
		this.id = Moona.generateID();
		this.clock = new ProcessClock(this);
		this.isRunning = new Status(false);
		this.isPaused = new Status(false);
	}
}
