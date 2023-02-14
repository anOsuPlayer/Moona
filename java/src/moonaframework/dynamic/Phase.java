package moonaframework.dynamic;

import java.util.ArrayList;
import java.util.List;

import moonaframework.base.MoonaHandlingException;
import moonaframework.dynamic.process.Daemon;
import moonaframework.dynamic.process.Process;
import moonaframework.dynamic.process.Worm;
import moonaframework.util.exception.NullArgumentException;

public class Phase {

	private final List<Process> processes;
	
	private int processCount = 0;
	
	private int daemonCount = 0;
	
	private int wormCount = 0;
	
	public void add(Process p) throws NullArgumentException, MoonaHandlingException {
		if (p == null) {
			throw new NullArgumentException("You cannot add null elements to a Phase.");
		}
		if (has(p)) {
			throw new MoonaHandlingException("This Process already belongs to this Phase.");
		}
		addProcess(p);
	}
	private void countProcess(Process p) {
		processCount++;
		daemonCount += (p instanceof Daemon) ? 1 : 0; 
		wormCount += (p instanceof Worm) ? 1 : 0;
		
		processes.add(p);
	}
	void addProcess(Process p) {
		countProcess(p);
		Processor.filteredAdd(p);
	}
	
	public void remove(Process p) throws NullArgumentException, MoonaHandlingException {
		if (p == null) {
			throw new NullArgumentException("You cannot remove null elements from a Phase.");
		}
		if (!has(p)) {
			throw new MoonaHandlingException("This Process does not belong to this Phase.");
		}
		removeProcess(p);
	}
	private void uncountProcess(Process p) {
		processCount--;
		daemonCount -= (p instanceof Daemon) ? 1 : 0; 
		wormCount -= (p instanceof Worm) ? 1 : 0;
		
		processes.remove(p);
	}
	void removeProcess(Process p) {
		uncountProcess(p);
		Processor.filteredRemove(p);
	}

	public void provide(Process p) throws MoonaHandlingException, NullArgumentException {
		if (has(p)) {
			throw new MoonaHandlingException("The Process you're trying to provide already belongs to this"
					+ " Phase.");
		}
		countProcess(p);
		Processor.provide(p);
	}

	public void await(Process p) throws MoonaHandlingException, NullArgumentException {
		if (has(p)) {
			throw new MoonaHandlingException("The Process you're trying to await already belongs to this"
					+ " Phase.");
		}
		countProcess(p);
		Processor.await(p);
	}
	
	public void unlock(Process p) throws MoonaHandlingException, NullArgumentException {
		if (!has(p)) {
			throw new MoonaHandlingException("The Process you're trying to unlock does not belong to this"
					+ " Phase");
		}
		Processor.unlock(p);
	}
	
	public void initiate(Process p) throws MoonaHandlingException, NullArgumentException {
		if (has(p)) {
			throw new MoonaHandlingException("The Process you're trying to initiate already belongs to this"
					+ " Phase.");
		}
		countProcess(p);
		Processor.initiate(p);
	}
	
	public void start(Process p) throws MoonaHandlingException, NullArgumentException {
		if (has(p)) {
			throw new MoonaHandlingException("The Process you're trying to start already belongs to this"
					+ " Phase.");
		}
		countProcess(p);
		Processor.start(p);
	}
	
	public void flick(Process p) throws MoonaHandlingException, NullArgumentException {
		if (!has(p)) {
			throw new MoonaHandlingException("The Process you're trying to flick does not belong to this"
					+ " Phase");
		}
		Processor.flick(p);
	}
	
	public void spark(Process p) throws MoonaHandlingException, NullArgumentException {
		if (!has(p)) {
			throw new MoonaHandlingException("The Process you're trying to spark does not belong to this"
					+ " Phase");
		}
		Processor.spark(p);
	}
	
	public void terminate(Process p) throws MoonaHandlingException, NullArgumentException {
		if (!has(p)) {
			throw new MoonaHandlingException("The Process you're trying to terminate does not belong to this"
					+ " Phase");
		}
		uncountProcess(p);
		Processor.terminate(p);
	}

	public void interrupt(Process p) throws MoonaHandlingException, NullArgumentException {
		if (!has(p)) {
			throw new MoonaHandlingException("The Process you're trying to interrupt does not belong to this"
					+ " Phase");
		}
		uncountProcess(p);
		Processor.interrupt(p);
	}
	
	public void fade() {
		for (Process p : processes) {
			Processor.terminate(p);
		}
		processes.clear();
	}
	public void collapse() {
		for (Process p : processes) {
			Processor.interrupt(p);
		}
		processes.clear();
	}
	
	public boolean has(Process p) {
		return processes.contains(p);
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
			processes.add(p);
		}
	}
}
