package moonaFramework;

import org.lwjgl.glfw.GLFW;

import moonaFramework.util.IshMap;
import moonaFramework.essentials.Natural;
import moonaFramework.essentials.Serial;
import moonaFramework.event.Event;
import moonaFramework.process.Process;
import moonaFramework.reflection.Reflection;

public class Moona {
	
	static boolean isOn = false;
	
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
	
	static final IshMap<Serial, Long> elements = new IshMap<>();
	
	public static void add(Serial s) throws MoonaHandlingException, NullPointerException {
		if (s == null) {
			throw new NullPointerException("You cannot add null elements to Moona.");
		}
		if (elements.has(s, s.id())) {
			throw new MoonaHandlingException("This Serial already belongs to Moona.");
		}
		addSerial(s);
	}
	static void addSerial(Serial s) {
		switch (s) {
			case Process p: Processor.add(p); break;
			case Reflection<?> r: Mirror.add(r); break;
			case Event e: Agent.add(e); break;
			default: elements.add(s, s.id());
		}
	}
	
	public static void remove(Serial s) throws MoonaHandlingException, NullPointerException {
		if (s == null) {
			throw new NullPointerException("You cannot remove a null element from Moona.");
		}
		if (!elements.has(s, s.id())) {
			throw new MoonaHandlingException("This Serial is not present in Moona.");
		}
		removeSerial(s);
	}
	static void removeSerial(Serial s) {
		switch (s.nature()) {
			case Natural.DAEMON, Natural.PROCESS, Natural.WORM: Processor.remove((Process) s);
			case Natural.REFLECTION: Mirror.add((Reflection<?>) s);
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
		return Processor.totalProcesses() + Mirror.totalReflections();
	}
	
	private Moona() {
		
	}
}
