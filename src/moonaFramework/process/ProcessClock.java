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
	
	public ProcessClock(Process host) throws IllegalArgumentException {
		this.beginning = System.nanoTime();
		if (host == null) {
			throw new IllegalArgumentException("A ProcessClock's host cannot be null.");
		}
		this.host = host;
	}
}
