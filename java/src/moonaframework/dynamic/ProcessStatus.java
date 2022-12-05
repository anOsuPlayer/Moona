package moonaframework.dynamic;

import moonaframework.util.Status;
import moonaframework.util.annotations.Deadlined;
import moonaframework.dynamic.process.Process;

public class ProcessStatus extends Status<ProcessCondition> {
	
	public @Override String toString() {
		return evaluate().toString();
	}
	
	protected void set(ProcessCondition pc) {
		super.setValue(pc);
	}
	
	protected void replace(Process p) {
		super.setValue(p.getStatus().evaluate());
	}
	
	protected @Deadlined void reverse() {
		
	}
	
	public ProcessStatus(ProcessCondition pc) {
		super(pc);
	}
}
