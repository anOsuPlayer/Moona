package moonaFramework.util;

import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class IshMap<V, K> {

	private final List<V> values;
	private final List<K> keys;
	
	public int size() {
		return values.size();
	}
	
	public String toString() {
		String str = "[ ";
		for (int i = 0; i < size(); i++) {
			str += (values.get(i) + " - " + keys.get(i) + ((i == size()-1 ? " ]" : " ]\n[ ")));
		}
		return str;
	}
	public IshMap<V, K> clone() {
		return new IshMap<>(values, keys);
	}
	
	private void requireInRange(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException(index);
		}
	}
	
	private boolean isImmutable = false;
	public boolean isImmutable() {
		return isImmutable;
	}
	public void lock() {
		this.isImmutable = true;
	}
	public void unlock() {
		this.isImmutable = false;
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
	public boolean set(int at, Duo<V, K> duo) {
		return set(at, duo.value1, duo.value2);
	}
	public boolean setValue(int at, V value) {
		return set(at, value, null);
	}
	public boolean setKey(int at, K key) {
		return set(at, null, key);
	}
	
	public Duo<V, K> get(int index) throws IndexOutOfBoundsException {
		return new Duo<>(getValue(index), getKey(index));
	}
	public V valueOf(K key) throws IllegalArgumentException {
		if (hasKey(key)) {
			return getValue(indexOfKey(key));
		}
		throw new IllegalArgumentException("No such Key belongs to this IshMap.");
	}
	public V getValue(int index) throws IndexOutOfBoundsException {
		requireInRange(index);
		return values.get(index);
	}
	public K keyOf(V value) throws IllegalArgumentException {
		if (hasValue(value)) {
			return getKey(indexOfValue(value));
		}
		throw new IllegalArgumentException("No such Value belongs to this IshMap.");
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
			return new int[] { -1 };
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
	
	public boolean remove(int at) {
		requireInRange(at);
		if (!isImmutable) {
			values.remove(at); keys.remove(at);
		}
		return isImmutable;
	}
	public boolean removeAll(int[] indexes) {
		if (!isImmutable) {
			for (int at : indexes) {
				values.remove(at); keys.remove(at);
			}
		}
		return isImmutable;
	}
	public boolean remove(V value, K key) {
		return remove(indexOf(value, key));
	}
	public boolean removeOccurrency(int at, V value, K key) {
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
		return values.indexOf(value) >= 0;
	}
	public boolean hasKey(K key) {
		return keys.indexOf(key) >= 0;
	}
	
	private final Reviterable<V> valuesIterator = new Reviterable<V>() {	
		private final Iterator<V> reverse = new Iterator<V>() {
			private int index = -2;
			
			public boolean hasNext() {
				return index >= 0 || index == -2;
			}
			public V next() {
				if (index == -1 || index == -2) { index = size()-1; }
				return values.get(index--);
			}
		};
		
		public Iterator<V> iterator() {
			return values.iterator();
		}
		public Iterable<V> reverse() {
			return new Iterable<V>() {
				public Iterator<V> iterator() {
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
			
			public boolean hasNext() {
				return index >= 0 || index == -2;
			}
			public K next() {
				if (index == -1 || index == -2) { index = size()-1; }
				return keys.get(index--);
			}
		};
		
		public Iterator<K> iterator() {
			return keys.iterator();
		}
		public Iterable<K> reverse() {
			return new Iterable<K>() {
				public Iterator<K> iterator() {
					return reverse;
				}
			};
		}
	}; 
	public Reviterable<K> keys() {
		return keysIterator;
	}
	
	public void forEach(Consumer<? super V> valueFunction, Consumer <? super K> keyFunction) {
		Objects.requireNonNull(valueFunction);
		Objects.requireNonNull(keyFunction);
		for (int i = 0; i < size(); i++) {
			valueFunction.accept(values.get(i));
			keyFunction.accept(keys.get(i));
		}
	}
	public void forEach(BiConsumer<? super V, ? super K> bifunction) {
		Objects.requireNonNull(bifunction);
		for (int i = 0; i < size(); i++) {
			bifunction.accept(values.get(i), keys.get(i));
		}
	}
	public void forEachValue(Consumer<? super V> function) {
		Objects.requireNonNull(function);
		values.forEach(function);
	}
	public void forEachKey(Consumer<? super K> function) {
		Objects.requireNonNull(function);
		keys.forEach(function);
	}
	
	public IshMap<V, K> subMap(int from, int to) throws IllegalArgumentException {
		requireInRange(from); requireInRange(to);
		to++;
		if (from == 0 && to == size()) {
			return clone();
		}
		if (from > to) { throw new IllegalArgumentException("The beginning index of the sub-Ishmap"
				+ " cannot be less the end index."); }
		return new IshMap<>(values.subList(from, to), keys.subList(from, to));
	}
	public IshMap<V, K> subMap(int from) throws IllegalArgumentException {
		return subMap(from, size()-1);
	}
	
	public void clone(IshMap<V, K> branch) throws UnsupportedOperationException, NullPointerException {
		Objects.requireNonNull(branch);
		if ((long) size() + (long) branch.size() >= Integer.MAX_VALUE) {
			throw new UnsupportedOperationException("The sum of the two Ishmaps' lengths goes beyond the"
					+ " Integer Limit, thus they cannot be joined.");
		}
		values.addAll(branch.values);
		keys.addAll(branch.keys);
	}
	public void join(IshMap<V, K> branch) throws UnsupportedOperationException {
		clone(branch);
		branch.clear();
	}
	
	public Object[][] toArray() {
		var arr = new Object[size()][size()];
		arr[0] = values.toArray(); arr[1] = keys.toArray();
		return arr;
	}
	
	public IshMap() {
		this.values = new ArrayList<>();
		this.keys = new ArrayList<>();
	}
	private IshMap(List<V> values, List<K> keys) {
		this.values = values; this.keys = keys;
	}
}
