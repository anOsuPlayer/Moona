package moonaFramework;

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
	public static boolean isException(Natural n) {
		return n.nature() == EXCEPTION;
	}
	
	public static final int PHASE = 0x00000000;
	public static boolean isPhase(Natural n) {
		return n.nature() == PHASE;
	}
	
	public static final int PROCESS = 0x00000001;
	public static boolean isProcess(Natural n) {
		return n.nature() == PROCESS;
	}
	
	public static final int DAEMON = 0x00000005;
	public static boolean isDaemon(Natural n) {
		return n.nature() == DAEMON;
	}
	
	public static boolean isProcesslike(Natural n) {
		return n.nature() == PROCESS || n.nature() == DAEMON;
	}
	
	/**
	 * Returns a certain number according to the nature of the given object.
	 * @return The nature of the type.
	 */
	int nature();
}
