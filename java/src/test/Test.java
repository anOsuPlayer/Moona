package test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.stream.Stream;

import moonaFramework.*;
import moonaFramework.annotations.*;
import moonaFramework.event.*;
import moonaFramework.process.*;
import moonaFramework.reflection.*;
import moonaFramework.time.*;
import moonaFramework.util.*;

@SuppressWarnings("unused")
public class Test {
	
	static Phase p = new Phase();
	
	static Task t = new Task() {
		public void update() {
			System.out.println("BBBBBBBBBB");
		}
	};
	
	static Task t2 = new Task() {
		@Timeless
		public void initialize() {
			
		}
		public void update() {
			System.out.println("AAAAAAAAAA");
		}
	};
	
	static Synchronizer s = new Synchronizer(new Timer(250l), t2, t);
	
	public static void main(String[] args) throws InterruptedException {
		Moona.init();
		
	}
}
