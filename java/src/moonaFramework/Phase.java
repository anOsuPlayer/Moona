package moonaFramework;

import moonaFramework.essentials.Natural;
import moonaFramework.event.AutoEvent;
import moonaFramework.process.Daemon;
import moonaFramework.process.Process;
import moonaFramework.process.Worm;
import moonaFramework.annotations.Timeless;

public class Phase extends Moona {
	
	protected static int totalProcesses = 0;
	
	protected static int totalDaemons = 0;
	
	protected static int totalWorms = 0;
	
	public static void add(Process p) throws MoonaHandlingException, NullPointerException {
		if (p == null) {
			throw new NullPointerException("You cannot add null elements to Phases.");
		}
		if (elements.has(p, p.id())) {
			throw new MoonaHandlingException("This element already belongs to this Phase.");
		}
		addProcess(p);
	}
	protected static void addProcess(Process p) {
		totalProcesses++;
		totalDaemons += (p instanceof Daemon) ? 1 : 0;
		totalWorms += (p instanceof Worm) ? 1 : 0;
		
		elements.add(p, p.id());
	}
	
	public static void remove(Process p) throws MoonaHandlingException, NullPointerException {
		if (p == null) {
			throw new NullPointerException("You cannot a null element.");
		}
		if (!elements.has(p, p.id())) {
			throw new MoonaHandlingException("This element is not present in this Phase.");
		}
		removeProcess(p);
	}
	protected static void removeProcess(Process p) {
		totalProcesses--;
		totalDaemons -= (p instanceof Daemon) ? 1 : 0;
		totalWorms -= (p instanceof Worm) ? 1 : 0;
		
		elements.remove(p, p.id());
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
		if (Moona.get(id) instanceof Process p) {
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
		if (Moona.get(id) instanceof Process p) {
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
		if (Moona.get(id) instanceof Process p) {
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
		if (Moona.get(id) instanceof Process p) {
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
		if (Moona.get(id) instanceof Process p) {
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
		if (Moona.get(id) instanceof Process p) {
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
		if (Moona.get(id) instanceof Process p) {
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
		if (Moona.get(id) instanceof Process p) {
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
		remove(p);
		ProcessCondition.DEAD.set(p);
		if (wasPaused) {
			p.getClock().release();
		}
	}

	public static void interrupt(long id) throws MoonaHandlingException {
		checkOn();
		if (Moona.get(id) instanceof Process p) {
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
		Process[] procs = new Process[totalProcesses];
		for (int i = 0, c = 0, pc = 0; i < elements.size(); i++) {
			if (procs.length > 0) {
				procs[c] = (elements.getValue(i).nature() == Natural.PROCESS) ?
						(Process) elements.getValue(i) : procs[c];
				c += (elements.getValue(i).nature() == Natural.PROCESS) ? 1 : 0;
			}
		}
		for (Process p : procs) {
			terminate(p);
		}
		Moona.isOn = false;
	}
	public static void collapse() throws MoonaHandlingException {
		checkOn();
		Process[] procs = new Process[totalProcesses];
		for (int i = 0, c = 0, pc = 0; i < elements.size(); i++) {
			if (procs.length > 0) {
				procs[c] = (elements.getValue(i).nature() == Natural.PROCESS) ?
						(Process) elements.getValue(i) : procs[c];
				c += (elements.getValue(i).nature() == Natural.PROCESS) ? 1 : 0;
			}
		}
		for (Process p : procs) {
			interrupt(p);
		}
		Moona.isOn = false;
	}

	private static final void initiator(Process p) {
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
	private static final void ender(Process p) {
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
	
	public static Process get(long id) {
		return isProcess(id) ? (Process) elements.valueOf(id) : null;
	}
	
	public static int totalProcesses() {
		return totalProcesses;
	}
	
	public static int totalDaemons() {
		return totalDaemons;
	}
	
	public static int totalWorms() {
		return totalWorms;
	}
	
	private Phase() {
		
	}
}
