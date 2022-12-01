package moonaframework.dynamic.process;

import moonaframework.base.Natural;
import moonaframework.base.Serial;
import moonaframework.dynamic.Dynamic;
import moonaframework.dynamic.ProcessCondition;
import moonaframework.dynamic.ProcessStatus;

public interface Process extends Natural, Runnable, Dynamic, Serial {
	
	@Override int nature();
	@Override long id();
	
	ProcessClock getClock();
	ProcessStatus getStatus();
	
	default boolean isRunning() {
		return getStatus().evaluate().equals(ProcessCondition.PAUSED) ||
				getStatus().evaluate().equals(ProcessCondition.RUNNING);
	}
	default boolean isPaused() {
		return getStatus().evaluate().equals(ProcessCondition.AWAITING) ||
				getStatus().evaluate().equals(ProcessCondition.PAUSED);
	}
	
	void onPause();
	void onUnpause();
	
	void initialize();
	void end();
	
	@Override void update();

	@Override void run();
}
