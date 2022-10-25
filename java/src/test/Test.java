package test;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.stream.Stream;

import moonaFramework.*;
import moonaFramework.annotations.*;
import moonaFramework.event.*;
import moonaFramework.process.*;
import moonaFramework.process.Process;
import moonaFramework.reflection.*;
import moonaFramework.time.*;
import moonaFramework.util.*;

@SuppressWarnings("unused")
public class Test {
	
	@Target(ElementType.TYPE_USE)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Annot {
	}
	
	@Annot
	public static Task t = new Task() {
		public void update() {
			
		}
	};
	
	static Annotated a = new Annotated(t, Annot.class, ElementType.TYPE_USE);
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println(a.evaluate());
	}
}
