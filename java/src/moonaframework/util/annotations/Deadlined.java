package moonaframework.util.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The Deadline Annotation is very similar to the @Override Annotation: they both serve the same purpose,
 * which is marking inherited methods. They just do it in two different situations.
 * <br><br>
 * Deadlining is the same as overriding, but it's slightly more specific: if overridden methods are methods
 * with the same name but with a different body, deadlined elements are those overridden methods which do not
 * declare anything inside of their body. If the method is void, you just put nothing inside the curly
 * brackets; if it's not, you just return a default value which doesn't affect anything if the method gets
 * called.
 * <br><br>
 * The purpose of deadlining is to not force overriding: for example, if you implement an interface and you
 * don't want a method to be specified, you can just deadline it. Deadlined methods are still able to be
 * overridden in subtypes, though: you might not want a method to be declared inside of a class, but you
 * might also want that same method to do something in some subtype of your class.
 * @author MasterZEr0
 */

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface Deadlined {
}
