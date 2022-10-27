package moonaFramework.process;

import java.util.ArrayList;
import java.util.List;

import moonaFramework.MoonaHandlingException;
import moonaFramework.annotations.Deadlined;
import moonaFramework.essentials.Natural;
import moonaFramework.essentials.Serial;
import moonaFramework.essentials.Container;
import moonaFramework.event.Event;
import moonaFramework.event.EventMode;
import moonaFramework.event.ModalEvent;
import moonaFramework.util.IshMap;

public class EventPlace extends Task implements Serial, Container<Event> {

	final IshMap<Event, Long> events;
	
	private int eventCount = 0;
	
	@Override
	public int nature() {
		return Natural.EVENTSPACE;
	}
	
	final List<Event> toRemove;
	
	final List<Event> toAdd;
	
	public void add(Event e) throws MoonaHandlingException, NullPointerException {
		if (e == null) {
			throw new NullPointerException("You cannot add null Events.");
		}
		if (events.has(e, e.id())) {
			throw new MoonaHandlingException("This Event is already present in this EventSpace.");
		}
		eventCount++;
		toAdd.add(e);
		getClock().release();
	}
	
	@Override
	public void remove(Event e) throws MoonaHandlingException, NullPointerException {
		if (e == null) {
			throw new NullPointerException("You cannot remove null Events.");
		}
		if (!events.has(e, e.id())) {
			throw new MoonaHandlingException("This Event is not present in this EventSpace.");
		}
		eventCount--;
		toRemove.add(e);
	}
	
	@Deadlined
	public final void initialize() {
	}
	@Deadlined
	public final void end() {
	}
	
	@Override
	public void update() {
		for (Event e : toRemove) {
			events.remove(e, e.id());
			eventCount--;
		}
		for (Event e : toAdd) {
			events.add(e, e.id());
			eventCount++;
		}
		
		toRemove.clear();
		toAdd.clear();
		
		if (events.size() == 0) {
			getClock().stasys();
		}
		
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
	
	@Override
	public Event get(long id) {
		return events.valueOf(id);
	}
	
	@Override
	public boolean has(Event e) {
		return events.has(e, e.id());
	}
	
	@Override
	public int elementCount() {
		return eventCount;
	}
	
	public EventPlace() {
		this.events = new IshMap<>();
		this.toRemove = new ArrayList<>();
		this.toAdd = new ArrayList<>();
	}
	public EventPlace(Event...es) {
		this();
		for (Event e : es) {
			events.add(e, e.id());
		}
	}
}
