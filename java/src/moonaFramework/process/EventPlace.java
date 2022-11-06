package moonaFramework.process;

import java.util.ArrayList;
import java.util.List;

import moonaFramework.MoonaHandlingException;
import moonaFramework.annotations.Deadlined;
import moonaFramework.basics.Serial;
import moonaFramework.event.Event;
import moonaFramework.event.EventMode;
import moonaFramework.event.ModalEvent;
import moonaFramework.util.IshMap;

public class EventPlace extends Task implements Serial {

	final IshMap<Event, Long> events;
	
	private int eventCount = 0;
	
	private int modalCount = 0;
	
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
	
	@Override
	public void update() {
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
