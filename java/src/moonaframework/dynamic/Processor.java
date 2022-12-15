package moonaframework.dynamic;

import java.util.ArrayList;
import java.util.List;

import moonaframework.base.Agent;
import moonaframework.base.Mirror;
import moonaframework.base.Moona;
import moonaframework.base.MoonaHandlingException;
import moonaframework.base.Nature;
import moonaframework.base.Serial;
import moonaframework.dynamic.event.AbstractEvent;
import moonaframework.dynamic.process.Process;
import moonaframework.util.annotations.Timeless;
import moonaframework.util.collection.IshMap;
import moonaframework.util.exceptions.NullArgumentException;
import moonaframework.util.reflection.Annotated;

public final class Processor {
	
	static final IshMap<Process, Long> processes = new IshMap<>();
	
	static final List<Long> uniques = new ArrayList<>();
	
	private static int totalProcesses = 0;
	
	private static int totalDaemons = 0;
	
	private static int totalWorms = 0;
	
	public static void add(Process p) throws MoonaHandlingException, NullArgumentException {
		if (p == null) {
			throw new NullArgumentException("You cannot add null elements to Moona.");
		}
		if (processes.hasKey(p.id())) {
			throw new MoonaHandlingException("This Process already belongs to Moona.");
		}
		addProcess(p);
	}
	static void filteredAdd(Process p) {
		if (!processes.hasKey(p.id())) {
			addProcess(p);
		}
	}
	static void addProcess(Process p) {
		totalProcesses++;
		totalDaemons += (p.nature() == Nature.DAEMON) ? 1 : 0;
		totalWorms += (p.nature() == Nature.WORM) ? 1 : 0;
		
		processes.add(p, p.id());
	}
	
	public static void remove(Process p) throws MoonaHandlingException, NullArgumentException {
		if (p == null) {
			throw new NullArgumentException("You cannot remove a null element from Moona.");
		}
		if (!processes.hasKey(p.id())) {
			throw new MoonaHandlingException("This Process is not present in Moona.");
		}
		removeProcess(p);
	}
	static void filteredRemove(Process p) {
		if (processes.hasKey(p.id())) {
			removeProcess(p);
		}
	}
	static void removeProcess(Process p) {
		totalProcesses--;
		totalDaemons -= (p.nature() == Nature.DAEMON) ? 1 : 0;
		totalWorms -= (p.nature() == Nature.WORM) ? 1 : 0;
		
		processes.remove(p, p.id());
	}
	
	static void buildProcess(Process p) {
		new Thread(p, "Process#" + p.id()).start();
	}
	
