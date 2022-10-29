package moonaFramework;

import moonaFramework.basics.Serial;
import moonaFramework.event.Event;
import moonaFramework.event.ModalEvent;

public final class Agent {

	private static int totalEvents = 0;
	
	private static int totalModals = 0;
	
	public static void add(Event e) throws NullPointerException, MoonaHandlingException {
		if (e == null) {
			throw new NullPointerException("You cannot add null Moona.elements to Moona.");
		}
		if (Moona.elements.has(e, e.id())) {
			throw new MoonaHandlingException("This Event already belongs to Moona.");
		}
		addEvent(e);
	}
	static void addEvent(Event e) {
		totalEvents++;
		totalModals += (e instanceof ModalEvent) ? 1 : 0;
		
		Moona.elements.add(e, e.id());
	}
	
	public static void remove(Event e) throws NullPointerException, MoonaHandlingException {
		if (e == null) {
			throw new NullPointerException("You cannot remove a null element from Moona.");
		}
		if (!Moona.elements.has(e, e.id())) {
			throw new MoonaHandlingException("This Event is not present in Moona.");
		}
		removeEvent(e);
	}
	static void removeEvent(Event e) {
		totalEvents--;
		totalModals -= (e instanceof ModalEvent) ? 1 : 0;
		
		Moona.elements.remove(e, e.id());
	}
	
	public static Event get(long id) {
		return isEvent(id) ? (Event) Moona.elements.valueOf(id) : null;
	}
	
	public static boolean isEvent(Serial s) {
		return s instanceof Event;
	}
	public static boolean isModalEvent(Serial s) {
		return s instanceof ModalEvent;
	}
	
	public static boolean isEvent(long id) {
		return Moona.elements.valueOf(id) instanceof Event;
	}
	public static boolean isModalEvent(long id) {
		return Moona.elements.valueOf(id) instanceof ModalEvent;
	}
	
	public static int totalEvents() {
		return totalEvents;
	}
	
	private Agent() {
		
	}
}
