package moonaframework.dynamic;

import moonaframework.dynamic.process.Process;
import moonaframework.util.exceptions.NullArgumentException;

public enum ProcessCondition {
	
	RUNNING (true, false),
	
	PAUSED (true, true),
	
	AWAITING (false, true),
	
	DEAD (false, false);
	
	private boolean isRunning;
	private boolean isPaused;
	
	protected void set(Process p) throws NullArgumentException {
		if (p == null) {
			throw new NullArgumentException("The given Process is null.");
		}
		p.isRunning().imposeSet(isRunning);
		p.isPaused().imposeSet(isPaused);
	}
	
	protected static void cloneCondition(Process from, Process at) throws NullArgumentException {
		if (from == null || at == null) {
			throw new NullArgumentException("The given Process is null.");
		}
		at.isRunning().imposeSet(from.isRunning().verify());
		at.isPaused().imposeSet(from.isPaused().verify());
	}
	
	public boolean check(Process p) throws NullArgumentException {
		if (p == null) {
			throw new NullArgumentException("The given Process is null.");
		}
		return p.isRunning().verify() == isRunning && p.isPaused().verify() == isPaused;
	}
	
	private ProcessCondition(boolean isRunning, boolean isPaused) {
		this.isRunning = isRunning;
		this.isPaused = isPaused;
	}
}