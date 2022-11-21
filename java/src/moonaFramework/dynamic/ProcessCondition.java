package moonaFramework.dynamic;

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
			throw new NullPointerException("The given Process is null.");
		}
		p.isRunning().imposeSet(isRunning);
		p.isPaused().imposeSet(isPaused);
	}
	
	protected static void cloneCondition(Process from, Process at) throws NullPointerException {
		if (from == null || at == null) {
			throw new NullPointerException("The given Process is null.");
		}
		at.isRunning().imposeSet(from.isRunning().verify());
		at.isPaused().imposeSet(from.isPaused().verify());
	}
	
	public boolean check(Process p) throws NullPointerException {
		if (p == null) {
			throw new NullPointerException("The given Process is null.");
		}
		return p.isRunning().verify() == isRunning && p.isPaused().verify() == isPaused;
	}
	
	private ProcessCondition(boolean isRunning, boolean isPaused) {
		this.isRunning = isRunning;
		this.isPaused = isPaused;
	}
}