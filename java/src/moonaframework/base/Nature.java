package moonaframework.base;

public enum Nature {
	
	EXCEPTION,
	
	OBJECT,
	
	PROCESS,
	
	DAEMON,
	
	WORM,
	
	EVENT,
	
	MODALEVENT,
	
	AUTOEVENT,
	
	REFLECTION;
	
	public static final boolean isProcessLike(Natural n) {
		return n.nature().equals(PROCESS) || n.nature().equals(DAEMON) || n.nature().equals(WORM);
	}
	
	public static final boolean isEventLike(Natural n) {
		return n.nature().equals(EVENT) || n.nature().equals(MODALEVENT);
	}
	
	public static final boolean isReflectionLike(Natural n) {
		return n.nature().equals(REFLECTION);
	}
	
	private Nature() {
	
	}
}
