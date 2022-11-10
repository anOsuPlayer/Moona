package moonaFramework.util;

public interface Cast<T> extends Mold<T, Object> {
	
	@Override
	T cast(Object object);
}
