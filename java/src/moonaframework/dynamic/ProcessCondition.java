package moonaframework.dynamic;

import moonaframework.dynamic.process.Process;
import moonaframework.util.exceptions.NullArgumentException;

public enum ProcessCondition {
	
	RUNNING,
	
	PAUSED,
	
	AWAITING,
	
	DEAD;
	
	protected void set(Process p) throws NullArgumentException {
		if (p == null) {
			throw new NullArgumentException("The given Process is null.");
		}
		p.getStatus().set(this);
	}
	
	protected static void cloneCondition(Process from, Process at) throws NullArgumentException {
		if (from == null || at == null) {
			throw new NullArgumentException("The given Process is null.");
		}
		from.getStatus().replace(at);
	}
	
	public boolean check(Process p) throws NullArgumentException {
		if (p == null) {
			throw new NullArgumentException("The given Process is null.");
		}
		return p.getStatus().evaluate().equals(this);
	}
	
	private ProcessCondition() {
	}
}