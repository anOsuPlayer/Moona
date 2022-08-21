package moonaFramework.process;

import moonaFramework.Serial;

public interface Process extends Runnable, Serial {
	
	Object getLock();
	
	void initialize();
	void run();
	void end();
}
