package moonaFramework;

import org.lwjgl.glfw.GLFW;
import moonaFramework.process.Process;
import moonaFramework.util.IshMap;

public final class Moona {

	private static boolean isOn = false;
	
	public static boolean IsOn() {
		return isOn;
	}

	public static final int EXCEPTION = -1;
	public static final int PROCESS = 0;
	
	public static void Init() {
		isOn = true;
		if (!GLFW.glfwInit()) {
			throw new MoonaHandlingException("Moona could not be initialized.");
		}
	}
	public static void Init(Process p) {
		Init();
		Add(p);
		ProcessCondition.RUNNING.set(p);
		p.initialize();
		p.run();
		Remove(p);
		ProcessCondition.DEAD.set(p);
		p.end();
	}
	
	private static final IshMap<Serial, Long> elements = new IshMap<>();
	
	private static int totalElements = 0;
	private static int totalProcesses = 0;
	
	public static void Add(Serial s) throws MoonaHandlingException, NullPointerException {
		if (s == null) {
			throw new NullPointerException();
		}
		if (elements.has(s, s.id())) {
			throw new MoonaHandlingException("Moona already contains this element.");
		}
		elements.add(s, s.id());
		totalElements++;
		totalProcesses += (s instanceof Process) ? 1 : 0;
	}
	public static void Remove(Serial s) throws MoonaHandlingException, NullPointerException {
		if (s == null) {
			throw new NullPointerException();
		}
		if (!elements.has(s, s.id())) {
			throw new MoonaHandlingException("Moona does not contain this element.");
		}
		elements.remove(s, s.id());
		totalElements--;
		totalProcesses -= (s instanceof Process) ? 1 : 0;
	}
	
	public static void Provide(Process p) throws MoonaHandlingException, NullPointerException {
		if (p == null) {
			throw new NullPointerException();
		}
		if (!ProcessCondition.DEAD.check(p)) {
			throw new MoonaHandlingException("A process cannot be provided if already running, awaiting,"
					+ " or paused.");
		}
		Add(p);
		ProcessCondition.AWAITING.set(p);
	}
	
	public static void Await(Process p) throws MoonaHandlingException, NullPointerException {
		Provide(p);
		p.initialize();
		new Thread(p, "Process#" + p.id()).start();
	}
	
	public static void Unlock(Process p) throws MoonaHandlingException, NullPointerException {
		if (p == null) {
			throw new NullPointerException();
		}
		if (!ProcessCondition.AWAITING.check(p)) {
			throw new MoonaHandlingException("A process cannot be unlocked if not awaiting.");
		}
		ProcessCondition.RUNNING.set(p);
		p.getClock().release();
	}
	
	public static void Start(Process p) throws MoonaHandlingException, NullPointerException {
		if (p == null) {
			throw new NullPointerException();
		}
		if (p.isRunning().verify()) {
			throw new MoonaHandlingException("The Process is already running.");
		}
		if (ProcessCondition.AWAITING.check(p)) {
			throw new MoonaHandlingException("An awaiting process cannot be started: you need to"
					+ " unlock it.");	
		}
		Add(p);
		ProcessCondition.RUNNING.set(p);
		p.initialize();
		new Thread(p, "Process#" + p.id()).start();
	}
	
	public static void Interrupt(Process p) throws MoonaHandlingException, NullPointerException {
		if (p == null) {
			throw new NullPointerException();
		}
		if (!p.isRunning().verify()) {
			throw new MoonaHandlingException("You cannot interrupt a process which is not initialized.");
		}
		Remove(p);
		ProcessCondition.DEAD.set(p);
		p.end();
	}
	
	public static Serial getElementByID(long id) throws MoonaHandlingException {
		if (elements.hasKey(id)) {
			return elements.valueOf(id);
		}
		throw new MoonaHandlingException("There is no such element with this ID: " + id + ".");
	}
	public static int totalElements() {
		return totalElements;
	}
	
	public static Process getProcessByID(long id) throws MoonaHandlingException {
		if (getElementByID(id) instanceof Process proc) {
			return proc;
		}
		throw new MoonaHandlingException("The element that corresponds to that ID is not a Process.");
	}
	public static int totalProcesses() {
		return totalProcesses;
	}
	
	private Moona() {
	}
}
