package moonaframework.base;

import org.lwjgl.glfw.GLFW;

import moonaframework.base.Natural.Nature;
import moonaframework.dynamic.Processor;
import moonaframework.dynamic.event.Event;
import moonaframework.dynamic.process.Process;
import moonaframework.util.collection.IshMap;
import moonaframework.util.exceptions.NullArgumentException;
import moonaframework.util.reflection.Reflection;

public final class Moona {
	
	static final IshMap<Serial, Long> elements = new IshMap<>();
	
	static boolean isOn = false;
	
	public static final @Nature int EXCEPTION = -1;
	
	public static final @Nature int OBJECT = 0;
	
	public static final @Nature int PROCESS = 1;
	
	public static final @Nature int DAEMON = 2;
	
	public static final @Nature int WORM = 5;
	
	public static final @Nature int EVENT = 10;
	
	public static final @Nature int MODALEVENT = 11;
	
	public static final @Nature int AUTOEVENT = 12;
	
	public static final @Nature int REFLECTION = 15;
	
	public static boolean isOn() {
		return isOn;
	}
	
	public static void init() throws MoonaHandlingException {
		isOn = true;
		if (!GLFW.glfwInit()) {
			throw new MoonaHandlingException("Moona could not be initialized.");
		}
		Mirror.loadReflections();
	}
	
	public static void checkOn() throws MoonaHandlingException {
		if (!isOn) { throw new MoonaHandlingException("Moona was not initialized."); }
	}
	
	private static long idCounter = 0;
	
	public static final long generateID() {
		return idCounter++;
	}
	
	public static void add(Serial s) throws MoonaHandlingException, NullArgumentException {
		if (s == null) {
			throw new NullArgumentException("You cannot add null elements to Moona.");
		}
		if (elements.hasKey(s.id()) || Processor.contains(s) || Mirror.contains(s) || Agent.contains(s)) {
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
	
	public static void remove(Serial s) throws MoonaHandlingException, NullArgumentException {
		if (s == null) {
			throw new NullArgumentException("You cannot remove a null element from Moona.");
		}
		if (!elements.hasKey(s.id()) && !Processor.contains(s) && !Mirror.contains(s) &
				!Agent.contains(s)) {
			throw new MoonaHandlingException("This Serial is not present in Moona.");
		}
		removeSerial(s);
	}
	static void removeSerial(Serial s) {
		switch (s) {
			case Process p: Processor.remove(p); break;
			case Reflection<?> r: Mirror.remove(r); break;
			case Event e: Agent.remove(e); break;
			default: elements.remove(s, s.id());
		}
	}
	
	public static void collapse() {
		Processor.collapse(); Mirror.wipe(); Agent.collapse();
		elements.clear();
		Moona.isOn = false;
	}
	
	public static Serial get(long id) {
		Serial s = elements.valueOf(id);
		s = (s != null) ? s : Processor.get(id);
		s = (s != null) ? s : Mirror.get(id);
		s = (s != null) ? s : Agent.get(id);
		return s;
	}
	
	public static boolean has(long id) {
		return elements.hasKey(id) || Processor.get(id) != null || Mirror.get(id) != null
				|| Agent.get(id) != null;
	}
	public static boolean has(Serial s) {
		return elements.hasKey(s.id()) || Processor.contains(s) || Mirror.contains(s)
				|| Agent.contains(s);
	}
	
	public static int totalElements() {
		return Processor.totalProcesses() + Mirror.totalReflections() + Agent.totalEvents();
	}
	
	private Moona() {
		
	}
}
