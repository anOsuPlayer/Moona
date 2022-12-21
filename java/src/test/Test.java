package test;

import java.lang.annotation.Annotation;
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
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
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
import moonaframework.base.Agent;
import moonaframework.base.Constexpr;
import moonaframework.base.Moona;
import moonaframework.base.Nature;
import moonaframework.base.Serial;
import moonaframework.dynamic.Synthetized;
import moonaframework.dynamic.Handler;
import moonaframework.dynamic.Phase;
import moonaframework.dynamic.Processor;
import moonaframework.dynamic.event.*;
import moonaframework.dynamic.process.*;
import moonaframework.util.Benchmark;
import moonaframework.util.Delegate;
import moonaframework.util.annotations.*;
import moonaframework.util.collection.*;
import moonaframework.util.condition.Condition;
import moonaframework.util.functional.Cast;
import moonaframework.util.functional.Mold;
import moonaframework.util.functional.Property;
import moonaframework.util.functional.Snippet;
import moonaframework.util.reflection.*;
import moonaframework.util.time.*;

@SuppressWarnings("unused")

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@interface Annot {
	
}

public class Test {
	
	static Action a = new Action(10) {
		public @Override void trigger() {
			System.out.println("AAAAAAAaa");
			Benchmark.sleep(1000);
		}
	};
	
	public @Timeless void a() {
		
	}
	
	public static void main(String[] args) throws Throwable {
		Reference.Method ref = new Reference.Method(Test.class, "a");
		Annotated ann = new Annotated(ref);
		
		Mirror.add(ann);
		
		Moona.init();
		
		Benchmark.showTime(() -> {
			ann.isAnnotatedWith(Timeless.class);
		});
	}
	
	public Test(@Annot int param1) {
		
	}
}