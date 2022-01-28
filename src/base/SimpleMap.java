package base;

/*
 * Simple version of HashMap, using always String as key, int as value.
 * @author Rael G.C. (rael.gc@gmail.com)
 */

public class SimpleMap {

    /**
     * The maximum capacity, used if a higher value is implicitly specified
     * by either of the constructors with arguments.
     * MUST be a power of two <= 1<<30.
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * The table, resized as necessary. Length MUST Always be a power of two.
     */
    private Entry[] table;

    /**
     * The number of key-value mappings contained in this map.
     */
    private int size;

    /**
     * The next size value at which to resize (capacity * load factor).
     * @serial
     */
    private int threshold;

    /**
     * The load factor for the hash table.
     *
     * @serial
     */
    private final float loadFactor = 0.75f;

    /**
     * Constructs an empty <tt>SimpleMap</tt> with the default initial capacity
     * (16) and the default load factor (0.75).
     */
    public SimpleMap() {
        threshold = (int)(16 * loadFactor);
        table = new Entry[16];
    }

    /**
     * Applies a supplemental hash function to a given hashCode, which
     * defends against poor quality hash functions.  This is critical
     * because SimpleMap uses power-of-two length hash tables, that
     * otherwise encounter collisions for hashCodes that do not differ
     * in lower bits. Note: Null keys always map to hash 0, thus index 0.
     */
    private final static int hash(final int h) {
        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
        //h ^= (h >>> 20) ^ (h >>> 12);
        return ((h >>> 20) ^ (h >>> 12)) ^ (h >>> 7) ^ (h >>> 4);
    }

    /**
     * Returns index for hash code h.
     */
    private final static int indexFor(final int h, final int length) {
        return h & (length-1);
    }

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return the number of key-value mappings in this map
     */
    public final int size() {
        return size;
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * <p>More formally, if this map contains a mapping from a key
     * {@code k} to a value {@code v} such that {@code (key==null ? k==null :
     * key.equals(k))}, then this method returns {@code v}; otherwise
     * it returns {@code null}.  (There can be at most one such mapping.)
     *
     * <p>A return value of {@code null} does not <i>necessarily</i>
     * indicate that the map contains no mapping for the key; it's also
     * possible that the map explicitly maps the key to {@code null}.
     * The {@link #containsKey containsKey} operation may be used to
     * distinguish these two cases.
     *
     * @see #put(String, int)
     */
    public final short get(final String key) {
        if (key == null)
            return (short) 0;
        int hash = hash(key.hashCode());
        for (Entry e = table[indexFor(hash, table.length)];
             e != null;
             e = e.next) {
            String k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k)))
                return e.value;
        }
        return (short) 0;
    }

    /**
     * Returns <tt>true</tt> if this map contains a mapping for the
     * specified key.
     *
     * @param   key   The key whose presence in this map is to be tested
     * @return <tt>true</tt> if this map contains a mapping for the specified
     * key.
     */
    public final boolean containsKey(final String key) {
        return getEntry(key) != null;
    }

    /**
     * Returns the entry associated with the specified key in the
     * SimpleMap.  Returns null if the SimpleMap contains no mapping
     * for the key.
     */
    private final Entry getEntry(final String key) {
        int hash = (key == null) ? 0 : hash(key.hashCode());
        String k;
        for (Entry e = table[indexFor(hash, table.length)];
             e != null;
             e = e.next) {
            if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                return e;
        }
        return null;
    }


    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old
     * value is replaced.
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with <tt>key</tt>, or
     *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
     *         (A <tt>null</tt> return can also indicate that the map
     *         previously associated <tt>null</tt> with <tt>key</tt>.)
     */
    public final short put(final String key, final short value) {
        int hash = hash(key.hashCode());
        int i = indexFor(hash, table.length);
        String k;
        for (Entry e = table[i]; e != null; e = e.next) {
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                short oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }

        addEntry(hash, key, value, i);
        return 0;
    }

    /**
     * Rehashes the contents of this map into a new array with a
     * larger capacity.  This method is called automatically when the
     * number of keys in this map reaches its threshold.
     *
     * If current capacity is MAXIMUM_CAPACITY, this method does not
     * resize the map, but sets threshold to Integer.MAX_VALUE.
     * This has the effect of preventing future calls.
     *
     * @param newCapacity the new capacity, MUST be a power of two;
     *        must be greater than current capacity unless current
     *        capacity is MAXIMUM_CAPACITY (in which case value
     *        is irrelevant).
     */
    private final void resize(final int newCapacity) {
        Entry[] oldTable = table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return;
        }

        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int)(newCapacity * loadFactor);
    }

    /**
     * Transfers all entries from current table to newTable.
     */
    private final void transfer(final Entry[] newTable) {
        Entry[] src = table;
        int newCapacity = newTable.length;
        for (int j = 0; j < src.length; j++) {
            Entry e = src[j];
            if (e != null) {
                src[j] = null;
                do {
                    Entry next = e.next;
                    int i = indexFor(e.hash, newCapacity);
                    e.next = newTable[i];
                    newTable[i] = e;
                    e = next;
                } while (e != null);
            }
        }
    }

    static final class Entry {
        final String key;
        short value;
        Entry next;
        final int hash;

        /**
         * Creates new entry.
         */
        Entry(final int h, String k, final short v, final Entry n) {
            value = v;
            next = n;
            key = k;
            hash = h;
        }

        public final String getKey() {
            return key;
        }

        public final int getValue() {
            return value;
        }

        public final int setValue(short newValue) {
            int oldValue = value;
            value = newValue;
            return oldValue;
        }

    }

    /**
     * Adds a new entry with the specified key, value and hash code to
     * the specified bucket.  It is the responsibility of this
     * method to resize the table if appropriate.
     *
     * Subclass overrides this to alter the behavior of put method.
     */
    private final void addEntry(int hash, String key, short value, int bucketIndex) {
        Entry e = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, e);
        if (size++ >= threshold) {
            resize(2 * table.length);
        }
    }
}