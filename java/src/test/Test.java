package test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.stream.Stream;

import moonaFramework.*;
import moonaFramework.annotations.*;
import moonaFramework.event.*;
import moonaFramework.process.*;
import moonaFramework.util.*;

@SuppressWarnings("unused")
public class Test {
	
	static Series<Integer> s = new Series<Integer>(0, 10) {
		public Integer loop(Integer n) {
			return n + 12;
		}
	};
	
	public static void main(String[] args) throws InterruptedException {
		Moona.init();
		
		s.generate();
		
		System.out.println(s);
	}
}
