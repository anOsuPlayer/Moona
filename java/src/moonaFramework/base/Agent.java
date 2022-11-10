package moonaFramework.base;

import java.util.ArrayList;
import java.util.List;

import moonaFramework.dynamic.ProcessCondition;
import moonaFramework.dynamic.Processor;
import moonaFramework.dynamic.event.Event;
import moonaFramework.dynamic.event.EventMode;
import moonaFramework.dynamic.event.ModalEvent;
import moonaFramework.dynamic.process.Task;
import moonaFramework.util.collection.IshMap;

public final class Agent {

	static final IshMap<Event, Long> events = new IshMap<>();
	
	private static final List<Event> toAdd = new ArrayList<>();
	private static final List<Event> toRemove = new ArrayList<>();
	
	private static int totalEvents = 0;
	
	private static int totalModals = 0;
	
	public static void add(Event e) throws NullPointerException, MoonaHandlingException {
		if (e == null) {
			throw new NullPointerException("You cannot add null elements to Moona.");
		}
		if (events.has(e, e.id())) {
			throw new MoonaHandlingException("This Event already belongs to Moona.");
		}
		addEvent(e);
	}
	static void addEvent(Event e) {
		if (!fader && !collapser) {
			toAdd.add(e);
			if (ProcessCondition.DEAD.check(handler)) {
				Processor.start(handler);
			}
		}
	}
	
	public static void remove(Event e) throws NullPointerException, MoonaHandlingException {
		if (e == null) {
			throw new NullPointerException("You cannot remove a null element from Moona.");
		}
		if (!events.has(e, e.id())) {
			throw new MoonaHandlingException("This Event is not present in Moona.");
		}
		removeEvent(e);
	}
	static void removeEvent(Event e) {
		if (!fader && !collapser) {
			toRemove.add(e);
		}
	}
	
	private static void flush() {
		if (collapser) {
			Processor.terminate(handler);
			toAdd.clear();
			toRemove.clear();
			events.clear();
		}
		
		toRemove.forEach((e) -> {
			events.remove(e, e.id());
			totalEvents--;
			totalModals -= (e instanceof ModalEvent) ? 1 : 0;
		});
		toRemove.clear();
		
		toAdd.forEach((e) -> {
			events.add(e, e.id());
			totalEvents++;
			totalModals += (e instanceof ModalEvent) ? 1 : 0;
		});
		toAdd.clear();
		
		if (totalEvents == 0) {
			Processor.terminate(handler);
		}
	}
	
	static final Task handler = new Task() {
		public void update() {
			flush();
			synchronized (getClock()) {
				for (Event e : events.values()) {
					if (e instanceof ModalEvent me) {
						if (me.getMode().equals(EventMode.ONCE)) {
							toRemove.add(e);
						}
						if (me.getMode().equals(EventMode.REPEAT)) {
							if (me.getIterations() - 1 == 0) {
								toRemove.add(e);
							}
							me.setIterations(me.getIterations()-1);
						}
						if (me.getMode().equals(EventMode.UNTIL) && me.getCondition() != null) {
							if (!me.getCondition().verify()) {
								toRemove.add(me);
							}
						}
					}
					else {
						toRemove.add(e);
					}
					e.trigger();
				}
			}
			getClock().sleep(1l);
		}
	};
	
	private static boolean collapser = false;
	
	private static boolean fader = false;
	
	public static void fade() {
		fader = true;
	}
	public static void collapse() {
		collapser = true;
	}
	
	public static Event get(long id) {
		return isEvent(id) ? events.valueOf(id) : null;
	}
	
	public static boolean isEvent(Serial s) {
		return s instanceof Event;
	}
	public static boolean isModalEvent(Serial s) {
		return s instanceof ModalEvent;
	}
	
	public static boolean isEvent(long id) {
		return events.valueOf(id) instanceof Event;
	}
	public static boolean isModalEvent(long id) {
		return events.valueOf(id) instanceof ModalEvent;
	}
	
	public static boolean contains(Serial s) {
		return s instanceof Event e ? events.has(e, e.id()) : false;
	}
	public static boolean has(long id) {
		return events.hasKey(id);
	}
	public static boolean has(Event e) {
		return events.has(e, e.id());
	}
	
	public static int totalEvents() {
		return totalEvents;
	}
	public static int totalModals() {
		return totalModals;
	}
	
	private Agent() {
		
	}
}
