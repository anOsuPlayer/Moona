package moonaframework.base;

import moonaframework.util.Setting;

public final class MoonaSetting extends Setting {

	public @Override void enable() throws MoonaHandlingException {
		if (!isFinal) {
			if (Moona.isOn()) {
				throw new MoonaHandlingException("MoonaSettings cannot be changed after Moona is started.");
			}
			super.setValue(true);
		}
	}
	public void disable() {
		if (!isFinal) {
			if (Moona.isOn()) {
				throw new MoonaHandlingException("MoonaSettings cannot be changed after Moona is started.");
			}
			super.setValue(false);
		}
	}
	
	protected MoonaSetting(boolean init, Setting...settings) {
		super(init, settings);
	}
	protected MoonaSetting(Setting...settings) {		
		this(false, settings);
	}
	protected MoonaSetting(boolean init) {
		this(init, new Setting[0]);
	}
	protected MoonaSetting() {
		this(false, new Setting[0]);
	}
}
