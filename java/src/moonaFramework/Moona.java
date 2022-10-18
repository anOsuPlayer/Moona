package moonaFramework;

import org.lwjgl.glfw.GLFW;

import moonaFramework.util.IshMap;
import moonaFramework.annotations.Timeless;
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
			if (!has(s, s.id())) {
				super.add(s, s.id());
				if (s.nature() == Natural.PHASE) { totalPhases++; }
				if (s.nature() == Natural.PROCESS) { totalProcesses++; }
				if (s.nature() == Natural.DAEMON) { totalDaemons++; }
			}
		}
		
		public void removeSerial(T s) {
			if (has(s, s.id())) {
				super.remove(s, s.id());
				if (s.nature() == Natural.PHASE) { totalPhases--; }
				if (s.nature() == Natural.PROCESS) { totalProcesses--; }
				if (s.nature() == Natural.DAEMON) { totalDaemons--; }
			}
		}
		
		private void eraseSerial(T s) {
			if (has(s, s.id())) {
				removeSerial(s);
			}
			else {
				for (Serial S : core.values()) {
					if (S instanceof Phase ph && ph.has(s)) {
						ph.core.removeSerial(s);
					}
				}
			}
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
		if (s == null) {
			throw new NullPointerException("You cannot add null elements to Moona.");
		}
		if (core.has(s, s.id())) {
			throw new MoonaHandlingException("This element already belongs to Moona.");
		}
		core.addSerial(s);
	}
	
	public static void remove(Serial s) throws MoonaHandlingException, NullPointerException {
		if (s == null) {
			throw new NullPointerException("You cannot add null elements to Moona.");
		}
		if (!core.has(s, s.id())) {
			throw new MoonaHandlingException("This element is not present in Moona.");
		}
		core.removeSerial(s);
	}
	public static void erase(Serial s) throws MoonaHandlingException, NullPointerException {
		if (s == null) {
			throw new NullPointerException("You cannot add null elements to Moona.");
		}
		if (!core.has(s, s.id())) {
			throw new MoonaHandlingException("This element is not present in Moona.");
		}
		core.eraseSerial(s);
	}
	
	public static void mainStart(Process p) throws MoonaHandlingException, NullPointerException {
		if (p == null) {
			throw new NullPointerException("The Process you're trying to start is null.");
		}
		if (!ProcessCondition.DEAD.check(p)) {
			throw new MoonaHandlingException("You can't mainStart an already running Process.");
		}
		core.addSerial(p);
		p.initialize();
		ProcessCondition.RUNNING.set(p);
		p.run();
		ProcessCondition.DEAD.set(p);
		p.end();
		core.removeSerial(p);
	}
	
	public static void provide(long id) throws MoonaHandlingException {
		checkOn();
		if (core.valueOf(id) instanceof Process p) {
			provide(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void provide(Process p) throws MoonaHandlingException, NullPointerException {
		checkOn();
		if (p == null) {
			throw new NullPointerException();
		}
		if (!ProcessCondition.DEAD.check(p)) {
			throw new MoonaHandlingException("A process cannot be provided if already running, awaiting,"
					+ " or paused.");
		}
		core.addSerial(p);
		ProcessCondition.AWAITING.set(p);
	}

	public static void await(long id) throws MoonaHandlingException {
		checkOn();
		if (core.valueOf(id) instanceof Process p) {
			await(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void await(Process p) throws MoonaHandlingException, NullPointerException {
		checkOn();
		provide(p);
		p.initialize();
		new Thread(p, "Process#" + p.id()).start();
	}

	public static void unlock(long id) throws MoonaHandlingException {
		checkOn();
		if (core.valueOf(id) instanceof Process p) {
			unlock(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void unlock(Process p) throws MoonaHandlingException, NullPointerException {
		checkOn();
		if (p == null) {
			throw new NullPointerException();
		}
		if (!ProcessCondition.AWAITING.check(p)) {
			throw new MoonaHandlingException("A process cannot be unlocked if not awaiting.");
		}
		ProcessCondition.RUNNING.set(p);
		p.getClock().release();
	}

	public static void initiate(long id) throws MoonaHandlingException {
		checkOn();
		if (core.valueOf(id) instanceof Process p) {
			initiate(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void initiate(Process p) throws MoonaHandlingException, NullPointerException {
		checkOn();
		if (p == null) {
			throw new NullPointerException();
		}
		if (p.isRunning().verify()) {
			throw new MoonaHandlingException("The Process is already running.");
		}
		if (ProcessCondition.AWAITING.check(p)) {
			throw new MoonaHandlingException("An awaiting process cannot be initiated: you need to"
					+ " unlock it.");	
		}
		core.addSerial(p);
		ProcessCondition.RUNNING.set(p);
		new Thread(p, "Process#" + p.id()).start();
	}

	public static void start(long id) throws MoonaHandlingException {
		checkOn();
		if (core.valueOf(id) instanceof Process p) {
			start(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void start(Process p) throws MoonaHandlingException, NullPointerException {
		checkOn();
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
		core.addSerial(p);
		ProcessCondition.RUNNING.set(p);
		initiator(p);
		new Thread(p, "Process#" + p.id()).start();
	}

	public static void flick(long id) throws MoonaHandlingException {
		checkOn();
		if (core.valueOf(id) instanceof Process p) {
			flick(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void flick(Process p) throws MoonaHandlingException, NullPointerException {
		checkOn();
		if (p == null) {
			throw new NullPointerException();
		}
		if (ProcessCondition.DEAD.check(p) || ProcessCondition.AWAITING.check(p)) {
			throw new MoonaHandlingException("The process needs to be running in order to be able to pause"
					+ " it.");
		}
		if (p.isPaused().verify()) {
			ProcessCondition.RUNNING.set(p);
			p.getClock().release();
		}
		else {
			ProcessCondition.PAUSED.set(p);
			p.getClock().stasys();
		}
	}

	public static void spark(long id) throws MoonaHandlingException {
		checkOn();
		if (core.valueOf(id) instanceof Process p) {
			spark(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void spark(Process p) throws MoonaHandlingException, NullPointerException {
		checkOn();
		if (p == null) {
			throw new NullPointerException();
		}
		if (ProcessCondition.DEAD.check(p) || ProcessCondition.AWAITING.check(p)) {
			throw new MoonaHandlingException("The process needs to be running in order to be able to pause"
					+ " it.");
		}
		if (p.isPaused().verify()) {
			ProcessCondition.RUNNING.set(p);
			p.getClock().release();
			synchronized (p.getClock()) {
				p.onUnpause();
			}
		}
		else {
			synchronized (p.getClock()) {
				p.onPause();
			}
			ProcessCondition.PAUSED.set(p);
			p.getClock().stasys();
		}
	}

	public static void terminate(long id) throws MoonaHandlingException {
		checkOn();
		if (core.valueOf(id) instanceof Process p) {
			terminate(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void terminate(Process p) throws MoonaHandlingException, NullPointerException {
		checkOn();
		if (p == null) {
			throw new NullPointerException();
		}
		if (ProcessCondition.DEAD.check(p)) {
			throw new MoonaHandlingException("You cannot interrupt a process which is not running"
					+ " or awaiting.");
		}
		boolean wasPaused = p.isPaused().verify();
		core.eraseSerial(p);
		ProcessCondition.DEAD.set(p);
		if (wasPaused) {
			p.getClock().release();
		}
	}

	public static void interrupt(long id) throws MoonaHandlingException {
		checkOn();
		if (core.valueOf(id) instanceof Process p) {
			interrupt(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void interrupt(Process p) throws MoonaHandlingException, NullPointerException {
		checkOn();
		terminate(p);
		synchronized (p.getClock()) {
			ender(p);
		}
	}

	public static void fade() throws MoonaHandlingException {
		checkOn();
		Process[] procs = new Process[core.totalProcesses];
		for (int i = 0, c = 0; i < core.size(); i++) {
			procs[c] = (core.getValue(i) instanceof Process p) ? p : procs[c += (procs[c] != null) ? 1 : 0];
		}
		for (Process p : procs) {
			terminate(p);
		}
		Moona.isOn = false;
	}
	public static void collapse() throws MoonaHandlingException {
		checkOn();
		Process[] procs = new Process[core.totalProcesses];
		for (int i = 0, c = 0; i < core.size(); i++) {
			procs[c] = (core.getValue(i) instanceof Process p) ? p : procs[c += (procs[c] != null) ? 1 : 0];
		}
		for (Process p : procs) {
			interrupt(p);
		}
		Moona.isOn = false;
	}

	static final void initiator(Process p) {
			try {
				if (p.getClass().getMethod("initialize", new Class<?>[0])
						.getAnnotation(Timeless.class) != null) {

					new Thread(() -> {
						p.initialize();
					}, "initiator#" + p.id()).start();
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

				new Thread(() -> {
					p.end();
				}, "ender#" + p.id()).start();
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
		return core.valueOf(id);
	}
	public static Serial search(long id) {
		for (Serial s : core.values()) {
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
		return core.valueOf(id) != null ? (core.valueOf(id) instanceof Process) ? (Process) core.valueOf(id)
				: null : null;
	}
	public static Process searchProcess(long id) {
		return search(id) != null ? search(id) instanceof Process ? (Process) search(id)
				: null : null;
	}
	
	public static boolean has(Serial s) {
		return core.has(s, s.id());
	}
	public static boolean contains(Serial s) {
		return search(s.id()) != null;
	}
	
	public static int processCount() {
		return core.totalProcesses;
	}
	public static int totalProcesses() {
		int total = core.totalProcesses;
		for (int i = 0; i < core.size(); i++) {
			if (core.getValue(i) instanceof Phase p) {
				total += p.processCount();
			}
		}
		return total;
	}
	public static int daemonCount() {
		return core.totalDaemons;
	}
	public static int totalDaemons() {
		int total = core.totalDaemons;
		for (int i = 0; i < core.size(); i++) {
			if (core.getValue(i) instanceof Phase p) {
				total += p.daemonCount();
			}
		}
		return total;
	}
	
	private Moona() {
		
	}
}
