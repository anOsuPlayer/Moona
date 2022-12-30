package moonaframework.dynamic;

import moonaframework.dynamic.process.AbstractProcess;
import moonaframework.util.Packable;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.functional.Snippet;

public class Synthetized extends AbstractProcess implements Packable<Snippet> {

	private final Snippet[] instructions = new Snippet[5];
	
	public @Override Snippet[] pack() {
		return instructions;
	}
	
	public @Override void onPause() {
		instructions[3].run();
	}
	public void setPause(Snippet s) {
		instructions[3] = s;
	}
	
	public @Override void onUnpause() {
		instructions[4].run();
	}
	public void setUnpause(Snippet s) {
		instructions[4] = s;
	}
	
	public @Override void initialize() {
		instructions[1].run();
	}
	public void setInitialize(Snippet s) {
		instructions[1] = s;
	}
	
	public @Override void update() {
		instructions[0].run();
	}
	public void setUpdate(Snippet s) {
		instructions[0] = s;
	}
	
	public @Override void end() {
		instructions[2].run();
	}
	public void setEnd(Snippet s) {
		instructions[2] = s;
	}
	
	public Synthetized(Snippet...instructions) throws NullArgumentException, IllegalArgumentException {
		if (instructions == null) {
			throw new NullArgumentException("You cannot initialize a CompositeProcess using a null array of"
					+ " instructions.");
		}
		if (instructions.length > 5) {
			throw new IllegalArgumentException("In order to initialize CompositeProcesses, no more than 5"
					+ " instructions are allowed.");
		}
		for (int i = 0; i < 5; i++) {
			this.instructions[i] = (instructions[i] == null) ? () -> {} : instructions[i];
		}
	}
	public Synthetized(Snippet updater) {
		this(new Snippet[] {updater});
	}
	public Synthetized() {
		this(new Snippet[5]);
	}
}
