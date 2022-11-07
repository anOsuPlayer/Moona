package moonaFramework.base;

import moonaFramework.dynamic.process.Process;

public enum ProcessCondition {
	
	RUNNING (true, false),
	
	PAUSED (true, true),
	
	AWAITING (false, true),
	
	DEAD (false, false);
	
	private boolean isRunning;
	private boolean isPaused;
	
	protected void set(Process p) throws NullPointerException {
		if (p == null) {
			throw new NullPointerException("The given process is null.");
		}
		p.isRunning().imposeSet(isRunning);
		p.isPaused().imposeSet(isPaused);
	}
	
	public boolean check(Process p) throws NullPointerException {
		if (p == null) {
			throw new NullPointerException("The given process is null.");
		}
		return p.isRunning().verify() == isRunning && p.isPaused().verify() == isPaused;
	}
	
	private ProcessCondition(boolean isRunning, boolean isPaused) {
		this.isRunning = isRunning;
		this.isPaused = isPaused;
	}
}