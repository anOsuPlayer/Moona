package moonaframework.dynamic.process;

import moonaframework.base.MoonaObject;
import moonaframework.dynamic.Dynamic;
import moonaframework.dynamic.ProcessCondition;
import moonaframework.dynamic.ProcessStatus;
import moonaframework.util.annotation.Functional;

public @Functional interface Process extends MoonaObject, Runnable, Dynamic {
	
	ProcessClock getClock();
	ProcessStatus getStatus();
	
	default boolean isRunning() {
		return getStatus().isValue(ProcessCondition.PAUSED) || getStatus().isValue(ProcessCondition.RUNNING);
	}
	default boolean isPaused() {
		return getStatus().isValue(ProcessCondition.AWAITING) || getStatus().isValue(ProcessCondition.PAUSED);
	}
	
	void onPause();
	void onUnpause();
	
	void initialize();
	void end();
	
	@Override void update();

	@Override void run();
}
