package moonaframework.hallway;

import moonaframework.base.Moona;
import moonaframework.base.MoonaHandlingException;

public final class Cpp {

	public static native long version();
	
	public static String stringVersion() throws MoonaHandlingException {
		if (!Moona.isOn()) {
			throw new HallwayAccessException();
		}
		long version = version();
		return ("C++ v." + version / 100 + "-" + version % 100);
	}
	
	private Cpp() {
		
	}
}
