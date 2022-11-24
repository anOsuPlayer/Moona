package moonaframework.dynamic;

import moonaframework.util.annotations.Deadlined;
import moonaframework.util.condition.Status;
import moonaframework.dynamic.process.Process;

public class ProcessStatus extends Status<ProcessCondition> {
	
	protected void set(ProcessCondition pc) {
		super.setValue(pc);
	}
	
	protected void replace(Process p) {
		super.setValue(p.getStatus().getValue());
	}
	
	@Deadlined
	protected void reverse() {
	}
	
	public ProcessStatus(ProcessCondition pc) {
		super(pc);
	}
}
