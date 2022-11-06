package moonaFramework.process;

import moonaFramework.Processor;
import moonaFramework.event.Event;
import moonaFramework.event.EventMode;
import moonaFramework.event.ModalEvent;

public class EventSpace extends EventPlace {
	
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
		
		if (eventCount == 0) {
			Processor.terminate(this);
		}
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
			Processor.interrupt(this);
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
	
	public EventSpace(Event...es) {
		super(es);
	}
	public EventSpace() {
		super();
	}
}
