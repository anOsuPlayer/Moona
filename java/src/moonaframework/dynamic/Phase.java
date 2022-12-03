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
	void addProcess(Process p) {
		processCount++;
		daemonCount += (p.nature() == Nature.DAEMON) ? 1 : 0; 
		wormCount += (p.nature() == Nature.WORM) ? 1 : 0;
		
		Processor.filteredAdd(p);
		processes.add(p.id());
	}
	
	public void remove(Process p) throws NullArgumentException, MoonaHandlingException {
		if (p == null) {
			throw new NullArgumentException("You cannot remove null elements from a Phase.");
		}
		if (processes.contains(p.id())) {
			throw new MoonaHandlingException("This Process does not belong to this Phase.");
		}
		removeProcess(p);
		processes.remove(p.id());
	}
	void removeProcess(Process p) {
		processCount--;
		daemonCount -= (p.nature() == Nature.DAEMON) ? 1 : 0; 
		wormCount -= (p.nature() == Nature.WORM) ? 1 : 0;
		
		Processor.filteredRemove(p);
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
