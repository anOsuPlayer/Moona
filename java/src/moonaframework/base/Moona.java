package moonaframework.base;

import java.lang.foreign.MemorySession;
import java.util.ArrayList;
import java.util.List;

import moonaframework.dynamic.Agent;
import moonaframework.dynamic.Processor;
import moonaframework.hallway.HallwayAccessException;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.reflection.Mirror;

public final class Moona {
	
	static final List<MoonaObject> elements = new ArrayList<>();
	
	static boolean isOn = false;
	
	static boolean wasInitialized = false;
	
	public static boolean isOn() {
		return isOn;
	}
	public static void checkOn() throws MoonaHandlingException {
		if (!isOn) { throw new MoonaHandlingException("Moona was not initialized."); }
	}
	
	public static final MoonaSetting autoReflections = new MoonaSetting(true);
	
	public static final MoonaSetting autoDeriveReferences = new MoonaSetting(true, autoReflections);
	
	public static final MoonaSetting deriveWhenInitialized = new MoonaSetting(false, autoDeriveReferences);
	
	public static final MoonaSetting loadReflections = new MoonaSetting(true);
	
	public static final MoonaSetting unsafeReflectionLoading = new MoonaSetting(false, loadReflections);
	
	public static final MoonaSetting enableHallwayAccess = new MoonaSetting(false);
	
	private static MemorySession moonastack;
	
	public static MemorySession moonastack() throws HallwayAccessException, MoonaHandlingException {
		if (!enableHallwayAccess.evaluate()) {
			throw new HallwayAccessException();
		}
		if (!Moona.isOn) {
			throw new MoonaHandlingException("Cannot access the MoonaStack when Moona isn't on.");
		}
		return moonastack;
	}
	
	private static native void nativeInit();
	
	public static void init() throws MoonaHandlingException {
		if (isOn) {
			throw new MoonaHandlingException("Moona.init() method can only be invoked once.");
		}
		if (wasInitialized) {
			throw new MoonaHandlingException("Cannot invoke Moona.init() method after invoking Moona.interrupt().");
		}
		wasInitialized = true;
		
		if (enableHallwayAccess.evaluate()) {
			System.loadLibrary("shared/Moona");
			Moona.moonastack = MemorySession.openShared();
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
		
		Runtime.getRuntime().addShutdownHook(new Thread(ender));
	}
	
	private static final Runnable ender = () -> { if (isOn) Moona.interrupt(); };
	
	private static native void nativeInterrupt();
	
	public static void interrupt() throws MoonaHandlingException {
		if (!isOn) {
			throw new MoonaHandlingException("Moona cannot be interrupted if not previously started.");
		}
		
		for (MoonaObject mo : elements) {
			if (mo instanceof Endexpr ex) {
				ex.code.run();
			}
		}
		
		elements.clear();
		
		Processor.collapse();
		Agent.collapse();
		
		if (Moona.enableHallwayAccess.evaluate()) {
			if (Moona.moonastack.isAlive()) {
				Moona.moonastack.close();
			}
			nativeInterrupt();
		}
		
		isOn = false;
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
