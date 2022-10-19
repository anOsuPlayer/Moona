package moonaFramework.event;

import moonaFramework.Moona;

public class EventDaemon extends EventSpace {
	
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
			Moona.interrupt(this);
		}
		
		for (Event e : events.values()) {
			if (e.getMode().equals(EventMode.ONCE)) {
				toRemove.add(e);
			}
			if (e.getMode().equals(EventMode.REPEAT)) {
				if (e.getIterations() - 1 == 0) {
					toRemove.add(e);
				}
				e.setIterations(e.getIterations()-1);
			}
			if (e.getMode().equals(EventMode.UNTIL) && e.getCondition() != null) {
				if (!e.getCondition().verify()) {
					toRemove.add(e);
				}
			}
			e.onTrigger();
		}
	}
	
	public EventDaemon(Event...es) {
		super(es);
	}
	public EventDaemon() {
		super();
	}
}
