package moonaframework.util.reflection.flare;

import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.reflection.RecordComponent;
import moonaframework.util.reflection.Type;

public final class RecordContent extends Flare<RecordComponent> {
	
	private final Type target;
	
	public @Override Type getTarget() {
		return this.target;
	}
	
	public @Override boolean equals(Object o) {
		return (o instanceof RecordContent rc) ?
				this.target.equals(rc.target)
				: false;
	}
	
	public @Override String toString() {
		return (!super.hasGenerated) ? "Non-generated Flare." : "RecordContent of " + target;
	}
	
	public @Override void reflect() {
		
	}

	public RecordContent(Type target) throws IllegalArgumentException, NullArgumentException {
		if (target == null) {
			throw new NullArgumentException("SealedProfilers cannot be extracted from a null Type Reference.");
		}
		if (!target.evaluate().isRecord()) {
			throw new IllegalArgumentException("RecordContents can only be built from Type References that"
					+ " target records.");
		}
		this.target = target;
	}
}
