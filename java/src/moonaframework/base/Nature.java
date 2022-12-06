package moonaframework.base;

public enum Nature {
	
	EXCEPTION(0b00000000),
	
	OBJECT(0b00000000),
	
	PROCESS(0b00000010),
	
	DAEMON(0b00000010),
	
	WORM(0b00000010),
	
	EVENT(0b00000100),
	
	MODALEVENT(0b00000100),
	
	AUTOEVENT(0b00000110),
	
	REFLECTION(0b00001000);
	
	public static final boolean isProcessLike(Natural n) {
		return (n.nature().essence & PROCESS.essence) >> 1 == 1;
	}
	
	public static final boolean isEventLike(Natural n) {
		return (n.nature().essence & EVENT.essence) >> 2 == 1;
	}
	
	public static final boolean isReflectionLike(Natural n) {
		return (n.nature().essence & REFLECTION.essence) >> 3 == 1;
	}
	
	private final int essence;
	
	private Nature(int essence) {
		this.essence = essence;
	}
}
