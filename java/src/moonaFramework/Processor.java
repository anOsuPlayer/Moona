package moonaFramework;

import moonaFramework.event.AutoEvent;
import moonaFramework.process.Daemon;
import moonaFramework.process.Process;
import moonaFramework.process.Worm;
import moonaFramework.annotations.Timeless;
import moonaFramework.basics.Serial;

public final class Processor extends Core {
	
	private static int totalProcesses = 0;
	
	private static int totalDaemons = 0;
	
	private static int totalWorms = 0;
	
	public static void add(Process p) throws MoonaHandlingException, NullPointerException {
		if (p == null) {
			throw new NullPointerException("You cannot add null elements to Moona.");
		}
		if (elements.has(p, p.id())) {
			throw new MoonaHandlingException("This Process already belongs to Moona.");
		}
		addProcess(p);
	}
	static void addProcess(Process p) {
		totalProcesses++;
		totalDaemons += (p instanceof Daemon) ? 1 : 0;
		totalWorms += (p instanceof Worm) ? 1 : 0;
		
		elements.add(p, p.id());
	}
	
	public static void remove(Process p) throws MoonaHandlingException, NullPointerException {
		if (p == null) {
			throw new NullPointerException("You cannot remove a null element from Moona.");
		}
		if (!elements.has(p, p.id())) {
			throw new MoonaHandlingException("This Process is not present in Moona.");
		}
		removeProcess(p);
	}
	static void removeProcess(Process p) {
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
		addProcess(p);
		p.initialize();
		ProcessCondition.RUNNING.set(p);
		p.run();
		ProcessCondition.DEAD.set(p);
		p.end();
		removeProcess(p);
	}
	
	public static void provide(long id) throws MoonaHandlingException {
		Moona.checkOn();
		if (Moona.get(id) instanceof Process p) {
			provide(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void provide(Process p) throws MoonaHandlingException, NullPointerException {
		Moona.checkOn();
		if (p == null) {
			throw new NullPointerException("You cannot provide a null Process.");
		}
		if (!ProcessCondition.DEAD.check(p)) {
			throw new MoonaHandlingException("A process cannot be provided if already running, awaiting,"
					+ " or paused.");
		}
		addProcess(p);
		ProcessCondition.AWAITING.set(p);
	}

	public static void await(long id) throws MoonaHandlingException {
		Moona.checkOn();
		if (Moona.get(id) instanceof Process p) {
			await(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void await(Process p) throws MoonaHandlingException, NullPointerException {
		Moona.checkOn();
		provide(p);
		p.initialize();
		new Thread(p, "Process#" + p.id()).start();
	}

	public static void unlock(long id) throws MoonaHandlingException {
		Moona.checkOn();
		if (Moona.get(id) instanceof Process p) {
			unlock(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void unlock(Process p) throws MoonaHandlingException, NullPointerException {
		Moona.checkOn();
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
		Moona.checkOn();
		if (Moona.get(id) instanceof Process p) {
			initiate(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void initiate(Process p) throws MoonaHandlingException, NullPointerException {
		Moona.checkOn();
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
		addProcess(p);
		ProcessCondition.RUNNING.set(p);
		new Thread(p, "Process#" + p.id()).start();
	}

	public static void start(long id) throws MoonaHandlingException {
		Moona.checkOn();
		if (Moona.get(id) instanceof Process p) {
			start(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void start(Process p) throws MoonaHandlingException, NullPointerException {
		Moona.checkOn();
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
		addProcess(p);
		ProcessCondition.RUNNING.set(p);
		initiator(p);
		new Thread(p, "Process#" + p.id()).start();
	}

	public static void flick(long id) throws MoonaHandlingException {
		Moona.checkOn();
		if (Moona.get(id) instanceof Process p) {
			flick(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void flick(Process p) throws MoonaHandlingException, NullPointerException {
		Moona.checkOn();
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
		Moona.checkOn();
		if (Moona.get(id) instanceof Process p) {
			spark(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void spark(Process p) throws MoonaHandlingException, NullPointerException {
		Moona.checkOn();
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
		Moona.checkOn();
		if (Moona.get(id) instanceof Process p) {
			terminate(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void terminate(Process p) throws MoonaHandlingException, NullPointerException {
		Moona.checkOn();
		if (p == null) {
			throw new NullPointerException("You cannot terminate a null Process.");
		}
		if (ProcessCondition.DEAD.check(p)) {
			throw new MoonaHandlingException("You can't interrupt Processes which are not running or awaiting");
		}
		boolean wasPaused = p.isPaused().verify();
		removeProcess(p);
		ProcessCondition.DEAD.set(p);
		if (wasPaused) {
			p.getClock().release();
		}
	}

	public static void interrupt(long id) throws MoonaHandlingException {
		Moona.checkOn();
		if (Moona.get(id) instanceof Process p) {
			interrupt(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public static void interrupt(Process p) throws MoonaHandlingException, NullPointerException {
		Moona.checkOn();
		terminate(p);
		synchronized (p.getClock()) {
			ender(p);
		}
	}

	public static void fade() throws MoonaHandlingException {
		Moona.checkOn();
		Process[] procs = new Process[totalProcesses];
		for (int i = 0, c = 0, pc = 0; i < elements.size(); i++) {
			if (elements.getValue(i) instanceof Process p && procs.length > 0) {
				procs[c] = p;
			}
		}
		for (Process p : procs) {
			terminate(p);
		}
	}
	public static void collapse() throws MoonaHandlingException {
		Moona.checkOn();
		Process[] procs = new Process[totalProcesses];
		for (int i = 0, c = 0; i < elements.size(); i++) {
			if (elements.getValue(i) instanceof Process p && procs.length > 0) {
				procs[c] = p;
			}
		}
		for (Process p : procs) {
			interrupt(p);
		}
	}

	private static void initiator(Process p) {
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
	private static void ender(Process p) {
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
	
	public static boolean isProcess(Serial s) {
		return s instanceof Process;
	}
	public static boolean isProcess(long id) {
		return elements.valueOf(id) instanceof Process;
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
	
	private Processor() {
		
	}
}
