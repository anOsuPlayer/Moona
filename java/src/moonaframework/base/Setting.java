package moonaframework.base;

import moonaframework.util.Status;

public final class Setting extends Status<Boolean> {

	public void enable() {
		super.setValue(true);
	}
	public void disable() {
		super.setValue(false);
	}
	
	public Setting(boolean init) {
		setValue(init);
	}
	public Setting() {
		disable();
	}
}
