package test;

import java.util.ArrayList;
import java.util.List;

import moonaFramework.Benchmark;
import moonaFramework.Moona;
import moonaFramework.ProcessCondition;
import moonaFramework.Status;
import moonaFramework.process.*;
import moonaFramework.util.Condition;

@SuppressWarnings("unused")
public class Test {
	
	static Moona m;
	
	static Daemon d = new Daemon(m) {
		public void update() {
			
		}
	};
	
	public static void main(String[] args) throws InterruptedException {
		
	}
}
