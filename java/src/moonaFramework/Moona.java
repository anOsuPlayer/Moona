package moonaFramework;

import org.lwjgl.glfw.GLFW;

import moonaFramework.util.IshMap;
import moonaFramework.essentials.Natural;
import moonaFramework.essentials.Serial;
import moonaFramework.process.Process;
import moonaFramework.reflection.Reflection;

public class Moona {
	
	protected static boolean isOn = false;
	
	public static boolean isOn() {
		return isOn;
	}
	
	public static void init() throws MoonaHandlingException {
		isOn = true;
		if (!GLFW.glfwInit()) {
			throw new MoonaHandlingException("Moona could not be initialized.");
		}
	}
	
	public static void checkOn() throws MoonaHandlingException {
		if (!isOn) { throw new MoonaHandlingException("Moona was not initialized."); }
	}
	
	private static long idCounter = 0;
	
	public static final long generateID() {
		return idCounter++;
	}
	
	protected static final IshMap<Serial, Long> elements = new IshMap<>();
	
	public static void add(Serial s) throws MoonaHandlingException, NullPointerException {
		if (s == null) {
			throw new NullPointerException("The Serial you're trying to add is null.");
		}
		if (elements.has(s, s.id())) {
			throw new MoonaHandlingException("The Serial you're trying to add is already present in Moona");
		}
		addSerial(s);
	}
	protected static void addSerial(Serial s) {
		switch (s.nature()) {
			case Natural.DAEMON, Natural.PROCESS, Natural.WORM: Phase.add((Process) s);
			case Natural.REFLECTION: Mirror.add(s);
			default: elements.add(s, s.id());
		}
	}
	
	public static void remove(Serial s) throws MoonaHandlingException, NullPointerException {
		if (s == null) {
			throw new NullPointerException("The Serial you're trying to remove is null.");
		}
		if (!elements.has(s, s.id())) {
			throw new MoonaHandlingException("The Serial you're trying to remove is not present in Moona");
		}
		removeSerial(s);
	}
	protected static void removeSerial(Serial s) {
		switch (s.nature()) {
			case Natural.DAEMON, Natural.PROCESS, Natural.WORM: Phase.remove((Process) s);
			default: elements.remove(s, s.id());
		}
	}
	
	public static Serial get(long id) {
		return elements.valueOf(id);
	}
	
	public static boolean has(Serial s) {
		return elements.has(s, s.id());
	}
	public static boolean has(long id) {
		return elements.hasKey(id);
	}
	
	public static int totalElements() {
		return Phase.totalProcesses() + Mirror.totalReflections();
	}
	
	protected Moona() {
		
	}
}
