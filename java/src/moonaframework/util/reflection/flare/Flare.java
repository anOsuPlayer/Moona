package moonaframework.util.reflection.flare;

import java.lang.reflect.Executable;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Member;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

import moonaframework.base.Moona;
import moonaframework.util.annotation.Deadlined;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.Mirror;
import moonaframework.util.reflection.Modifier;
import moonaframework.util.reflection.Reference;
import moonaframework.util.reflection.Reflection;
import moonaframework.util.reflection.Parameter;
import moonaframework.util.reflection.Generic;

public abstract class Flare<R extends Reflection<?>> extends Reflection<List<R>> {

	public static final Flare<Reflection<?>> EMPTY_FLARE = new Flare<>() {
		
		public @Override String toString() {
			return "Empty Flare";
		}
		
		private static final Object PLACEHOLDER = new Object();
		
		public @Deadlined Object getTarget() {
			return PLACEHOLDER;
		}
		
		{
			if (Moona.autoReflections.evaluate()) {
				Mirror.add(this);
			}
		}
	};
	
	final class Modifier_ extends Modifier {	
		Modifier_(Reference<? extends Member> source, int modifiers) {
			super(source, modifiers);
		}
	}
	
	final class Parameter_ extends Parameter {	
		public Parameter_(Reference<? extends Executable> source, int index, java.lang.reflect.Parameter param) {
			super(source, index);
		}
	}
	
	final class Generic_ extends Generic {	
		public Generic_(Reference<? extends GenericDeclaration> source, String name, TypeVariable<?> typevar) {
			super(source, name, typevar);
		}
	}
	
	public @Override final boolean equals(Object o) {
		return (o instanceof Flare<?> beacon) ?
				this.getTarget().equals(beacon.getTarget())
				: false;
	}
	
	public @Override abstract Object getTarget();
	
	protected boolean hasGenerated = false;
	
	public @Override void reflect() throws UndefinedReflectionException {
		for (R refl : super.value) {
			refl.evaluate();
		}
		hasGenerated = true;
	}
	
	public @Override List<R> evaluate() throws UndefinedReflectionException {
		if (!hasGenerated) {
			reflect();
		}
		return super.value;
	}
	
	protected Flare() {
		super(); super.value = new ArrayList<>();
	}
}
