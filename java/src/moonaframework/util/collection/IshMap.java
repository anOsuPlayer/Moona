package moonaframework.util.collection;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import moonaframework.util.Reviterable;
import moonaframework.util.exceptions.NullArgumentException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class IshMap<V, K> {

	private final List<V> values;
	private final List<K> keys;
	
	public int size() {
		return values.size();
	}
	
	public @Override String toString() {
		String str = "[ ";
		for (int i = 0; i < size(); i++) {
			str += (values.get(i) + " - " + keys.get(i) + ((i == size()-1 ? " ]" : " ]\n[ ")));
		}
		return str;
	}
	public @Override IshMap<V, K> clone() {
		return new IshMap<>(values, keys);
	}
	
	private void requireInRange(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for this IshMap of"
					+ " size " + size() + ".");
		}
	}
	
	private boolean isImmutable = false;
	public boolean isImmutable() {
		return isImmutable;
	}
	public void setImmutable(boolean value) {
		this.isImmutable = value;
	}
	
	public boolean add(int at, V value, K key) throws IndexOutOfBoundsException {
		requireInRange(at);
		if (!isImmutable) {
			values.add(at, value); keys.add(at, key);
		}
		return isImmutable;
	}
	public boolean add(V value, K key) {
		return add(size(), value, key);
	}
	public boolean add(int at, Duo<V, K> duo) throws IndexOutOfBoundsException {
		return add(at, duo.getValue1(), duo.getValue2());
	}
	public boolean add(FinalDuo<V, K> duo) {
		return add(duo.getValue1(), duo.getValue2());
	}
	public boolean addValue(int at, V value) throws IndexOutOfBoundsException {
		return add(at, value, null);
	}
	public boolean addValue(V value) {
		return add(value, null);
	}
	public boolean addKey(int at, K key) throws IndexOutOfBoundsException {
		return add(at, null, key);
	}
	public boolean addKey(K key) {
		return add(null, key);
	}
	
	public boolean set(int at, V value, K key) throws IndexOutOfBoundsException {
		requireInRange(at);
		if (!isImmutable) {
			values.set(at, value); keys.set(at, key);
		}
		return isImmutable;
	}
	public boolean set(int at, Duo<V, K> duo) throws IndexOutOfBoundsException {
		return set(at, duo.value1, duo.value2);
	}
	public boolean setValue(int at, V value) throws IndexOutOfBoundsException {
		return set(at, value, null);
	}
	public boolean setKey(int at, K key) throws IndexOutOfBoundsException {
		return set(at, null, key);
	}
	
	public Duo<V, K> get(int index) throws IndexOutOfBoundsException {
		return new Duo<>(getValue(index), getKey(index));
	}
	public V valueOf(K key) {
		if (hasKey(key)) {
			return getValue(indexOfKey(key));
		}
		return null;
	}
	public V getValue(int index) throws IndexOutOfBoundsException {
		requireInRange(index);
		return values.get(index);
	}
	public K keyOf(V value) {
		if (hasValue(value)) {
			return getKey(indexOfValue(value));
		}
		return null;
	}
	public K getKey(int index) throws IndexOutOfBoundsException {
		requireInRange(index);
		return keys.get(index);
	}
	
	public int indexOf(V value, K key) {
		for (int i = 0; i < size(); i++) {
			if (getValue(i).equals(value) && getKey(i).equals(key)) { return i; }
		}
		return -1;
	}
	public int[] indexesOf(V value, K key) {
		int[] arr = new int[size()]; int c = 0;
		for (int i = 0; i < size(); i++) {
			arr[c] = (getValue(i).equals(value) && getKey(i).equals(key)) ? i : -1;
			c += (arr[c] == i) ? 1 : 0;
		}
		if (arr[0] == -1) {
			return new int[0];
		}
		return Arrays.copyOf(arr, c);
	}
	public int occurrenciesOf(V value, K key) {
		int c = 0;
		for (int i = 0; i < size(); i++) {
			c += (getValue(i).equals(value) && getKey(i).equals(key)) ? 1 : 0;
		}
		return c;
	}
	public int lastIndexOf(V value, K key) {
		for (int i = size()-1; i >= 0; i--) {
			if (getValue(i).equals(value) && getKey(i).equals(key)) { return i; }
		}
		return -1;
	}
	public int indexOf(Duo<V, K> duo) {
		return indexOf(duo.value1, duo.value2);
	}
	public int[] indexesOf(Duo<V, K> duo) {
		return indexesOf(duo.value1, duo.value2);
	}
	public int occurrenciesOf(Duo<V, K> duo) {
		return occurrenciesOf(duo.value1, duo.value2);
	}
	public int lastIndexOf(Duo<V, K> duo) {
		return lastIndexOf(duo.value1, duo.value2);
	}
	public int indexOfValue(V value) {
		return values.indexOf(value);
	}
 	public int[] indexesOfValue(V value) {
 		int[] arr = new int[size()]; int c = 0;
 		for (int i = 0; i < size(); i++) {
 			arr[c] = (getValue(i).equals(value)) ? i : -1;
 			c += (arr[c] == i) ? 1 : 0;
 		}
 		if (arr[0] == -1) {
 			return new int[0];
 		}
 		return Arrays.copyOf(arr, c);
 	}
 	public int occurrenciesOfValue(V value) {
 		int c = 0;
 		for (int i = 0; i < size(); i++) {
 			c += (getValue(i).equals(value)) ? 1 : 0;
 		}
 		return c;
 	}
	public int lastIndexOfValue(V value) {
		return values.lastIndexOf(value);
	}
	public int indexOfKey(K key) {
		return keys.indexOf(key);
	}
	public int[] indexesOfKey(K key) {
		int[] arr = new int[size()]; int c = 0;
 		for (int i = 0; i < size(); i++) {
 			arr[c] = (getKey(i).equals(key)) ? i : -1;
 			c += (arr[c] == i) ? 1 : 0;
 		}
 		if (arr[0] == -1) {
 			return new int[0];
 		}
 		return Arrays.copyOf(arr, c);
	}
	public int occurrenciesOfKey(K key) {
		int c = 0;
 		for (int i = 0; i < size(); i++) {
 			c += (getKey(i).equals(key)) ? 1 : 0;
 		}
 		return c;
	}
	public int lastIndexOfKey(K key) {
		return keys.lastIndexOf(key);
	}
	
	public boolean remove(int at) throws IndexOutOfBoundsException {
		requireInRange(at);
		if (!isImmutable) {
			values.remove(at); keys.remove(at);
		}
		return isImmutable;
	}
	public boolean removeAll(int[] indexes) throws IndexOutOfBoundsException {
		if (!isImmutable) {
			for (int at : indexes) {
				requireInRange(at);
				values.remove(at); keys.remove(at);
			}
		}
		return isImmutable;
	}
	public boolean remove(V value, K key) {
		return remove(indexOf(value, key));
	}
	public boolean removeOccurrency(int at, V value, K key) throws IndexOutOfBoundsException {
		return remove(indexesOf(value, key)[at]);
	}
	public boolean removeLast(V value, K key) {
		return remove(lastIndexOf(value, key));
	}
	public boolean removeAll(V value, K key) {
		return removeAll(indexesOf(value, key));
	}
	
	public boolean clear() {
		if (!isImmutable) {
			values.clear();
			keys.clear();
		}
		return isImmutable;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public boolean has(V value, K key) {
		return values.contains(value) && keys.contains(key) && indexOf(value, key) >= 0; 
	}
	public boolean hasValue(V value) {
		return values.contains(value);
	}
	public boolean hasKey(K key) {
		return keys.contains(key);
	}
	
	private final Reviterable<V> valuesIterator = new Reviterable<V>() {	
		private final Iterator<V> reverse = new Iterator<V>() {
			private int index = -2;
			
			public @Override boolean hasNext() {
				return index >= 0 || index == -2;
			}
			public @Override V next() {
				if (index == -1 || index == -2) { index = size()-1; }
				return values.get(index--);
			}
		};
		
		public @Override Iterator<V> iterator() {
			return values.iterator();
		}
		public @Override Iterable<V> reverse() {
			return new Iterable<V>() {
				
				public @Override Iterator<V> iterator() {
					return reverse;
				}
			};
		}
	};
	public Reviterable<V> values() {
		return valuesIterator;
	}
	
	private final Reviterable<K> keysIterator = new Reviterable<K>() {
		private final Iterator<K> reverse = new Iterator<K>() {
			private int index = -2;

			public @Override boolean hasNext() {
				return index >= 0 || index == -2;
			}
			public @Override K next() {
				if (index == -1 || index == -2) { index = size()-1; }
				return keys.get(index--);
			}
		};
		
		public @Override Iterator<K> iterator() {
			return keys.iterator();
		}
		public @Override Iterable<K> reverse() {
			return new Iterable<K>() {
				
				public @Override Iterator<K> iterator() {
					return reverse;
				}
			};
		}
	}; 
	public Reviterable<K> keys() {
		return keysIterator;
	}
	
	public void forEach(Consumer<? super V> valueFunction, Consumer <? super K> keyFunction)
			throws NullArgumentException {
		if (valueFunction == null || keyFunction == null) {
			throw new NullArgumentException("One of the functions you tried to pass is null.");
		}
		for (int i = 0; i < size(); i++) {
			valueFunction.accept(values.get(i));
			keyFunction.accept(keys.get(i));
		}
	}
	public void forEach(BiConsumer<? super V, ? super K> bifunction) throws NullArgumentException {
		if (bifunction == null) {
			throw new NullArgumentException("The function you tried to pass is null.");
		}
		for (int i = 0; i < size(); i++) {
			bifunction.accept(values.get(i), keys.get(i));
		}
	}
	public void forEachValue(Consumer<? super V> function) throws NullArgumentException {
		if (function == null) {
			throw new NullArgumentException("The function you tried to pass is null.");
		}
		values.forEach(function);
	}
	public void forEachKey(Consumer<? super K> function) throws NullArgumentException {
		if (function == null) {
			throw new NullArgumentException("The function you tried to pass is null.");
		}
		keys.forEach(function);
	}
	
	public IshMap<V, K> subMap(int from, int to) throws IllegalArgumentException, IndexOutOfBoundsException {
		requireInRange(from); requireInRange(to);
		if (from == 0 && to+1 == size()) {
			return clone();
		}
		if (from > to+1) { throw new IllegalArgumentException("The beginning index of the sub-IshMap"
				+ " cannot be less than the end index."); }
		return new IshMap<>(values.subList(from, to+1), keys.subList(from, to+1));
	}
	public IshMap<V, K> subMap(int from) throws IllegalArgumentException {
		return subMap(from, size()-1);
	}
	
	public void absorb(IshMap<V, K> branch) throws UnsupportedOperationException, NullArgumentException {
		if (branch == null) {
			throw new NullArgumentException("You cannot absorb a null IshMap.");
		}
		values.addAll(branch.values);
		keys.addAll(branch.keys);
	}
	public void join(IshMap<V, K> branch) throws UnsupportedOperationException, NullArgumentException {
		if (branch == null) {
			throw new NullArgumentException("You cannot join a null IshMap.");
		}
		absorb(branch);
		branch.clear();
	}
	
	public Object[][] toArray() {
		var arr = new Object[size()][size()];
		arr[0] = values.toArray(); arr[1] = keys.toArray();
		return arr;
	}
	
	public List<V> listValues() {
		return List.copyOf(values);
	}
	public List<K> listKeys() {
		return List.copyOf(keys);
	}
	
	public IshMap() {
		this.values = new ArrayList<>();
		this.keys = new ArrayList<>();
	}
	private IshMap(List<V> values, List<K> keys) {
		this.values = values; this.keys = keys;
	}
}
