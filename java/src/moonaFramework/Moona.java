package moonaFramework;

import org.lwjgl.glfw.GLFW;

import moonaFramework.util.IshMap;
import moonaFramework.process.Process;

public final class Moona {
	
	static class Core<T extends Serial> extends IshMap<T, Long> {
		
		private int totalPhases = 0;
		public int totalPhases() {
			return totalPhases;
		}
		
		private int totalProcesses = 0;
		public int totalProcesses() {
			return totalProcesses;
		}
		
		private int totalDaemons = 0;
		public int totalDaemons() {
			return totalDaemons;
		}
		
		public void addSerial(T s) {
			super.add(s, s.id());
			if (s.nature() == Natural.PHASE) { totalPhases++; }
			if (s.nature() == Natural.PROCESS) { totalProcesses++; }
			if (s.nature() == Natural.DAEMON) { totalDaemons++; }
		}
		
		public void removeSerial(T s) {
			super.remove(s, s.id());
			if (s.nature() == Natural.PHASE) { totalPhases--; }
			if (s.nature() == Natural.PROCESS) { totalProcesses--; }
			if (s.nature() == Natural.DAEMON) { totalDaemons--; }
		}
		
		public Core() {
		}
	}
	
	static final Core<Serial> core = new Core<>();
	
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
	
	public static void add(Serial s) throws MoonaHandlingException, NullPointerException {
		checkOn();
		if (s == null) {
			throw new NullPointerException("You cannot add null elements to Moona.");
		}
		if (core.has(s, s.id())) {
			throw new MoonaHandlingException("This element already belongs to Moona.");
		}
		core.addSerial(s);
	}
	
	public static void remove(Serial s) throws MoonaHandlingException, NullPointerException {
		checkOn();
		if (s == null) {
			throw new NullPointerException("You cannot add null elements to Moona.");
		}
		if (!core.has(s, s.id())) {
			throw new MoonaHandlingException("This element is not present in Moona.");
		}
		core.removeSerial(s);
	}
	
	public static Serial get(long id) {
		return core.valueOf(id);
	}
	public static Process getProcess(long id) throws MoonaHandlingException {
		return core.valueOf(id) != null ? (core.valueOf(id) instanceof Process) ? (Process) core.valueOf(id)
				: null : null;
	}
	
	public static boolean has(Serial s) {
		return core.has(s, s.id());
	}
	
	private Moona() {
		
	}
}
