package moonaframework.util;

public class Setting extends Status<Boolean> {

	protected boolean isFinal = false;
	
	protected final Setting[] dependencies;
	
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
		boolean value = super.evaluate().booleanValue();
		for (Setting s : dependencies) {
			value &= s.evaluate().booleanValue();
		}
		return Boolean.valueOf(value);
	}
	
	public Setting(boolean init, Setting...settings) {
		setValue(init);
		this.dependencies = settings;
	}
	public Setting(Setting...settings) {		
		this(false, settings);
	}
	public Setting(boolean init) {
		this(init, new Setting[0]);
	}
	public Setting() {
		this(false, new Setting[0]);
	}
}
