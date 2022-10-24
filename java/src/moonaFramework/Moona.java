package moonaFramework;

import org.lwjgl.glfw.GLFW;

import moonaFramework.util.IshMap;
import moonaFramework.annotations.Timeless;
import moonaFramework.essentials.Natural;
import moonaFramework.essentials.Serial;
import moonaFramework.event.AutoEvent;
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
		if (!elements.has(s, s.id())) {
			throw new MoonaHandlingException("This element is not present in Moona.");
		}
		if (s.nature() == Natural.DEVIL) {
			throw new MoonaHandlingException("Devils can exclusively be handled by their owner Phase.");
		}
		eraseSerial(s);
	}
	private static void eraseSerial(Serial s) {
		if (elements.has(s, s.id())) {
			removeSerial(s);
		}
		else {
			for (Serial S : elements.values()) {
				if (S instanceof Phase ph && ph.has(s)) {
					ph.removeSerial(s);
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
	
	public static void provide(long id) throws MoonaHandlingException {
		checkOn();
		if (search(id) instanceof Process p) {
			provide(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void provide(Process p) throws MoonaHandlingException, NullPointerException {
		checkOn();
		if (p == null) {
			throw new NullPointerException("You cannot provide a null Process.");
		}
		if (!ProcessCondition.DEAD.check(p)) {
			throw new MoonaHandlingException("A process cannot be provided if already running, awaiting,"
					+ " or paused.");
		}
		if (p.nature() == Natural.DEVIL) {
			throw new MoonaHandlingException("Devils can exclusively be handled by their owner Phase.");
		}
		addSerial(p);
		ProcessCondition.AWAITING.set(p);
	}

	public static void await(long id) throws MoonaHandlingException {
		checkOn();
		if (search(id) instanceof Process p) {
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
		if (search(id) instanceof Process p) {
			unlock(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void unlock(Process p) throws MoonaHandlingException, NullPointerException {
		checkOn();
		if (p == null) {
			throw new NullPointerException("You cannot unlock a null Process.");
		}
		if (!ProcessCondition.AWAITING.check(p)) {
			throw new MoonaHandlingException("A process cannot be unlocked if not awaiting.");
		}
		ProcessCondition.RUNNING.set(p);
		p.getClock().release();
	}

	public static void initiate(long id) throws MoonaHandlingException {
		checkOn();
		if (search(id) instanceof Process p) {
			initiate(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void initiate(Process p) throws MoonaHandlingException, NullPointerException {
		checkOn();
		if (p == null) {
			throw new NullPointerException("You cannot initiate a null Process.");
		}
		if (p.isRunning().verify()) {
			throw new MoonaHandlingException("The Process is already running.");
		}
		if (ProcessCondition.AWAITING.check(p)) {
			throw new MoonaHandlingException("An awaiting process cannot be initiated: you need to"
					+ " unlock it.");	
		}
		if (p.nature() == Natural.DEVIL) {
			throw new MoonaHandlingException("Devils can exclusively be handled by their owner Phase.");
		}
		addSerial(p);
		ProcessCondition.RUNNING.set(p);
		new Thread(p, "Process#" + p.id()).start();
	}

	public static void start(long id) throws MoonaHandlingException {
		checkOn();
		if (search(id) instanceof Process p) {
			start(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void start(Process p) throws MoonaHandlingException, NullPointerException {
		checkOn();
		if (p == null) {
			throw new NullPointerException("You cannot start a null Process.");
		}
		if (p.isRunning().verify()) {
			throw new MoonaHandlingException("The Process is already running.");
		}
		if (ProcessCondition.AWAITING.check(p)) {
			throw new MoonaHandlingException("An awaiting process cannot be started: you need to"
					+ " unlock it.");	
		}
		if (p.nature() == Natural.DEVIL) {
			throw new MoonaHandlingException("Devils can exclusively be handled by their owner Phase.");
		}
		addSerial(p);
		ProcessCondition.RUNNING.set(p);
		initiator(p);
		new Thread(p, "Process#" + p.id()).start();
	}

	public static void flick(long id) throws MoonaHandlingException {
		checkOn();
		if (search(id) instanceof Process p) {
			flick(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void flick(Process p) throws MoonaHandlingException, NullPointerException {
		checkOn();
		if (p == null) {
			throw new NullPointerException("You cannot flick a null Process.");
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
		if (search(id) instanceof Process p) {
			spark(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void spark(Process p) throws MoonaHandlingException, NullPointerException {
		checkOn();
		if (p == null) {
			throw new NullPointerException("You cannot spark a null Process.");
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
		if (search(id) instanceof Process p) {
			terminate(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void terminate(Process p) throws MoonaHandlingException, NullPointerException {
		checkOn();
		if (p == null) {
			throw new NullPointerException("You cannot terminate a null Process.");
		}
		if (ProcessCondition.DEAD.check(p)) {
			throw new MoonaHandlingException("You can't interrupt Processes which are not running or awaiting");
		}
		if (p.nature() == Natural.DEVIL) {
			throw new MoonaHandlingException("Devils can exclusively be handled by their owner Phase.");
		}
		boolean wasPaused = p.isPaused().verify();
		eraseSerial(p);
		ProcessCondition.DEAD.set(p);
		if (wasPaused) {
			p.getClock().release();
		}
	}

	public static void interrupt(long id) throws MoonaHandlingException {
		checkOn();
		if (search(id) instanceof Process p) {
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
		Process[] procs = new Process[processCount];
		Phase[] phases = new Phase[phaseCount];
		for (int i = 0, c = 0, pc = 0; i < elements.size(); i++) {
			if (procs.length > 0) {
				procs[c] = (elements.getValue(i).nature() == Natural.PROCESS) ?
						(Process) elements.getValue(i) : procs[c];
				c += (elements.getValue(i).nature() == Natural.PROCESS) ? 1 : 0;
			}
			if (phases.length > 0) {
				phases[pc] = (elements.getValue(i) instanceof Phase ph) ? ph : phases[pc];
				pc += (elements.getValue(i) instanceof Phase) ? 1 : 0;
			}
		}
		for (Process p : procs) {
			terminate(p);
		}
		for (Phase ph : phases) {
			ph.fade();
		}
		Moona.isOn = false;
	}
	public static void collapse() throws MoonaHandlingException {
		checkOn();
		Process[] procs = new Process[processCount];
		Phase[] phases = new Phase[phaseCount];
		for (int i = 0, c = 0, pc = 0; i < elements.size(); i++) {
			if (procs.length > 0) {
				procs[c] = (elements.getValue(i).nature() == Natural.PROCESS) ?
						(Process) elements.getValue(i) : procs[c];
				c += (elements.getValue(i).nature() == Natural.PROCESS) ? 1 : 0;
			}
			if (phases.length > 0) {
				phases[pc] = (elements.getValue(i) instanceof Phase ph) ? ph : phases[pc];
				pc += (elements.getValue(i) instanceof Phase) ? 1 : 0;
			}
		}
		for (Process p : procs) {
			interrupt(p);
		}
		for (Phase ph : phases) {
			ph.collapse();
		}
		Moona.isOn = false;
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
