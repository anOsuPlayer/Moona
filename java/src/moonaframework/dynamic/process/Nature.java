package moonaframework.dynamic.process;

import moonaframework.dynamic.ProcessStatus;

public final class Nature {

	private final ProcessClock clock;
	
	public ProcessClock getClock() {
		return this.clock;
	}
	
	private final ProcessStatus status;
	
	public ProcessStatus getStatus() {
		return this.status;
	}
	
	Nature(ProcessClock pc, ProcessStatus ps) {
		this.clock = pc; this.status = ps;
	}
}
