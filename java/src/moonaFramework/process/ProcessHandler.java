package moonaFramework.process;

public interface ProcessHandler {

	void MainStart(Process p);
	
	void Provide(Process p);
	
	void Await(Process p);
	void Unlock(Process p);
	
	void Initiate(Process p);
	void Start(Process p);
	
	void Flick(Process p);
	void Spark(Process p);
	
	void Terminate(Process p);
	void Interrupt(Process p);
}
