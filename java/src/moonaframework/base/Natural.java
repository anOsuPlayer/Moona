package moonaframework.base;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.RetentionPolicy.*;
import static java.lang.annotation.ElementType.*;

public interface Natural {
	
	@Retention(SOURCE)
	@Target(FIELD)
	public static @interface Nature {
	}
	
	int nature();
}
