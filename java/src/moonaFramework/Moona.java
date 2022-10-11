package moonaFramework;

import org.lwjgl.glfw.GLFW;
import moonaFramework.process.Process;
import moonaFramework.util.IshMap;

public final class Moona {

	static boolean isOn = false;
	
	public static boolean IsOn() {
		return isOn;
	}

	public static final int EXCEPTION = -1;
	
	public static final int PROCESS = 0;
	
	public static final int DAEMON = 1;
	
	public static void Init() throws MoonaHandlingException {
		isOn = true;
		if (!GLFW.glfwInit()) {
			throw new MoonaHandlingException("Moona could not be initialized.");
		}
	}
	
	private static void CheckOn() throws MoonaHandlingException {
		if (!isOn) { throw new MoonaHandlingException("Moona was not initialized."); }
	}
	
	public static void Initialize(Process p) {
		CheckOn();
		Add(p);
		ProcessCondition.RUNNING.set(p);
		p.initialize();
		p.run();
		Remove(p);
		ProcessCondition.DEAD.set(p);
		p.end();
	}
	
	private static final IshMap<Serial, Long> elements = new IshMap<>();
	
	private static long idCounter = 0;
	
	private static long totalElements = 0;
	
	private static int totalProcesses = 0;
	
	private static int totalDaemons = 0;
	
	static void FilteredAdd(Serial s) throws MoonaHandlingException, NullPointerException {
		if (s == null) {
			throw new NullPointerException();
		}
		if (!elements.has(s, s.id())) {
			elements.add(s, s.id());
			totalElements++;
			totalProcesses += (s.nature() == PROCESS)? 1 : 0;
			totalDaemons += (s.nature() == DAEMON)? 1 : 0;
		}
	}
	public static void Add(Serial s) throws MoonaHandlingException, NullPointerException {
		CheckOn();
		if (s == null) {
			throw new NullPointerException();
		}
		if (elements.has(s, s.id())) {
			throw new MoonaHandlingException("Moona already contains this element.");
		}
		elements.add(s, s.id());
		totalElements++;
		totalProcesses += (s.nature() == PROCESS) ? 1 : 0;
		totalDaemons += (s.nature() == DAEMON)? 1 : 0;
	}
	static void FilteredRemove(Serial s) throws MoonaHandlingException, NullPointerException {
		if (s == null) {
			throw new NullPointerException();
		}
		if (elements.has(s, s.id())) {
			elements.remove(s, s.id());
			totalElements--;
			totalProcesses -= (s.nature() == PROCESS) ? 1 : 0;
			totalDaemons -= (s.nature() == DAEMON)? 1 : 0;
		}
	}
	public static void Remove(Serial s) throws MoonaHandlingException, NullPointerException {
		CheckOn();
		if (s == null) {
			throw new NullPointerException();
		}
		if (!elements.has(s, s.id())) {
			throw new MoonaHandlingException("Moona does not contain this element.");
		}
		elements.remove(s, s.id());
		totalElements--;
		totalProcesses -= (s.nature() == PROCESS) ? 1 : 0;
		totalDaemons -= (s.nature() == DAEMON)? 1 : 0;
	}
	
