package test;

import java.util.ArrayList;
import java.util.List;
import moonaFramework.Moona;
import moonaFramework.process.*;

@SuppressWarnings("unused")
public class Test {
	
	static Daemon d = new Daemon() {
		public void update() {
			System.out.println("AAAAAAAA");
		}
	};
	
	public static void main(String[] args) {
		Moona.Init();
		Moona.Start(d);
	}
}
