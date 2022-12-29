package moonaframework.base;

import moonaframework.util.Status;

public final class Setting extends Status<Boolean> {

	public final void enable() {
		super.setValue(true);
	}
	public final void disable() {
		super.setValue(false);
	}
	
	public Setting(boolean init) {
		setValue(init);
	}
	public Setting() {
		disable();
	}
}
