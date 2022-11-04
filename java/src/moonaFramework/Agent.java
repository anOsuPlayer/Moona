package moonaFramework;

import moonaFramework.basics.Serial;
import moonaFramework.event.Event;
import moonaFramework.event.ModalEvent;
import moonaFramework.util.IshMap;

public final class Agent {

	static final IshMap<Event, Long> events = new IshMap<>();
	
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
		totalEvents++;
		totalModals += (e instanceof ModalEvent) ? 1 : 0;
		
		events.add(e, e.id());
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
		totalEvents--;
		totalModals -= (e instanceof ModalEvent) ? 1 : 0;
		
		events.remove(e, e.id());
	}
	
	public static void collapse() {
		
	}
	
	public static Event get(long id) {
		return isEvent(id) ? (Event) events.valueOf(id) : null;
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
