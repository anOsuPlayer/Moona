package moonaFramework.process;

import java.util.ArrayList;
import java.util.List;

import moonaFramework.MoonaHandlingException;
import moonaFramework.annotations.Deadlined;
import moonaFramework.essentials.Natural;
import moonaFramework.event.Event;
import moonaFramework.event.EventMode;
import moonaFramework.event.ModalEvent;
import moonaFramework.util.IshMap;

public class EventPlace extends Task {

	final IshMap<Event, Long> events;
	
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
		}
		for (Event e : toAdd) {
			events.add(e, e.id());
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
