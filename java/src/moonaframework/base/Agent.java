package moonaframework.base;

import java.util.ArrayList;
import java.util.List;

import moonaframework.dynamic.ProcessCondition;
import moonaframework.dynamic.Processor;
import moonaframework.dynamic.event.Event;
import moonaframework.dynamic.event.EventMode;
import moonaframework.dynamic.event.ModalEvent;
import moonaframework.dynamic.process.Task;
import moonaframework.util.collection.IshMap;
import moonaframework.util.exceptions.NullArgumentException;

public final class Agent {

	static final IshMap<Event, Long> events = new IshMap<>();
	
	private static final List<Event> toAdd = new ArrayList<>();
	
	private static final List<Event> toRemove = new ArrayList<>();
	
	private static int totalEvents = 0;
	
	private static int totalModals = 0;
	
	public static void include(Event e) throws NullArgumentException, MoonaHandlingException {
		Moona.checkOn();
		if (!fader) {
			if (e == null) {
				throw new NullArgumentException("You cannot add null elements to Moona.");
			}
			if (events.hasKey(e.id())) {
				throw new MoonaHandlingException("This Event already belongs to Moona.");
			}
			includeEvent(e);
		}
	}
	static void filteredInclude(Event e) {
		if (!events.hasKey(e.id())) {
			includeEvent(e);
		}
	}
	static void includeEvent(Event e) {
		if (!collapser) {
			toAdd.add(e);
			if (ProcessCondition.DEAD.check(handler)) {
				Processor.start(handler);
			}
		}
	}
	
	public static void exclude(Event e) throws NullArgumentException, MoonaHandlingException {
		Moona.checkOn();
		if (e == null) {
			throw new NullArgumentException("You cannot remove a null element from Moona.");
		}
		if (!events.hasKey(e.id())) {
			throw new MoonaHandlingException("This Event is not present in Moona.");
		}
		excludeEvent(e);
	}
	static void filteredExclude(Event e) {
		if (events.hasKey(e.id())) {
			excludeEvent(e);
		}
	}
	static void excludeEvent(Event e) {
		if (!collapser) {
			toRemove.add(e);
		}
	}
	
	private static void flush() {
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
		public @Override void update() {
			if (collapser) {
				Processor.terminate(handler);
				toRemove.clear(); toAdd.clear(); events.clear();
				collapser = false;
				return;
			}
			
			flush();
			
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
			
			getClock().sleep(1l);
		}
	};
	
	private static boolean collapser = false;
	
	public static void collapse() {
		collapser = true;
	}
	
	private static boolean fader = false;
	
	public static void fade() {
		fader = true;
	}
	
	public static Event get(long id) {
		return events.valueOf(id);
	}
	
	public static boolean isEvent(Serial s) {
		return s instanceof Event;
	}
	public static boolean isModalEvent(Serial s) {
		return s instanceof ModalEvent;
	}
	
	public static boolean isEvent(long id) {
		return events.hasKey(id);
	}
	public static boolean isModalEvent(long id) {
		return events.hasKey(id) && events.valueOf(id) instanceof ModalEvent;
	}
	
	public static boolean contains(Serial s) {
		return s instanceof Event e ? events.has(e, e.id()) : false;
	}
	public static boolean has(long id) {
		return events.hasKey(id);
	}
	public static boolean has(Event e) {
		return has(e.id());
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
