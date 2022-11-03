package moonaFramework;

import moonaFramework.basics.Serial;
import moonaFramework.event.Event;
import moonaFramework.event.ModalEvent;

public final class Agent extends Core {

	private static int totalEvents = 0;
	
	private static int totalModals = 0;
	
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
	public static int totalModals() {
		return totalModals;
	}
	
	private Agent() {
		
	}
}
