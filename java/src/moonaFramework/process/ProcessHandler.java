package moonaFramework.process;

public interface ProcessHandler {

	void provide(Process p);
	
	void await(Process p);
	void unlock(Process p);
	
	void initiate(Process p);
	void start(Process p);
	
	void flick(Process p);
	void spark(Process p);
	
	void terminate(Process p);
	void interrupt(Process p);
}
