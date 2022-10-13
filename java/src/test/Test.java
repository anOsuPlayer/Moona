package test;

import moonaFramework.Benchmark;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.stream.Stream;

import moonaFramework.*;
import moonaFramework.ProcessCondition;
import moonaFramework.Status;
import moonaFramework.annotations.*;
import moonaFramework.process.*;
import moonaFramework.util.Condition;

@SuppressWarnings("unused")
public class Test {
	
	static Phase p = new Phase();

	static Task d = new Task() {
		@Timeless
		public void initialize() {
			
		}
		public void update() {
			System.out.println("AAAAAAAAA");
		}
	};
	
	public static void main(String[] args) throws InterruptedException {
		Moona.Init();
		
		p.Start(d);
	}
}
