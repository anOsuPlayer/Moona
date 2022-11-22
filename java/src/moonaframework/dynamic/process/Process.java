package moonaframework.dynamic.process;

import moonaframework.base.Serial;
import moonaframework.dynamic.Dynamic;
import moonaframework.dynamic.Status;

public interface Process extends Runnable, Dynamic, Serial {
	
	@Override
	long id();
	
	ProcessClock getClock();
	
	Status isRunning();
	Status isPaused();
	
	void onPause();
	void onUnpause();
	
	void initialize();
	void update();
	void end();
	
	@Override
	void run();
}
