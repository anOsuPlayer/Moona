package moonaframework.hallway;

import moonaframework.base.Moona;
import moonaframework.base.MoonaHandlingException;
import moonaframework.util.exception.NullArgumentException;

public final class Cpp {
	
	private static String preferredCompiler = "g++.exe";
	
	public static String getPreferredCompiler() {
		return preferredCompiler;
	}
	public static void setPreferredCompiler(String compiler) throws NullArgumentException {
		if (!Moona.enableHallwayAccess.evaluate()) {
			throw new HallwayAccessException();
		}
		if (compiler == null) {
			throw new NullArgumentException("Unable to use a null Compiler.");
		}
	}
	
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
