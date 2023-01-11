package moonaframework.util.reflection.flare;

import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.util.ArrayList;
import java.util.List;

import moonaframework.base.MoonaHandlingException;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.Field;
import moonaframework.util.reflection.Modifier;
import moonaframework.util.reflection.RawType;
import moonaframework.util.reflection.Reflection;

public class FieldProperty extends Flare<Reflection<?>> {

	private final Field target;
	
	public @Override Field getTarget() {
		return this.target;
	}
	
	public Modifier getModifiers() throws MoonaHandlingException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		
		return (Modifier) super.value.get(0);
	}
	
	public List<RawType> getTypeArguments() throws MoonaHandlingException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		
		final List<RawType> list = new ArrayList<>();
		for (int i = 1; i < super.value.size(); i++) {
			list.add((RawType) super.value.get(i));
		}
		return list;
	}
	public RawType getTypeArgument(int index) throws IllegalArgumentException, MoonaHandlingException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		
		if (index < 0) {
			throw new IllegalArgumentException("Negative indexes are not allowed.");
		}
		if (index + 1 >= super.value.size()) {
			throw new IllegalArgumentException("There are only " + (super.value.size()-1) + " RawType "
					+ "References, index " + index + " is out of range.");
		}
		
		return (RawType) super.value.get(index+1);
	}
	
	public int typeArgumentsCount() {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		
		return super.value.size()-1;
	}
	
	public @Override boolean equals(Object o) {
		return (o instanceof FieldProperty fp) ? this.getTarget().equals(fp.getTarget()) : false;
	}
	
	public @Override String toString() {
		return (!super.hasGenerated) ? "Non-generated Flare" : "FieldProperty of " + target;
	}
	
	public @Override void reflect() throws UndefinedReflectionException, MoonaHandlingException {
		java.lang.reflect.Field field = target.evaluate();
		
		strictContext.enable();
		
		super.value.add(new Modifier(target));
		
		if (field.getGenericType().getTypeName().contains("<")) {
			AnnotatedParameterizedType apt = null;
			
			if (field.getType().isArray()) {
				AnnotatedArrayType aat = (AnnotatedArrayType) field.getAnnotatedType();
				while (aat.getAnnotatedGenericComponentType() instanceof AnnotatedArrayType) {
					aat = (AnnotatedArrayType) aat.getAnnotatedGenericComponentType();
				}
				apt = (AnnotatedParameterizedType) aat.getAnnotatedGenericComponentType();
			}
			else {
				apt = (AnnotatedParameterizedType) field.getAnnotatedType();
			}
			
			AnnotatedType[] generics = apt.getAnnotatedActualTypeArguments();
			for (AnnotatedType ann : generics) {
				super.value.add(new RawType(ann));
			}
		}
		
		strictContext.disable();
		
		super.reflect();
	}
	
	public FieldProperty(Field target) throws NullArgumentException {
		if (target == null) {
			throw new NullArgumentException("FieldProperties cannot be extracted from a null Field Reference.");
		}
		this.target = target;
	}
}
