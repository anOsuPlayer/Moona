package moonaFramework;

import org.lwjgl.glfw.GLFW;

import moonaFramework.util.IshMap;
import moonaFramework.process.Process;

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
	}
	
	public static void checkOn() throws MoonaHandlingException {
		if (!isOn) { throw new MoonaHandlingException("Moona was not initialized."); }
	}
	
	static final IshMap<Serial, Long> globalElements = new IshMap<>();
	public static int totalPhases() {
		int tot = 0;
		for (int i = 0; i < globalElements.size(); i++) {
			tot += (globalElements.getValue(i).nature() == Natural.PHASE) ? 1 : 0;
		}
		return tot;
	}
	public static Phase getPhaseByID(long id) throws MoonaHandlingException {
		for (int i = 0; i < globalElements.size(); i++) {
			if (globalElements.getValue(i).id() == id) {
				if (globalElements.getValue(i).nature() == Natural.PHASE) {
					return (Phase) globalElements.getValue(i);
				}
				throw new MoonaHandlingException("The given ID does not correspond to any Phase.");
			}
		}
		return null;
	}
	
	private static long totalElements = 0;
	public static long totalElements() {
		return totalElements;
	}
	
	private static int totalProcesses = 0;
	public static int totalProcesses() {
		return totalProcesses;
	}
	
	private static int totalDaemons = 0;
	public static int totalDaemons() {
		return totalDaemons;
	}
	
	static void silentAdd(Phase p, Serial s) throws MoonaHandlingException, NullPointerException {
		if (s == null) {
			throw new NullPointerException();
		}
		if (!p.elements.has(s, s.id())) {
			p.elements.add(s, s.id());
			totalElements++; p.elementCount++;
			if (s instanceof Process) {
				if (s.nature() == Natural.PROCESS) { totalProcesses++; p.processCount++; }
				if (s.nature() == Natural.DAEMON) { totalDaemons++; p.daemonCount++; }
			}
		}
	}
	static void silentRemove(Phase p, Serial s) throws MoonaHandlingException, NullPointerException {
		if (s == null) {
			throw new NullPointerException();
		}
		if (p.elements.has(s, s.id())) {
			p.elements.remove(s, s.id());
			totalElements--; p.elementCount--;
			if (s.nature() == Natural.PROCESS) { totalProcesses--; p.processCount--; };
			if (s.nature() == Natural.DAEMON) { totalDaemons--; p.daemonCount--; };
		}
	}
	
	static void addPhase(Phase p) throws NullPointerException {
		if (p == null) {
			throw new NullPointerException();
		}
		globalElements.add(p, p.id());
	}
	
	private static long idCounter = 0;
	
	public static long GenerateID() {
		return idCounter++;
	}
	
	private Moona() {
		
	}
}
