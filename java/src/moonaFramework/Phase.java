package moonaFramework;

import moonaFramework.process.Process;
import moonaFramework.process.ProcessHandler;
import moonaFramework.util.IshMap;

public class Phase implements Serial, ProcessHandler {
	
	private final long id;
	@Override
	public final long id() {
		return this.id;
	}
	@Override
	public final int nature() {
		return Natural.PHASE;
	}
	
	public void MainStart(Process p) {
		Moona.CheckOn();
		Add(p);
		ProcessCondition.RUNNING.set(p);
		p.initialize();
		p.run();
		Remove(p);
		ProcessCondition.DEAD.set(p);
		p.end();
	}
	
	final IshMap<Serial, Long> elements = new IshMap<>();
	
	public void Add(Serial s) throws MoonaHandlingException, NullPointerException {
		Moona.CheckOn();
		if (s == null) {
			throw new NullPointerException();
		}
		if (elements.has(s, s.id())) {
			throw new MoonaHandlingException("Moona already contains this element.");
		}
		Moona.FilteredAdd(this, s);
	}
	public void Remove(Serial s) throws MoonaHandlingException, NullPointerException {
		Moona.CheckOn();
		if (s == null) {
			throw new NullPointerException();
		}
		if (!elements.has(s, s.id())) {
			throw new MoonaHandlingException("Moona does not contain this element.");
		}
		Moona.FilteredRemove(this, s);
	}
	
	public void Provide(long id) throws MoonaHandlingException {
		Moona.CheckOn();
		if (elements.valueOf(id) instanceof Process p) {
			Provide(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	@Override
	public void Provide(Process p) throws MoonaHandlingException, NullPointerException {
		Moona.CheckOn();
		if (p == null) {
			throw new NullPointerException();
		}
		if (!ProcessCondition.DEAD.check(p)) {
			throw new MoonaHandlingException("A process cannot be provided if already running, awaiting,"
					+ " or paused.");
		}
		Moona.FilteredAdd(this, p);
		ProcessCondition.AWAITING.set(p);
	}
	
	public void Await(long id) throws MoonaHandlingException {
		Moona.CheckOn();
		if (elements.valueOf(id) instanceof Process p) {
			Await(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	@Override
	public void Await(Process p) throws MoonaHandlingException, NullPointerException {
		Moona.CheckOn();
		Provide(p);
		p.initialize();
		new Thread(p, "Process#" + p.id()).start();
	}
	
	public void Unlock(long id) throws MoonaHandlingException {
		Moona.CheckOn();
		if (elements.valueOf(id) instanceof Process p) {
			Unlock(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	@Override
	public void Unlock(Process p) throws MoonaHandlingException, NullPointerException {
		Moona.CheckOn();
		if (p == null) {
			throw new NullPointerException();
		}
		if (!ProcessCondition.AWAITING.check(p)) {
			throw new MoonaHandlingException("A process cannot be unlocked if not awaiting.");
		}
		ProcessCondition.RUNNING.set(p);
		p.getClock().release();
	}
	
	public void Initiate(long id) throws MoonaHandlingException {
		Moona.CheckOn();
		if (elements.valueOf(id) instanceof Process p) {
			Initiate(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	@Override
	public void Initiate(Process p) throws MoonaHandlingException, NullPointerException {
		Moona.CheckOn();
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
		Moona.FilteredAdd(this, p);
		ProcessCondition.RUNNING.set(p);
		new Thread(p, "Process#" + p.id()).start();
	}
	
	public void Start(long id) throws MoonaHandlingException {
		Moona.CheckOn();
		if (elements.valueOf(id) instanceof Process p) {
			Start(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	@Override
	public void Start(Process p) throws MoonaHandlingException, NullPointerException {
		Moona.CheckOn();
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
		Moona.FilteredAdd(this, p);
		ProcessCondition.RUNNING.set(p);
		p.initialize();
		new Thread(p, "Process#" + p.id()).start();
	}
	
	public void Flick(long id) throws MoonaHandlingException {
		Moona.CheckOn();
		if (elements.valueOf(id) instanceof Process p) {
			Flick(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	@Override
	public void Flick(Process p) throws MoonaHandlingException, NullPointerException {
		Moona.CheckOn();
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
	
	public void Spark(long id) throws MoonaHandlingException {
		Moona.CheckOn();
		if (elements.valueOf(id) instanceof Process p) {
			Spark(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	@Override
	public void Spark(Process p) throws MoonaHandlingException, NullPointerException {
		Moona.CheckOn();
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
	
	public void Terminate(long id) throws MoonaHandlingException {
		Moona.CheckOn();
		if (elements.valueOf(id) instanceof Process p) {
			Terminate(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	@Override
	public void Terminate(Process p) throws MoonaHandlingException, NullPointerException {
		Moona.CheckOn();
		if (p == null) {
			throw new NullPointerException();
		}
		if (ProcessCondition.DEAD.check(p)) {
			throw new MoonaHandlingException("You cannot interrupt a process which is not running"
					+ " or awaiting.");
		}
		boolean wasPaused = p.isPaused().verify();
		Moona.FilteredRemove(this, p);
		ProcessCondition.DEAD.set(p);
		if (wasPaused) {
			p.getClock().release();
		}
	}
	
	public void Interrupt(long id) throws MoonaHandlingException {
		Moona.CheckOn();
		if (elements.valueOf(id) instanceof Process p) {
			Interrupt(p);
		}
		throw new MoonaHandlingException("The given ID either doesn't exist or does not correspond to a"
				+ " process.");
	}
	@Override
	public void Interrupt(Process p) throws MoonaHandlingException, NullPointerException {
		Moona.CheckOn();
		Terminate(p);
		synchronized (p.getClock()) {
			p.end();
		}
	}
	
	public void Fade() throws MoonaHandlingException {
		Moona.CheckOn();
		Process[] procs = new Process[processCount+daemonCount];
		for (int i = 0, c = 0; i < elements.size(); i++) {
			procs[c] = (elements.getValue(i) instanceof Process p) ? p : procs[c];
			c += (procs[c] != null) ? 1 : 0;
		}
		for (Process p : procs) {
			Terminate(p);
		}
		Moona.isOn = false;
	}
	public void Collapse() throws MoonaHandlingException {
		Moona.CheckOn();
		Process[] procs = new Process[processCount+daemonCount];
		for (int i = 0, c = 0; i < elements.size(); i++) {
			procs[c] = (elements.getValue(i) instanceof Process p) ? p : procs[c];
			c += (procs[c] != null) ? 1 : 0;
		}
		for (Process p : procs) {
			Interrupt(p);
		}
		Moona.isOn = false;
	}

	long elementCount = 0;
	public long elementCount() {
		return elementCount;
	}
	
	public Serial getElementByID(long id) throws MoonaHandlingException {
		Moona.CheckOn();
		if (elements.hasKey(id)) {
			return elements.valueOf(id);
		}
		throw new MoonaHandlingException("There is no such element with this ID: " + id + ".");
	}
	
	int processCount = 0;
	public int processCount() {
		return processCount;
	}
	
	int daemonCount = 0;
	public int daemonCount() {
		return daemonCount;
	}
	
	public Process getProcessByID(long id) throws MoonaHandlingException {
		Moona.CheckOn();
		if (getElementByID(id) instanceof Process proc) {
			return proc;
		}
		throw new MoonaHandlingException("The element that corresponds to that ID is not a Process.");
	}
	
	public Phase() {
		this.id = Moona.GenerateID();
		Moona.AddPhase(this);
	}
}
