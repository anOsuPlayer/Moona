package moonaframework.dynamic;

import java.util.ArrayList;
import java.util.List;

import moonaframework.base.Moona;
import moonaframework.base.MoonaHandlingException;
import moonaframework.base.MoonaObject;
import moonaframework.dynamic.event.AutoEvent;
import moonaframework.dynamic.event.Event;
import moonaframework.dynamic.process.Daemon;
import moonaframework.dynamic.process.Process;
import moonaframework.dynamic.process.Worm;
import moonaframework.util.annotation.Timeless;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.Method;
import moonaframework.util.reflection.Mirror;
import moonaframework.util.reflection.Reflection;
import moonaframework.util.reflection.flare.Annotated;

public final class Processor {
	
	static final List<Process> processes = new ArrayList<>();
	
	private static int totalProcesses = 0;
	
	private static int totalDaemons = 0;
	
	private static int totalWorms = 0;
	
	public static void add(Process p) throws MoonaHandlingException, NullArgumentException {
		if (p == null) {
			throw new NullArgumentException("You cannot add null elements to Moona.");
		}
		if (has(p)) {
			throw new MoonaHandlingException("This Process already belongs to Moona.");
		}
		addProcess(p);
	}
	public static void add(Process...processes) throws MoonaHandlingException, NullArgumentException {
		for (Process p : processes) {
			add(p);
		}
	}
	static void filteredAdd(Process p) {
		if (!processes.contains(p)) {
			addProcess(p);
		}
	}
	static void addProcess(Process p) {
		totalProcesses++;
		totalDaemons += (p instanceof Daemon) ? 1 : 0;
		totalWorms += (p instanceof Worm) ? 1 : 0;
		
		processes.add(p);
	}
	
	public static void remove(Process p) throws MoonaHandlingException, NullArgumentException {
		if (p == null) {
			throw new NullArgumentException("You cannot remove a null element from Moona.");
		}
		if (!has(p)) {
			throw new MoonaHandlingException("This Process is not present in Moona.");
		}
		removeProcess(p);
	}
	public static void remove(Process...processes) throws MoonaHandlingException, NullArgumentException {
		for (Process p : processes) {
			remove(p);
		}
	}
	static void filteredRemove(Process p) {
		if (processes.contains(p)) {
			removeProcess(p);
		}
	}
	static void removeProcess(Process p) {
		totalProcesses--;
		totalDaemons -= (p instanceof Daemon) ? 1 : 0;
		totalWorms -= (p instanceof Worm) ? 1 : 0;
		
		processes.remove(p);
	}
	
	static void buildProcess(Process p) {
		new Thread(p, "Process#" + Moona.generateID()).start();
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

	public static void await(Process p) throws MoonaHandlingException, NullArgumentException {
		Moona.checkOn();
		provide(p);
		p.initialize();
		buildProcess(p);
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
				procs[c++] = processes.get(i);
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
				procs[c++] = processes.get(i);
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
		Annotated initiator = Mirror.filterMethods().filterByName("initialize").filterByClass(p.getClass()).evaluate().getAnnotated();
		try {
			if (initiator != null && initiator.hasAnnotation(Timeless.class)) {
				Agent.include(new Event() {
					public @Override void trigger() {
						p.initialize();
					}
				});
				return;
			}
		}
		catch (UndefinedReflectionException ure) {
			ure.printStackTrace();
		}
		p.initialize();
	}
	private static void ender(Process p) {
		Annotated ender = Mirror.getProcessEnder(p);
		try {
			if (ender != null && ender.hasAnnotation(Timeless.class)) {
				Agent.include(new Event() {
					public @Override void trigger() {
						p.end();
					}
				});
				return;
			}
		}
		catch (UndefinedReflectionException ure) {
			ure.printStackTrace();
		}
		p.end();
	}

	public static boolean isProcess(MoonaObject mo) {
		return mo instanceof Process;
	}
	
	public static boolean contains(MoonaObject mo) {
		return mo instanceof Process p ? processes.contains(p) : false;
	}
	public static boolean has(Process p) {
		return processes.contains(p);
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
