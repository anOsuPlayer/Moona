package moonaFramework;

import moonaFramework.Moona.Core;
import moonaFramework.process.Process;

public class Phase implements Serial {
	
	private final long id;
	@Override
	public final long id() {
		return this.id;
	}
	@Override
	public final int nature() {
		return Natural.PHASE;
	}
	
	final Core<Serial> core;
	
	public void add(Serial s) throws MoonaHandlingException, NullPointerException {
		if (s == null) {
			throw new NullPointerException("You cannot add null elements to Phases.");
		}
		if (core.has(s, s.id())) {
			throw new MoonaHandlingException("This element already belongs to this Phase.");
		}
		core.addSerial(s);
	}
	
	public void remove(Serial s) throws MoonaHandlingException, NullPointerException {
		if (s == null) {
			throw new NullPointerException("You cannot a null element.");
		}
		if (!core.has(s, s.id())) {
			throw new MoonaHandlingException("This element is not present in this Phase.");
		}
		core.removeSerial(s);
	}
	
	public void provide(long id) throws MoonaHandlingException {
		Moona.checkOn();
		if (core.valueOf(id) instanceof Process p) {
			provide(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public void provide(Process p) throws MoonaHandlingException, NullPointerException {
		Moona.checkOn();
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

	public void await(long id) throws MoonaHandlingException {
		Moona.checkOn();
		if (core.valueOf(id) instanceof Process p) {
			await(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public void await(Process p) throws MoonaHandlingException, NullPointerException {
		Moona.checkOn();
		provide(p);
		p.initialize();
		new Thread(p, "Process#" + p.id()).start();
	}

	public void unlock(long id) throws MoonaHandlingException {
		Moona.checkOn();
		if (core.valueOf(id) instanceof Process p) {
			unlock(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public void unlock(Process p) throws MoonaHandlingException, NullPointerException {
		Moona.checkOn();
		if (p == null) {
			throw new NullPointerException();
		}
		if (!ProcessCondition.AWAITING.check(p)) {
			throw new MoonaHandlingException("A process cannot be unlocked if not awaiting.");
		}
		ProcessCondition.RUNNING.set(p);
		p.getClock().release();
	}

	public void initiate(long id) throws MoonaHandlingException {
		Moona.checkOn();
		if (core.valueOf(id) instanceof Process p) {
			initiate(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public void initiate(Process p) throws MoonaHandlingException, NullPointerException {
		Moona.checkOn();
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

	public void start(long id) throws MoonaHandlingException {
		Moona.checkOn();
		if (core.valueOf(id) instanceof Process p) {
			start(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public void start(Process p) throws MoonaHandlingException, NullPointerException {
		Moona.checkOn();
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
		Moona.initiator(p);
		new Thread(p, "Process#" + p.id()).start();
	}

	public void flick(long id) throws MoonaHandlingException {
		Moona.checkOn();
		if (core.valueOf(id) instanceof Process p) {
			flick(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public void flick(Process p) throws MoonaHandlingException, NullPointerException {
		Moona.checkOn();
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

	public void spark(long id) throws MoonaHandlingException {
		Moona.checkOn();
		if (core.valueOf(id) instanceof Process p) {
			spark(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public void spark(Process p) throws MoonaHandlingException, NullPointerException {
		Moona.checkOn();
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

	public void terminate(long id) throws MoonaHandlingException {
		Moona.checkOn();
		if (core.valueOf(id) instanceof Process p) {
			terminate(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public void terminate(Process p) throws MoonaHandlingException, NullPointerException {
		Moona.checkOn();
		if (p == null) {
			throw new NullPointerException();
		}
		if (ProcessCondition.DEAD.check(p)) {
			throw new MoonaHandlingException("You cannot interrupt a process which is not running"
					+ " or awaiting.");
		}
		boolean wasPaused = p.isPaused().verify();
		core.removeSerial(p);
		ProcessCondition.DEAD.set(p);
		if (wasPaused) {
			p.getClock().release();
		}
	}

	public void interrupt(long id) throws MoonaHandlingException {
		Moona.checkOn();
		if (core.valueOf(id) instanceof Process p) {
			interrupt(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public void interrupt(Process p) throws MoonaHandlingException, NullPointerException {
		Moona.checkOn();
		terminate(p);
		synchronized (p.getClock()) {
			Moona.ender(p);
		}
	}

	public void fade() throws MoonaHandlingException {
		Moona.checkOn();
		Process[] procs = new Process[core.totalProcesses()];
		for (int i = 0, c = 0; i < core.size(); i++) {
			procs[c] = (core.getValue(i) instanceof Process p) ? p : procs[c += (procs[c] != null) ? 1 : 0];
		}
		for (Process p : procs) {
			terminate(p);
		}
	}
	public void collapse() throws MoonaHandlingException {
		Moona.checkOn();
		Process[] procs = new Process[core.totalProcesses()];
		for (int i = 0, c = 0; i < core.size(); i++) {
			procs[c] = (core.getValue(i) instanceof Process p) ? p : procs[c += (procs[c] != null) ? 1 : 0];
		}
		for (Process p : procs) {
			interrupt(p);
		}
	}
	
	public Serial get(long id) {
		return core.valueOf(id);
	}
	public Process getProcess(long id) throws MoonaHandlingException {
		return core.valueOf(id) != null ? (core.valueOf(id) instanceof Process) ? (Process) core.valueOf(id)
				: null : null;
	}
	
	public boolean has(Serial s) {
		return core.has(s, s.id());
	}
	
	public int elementCount() {
		return core.size();
	}
	
	public int processCount() {
		return core.totalProcesses();
	}
	public int daemonCount() {
		return core.totalDaemons();
	}
	
	public Phase() {
		this.core = new Core<>();
		this.id = Moona.generateID();
		Moona.add(this);
	}
}
