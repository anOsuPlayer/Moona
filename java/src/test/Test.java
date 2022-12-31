package test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

import moonaframework.*;
import moonaframework.base.Constexpr;
import moonaframework.base.Moona;
import moonaframework.base.MoonaHandlingException;
import moonaframework.base.Natural;
import moonaframework.base.Nature;
import moonaframework.base.Serial;
import moonaframework.dynamic.Synthetized;
import moonaframework.dynamic.Agent;
import moonaframework.dynamic.Handler;
import moonaframework.dynamic.Phase;
import moonaframework.dynamic.Processor;
import moonaframework.dynamic.event.*;
import moonaframework.dynamic.process.*;
import moonaframework.util.Benchmark;
import moonaframework.util.annotation.*;
import moonaframework.util.collection.*;
import moonaframework.util.condition.Condition;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.ReflectionNotFoundException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.functional.Cast;
import moonaframework.util.functional.Mold;
import moonaframework.util.functional.Property;
import moonaframework.util.functional.Snippet;
import moonaframework.util.reflection.*;
import moonaframework.util.reflection.flare.Annotated;
import moonaframework.util.reflection.flare.MethodProperty;
import moonaframework.util.reflection.flare.SealedProfile;
import moonaframework.util.reflection.flare.TypeContent;
import moonaframework.util.relation.Attached;
import moonaframework.util.relation.Delegate;
import moonaframework.util.time.*;

import static java.lang.annotation.ElementType.*;

@SuppressWarnings("unused")

@Retention(RetentionPolicy.RUNTIME)
@Target({ CONSTRUCTOR, FIELD, PARAMETER, TYPE_USE })
@interface Annot {
	int hello();
}

public class Test<T extends @Annot(hello = 0) Serial> {
	
	static Action action = new Action(10) {
		public @Override void trigger() {
			System.out.println("AAAAAAAaa");
			Benchmark.sleep(100);
		}
	};
	
	public @Timeless void a(int a) {
		
	}
	
	public static void main(String[] args) throws Throwable {
		Moona.autoReflections.enable();
		
		Type t = new Type(Test.class);
		
		Annotated annot = new Annotated(t.derive().getGeneric(0).derive().getBound(0));
		
		Moona.init();
		
		System.out.println(annot);
	}
	
	public Test() {
		
	}
}