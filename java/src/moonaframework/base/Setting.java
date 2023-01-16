package moonaframework.base;

import moonaframework.util.Status;

public final class Setting extends Status<Boolean> {

	private boolean isFinal = false;
	
	private final Setting[] chains;
	
	public void enable() {
		if (!isFinal) { super.setValue(true); }
	}
	public void disable() {
		if (!isFinal) { super.setValue(false); }
	}
	
	protected void lock() {
		isFinal = true;
	}
	
	public @Override Boolean evaluate() {
		Boolean value = Boolean.valueOf(super.evaluate());
		for (Setting s : chains) {
			value &= s.evaluate();
		}
		return value;
	}
	
	public Setting(boolean init, Setting...settings) {
		setValue(init);
		this.chains = settings;
	}
	public Setting(boolean init) {
		this(init, new Setting[0]);
	}
	public Setting() {
		this(false, new Setting[0]);
	}
}
