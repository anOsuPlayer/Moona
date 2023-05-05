package moonaframework.hallway;

import java.io.File;
import java.nio.file.NoSuchFileException;

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
	
	private static String JNILocation = "%JAVA_HOME%\\include";
	
	public static String getJNILocation() {
		return JNILocation;
	}
	
	public static void setJNILocation(String location) throws NullArgumentException, IllegalArgumentException, NoSuchFileException {
		if (location == null) {
			throw new NullArgumentException("Unable to locate the JNILocation with a null String Path.");
		}
		
		setJNIDirectory(new File(location));
	}
	public static void setJNIDirectory(File location) throws NullArgumentException, IllegalArgumentException, NoSuchFileException {
		if (location == null) {
			throw new NullArgumentException("Unable to locate the JNILocation with a null File.");
		}
		
		if (!location.exists()) {
			throw new NoSuchFileException("The given location does not exist.");
		}
		
		File jni = new File(location.getAbsolutePath() + "/jni.h");
		if (!jni.exists()) {
			throw new IllegalArgumentException("The given path does not contain jni.h file.");
		}
		
		JNILocation = jni.getAbsolutePath();
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
