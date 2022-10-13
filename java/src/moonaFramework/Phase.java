package moonaFramework;

import moonaFramework.annotations.Timeless;
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
	
	final IshMap<Serial, Long> elements;
	
	public void mainStart(Process p) {
		Moona.checkOn();
		add(p);
		ProcessCondition.RUNNING.set(p);
		p.initialize();
		p.run();
		remove(p);
		ProcessCondition.DEAD.set(p);
		p.end();
	}
	
	public void add(Serial s) throws MoonaHandlingException, NullPointerException {
		Moona.checkOn();
		if (s == null) {
			throw new NullPointerException();
		}
		if (elements.has(s, s.id())) {
			throw new MoonaHandlingException("Moona already contains this element.");
		}
		Moona.silentAdd(this, s);
	}
	public void remove(Serial s) throws MoonaHandlingException, NullPointerException {
		Moona.checkOn();
		if (s == null) {
			throw new NullPointerException();
		}
		if (!elements.has(s, s.id())) {
			throw new MoonaHandlingException("Moona does not contain this element.");
		}
		Moona.silentRemove(this, s);
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
			throw new NullPointerException();
		}
		if (!ProcessCondition.DEAD.check(p)) {
			throw new MoonaHandlingException("A process cannot be provided if already running, awaiting,"
					+ " or paused.");
		}
		Moona.silentAdd(this, p);
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
		if (elements.valueOf(id) instanceof Process p) {
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
		Moona.silentAdd(this, p);
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
			throw new NullPointerException();
		}
		if (p.isRunning().verify()) {
			throw new MoonaHandlingException("The Process is already running.");
		}
		if (ProcessCondition.AWAITING.check(p)) {
			throw new MoonaHandlingException("An awaiting process cannot be started: you need to"
					+ " unlock it.");	
		}
		Moona.silentAdd(this, p);
		ProcessCondition.RUNNING.set(p);
		initiator(p);
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
			throw new NullPointerException();
		}
		if (ProcessCondition.DEAD.check(p)) {
			throw new MoonaHandlingException("You cannot interrupt a process which is not running"
					+ " or awaiting.");
		}
		boolean wasPaused = p.isPaused().verify();
		Moona.silentRemove(this, p);
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
			ender(p);
		}
	}
	
	public void fade() throws MoonaHandlingException {
		Moona.checkOn();
		Process[] procs = new Process[processCount+daemonCount];
		for (int i = 0, c = 0; i < elements.size(); i++) {
			procs[c] = (elements.getValue(i) instanceof Process p) ? p : procs[c];
			c += (procs[c] != null) ? 1 : 0;
		}
		for (Process p : procs) {
			terminate(p);
		}
		Moona.isOn = false;
	}
	public void collapse() throws MoonaHandlingException {
		Moona.checkOn();
		Process[] procs = new Process[processCount+daemonCount];
		for (int i = 0, c = 0; i < elements.size(); i++) {
			procs[c] = (elements.getValue(i) instanceof Process p) ? p : procs[c];
			c += (procs[c] != null) ? 1 : 0;
		}
		for (Process p : procs) {
			interrupt(p);
		}
		Moona.isOn = false;
	}

	private final void initiator(Process p) {
			try {
				if (p.getClass().getMethod("initialize", new Class<?>[0])
						.getAnnotation(Timeless.class) != null) {
					
					new Thread(() -> {
						p.initialize();
					}, "initiator#" + p.id()).start();
				}
				else {
					p.initialize();
				}
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
	}
	private final void ender(Process p) {
		try {
			if (p.getClass().getMethod("end", new Class<?>[0])
					.getAnnotation(Timeless.class) != null) {
				
				new Thread(() -> {
					p.end();
				}, "ender#" + p.id()).start();
			}
			else {
				p.end();
			}
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}
	
	long elementCount = 0;
	public long elementCount() {
		return elementCount;
	}
	
	public Serial getElementByID(long id) throws MoonaHandlingException {
		Moona.checkOn();
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
		Moona.checkOn();
		if (getElementByID(id) instanceof Process proc) {
			return proc;
		}
		throw new MoonaHandlingException("The element that corresponds to that ID is not a Process.");
	}
	
	public Phase() {
		this.id = Moona.GenerateID();
		this.elements = new IshMap<>();
		Moona.AddPhase(this);
	}
}
