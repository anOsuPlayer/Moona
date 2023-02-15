package moonaframework.dynamic;

import java.util.ArrayList;
import java.util.List;

import moonaframework.dynamic.process.Process;
import moonaframework.base.MoonaHandlingException;
import moonaframework.base.MoonaObject;
import moonaframework.dynamic.event.Event;
import moonaframework.dynamic.event.EventMode;
import moonaframework.dynamic.event.ModalEvent;
import moonaframework.dynamic.process.Task;
import moonaframework.util.exception.NullArgumentException;

public final class Agent {

	static final List<Event> events = new ArrayList<>();
	
	private static final List<Event> toAdd = new ArrayList<>();
	
	private static final List<Event> toRemove = new ArrayList<>();
	
	private static int totalEvents = 0;
	
	private static int totalModals = 0;
	
	public static void include(Event e) throws NullArgumentException, MoonaHandlingException {
		if (!fader) {
			if (e == null) {
				throw new NullArgumentException("You cannot add null elements to Moona.");
			}
			if (has(e)) {
				throw new MoonaHandlingException("This Event already belongs to Moona.");
			}
			includeEvent(e);
		}
	}
	public static void inlcude(Event...events) throws NullArgumentException, MoonaHandlingException {
		for (Event e : events) {
			include(e);
		}
	}
	static void filteredInclude(Event e) {
		if (!events.contains(e)) {
			includeEvent(e);
		}
	}
	static void includeEvent(Event e) {
		if (!fader) {
			toAdd.add(e);
			if (ProcessCondition.DEAD.check(handler)) {
				Processor.start(handler);
			}
		}
	}
	
	public static void exclude(Event e) throws NullArgumentException, MoonaHandlingException {
		if (!fader) {
			if (e == null) {
				throw new NullArgumentException("You cannot remove a null element from Moona.");
			}
			if (!has(e)) {
				throw new MoonaHandlingException("This Event is not present in Moona.");
			}
			excludeEvent(e);
		}
	}
	public static void exclude(Event...events) throws NullArgumentException, MoonaHandlingException {
		for (Event e : events) {
			exclude(e);
		}
	}
	static void filteredExclude(Event e) {
		if (events.contains(e)) {
			excludeEvent(e);
		}
	}
	static void excludeEvent(Event e) {
		if (!fader) {
			toRemove.add(e);
		}
	}
	
	private static void flush() {	
		if (!toRemove.isEmpty()) {
			toRemove.forEach((e) -> {
				events.remove(e);
				totalEvents--;
				totalModals -= (e instanceof ModalEvent) ? 1 : 0;
			});
			toRemove.clear();
		}
		
		if (!toAdd.isEmpty()) {
			toAdd.forEach((e) -> {
				events.add(e);
				totalEvents++;
				totalModals += (e instanceof ModalEvent) ? 1 : 0;
			});
			toAdd.clear();
		}
		
		if (totalEvents() == 0) {
			Processor.terminate(handler);
		}
	}
	
	static final Task handler = new Task() {
		public @Override void update() {
			if (collapser) {
				Processor.terminate(handler);
				toRemove.clear(); toAdd.clear(); events.clear();
				return;
			}
			
			flush();
			
			for (Event e : events) {
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
		
		public @Override void end() {
			collapser = false; fader = false;
		}
	};
	
	private static boolean collapser = false;
	
	public static void collapse() {
		if (handler.isRunning()) {
			collapser = true; fader = false;
		}
	}
	
	private static boolean fader = false;
	
	public static void fade() {
		if (handler.isRunning()) {
			fader = true; collapser = false;
		}
	}
	
	public static boolean isEvent(MoonaObject mo) {
		return mo instanceof Event;
	}
	public static boolean isModalEvent(MoonaObject mo) {
		return mo instanceof ModalEvent;
	}
	
	public static boolean contains(MoonaObject mo) {
		return mo instanceof Event e ? has(e) : false;
	}
	public static boolean has(Event e) {
		return events.contains(e) || toAdd.contains(e);
	}
	
	public static int totalEvents() {
		return totalEvents;
	}
	public static int eventCount() {
		return totalEvents - totalModals;
	}
	public static int modalCount() {
		return totalModals;
	}
	
	private Agent() {
		
	}
}
