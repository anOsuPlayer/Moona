package moonaframework.bridge;

import moonaframework.base.Moona;
import moonaframework.base.MoonaHandlingException;

public final class Cplusplus {

	public static native long version();
	
	public static void printVersion() throws MoonaHandlingException {
		if (!Moona.isOn()) {
			throw new MoonaHandlingException("Moona needs to be active in order to access native features.");
		}
		long version = version();
		System.out.println("C++ v." + version / 100 + "-" + version % 100);
	}
	
	private Cplusplus() {
		
	}
}
