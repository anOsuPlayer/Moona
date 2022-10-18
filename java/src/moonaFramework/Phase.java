package moonaFramework;

import moonaFramework.Moona.Core;
import moonaFramework.process.Process;

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
		if (s == null) {
			throw new NullPointerException("You cannot add null elements to Phases.");
		}
		if (core.has(s, s.id())) {
			throw new MoonaHandlingException("This element already belongs to this Phase.");
		}
		core.addSerial(s);
	}
	
	public void remove(Serial s) throws MoonaHandlingException, NullPointerException {
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
	public Process getProcess(long id) throws MoonaHandlingException {
		return core.valueOf(id) != null ? (core.valueOf(id) instanceof Process) ? (Process) core.valueOf(id)
				: null : null;
	}
	
	public boolean has(Serial s) {
		return core.has(s, s.id());
	}
	
	public Phase() {
		this.core = new Core<>();
		this.id = Moona.generateID();
		Moona.add(this);
	}
}
