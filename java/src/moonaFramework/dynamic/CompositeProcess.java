package moonaFramework.dynamic;

import moonaFramework.dynamic.process.AbstractProcess;
import moonaFramework.util.function.Snippet;

public class CompositeProcess extends AbstractProcess {

	private final Snippet[] instructions = new Snippet[5];
	
	@Override
	public void onPause() {
		instructions[3].code();
	}
	public void setPause(Snippet s) {
		instructions[3] = s;
	}
	
	@Override
	public void onUnpause() {
		instructions[4].code();
	}
	public void setUnpause(Snippet s) {
		instructions[4] = s;
	}
	
	@Override
	public void initialize() {
		instructions[1].code();
	}
	public void setInitialize(Snippet s) {
		instructions[1] = s;
	}
	
	@Override
	public void update() {
		instructions[0].code();
	}
	public void setUpdate(Snippet s) {
		instructions[0] = s;
	}
	
	@Override
	public void end() {
		instructions[2].code();
	}
	public void setEnd(Snippet s) {
		instructions[2] = s;
	}
	
	public CompositeProcess(Snippet...instructions) throws NullPointerException, IllegalArgumentException {
		if (instructions == null) {
			throw new NullPointerException("You cannot initialize a CompositeProcess using a null array of"
					+ " instructions.");
		}
		if (instructions.length > 5) {
			throw new IllegalArgumentException("In order to initialize CompositeProcesses, no more than 5"
					+ " instructions are allowed.");
		}
		for (int i = 0; i < instructions.length; i++) {
			this.instructions[i] = instructions[i];
		}
		for (int i = 0; i < 5; i++) {
			this.instructions[i] = (this.instructions[i] == null) ? () -> {} : this.instructions[i];
		}
	}
	public CompositeProcess(Snippet updater) {
		this(new Snippet[] {updater});
	}
	public CompositeProcess() {
		this(new Snippet[5]);
	}
}
