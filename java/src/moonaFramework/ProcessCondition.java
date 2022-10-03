package moonaFramework;

import moonaFramework.process.Process;

public enum ProcessCondition {
	
	RUNNING {
		protected void set(Process p) throws NullPointerException {
			if (p == null) {
				throw new NullPointerException();
			}
			p.isRunning().imposeSet(true);
			p.isPaused().imposeSet(false);
		}
		
		public boolean check(Process p) throws NullPointerException {
			if (p == null) {
				throw new NullPointerException();
			}
			return p.isRunning().verify() && !p.isPaused().verify();
		}
	},
	PAUSED {
		protected void set(Process p) throws NullPointerException {
			if (p == null) {
				throw new NullPointerException();
			}
			p.isRunning().imposeSet(true);
			p.isPaused().imposeSet(true);
		}
		
		public boolean check(Process p) throws NullPointerException {
			if (p == null) {
				throw new NullPointerException();
			}
			return p.isRunning().verify() && p.isPaused().verify();
		}
	},
	AWAITING {
		protected void set(Process p) throws NullPointerException {
			if (p == null) {
				throw new NullPointerException();
			}
			p.isRunning().imposeSet(false);
			p.isPaused().imposeSet(true);
		}
		
		public boolean check(Process p) throws NullPointerException {
			if (p == null) {
				throw new NullPointerException();
			}
			return !p.isRunning().verify() && p.isPaused().verify();
		}
	},
	DEAD {
		protected void set(Process p) throws NullPointerException {
			if (p == null) {
				throw new NullPointerException();
			}
			p.isRunning().imposeSet(false);
			p.isPaused().imposeSet(false);
		}
		
		public boolean check(Process p) throws NullPointerException {
			if (p == null) {
				throw new NullPointerException();
			}
			return !p.isRunning().verify() && !p.isPaused().verify();
		}
	};
	
	protected abstract void set(Process p) throws NullPointerException;
	
	public abstract boolean check(Process p) throws NullPointerException;
	
	public static boolean allowRemoval(Process p) throws NullPointerException {
		if (p == null) {
			throw new NullPointerException();
		}
		return DEAD.check(p);
	}
}