package moonaFramework.process;

import moonaFramework.relation.Attached;
import moonaFramework.time.Chrono;

public class ProcessClock extends Chrono implements Attached<Process> {

	private Process host;
	public Process getHost() {
		return host;
	}
	
	private final long beginning;
	public long getBeginning() {
		return beginning;
	}
	
	public final long lifeTime() {
		return System.nanoTime() - beginning;
	}
	
	public void pauseHolder() {
		if (host.isPaused().verify()) {
			stasys();
		}
		else {
			release();
		}
	}
	
	private ProcessClock() {
		this.beginning = System.nanoTime();
	}
	public ProcessClock(Process host) {
		this.beginning = System.nanoTime();
		this.host = host;
	}
}
