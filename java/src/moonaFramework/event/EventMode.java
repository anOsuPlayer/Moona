package moonaFramework.event;

import moonaFramework.annotations.Deadlined;
import moonaFramework.util.Conditional;

public enum EventMode {

	ONCE {
		@Deadlined
		public int getIterations() {
			return 1;
		}
		@Deadlined
		public EventMode setIterations(int iterations) throws UnsupportedOperationException {
			throw new UnsupportedOperationException("You cannot set the number of iterations if the Event "
					+ "uses this EventMode.");
		}
		
		@Deadlined
		public Conditional getCondition() {
			return null;
		}
		@Deadlined
		public EventMode setCondition(Conditional c) throws UnsupportedOperationException {
			throw new UnsupportedOperationException("You cannot set the condition of existence if the Event "
					+ "uses this EventMode.");
		}
	},
	
	REPEAT {
		public int getIterations() {
			return iterations;
		}
		public EventMode setIterations(int iterations) {
			super.iterations = iterations;
			return this;
		}
		
		@Deadlined
		public Conditional getCondition() {
			return null;
		}
		@Deadlined
		public EventMode setCondition(Conditional c) throws UnsupportedOperationException {
			throw new UnsupportedOperationException("You cannot set the condition of existence if the Event "
					+ "uses this EventMode.");
		}
	},
	
	UNTIL {
		@Deadlined
		public int getIterations() {
			return -1;
		}
		@Deadlined
		public EventMode setIterations(int iterations) throws UnsupportedOperationException {
			throw new UnsupportedOperationException("You cannot set the number of iterations if the Event "
					+ "uses this EventMode.");
		}
		
		public Conditional getCondition() {
			return existence;
		}
		public EventMode setCondition(Conditional c) {
			super.existence = c;
			return this;
		}
	};
	
	protected Conditional existence = null;
	
	public abstract Conditional getCondition();
	public abstract EventMode setCondition(Conditional c);
	
	protected int iterations = -1;
	
	public abstract int getIterations();
	public abstract EventMode setIterations(int iterations);
	
	protected int iterationDistance = 0;
	
	private EventMode() {
		
	}
}
