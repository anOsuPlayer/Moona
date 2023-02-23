package moonaframework.dynamic.process;

import moonaframework.base.MoonaObject;
import moonaframework.dynamic.Dynamic;
import moonaframework.dynamic.ProcessCondition;
import moonaframework.dynamic.ProcessStatus;
import moonaframework.util.annotation.Functional;

public @Functional interface Process extends MoonaObject, Runnable, Dynamic {
	
	Nature nature();
	
	default ProcessClock getClock() {
		return nature().getClock();
	}
	default ProcessStatus getStatus() {
		return nature().getStatus();
	}
	
	default boolean isRunning() {
		return nature().getStatus().evaluate().equals(ProcessCondition.PAUSED) ||
				nature().getStatus().evaluate().equals(ProcessCondition.RUNNING);
	}
	default boolean isPaused() {
		return nature().getStatus().evaluate().equals(ProcessCondition.AWAITING) ||
				nature().getStatus().evaluate().equals(ProcessCondition.PAUSED);
	}
	
	void onPause();
	void onUnpause();
	
	void initialize();
	void end();
	
	@Override void update();

	@Override void run();
}