	public @Deprecated static void mainStart(Process p) throws MoonaHandlingException, NullArgumentException {
		if (p == null) {
			throw new NullArgumentException("The Process you're trying to start is null.");
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
		if (has(id)) {
			provide(processes.valueOf(id));
			return;
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " Process.");
	}
	public static void provide(Process p) throws MoonaHandlingException, NullArgumentException {
		Moona.checkOn();
		if (p == null) {
			throw new NullArgumentException("You cannot provide a null Process.");
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
		if (has(id)) {
			await(processes.valueOf(id));
			return;
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " Process.");
	}
	public static void await(Process p) throws MoonaHandlingException, NullArgumentException {
		Moona.checkOn();
		provide(p);
		p.initialize();
		buildProcess(p);
	}

	public static void unlock(long id) throws MoonaHandlingException {
		Moona.checkOn();
		if (has(id)) {
			unlock(processes.valueOf(id));
			return;
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " Process.");
	}
	public static void unlock(Process p) throws MoonaHandlingException, NullArgumentException {
		Moona.checkOn();
		if (p == null) {
			throw new NullArgumentException("You cannot unlock a null Process.");
		}
		if (!ProcessCondition.AWAITING.check(p)) {
			throw new MoonaHandlingException("A process cannot be unlocked if not awaiting.");
		}
		ProcessCondition.RUNNING.set(p);
		p.getClock().release();
	}

	public static void initiate(long id) throws MoonaHandlingException {
		Moona.checkOn();
		if (has(id)) {
			initiate(processes.valueOf(id));
			return;
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " Process.");
	}
	public static void initiate(Process p) throws MoonaHandlingException, NullArgumentException {
		Moona.checkOn();
		if (p == null) {
			throw new NullArgumentException("You cannot initiate a null Process.");
		}
		if (p.isRunning()) {
			throw new MoonaHandlingException("The Process is already running.");
		}
		if (ProcessCondition.AWAITING.check(p)) {
			throw new MoonaHandlingException("An awaiting process cannot be initiated: you need to"
					+ " unlock it.");	
		}
		addProcess(p);
		ProcessCondition.RUNNING.set(p);
		buildProcess(p);
	}

	public static void start(long id) throws MoonaHandlingException {
		Moona.checkOn();
		if (has(id)) {
			start(processes.valueOf(id));
			return;
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " Process.");
	}
	public static void start(Process p) throws MoonaHandlingException, NullArgumentException {
		Moona.checkOn();
		if (p == null) {
			throw new NullArgumentException("You cannot start a null Process.");
		}
		if (p.isRunning()) {
			throw new MoonaHandlingException("The Process is already running.");
		}
		if (ProcessCondition.AWAITING.check(p)) {
			throw new MoonaHandlingException("An awaiting process cannot be started: you need to"
					+ " unlock it.");	
		}
		addProcess(p);
		ProcessCondition.RUNNING.set(p);
		initiator(p);
		buildProcess(p);
	}

	public static void flick(long id) throws MoonaHandlingException {
		Moona.checkOn();
		if (has(id)) {
			flick(processes.valueOf(id));
			return;
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " Process.");
	}
	public static void flick(Process p) throws MoonaHandlingException, NullArgumentException {
		Moona.checkOn();
		if (p == null) {
			throw new NullArgumentException("You cannot flick a null Process.");
		}
		if (ProcessCondition.DEAD.check(p) || ProcessCondition.AWAITING.check(p)) {
			throw new MoonaHandlingException("The Process needs to be running in order to be able to pause"
					+ " it.");
		}
		if (p.isPaused()) {
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
		if (has(id)) {
			spark(processes.valueOf(id));
			return;
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " Process.");
	}
	public static void spark(Process p) throws MoonaHandlingException, NullArgumentException {
		Moona.checkOn();
		if (p == null) {
			throw new NullArgumentException("You cannot spark a null Process.");
		}
		if (ProcessCondition.DEAD.check(p) || ProcessCondition.AWAITING.check(p)) {
			throw new MoonaHandlingException("The Process needs to be running in order to be able to pause"
					+ " it.");
		}
		if (p.isPaused()) {
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

	static void terminateProcess(Process p) {
		boolean wasPaused = p.isPaused();
		removeProcess(p);
		ProcessCondition.DEAD.set(p);
		if (wasPaused) {
			p.getClock().release();
		}
	}
	
	public static void terminate(long id) throws MoonaHandlingException {
		Moona.checkOn();
		if (has(id)) {
			terminate(processes.valueOf(id));
			return;
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " Process.");
	}
	public static void terminate(Process p) throws MoonaHandlingException, NullArgumentException {
		Moona.checkOn();
		if (p == null) {
			throw new NullArgumentException("You cannot terminate a null Process.");
		}
		if (ProcessCondition.DEAD.check(p)) {
			throw new MoonaHandlingException("You can't interrupt Processes which are not running or awaiting");
		}
		terminateProcess(p);
	}

	public static void interrupt(long id) throws MoonaHandlingException {
		Moona.checkOn();
		if (has(id)) {
			interrupt(processes.valueOf(id));
			return;
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " Process.");
	}
	public static void interrupt(Process p) throws MoonaHandlingException, NullArgumentException {
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
			terminateProcess(p);
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
			terminateProcess(p);
			synchronized (p.getClock()) {
				ender(p);
			}
		}
	}

	private static void initiator(Process p) {
//		if (Mirror.getAnnotatedMethod(p.getClass(), Timeless.class, "initialize", Annotated.NO_ARGS).evaluate()) {
//			Agent.include(new AbstractEvent() {
//				public void trigger() {
//					p.initialize();
//				}
//			});
//			return;
//		}
		p.initialize();
	}
	private static void ender(Process p) {
//		if (Mirror.getAnnotatedMethod(p.getClass(), Timeless.class, "end", Annotated.NO_ARGS).evaluate()) {
//			Agent.exclude(new AbstractEvent() {
//				public void trigger() {
//					p.end();
//				}
//			});
//			return;
//		}
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
	public static int totalDaemons() {
		return totalDaemons;
	}
	public static int totalWorms() {
		return totalWorms;
	}
	
	private Processor() {
		
	}
}
