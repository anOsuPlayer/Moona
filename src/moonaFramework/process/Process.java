package moonaFramework.process;

import moonaFramework.Serial;
import moonaFramework.Status;

public interface Process extends Runnable, Serial {
	
	Object getLock();
	
	Status isRunning();
	Status isPaused();
	
	void onPause();
	void onUnpause();
	
	void initialize();
	void run();
	void end();
}
