package moonaFramework.essentials;

/**
 * The Natural Interface aims to create a new layer of distinction between different types. Each class can
 * declare a different nature and, thus, be considered of the same nature as another or totally different.
 * <br><br>
 * The distinction created by this interface elevates above all the concepts of inheritance: there are no
 * rules stating that inheritant types should also have the same nature, so you might find yourself having
 * types extending each others having different natures (and vice versa).
 * <br><br>
 * In this interface there's only one method: the .nature() method. It returns an integer identifying the
 * given type (defining its nature).
 * 
 * @author MasterZEr0
 */
public interface Natural {
	
	public static final int EXCEPTION = 0xffffffff;
	public static boolean isException(Natural n) throws NullPointerException {
		if (n == null) {
			throw new NullPointerException("Null Naturals do not specify any nature.");
		}
		return n.nature() == EXCEPTION;
	}
	
	public static final int PHASE = 0x0;
	public static boolean isPhase(Natural n) throws NullPointerException {
		if (n == null) {
			throw new NullPointerException("Null Naturals do not specify any nature.");
		}
		return n.nature() == PHASE;
	}
	
	public static final int EVENTSPACE = 0x2;
	public static boolean isEventSpace(Natural n) throws NullPointerException {
		if (n == null) {
			throw new NullPointerException("Null Naturals do not specify any nature.");
		}
		return n.nature() == EVENTSPACE;
	}
	
	public static boolean isPhasic(Natural n) throws NullPointerException {
		if (n == null) {
			throw new NullPointerException("Null Naturals do not specify any nature.");
		}
		return n.nature() == PHASE || n.nature() == EVENTSPACE;
	}
	
	public static final int PROCESS = 0x3;
	public static boolean isProcess(Natural n) throws NullPointerException {
		if (n == null) {
			throw new NullPointerException("Null Naturals do not specify any nature.");
		}
		return n.nature() == PROCESS;
	}
	
	public static final int DAEMON = 0x5;
	public static boolean isDaemon(Natural n) throws NullPointerException {
		if (n == null) {
			throw new NullPointerException("Null Naturals do not specify any nature.");
		}
		return n.nature() == DAEMON;
	}
	
	public static final int DEVIL = 0x7;
	public static boolean isDevil(Natural n) throws NullPointerException {
		if (n == null) {
			throw new NullPointerException("Null Naturals do not specify any nature.");
		}
		return n.nature() == DEVIL;
	}
	
	public static final int WORM = 0xb;
	public static boolean isWorm(Natural n) throws NullPointerException {
		if (n == null) {
			throw new NullPointerException("Null Naturals do not specify any nature.");
		}
		return n.nature() == WORM;
	}
	
	public static boolean isProcesslike(Natural n) throws NullPointerException {
		if (n == null) {
			throw new NullPointerException("Null Naturals do not specify any nature.");
		}
		return n.nature() == PROCESS || n.nature() == DAEMON || n.nature() == DEVIL || n.nature() == WORM;
	}
	
	public static boolean isDaemonic(Natural n) throws NullPointerException {
		if (n == null) {
			throw new NullPointerException("Null Naturals do not specify any nature.");
		}
		return n.nature() == DAEMON || n.nature() == DEVIL || n.nature() == WORM;
	}
	
	public static final int EVENT = 0xd;
	public static boolean isEvent(Natural n) throws NullPointerException {
		if (n == null) {
			throw new NullPointerException("Null Naturals do not specify any nature.");
		}
		return n.nature() == EVENT;
	}
	
	public static final int REFLECTION = 0xf;
	public static boolean isReflection(Natural n) throws NullPointerException {
		if (n == null) {
			throw new NullPointerException("Null Naturals do not specify any nature.");
		}
		return n.nature() == REFLECTION;
	}
	
	/**
	 * Returns a certain number according to the nature of the given object.
	 * @return The nature of the type.
	 */
	int nature();
}
