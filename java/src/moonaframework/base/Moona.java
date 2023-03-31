package moonaframework.base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import moonaframework.dynamic.Agent;
import moonaframework.dynamic.Processor;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.reflection.Mirror;

public final class Moona {
	
	static final List<MoonaObject> elements = new ArrayList<>();
	
	static boolean isOn = false;
	
	public static boolean isOn() {
		return isOn;
	}
	
	public static final Setting autoReflections = new Setting(true);
	
	public static final Setting autoDeriveReferences = new Setting(true, autoReflections);
	
	public static final Setting deriveWhenInitialized = new Setting(false, autoDeriveReferences);
	
	public static final Setting loadReflections = new Setting(true);
	
	public static final Setting unsafeReflectionLoading = new Setting(false, loadReflections);
	
	public static final Setting enableHallwayAccess = new Setting(false);
	
	private static native void nativeInit();
	
	private static final List<String> libraries = new ArrayList<>();
	
	public static void addLibrary(String lib) {
		if (enableHallwayAccess.evaluate()) {
			libraries.add(lib);
		}
	}
	private static void loadLibaries() throws UnsatisfiedLinkError {
		System.loadLibrary("shared/Moona");
		libraries.forEach(lib -> System.loadLibrary(lib));
	}
	
	public static void init() throws MoonaHandlingException {
		if (isOn) {
			throw new MoonaHandlingException("Moona.init() method can only be invoked once.");
		}
		
		if (enableHallwayAccess.evaluate()) {
			loadLibaries();
			nativeInit();
		}
		
		if (loadReflections.evaluate()) {
			Mirror.loadReflections();
		}
		
		isOn = true;
		
		for (MoonaObject mo : elements) {
			if (mo instanceof Constexpr cx) {
				cx.code.run();
			}
		}
	}
	
	public static void checkOn() throws MoonaHandlingException {
		if (!isOn) { throw new MoonaHandlingException("Moona was not initialized."); }
	}
	
	private static AtomicLong idCounter = new AtomicLong(0);
	
	public static long generateID() {
		return idCounter.incrementAndGet();
	}
	
	public static void add(MoonaObject mo) throws MoonaHandlingException, NullArgumentException {
		if (mo == null) {
			throw new NullArgumentException("You cannot add null elements to Moona.");
		}
		if (elements.contains(mo) || Processor.contains(mo) || Mirror.contains(mo) || Agent.contains(mo)) {
			throw new MoonaHandlingException("This MoonaObject already belongs to Moona.");
		}
		addMoonaObject(mo);
	}
	static void addMoonaObject(MoonaObject mo) {
		elements.add(mo);
	}
	
	public static void remove(MoonaObject mo) throws MoonaHandlingException, NullArgumentException {
		if (mo == null) {
			throw new NullArgumentException("You cannot remove a null element from Moona.");
		}
		if (!elements.contains(mo) && !Processor.contains(mo) && !Mirror.contains(mo) &
				!Agent.contains(mo)) {
			throw new MoonaHandlingException("This MoonaObject is not present in Moona.");
		}
		removeMoonaObject(mo);
	}
	static void removeMoonaObject(MoonaObject mo) {
		elements.remove(mo);
	}
	
	public static void collapse() {
		Processor.collapse(); Agent.collapse();
		elements.clear();
		Moona.isOn = false;
	}

	public static boolean has(MoonaObject mo) {
		return elements.contains(mo) || Processor.contains(mo) || Mirror.contains(mo)
				|| Agent.contains(mo);
	}
	
	public static int totalElements() {
		return Processor.totalProcesses() + Mirror.totalReflections() + Agent.totalEvents();
	}
	
	private Moona() {
		
	}
}