	public static void Provide(long id) throws MoonaHandlingException {
		CheckOn();
		if (elements.valueOf(id) instanceof Process p) {
			Provide(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void Provide(Process p) throws MoonaHandlingException, NullPointerException {
		CheckOn();
		if (p == null) {
			throw new NullPointerException();
		}
		if (!ProcessCondition.DEAD.check(p)) {
			throw new MoonaHandlingException("A process cannot be provided if already running, awaiting,"
					+ " or paused.");
		}
		FilteredAdd(p);
		ProcessCondition.AWAITING.set(p);
	}
	
	public static void Await(long id) throws MoonaHandlingException {
		CheckOn();
		if (elements.valueOf(id) instanceof Process p) {
			Await(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void Await(Process p) throws MoonaHandlingException, NullPointerException {
		CheckOn();
		Provide(p);
		p.initialize();
		new Thread(p, "Process#" + p.id()).start();
	}
	
	public static void Unlock(long id) throws MoonaHandlingException {
		CheckOn();
		if (elements.valueOf(id) instanceof Process p) {
			Unlock(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void Unlock(Process p) throws MoonaHandlingException, NullPointerException {
		CheckOn();
		if (p == null) {
			throw new NullPointerException();
		}
		if (!ProcessCondition.AWAITING.check(p)) {
			throw new MoonaHandlingException("A process cannot be unlocked if not awaiting.");
		}
		ProcessCondition.RUNNING.set(p);
		p.getClock().release();
	}
	
	public static void Initiate(long id) throws MoonaHandlingException {
		CheckOn();
		if (elements.valueOf(id) instanceof Process p) {
			Initiate(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void Initiate(Process p) throws MoonaHandlingException, NullPointerException {
		CheckOn();
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
		FilteredAdd(p);
		ProcessCondition.RUNNING.set(p);
		new Thread(p, "Process#" + p.id()).start();
	}
	
	public static void Start(long id) throws MoonaHandlingException {
		CheckOn();
		if (elements.valueOf(id) instanceof Process p) {
			Start(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void Start(Process p) throws MoonaHandlingException, NullPointerException {
		CheckOn();
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
		FilteredAdd(p);
		ProcessCondition.RUNNING.set(p);
		p.initialize();
		new Thread(p, "Process#" + p.id()).start();
	}
	
	public static void Spark(long id) throws MoonaHandlingException {
		CheckOn();
		if (elements.valueOf(id) instanceof Process p) {
			Spark(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void Spark(Process p) throws MoonaHandlingException, NullPointerException {
		CheckOn();
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
			ProcessCondition.PAUSED.set(p);
			p.getClock().stasys();
			synchronized (p.getClock()) {
				p.onPause();
			}
		}
	}
	
	public static void Flick(long id) throws MoonaHandlingException {
		CheckOn();
		if (elements.valueOf(id) instanceof Process p) {
			Flick(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void Flick(Process p) throws MoonaHandlingException, NullPointerException {
		CheckOn();
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
			ProcessCondition.PAUSED.set(p);
			p.getClock().stasys();
			synchronized (p.getClock()) {
				p.onPause();
			}
		}
	}
	
	public static void Terminate(long id) throws MoonaHandlingException {
		CheckOn();
		if (elements.valueOf(id) instanceof Process p) {
			Terminate(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void Terminate(Process p) throws MoonaHandlingException, NullPointerException {
		CheckOn();
		if (p == null) {
			throw new NullPointerException();
		}
		if (ProcessCondition.DEAD.check(p)) {
			throw new MoonaHandlingException("You cannot interrupt a process which is not running"
					+ " or awaiting.");
		}
		boolean wasPaused = p.isPaused().verify();
		FilteredRemove(p);
		ProcessCondition.DEAD.set(p);
		if (wasPaused) {
			p.getClock().release();
		}
	}
	
	public static void Interrupt(long id) throws MoonaHandlingException {
		CheckOn();
		if (elements.valueOf(id) instanceof Process p) {
			Interrupt(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void Interrupt(Process p) throws MoonaHandlingException, NullPointerException {
		CheckOn();
		Terminate(p);
		synchronized (p.getClock()) {
			p.end();
		}
	}
	
	public static void Fade() throws MoonaHandlingException {
		CheckOn();
		Process[] procs = new Process[totalProcesses+totalDaemons];
		for (int i = 0, c = 0; i < elements.size(); i++) {
			procs[c] = (elements.getValue(i) instanceof Process p) ? p : procs[c];
			c += (procs[c] != null) ? 1 : 0;
		}
		for (Process p : procs) {
			Terminate(p);
		}
		Moona.isOn = false;
	}
	public static void Collapse() throws MoonaHandlingException {
		CheckOn();
		Process[] procs = new Process[totalProcesses+totalDaemons];
		for (int i = 0, c = 0; i < elements.size(); i++) {
			procs[c] = (elements.getValue(i) instanceof Process p) ? p : procs[c];
			c += (procs[c] != null) ? 1 : 0;
		}
		for (Process p : procs) {
			Interrupt(p);
		}
		Moona.isOn = false;
	}
	
	public static long GenerateID() {
		return idCounter++;
	}
	
	public static Serial getElementByID(long id) throws MoonaHandlingException {
		CheckOn();
		if (elements.hasKey(id)) {
			return elements.valueOf(id);
		}
		throw new MoonaHandlingException("There is no such element with this ID: " + id + ".");
	}
	public static long totalElements() {
		return totalElements;
	}
	
	public static Process getProcessByID(long id) throws MoonaHandlingException {
		CheckOn();
		if (getElementByID(id) instanceof Process proc) {
			return proc;
		}
		throw new MoonaHandlingException("The element that corresponds to that ID is not a Process.");
	}
	public static int totalProcesses() {
		return totalProcesses;
	}
	public static int totalDaemons() {
		return totalDaemons;
	}
	
	private Moona() {
	
	}
}
