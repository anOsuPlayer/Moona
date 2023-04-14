package moonaframework.base;

import moonaframework.util.Setting;

public final class MoonaSetting extends Setting {

	public @Override void enable() {
		if (!isFinal && Moona.isOn()) { super.setValue(true); }
	}
	public void disable() {
		if (!isFinal && Moona.isOn()) { super.setValue(false); }
	}
	
	public MoonaSetting(boolean init, Setting...settings) {
		super(init, settings);
	}
	public MoonaSetting(Setting...settings) {		
		this(false, settings);
	}
	public MoonaSetting(boolean init) {
		this(init, new Setting[0]);
	}
	public MoonaSetting() {
		this(false, new Setting[0]);
	}
}
