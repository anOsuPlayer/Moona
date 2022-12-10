package moonaframework.dynamic;

import java.util.ArrayList;
import java.util.List;

import moonaframework.base.MoonaHandlingException;
import moonaframework.base.Nature;
import moonaframework.dynamic.process.Process;
import moonaframework.util.exceptions.NullArgumentException;

public class Phase {

	private final List<Long> processes;
	
	private int processCount = 0;
	
	private int daemonCount = 0;
	
	private int wormCount = 0;
	
	public void add(Process p) throws NullArgumentException, MoonaHandlingException {
		if (p == null) {
			throw new NullArgumentException("You cannot add null elements to a Phase.");
		}
		if (processes.contains(p.id())) {
			throw new MoonaHandlingException("This Process already belongs to this Phase.");
		}
		addProcess(p);
	}
	private void countProcess(Process p) {
		processCount++;
		daemonCount += (p.nature() == Nature.DAEMON) ? 1 : 0; 
		wormCount += (p.nature() == Nature.WORM) ? 1 : 0;
		
		processes.add(p.id());
	}
	void addProcess(Process p) {
		countProcess(p);
		Processor.filteredAdd(p);
	}
	
	public void remove(Process p) throws NullArgumentException, MoonaHandlingException {
		if (p == null) {
			throw new NullArgumentException("You cannot remove null elements from a Phase.");
		}
		if (processes.contains(p.id())) {
			throw new MoonaHandlingException("This Process does not belong to this Phase.");
		}
		removeProcess(p);
	}
	private void uncountProcess(Process p) {
		processCount--;
		daemonCount -= (p.nature() == Nature.DAEMON) ? 1 : 0; 
		wormCount -= (p.nature() == Nature.WORM) ? 1 : 0;
		processes.remove(p.id());
	}
	void removeProcess(Process p) {
		uncountProcess(p);
		Processor.filteredRemove(p);
	}
	
	public void provide(long id) throws MoonaHandlingException {
		Processor.provide(id);
		if (!processes.contains(id)) {
			countProcess(Processor.processes.valueOf(id));
		}
	}
	public void provide(Process p) throws MoonaHandlingException, NullArgumentException {
		Processor.provide(p);
		if (!processes.contains(p.id())) {
			countProcess(p);
		}
	}
	
	public void await(long id) throws MoonaHandlingException {
		Processor.await(id);
		if (!processes.contains(id)) {
			countProcess(Processor.processes.valueOf(id));
		}
	}
	public void await(Process p) throws MoonaHandlingException, NullArgumentException {
		Processor.await(p);
		if (!processes.contains(p.id())) {
			countProcess(p);
		}
	}
	
	public void unlock(long id) throws MoonaHandlingException {
		Processor.await(id);
		if (!processes.contains(id)) {
			countProcess(Processor.processes.valueOf(id));
		}
	}
	public void unlock(Process p) throws MoonaHandlingException, NullArgumentException {
		Processor.await(p);
		if (!processes.contains(p.id())) {
			countProcess(p);
		}
	}
	
	public void initiate(long id) throws MoonaHandlingException {
		Processor.initiate(id);
		if (!processes.contains(id)) {
			countProcess(Processor.processes.valueOf(id));
		}
	}
	public void initiate(Process p) throws MoonaHandlingException, NullArgumentException {
		Processor.initiate(p);
		if (!processes.contains(p.id())) {
			countProcess(p);
		}
	}
	
	public void start(long id) throws MoonaHandlingException {
		Processor.start(id);
		if (!processes.contains(id)) {
			countProcess(Processor.processes.valueOf(id));
		}
	}
	public void start(Process p) throws MoonaHandlingException, NullArgumentException {
		Processor.start(p);
		if (!processes.contains(p.id())) {
			countProcess(p);
		}
	}
	
	public void flick(long id) throws MoonaHandlingException {
		if (!processes.contains(id)) {
			throw new MoonaHandlingException("The Process you're trying to flick does not belong to this"
					+ " Phase");
		}
		Processor.flick(id);
	}
	public void flick(Process p) throws MoonaHandlingException, NullArgumentException {
		if (!processes.contains(p.id())) {
			throw new MoonaHandlingException("The Process you're trying to flick does not belong to this"
					+ " Phase");
		}
		Processor.flick(p);
	}
	
	public void spark(long id) throws MoonaHandlingException {
		if (!processes.contains(id)) {
			throw new MoonaHandlingException("The Process you're trying to spark does not belong to this"
					+ " Phase");
		}
		Processor.spark(id);
	}
	public void spark(Process p) throws MoonaHandlingException, NullArgumentException {
		if (!processes.contains(p.id())) {
			throw new MoonaHandlingException("The Process you're trying to spark does not belong to this"
					+ " Phase");
		}
		Processor.spark(p);
	}
	
	public void terminate(long id) throws MoonaHandlingException {
		if (!processes.contains(id)) {
			throw new MoonaHandlingException("The Process you're trying to terminate does not belong to this"
					+ " Phase");
		}
		Processor.terminate(id);
		uncountProcess(Processor.processes.valueOf(id));
	}
	public void terminate(Process p) throws MoonaHandlingException, NullArgumentException {
		if (!processes.contains(p.id())) {
			throw new MoonaHandlingException("The Process you're trying to terminate does not belong to this"
					+ " Phase");
		}
		Processor.terminate(p);
		uncountProcess(p);
	}
	
	public void interrupt(long id) throws MoonaHandlingException {
		if (!processes.contains(id)) {
			throw new MoonaHandlingException("The Process you're trying to interrupt does not belong to this"
					+ " Phase");
		}
		Processor.interrupt(id);
		uncountProcess(Processor.processes.valueOf(id));
	}
	public void interrupt(Process p) throws MoonaHandlingException, NullArgumentException {
		if (!processes.contains(p.id())) {
			throw new MoonaHandlingException("The Process you're trying to interrupt does not belong to this"
					+ " Phase");
		}
		Processor.interrupt(p);
		uncountProcess(p);
	}
	
	public void fade() {
		for (long p : processes) {
			Processor.terminate(p);
		}
		processes.clear();
	}
	public void collapse() {
		for (long p : processes) {
			Processor.interrupt(p);
		}
		processes.clear();
	}
	
	public boolean has(Process p) {
		return processes.contains(p.id());
	}
	public boolean has(long id) {
		return processes.contains(id);
	}
	
	public int totalProcesses() {
		return processCount;
	}
	
	public int processCount() {
		return processCount - daemonCount - wormCount;
	}
	public int totalDaemons() {
		return daemonCount;
	}
	public int totalWorms() {
		return wormCount;
	}
	
	public Phase() {
		this.processes = new ArrayList<>();
	}
	public Phase(Process...procs) {
		this();
		for (Process p : procs) {
			processes.add(p.id());
		}
	}
}
