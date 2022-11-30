package moonaframework.dynamic;

import java.util.ArrayList;
import java.util.List;

import moonaframework.base.MoonaHandlingException;
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
