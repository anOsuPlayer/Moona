package moonaFramework;

import java.util.ArrayList;
import java.util.List;

import moonaFramework.basics.Serial;
import moonaFramework.event.Event;
import moonaFramework.event.EventMode;
import moonaFramework.event.ModalEvent;
import moonaFramework.process.Task;

public final class Agent extends Core {

	private static int totalEvents = 0;
	
	private static int totalModals = 0;
	
	private static final List<Event> toAdd = new ArrayList<>();
	private static final List<Event> toRemove = new ArrayList<>();
	
	public static void add(Event e) throws NullPointerException, MoonaHandlingException {
		if (e == null) {
			throw new NullPointerException("You cannot add null elements to Moona.");
		}
		if (elements.has(e, e.id())) {
			throw new MoonaHandlingException("This Event already belongs to Moona.");
		}
		addEvent(e);
	}
	static void addEvent(Event e) {
		totalEvents++;
		totalModals += (e instanceof ModalEvent) ? 1 : 0;
		
		elements.add(e, e.id());
	}
	
	public static void remove(Event e) throws NullPointerException, MoonaHandlingException {
		if (e == null) {
			throw new NullPointerException("You cannot remove a null element from Moona.");
		}
		if (!elements.has(e, e.id())) {
			throw new MoonaHandlingException("This Event is not present in Moona.");
		}
		removeEvent(e);
	}
	static void removeEvent(Event e) {
		totalEvents--;
		totalModals -= (e instanceof ModalEvent) ? 1 : 0;
		
		elements.remove(e, e.id());
	}
	
	private static final List<Event> currentEvents = new ArrayList<>();
	
	private static void updateEvents() {
		elements.forEachValue((s) -> {
			if (!currentEvents.contains(s) && s instanceof Event e) {
				currentEvents.add(e);
			}
		});
	}
	
	private static void flush() {
		toAdd.forEach((e) -> elements.add(e, e.id()));
		toRemove.forEach((e) -> elements.remove(e, e.id()));
		if (toAdd.size() + toRemove.size() != 0) {
			updateEvents();
		}
		toAdd.clear(); toRemove.clear();
	}
	
	private static final Task handler = new Task() {
		public void update() {
			flush();
			for (Event e : currentEvents) {
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
				getClock().sleep(1l);
			}
		}
	};
	
	public static Event get(long id) {
		return isEvent(id) ? (Event) elements.valueOf(id) : null;
	}
	
	public static void collapse() {
		
	}
	
	public static boolean isEvent(Serial s) {
		return s instanceof Event;
	}
	public static boolean isModalEvent(Serial s) {
		return s instanceof ModalEvent;
	}
	
	public static boolean isEvent(long id) {
		return elements.valueOf(id) instanceof Event;
	}
	public static boolean isModalEvent(long id) {
		return elements.valueOf(id) instanceof ModalEvent;
	}
	
	public static int totalEvents() {
		return totalEvents;
	}
	
	static {
		Processor.start(handler);
	}
	
	private Agent() {
		
	}
}
