package ru.job4j.collection.map;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleHashMap<K, V> implements Iterable<SimpleHashMap.EntrySet<K, V>> {

    private final int capacity = 16;
    @SuppressWarnings("unchecked")
    private EntrySet<K, V>[] tab = new EntrySet[capacity];
    private int size = 0;

    private int hash(K key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private int indexFor(K key) {
        return (capacity - 1) & hash(key);
    }

    private void grow() {
        final int newCapacity = (tab.length * 3) / 2 + 1;
        tab = Arrays.copyOf(tab, newCapacity);
    }

    public boolean insert(K key, V value) {
        if (size == tab.length) {
            grow();
        }
        final int index = indexFor(key);
        if (tab[index] == null) {
            tab[index] = new EntrySet<>(hash(key), key, value);
            size++;
            return true;
        }
        return false;
    }

    public V get(K key) {
        final EntrySet<K, V> el = tab[indexFor(key)];
        if (el != null && el.hash == hash(key)) {
            return el.value;
        } else {
            throw new NoSuchElementException();
        }
    }

    public V delete(K key) {
        final EntrySet<K, V> old;
        final int index = indexFor(key);
        if ((old = tab[index]) != null) {
            tab[index] = null;
            size--;
            return old.value;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public Iterator<EntrySet<K, V>> iterator() {
        return new Iterator<>() {
            int pos = 0;

            @Override
            public boolean hasNext() {
                while (pos < tab.length) {
                    if (tab[pos] != null) {
                        return true;
                    } else {
                        pos++;
                    }
                }
                return false;
            }

            @Override
            public EntrySet<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    return tab[pos++];
                }
            }
        };
    }

    static class EntrySet<K, V> {

        private final int hash;
        private final K key;
        private V value;

        EntrySet(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        public final String toString() {
            return key + " = " + value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public boolean setValue(V value) {
            if (value == null) {
                return false;
            } else {
                this.value = value;
                return true;
            }
        }

        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof EntrySet) {
                final EntrySet<?, ?> e = (EntrySet<?, ?>) o;
                return Objects.equals(key, e.key)
                        && Objects.equals(value, e.value);
            }
            return false;
        }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }
    }
}
