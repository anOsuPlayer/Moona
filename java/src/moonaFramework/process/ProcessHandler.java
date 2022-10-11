package moonaFramework.process;

import java.util.stream.Stream;
import moonaFramework.Timeless;

public interface ProcessHandler {

	void Provide(Process p);
	
	void Await(Process p);
	void Unlock(Process p);
	
	void Initiate(Process p);
	void Start(Process p);
	
	void Flick(Process p);
	void Spark(Process p);
	
	void Terminate(Process p);
	void Interrupt(Process p);
	
	static void initializer(Process p) {
		if (Stream.of(p.getClass().getMethods())
				.filter(method -> method.getName() == "initialize")
				.findAny().get().getAnnotation(Timeless.class) != null) {
			p.getClock().stasys();
		}
	}
}
