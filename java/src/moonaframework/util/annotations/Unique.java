package moonaframework.util.annotations;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({ TYPE, FIELD, TYPE_USE, ANNOTATION_TYPE })
@SuppressWarnings("all")
public @interface Unique {
}
