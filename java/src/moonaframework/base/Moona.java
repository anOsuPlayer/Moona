package moonaframework.base;

import java.util.concurrent.atomic.AtomicLong;

import moonaframework.dynamic.Agent;
import moonaframework.dynamic.Processor;
import moonaframework.dynamic.event.Event;
import moonaframework.dynamic.process.Process;
import moonaframework.util.collection.IshMap;
import moonaframework.util.exceptions.NullArgumentException;
import moonaframework.util.reflection.Mirror;
import moonaframework.util.reflection.Reflection;

public final class Moona {
	
	static final IshMap<Serial, Long> elements = new IshMap<>();
	
	static boolean isOn = false;
	
	public static boolean isOn() {
		return isOn;
	}
	
	public static void init() throws MoonaHandlingException {
		if (isOn) {
			throw new MoonaHandlingException("Moona.init() method can only be invoked once.");
		}
		
		isOn = true;
		
		for (Serial s : elements.values()) {
			if (s instanceof Constexpr cx) {
				cx.code.run();
			}
		}
		
		Mirror.loadReflections();
	}
	
	public static void checkOn() throws MoonaHandlingException {
		if (!isOn) { throw new MoonaHandlingException("Moona was not initialized."); }
	}
	
	public static final Setting autoReflections = new Setting(false);
	
	private static AtomicLong idCounter = new AtomicLong(0);
	
	public static long generateID() {
		return idCounter.incrementAndGet();
	}
	
	public static void add(Serial s) throws MoonaHandlingException, NullArgumentException {
		if (s == null) {
			throw new NullArgumentException("You cannot add null elements to Moona.");
		}
		if (elements.hasKey(s.id()) || Processor.contains(s) || Mirror.contains(s) || Agent.contains(s)) {
			throw new MoonaHandlingException("This Serial already belongs to Moona.");
		}
		addSerial(s);
	}
	static void addSerial(Serial s) {
		if (Nature.isProcessLike(s)) {
			Processor.add((Process) s);
			return;
		}
		if (Nature.isEventLike(s)) {
			Agent.include((Event) s);
			return;
		}
		if (Nature.isReflectionLike(s)) {
			Mirror.add((Reflection<?>) s);
			return;
		}
		elements.add(s, s.id());
	}
	
	public static void remove(Serial s) throws MoonaHandlingException, NullArgumentException {
		if (s == null) {
			throw new NullArgumentException("You cannot remove a null element from Moona.");
		}
		if (!elements.hasKey(s.id()) && !Processor.contains(s) && !Mirror.contains(s) &
				!Agent.contains(s)) {
			throw new MoonaHandlingException("This Serial is not present in Moona.");
		}
		removeSerial(s);
	}
	static void removeSerial(Serial s) {
		if (Nature.isProcessLike(s)) {
			Processor.remove((Process) s);
			return;
		}
		if (Nature.isEventLike(s)) {
			Agent.exclude((Event) s);
			return;
		}
		if (Nature.isReflectionLike(s)) {
			Mirror.remove((Reflection<?>) s);
			return;
		}
		elements.remove(s, s.id());
	}
	
	public static void collapse() {
		Processor.collapse(); Mirror.wipe(); Agent.collapse();
		elements.clear();
		Moona.isOn = false;
	}
	
	public static Serial get(long id) {
		Serial s = elements.valueOf(id);
		s = (s != null) ? s : Processor.get(id);
		s = (s != null) ? s : Mirror.get(id);
		s = (s != null) ? s : Agent.get(id);
		return s;
	}
	
	public static boolean has(long id) {
		return elements.hasKey(id) || Processor.has(id) || Mirror.has(id) || Agent.has(id);
	}
	public static boolean has(Serial s) {
		return elements.hasKey(s.id()) || Processor.contains(s) || Mirror.contains(s)
				|| Agent.contains(s);
	}
	
	public static int totalElements() {
		return Processor.totalProcesses() + Mirror.totalReflections() + Agent.totalEvents();
	}
	
	private Moona() {
		
	}
}
