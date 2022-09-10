package moonaFramework.process;

import moonaFramework.relation.Attached;
import moonaFramework.util.Chrono;

public class ProcessClock extends Chrono implements Attached<Process> {

	private final long beginning;
	public long getBeginning() {
		return beginning;
	}
	
	private Process host;
	public Process getHost() {
		return host;
	}
	
	public void pauseHolder() {
		if (host.isPaused().verify()) {
			stasys();
		}
		else {
			release();
		}
	}
	
	public ProcessClock(Process host) {
		this.beginning = System.nanoTime();
		this.host = host;
	}
}
