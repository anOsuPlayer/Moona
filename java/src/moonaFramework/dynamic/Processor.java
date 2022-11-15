package moonaFramework.dynamic;

import moonaFramework.base.Agent;
import moonaFramework.base.Mirror;
import moonaFramework.base.Moona;
import moonaFramework.base.MoonaHandlingException;
import moonaFramework.base.Natural;
import moonaFramework.base.Serial;
import moonaFramework.dynamic.event.AbstractEvent;
import moonaFramework.dynamic.process.Process;
import moonaFramework.util.annotations.Timeless;
import moonaFramework.util.collection.IshMap;
import moonaFramework.util.reflection.Annotated;

public final class Processor {
	
	private static final IshMap<Process, Long> processes = new IshMap<>();
	
	private static int totalProcesses = 0;
	
	private static int totalDaemons = 0;
	
	private static int totalWorms = 0;
	
	public static void add(Process p) throws MoonaHandlingException, NullPointerException {
		if (p == null) {
			throw new NullPointerException("You cannot add null elements to Moona.");
		}
		if (processes.hasKey(p.id())) {
			throw new MoonaHandlingException("This Process already belongs to Moona.");
		}
		addProcess(p);
	}
	static void addProcess(Process p) {
		totalProcesses++;
		totalDaemons += (p.nature() == Natural.DAEMON) ? 1 : 0;
		totalWorms += (p.nature() == Natural.WORM) ? 1 : 0;
		
		processes.add(p, p.id());
	}
	
	public static void remove(Process p) throws MoonaHandlingException, NullPointerException {
		if (p == null) {
			throw new NullPointerException("You cannot remove a null element from Moona.");
		}
		if (!processes.hasKey(p.id())) {
			throw new MoonaHandlingException("This Process is not present in Moona.");
		}
		removeProcess(p);
	}
	static void removeProcess(Process p) {
		totalProcesses--;
		totalDaemons -= (p.nature() == Natural.DAEMON) ? 1 : 0;
		totalWorms -= (p.nature() == Natural.WORM) ? 1 : 0;
		
		processes.remove(p, p.id());
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
		new Thread(p).start();
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
		new Thread(p).start();
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
			ProcessCondition.PAUSED.set(p);
			p.getClock().stasys();
			synchronized (p.getClock()) {
				p.onPause();
			}
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
		for (int i = 0, c = 0; i < processes.size(); i++) {
			if (procs.length != 0) {
				procs[c++] = processes.getValue(i);
			}
		}
		for (Process p : procs) {
			terminate(p);
		}
	}
	public static void collapse() throws MoonaHandlingException {
		Moona.checkOn();
		Process[] procs = new Process[totalProcesses];
		for (int i = 0, c = 0; i < processes.size(); i++) {
			if (procs.length != 0) {
				procs[c++] = processes.getValue(i);
			}
		}
		for (Process p : procs) {
			interrupt(p);
		}
	}

	private static void initiator(Process p) {
		if (Mirror.getAnnotatedMethod(p.getClass(), Timeless.class, "initialize", Annotated.NO_ARGS).evaluate()) {
			Agent.add(new AbstractEvent() {
				public void trigger() {
					p.end();
				}
			});
			return;
		}
		p.initialize();
	}
	private static void ender(Process p) {
		if (Mirror.getAnnotatedMethod(p.getClass(), Timeless.class, "end", Annotated.NO_ARGS).evaluate()) {
			Agent.add(new AbstractEvent() {
				public void trigger() {
					p.end();
				}
			});
			return;
		}
		p.end();
	}
	
	public static Process get(long id) {
		return isProcess(id) ? processes.valueOf(id) : null;
	}
	
	public static boolean isProcess(long id) {
		return processes.hasKey(id);
	}
	public static boolean isProcess(Serial s) {
		return s instanceof Process;
	}
	
	public static boolean contains(Serial s) {
		return s instanceof Process p ? processes.hasKey(p.id()) : false;
	}
	public static boolean has(long id) {
		return processes.hasKey(id);
	}
	public static boolean has(Process p) {
		return has(p.id());
	}
	
	public static int totalProcesses() {
		return totalProcesses;
	}
	
	public static int processCount() {
		return totalProcesses - totalDaemons - totalWorms;
	}
	public static int daemonCount() {
		return totalDaemons;
	}
	public static int wormCount() {
		return totalWorms;
	}
	
	private Processor() {
		
	}
}
