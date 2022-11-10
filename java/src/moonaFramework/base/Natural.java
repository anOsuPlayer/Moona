package moonaFramework.base;

public interface Natural {

	int EXCEPTION = -1;
	
	int OBJECT = 0;
	
	int PROCESS = 1;
	
	int DAEMON = 2;
	
	int WORM = 5;
	
	int EVENT = 10;
	
	int MODALEVENT = 11;
	
	int AUTOEVENT = 12;
	
	int REFLECTION = 15;
	
	int nature();
}
