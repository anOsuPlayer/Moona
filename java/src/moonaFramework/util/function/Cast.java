package moonaFramework.util.function;

public interface Cast<T> extends Mold<T, Object> {
	
	@Override
	T cast(Object object);
}
