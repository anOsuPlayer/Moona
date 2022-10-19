package moonaFramework.event;

public class EventDaemon extends EventSpace {

	
	
	@Override
	public void update() {
		for (Event e : events.values()) {
			if (e.getMode().equals(EventMode.ONCE)) {
				toRemove.add(e);
			}
			if (e.getMode().equals(EventMode.UNTIL)) {
				if (!e.getMode().getCondition().verify()) {
					toRemove.add(e);
				}
			}
			e.onTrigger();
		}
		for (Event e : toRemove) {
			events.remove(e, e.id());
		}
		if (events.size() == 0) {
			getClock().stasys();
		}
	}
	
	public EventDaemon(Event...es) {
		super(es);
	}
	public EventDaemon() {
		super();
	}
}
