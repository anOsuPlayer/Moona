package moonaFramework;

import org.lwjgl.glfw.GLFW;

import moonaFramework.util.IshMap;
import moonaFramework.annotations.Timeless;
import moonaFramework.essentials.Container;
import moonaFramework.essentials.Natural;
import moonaFramework.essentials.Serial;
import moonaFramework.event.AutoEvent;
import moonaFramework.process.Process;
import moonaFramework.reflection.Reflection;

public final class Moona {
	
	static boolean isOn = false;
	
	public static boolean isOn() {
		return isOn;
	}
	
	public static void init() throws MoonaHandlingException {
		isOn = true;
		if (!GLFW.glfwInit()) {
			throw new MoonaHandlingException("Moona could not be initialized.");
		}
		for (Serial s : elements.values()) {
			if (s instanceof Reflection<?> r) { r.reflect(); }
			if (s instanceof Mirror m) { m.reflections.forEachValue((r) -> r.reflect()); }
		}
	}
	
	public static void checkOn() throws MoonaHandlingException {
		if (!isOn) { throw new MoonaHandlingException("Moona was not initialized."); }
	}
	
	private static long idCounter = 0;
	
	public static final long generateID() {
		return idCounter++;
	}
	
	private static final IshMap<Serial, Long> elements = new IshMap<>();
	
	private static int phaseCount = 0;
	
	private static int processCount = 0;
	
	private static int daemonCount = 0;
	
	public static void add(Serial s) throws MoonaHandlingException, NullPointerException {
		if (s == null) {
			throw new NullPointerException("You cannot add null elements to Moona.");
		}
		if (elements.has(s, s.id())) {
			throw new MoonaHandlingException("This element already belongs to Moona.");
		}
		if (s.nature() == Natural.DEVIL) {
			throw new MoonaHandlingException("Devils cannot be contained inside Moona.");
		}
		addSerial(s);
	}
	private static void addSerial(Serial s) {
		if (!elements.has(s, s.id())) {
			elements.add(s, s.id());
			if (s.nature() == Natural.PHASE) { phaseCount++; }
			if (s.nature() == Natural.PROCESS) { processCount++; }
			if (s.nature() == Natural.DAEMON) { daemonCount++; }
		}
	}
	
	public static void remove(Serial s) throws MoonaHandlingException, NullPointerException {
		if (s == null) {
			throw new NullPointerException("You cannot add null elements to Moona.");
		}
		if (!elements.has(s, s.id())) {
			throw new MoonaHandlingException("This element is not present in Moona.");
		}
		if (s.nature() == Natural.DEVIL) {
			throw new MoonaHandlingException("Devils cannot be contained inside Moona.");
		}
		removeSerial(s);
	}
	private static void removeSerial(Serial s) {
		if (elements.has(s, s.id())) {
			elements.remove(s, s.id());
			if (s.nature() == Natural.PHASE) { phaseCount--; }
			if (s.nature() == Natural.PROCESS) { processCount--; }
			if (s.nature() == Natural.DAEMON) { daemonCount--; }
		}
	}
	
	public static void erase(Serial s) throws MoonaHandlingException, NullPointerException {
		if (s == null) {
			throw new NullPointerException("You cannot erase null elements.");
		}
		if (s.nature() == Natural.DEVIL) {
			throw new MoonaHandlingException("Devils can exclusively be handled by their owner Phase.");
		}
		eraseSerial(s);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void eraseSerial(Serial s) {
		if (elements.has(s, s.id())) {
			removeSerial(s);
		}
		else {
			for (Serial S : elements.values()) {
				if (S instanceof Container<?> con) {
					((Container) con).remove(s);
				}
			}
		}
	}
	
	public static void mainStart(Process p) throws MoonaHandlingException, NullPointerException {
		if (p == null) {
			throw new NullPointerException("The Process you're trying to start is null.");
		}
		if (!ProcessCondition.DEAD.check(p)) {
			throw new MoonaHandlingException("You can't mainStart an already running Process.");
		}
		addSerial(p);
		p.initialize();
		ProcessCondition.RUNNING.set(p);
		p.run();
		ProcessCondition.DEAD.set(p);
		p.end();
		removeSerial(p);
	}

	static final void initiator(Process p) {
		try {
			if (p.getClass().getMethod("initialize", new Class<?>[0])
					.getAnnotation(Timeless.class) != null) {

				start(new AutoEvent() {
					public void trigger() {
						p.initialize();
					}
				});
			}
			else {
				p.initialize();
			}
		}
		catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}
	static final void ender(Process p) {
		try {
			if (p.getClass().getMethod("end", new Class<?>[0])
					.getAnnotation(Timeless.class) != null) {

				start(new AutoEvent() {
					public void trigger() {
						p.end();
					}
				});
			}
			else {
				p.end();
			}
		}
		catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}
	
	public static Serial get(long id) {
		return elements.valueOf(id);
	}
	public static Serial search(long id) {
		for (Serial s : elements.values()) {
			if (s.equals(get(id))) {
				return s;
			}
			if (s instanceof Phase p && p.get(id) != null) {
				return p.get(id);
			}
		}
		return null;
	}
	
	public static Process getProcess(long id) {
		return elements.valueOf(id) != null ? (elements.valueOf(id) instanceof Process) ? (Process) elements.valueOf(id)
				: null : null;
	}
	public static Process searchProcess(long id) {
		return search(id) != null ? search(id) instanceof Process ? (Process) search(id)
				: null : null;
	}
	
	public static boolean has(Serial s) throws NullPointerException {
		return elements.has(s, s.id());
	}
	public static boolean contains(Serial s) throws NullPointerException {
		return search(s.id()) != null;
	}
	
	public static int phaseCount() {
		return phaseCount;
	}
	
	public static int processCount() {
		return processCount;
	}
	
	private static int totalProcesses = 0;
	static void newProcess(boolean addRem) {
		totalProcesses += (addRem) ? 1 : -1;
	}
	public static int totalProcesses() {
		return totalProcesses + processCount;
	}
	
	public static int daemonCount() {
		return daemonCount;
	}
	
	private static int totalDaemons = 0;
	static void newDaemon(boolean addRem) {
		totalDaemons += (addRem) ? 1 : -1;
	}
	public static int totalDaemons() {
		return totalDaemons + daemonCount;
	}
	
	private Moona() {
		
	}
}
