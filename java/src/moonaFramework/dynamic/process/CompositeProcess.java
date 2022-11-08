package moonaFramework.dynamic.process;

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
}
