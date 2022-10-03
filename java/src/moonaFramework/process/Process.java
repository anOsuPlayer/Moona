package moonaFramework.process;

import moonaFramework.Serial;
import moonaFramework.Status;

public interface Process extends Runnable, Serial {
	
	ProcessClock getClock();
	
	Status isRunning();
	Status isPaused();
	
	void onPause();
	void onUnpause();
	
	void initialize();
	void update();
	void end();
}
