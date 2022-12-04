package moonaframework.dynamic.process;

import java.util.ArrayList;
import java.util.List;

import moonaframework.base.MoonaHandlingException;
import moonaframework.base.Serial;
import moonaframework.dynamic.event.Event;
import moonaframework.dynamic.event.EventMode;
import moonaframework.dynamic.event.ModalEvent;
import moonaframework.util.annotations.Deadlined;
import moonaframework.util.collection.IshMap;
import moonaframework.util.exceptions.NullArgumentException;

public class EventPlace extends Task implements Serial {

	final IshMap<Event, Long> events;
	
	private int eventCount = 0;
	
	private int modalCount = 0;
	
	final List<Event> toRemove;
	
	final List<Event> toAdd;
	
	public void include(Event e) throws MoonaHandlingException, NullArgumentException {
		if (e == null) {
			throw new NullArgumentException("You cannot add null Events.");
		}
		if (events.hasKey(e.id())) {
			throw new MoonaHandlingException("This Event is already present in this EventSpace.");
		}
		eventCount++;
		toAdd.add(e);
		getClock().release();
	}
	
	public void exclude(Event e) throws MoonaHandlingException, NullArgumentException {
		if (e == null) {
			throw new NullArgumentException("You cannot remove null Events.");
		}
		if (!events.hasKey(e.id())) {
			throw new MoonaHandlingException("This Event is not present in this EventSpace.");
		}
		eventCount--;
		toRemove.add(e);
	}
	
	public @Deadlined final void initialize() {
	}
	public @Deadlined final void end() {
	}
	
	protected final void flush() {
		toRemove.forEach((e) -> {
			events.remove(e, e.id());
			eventCount--;
			modalCount -= (e instanceof ModalEvent) ? 1 : 0;
		});
		toRemove.clear();
		
		toAdd.forEach((e) -> {
			events.add(e, e.id());
			eventCount++;
			modalCount += (e instanceof ModalEvent) ? 1 : 0;
		});
		toAdd.clear();
		
		whenEmpty();
	}
	
	protected void whenEmpty() {
		if (eventCount == 0) {
			getClock().stasys();
		}
	}
	
	public @Override void update() {
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
	}
	
	public Event get(long id) {
		return events.valueOf(id);
	}
	
	public boolean has(long id) {
		return events.hasKey(id);
	}
	public boolean has(Event e) {
		return events.has(e, e.id());
	}
	
	public int eventCount() {
		return eventCount;
	}
	public int modalCount() {
		return modalCount;
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
