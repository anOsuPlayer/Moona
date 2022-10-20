package moonaFramework;

import moonaFramework.process.Process;
import moonaFramework.process.Devil;
import moonaFramework.util.IshMap;

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
	
	final IshMap<Serial, Long> elements;
	
	private int processCount = 0;
	
	private int daemonCount = 0;
	
	public void add(Serial s) throws MoonaHandlingException, NullPointerException {
		if (s == null) {
			throw new NullPointerException("You cannot add null elements to Phases.");
		}
		if (elements.has(s, s.id())) {
			throw new MoonaHandlingException("This element already belongs to this Phase.");
		}
		if (Natural.isPhasic(s)) {
			throw new MoonaHandlingException("You cannot add a Phase to another Phase.");
		}
		if (s.nature() == Natural.DEVIL) {
			if (!((Devil) s).getHost().equals(this)) {
				throw new MoonaHandlingException("A Devil cannot be part of a Phase which differs from"
						+ " its host.");
			}
		}
		addSerial(s);
	}
	void addSerial(Serial s) {
		if (!elements.has(s, s.id())) {
			elements.add(s, s.id());
			if (s.nature() == Natural.PROCESS) {
				processCount++;
				Moona.newProcess(true);
			}
			if (s.nature() == Natural.DAEMON) {
				daemonCount++;
				Moona.newDaemon(true);
			}
		}
	}
	
	public void remove(Serial s) throws MoonaHandlingException, NullPointerException {
		if (s == null) {
			throw new NullPointerException("You cannot a null element.");
		}
		if (!elements.has(s, s.id())) {
			throw new MoonaHandlingException("This element is not present in this Phase.");
		}
		removeSerial(s);
	}
	void removeSerial(Serial s) {
		if (elements.has(s, s.id())) {
			elements.remove(s, s.id());
			if (s.nature() == Natural.PROCESS) {
				processCount--;
				Moona.newProcess(false);
			}
			if (s.nature() == Natural.DAEMON) {
				daemonCount--;
				Moona.newDaemon(false);
			}
		}
	}
	
	public void provide(long id) throws MoonaHandlingException {
		Moona.checkOn();
		if (elements.valueOf(id) instanceof Process p) {
			provide(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public void provide(Process p) throws MoonaHandlingException, NullPointerException {
		Moona.checkOn();
		if (p == null) {
			throw new NullPointerException("You cannot provide a null Process.");
		}
		if (!ProcessCondition.DEAD.check(p)) {
			throw new MoonaHandlingException("A process cannot be provided if already running, awaiting,"
					+ " or paused.");
		}
		if (p.nature() == Natural.DEVIL) {
			if (!((Devil) p).getHost().equals(this)) {
				throw new MoonaHandlingException("A Devil cannot be part of a Phase which differs from"
						+ " its host.");
			}
		}
		addSerial(p);
		ProcessCondition.AWAITING.set(p);
	}

	public void await(long id) throws MoonaHandlingException {
		Moona.checkOn();
		if (elements.valueOf(id) instanceof Process p) {
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
		if (elements.valueOf(id) instanceof Process p) {
			unlock(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public void unlock(Process p) throws MoonaHandlingException, NullPointerException {
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

	public void initiate(long id) throws MoonaHandlingException {
		Moona.checkOn();
		if (elements.valueOf(id) instanceof Process p) {
			initiate(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public void initiate(Process p) throws MoonaHandlingException, NullPointerException {
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
		if (p.nature() == Natural.DEVIL) {
			if (!((Devil) p).getHost().equals(this)) {
				throw new MoonaHandlingException("A Devil cannot be part of a Phase which differs from"
						+ " its host.");
			}
		}
		addSerial(p);
		ProcessCondition.RUNNING.set(p);
		new Thread(p, "Process#" + p.id()).start();
	}

	public void start(long id) throws MoonaHandlingException {
		Moona.checkOn();
		if (elements.valueOf(id) instanceof Process p) {
			start(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public void start(Process p) throws MoonaHandlingException, NullPointerException {
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
		if (p.nature() == Natural.DEVIL) {
			if (!((Devil) p).getHost().equals(this)) {
				throw new MoonaHandlingException("A Devil cannot be part of a Phase which differs from"
						+ " its host.");
			}
		}
		addSerial(p);
		ProcessCondition.RUNNING.set(p);
		Moona.initiator(p);
		new Thread(p, "Process#" + p.id()).start();
	}
	
	public void flick(long id) throws MoonaHandlingException {
		Moona.checkOn();
		if (elements.valueOf(id) instanceof Process p) {
			flick(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public void flick(Process p) throws MoonaHandlingException, NullPointerException {
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

	public void spark(long id) throws MoonaHandlingException {
		Moona.checkOn();
		if (elements.valueOf(id) instanceof Process p) {
			spark(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public void spark(Process p) throws MoonaHandlingException, NullPointerException {
		Moona.checkOn();
		if (p == null) {
			throw new NullPointerException("You cannot spark a null Process");
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
		if (elements.valueOf(id) instanceof Process p) {
			terminate(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	public void terminate(Process p) throws MoonaHandlingException, NullPointerException {
		Moona.checkOn();
		if (p == null) {
			throw new NullPointerException("You cannot terminate a null Process.");
		}
		if (ProcessCondition.DEAD.check(p)) {
			throw new MoonaHandlingException("You can't interrupt Processes which are not running or awaiting");
		}
		if (p.nature() == Natural.DEVIL) {
			if (!((Devil) p).getHost().equals(this)) {
				throw new MoonaHandlingException("A Devil cannot be part of a Phase which differs from"
						+ " its host.");
			}
		}
		boolean wasPaused = p.isPaused().verify();
		removeSerial(p);
		ProcessCondition.DEAD.set(p);
		if (wasPaused) {
			p.getClock().release();
		}
	}

	public void interrupt(long id) throws MoonaHandlingException {
		Moona.checkOn();
		if (elements.valueOf(id) instanceof Process p) {
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
		Process[] procs = new Process[processCount];
		for (int i = 0, c = 0; i < elements.size(); i++) {
			if (procs.length > 0) {
				procs[c] = (elements.getValue(i).nature() == Natural.PROCESS) ?
						(Process) elements.getValue(i) : procs[c];
				c += (elements.getValue(i).nature() == Natural.PROCESS) ? 1 : 0;
			}
		}
		for (Process p : procs) {
			terminate(p);
		}
	}
	public void collapse() throws MoonaHandlingException {
		Moona.checkOn();
		Process[] procs = new Process[processCount];
		for (int i = 0, c = 0; i < elements.size(); i++) {
			if (procs.length > 0) {
				procs[c] = (elements.getValue(i).nature() == Natural.PROCESS) ?
						(Process) elements.getValue(i) : procs[c];
				c += (elements.getValue(i).nature() == Natural.PROCESS) ? 1 : 0;
			}
		}
		for (Process p : procs) {
			interrupt(p);
		}
	}
	
	public Serial get(long id) {
		return elements.valueOf(id);
	}
	public Process getProcess(long id) throws MoonaHandlingException {
		return elements.valueOf(id) != null ? (elements.valueOf(id) instanceof Process) ? (Process) elements.valueOf(id)
				: null : null;
	}
	
	public boolean has(Serial s) {
		return elements.has(s, s.id());
	}
	
	public int elementCount() {
		return elements.size();
	}
	
	public int processCount() {
		return processCount;
	}
	public int daemonCount() {
		return daemonCount;
	}
	
	public Phase() {
		this.elements = new IshMap<>();
		this.id = Moona.generateID();
		Moona.add(this);
	}
}
