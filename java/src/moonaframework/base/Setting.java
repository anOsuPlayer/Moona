package moonaframework.base;

import moonaframework.util.Status;

public final class Setting extends Status<Boolean> {

	private boolean isFinal = false;
	
	public void enable() {
		if (!isFinal) { super.setValue(true); }
	}
	public void disable() {
		if (!isFinal) { super.setValue(false); }
	}
	
	protected void lock() {
		isFinal = true;
	}
	
	public Setting(boolean init) {
		setValue(init);
	}
	public Setting() {
		disable();
	}
}
