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
		p.run();
		Remove(p);
		ProcessCondition.DEAD.set(p);
		p.end();
	}
	
	private static final IshMap<Serial, Long> elements = new IshMap<>();
	
	private static int totalElements = 0;
	
	public static void Add(Serial s) throws MoonaHandlingException, NullPointerException {
		if (s == null) {
			throw new NullPointerException();
		}
		if (elements.has(s, s.id())) {
			throw new MoonaHandlingException("Moona already contains this element.");
		}
		elements.add(s, s.id());
		totalElements++;
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
	}
	
	public static void Start(Process p) throws MoonaHandlingException, NullPointerException {
		if (p == null) {
			throw new NullPointerException();
		}
		if (p.isRunning().verify()) {
			if (p.isPaused().verify()) {
				throw new MoonaHandlingException("An awaiting process cannot be started: you need to"
						+ " unlock it.");	
			}
			throw new MoonaHandlingException("The Process is already running.");
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
	
	private Moona() {
	}
}
