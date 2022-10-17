package moonaFramework;

import moonaFramework.Moona.Core;

public class Phase implements Serial {
	
	private final long id;
	@Override
	public final long id() {
		return this.id;
	}
	@Override
	public final int nature() {
		return Natural.PHASE;
	}
	
	private final Core<Serial> core;
	
	public void add(Serial s) throws MoonaHandlingException, NullPointerException {
		Moona.checkOn();
		if (s == null) {
			throw new NullPointerException("You cannot add null elements to Phases.");
		}
		if (core.has(s, s.id())) {
			throw new MoonaHandlingException("This element already belongs to this Phase.");
		}
		core.addSerial(s);
	}
	
	public void remove(Serial s) throws MoonaHandlingException, NullPointerException {
		Moona.checkOn();
		if (s == null) {
			throw new NullPointerException("You cannot a null element.");
		}
		if (!core.has(s, s.id())) {
			throw new MoonaHandlingException("This element is not present in this Phase.");
		}
		core.removeSerial(s);
	}
	
	public Serial get(long id) {
		return core.valueOf(id);
	}
	
	public Phase() {
		this.core = new Core<>();
		this.id = Moona.generateID();
		Moona.add(this);
	}
}
