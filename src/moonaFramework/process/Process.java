package moonaFramework.process;

import moonaFramework.Serial;
import moonaFramework.Status;
import moonaFramework.util.Clock;

public interface Process extends Runnable, Serial {
	
	Clock getClock();
	
	Status isRunning();
	Status isPaused();
	
	void onPause();
	void onUnpause();
	
	void initialize();
	void update();
	void end();
}
